package net.rj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.rj.model.Usuario;

public interface UsuariosRepository extends JpaRepository<Usuario, Integer> {

}
