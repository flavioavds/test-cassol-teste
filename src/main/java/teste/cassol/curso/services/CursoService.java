package teste.cassol.curso.services;

import java.util.List;

import teste.cassol.curso.dtos.curso.CursoDTORequest;
import teste.cassol.curso.dtos.curso.CursoDTOResponse;

public interface CursoService {
	
	CursoDTOResponse createCurso(CursoDTORequest cursoDTORequest);
	CursoDTOResponse getCursoById(Long id);
	List<CursoDTOResponse> getAllCursos();
	CursoDTOResponse updateCurso(Long id, CursoDTORequest cursoDTORequest);
	void deleteCurso(Long id);
	Double getMediaNotaByCursoId(Long cursoId);
	String getFormattedMediaNotaByCursoId(Long cursoId);
	
}
