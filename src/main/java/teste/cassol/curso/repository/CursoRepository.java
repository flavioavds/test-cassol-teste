package teste.cassol.curso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import teste.cassol.curso.entities.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
	
}