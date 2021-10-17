package br.pro.felipi.trab;

public class Produto {

    public int id;

    public String nome, categoria, fone, cidade;


    public Produto() {

    }

    public Produto(String nome, String categoria, String fone, String cidade) {
        this.nome = nome;
        this.categoria = categoria;
        this.fone = fone;
        this.cidade = cidade;
    }

    @Override
    public String toString() { return   nome + " | " + categoria;    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getFone() { return fone; }

    public void setFone(String fone) { this.fone = fone; }

    public String getCidade() { return cidade; }

    public void setCidade(String cidade) { this.cidade = cidade; }
}
