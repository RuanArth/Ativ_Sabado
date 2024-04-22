package sistemabancario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Banco {
    private static Banco instance;
    private final List<Conta> contas;
    private final Lock lock;
    private final Map<String, Double> transferencias;

    private Banco() {
        this.contas = new ArrayList<>();
        this.lock = new ReentrantLock(); // Utiliza um ReentrantLock para sincronização
        this.transferencias = new HashMap<>();
    }

    public static synchronized Banco getInstance() {
        if (instance == null) {
            instance = new Banco();
        }
        return instance;
    }

    public void adicionarConta(Conta conta) {
        contas.add(conta);
    }

    public List<Conta> getContas() {
        return new ArrayList<>(contas); // Retorna uma cópia da lista para evitar modificações externas
    }

    public void transferir(Conta origem, Conta destino, double valor) {
        lock.lock(); // Bloqueia o acesso concorrente
        try {
            if (origem.getSaldo() >= valor) {
                origem.sacar(valor);
                destino.depositar(valor);
                // Registrar a transferência
                String chave = origem.getNumero() + " -> " + destino.getNumero();
                transferencias.put(chave, valor);
                System.out.println("Transferência de R$ " + valor + " da conta " + origem.getNumero() +
                        " para a conta " + destino.getNumero() + " realizada com sucesso.");
            } else {
                System.out.println("Saldo insuficiente na conta " + origem.getNumero() +
                        " para transferir R$ " + valor);
            }
        } finally {
            lock.unlock(); // Libera o lock após a operação
        }
    }

    public void exibirSaldos() {
        System.out.println("\nSaldo final das contas:");
        for (Conta conta : contas) {
            System.out.println("Conta " + conta.getNumero() + ": R$ " + conta.getSaldo());
        }
        System.out.println("\nTransferências realizadas:");
        for (Map.Entry<String, Double> entry : transferencias.entrySet()) {
            System.out.println(entry.getKey() + ": R$ " + entry.getValue());
        }
    }
}
