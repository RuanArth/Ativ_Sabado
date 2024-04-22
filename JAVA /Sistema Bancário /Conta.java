package sistemabancario;

class Conta {
    private int numero;
    private double saldo;

    public Conta(int numero, double saldo) {
        this.numero = numero;
        this.saldo = saldo;
    }

    public synchronized void depositar(double valor) {
        saldo += valor;
    }

    public synchronized void sacar(double valor) {
        saldo -= valor;
    }

    public synchronized double getSaldo() {
        return saldo;
    }

    public int getNumero() {
        return numero;
    }
}
