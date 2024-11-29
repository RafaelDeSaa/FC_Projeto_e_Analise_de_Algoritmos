import java.util.*;

public class Main {
    public static void main(String[] args) {

        //(Trabalho 1)-Busca Sequencial e Binária
        
        int[] numeros = {3, 8, 12, 17, 25, 30};
        int procurar = 17;

        int posicaoLinear = Algoritmos.encontrarElementoLinear(numeros, procurar);
        int posicaoBinaria = Algoritmos.localizarElementoBinario(numeros, procurar);

        System.out.println("Busca Linear: Índice do elemento = " + posicaoLinear);
        System.out.println("Busca Binária: Índice do elemento = " + posicaoBinaria);

        //(Trabalho 2)-Mochila

        int[] pesos = {2, 3, 4, 5};
        int[] valores = {3, 4, 8, 8};
        int capacidadeMaxima = 5;

        int valorDP = Algoritmos.resolverMochilaDP(pesos, valores, capacidadeMaxima);
        int valorGuloso = Algoritmos.resolverMochilaGulosa(pesos, valores, capacidadeMaxima);

        System.out.println("Mochila DP: Valor máximo = " + valorDP);
        System.out.println("Mochila Gulosa: Valor máximo = " + valorGuloso);

        //(Trabalho 3)-Grafos em BFS e DFS

        Map<String, List<String>> grafo = new HashMap<>();
        grafo.put("X", Arrays.asList("Y", "Z"));
        grafo.put("Y", Arrays.asList("W", "Z"));
        grafo.put("Z", List.of("W"));
        grafo.put("W", Collections.emptyList());

        List<String> resultadoBFS = Algoritmos.buscaEmLargura(grafo, "X");
        List<String> resultadoDFS = Algoritmos.buscaEmProfundidade(grafo, "X");

        System.out.println("Busca em Largura (BFS): " + resultadoBFS);
        System.out.println("Busca em Profundidade (DFS): " + resultadoDFS);
    }
}
