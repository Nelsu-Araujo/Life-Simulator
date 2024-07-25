public class Inseto extends SerVivo{

    private boolean venenoso;

    /**
     * Metodo Construtor
     * @param nome nome do inseto
     * @param especie especie do inseto
     * @param pais pais de origem do inseto
     * @param idade idade do inseto
     * @param venenoso true se for venenoso, false se não for venenoso
     */
    public Inseto(String nome, String especie, String pais, int idade, boolean venenoso) {
        super(nome, especie, pais, idade);
        this.venenoso = venenoso;
    }

    public boolean isVenenoso() {
        return venenoso;
    }

    public void setVenenoso(boolean venenoso) {
        this.venenoso = venenoso;
    }
}
