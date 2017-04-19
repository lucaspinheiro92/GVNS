import java.util.Comparator;

public class Aresta implements Comparator<Aresta>, Comparable<Aresta>{
	private Integer origem;
	private Integer destino;
	private double peso;
	
	public Integer destino() {
		return destino;
	}
	public void setDestino(Integer destino) {
		this.destino = destino;
	}
	
	public Integer origem() {
		return origem;
	}
	public void setOrigem(Integer origem) {
		this.origem = origem;
	}
	
	public double peso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	public Aresta(Integer origem, Integer destino, double peso) {
		super();
		this.origem = origem;
		this.destino = destino;
		this.peso = peso;
	}
	@Override
	public int compare(Aresta o1, Aresta o2) {
		return (int) (o1.peso - o2.peso);
	}
	@Override
	public int compareTo(Aresta o) {
		Integer selfPeso = (int) this.peso;
		Integer oPeso = (int) o.peso;
		return selfPeso.compareTo(oPeso);
	}
	
	
	
	
	
	
}
