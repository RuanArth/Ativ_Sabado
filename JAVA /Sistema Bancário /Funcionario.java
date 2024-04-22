package sistemabancario;

import java.util.Random;

class Funcionario extends Thread {
    private final String nome;
    private final Loja loja;
    private final Banco banco;
    private final Conta contaSalario;
    private final Conta contaInvestimento;

    public Funcionario(String nome, Loja loja, Banco banco) {
        this.nome = nome;
        this.loja = loja;
        this.banco = banco;
        this.contaSalario = new Conta(0, 0); // Conta inicialmente vazia
        this.contaInvestimento = new Conta(0, 0); // Conta de investimento inicialmente vazia
    }

    public void receberSalario() {
        double salario = 1400.0;
        contaSalario.depositar(salario);
        double valorInvestimento = salario * 0.20;
        contaInvestimento.depositar(valorInvestimento);
        System.out.println(nome + " recebeu o salário de R$ " + salario + " e investiu R$ " + valorInvestimento);
    }

    public Conta getContaSalario() {
        return contaSalario;
    }

    public Conta getContaInvestimento() {
        return contaInvestimento;
    }

    @Override
    public void run() {
        Random random = new Random();
        while (true) {
            synchronized (loja.getConta()) {
                if (loja.getConta().getSaldo() < 1400.0) {
                    System.out.println(nome + " está aguardando pagamento. Saldo atual: R$ " + loja.getConta().getSaldo());
                } else {
                    loja.pagarFuncionarios(); // Chamada do método para pagar os funcionários
                    break;
                }
            }
            try {
                Thread.sleep(random.nextInt(1000) + 500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
