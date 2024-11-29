import java.util.*;

public class Algoritmos {
 //Trabalho 1)-Faça a implementação do algoritmo de Busca Binária
 //e Busca Sequencial. Além disso você deve analisar
 //suas complexidades de tempo e espaço usando a notação Big O.

    // -Busca Sequencial-
    public static int encontrarElementoLinear(int[] vetor, int elemento) {
        for (int indice = 0; indice < vetor.length; indice++) {
            if (vetor[indice] == elemento) {
                return indice;
            }
        }
        return -1; // Não encontrado
    }

    // -Busca Binária-
    public static int localizarElementoBinario(int[] vetorOrdenado, int elemento) {
        int inicio = 0, fim = vetorOrdenado.length - 1;

        while (inicio <= fim) {
            int meio = inicio + (fim - inicio) / 2;

            if (vetorOrdenado[meio] == elemento) {
                return meio;
            }
            if (vetorOrdenado[meio] < elemento) {
                inicio = meio + 1;
            } else {
                fim = meio - 1;
            }
        }
        return -1; // Não encontrado
    }

 //Trabalho 2)-Faça a implementação de duas soluções para o problema
 //da Mochila (Knapsack Problem), cada solução deve utilizar algoritmos
 //diferentes. A primeira deve ser feita utilizando Programação Dinâmica
 //e a segunda utilizando Algoritmo gulosos.
 //Imagine que você está planejando uma expedição e precisa levar itens
 //essenciais em uma mochila. Cada item tem um peso e um valor 
 //(importância para a expedição). No entanto, a mochila tem uma capacidade
 //máxima de peso, e você deve escolher quais itens levar para maximizar o 
 //valor total sem ultrapassar essa capacidade.

    // Mochila: Programação Dinâmica
    public static int resolverMochilaDP(int[] pesos, int[] valores, int capacidadeMaxima) {
        int itens = pesos.length;
        int[][] tabela = new int[itens + 1][capacidadeMaxima + 1];

        for (int item = 1; item <= itens; item++) {
            for (int capacidadeAtual = 1; capacidadeAtual <= capacidadeMaxima; capacidadeAtual++) {
                if (pesos[item - 1] <= capacidadeAtual) {
                    tabela[item][capacidadeAtual] = Math.max(
                            tabela[item - 1][capacidadeAtual],
                            valores[item - 1] + tabela[item - 1][capacidadeAtual - pesos[item - 1]]
                    );
                } else {
                    tabela[item][capacidadeAtual] = tabela[item - 1][capacidadeAtual];
                }
            }
        }
        return tabela[itens][capacidadeMaxima];
    }

    // Mochila: Algoritmo Guloso
    public static int resolverMochilaGulosa(int[] pesos, int[] valores, int capacidadeMaxima) {
        List<Item> itens = new ArrayList<>();

        for (int i = 0; i < pesos.length; i++) {
            itens.add(new Item(pesos[i], valores[i]));
        }

        itens.sort((a, b) -> Double.compare(b.getRatio(), a.getRatio()));

        int capacidadeAtual = 0, valorTotal = 0;

        for (Item item : itens) {
            if (capacidadeAtual + item.peso <= capacidadeMaxima) {
                capacidadeAtual += item.peso;
                valorTotal += item.valor;
            }
        }
        return valorTotal;
    }

    // Classe Interna para Itens da Mochila
    static class Item {
        int peso, valor;

        Item(int peso, int valor) {
            this.peso = peso;
            this.valor = valor;
        }

        double getRatio() {
            return (double) valor / peso;
        }
    }
    
 //Trabalho 3)-Imagine que você está desenvolvendo uma aplicação de navegação
 //entre cidades conectadas por estradas. Cada cidade é representada por um nó
 //e cada estrada é uma aresta no grafo. Seu desafio é implementar duas 
 //abordagens para percorrer esse grafo: Busca em Largura (BFS) e Busca em
 //Profundidade (DFS).Faça a comparação dessas duas técnicas, identificando
 //quais cidades podem ser alcançadas a partir de uma cidade inicial
 //e a ordem em que essas cidades são visitadas.

    // Busca em Largura(BFS)
    public static List<String> buscaEmLargura(Map<String, List<String>> conexoes, String inicio) {
        List<String> explorados = new ArrayList<>();
        Queue<String> fila = new LinkedList<>();
        fila.offer(inicio);

        while (!fila.isEmpty()) {
            String atual = fila.poll();

            if (!explorados.contains(atual)) {
                explorados.add(atual);
                List<String> adjacentes = conexoes.getOrDefault(atual, Collections.emptyList());
                fila.addAll(adjacentes);
            }
        }
        return explorados;
    }

    // Busca em Profundidade(DFS)
    public static List<String> buscaEmProfundidade(Map<String, List<String>> conexoes, String inicio) {
        Set<String> visitados = new HashSet<>();
        List<String> explorados = new ArrayList<>();
        pilhaDFS(inicio, conexoes, visitados, explorados);
        return explorados;
    }

    private static void pilhaDFS(String atual, Map<String, List<String>> conexoes, Set<String> visitados, List<String> explorados) {
        visitados.add(atual);
        explorados.add(atual);

        for (String vizinho : conexoes.getOrDefault(atual, Collections.emptyList())) {
            if (!visitados.contains(vizinho)) {
                pilhaDFS(vizinho, conexoes, visitados, explorados);
            }
        }
    }
}
