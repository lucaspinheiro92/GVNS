import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Solucao {
//	private List<Aresta> arestas;
	private double custo;
	private List<Integer> vertices;
	private boolean custoAtualizado;
	
	public Solucao(List<Aresta> arestas, double custo, List<Integer> vertices) {
		super();
//		this.arestas = arestas;
		this.custo = custo;
		this.vertices = vertices;
		
		if(this.vertices == null)
			this.vertices = new ArrayList<Integer>();
		
//		if(this.arestas == null)
//			this.arestas = new ArrayList<Aresta>();
		
		custoAtualizado = true;
	}
	
	public Solucao() {
		super();
//		arestas = new ArrayList<Aresta>();
		vertices = new ArrayList<Integer>();
		custoAtualizado = false;
	}

//	public List<Aresta> arestas() {
//		return arestas;
//	}
//
//	public void setArestas(List<Aresta> arestas) {
//		this.arestas = arestas;
//	}

	public double custo() {
		if(!custoAtualizado){
			calculaCusto();
		}
		return custo;
	}
	
	private void calculaCusto(){
		int i;
		Vertice v = null;
		double custo = 0;
		Integer proximo; //pra nao ter problemas com o int primitivo a lista
		HashMap <Integer, Vertice> grafo = Grafo.getInstancia().getGrafo();
		
		for(i=0; i<vertices.size(); i++){
			v = grafo.get(vertices.get(i));
			proximo = vertices.get( (i+1)%vertices.size());
			custo += v.getAresta(proximo).peso();
		}
		custoAtualizado = true;
		this.custo = custo;
	}

//	public double calculaCusto() {
//		
//		if(custoAtualizado)
//			return this.custo;
//		
//		double custo = 0;
//		for(Aresta a : arestas){
//			custo += a.peso();
//		}
//		this.custo = custo;
//		return custo;
//	}

	public void setCusto(double custo) {
		this.custo = custo;
		custoAtualizado = true;
	}
	
//	public void construirVertices(){
//		List<Integer> vertices2 = new ArrayList<>();
//		vertices2.add(arestas.get(0).origem());
//		for(Aresta a : arestas){
//			vertices2.add(a.destino());
//		}
//		vertices2.remove(vertices2.size()-1);
//		this.vertices = vertices2;
//	}
	
//	public void construirArestas(){
//		List<Aresta> arestasAux = new ArrayList<>();
//		int i;
//		Aresta a = null;
//		Vertice v = null;
//		double custo = 0;
//		Integer proximo; //pra nao ter problemas com o int primitivo a lista
//		HashMap <Integer, Vertice> grafo = Grafo.getInstancia().getGrafo();
//		for(i=0; i<vertices.size(); i++){
//
//			v = grafo.get(vertices.get(i));
//			proximo = vertices.get( (i+1)%vertices.size());
//			a = v.getAresta(proximo);
//			custo += a.peso();
//			arestasAux.add(a);
//		}
//		this.arestas = arestasAux;
//		this.custo = custo;
//	}
	
	public List<Integer> vertices(){
		return vertices;
	}
	
	public Solucao clone(){
		List<Aresta> arestas2 = new ArrayList<>();
		double custo2;
		List<Integer> vertices2 = new ArrayList<>();
		
//		for(Aresta a : arestas){
//			arestas2.add(a);
//		}
		
		for(Integer v : vertices){
			vertices2.add(v);
		}
		custo2 = custo;
		
		Solucao s2 = new Solucao(arestas2, custo2, vertices2);
		return s2;
	}
	
	public void atualizarCusto(){
		this.custoAtualizado = false;
	}
	
}
