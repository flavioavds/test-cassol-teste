package teste.cassol.curso.dtos.aluno;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlunoDTORequest {
	
	@Schema(description = "Nome do aluno", example = "Ze Antonio")
	private String nome;
	
}
