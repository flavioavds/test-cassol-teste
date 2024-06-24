package teste.cassol.curso.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import teste.cassol.curso.entities.Aluno;
import teste.cassol.curso.entities.Curso;
import teste.cassol.curso.entities.Matricula;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
	
	List<Matricula> findByCursoId(Long cursoId);
	
	@Query("SELECT AVG(m.nota) FROM Matricula m WHERE m.curso.id = :cursoId")
    Double findMediaNotaByCursoId(@Param("cursoId") Long cursoId);
	
	@Query("SELECT m FROM Matricula m JOIN FETCH m.curso WHERE m.aluno.id = :alunoId")
    List<Matricula> findByAlunoId(@Param("alunoId") Long alunoId);
	
	boolean existsByAlunoAndCurso(Aluno aluno, Curso curso);
	
}