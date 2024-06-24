package teste.cassol.curso.dtos.curso;

import org.springframework.beans.BeanUtils;

import teste.cassol.curso.entities.Curso;

public class CursoMapper {
	
	public static Curso fromDTO (CursoDTORequest request) {
		Curso curso = new Curso();
		BeanUtils.copyProperties(request, curso);
		return curso;
	}
	
	public static CursoDTOResponse fromEntity (Curso curso) {
		CursoDTOResponse response = new CursoDTOResponse();
		BeanUtils.copyProperties(curso, response);
		return response;
	}

}
