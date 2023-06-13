package modulo;

public enum TipoUsuario {
    ADMINISTRADOR(1),
    RECEPCIONISTA(2),
    HOSPEDE(3);

    private int valor;

    TipoUsuario(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
