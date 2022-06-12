package br.comp.unifor.ProjetoMusicas.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.comp.unifor.ProjetoMusicas.repository.dto.PlaylistDTO;
import br.comp.unifor.ProjetoMusicas.repository.entity.Playlist;
import br.comp.unifor.ProjetoMusicas.service.impl.PlaylistServiceImpl;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/playlists")
@RequiredArgsConstructor
public class PlaylistController {
	
	private final PlaylistServiceImpl playlistService;
	
	
	@GetMapping()
	public List<Playlist> listarTodos() {
		return playlistService.listarTodos();
	}
	
	@GetMapping("/{id}")
	public Playlist buscarPorId(@PathVariable Integer id) {
		return playlistService.buscarPorId(id).get();
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping()
	public Playlist salvar(@RequestBody PlaylistDTO playlistDTO) {
		return playlistService.salvar(playlistDTO);
	}
	
	@PutMapping("/{id}")
	public Playlist editar(@PathVariable Integer id, @RequestBody PlaylistDTO playlistDTO) {
		return playlistService.editar(id, playlistDTO);
	}
	
	@PutMapping("/adicionar/{id}/musicas/{idMusica}")
	public Playlist adicionarMusica(@PathVariable Integer id, @PathVariable Integer idMusica) {
		return playlistService.adicionarMusica(id, idMusica);
	}
	
	@PutMapping("/remover/{id}/musicas/{idMusica}")
	public Playlist removerMusica(@PathVariable Integer id, @PathVariable Integer idMusica) {
		return playlistService.removerMusica(id, idMusica);
	}
}
