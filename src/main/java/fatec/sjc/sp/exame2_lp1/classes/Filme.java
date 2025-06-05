package fatec.sjc.sp.exame2_lp1.classes;

public class Filme {

    private int id;
    private String titulo;
    private String genero;
    private int duracao; // minutos

    public Filme(int id, String titulo, String genero, int duracao) {
        setId(id);
        setTitulo(titulo);
        setGenero(genero);
        setDuracao(duracao);
    }

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
