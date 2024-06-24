package teste.cassol.curso.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import teste.cassol.curso.dtos.curso.CursoDTORequest;
import teste.cassol.curso.dtos.curso.CursoDTOResponse;
import teste.cassol.curso.dtos.curso.CursoMapper;
import teste.cassol.curso.entities.Curso;
import teste.cassol.curso.exception.CursoNotFoundException;
import teste.cassol.curso.repository.CursoRepository;
import teste.cassol.curso.repository.MatriculaRepository;

@Service
public class CursoServiceImpl implements CursoService{
	
	private final CursoRepository cursoRepository;
    private final MatriculaRepository matriculaRepository;

    public CursoServiceImpl(CursoRepository cursoRepository, MatriculaRepository matriculaRepository) {
        this.cursoRepository = cursoRepository;
        this.matriculaRepository = matriculaRepository;
    }

	@Override
	public CursoDTOResponse createCurso(CursoDTORequest cursoDTORequest) {
		Optional<Curso> existingCurso = cursoRepository.findByNome(cursoDTORequest.getNome());
        if (existingCurso.isPresent()) {
            throw new DuplicateKeyException("Curso com o nome " + cursoDTORequest.getNome() + " já existe");
        }
        Curso curso = CursoMapper.fromDTO(cursoDTORequest);
        Curso savedCurso = cursoRepository.save(curso);
        return CursoMapper.fromEntity(savedCurso);
	}

	@Override
	public CursoDTOResponse getCursoById(Long id) {
		Optional<Curso> cursoOptional = cursoRepository.findById(id);
        if (cursoOptional.isPresent()) {
            return CursoMapper.fromEntity(cursoOptional.get());
        }
        throw new CursoNotFoundException("Curso não encontrado com esse id: " + id);
    }

	@Override
	public List<CursoDTOResponse> getAllCursos() {
		List<Curso> cursos = cursoRepository.findAll();
        return cursos.stream()
                .map(CursoMapper::fromEntity)
                .collect(Collectors.toList());
	}

	@Override
	public CursoDTOResponse updateCurso(Long id, CursoDTORequest cursoDTORequest) {
		
		if (cursoDTORequest.getNome() == null || cursoDTORequest.getNome().isEmpty()) {
            throw new IllegalArgumentException("Nome do curso não pode estar vazio");
        }
		
		Optional<Curso> cursoOptional = cursoRepository.findById(id);
        if (!cursoOptional.isPresent()) {
            throw new CursoNotFoundException("Curso não encontrado com esse id: " + id);
        }

        Curso curso = cursoOptional.get();

        Optional<Curso> existingCurso = cursoRepository.findByNome(cursoDTORequest.getNome());
        if (existingCurso.isPresent()) {
            throw new DuplicateKeyException("Curso com o nome " + cursoDTORequest.getNome() + " já existe");
        }

        BeanUtils.copyProperties(cursoDTORequest, curso, "id");
        Curso updatedCurso = cursoRepository.save(curso);
        return CursoMapper.fromEntity(updatedCurso);
    }

	@Override
	public void deleteCurso(Long id) {
		if (cursoRepository.existsById(id)) {
            cursoRepository.deleteById(id);
            System.out.println("Curso com id " + id + " deletado com sucesso");
        } else {
            throw new CursoNotFoundException("Curso não encontrado com o id: " + id);
        }		
	}
	
	@Override
    public Double getMediaNotaByCursoId(Long cursoId) {
        cursoRepository.findById(cursoId)
                .orElseThrow(() -> new CursoNotFoundException("Curso não encontrado com id: " + cursoId));
        
        Double mediaNota = matriculaRepository.findMediaNotaByCursoId(cursoId);

        if (mediaNota == null) {
            return 0.0;
        }

        return mediaNota;
    }

    @Override
    public String getFormattedMediaNotaByCursoId(Long cursoId) {
        Double mediaNota = getMediaNotaByCursoId(cursoId);
        return String.format("A média de nota neste curso é de %.2f", mediaNota);
    }
}