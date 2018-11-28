
import java.util.List;

// Interface que permite criar tanto grafos direcionados quanto não-direcionados

public interface Grafo {

	enum TipoGrafo{
		DIRECTED,
		UNDIRECTED
	}
	
	// V1 é a disciplina que deve ser feita antes de V2

    void adicionaAdjacencia(int v1, int v2);

    
    // Método caso queira dar preferência entre disciplinas, sendo as das primeiras fases mais relevantes
    
    void adicionaAdjacencia(int v1, int v2, int peso);

    
    // Método para obter o peso do relacionamento entre disciplinas
    int getPesoPreRequisito(int v1, int v2);

    // Método para gerar uma lista de relacionamentos por disciplina
    List<Integer> getVerticesAdjacentes(int v);

    int getNumVertices();

    // Método para gerar o número de pré-requisitos da disciplina
    int getIndegree(int v);
    
}    