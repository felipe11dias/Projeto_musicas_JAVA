package br.comp.unifor.ProjetoMusicas.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.comp.unifor.ProjetoMusicas.repository.dto.UsuarioDTO;
import br.comp.unifor.ProjetoMusicas.repository.entity.Playlist;
import br.comp.unifor.ProjetoMusicas.repository.entity.Usuario;
import br.comp.unifor.ProjetoMusicas.repository.jpa.UsuariosRepository;
import br.comp.unifor.ProjetoMusicas.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	private UsuariosRepository usuarioRepository;

	@Override
	public Usuario salvar(UsuarioDTO usuarioDTO) {
		Usuario usuario = new Usuario();
		usuario.setNome(usuarioDTO.getNome());
		usuario.setIdade(usuarioDTO.getIdade());
		usuario.setPlaylists(new ArrayList<Playlist>());
		return usuarioRepository.save(usuario);
	}

	@Override
	public Usuario editar(Integer id, UsuarioDTO usuarioDTO) {
		return usuarioRepository.findById(id).map( usuario -> { 
			
			usuario.setNome(usuarioDTO.getNome());
			usuario.setIdade(usuarioDTO.getIdade());
			return usuarioRepository.save(usuario);
		})
		.orElseThrow( () -> new ResponseStatusException(HttpStatus.OK, "Não foi possivel encontrar o usuário."));
	}

	@Override
	public List<Usuario> listarTodos() {
		return usuarioRepository.findAll();
	}

	@Override
	public Usuario buscarPorId(Integer id) {
		return usuarioRepository.findById(id).map( usuario -> usuario )
				.orElseThrow( () -> new ResponseStatusException(HttpStatus.OK, "Não foi possivel encontrar o usuário."));
	}

	@Override
	public void deletar(Integer id) {
		usuarioRepository.findById(id)
						  .map(usuario -> {
							  usuarioRepository.delete(usuario);
							  return usuario;
						  })
						  .orElseThrow( () -> new ResponseStatusException(HttpStatus.OK, "Não foi possivel encontrar o usuário."));
	}

}
