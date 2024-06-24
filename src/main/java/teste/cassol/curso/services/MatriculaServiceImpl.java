package teste.cassol.curso.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import teste.cassol.curso.dtos.matricula.MatriculaDTORequest;
import teste.cassol.curso.dtos.matricula.MatriculaDTOResponse;
import teste.cassol.curso.dtos.matricula.MatriculaMapper;
import teste.cassol.curso.entities.Aluno;
import teste.cassol.curso.entities.Curso;
import teste.cassol.curso.entities.Matricula;
import teste.cassol.curso.exception.InvalidNotaException;
import teste.cassol.curso.exception.MatriculaDuplicadaException;
import teste.cassol.curso.exception.MatriculaNotFoundException;
import teste.cassol.curso.repository.AlunoRepository;
import teste.cassol.curso.repository.CursoRepository;
import teste.cassol.curso.repository.MatriculaRepository;

@Service
public class MatriculaServiceImpl implements MatriculaService {

    private final MatriculaRepository matriculaRepository;
    private final AlunoRepository alunoRepository;
    private final CursoRepository cursoRepository;

    public MatriculaServiceImpl(MatriculaRepository matriculaRepository, AlunoRepository alunoRepository, CursoRepository cursoRepository) {
        this.matriculaRepository = matriculaRepository;
        this.alunoRepository = alunoRepository;
        this.cursoRepository = cursoRepository;
    }

    @Override
    public MatriculaDTOResponse createMatricula(MatriculaDTORequest matriculaDTORequest) {
        Aluno aluno = alunoRepository.findById(matriculaDTORequest.getAlunoId())
                .orElseThrow(() -> new MatriculaNotFoundException("Aluno não encontrado com id: " + matriculaDTORequest.getAlunoId()));
        Curso curso = cursoRepository.findById(matriculaDTORequest.getCursoId())
                .orElseThrow(() -> new MatriculaNotFoundException("Curso não encontrado com id: " + matriculaDTORequest.getCursoId()));

        boolean exists = matriculaRepository.existsByAlunoAndCurso(aluno, curso);
        if (exists) {
            throw new MatriculaDuplicadaException("Aluno já está matriculado neste curso");
        }

        Matricula matricula = Matricula.builder()
                .aluno(aluno)
                .curso(curso)
                .nota(matriculaDTORequest.getNota())
                .build();

        Matricula savedMatricula = matriculaRepository.save(matricula);
        return MatriculaMapper.fromEntity(savedMatricula);
    }

    @Override
    public List<MatriculaDTOResponse> getAllMatriculas() {
        return matriculaRepository.findAll().stream()
                .map(MatriculaMapper::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public MatriculaDTOResponse getMatriculaById(Long id) {
        Matricula matricula = matriculaRepository.findById(id)
                .orElseThrow(() -> new MatriculaNotFoundException("Matricula não encontrada com id: " + id));
        return MatriculaMapper.fromEntity(matricula);
    }

    @Override
    public MatriculaDTOResponse updateNota(Long id, Double nota) {
        if (nota == null || nota.isNaN()) {
            throw new InvalidNotaException("Nota não pode ser vazia ou inválida");
        }

        Matricula matricula = matriculaRepository.findById(id)
                .orElseThrow(() -> new MatriculaNotFoundException("Matricula não encontrada com id: " + id));
        
        matricula.setNota(nota);
        Matricula updatedMatricula = matriculaRepository.save(matricula);
        return MatriculaMapper.fromEntity(updatedMatricula);
    }


    @Override
    public void deleteMatricula(Long id) {
        if (matriculaRepository.existsById(id)) {
            matriculaRepository.deleteById(id);
        } else {
            throw new MatriculaNotFoundException("Matricula não encontrada com id: " + id);
        }
    }
    
}
