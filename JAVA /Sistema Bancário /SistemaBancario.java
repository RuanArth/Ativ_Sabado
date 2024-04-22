package sistemabancario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;




public class SistemaBancario {
    public static void main(String[] args) {
        Banco banco = Banco.getInstance();

        // Criando contas
        Conta contaLoja1 = new Conta(1, 0);
        Conta contaLoja2 = new Conta(2, 0);

        // Criando lojas
        Loja loja1 = new Loja("Loja 1", contaLoja1);
        Loja loja2 = new Loja("Loja 2", contaLoja2);

   // Adicionando funcionários às lojas
for (int i = 1; i <= 4; i++) {
    Funcionario funcionario = new Funcionario("Funcionário " + i, i <= 2 ? loja1 : loja2, banco);
    if (i <= 2) {
        loja1.adicionarFuncionario(funcionario);
    } else {
        loja2.adicionarFuncionario(funcionario);
    }
}


        // Adicionando contas ao banco
        banco.adicionarConta(contaLoja1);
        banco.adicionarConta(contaLoja2);

        // Iniciando threads dos funcionários
        for (Loja loja : List.of(loja1, loja2)) {
            for (Funcionario funcionario : loja.getFuncionarios()) {
                new Thread(funcionario).start();
            }
        }

        // Criando clientes
        for (int i = 1; i <= 5; i++) {
            Cliente cliente = new Cliente("Cliente " + i, new Conta(i + 2, 1000), i % 2 == 0 ? loja1 : loja2);
            new Thread(cliente).start();
        }

        // Espera todas as threads terminarem
        try {
            Thread.sleep(10000); // Tempo suficiente para as transações
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Pagar os funcionários
        loja1.pagarFuncionarios();
        loja2.pagarFuncionarios();

        // Exibir saldos e transferências
        banco.exibirSaldos();
    }
}
