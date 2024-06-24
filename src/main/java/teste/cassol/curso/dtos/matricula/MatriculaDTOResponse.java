package teste.cassol.curso.dtos.matricula;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatriculaDTOResponse {
	
	private Long id;
    private Long alunoId;
    private String alunoNome;
    private Long cursoId;
    private String cursoNome;
    private Double nota;

}
