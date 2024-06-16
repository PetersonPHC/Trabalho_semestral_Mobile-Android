package fateczl.aps.trabalhosemestral_mobile_alugueldecarros.model;

public class Carro extends Veiculo implements IVeiculo {

    private int qtdPortas;

    //Construtores
    public Carro() {
        super();
    }

    public Carro(Integer chassi, String nome, String montadora, int numeroDeRodas, int qtdPortas) {
        super(chassi, nome, montadora, numeroDeRodas);
        this.qtdPortas = qtdPortas;
    }

    //Getters e Setters
    public int getQtdPortas() {
        return qtdPortas;
    }

    public void setQtdPortas(int qtdPortas) {
        this.qtdPortas = qtdPortas;
    }

    //Métodos
    @Override
    public String acelerar() {
        return "Carro acelera... Vruuuuummmmm!!!!!";
    }

    @Override
    public String frear() {
        return "Carro Freia...  Screeeeech!!!!!";
    }

    @Override
    public String toString() {
        return "Carro\n" +
                "Chassi: " + chassi +
                "\nMontadora: " + montadora +
                "\nNome: " + nome +
                "\nNumero De Rodas: " + numeroDeRodas +
                "\nQuantidade de Portas: " + qtdPortas;
    }
}
