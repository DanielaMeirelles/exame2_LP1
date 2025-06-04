package fatec.sjc.sp.exame2_lp1.classes;

public class Flor {

    private String especie;
    private String cor;
    private double altura;

    public Flor(String especie, String cor, double altura) {
        setEspecie(especie);
        setCor(cor);
        setAltura(altura);
    }

    public void florescer() {
        if (altura > 0.3) {
            System.out.println("A flor da espécie " +especie+ " está florescendo!");
        } else  {
            System.out.println("A flor da espécie " +especie+ " ainda está pequena para florescer!");
        }
    }

    public void murchar() {
        System.out.println("A flor da espécie " +especie+ " está murchando!");
    }

    public void crescer(double crescimento) {
        if (crescimento >0) {
            altura += crescimento;
            System.out.println("A flor da espécie " +especie+ " cresceu " +crescimento+ " centímetros." +
                    "Agora ela possuí " +altura+ " centímetros de altura.");
        } else {
            System.out.println("O valor de crescimento deve ser positivo.");
        }
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        if (!especie.isEmpty()) {
            this.especie = especie;
        } else {
            this.especie = "Desconhecida";
        }
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        if (!cor.isEmpty()) {
            this.cor = cor;
        } else {
            this.cor = "Sem cor definida";
        }
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        if (altura >= 0) {
            this.altura = altura;
        } else {
            this.altura = 0; // Evita altura negativa
        }
    }
}
