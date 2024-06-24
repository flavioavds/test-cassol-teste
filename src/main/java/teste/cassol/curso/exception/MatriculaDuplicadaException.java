package teste.cassol.curso.exception;

public class MatriculaDuplicadaException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public MatriculaDuplicadaException(String message) {
        super(message);
    }
}