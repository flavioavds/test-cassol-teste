package teste.cassol.curso.entities;

import java.util.Random;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_matricula")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Matricula {
	
	@Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "aluno_id", nullable = false)
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    private Double nota;
    
    public Matricula(Aluno aluno, Curso curso) {
        this.aluno = aluno;
        this.curso = curso;
    }
    
    @PrePersist
    public void generateId() {
        this.id = generateRandomId();
    }

    private Long generateRandomId() {
        Random random = new Random();
        long randomId = 100000 + random.nextInt(900000);
        return randomId;
    }

}
