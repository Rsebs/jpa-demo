package net.rj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import net.rj.model.Categoria;

//public interface CategoriasRepository extends CrudRepository<Categoria, Integer> 
public interface CategoriasRepository extends JpaRepository<Categoria, Integer> {

}
