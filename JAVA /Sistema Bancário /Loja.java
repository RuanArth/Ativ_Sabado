package sistemabancario;

import java.util.ArrayList;
import java.util.List;

class Loja {
    private String nome;
    private Conta conta;
    private List<Funcionario> funcionarios;

    public Loja(String nome, Conta conta) {
        this.nome = nome;
        this.conta = conta;
        this.funcionarios = new ArrayList<>();
    }

    public void adicionarFuncionario(Funcionario funcionario) {
        synchronized (funcionarios) {
            funcionarios.add(funcionario);
        }
    }

    public List<Funcionario> getFuncionarios() {
        synchronized (funcionarios) {
            return new ArrayList<>(funcionarios);
        }
    }

    public Conta getConta() {
        return conta;
    }

    public String getNome() {
        return nome;
    }

    public synchronized void pagarFuncionarios() {
        double totalSalario = funcionarios.size() * 1400.0;
        if (conta.getSaldo() >= totalSalario) {
            for (Funcionario funcionario : funcionarios) {
                funcionario.receberSalario();
            }
            conta.sacar(totalSalario);
            System.out.println("Salários pagos aos funcionários da " + nome);
        } else {
            System.out.println("A " + nome + " não tem saldo suficiente para pagar os funcionários.");
        }
    }
}
