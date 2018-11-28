
import java.util.*;


public class OrdenacaoTopologica {

	//Método que retorna uma lista de inteiros (disciplinasJaOrdenadas) representando os vértices ordenados. Recebe uma matriz de adjacência
    public static List<Integer> ordena(Grafo grafo){
    	
        LinkedList<Integer> listaIndegreeZero = new LinkedList<>();
        
        //Instancia um HashMap com chave sendo o vértice e elemento o seu grau
        Map<Integer, Integer> verticeGrauMap = new HashMap<>();

        //Método que para cada vértice retorna para a listaOrdem um vértice sem vértice incidente
        for (int vertice = 0; vertice < grafo.getNumVertices(); vertice++) {
            int indegree = grafo.getIndegree(vertice);
            verticeGrauMap.put(vertice, indegree);
            if (indegree == 0) {
                listaIndegreeZero.add(vertice);
            }
        }

        /*Método para retirar da listaOrdem disciplinas que repetem e joga, por ordem de entrada na listaOrdem
         * em uma ArrayList que vai depois ser usado para contar as disciplinas do curso.
         * Quando repete significa que tem mais de uma solução */
        
        List<Integer> disciplinasJaOrdenadas = new ArrayList<>();
        while (!listaIndegreeZero.isEmpty()){
            int vertice = listaIndegreeZero.pollLast();
            disciplinasJaOrdenadas.add(vertice);
            
            //Método q vai recalcular o grau do adjacente no HashMap após a retirada do vertice incidente da listaOrdem
            List<Integer> listaDeAdjacentes = grafo.getVerticesAdjacentes(vertice);
            for (int adjacente : listaDeAdjacentes) {
                int updatedIndegree = verticeGrauMap.get(adjacente) - 1;
                verticeGrauMap.remove(adjacente);
                verticeGrauMap.put(adjacente, updatedIndegree);

                if (updatedIndegree == 0) {
                    listaIndegreeZero.add(adjacente);
                }
            }
        }

        if (disciplinasJaOrdenadas.size() != grafo.getNumVertices()) {
            throw new RuntimeException("Este grafo tem um ciclo!");
        }

       
        return  disciplinasJaOrdenadas;
    }


    public static void main(String[] args) {
    	
        Grafo sistemasInformaçao = new MatrizAdjacencia(48, Grafo.TipoGrafo.DIRECTED);
        sistemasInformaçao.adicionaAdjacencia(0, 5, 8);
        sistemasInformaçao.adicionaAdjacencia(3, 5, 8);
        sistemasInformaçao.adicionaAdjacencia(3, 9, 8);
        sistemasInformaçao.adicionaAdjacencia(5, 12, 7);
        sistemasInformaçao.adicionaAdjacencia(5, 15, 7);
        sistemasInformaçao.adicionaAdjacencia(14, 16, 6);
        sistemasInformaçao.adicionaAdjacencia(0, 18, 6);
        sistemasInformaçao.adicionaAdjacencia(5, 19, 6);
        sistemasInformaçao.adicionaAdjacencia(16, 21, 6);
        sistemasInformaçao.adicionaAdjacencia(22, 23, 5);
        sistemasInformaçao.adicionaAdjacencia(19, 24, 5);
        sistemasInformaçao.adicionaAdjacencia(26, 27, 4);
        sistemasInformaçao.adicionaAdjacencia(20, 29, 4);
        sistemasInformaçao.adicionaAdjacencia(24, 30, 4);
        sistemasInformaçao.adicionaAdjacencia(30, 32, 3);
        sistemasInformaçao.adicionaAdjacencia(5, 36, 3);
        sistemasInformaçao.adicionaAdjacencia(31, 37, 3);
        sistemasInformaçao.adicionaAdjacencia(36, 39, 2);
        sistemasInformaçao.adicionaAdjacencia(32, 40, 2);
        sistemasInformaçao.adicionaAdjacencia(15, 42, 2);
        sistemasInformaçao.adicionaAdjacencia(27, 42, 2);
        sistemasInformaçao.adicionaAdjacencia(42, 47, 1);
        
        printList(ordena(sistemasInformaçao));
    }

    public static void printList(List<Integer> lista) {
        for (int v : lista) {
            System.out.println(v);
        }
    }
}