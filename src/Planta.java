import Enums.FamiliaPlanta;

public class Planta extends SerVivo{

    private FamiliaPlanta familia;
    private int grauDefesa;


    /**
     * Metodo Construtor
     * @param nome nome da planta
     * @param especie especie da planta
     * @param pais pais de origem da planta
     * @param idade idade da planta
     * @param familia familia da planta
     * @param grauDefesa grau de defesa da planta (de 0 a 5)
     */
    public Planta(String nome, String especie, String pais, int idade, FamiliaPlanta familia, int grauDefesa) {
        super(nome, especie, pais, idade);
        this.familia = familia;
        this.grauDefesa = grauDefesa;


    }

    public FamiliaPlanta getFamilia() {
        return familia;
    }

    public void setFamilia(FamiliaPlanta familia) {
        this.familia = familia;
    }

    public int getGrauDefesa() {
        return grauDefesa;
    }

    public void setGrauDefesa(int grauDefesa) {
        this.grauDefesa = grauDefesa;
    }
}

