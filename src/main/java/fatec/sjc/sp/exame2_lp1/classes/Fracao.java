package fatec.sjc.sp.exame2_lp1.classes;

public class Fracao {

    int numerador;
    int denominador;

    public Fracao(int numerador, int denominador) {
        this.numerador = numerador;
        this.denominador = denominador;
        simplificar();
    }

    public Fracao somar(Fracao outra) {
        int num = this.numerador * outra.denominador + outra.numerador * this.denominador;
        int den = this.denominador * outra.denominador;
        return new Fracao(num, den);
    }

    public Fracao subtrair(Fracao outra) {
        int num = this.numerador * outra.denominador - outra.numerador * this.denominador;
        int den = this.denominador * outra.denominador;
        return new Fracao(num, den);
    }

    public Fracao multiplicar(Fracao outra) {
        int num = this.numerador * outra.numerador;
        int den = this.denominador * outra.denominador;
        return new Fracao(num, den);
    }

    public Fracao dividir(Fracao outra) {
        int num = this.numerador * outra.denominador;
        int den = this.denominador * outra.numerador;
        return new Fracao(num, den);
    }

    private void simplificar() {
        int mdc = calcularMDC(numerador, denominador);
        numerador /= mdc;
        denominador /= mdc;
    }

    private int calcularMDC(int a, int b) {
        if (b == 0) return a;
        return calcularMDC(b, a % b);
    }

    public void exibir() {
        System.out.println(numerador + "/" + denominador);

    }
}
