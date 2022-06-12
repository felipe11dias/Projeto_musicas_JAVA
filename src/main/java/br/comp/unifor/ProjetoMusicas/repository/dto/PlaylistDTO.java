package br.comp.unifor.ProjetoMusicas.repository.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistDTO {
	@NotNull
	@NotBlank
	public String nome;
	@NotNull
	public Integer usuarioId;
	
	public List<Integer> musicasId = new ArrayList<>();
}
