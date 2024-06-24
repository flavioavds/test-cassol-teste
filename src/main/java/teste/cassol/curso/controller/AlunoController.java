package teste.cassol.curso.controller;

import java.util.List;

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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import teste.cassol.curso.dtos.aluno.AlunoComCursosDTO;
import teste.cassol.curso.dtos.aluno.AlunoDTORequest;
import teste.cassol.curso.dtos.aluno.AlunoDTOResponse;
import teste.cassol.curso.exception.AlunoNotFoundException;
import teste.cassol.curso.services.AlunoService;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }
    
    @Operation(
            summary = "POST - Salvando um Aluno",
            description = "Click em Try it out, substitua o nome ou execute para salvar e irá gera o http status 200, click novamente em executar e não irá salvar pois o nome já existe e gerará um erro.",
            tags = {"ALUNOS"},
            responses = {
                @ApiResponse(
                    description = "Save",
                    responseCode = "200",
                    content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = AlunoDTORequest.class),
                        examples = @ExampleObject(value = "{\"nome\": \"Ze Antonio\"}")
                    )
                )
            }
        )
    @PostMapping
    public ResponseEntity<AlunoDTOResponse> createAluno(@Valid @RequestBody AlunoDTORequest alunoDTORequest) {
        AlunoDTOResponse alunoDTOResponse = alunoService.createAluno(alunoDTORequest);
        return ResponseEntity.ok(alunoDTOResponse);
    }

    @Operation(summary = "GET - Buscando por todos os Alunos",
			description = "Clique em Try it out e execute para trazer todos os Alunos",
			tags = {"ALUNOS"},
			responses = {
					@ApiResponse(description = "Get All", responseCode = "200", 
							content = @Content( 
								mediaType = "application/json",
								array = @ArraySchema(schema = @Schema(implementation = AlunoDTORequest.class))))
					
			}
	)
    @GetMapping
    public ResponseEntity<List<AlunoDTOResponse>> getAllAlunos() {
        List<AlunoDTOResponse> alunos = alunoService.getAllAlunos();
        return ResponseEntity.ok(alunos);
    }

    @Operation(summary = "GET - Buscando aluno por Id",
			description = "Clique em Try it out e digite um id de um aluno e execute para trazer os dados do aluno, caso você digite um id invalido gerará um http status 404",
			tags = {"ALUNOS"},
			responses = {
					@ApiResponse(description = "Get by Id", responseCode = "200", 
							content = @Content( 
								mediaType = "application/json",
								array = @ArraySchema(schema = @Schema(implementation = AlunoDTORequest.class))))
					
			}
	)
    @GetMapping("/{id}")
    public ResponseEntity<AlunoDTOResponse> getAlunoById(@PathVariable Long id) {
        AlunoDTOResponse alunoDTOResponse = alunoService.getAlunoById(id);
        return ResponseEntity.ok(alunoDTOResponse);
    }

    @Operation(
            summary = "PUT - Atualizando dados de um Aluno",
            description = "Click em Try it out, inclua um id de um aluno existente e substitua o nome ou execute para salvar e irá gera o http status 200, click novamente em executar e não irá salvar pois o nome já existe e gerará um erro, ou gerara um erro caso o id seja invalido, caso necessario utilize a sugestão abaixo",
            tags = {"ALUNOS"},
            responses = {
                @ApiResponse(
                    description = "Atualizando dados",
                    responseCode = "200",
                    content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = AlunoDTORequest.class),
                        examples = @ExampleObject(value = "{\"nome\": \"Maria Aparecida\"}")
                    )
                )
            }
        )
    @PutMapping("/{id}")
    public ResponseEntity<AlunoDTOResponse> updateAluno(@Valid @PathVariable Long id, @RequestBody AlunoDTORequest alunoDTORequest) {
        AlunoDTOResponse alunoDTOResponse = alunoService.updateAluno(id, alunoDTORequest);
        return ResponseEntity.ok(alunoDTOResponse);
    }

    @Operation(summary = "DELETE - Deletando um aluno por Id",
			description = "Clique em Try it out e digite um id de um aluno e execute para deletar o dado de um aluno, caso você digite um id invalido gerará um http status 404 de cliente não localizado",
			tags = {"ALUNOS"},
			responses = {
					@ApiResponse(description = "Delete", responseCode = "200", 
							content = @Content( 
								mediaType = "application/json",
								array = @ArraySchema(schema = @Schema(implementation = AlunoDTORequest.class))))
					
			}
	)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAluno(@PathVariable Long id) {
        try {
            alunoService.deleteAluno(id);
            return ResponseEntity.ok("Aluno com id " + id + " deletado com sucesso");
        } catch (AlunoNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    
    @Operation(summary = "GET - Buscando todos os cursos matriculados pelo aluno",
			description = "Clique em Try it out e digite um id de um aluno e execute para encontrar o dado de um aluno matriculado em um curso, caso você digite um id invalido gerará um http status 404 de cliente não localizado",
			tags = {"ALUNOS"},
			responses = {
					@ApiResponse(description = "Get Cursos By Aluno Id", responseCode = "200", 
							content = @Content( 
								mediaType = "application/json",
								array = @ArraySchema(schema = @Schema(implementation = AlunoDTORequest.class))))
					
			}
	)
    @GetMapping("/{id}/cursos")
    public ResponseEntity<AlunoComCursosDTO> getCursosByAlunoId(@PathVariable Long id) {
        AlunoComCursosDTO alunoComCursos = alunoService.getCursosByAlunoId(id);
        return ResponseEntity.ok(alunoComCursos);
    }
    
}
