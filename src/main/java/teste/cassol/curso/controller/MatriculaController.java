package teste.cassol.curso.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import teste.cassol.curso.dtos.matricula.MatriculaDTORequest;
import teste.cassol.curso.dtos.matricula.MatriculaDTOResponse;
import teste.cassol.curso.services.MatriculaService;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {

    private final MatriculaService matriculaService;

    public MatriculaController(MatriculaService matriculaService) {
        this.matriculaService = matriculaService;
    }

    @Operation(
            summary = "POST - Salvando uma matricula em um Curso e um Aluno",
            description = "Click em Try it out, adicione o idAluno e idCurso ou execute para salvar e irá gerar o http status 200, click novamente em executar e não irá salvar pois o curso do mesmo aluno já está matriculado e caso não exista o aluno ou curso gerará um erro.",
            tags = {"MATRICULAS"},
            responses = {
                @ApiResponse(
                    description = "Save",
                    responseCode = "200",
                    content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = MatriculaDTORequest.class),
                        examples = @ExampleObject(value = "{\"alunoId\": \"3\", \"cursoId\": \"1\", \"nota\": \"7\"}")
                    )
                )
            }
        )
    @PostMapping
    public ResponseEntity<MatriculaDTOResponse> createMatricula(@RequestBody MatriculaDTORequest matriculaDTORequest) {
        MatriculaDTOResponse matriculaDTOResponse = matriculaService.createMatricula(matriculaDTORequest);
        return ResponseEntity.ok(matriculaDTOResponse);
    }

    @Operation(summary = "GET - Buscando por todas as matriculas dos Cursos e Alunos",
			description = "Clique em Try it out e execute para trazer todos os Cursos",
			tags = {"MATRICULAS"},
			responses = {
					@ApiResponse(description = "Get All", responseCode = "200", 
							content = @Content( 
								mediaType = "application/json",
								array = @ArraySchema(schema = @Schema(implementation = MatriculaDTORequest.class))))
					
			}
	)
    @GetMapping
    public ResponseEntity<List<MatriculaDTOResponse>> getAllMatriculas() {
        List<MatriculaDTOResponse> matriculas = matriculaService.getAllMatriculas();
        return ResponseEntity.ok(matriculas);
    }

    @Operation(summary = "GET - Buscando as matriculas por Id",
			description = "Clique em Try it out e digite um id de uma matricula e execute para trazer os dados do curso e alunos matriculados, caso você digite um id invalido gerará um http status 404 not found",
			tags = {"MATRICULAS"},
			responses = {
					@ApiResponse(description = "Get by Id", responseCode = "200", 
							content = @Content( 
								mediaType = "application/json",
								array = @ArraySchema(schema = @Schema(implementation = MatriculaDTORequest.class))))
					
			}
	)
    @GetMapping("/{id}")
    public ResponseEntity<MatriculaDTOResponse> getMatriculaById(@PathVariable Long id) {
        MatriculaDTOResponse matriculaDTOResponse = matriculaService.getMatriculaById(id);
        return ResponseEntity.ok(matriculaDTOResponse);
    }

    @Operation(
            summary = "PUT - Atualizando uma matricula em um Curso",
            description = "Click em Try it out, informe o id de matricula e adicione o idAluno e idCurso ou execute para salvar e irá gerar o http status 200, click novamente em executar e não irá salvar pois o curso do mesmo aluno já está matriculado neste curso e caso não exista o aluno ou curso gerará um erro.",
            tags = {"MATRICULAS"},
            responses = {
                @ApiResponse(
                    description = "Save",
                    responseCode = "200",
                    content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = MatriculaDTORequest.class),
                        examples = @ExampleObject(value = "{\"nota\": 7}")
                    )
                )
            }
        )
    @PutMapping("/{id}")
    public ResponseEntity<MatriculaDTOResponse> updateNota(@PathVariable Long id, @RequestBody Map<String, Double> notaRequest) {
        if (!notaRequest.containsKey("nota")) {
            return ResponseEntity.badRequest().body(null);
        }
        Double nota = notaRequest.get("nota");
        MatriculaDTOResponse response = matriculaService.updateNota(id, nota);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "DELETE - Deletando uma matricula por Id",
			description = "Clique em Try it out e digite um id de uma matricula e execute para deletar o dado de uma matricula, caso você digite um id invalido gerará um http status 404 de matricula não localizado",
			tags = {"MATRICULAS"},
			responses = {
					@ApiResponse(description = "Delete", responseCode = "200", 
							content = @Content( 
								mediaType = "application/json",
								array = @ArraySchema(schema = @Schema(implementation = MatriculaDTORequest.class))))
					
			}
	)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMatricula(@PathVariable Long id) {
        try {
            matriculaService.deleteMatricula(id);
            return ResponseEntity.ok("Matricula com id " + id + " deletado sucesso");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
    
}
