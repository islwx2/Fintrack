package model;

public class Receita extends Transacao {
    public Receita(String descricao, double valor) {
        super(descricao, valor);
    }

    @Override
    public double calcularSaldo() {
        return valor;
    }
}