package SistemaBancario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SistemaConta {

    private Map<Integer, Conta> contas;
    private int proximoNumeroConta;

    public SistemaConta() {
        contas = new HashMap<>();

        proximoNumeroConta = 1;
    }

    public void criarConta(String nome, String cpf, double saldo) {
        for (Conta c : contas.values()) {
            if (c.getCpf().equals(cpf)) {
                throw new IllegalArgumentException("Conta já cadastrada");

            }
        }
        Conta novaConta = new Conta(nome, cpf, saldo, proximoNumeroConta);
        contas.put(proximoNumeroConta, novaConta);
        proximoNumeroConta++;

        System.out.println("Conta cadastrada com sucesso!");
    }

    public void listarContas() {
        if (contas.isEmpty()) {
            System.out.println("Nenhuma conta encontrada");
            return;
        }

        for (Conta c : contas.values()) {
            System.out.println(c);
        }
    }

    public Conta buscarConta(int numeroConta) {
        Conta conta = contas.get(numeroConta);
        if (conta == null) {
            throw new IllegalArgumentException("Conta não encontrada.");
        }
        return conta;

    }

    public List<Conta> buscarPorNome(String nome) {
        List<Conta> encontradas = new ArrayList<>();

        for (Conta c : contas.values()) {
            if (c.getNome().toLowerCase().contains(nome.toLowerCase())) {
                encontradas.add(c);
            }
        }
        if (encontradas.isEmpty()) {
            throw new IllegalArgumentException("Nome não encontrado");

        }
        return encontradas;
    }

    public Conta buscaCpf(String cpf) {
        for (Conta c : contas.values()) {
            if (c.getCpf().equals(cpf)) {
                return c;
            }
        }
        throw new IllegalArgumentException("Cpf não encontrado.");
    }

    public void depositar(int numeroConta, double valor) {
        Conta conta = buscarConta(numeroConta);
        conta.depositar(valor);
        System.out.println("Depósito realizado com sucesso!");
    }

    public void sacar(int numeroConta, double valor) {
        Conta conta = buscarConta(numeroConta);
        conta.sacar(valor);
        System.out.println("Saque realizado com sucesso!");
    }

    public void transferir(int numeroOrigem, int numeroDestino, double valor) {
        Conta origem = buscarConta(numeroOrigem);
        Conta destino = buscarConta(numeroDestino);
        origem.sacar(valor);
        destino.depositar(valor);
        System.out.println("Transferência realizada com sucesso!");

    }

}
