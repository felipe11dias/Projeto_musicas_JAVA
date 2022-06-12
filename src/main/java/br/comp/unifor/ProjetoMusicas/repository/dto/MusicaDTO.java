package br.comp.unifor.ProjetoMusicas.repository.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.comp.unifor.ProjetoMusicas.repository.entity.Playlist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MusicaDTO {
	@NotNull
	@NotBlank
	public String nome;
	@NotNull
	@NotBlank
	public String artista;
	
	public List<Playlist> playlists = new ArrayList<>();
	
	public Integer playlistId;
}
