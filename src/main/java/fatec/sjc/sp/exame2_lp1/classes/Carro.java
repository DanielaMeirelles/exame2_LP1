package fatec.sjc.sp.exame2_lp1.classes;

public class Carro {

    private String modelo;
    private String cor;
    private double velocidade;

    public Carro(String modelo, String cor, double velocidade) {
        setModelo(modelo);
        setCor(cor);
        setVelocidade(velocidade);
    }

    public void acelerar() {
        velocidade += 10;
        System.out.println(modelo + " acelerou. Velocidade atual: " + velocidade + " km/h.");
    }

    public void frear() {
        velocidade -= 10;
        System.out.println(modelo + " freou. Velocidade atual: " + velocidade + " km/h.");
    }

    public void exibirDados() {
        System.out.println("Modelo: " + modelo + ", Cor: " + cor + ", Velocidade: " + velocidade + " km/h.");
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public double getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(double velocidade) {
        this.velocidade = velocidade;
    }
}
