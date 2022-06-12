package br.comp.unifor.ProjetoMusicas.service;

import java.util.List;
import java.util.Optional;

import br.comp.unifor.ProjetoMusicas.repository.dto.MusicaDTO;
import br.comp.unifor.ProjetoMusicas.repository.entity.Musica;


public interface MusicaService {
	Musica salvar(MusicaDTO musicaDTO);
	Musica editar(Integer id, MusicaDTO musicaDTO);
	List<Musica> listarTodos();
	Optional<Musica> buscarPorId(Integer id);
	void deletar(Integer id);
}
