package teste.cassol.curso.exception;

public class InvalidNotaException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public InvalidNotaException(String message) {
        super(message);
    }
}