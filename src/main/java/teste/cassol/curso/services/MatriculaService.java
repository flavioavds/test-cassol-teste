package teste.cassol.curso.services;

import java.util.List;

import teste.cassol.curso.dtos.matricula.MatriculaDTORequest;
import teste.cassol.curso.dtos.matricula.MatriculaDTOResponse;

public interface MatriculaService {
    MatriculaDTOResponse createMatricula(MatriculaDTORequest matriculaDTORequest);
    List<MatriculaDTOResponse> getAllMatriculas();
    MatriculaDTOResponse getMatriculaById(Long id);
    MatriculaDTOResponse updateNota(Long id, Double nota);
    void deleteMatricula(Long id);
}
