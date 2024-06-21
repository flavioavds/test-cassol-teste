package teste.cassol.curso.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import teste.cassol.curso.dtos.matricula.MatriculaDTOResponse;
import teste.cassol.curso.entities.Aluno;
import teste.cassol.curso.entities.Curso;
import teste.cassol.curso.entities.Matricula;
import teste.cassol.curso.repository.CursoRepository;
import teste.cassol.curso.repository.MatriculaRepository;

@Service
public class MatriculaService {

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Transactional
    public MatriculaDTOResponse matricularAluno(Long alunoId, Long cursoId) {
        Aluno aluno = new Aluno();
        aluno.setId(alunoId);

        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new RuntimeException("Curso not found"));

        Matricula matricula = new Matricula();
        matricula.setAluno(aluno);
        matricula.setCurso(curso);

        Matricula savedMatricula = matriculaRepository.save(matricula);
        return entityToDto(savedMatricula);
    }

    public List<MatriculaDTOResponse> getAllMatriculas() {
        List<Matricula> matriculas = matriculaRepository.findAll();
        return matriculas.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public MatriculaDTOResponse getMatriculaById(Long id) {
        Matricula matricula = matriculaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matricula not found"));
        return entityToDto(matricula);
    }

    @Transactional
    public MatriculaDTOResponse updateNotaMatricula(Long id, Double nota) {
        Matricula matricula = matriculaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matricula not found"));
        matricula.setNota(nota);
        Matricula updatedMatricula = matriculaRepository.save(matricula);
        return entityToDto(updatedMatricula);
    }

    @Transactional
    public void deleteMatricula(Long id) {
        Matricula matricula = matriculaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matricula not found"));
        matriculaRepository.delete(matricula);
    }

    public Double calcularNotaMediaCurso(Long cursoId) {
        List<Matricula> matriculas = matriculaRepository.findByCursoId(cursoId);
        if (matriculas.isEmpty()) {
            return null;
        }

        double somaNotas = matriculas.stream()
                .filter(matricula -> matricula.getNota() != null)
                .mapToDouble(Matricula::getNota)
                .sum();

        return somaNotas / matriculas.size();
    }

    private MatriculaDTOResponse entityToDto(Matricula matricula) {
        return MatriculaDTOResponse.builder()
                .id(matricula.getId())
                .alunoId(matricula.getAluno().getId()) 
                .cursoId(matricula.getCurso().getId()) 
                .nota(matricula.getNota())
                .build();
    }

}