package teste.cassol.curso.dtos.matricula;

import org.springframework.beans.BeanUtils;

import teste.cassol.curso.entities.Matricula;

public class MatriculaMapper {

    public static Matricula fromDTO(MatriculaDTORequest request) {
        Matricula matricula = new Matricula();
        BeanUtils.copyProperties(request, matricula, "id", "alunoId", "cursoId");
        return matricula;
    }

    public static MatriculaDTOResponse fromEntity(Matricula matricula) {
        MatriculaDTOResponse response = MatriculaDTOResponse.builder()
            .id(matricula.getId())
            .alunoId(matricula.getAluno().getId())
            .alunoNome(matricula.getAluno().getNome())
            .cursoId(matricula.getCurso().getId())
            .cursoNome(matricula.getCurso().getNome())
            .nota(matricula.getNota())
            .build();
        return response;
    }
}
