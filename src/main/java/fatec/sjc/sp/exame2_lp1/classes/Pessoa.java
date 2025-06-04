package fatec.sjc.sp.exame2_lp1.classes;

public class Pessoa {

    private int id;
    private String nome;
    private int idade;
    private String cpf;

    public Pessoa(int id, String nome, int idade, String cpf) {
        setId(id);
        setNome(nome);
        setIdade(idade);
        setCpf(cpf);
    }

    public Pessoa(String nome, int idade, String cpf) {
        setNome(nome);
        setIdade(idade);
        setCpf(cpf);
    }

    public void falar() {
        System.out.println(nome+ " está falando.");
    }

    public void andar() {
        if (idade < 1) {
            System.out.println(nome+ " ainda é muito jovem para andar.");
        } else {
            System.out.println(nome+ " está andando.");
        }
    }

    public void dormir() {
        System.out.println(nome+ " está dormindo.");
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
        if (!nome.isEmpty()) {
            this.nome = nome;
        } else {
            this.nome = "Desconhecido";
        }
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        if (idade >= 0) {
            this.idade = idade;
        } else {
            this.idade = 0; // Evita idade negativa
        }
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if (cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")){
            this.cpf = cpf;
        } else  {
            this.cpf = "000.000.000-00"; // CPF padrão inválido
        }
    }
}
