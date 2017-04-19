import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class GRASP {
	private static HashMap<Integer, Vertice> mapaVertices;
	
	private static double calculaLimite(List<Aresta> lrcAux, double alpha){
		if(lrcAux.isEmpty())
			return 0.0;
		
		int tamanho = lrcAux.size();
		double min = lrcAux.get(0).peso();
		double max = lrcAux.get(tamanho-1).peso();
		double limite = min + alpha*(max-min);
		return limite;
	}
	
	private static List<Aresta> criaLRC(Integer vertice, ArrayList<Integer> verticesUtilizados, double alpha){
		double limite;
		List<Aresta> listaArestas = mapaVertices.get(vertice).arestas();
		List<Aresta> lrc = new ArrayList<>();
		List<Aresta> lrcAux = new ArrayList<>();

		for(Aresta a : listaArestas){
			if(!verticesUtilizados.contains(a.destino()))
				lrcAux.add(a);
		}
		
		limite = calculaLimite(lrcAux, alpha);
		
		for(Aresta a : lrcAux){
			if(a.peso() <= limite){
					lrc.add(a);
			}else{
				break;
			}
		}
		return lrc;
	}
	
	
	
	private static Solucao semiGuloso(Integer vInicial, double alpha){
		Solucao s = new Solucao();
		List<Aresta> lrc = new ArrayList<>();
		Double custo = 0.0;
		Integer ultimo = null;
		ArrayList<Integer> verticesUtilizados = new ArrayList<>();
		Integer vAtual = vInicial;
		
		s.vertices().add(vAtual);
		
		lrc = criaLRC(vInicial, verticesUtilizados, alpha);
		
		while(!lrc.isEmpty() && vAtual != null){
			ultimo = vAtual;

			Aresta a = Utils.selecionaAresta(lrc);
			if(a != null){
				custo += a.peso();
				s.vertices().add(a.destino());
				verticesUtilizados.add(vAtual);

				vAtual = a.destino();
				ultimo = vAtual;
				
				lrc = criaLRC(vAtual, verticesUtilizados, alpha);
			}else{
				vAtual = null;
			}

		}
		verticesUtilizados.remove(vInicial);
		lrc = criaLRC(ultimo, verticesUtilizados, alpha);
		Aresta ultimaAresta = Utils.selecionaAresta(lrc); 
		
		custo += ultimaAresta.peso();
		s.setCusto(custo);
		return s;
	}
	
	private static void criaArestas(String nomeArquivo) throws IOException{
		ArrayList<Distancia>listaDistancias = criaListaDistancias(nomeArquivo);
		double pesoMax = 0;
		Integer vOrigem;
		Double xOrigem;
		Double yOrigem;
		
		Integer vDestino;
		Double xDestino;
		Double yDestino;
		
		Double dx;
		Double dy;
		Integer peso;
		
		for(int i = 0; i<listaDistancias.size()-1; i++){
			vOrigem = listaDistancias.get(i).vertice;
			xOrigem = listaDistancias.get(i).x;
			yOrigem = listaDistancias.get(i).y;
			for(int j = i+1; j<listaDistancias.size(); j++){
				vDestino = listaDistancias.get(j).vertice;
				xDestino = listaDistancias.get(j).x();
				yDestino = listaDistancias.get(j).y();
				
				dx = xOrigem - xDestino;
				dy = yOrigem - yDestino;
				peso = (int) Math.sqrt(dx*dx + dy*dy);
				pesoMax += peso;
				mapaVertices.get(vOrigem).arestas().add(new Aresta(vOrigem,vDestino, peso));
				mapaVertices.get(vDestino).arestas().add(new Aresta(vDestino,vOrigem, peso));
				
			}
			
			Collections.sort(mapaVertices.get(vOrigem).arestas());
		}
		Collections.sort(mapaVertices.get(listaDistancias.size()).arestas());
		Grafo.getInstancia().setMapaVertices(mapaVertices);
	}
	
	private static ArrayList<Distancia> criaListaDistancias(String nomeArquivo) throws IOException{
		ArrayList<Distancia>listaDistancias = new ArrayList<>();
		String linha;
		Double y;
		Double x;
		Integer node;
		
		InputStream is = new FileInputStream("instancias/"+nomeArquivo);
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		
		
		linha = br.readLine();
		
		while(!linha.equals("EOF")){
			String vLinha[] = linha.split(" ");		
			node = Integer.parseInt(vLinha[0]);
			x = Double.parseDouble(vLinha[1]);
			y = Double.parseDouble(vLinha[2]);

			Distancia d = new Distancia(node, x, y);
			Vertice v = new Vertice(node, null);
			
			mapaVertices.put(v.nome(), v);
			listaDistancias.add(d);

			linha = br.readLine();
		}
		br.close();
		return listaDistancias;
	}
	
	public static Solucao grasp(String nomeArquivo, int maxIteracoesGRASP, int maxIteracoesVNS, double alpha, OutputStream out, OutputStreamWriter isw, BufferedWriter bw) throws IOException{
//		long tempoInicial;
//		long tempoFinal;
//		long tempoTotal;
		
//		tempoInicial = System.currentTimeMillis();
		
		mapaVertices = new HashMap<>();
		Solucao s;
		Solucao sBest;
		criaArestas(nomeArquivo);
		int t = 0;

		sBest = new Solucao(null, Constantes.pesoMaximo,null);
		while(t < maxIteracoesGRASP){
			System.out.println("GRASP t "+t);
			s = semiGuloso(Utils.cidadeAleatoria(), alpha);
			s = VNS.vns(s.clone(), maxIteracoesVNS);
			//se a solucao encontrada for viavel e o custo for menor que a melhor, atualize a melhor solucao
			if(s.custo() < sBest.custo() && Utils.isSolucaoViavel(s, mapaVertices)){
				sBest = s;
			}
			t++;
		}
		
//		tempoFinal = System.currentTimeMillis();
//		tempoTotal = tempoFinal - tempoInicial;
		
//		Utils.imprimeResultadoArquivo(sBest, tempoTotal, out, isw, bw);
		return sBest;
	}
	
}
