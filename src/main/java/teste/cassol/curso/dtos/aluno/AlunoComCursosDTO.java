package teste.cassol.curso.dtos.aluno;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import teste.cassol.curso.dtos.curso.CursoDTOResponse;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlunoComCursosDTO {
	
	private Long alunoId;
    private String alunoNome;
    private List<CursoDTOResponse> cursos;

}
