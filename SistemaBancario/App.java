package SistemaBancario;

import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SistemaConta sistema = new SistemaConta();

        int opcao = 0;

        do {
            System.out.println("\n Banco ");
            System.out.println("1 - Criar conta");
            System.out.println("2 - Listar contas");
            System.out.println("3 - Buscar conta");
            System.out.println("4 - Depósito");
            System.out.println("5 - Saque");
            System.out.println("6 - Transferência");
            System.out.println("7 - Buscar por CPF");
            System.out.println("8 - Mostrar histórico");
            System.out.println("9 - Mostrar histórico por tipo");
            System.out.println("10 - Buscar conta por nome");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma das opções acima: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    try {
                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();

                        System.out.print("CPF: ");
                        String cpf = scanner.nextLine();

                        System.out.print("Saldo inicial: ");
                        double saldo = scanner.nextDouble();
                        scanner.nextLine();

                        sistema.criarConta(nome, cpf, saldo);

                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case 2:
                    sistema.listarContas();
                    break;

                case 3:
                    System.out.print("Número da conta: ");
                    int numeroConta = scanner.nextInt();
                    scanner.nextLine();
                    try {
                        Conta conta = sistema.buscarConta(numeroConta);
                        System.out.println(conta);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 4:
                    System.out.println("Número da conta: ");
                    int numeroC = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Valor do depósito: ");
                    double valor = scanner.nextDouble();
                    scanner.nextLine();

                    try {
                        sistema.depositar(numeroC, valor);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case 5:
                    System.out.println("Número da conta: ");
                    int numeroCont = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Valor do saque: ");
                    double valor1 = scanner.nextDouble();
                    scanner.nextLine();

                    try {
                        sistema.sacar(numeroCont, valor1);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case 6:
                    System.out.println("Número conta de origem: ");
                    int numeroOrigem = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Número conta destino: ");
                    int numeroDestino = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Valor da transferência: ");
                    double valorTransfer = scanner.nextDouble();
                    scanner.nextLine();

                    try {
                        sistema.transferir(numeroOrigem, numeroDestino, valorTransfer);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case 7:
                    System.out.println("Número do CPF: ");
                    String cpf = scanner.nextLine();
                    try {
                        Conta conta = sistema.buscaCpf(cpf);
                        System.out.println(conta);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 8:
                    System.out.println("Insira o número da conta: ");
                    int numeroContaa = scanner.nextInt();
                    scanner.nextLine();

                    try {
                        Conta conta = sistema.buscarConta(numeroContaa);
                        conta.mostrarHistorico();

                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case 9:
                    System.out.println("Insira o número da conta: ");
                    int numConta = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Digite o tipo: ");
                    String tipo = scanner.nextLine();

                    try {
                        Conta conta = sistema.buscarConta(numConta);
                        conta.mostrarHistoricoPorTipo(tipo);

                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());

                    }
                    break;

                case 10:
                    System.out.println("Digite o nome da pessoa: ");
                    String nome = scanner.nextLine();

                    try {
                        List<Conta> contasEncontradas = sistema.buscarPorNome(nome);
                        for (Conta c : contasEncontradas) {
                            System.out.println(c);
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 0:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida!");
                    break;

            }

        } while (opcao != 0);
        scanner.close();
    }
}
