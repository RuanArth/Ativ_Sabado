package sistemabancario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Banco {
    private static Banco instance;
    private List<Conta> contas;
    private Object lock;
    private Map<String, Double> transferencias; // Mapa para armazenar as transferências

    private Banco() {
        this.contas = new ArrayList<>();
        this.lock = new Object();
        this.transferencias = new HashMap<>();
    }

    public static Banco getInstance() {
        if (instance == null) {
            instance = new Banco();
        }
        return instance;
    }

    public synchronized void adicionarConta(Conta conta) {
        contas.add(conta);
    }

    public synchronized List<Conta> getContas() {
        return contas;
    }

    public void transferir(Conta origem, Conta destino, double valor) {
        synchronized (lock) {
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
