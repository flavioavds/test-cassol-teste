package teste.cassol.curso.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import teste.cassol.curso.dtos.aluno.AlunoDTORequest;
import teste.cassol.curso.dtos.aluno.AlunoDTOResponse;
import teste.cassol.curso.dtos.curso.CursoDTOResponse;
import teste.cassol.curso.entities.Aluno;
import teste.cassol.curso.repository.AlunoRepository;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public AlunoDTOResponse createAluno(AlunoDTORequest alunoDTORequest) {
        Aluno aluno = dtoToEntity(alunoDTORequest);
        Aluno savedAluno = alunoRepository.save(aluno);
        return entityToDto(savedAluno);
    }

    public AlunoDTOResponse getAlunoById(Long id) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno not found"));
        return entityToDto(aluno);
    }

    public List<AlunoDTOResponse> getAllAlunos() {
        List<Aluno> alunos = alunoRepository.findAll();
        return alunos.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public AlunoDTOResponse updateAluno(Long id, AlunoDTORequest alunoDTORequest) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno not found"));
        aluno.setNome(alunoDTORequest.getNome());
        Aluno updatedAluno = alunoRepository.save(aluno);
        return entityToDto(updatedAluno);
    }

    public void deleteAluno(Long id) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno not found"));
        alunoRepository.delete(aluno);
    }

    public List<CursoDTOResponse> getCursosByAlunoId(Long id) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno not found"));
        return aluno.getMatriculas().stream()
                .map(matricula -> CursoDTOResponse.builder()
                        .id(matricula.getCurso().getId())
                        .nome(matricula.getCurso().getNome())
                        .descricao(matricula.getCurso().getDescricao())
                        .build())
                .collect(Collectors.toList());
    }

    private Aluno dtoToEntity(AlunoDTORequest alunoDTORequest) {
        return Aluno.builder()
                .nome(alunoDTORequest.getNome())
                .build();
    }

    private AlunoDTOResponse entityToDto(Aluno aluno) {
        return AlunoDTOResponse.builder()
                .id(aluno.getId())
                .nome(aluno.getNome())
                .build();
    }
}