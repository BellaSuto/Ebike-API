package api.ebike.exceptions;

public class BicicletaNaoEncontradaException extends RuntimeException {
    public BicicletaNaoEncontradaException() {
        super("Bicicleta não encontrada.");
    }
}

