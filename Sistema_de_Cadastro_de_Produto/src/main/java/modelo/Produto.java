package modelo;

public class Produto implements Comparable<Produto> {

    private String nome;
    private String descricao;
    private double valor;
    private boolean disponivel;

    public Produto(){

    }
    public Produto(String nome, String descricao, double valor, boolean disponivel){
        this.setNome(nome);
        this.setDescricao(descricao);
        this.setValor(valor);
        this.setDisponivel(disponivel);
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        if (valor >= 0){
            this.valor = valor;
        } else {
            throw new IllegalArgumentException("Valor não pode ser negativo.");
        }
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isDisponivel() {
        return disponivel;
    }
    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }


    @Override
    public String toString() {
        return nome + " - " + descricao + " - R$" + valor +
                " - Disponível para venda: " + (disponivel? "Sim" : "Não");
    }

    @Override
    public int compareTo(Produto outro){
        return Double.compare(this.valor, outro.valor);
    }
}
