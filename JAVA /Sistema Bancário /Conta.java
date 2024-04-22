package sistemabancario;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Conta {
    private final int numero;
    private double saldo;
    private final Lock lock; // Adiciona um lock para controlar o acesso concorrente

    public Conta(int numero, double saldo) {
        this.numero = numero;
        this.saldo = saldo;
        this.lock = new ReentrantLock(); // Inicializa o lock
    }

    public void depositar(double valor) {
        lock.lock(); // Bloqueia o acesso concorrente ao saldo
        try {
            saldo += valor;
        } finally {
            lock.unlock(); // Libera o lock após a operação
        }
    }

    public void sacar(double valor) {
        lock.lock(); // Bloqueia o acesso concorrente ao saldo
        try {
            saldo -= valor;
        } finally {
            lock.unlock(); // Libera o lock após a operação
        }
    }

    public double getSaldo() {
        lock.lock(); // Bloqueia o acesso concorrente ao saldo
        try {
            return saldo;
        } finally {
            lock.unlock(); // Libera o lock após a operação
        }
    }

    public int getNumero() {
        return numero;
    }
}
