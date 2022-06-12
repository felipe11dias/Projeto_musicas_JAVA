package br.comp.unifor.ProjetoMusicas.controller;

import static org.springframework.http.HttpStatus.NO_CONTENT;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.comp.unifor.ProjetoMusicas.repository.dto.UsuarioDTO;
import br.comp.unifor.ProjetoMusicas.repository.entity.Usuario;
import br.comp.unifor.ProjetoMusicas.service.impl.UsuarioServiceImpl;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
	
	private final UsuarioServiceImpl usuarioService;
	
	@GetMapping()
	public List<Usuario> listarTodos() {
		return usuarioService.listarTodos();
	}
	
	@GetMapping("/{id}")
	public Usuario buscarPorId(@PathVariable Integer id) {
		return usuarioService.buscarPorId(id);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping()
	public Usuario salvar(@RequestBody UsuarioDTO usuarioDTO) {
		return usuarioService.salvar(usuarioDTO);
	}
	
	@PutMapping("/{id}")
	public Usuario editar(@PathVariable Integer id, @RequestBody UsuarioDTO usuarioDTO) {
		return usuarioService.editar(id, usuarioDTO);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(NO_CONTENT)
	public void deletar(@PathVariable Integer id) {
		usuarioService.deletar(id);
	}
	
}
