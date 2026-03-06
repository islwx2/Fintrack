package app;

import model.*;
import exceptions.FinTrackException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FinTrackApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Transacao> extrato = new ArrayList<>();
        int opcao = 0;

        System.out.println("Boas Vindas ao FinTrack");

        do {
            try {
                System.out.println("\n--- MENU PRINCIPAL ---");
                System.out.println("1. Adicionar Receita");
                System.out.println("2. Adicionar Despesa");
                System.out.println("3. Ver Extrato e Saldo");
                System.out.println("4. Remover Transação");
                System.out.println("5. Sair");
                System.out.print("Escolha uma opção: ");

                opcao = Integer.parseInt(scanner.nextLine());

                if (opcao == 1 || opcao == 2) {
                    System.out.print("Digite a descrição: ");
                    String desc = scanner.nextLine();
                    System.out.print("Digite o valor: ");
                    double val = Double.parseDouble(scanner.nextLine());

                    if (val <= 0) {
                        throw new FinTrackException("Atenção, o valor deve ser maior que zero!");
                    }

                    if (opcao == 1) {
                        extrato.add(new model.Receita(desc, val));
                        System.out.println("Receita adicionada!");
                    } else {
                        extrato.add(new model.Despesa(desc, val));
                        System.out.println("Despesa registrada!");
                    }

                } else if (opcao == 3) {
                    double saldoTotal = 0;
                    System.out.println("\n--- SEU EXTRATO ---");
                    if (extrato.isEmpty()) {
                        System.out.println("Nenhuma transação registrada.");
                    } else {
                        for (Transacao t : extrato) {
                            System.out.println(t);
                            saldoTotal += t.calcularSaldo(); // Polimorfismo aqui!
                        }
                        System.out.printf("\nSALDO ATUAL: R$ %.2f\n", saldoTotal);
                    }
                } else if (opcao == 4) {
                    if (extrato.isEmpty()) {
                        System.out.println("O extrato está vazio. Não há nenhuma transação para remover.");
                    } else {
                        System.out.println("\n--- Selecione o número do item para remover ---");

                        for (int i = 0; i < extrato.size(); i++) {
                            System.out.println("[" + i + "] " + extrato.get(i));
                        }

                        System.out.print("\nDigite o número do item: ");
                        int indiceParaRemover = Integer.parseInt(scanner.nextLine());

                        if (indiceParaRemover >= 0 && indiceParaRemover < extrato.size()) {
                            Transacao removida = extrato.remove(indiceParaRemover);
                            System.out.println("Sucesso: A transação'" + removida.getDescricao() + "' foi removida!");
                        } else {
                            System.out.println("Erro: Número inválido! Item não encontrado.");
                        }
                    }
                }

            } catch (NumberFormatException e) {
                System.out.println("Erro: Por favor, digite um número válido para opção e valor.");
            } catch (FinTrackException e) {
                System.out.println("Erro de Negócio: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
            }

        } while (opcao != 5);

        System.out.println("Sistema encerrado. Boas financas!");
        scanner.close();
    }
}