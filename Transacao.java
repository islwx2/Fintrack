package model;

public abstract class Transacao {
    protected String descricao;
    protected double valor;

    public Transacao(String descricao, double valor) {
        this.descricao = descricao;
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public abstract double calcularSaldo();

    @Override
    public String toString() {
        return "Descrição: " + descricao + " | Valor: R$ " + valor;
    }
} /