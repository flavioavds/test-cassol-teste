package teste.cassol.curso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import teste.cassol.curso.dtos.matricula.MatriculaDTORequest;
import teste.cassol.curso.dtos.matricula.MatriculaDTOResponse;
import teste.cassol.curso.services.MatriculaService;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {

    @Autowired
    private MatriculaService matriculaService;

    @PostMapping
    public ResponseEntity<MatriculaDTOResponse> matricularAluno(@RequestBody MatriculaDTORequest matriculaDTORequest) {
        MatriculaDTOResponse response = matriculaService.matricularAluno(matriculaDTORequest.getAlunoId(), matriculaDTORequest.getCursoId());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MatriculaDTOResponse>> getAllMatriculas() {
        List<MatriculaDTOResponse> response = matriculaService.getAllMatriculas();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatriculaDTOResponse> getMatriculaById(@PathVariable Long id) {
        MatriculaDTOResponse response = matriculaService.getMatriculaById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MatriculaDTOResponse> updateNotaMatricula(@PathVariable Long id, @RequestParam Double nota) {
        MatriculaDTOResponse response = matriculaService.updateNotaMatricula(id, nota);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatricula(@PathVariable Long id) {
        matriculaService.deleteMatricula(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}