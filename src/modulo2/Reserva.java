package modulo2;

import modulo.Usuario;

import java.time.LocalDate;

public class Reserva {
    private LocalDate dataInicio;
    private LocalDate dataFinal;
    private Quarto quarto;
    private Usuario usuario;
    private double valorTotal;

    public Reserva(LocalDate dataInicio, LocalDate dataFinal, Quarto quarto, Usuario usuario) {
        this.dataInicio = dataInicio;
        this.dataFinal = dataFinal;
        this.quarto = quarto;
        this.usuario = usuario;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void calcularValorTotal() {
        long dias = dataInicio.until(dataFinal).getDays();
        valorTotal = dias * quarto.getDiaria();
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "dataInicio=" + dataInicio +
                ", dataFinal=" + dataFinal +
                ", quarto=" + quarto +
                ", usuario=" + usuario +
                ", valorTotal=" + valorTotal +
                '}';
    }
}
