package br.comp.unifor.ProjetoMusicas.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.comp.unifor.ProjetoMusicas.repository.entity.Playlist;

public interface PlaylistsRepository extends JpaRepository<Playlist, Integer> {
	
	List<Playlist> findPlaylistsByMusicasId(Integer idMusica);

}
