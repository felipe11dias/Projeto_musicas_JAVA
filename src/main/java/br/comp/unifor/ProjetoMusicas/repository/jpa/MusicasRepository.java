package br.comp.unifor.ProjetoMusicas.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.comp.unifor.ProjetoMusicas.repository.entity.Musica;
import br.comp.unifor.ProjetoMusicas.repository.entity.Playlist;

public interface MusicasRepository extends JpaRepository<Musica, Integer> {

	List<Playlist> findMusicasByPlaylistsId(Integer idPlaylist);
}
