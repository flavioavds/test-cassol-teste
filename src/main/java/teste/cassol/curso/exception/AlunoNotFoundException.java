package teste.cassol.curso.exception;

public class AlunoNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

	public AlunoNotFoundException(String message) {
        super(message);
    }
}