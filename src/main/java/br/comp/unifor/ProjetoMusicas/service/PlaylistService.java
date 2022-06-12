package br.comp.unifor.ProjetoMusicas.service;

import java.util.List;
import java.util.Optional;

import br.comp.unifor.ProjetoMusicas.repository.entity.Playlist;
import br.comp.unifor.ProjetoMusicas.repository.dto.PlaylistDTO;

public interface PlaylistService {
	Playlist salvar(PlaylistDTO usuarioDTO);
	Playlist editar(Integer id, PlaylistDTO usuarioDTO);
	Playlist adicionarMusica(Integer idPlaylist, Integer idMusica);
	Playlist removerMusica(Integer idPlaylist, Integer idMusica);
	List<Playlist> listarTodos();
	Optional<Playlist> buscarPorId(Integer id);
	void deletar(Integer id);
}
