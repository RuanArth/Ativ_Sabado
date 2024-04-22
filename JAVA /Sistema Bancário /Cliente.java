package sistemabancario;

import java.util.Random;

class Cliente implements Runnable {
    private String nome;
    private Conta conta;
    private Loja loja;

    public Cliente(String nome, Conta conta, Loja loja) {
        this.nome = nome;
        this.conta = conta;
        this.loja = loja;
    }

    @Override
    public void run() {
        Random random = new Random();
        while (true) {
            synchronized (conta) {
                if (conta.getSaldo() <= 0) {
                    System.out.println(nome + " não tem saldo suficiente. Encerrando compras.");
                    break;
                }
                // Escolhe um valor aleatório entre R$ 100,00 e R$ 200,00
                double valorCompra = (random.nextInt(2) + 1) * 100;
                if (conta.getSaldo() >= valorCompra) {
                    conta.sacar(valorCompra);
                    loja.getConta().depositar(valorCompra); // Pagamento à loja
                    System.out.println(nome + " comprou na " + loja.getNome() + " no valor de R$ " + valorCompra);
                } else {
                    System.out.println(nome + " não tem saldo suficiente para comprar na " + loja.getNome());
                }
            }
            // Pausa para simular o tempo entre compras
            try {
                Thread.sleep(random.nextInt(1000) + 500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
