package fatec.sjc.sp.exame2_lp1.classes;

public class Livro {

    private String titulo;
    private String autor;
    private int paginas;

    public Livro(String titulo, String autor, int paginas) {
        setTitulo(titulo);
        setAutor(autor);
        setPaginas(paginas);
    }

    public void abrir() {
        System.out.println("Abrindo o livro: " + titulo);
    }

    public void fechar() {
        System.out.println("Fechando o livro: " + titulo);
    }

    public void mostrarDetalhes() {
        System.out.println("Título: " + titulo + ", Autor: " + autor + ", Páginas: " + paginas);
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }
}
