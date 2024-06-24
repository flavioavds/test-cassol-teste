CREATE TABLE IF NOT EXISTS public.tb_matricula (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  aluno_id BIGINT NOT NULL,
  curso_id BIGINT NOT NULL,
  nota DOUBLE PRECISION,
  CONSTRAINT fk_matricula_aluno FOREIGN KEY (aluno_id) REFERENCES tb_aluno (id),
  CONSTRAINT fk_matricula_curso FOREIGN KEY (curso_id) REFERENCES tb_curso (id)
);