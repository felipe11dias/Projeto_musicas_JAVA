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

import br.comp.unifor.ProjetoMusicas.repository.dto.MusicaDTO;
import br.comp.unifor.ProjetoMusicas.repository.entity.Musica;
import br.comp.unifor.ProjetoMusicas.service.impl.MusicaServiceImpl;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/musicas")
@RequiredArgsConstructor
public class MusicaController {
	
	private final MusicaServiceImpl musicaService;
	
	@GetMapping()
	public List<Musica> listarTodos() {
		return musicaService.listarTodos();
	}
	
	@GetMapping("/{id}")
	public Musica buscarPorId(@PathVariable Integer id) {
		return musicaService.buscarPorId(id).get();
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping()
	public Musica salvar(@RequestBody MusicaDTO musicaDTO) {
		return musicaService.salvar(musicaDTO);
	}
	
	@PutMapping("/{id}")
	public Musica editar(@PathVariable Integer id, @RequestBody MusicaDTO musicaDTO) {
		return musicaService.editar(id, musicaDTO);
	}
	
}
