package br.comp.unifor.ProjetoMusicas.service;

import java.util.List;

import br.comp.unifor.ProjetoMusicas.repository.dto.UsuarioDTO;
import br.comp.unifor.ProjetoMusicas.repository.entity.Usuario;

public interface UsuarioService {
	Usuario salvar(UsuarioDTO usuarioDTO);
	Usuario editar(Integer id, UsuarioDTO usuarioDTO);
	List<Usuario> listarTodos();
	Usuario buscarPorId(Integer id);
	void deletar(Integer id);
}
