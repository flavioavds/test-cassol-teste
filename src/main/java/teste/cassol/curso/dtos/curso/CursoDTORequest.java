package teste.cassol.curso.dtos.curso;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CursoDTORequest {
	
	private String nome;
    private String descricao;

}
