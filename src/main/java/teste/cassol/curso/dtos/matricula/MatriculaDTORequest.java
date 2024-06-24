package teste.cassol.curso.dtos.matricula;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatriculaDTORequest {
	
	@Schema(description = "Id do Aluno", example = "3")
	private Long alunoId;
	
	@Schema(description = "Id do Curso", example = "1")
    private Long cursoId;
	
	@Schema(description = "Nota do aluno", example = "8.5")
    private Double nota;

}
