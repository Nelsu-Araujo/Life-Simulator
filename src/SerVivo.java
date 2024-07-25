public class SerVivo {

    private String nome;
    private String especie;
    private String pais;
    private int idade;

    /**
     * Método Construtor
     * @param nome nome do animal
     * @param especie espécie do animal
     * @param pais país de origem do animal
     * @param idade idade do animal
     */
    public SerVivo(String nome, String especie, String pais, int idade) {
        this.nome = nome;
        this.especie = especie;
        this.pais = pais;
        this.idade = idade;
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

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    /**
     * Método que lista todas as informações associadas aos Seres Vivos
     */
    public void exibirDetalhes(){
        System.out.println("Nome do SerVivo: "+this.nome);
        System.out.println("País de Origem do Ser Vivo: "+this.pais);
        System.out.println("Idade do Ser Vivo: "+this.idade);
        System.out.println("Espécie do Ser Vivo: "+this.especie);
    }

}
