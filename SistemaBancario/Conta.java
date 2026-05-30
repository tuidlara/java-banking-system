package SistemaBancario;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class Conta {

    private String nome;
    private String cpf;
    private double saldo;
    private final int numeroConta;

    private List<Transacao> historico;

    public Conta(String nome, String cpf, double saldo, int numeroConta) {
        setNome(nome);
        setCpf(cpf);
        setSaldo(saldo);
        this.numeroConta = numeroConta;
        historico = new ArrayList<>();

    }

    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Você não pode deixar o nome vazio.");
        }
        this.nome = nome;

    }

    public String getNome() {
        return nome;
    }

    public void setCpf(String cpf) {
        if (!cpf.matches("\\d{11}")) {
            throw new IllegalArgumentException("Insira um CPF VALIDO!");
        }
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setSaldo(double saldo) {
        if (saldo < 0) {
            throw new IllegalArgumentException("Saldo não pode ser negativo.");
        }

        this.saldo = saldo;
    }

    public double getSaldo() {
        return saldo;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public void depositar(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor deve ser maior que zero");
        }
        saldo += valor;
        Transacao transacao = new Transacao("DEPOSITO", valor);
        historico.add(transacao);
    }

    public void sacar(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Insira um valor válido");
        }
        if (saldo < valor) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }

        double totalSacadoHoje = 0;
        double limiteDiario = 5000;

        for (Transacao t : historico) {
            if (t.getTipo().equals("SAQUE") && t.getData().toLocalDate().equals(LocalDate.now())) {
                totalSacadoHoje += t.getValor();
            }

        }
        if (totalSacadoHoje + valor > limiteDiario) {
            throw new IllegalArgumentException("Limite excedido");

        }
        saldo -= valor;
        Transacao transacao = new Transacao("SAQUE", valor);
        historico.add(transacao);
    }

    public List<Transacao> getHistorico() {
        return new ArrayList<>(historico); // vai devolver uma cópia da lista, por segurança, caso alguem use o
                                           // .clear(), apagará só a cópia

    }

    public void mostrarHistorico() {
        if (historico.isEmpty()) {
            throw new IllegalArgumentException("Nenhuma transação encontrada");
        }
        for (Transacao transacao : historico) {
            System.out.println(transacao);
        }
    }

    public void mostrarHistoricoPorTipo(String tipo) {
        boolean encontrou = false;
        for (Transacao t : historico) {
            if (t.getTipo().equalsIgnoreCase(tipo)) {
                System.out.println(t);
                encontrou = true;
            }
        }
        if (!encontrou) {
            throw new IllegalArgumentException("Nenhuma transação do tipo encontrada");
        }
    }

    @Override
    public String toString() {
        return "Conta: " + numeroConta +
                "\nNome: " + nome +
                "\nCPF: " + cpf +
                "\nSaldo: " + saldo;
    }
}
