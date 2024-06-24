package teste.cassol.curso.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import teste.cassol.curso.entities.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
	
	Optional<Aluno> findByNome(String nome);
	
}