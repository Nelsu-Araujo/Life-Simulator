import Enums.AlimentacaoAnimal;
import Enums.FamiliaPlanta;

import java.util.ArrayList;
import java.util.Random;

public class MeioAmbiente {

    private String nome;
    private double agua;
    private ArrayList<SerVivo> seres;

    /**
     * Metodo construtor
     *
     * @param nome nome do meio ambiente
     * @param agua litros de agua no meio ambiente
     */
    public MeioAmbiente(String nome, double agua) {
        this.nome = nome;
        this.agua = agua;
        this.seres = new ArrayList<SerVivo>(seres);
    }


    /**
     * Método que analisa a água disponível no meio ambiente no casa da familia das Plantas
     *
     * @param indexPlanta
     * @return
     */
    public boolean plantaBebe(int indexPlanta) {

        Planta planta = (Planta) this.seres.get(indexPlanta); // Casting

        // Variáveis
        double aguaNecessaria = 0; // é em litros;


        if (indexPlanta >= 0 && indexPlanta < this.seres.size()) { //para definir se o index do array que definimos para os seres está em conformidade

            // análise da agua disponivel de acordo com a familia da planta
            switch (planta.getFamilia()) {
                case ARVORES:
                    aguaNecessaria = 1;
                    break;
                case FLORES:
                    aguaNecessaria = 0.1;
                    break;
                case ERVAS:
                    aguaNecessaria = 0.25;
                    break;
            }

        } else {
            return false; // indice da planta não é valido
        }

        if (this.agua >= aguaNecessaria) { //
            this.agua = this.agua - aguaNecessaria; // retornamos true portanto diminuimos a agua no ambiente
            return true;

        } else {
            this.seres.remove(indexPlanta);
            return false; // a planta não bebe e seca, logo é removida
        }
    }


    /**
     * Metodo da planta que come insetos. Caso não haja insetos a planta morre
     *
     * @param indexPlanta
     * @return
     */
    public boolean plantaComeInsetos(int indexPlanta) {
        Planta plantaInsetos = (Planta) this.seres.get(indexPlanta); //casting

        if (indexPlanta >= 0 && indexPlanta < this.seres.size()) { //para definir se o index do array que definimos para os seres está em conformidade

            boolean insetosPresentes = false;
            for (SerVivo ser : this.seres) {
                if (ser instanceof Inseto) {
                    insetosPresentes = true;
                    this.seres.remove(ser); // Removemos o inseto
                    break;
                }
            }

            if (insetosPresentes) {
                return true;
            } else {
                this.seres.remove(plantaInsetos);
                return false;
            }
        } else {
            return false; // O indice da planta não é valido
        }
    }


    /**
     * Método que imprime o facto de esyar muito vento no meio ambiente
     *
     * @param indexPlanta
     */
    public void plantaAbanaComVento(int indexPlanta) {

        System.out.println("\n Está muito vento. \n");
    }


    /**
     * Metodo que imprime o barulho do animal através de um get
     *
     * @param indexAnimal
     */
    public void animalFazBarulho(int indexAnimal) {

        System.out.println(Animal.getBarulho());
    }


    /**
     * Método que nos diz o animal que se movimentou
     *
     * @param indexAnimal
     */
    public void animalMovimenta(int indexAnimal) {
        System.out.println(" O " + this.nome + "movimentou-se");
    }


    /**
     * Método semelhante ao planta bebe mas aplicado ao animal
     *
     * @param indexAnimal
     * @return
     */
    public boolean animalBebe(int indexAnimal) {

        Animal animal = (Animal) this.seres.get(indexAnimal);

        double aguaNecessaria = animal.getPeso() * 0.025; // A agua necessária é igual ao peso do animal * 0,025L

        if (indexAnimal <= 0 && indexAnimal < this.seres.size()) {
            if (this.agua >= aguaNecessaria) { // Verificar se há agua suficiente
                this.agua = this.agua - aguaNecessaria; // Reduzir agua do meio ambiente
                return true;
            }
            this.seres.remove(animal); // O animal morre e é retirado
        }
        return false;
    }


