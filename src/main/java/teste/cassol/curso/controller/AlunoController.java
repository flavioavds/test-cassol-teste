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
import org.springframework.web.bind.annotation.RestController;

import teste.cassol.curso.dtos.aluno.AlunoDTORequest;
import teste.cassol.curso.dtos.aluno.AlunoDTOResponse;
import teste.cassol.curso.dtos.curso.CursoDTOResponse;
import teste.cassol.curso.services.AlunoService;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @PostMapping
    public ResponseEntity<AlunoDTOResponse> createAluno(@RequestBody AlunoDTORequest alunoDTORequest) {
        AlunoDTOResponse response = alunoService.createAluno(alunoDTORequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AlunoDTOResponse>> getAllAlunos() {
        List<AlunoDTOResponse> response = alunoService.getAllAlunos();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoDTOResponse> getAlunoById(@PathVariable Long id) {
        AlunoDTOResponse response = alunoService.getAlunoById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoDTOResponse> updateAluno(@PathVariable Long id, @RequestBody AlunoDTORequest alunoDTORequest) {
        AlunoDTOResponse response = alunoService.updateAluno(id, alunoDTORequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAluno(@PathVariable Long id) {
        alunoService.deleteAluno(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}/cursos")
    public ResponseEntity<List<CursoDTOResponse>> getCursosByAlunoId(@PathVariable Long id) {
        List<CursoDTOResponse> response = alunoService.getCursosByAlunoId(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}