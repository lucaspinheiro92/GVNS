import java.util.HashMap;

public class Grafo {
	private static Grafo instancia;
	private HashMap<Integer, Vertice> grafo;
	
	private Grafo(){		
	}
	
	public static synchronized Grafo getInstancia(){
		if(instancia == null)
			instancia = new Grafo();
		return instancia;
	}

	public HashMap<Integer, Vertice> getGrafo() {
		return grafo;
	}

	public void setMapaVertices(HashMap<Integer, Vertice> grafo) {
		this.grafo = grafo;
	}
	
}
