package net.rj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.rj.model.Vacante;

public interface VacantesRepository extends JpaRepository<Vacante, Integer> {

}
