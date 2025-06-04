package fatec.sjc.sp.exame2_lp1.classes;

public class Produto {

    private String nome;
    private double preco;
    private int quantidadeEmEstoque;

    public Produto(String nome, double preco, int quantidadeEmEstoque) {
        setNome(nome);
        setPreco(preco);
        setQuantidadeEmEstoque(quantidadeEmEstoque);
    }

    public void vender() {
        if (quantidadeEmEstoque > 0) {
            quantidadeEmEstoque--;
            System.out.println("Produto vendido: " + nome);
        } else {
            System.out.println("Estoque esgotado para: " + nome);
        }
    }

    public void repor() {
        quantidadeEmEstoque++;
        System.out.println("Produto reposto: " + nome);
    }

    public void exibirProduto() {
        System.out.println("Nome: " + nome + ", Preço: R$" + preco + ", Estoque: " + quantidadeEmEstoque);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidadeEmEstoque() {
        return quantidadeEmEstoque;
    }

    public void setQuantidadeEmEstoque(int quantidadeEmEstoque) {
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }
}
