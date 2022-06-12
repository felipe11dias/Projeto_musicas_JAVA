package br.comp.unifor.ProjetoMusicas.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.comp.unifor.ProjetoMusicas.repository.dto.MusicaDTO;
import br.comp.unifor.ProjetoMusicas.repository.entity.Musica;
import br.comp.unifor.ProjetoMusicas.repository.jpa.MusicasRepository;
import br.comp.unifor.ProjetoMusicas.service.MusicaService;

@Service
public class MusicaServiceImpl implements MusicaService {
	
	@Autowired
	private MusicasRepository musicaRepository;
	
	// @Autowired
	// private PlaylistsRepository playlistRepository;
	
	@Override
	public Musica salvar(MusicaDTO musicaDTO) {
		Musica musica = new Musica();
		musica.setNome(musicaDTO.getNome());
		musica.setArtista(musicaDTO.getArtista());
		musica.setPlaylists(musicaDTO.getPlaylists());
		return musicaRepository.save(musica);
	}

	@Override
	public Musica editar(Integer id, MusicaDTO musicaDTO) {
		return musicaRepository.findById(id).map( musica -> { 
			musica.setNome(musicaDTO.getNome());
			musica.setArtista(musicaDTO.getArtista());
			musica.setPlaylists(musicaDTO.getPlaylists());
			return musicaRepository.save(musica);
		})
		.orElseThrow( () -> new ResponseStatusException(HttpStatus.OK, "Não é possivel encontrar a playlist."));
	}

	@Override
	public List<Musica> listarTodos() {
		return musicaRepository.findAll();
	}

	@Override
	public Optional<Musica> buscarPorId(Integer id) {
		return Optional.ofNullable(musicaRepository.findById(id).map( playlist -> playlist )
				.orElseThrow( () -> new ResponseStatusException(HttpStatus.OK, "Não é possivel encontrar a playlist.")));
	}

	@Override
	public void deletar(Integer id) {
		musicaRepository.findById(id)
		  .map(usuario -> {
			  musicaRepository.delete(usuario);
			  return usuario;
		  })
		  .orElseThrow( () -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Não foi possivel encontrar a música."));
	}
}
