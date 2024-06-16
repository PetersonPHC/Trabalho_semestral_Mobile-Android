package fateczl.aps.trabalhosemestral_mobile_alugueldecarros.model;

public abstract class Veiculo {
    protected Integer chassi;
    protected String nome;
    protected String montadora;
    protected int numeroDeRodas;

    //Construtores
    public Veiculo() {
        super();
    }

    public Veiculo(int chassi, String nome, String montadora, int numeroDeRodas) {
        this.chassi = chassi;
        this.nome = nome;
        this.montadora = montadora;
        this.numeroDeRodas = numeroDeRodas;
    }

    //Getters e Setters
    public int getChassi() {
        return chassi;
    }

    public void setChassi(int chassi) {
        this.chassi = chassi;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMontadora() {
        return montadora;
    }

    public void setMontadora(String montadora) {
        this.montadora = montadora;
    }

    public int getNumeroDeRodas() {
        return numeroDeRodas;
    }

    public void setNumeroDeRodas(int numeroDeRodas) {
        this.numeroDeRodas = numeroDeRodas;
    }

    @Override
    public String toString() {
        return "Veiculo\n" +
                "Chassi: " + chassi +
                "\nNome: " + nome  +
                "\nMontadora: " + montadora +
                "\nNúmero De Rodas:" + numeroDeRodas;
    }
}
