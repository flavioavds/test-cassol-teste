package teste.cassol.curso.exception;

public class MatriculaNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

	public MatriculaNotFoundException(String message) {
        super(message);
    }
}