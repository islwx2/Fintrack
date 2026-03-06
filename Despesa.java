package model;

public class Despesa extends Transacao {
    public Despesa(String descricao, double valor) {
        super(descricao, valor);
    }

    @Override
    public double calcularSaldo() {
        return -valor;
    }
}