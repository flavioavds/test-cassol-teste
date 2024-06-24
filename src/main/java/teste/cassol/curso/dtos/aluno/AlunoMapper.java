package teste.cassol.curso.dtos.aluno;

import org.springframework.beans.BeanUtils;

import teste.cassol.curso.entities.Aluno;

public class AlunoMapper {
	
	public static Aluno fromDTO(AlunoDTORequest request) {
		Aluno aluno = new Aluno();
		BeanUtils.copyProperties(request, aluno);
		return aluno;
	}
	
	public static AlunoDTOResponse fromEntity (Aluno aluno) {
		AlunoDTOResponse response = new AlunoDTOResponse();
		BeanUtils.copyProperties(aluno, response);
		return response;
	}

}
