package teste.cassol.curso.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import teste.cassol.curso.dtos.aluno.AlunoComCursosDTO;
import teste.cassol.curso.dtos.aluno.AlunoDTORequest;
import teste.cassol.curso.dtos.aluno.AlunoDTOResponse;
import teste.cassol.curso.dtos.aluno.AlunoMapper;
import teste.cassol.curso.dtos.curso.CursoDTOResponse;
import teste.cassol.curso.dtos.curso.CursoMapper;
import teste.cassol.curso.entities.Aluno;
import teste.cassol.curso.entities.Matricula;
import teste.cassol.curso.exception.AlunoNotFoundException;
import teste.cassol.curso.repository.AlunoRepository;
import teste.cassol.curso.repository.MatriculaRepository;

@Service
public class AlunoServiceImpl implements AlunoService {

	private final AlunoRepository alunoRepository;
    private final MatriculaRepository matriculaRepository;

    public AlunoServiceImpl(AlunoRepository alunoRepository, MatriculaRepository matriculaRepository) {
        this.alunoRepository = alunoRepository;
        this.matriculaRepository = matriculaRepository;
    }
    
    @Override
    public AlunoDTOResponse createAluno(AlunoDTORequest alunoDTORequest) {
        if (alunoDTORequest.getNome() == null || alunoDTORequest.getNome().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode estar vazio");
        }

        Optional<Aluno> existingAluno = alunoRepository.findByNome(alunoDTORequest.getNome());
        if (existingAluno.isPresent()) {
            throw new DuplicateKeyException("Aluno com o nome " + alunoDTORequest.getNome() + " já existe");
        }

        Aluno aluno = AlunoMapper.fromDTO(alunoDTORequest);
        Aluno savedAluno = alunoRepository.save(aluno);

        return AlunoMapper.fromEntity(savedAluno);
    }

    @Override
    public AlunoDTOResponse getAlunoById(Long id) {
        Optional<Aluno> alunoOptional = alunoRepository.findById(id);
        if (alunoOptional.isPresent()) {
            return AlunoMapper.fromEntity(alunoOptional.get());
        }
        throw new AlunoNotFoundException("Aluno não encontrado com esse id: " + id);
    }

    @Override
    public List<AlunoDTOResponse> getAllAlunos() {
        List<Aluno> alunos = alunoRepository.findAll();
        return alunos.stream()
                .map(AlunoMapper::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public AlunoDTOResponse updateAluno(Long id, AlunoDTORequest alunoDTORequest) {
        Optional<Aluno> alunoOptional = alunoRepository.findById(id);
        if (!alunoOptional.isPresent()) {
            throw new AlunoNotFoundException("Aluno não encontrado com esse id: " + id);
        }

        Aluno aluno = alunoOptional.get();
        
        if (alunoDTORequest.getNome() == null || alunoDTORequest.getNome().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode estar vazio");
        }

        Optional<Aluno> existingAluno = alunoRepository.findByNome(alunoDTORequest.getNome());
        if (existingAluno.isPresent()) {
            throw new DuplicateKeyException("Aluno com o nome " + alunoDTORequest.getNome() + " já existe");
        }

        BeanUtils.copyProperties(alunoDTORequest, aluno, "id");
        Aluno updatedAluno = alunoRepository.save(aluno);
        return AlunoMapper.fromEntity(updatedAluno);
    }
    
    @Override
    public void deleteAluno(Long id) {
        if (alunoRepository.existsById(id)) {
            alunoRepository.deleteById(id);
            System.out.println("Aluno com id " + id + " deletado com sucesso");
        } else {
            throw new AlunoNotFoundException("Aluno não encontrado com o id: " + id);
        }
    }
    
    @Override
    public AlunoComCursosDTO getCursosByAlunoId(Long alunoId) {
        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new AlunoNotFoundException("Aluno não encontrado com esse id: " + alunoId));

        List<Matricula> matriculas = matriculaRepository.findByAlunoId(alunoId);

        List<CursoDTOResponse> cursos = matriculas.stream()
                .map(matricula -> CursoMapper.fromEntity(matricula.getCurso()))
                .collect(Collectors.toList());

        return new AlunoComCursosDTO(aluno.getId(), aluno.getNome(), cursos);
    }
    
}
