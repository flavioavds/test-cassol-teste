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
import teste.cassol.curso.dtos.curso.CursoDTORequest;
import teste.cassol.curso.dtos.curso.CursoDTOResponse;
import teste.cassol.curso.exception.CursoNotFoundException;
import teste.cassol.curso.services.CursoServiceImpl;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    private final CursoServiceImpl cursoService;

    public CursoController(CursoServiceImpl cursoService) {
		this.cursoService = cursoService;
	}

    @Operation(
            summary = "POST - Salvando um Curso",
            description = "Click em Try it out, substitua o nome ou execute para salvar e irá gerar o http status 200, click novamente em executar e não irá salvar pois o nome já existe e gerará um erro.",
            tags = {"CURSOS"},
            responses = {
                @ApiResponse(
                    description = "Save",
                    responseCode = "200",
                    content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = CursoDTORequest.class),
                        examples = @ExampleObject(value = "{\"nome\": \"Scrum Master\", \"descricao\": \"Professional Scrum Master™ (PSM I) é um curso que aborda os princípios e a teoria do processo empírico implementado pelo Framework Scrum, e esclarece qual o papel do Scrum Master nesse processo. Esse curso combina teoria, exercícios em grupo e ensina os princípios e ferramentas do Scrum e da Agilidade.\"}")
                    )
                )
            }
        )
	@PostMapping
    public ResponseEntity<CursoDTOResponse> createCurso(@Valid @RequestBody CursoDTORequest cursoDTORequest) {
        CursoDTOResponse response = cursoService.createCurso(cursoDTORequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "GET - Buscando por todos os Cursos",
			description = "Clique em Try it out e execute para trazer todos os Cursos",
			tags = {"CURSOS"},
			responses = {
					@ApiResponse(description = "Get All", responseCode = "200", 
							content = @Content( 
								mediaType = "application/json",
								array = @ArraySchema(schema = @Schema(implementation = CursoDTORequest.class))))
					
			}
	)
    @GetMapping
    public ResponseEntity<List<CursoDTOResponse>> getAllCursos() {
        List<CursoDTOResponse> response = cursoService.getAllCursos();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "GET - Buscando curso por Id",
			description = "Clique em Try it out e digite um id de um curso e execute para trazer os dados do curso, caso você digite um id invalido gerará um http status 404 not found",
			tags = {"CURSOS"},
			responses = {
					@ApiResponse(description = "Get by Id", responseCode = "200", 
							content = @Content( 
								mediaType = "application/json",
								array = @ArraySchema(schema = @Schema(implementation = CursoDTORequest.class))))
					
			}
	)
    @GetMapping("/{id}")
    public ResponseEntity<CursoDTOResponse> getCursoById(@PathVariable Long id) {
        CursoDTOResponse response = cursoService.getCursoById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @Operation(
            summary = "PUT - Atualizando dados de um Curso",
            description = "Click em Try it out, inclua um id de um curso existente e substitua o nome ou execute para salvar e irá gera o http status 200, click novamente em executar e não irá salvar pois o nome já existe e gerará um erro status 409, ou gerara um erro caso o id seja invalido status 404, caso necessario utilize a sugestão abaixo",
            tags = {"CURSOS"},
            responses = {
                @ApiResponse(
                    description = "Atualizando dados",
                    responseCode = "200",
                    content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = CursoDTORequest.class),
                        examples = @ExampleObject(value = "{\"nome\": \"Scrum Master\", \"descricao\": \"Professional Scrum Master™ (PSM I) é um curso que aborda os princípios e a teoria do processo empírico implementado pelo Framework Scrum, e esclarece qual o papel do Scrum Master nesse processo. Esse curso combina teoria, exercícios em grupo e ensina os princípios e ferramentas do Scrum e da Agilidade.\"}")
                    )
                )
            }
        )
    @PutMapping("/{id}")
    public ResponseEntity<CursoDTOResponse> updateCurso(@Valid @PathVariable Long id, @RequestBody CursoDTORequest cursoDTORequest) {
        CursoDTOResponse response = cursoService.updateCurso(id, cursoDTORequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @Operation(summary = "DELETE - Deletando um curso por Id",
			description = "Clique em Try it out e digite um id de um curso e execute para deletar o dado de um curso, caso você digite um id invalido gerará um http status 404 de cliente não localizado",
			tags = {"CURSOS"},
			responses = {
					@ApiResponse(description = "Delete", responseCode = "200", 
							content = @Content( 
								mediaType = "application/json",
								array = @ArraySchema(schema = @Schema(implementation = CursoDTORequest.class))))
					
			}
	)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCurso(@PathVariable Long id) {
        try {
            cursoService.deleteCurso(id);
            return ResponseEntity.ok("Curso com id " + id + " deletado com sucesso");
        } catch (CursoNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    
    @Operation(summary = "GET - Buscando todas as notas de um determinado curso e calculando a media",
			description = "Clique em Try it out e digite um id de um curso e execute para calcular a media de todos alunos matriculado em um curso, caso você digite um id invalido gerará um http status 404 de cliente não localizado",
			tags = {"CURSOS"},
			responses = {
					@ApiResponse(description = "Get Media Nota Curso Id", responseCode = "200", 
							content = @Content( 
								mediaType = "application/json",
								array = @ArraySchema(schema = @Schema(implementation = CursoDTORequest.class))))
					
			}
	)
    @GetMapping("/{id}/media")
    public ResponseEntity<String> getFormattedMediaNotaByCursoId(@PathVariable Long id) {
        String message = cursoService.getFormattedMediaNotaByCursoId(id);
        return ResponseEntity.ok(message);
    }
}