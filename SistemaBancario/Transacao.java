package SistemaBancario;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transacao {

    private final String tipo;
    private final double valor;
    private final LocalDateTime data;

    public Transacao(String tipo, double valor) {
        this.tipo = tipo;
        this.valor = valor;
        this.data = LocalDateTime.now();
    }

    public String getTipo() {
        return tipo;
    }

    public double getValor() {
        return valor;
    }

    public LocalDateTime getData() {
        return data;
    }

    @Override
    public String toString() {

        DateTimeFormatter formatar = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"); // pra formatar nos padrões
        // brasileiros

        return "Tipo: " + tipo +
                "\nValor: " + valor +
                "\nData: " + data.format(formatar);
    }
}