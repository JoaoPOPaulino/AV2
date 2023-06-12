package modulo;

public class AutenticacaoException extends Exception {
    private String messagem;

    public AutenticacaoException(String mensagem) {
        super(mensagem);
    }

    public String getMessagem() {
        return messagem;
    }

    public void setMessagem(String messagem) {
        this.messagem = messagem;
    }
}





