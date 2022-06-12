package br.comp.unifor.ProjetoMusicas.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import br.comp.unifor.ProjetoMusicas.repository.entity.Usuario;

public interface UsuariosRepository extends JpaRepository<Usuario, Integer> {

}
