package fateczl.aps.trabalhosemestral_mobile_alugueldecarros.model;


public class Cliente {

    private int CPF;
    private String nome;

    //Construtores
    public Cliente() {
        super();
    }

    public Cliente(int cpf, String Nome) {
        this.CPF = cpf;
        this.nome = Nome;
    }

    //Getters e Setters
    public int getCPF() {
        return CPF;
    }

    public void setCPF(int CPF) {
        this.CPF = CPF;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Cliente\n" +
                "CPF: " + CPF +
                "\nNome: " + nome;
    }
}
