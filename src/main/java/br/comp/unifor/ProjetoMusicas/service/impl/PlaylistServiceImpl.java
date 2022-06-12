package br.comp.unifor.ProjetoMusicas.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.comp.unifor.ProjetoMusicas.repository.dto.PlaylistDTO;
import br.comp.unifor.ProjetoMusicas.repository.entity.Musica;
import br.comp.unifor.ProjetoMusicas.repository.entity.Playlist;
import br.comp.unifor.ProjetoMusicas.repository.entity.Usuario;
import br.comp.unifor.ProjetoMusicas.repository.jpa.MusicasRepository;
import br.comp.unifor.ProjetoMusicas.repository.jpa.PlaylistsRepository;
import br.comp.unifor.ProjetoMusicas.repository.jpa.UsuariosRepository;
import br.comp.unifor.ProjetoMusicas.service.PlaylistService;

@Service
public class PlaylistServiceImpl implements PlaylistService {
	
	@Autowired
	private PlaylistsRepository playlistRepository;
	
	@Autowired
	private UsuariosRepository usuarioRepository;
	
	@Autowired
	private MusicasRepository musicaRepository;

	@Transactional
	@Override
	public Playlist salvar(PlaylistDTO playlistDTO) {
		Playlist playlist = new Playlist();
		playlist.setNome(playlistDTO.getNome());
		
		Usuario usuario = usuarioRepository
				.findById(playlistDTO.getUsuarioId())
				.orElseThrow( () -> new ResponseStatusException(HttpStatus.OK, "Código do usuário inválido."));
		
		playlist.setUsuario(usuario);
		
		List<Musica> musicas = converterMusicas(playlist, playlistDTO.getMusicasId());
		usuario.getPlaylists().add(playlist);
		playlistRepository.save(playlist);
		usuarioRepository.save(usuario);
		musicaRepository.saveAll(musicas);
		playlist.setMusicas(musicas);
			
		return playlist;
	}

	@Transactional
	@Override
	public Playlist editar(Integer id, PlaylistDTO playlistDTO) {
		return playlistRepository.findById(id).map( playlist -> { 
			playlist.setNome(playlistDTO.getNome());
			
			Usuario usuario = usuarioRepository
					.findById(playlistDTO.getUsuarioId())
					.orElseThrow( () -> new ResponseStatusException(HttpStatus.OK, "Código do usuário inválido."));
			
			playlist.setUsuario(usuario);
			playlist.setMusicas(converterMusicas(playlist, playlistDTO.getMusicasId()));
			return playlistRepository.save(playlist);
		})
		.orElseThrow( () -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Não é possivel encontrar a playlist."));
	}

	@Override
	public List<Playlist> listarTodos() {
		return playlistRepository.findAll();
	}

	@Override
	public Optional<Playlist> buscarPorId(Integer id) {
		return Optional.ofNullable(playlistRepository.findById(id).map( playlist -> playlist )
				.orElseThrow( () -> new ResponseStatusException(HttpStatus.OK, "Não é possivel encontrar a playlist.")));
	}
	
	@Transactional
	@Override
	public Playlist adicionarMusica(Integer idPlaylist, Integer idMusica) {
		Playlist playlist = playlistRepository
				.findById(idPlaylist)
				.orElseThrow( () -> new ResponseStatusException(HttpStatus.OK, "Não é possivel encontrar a playlist."));
		
		Musica musica = musicaRepository
				.findById(idMusica)
				.orElseThrow( () -> new ResponseStatusException(HttpStatus.OK, "Não é possivel encontrar a música."));
		
		if (playlist.getMusicas().contains(musica)) {
			throw new ResponseStatusException(HttpStatus.OK, "Não é possivel adicionar uma música já existente na playlist.");
		}
		musica.getPlaylists().add(playlist);
		playlist.getMusicas().add(musica);
		musicaRepository.save(musica);
		playlistRepository.save(playlist);
		return playlist;
	}
	
	@Transactional
	@Override
	public Playlist removerMusica(Integer idPlaylist, Integer idMusica) {
		Playlist playlist = playlistRepository
				.findById(idPlaylist)
				.orElseThrow( () -> new ResponseStatusException(HttpStatus.OK, "Não é possivel encontrar a playlist."));
		
		Musica musica = musicaRepository
				.findById(idMusica)
				.orElseThrow( () -> new ResponseStatusException(HttpStatus.OK, "Não é possivel encontrar a música."));
		
		if (!playlist.getMusicas().contains(musica)) {
			throw new ResponseStatusException(HttpStatus.OK, "Não é possivel adicionar uma música já existente na playlist.");
		}
		musica.getPlaylists().remove(playlist);
		playlist.getMusicas().remove(musica);
		musicaRepository.save(musica);
		playlistRepository.save(playlist);
		return playlist;
	}

	@Override
	public void deletar(Integer id) {
		playlistRepository.findById(id)
		  .map(usuario -> {
			  playlistRepository.delete(usuario);
			  return usuario;
		  })
		  .orElseThrow( () -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Não foi possivel encontrar a playlist."));
	}
	
	private List<Musica> converterMusicas(Playlist playlist, List<Integer> musicasId) {
		if(musicasId.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.OK, "Não é possivel salvar/editar a playlist sem músicas.");
		}
		
		return musicasId
				.stream()
				.map( idMusica -> {
					Musica musica = musicaRepository
										.findById(idMusica)
										.orElseThrow( () -> new ResponseStatusException(HttpStatus.OK, "Código da música inválido: " + idMusica));
					musica.getPlaylists().add(playlist);
					return musica;
				}).collect(Collectors.toList());
	}

	
}
