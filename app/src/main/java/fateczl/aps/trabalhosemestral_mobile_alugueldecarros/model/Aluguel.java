package fateczl.aps.trabalhosemestral_mobile_alugueldecarros.model;

public class Aluguel {
    private int numero;
    private String dataInicio;
    private String dataFinal;
    private Cliente cliente;
    private Carro carro;

    //Construtores
    public Aluguel() {
        super();
    }
    public Aluguel(int numero, String dataInicio, String dataFinal, Cliente cliente, Carro carro) {
        this.numero = numero;
        this.dataInicio = dataInicio;
        this.dataFinal = dataFinal;
        this.cliente = cliente;
        this.carro = carro;
    }

    //Getters e Setters
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(String dataFinal) {
        this.dataFinal = dataFinal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    @Override
    public String toString() {
        return "Aluguel\n" +
                "Número: " + numero +
                "\nData Inicio: " + dataInicio +
                "\nData Final: " + dataFinal +
                "\nCliente: " + cliente.getNome() +
                "\nCPF Cliente: " + cliente.getCPF() +
                "\nCarro: " + carro.getNome() +
                "\nChassi Carro: " + carro.getChassi() +
                "\n";
    }
}
