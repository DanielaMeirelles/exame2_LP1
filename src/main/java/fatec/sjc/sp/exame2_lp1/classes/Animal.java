package fatec.sjc.sp.exame2_lp1.classes;

public class Animal {

    private int id;
    private String nome;
    private String especie;
    private int idade;

    public Animal(int id, String nome, int idade, String especie) {
        setId(id);
        setNome(nome);
        setEspecie(especie);
        setIdade(idade);
    }

    public Animal(String nome, String especie, int idade) {
        setNome(nome);
        setEspecie(especie);
        setIdade(idade);
    }

    public void emitirSom() {
        System.out.println(nome + " está emitindo som.");
    }

    public void correr() {
        System.out.println(nome + " está correndo.");
    }

    public void mostrarInfo() {
        System.out.println("Nome: " + nome + ", Espécie: " + especie + ", Idade: " + idade + " anos.");
    }

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

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
}
