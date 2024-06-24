package teste.cassol.curso.dtos.curso;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CursoDTORequest {
	
	@NotNull(message = "Nome não pode ser nulo")
    @NotEmpty(message = "Nome não pode estar vazio")
	@Schema(description = "Nome do curso", example = "Scrum Master")
	private String nome;
	
	@Schema(description = "Descrição do curso", example = "Professional Scrum Master™ (PSM I) é um curso que aborda os princípios e a teoria do processo empírico implementado pelo Framework Scrum, e esclarece qual o papel do Scrum Master nesse processo. Esse curso combina teoria, exercícios em grupo e ensina os princípios e ferramentas do Scrum e da Agilidade.")
    private String descricao;

}
