package fatec.sjc.sp.exame2_lp1.classes;

public class Computador {

    private int id;
    private String marca;
    private String processador;
    private int memoriaRAM;

    public Computador(int id, String marca, String processador, int memoriaRAM) {
        setId(id);
        setMarca(marca);
        setProcessador(processador);
        setMemoriaRAM(memoriaRAM);
    }

    public Computador(String marca, String processador, int memoriaRAM) {
        setMarca(marca);
        setProcessador(processador);
        setMemoriaRAM(memoriaRAM);
    }

    public void ligar() {
        System.out.println(marca + " está ligando.");
    }

    public void desligar() {
        System.out.println(marca + " está desligando.");
    }

    public void infoSistema() {
        System.out.println("Marca: " + marca + ", Processador: " + processador + ", Memória RAM: " + memoriaRAM + "GB");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getProcessador() {
        return processador;
    }

    public void setProcessador(String processador) {
        this.processador = processador;
    }

    public int getMemoriaRAM() {
        return memoriaRAM;
    }

    public void setMemoriaRAM(int memoriaRAM) {
        this.memoriaRAM = memoriaRAM;
    }
}
