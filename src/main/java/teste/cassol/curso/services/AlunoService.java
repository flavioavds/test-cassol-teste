package teste.cassol.curso.services;

import java.util.List;

import teste.cassol.curso.dtos.aluno.AlunoComCursosDTO;
import teste.cassol.curso.dtos.aluno.AlunoDTORequest;
import teste.cassol.curso.dtos.aluno.AlunoDTOResponse;

public interface AlunoService {
	
	AlunoDTOResponse createAluno(AlunoDTORequest alunoDTORequest);
	AlunoDTOResponse getAlunoById(Long id);
	List<AlunoDTOResponse> getAllAlunos();
	AlunoDTOResponse updateAluno(Long id, AlunoDTORequest alunoDTORequest);
	void deleteAluno(Long id);
	AlunoComCursosDTO getCursosByAlunoId(Long alunoId);
}
