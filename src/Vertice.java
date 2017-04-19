import java.util.ArrayList;
import java.util.HashMap;

public class Vertice {
	private Integer nome;
	private ArrayList<Aresta> arestas;

	public Integer nome() {
		return nome;
	}

	public void setNome(Integer nome) {
		this.nome = nome;
	}

	public Vertice(Integer nome, ArrayList<Aresta> arestas) {
		super();
		this.nome = nome;
		if(arestas != null){
			this.arestas = arestas;
		}else{
			this.arestas = new ArrayList<>();
		}
	}

	public ArrayList<Aresta> arestas() {
		return arestas;
	}

	public void setArestas(ArrayList<Aresta> arestas) {
		this.arestas = arestas;
	}
	
	public boolean equals(Object b){
		if(b instanceof Vertice){
			if(((Vertice) b).nome() == this.nome)
				return true;
		}
		return false;
	}
	public Aresta getAresta(Integer destino){
		for(Aresta a : arestas){
			if(a.destino().equals(destino))
				return a;
		}
		return null;
	}
}
