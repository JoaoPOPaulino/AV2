package modulo2;

public class Quarto {
    private int numero;
    private TipoQuarto tipo;
    private double diaria;

    private boolean reservado;

    public Quarto(int numero, TipoQuarto tipo, double diaria) {
        this.numero = numero;
        this.tipo = tipo;
        this.diaria = diaria;
        this.reservado = false;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public TipoQuarto getTipo() {
        return tipo;
    }

    public void setTipo(TipoQuarto tipo) {
        this.tipo = tipo;
    }

    public double getDiaria() {
        return diaria;
    }

    public void setDiaria(double diaria) {
        this.diaria = diaria;
    }

    public boolean isReservado() {
        return reservado;
    }

    public void setReservado(boolean reservado) {
        this.reservado = reservado;
    }
}
