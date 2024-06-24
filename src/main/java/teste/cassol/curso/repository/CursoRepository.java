package teste.cassol.curso.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import teste.cassol.curso.entities.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {
	
	Optional<Curso> findByNome(String nome);
	
	@Query("SELECT c FROM Curso c LEFT JOIN FETCH c.matriculas WHERE c.id = :id")
    Optional<Curso> findByIdWithMatriculas(@Param("id") Long id);
	
}