    /**
     * Método que verifica se o animal pode comer uma planta com base na sua inteligência e no grau de defesa da planta
     *
     * @param animal
     * @param planta
     * @return
     */
    private boolean animalComePlanta(Animal animal, Planta planta) {

        double inteligenciaAnimal = animal.getInteligencia();
        int grauDefesaPlanta = planta.getGrauDefesa();

        // Verificamos se a inteligência do animal é maior que o mecanismo de defesa da planta
        if (inteligenciaAnimal >= (grauDefesaPlanta * 20)) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * Método que nos diz se um animal pode comer outro animal
     *
     * @param atacante
     * @param defensor
     * @return
     */
    private boolean animalComeAnimal(Animal atacante, Animal defensor) {
        // Vamos verificar se o animal que ataca é mais inteligente que o animal que defende
        if (atacante.getInteligencia() >= defensor.getInteligencia()) {
            return true;
        }

        // Aqui vamos verificar se o animal que ataca tem um índice de capacidade maior que o que defende com base na formula do enunciado
        double indexCapacidadeAtacante = (1 * atacante.getPeso()) + (2.5 * atacante.getInteligencia());
        double indexCapacidadeDefensor = (1 * defensor.getPeso()) + (2.5 * defensor.getInteligencia());

        if (indexCapacidadeAtacante >= indexCapacidadeDefensor) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * Método que define que o animal vai comer mediante uma série de fatores
     *
     * @param indexAnimal
     * @return
     */
    public boolean animalCome(int indexAnimal) {

        Animal animal = (Animal) this.seres.get(indexAnimal); //casting

        if (indexAnimal <= 0 && indexAnimal < this.seres.size()) {

            AlimentacaoAnimal alimentacao;

            if (animal.isFome()) { // Selecionamos o animal e vemos se tem fome. Se sim:
                alimentacao = animal.getAlimentacao();

                if (alimentacao == AlimentacaoAnimal.CARNIVORO) { // Começamos por definir o animal pelo tipo de alimentação
                    int animalDefende = (int) (Math.random() * seres.size()); // Vamos definir outro animal que será o defensor (inseto ou animal mesmo)
                    // Usei a função Math.random para escolher aleatoriamente outro ser vivo
                    // Obtenha o defensor (outro ser vivo)
                    SerVivo defensor = seres.get(animalDefende);

                    if (defensor instanceof Inseto) { // Se o animal for um inseto e se for venenoso...
                        Inseto inseto = (Inseto) defensor;
                        if (inseto.isVenenoso()) {
                            // ... o animal atacante morre e o inseto morre
                            seres.remove(indexAnimal);
                            seres.remove(animalDefende);
                            return false;
                        } else {
                            // Senão o inseto morre e o animal fica sem fome
                            seres.remove(animalDefende);
                            animal.setFome(false);
                            return true;
                        }
                    } else {
                        // e numa outra possibilidade o animal não come o inseto e continua com fome
                        animal.setFome(true);
                        return false;
                    }
                }
            }
            // mesma logica do anterior. Só introduzi o metodo animalComePlanta para verificar se o animal pode comer uma planta com base na sua inteligência e no grau de defesa
            if (alimentacao == AlimentacaoAnimal.HERBIVORO) {
                int planta = (int) (Math.random() * seres.size());
                SerVivo defensor = seres.get(planta);

                if ((defensor instanceof Planta)) {
                    planta = -1;
                }
            }

            Planta planta = (Planta) this.seres.get(planta);

            // Metodo
            if (animalComePlanta(animal, planta)) {
                seres.remove(planta);
                animal.setFome(false);
                return true;
            } else {
                animal.setFome(true);
                return false;
            }

            // mesma logica das outras. Aqui introduzi o metodo animalComeAnimal para verificar se o animal pode comer outro animal com base na fórmula: (1*peso)+(2.5*inteligência)
            if (alimentacao == AlimentacaoAnimal.OMNIVORO) {
                int animalDefende = (int) (Math.random() * seres.size());

                SerVivo defensor = seres.get(animalDefende);

                if (defensor instanceof Inseto) {
                    Inseto inseto = (Inseto) defensor;
                    if (inseto.isVenenoso()) {
                        seres.remove(indexAnimal);
                        seres.remove(animalDefende);
                        return false;
                    } else {
                        seres.remove(animalDefende);
                        animal.setFome(false);
                        return true;
                    }
                } else {
                    if (animalComeAnimal(animal, (Animal) defensor)) {
                        seres.remove(animalDefende);
                        animal.setFome(false);
                        return true;
                    } else {
                        animal.setFome(true);
                        return false;
                    }
                }
            }

            // Se o animal não tem fome:
            System.out.println("O animal está de barriga cheia");


            // Se o índice não for válido
            return false;
        }
        return false;
    }


    /**
     * Método que imprime o barulho de um inseto em função do que escolhermos
     */
    public void insetoChateia() {

        // Usei a função Math.random para escolher aleatoriamente 1 dos 3 barulhos
        int barulho = (int) (Math.random() * 3);


        switch (barulho) {
            case 1:
                System.out.println("Bzzzz bzzzz");
                break;
            case 2:
                System.out.println("Tic tic tic tic");
                break;
            case 3:
                System.out.println("Psssssssss");
                break;
            default:
                System.out.println("Não conheço esse barulho");
        }

    }


    /**
     * Método para adicionar e listar os seres vivos
     *
     * @param Ser
     */
    public void adicionarEListarSerVivo(SerVivo Ser) {
        seres.add(Ser);

        System.out.println("Seres vivos no meio ambiente:");
        for (SerVivo ser : this.seres) {
            System.out.println(ser);
        }
    }


    /**
     * Método auxiliar do Simulador para escolher um acontecimento no ambiente
     */
    public void acontecimento() {
        Random random = new Random();
        int indexRandom;

        // Vou declarar a variável acontecimento
        int acontecimento = random.nextInt(4) + 1;

        // Guardar os seus índices num novo array auxiliar. (usar ArrayList para facilitar)
        ArrayList<Integer> plantasIndex = new ArrayList<>();
        ArrayList<Integer> animaisIndex = new ArrayList<>();
        ArrayList<Integer> insectosIndex = new ArrayList<>();

        for (int i = 0; i < this.seres.size(); i++) {
            if (this.seres.get(i) instanceof Planta) {
                plantasIndex.add(i);
            } else if (this.seres.get(i) instanceof Animal) {
                animaisIndex.add(i);
            } else if (this.seres.get(i) instanceof Inseto) {
                insectosIndex.add(i);
            }
        }

        // No caso de uma planta...
        switch (acontecimento) {
            case 1:
                System.out.println("Uma planta irá ter uma ação");

                if (plantasIndex.size() == 0) {
                    System.out.println("Não existem plantas para fazer uma acção");

                } else {
                    indexRandom = (int) (Math.random() * plantasIndex.size());
                    int plantaRandomIndex = plantasIndex.get(indexRandom);

                    // Guarda a planta seleccionada
                    Planta plantaSelecionada = (Planta) this.seres.get(plantaRandomIndex);

                    // Random para definir aleatoriamento o número de acções que a planta pode fazer
                    int plantaAction;

                    if (plantaSelecionada.getFamilia().equals(FamiliaPlanta.COMEINSETOS)) {
                        plantaAction = random.nextInt(3) + 1;
                    } else {
                        plantaAction = random.nextInt(2) + 1;
                    }

                    // Será seleccionada uma das 3 acções:
                    switch (plantaAction) {
                        case 1:
                            this.plantaAbanaComVento(plantaRandomIndex);
                            System.out.println("A planta " + plantaSelecionada.getNome() + " abanou com o vento");
                            break;
                        case 2:
                            System.out.println("A planta " + plantaSelecionada.getNome() + " vai tentar beber água:");
                            this.plantaBebe(plantaRandomIndex);
                            break;
                        case 3:
                            System.out.println("A planta " + plantaSelecionada.getNome() + " vai tentar comer um insecto:");
                            this.plantaComeInsetos(plantaRandomIndex);
                            break;
                    }
                }
                break;

            // No caso de um animal...
            case 2:
                System.out.println("Um animal irá ter uma ação");

                if (animaisIndex.size() == 0) {
                    System.out.println("Não existem animais para fazer uma acção");

                } else {
                    indexRandom = (int) (Math.random() * animaisIndex.size());
                    int animalRandomIndex = animaisIndex.get(indexRandom);

                    // Animal seleccionado
                    Animal animalSelecionado = (Animal) seres.get(animalRandomIndex);

                    // Random para definir aleatoriamento o número de acções que o animal pode fazer
                    int animalAccao = random.nextInt(4) + 1;

                    // Será seleccionada uma das 4 acções:
                    switch (animalAccao) {
                        case 1:
                            System.out.println("O animal " + animalSelecionado.getNome() + "faz o barulho:");
                            this.animalFazBarulho(animalRandomIndex);
                            break;
                        case 2:
                            System.out.println("O animal " + animalSelecionado.getNome() + "movimenta-se:");
                            this.animalMovimenta(animalRandomIndex);
                            break;
                        case 3:
                            System.out.println("O animal " + animalSelecionado.getNome() + "bebe:");
                            this.animalBebe(animalRandomIndex);
                            break;
                        case 4:
                            System.out.println("O animal " + animalSelecionado.getNome() + "come:");
                            this.animalCome(animalRandomIndex);
                            break;
                    }
                }
                break;

            // No caso de um insecto...
            case 3:
                System.out.println("Um inseto irá ter uma ação");
                if (insectosIndex.size() == 0) {
                    System.out.println("Não existem insectos para fazer uma acção");
                } else {
                    indexRandom = (int) (Math.random() * insectosIndex.size());
                    int insectoRandomIndex = insectosIndex.get(indexRandom);

                    // Inseto seleccionado
                    Inseto insectoSelecionado = (Inseto) seres.get(insectoRandomIndex);

                    System.out.println("O insecto " + insectoSelecionado.getNome() + " chateia");
                    this.insetoChateia();
                }
                break;

            // No caso de uma Catastrofe Natural...
            case 4:
                int castastrofeNatural = random.nextInt(3) + 1;
                switch (castastrofeNatural) {
                    // Seca
                    case 1:
                        System.out.println("Seca");
                        this.agua /= 2;
                        System.out.println("Redução da agua para metade: " + this.agua);
                        break;
                    // Chuvas
                    case 2:
                        System.out.println("Chuvas");
                        this.agua *= 2;
                        System.out.println("Aumento da agua para o dobro: " + this.agua);
                        break;
                    // Erupção vulcânica
                    case 3:
                        System.out.println("Erupção Vulcânica");
                        System.out.println("Morrem metade das plantas e metade dos animais.");

                        // Divide o array das plantas e dos animais em metade
                        int halfPlantas = plantasIndex.size() / 2;
                        int halfAnimais = animaisIndex.size() / 2;


                        // Vamos remover metade das animais
                        for (int j = 0; j < halfAnimais; j++) {

                            // Atualização do index dos animais
                            for (int i = 0; i < this.seres.size(); i++) {
                                if (this.seres.get(i) instanceof Animal) {
                                    animaisIndex.add(i);
                                }
                            }

                            int index1 = (int) (Math.random() * animaisIndex.size());
                            int animalRandomIndex = animaisIndex.get(index1);
                            System.out.println("Morre o animal: " + this.seres.get(animalRandomIndex).getNome());
                        }

                        // Vamos remover metade das plantas
                        for (int j = 0; j < halfPlantas; j++) {
                            plantasIndex.clear();

                            // Atualização do index das plantas
                            for (int i = 0; i < this.seres.size(); i++) {
                                if (this.seres.get(i) instanceof Planta) {
                                    plantasIndex.add(i);
                                }
                            }

                            int index2 = (int) (Math.random() * plantasIndex.size());
                            int plantaRandomIndex = plantasIndex.get(index2);
                            System.out.println("Morre a planta: " + this.seres.get(plantaRandomIndex).getNome());
                        }

                        break;
                }
                break;
        }
    }


    /**
     * Método relativo à simulação dos três momentos (manhã, tarde e noite) no qual vão acontecer as ações
     *
     * @param numDias
     */
    public void simulador(int numDias) {

        // Número de dias
        for (int i = 0; i < numDias; i++) {
            System.out.println("Um novo dia começa no" + this.nome);
            System.out.println("Dia " + (i + 1));


            System.out.println("Seres Vivos que começam o dia:");
            if (this.seres.size() == 0) {
                System.out.println("Todos os seres vivos morreram");
            } else {
                this.adicionarEListarSerVivo();
            }

            // Os animais estão com fome quando começa o dia
            for (SerVivo ser : this.seres) {
                if (ser instanceof Animal) {
                    Animal animal = (Animal) ser;
                    animal.setFome(true);
                }
            }

            // 1 dia tem 3 periodos
            String[] periodosDia = {"Manhã", "Tarde", "Noite"};

            // Em cada momento do dia vai acontecer um acontecimento random
            for (String momento : periodosDia) {
                System.out.println(momento);
                acontecimento();
            }

            // Avaliar se algum animal se manteve com fome
            boolean animalAlive = false;

            // Verifica se ainda existem animais vivos no final do dia
            for (SerVivo ser : this.seres) {
                if (ser instanceof Animal) {
                    animalAlive = true;
                    break;
                }
            }

            // Se existir pelo menos um animal vivo vai tentar comer
            if (animalAlive) {
                System.out.println("Fim do dia...animais vivos vão comer");

                for (int j = 0; j < this.seres.size(); j++) {
                    if (this.seres.get(j) instanceof Animal) {
                        Animal animal = (Animal) this.seres.get(j);
                        if (animal.isFome()) {
                            System.out.println("Animal vai tentar comer: " + animal.getNome());
                            animalCome(j);
                            System.out.println();
                        }
                    }
                }
                System.out.println("Os animais se não tiverem sucesso com a sua refeição, morrem à fome antes do final do dia.");

                for (int j = this.seres.size() - 1; j >= 0; j--) {
                    if (this.seres.get(j) instanceof Animal) {
                        Animal animal = (Animal) this.seres.get(j);
                        if (animal.isFome()) {
                            System.out.println("Morreu o " + animal.getNome());
                        }
                    }
                }
            }

            // Dados do meio ambiente e lista de seres vivos que terminam o dia
            this.adicionarEListarSerVivo();
            System.out.println("Seres Vivos que chegam ao fim do dia:");
            if (this.seres.size() == 0) {
                System.out.println("Todos os seres vivos morreram");
            } else {
                this.adicionarEListarSerVivo();
            }
        }
    }
}

