package model;

import utili.Util;

public class Produto {   private static int count = 1;

    private int id;
    private String nome;
    private Double preco;

    public Produto(String nome, Double preco) {
        this.id= count;
        this.nome = nome;
        this.preco = preco;
        Produto.count += 1;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String toString() {
        return "id: " + this.getId() +
                "\n Nome: " + this.getNome() +
                "\n Preço: " + Util.doubleToString (this.getPreco());

    }
}
