package fatec.sjc.sp.exame2_lp1.classes;

public class Filme {

    private String titulo;
    private String genero;
    private int duracao; // minutos

    public Filme(String titulo, String genero, int duracao) {
        setTitulo(titulo);
        setGenero(genero);
        setDuracao(duracao);
    }

    public void reproduzir() {
        System.out.println("Reproduzindo o filme: " + titulo);
    }

    public void pausar() {
        System.out.println("Pausando o filme: " + titulo);
    }

    public void exibirInfo() {
        System.out.println("Título: " + titulo + ", Gênero: " + genero + ", Duração: " + duracao + " minutos");
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }
}
