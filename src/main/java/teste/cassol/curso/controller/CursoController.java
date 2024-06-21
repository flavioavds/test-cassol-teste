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

import teste.cassol.curso.dtos.curso.CursoDTORequest;
import teste.cassol.curso.dtos.curso.CursoDTOResponse;
import teste.cassol.curso.services.CursoService;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @PostMapping
    public ResponseEntity<CursoDTOResponse> createCurso(@RequestBody CursoDTORequest cursoDTORequest) {
        CursoDTOResponse response = cursoService.createCurso(cursoDTORequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CursoDTOResponse>> getAllCursos() {
        List<CursoDTOResponse> response = cursoService.getAllCursos();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoDTOResponse> getCursoById(@PathVariable Long id) {
        CursoDTOResponse response = cursoService.getCursoById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CursoDTOResponse> updateCurso(@PathVariable Long id, @RequestBody CursoDTORequest cursoDTORequest) {
        CursoDTOResponse response = cursoService.updateCurso(id, cursoDTORequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCurso(@PathVariable Long id) {
        cursoService.deleteCurso(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}