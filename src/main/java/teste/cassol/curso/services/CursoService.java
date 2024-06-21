package teste.cassol.curso.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import teste.cassol.curso.dtos.curso.CursoDTORequest;
import teste.cassol.curso.dtos.curso.CursoDTOResponse;
import teste.cassol.curso.entities.Curso;
import teste.cassol.curso.exception.CursoNotFoundException;
import teste.cassol.curso.repository.CursoRepository;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public CursoDTOResponse createCurso(CursoDTORequest cursoDTORequest) {
        Curso curso = dtoToEntity(cursoDTORequest);
        Curso savedCurso = cursoRepository.save(curso);
        return entityToDto(savedCurso);
    }

    public CursoDTOResponse getCursoById(Long id) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new CursoNotFoundException("Curso not found"));
        return entityToDto(curso);
    }

    public List<CursoDTOResponse> getAllCursos() {
        List<Curso> cursos = cursoRepository.findAll();
        return cursos.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public CursoDTOResponse updateCurso(Long id, CursoDTORequest cursoDTORequest) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso not found"));
        curso.setNome(cursoDTORequest.getNome());
        curso.setDescricao(cursoDTORequest.getDescricao());
        Curso updatedCurso = cursoRepository.save(curso);
        return entityToDto(updatedCurso);
    }

    public void deleteCurso(Long id) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso not found"));
        cursoRepository.delete(curso);
    }

    private Curso dtoToEntity(CursoDTORequest cursoDTORequest) {
        return Curso.builder()
                .nome(cursoDTORequest.getNome())
                .descricao(cursoDTORequest.getDescricao())
                .build();
    }

    private CursoDTOResponse entityToDto(Curso curso) {
        return CursoDTOResponse.builder()
                .id(curso.getId())
                .nome(curso.getNome())
                .descricao(curso.getDescricao())
                .build();
    }
}