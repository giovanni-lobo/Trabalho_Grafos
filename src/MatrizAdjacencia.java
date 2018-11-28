import java.util.ArrayList;
import java.util.List;

public class MatrizAdjacencia implements Grafo {
	
	private int[][] matrizAdj;
    private  TipoGrafo tipoGrafo = TipoGrafo.DIRECTED;
    private int numVertices = 0;

    // Método construtor de MatrizAdjacencia que inicializa todos os relacionamentos entre vértices com o valor 0
   
    public MatrizAdjacencia(int numVertices, TipoGrafo tipoGrafo) {
        this.tipoGrafo = tipoGrafo;
        this.numVertices = numVertices;
        matrizAdj = new int[numVertices][numVertices];

        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
            	matrizAdj[i][j] = 0;
            }
        }
    }

    //Sovrescreve o médodo de adicionar adjacências em um grafo
    
    @Override
    public void adicionaAdjacencia(int v1, int v2) {
        if (v1 >= numVertices || v2 >= numVertices || v1 < 0 || v2 < 0) {
            throw new  IllegalArgumentException("Este vértice não é válido!");
        }
        matrizAdj[v1][v2] = 1;
        if (tipoGrafo == TipoGrafo.UNDIRECTED) {
        	matrizAdj[v2][v1] = 1;
        }
    }
    
    //Sovrescreve o médodo de adicionar adjacências em um grafo considerando o peso da fase

    @Override
    public void adicionaAdjacencia(int v1, int v2, int peso) {
        if (v1 >= numVertices || v2 >= numVertices || v1 < 0 || v2 < 0) {
            throw new  IllegalArgumentException("Este vértice não é válido!");
        }
        matrizAdj[v1][v2] = peso;
        if(tipoGrafo == TipoGrafo.UNDIRECTED) {
        	matrizAdj[v2][v1] = peso;
        }
    }

    @Override
    public int getPesoPreRequisito(int v1, int v2) {
        return matrizAdj[v1][v2];
    }

    //Sobrescreve o método de obter os vértices adjacentes do vértice que é passado
    
    @Override
    public List<Integer> getVerticesAdjacentes(int v) {
        if (v < 0 ||  v >= numVertices) {
            throw new  IllegalArgumentException("Este vértice não é válido!");
        }
        List<Integer> listaDeVerticesAdjacentes = new ArrayList<>();
        for (int i = 0; i < numVertices; i++) {
            if (matrizAdj[v][i] != 0) {
                listaDeVerticesAdjacentes.add(i);
            }
        }
        return listaDeVerticesAdjacentes;
    }

    /* Sobresecreve o método da interface Grafo de obter o grau de entrada de determinado vértice. 
    Na matriz de adjacência, percorre a coluna do vértice que é passado e vê de quem ele é adjacente, contando-os*/
    
    @Override
    public int getIndegree(int v){
        if (v < 0 ||  v >= numVertices) {
            throw new  IllegalArgumentException("Vertice não válido!");
        }
        int indegree = 0;
        for (int i = 0; i < getNumVertices(); i++) {
            if (matrizAdj[i][v] != 0) {
                indegree++;
            }
        }
        return indegree;
    }

    @Override
    public int getNumVertices() {
        return numVertices;
    }
}
	
