import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Utils {

	public static boolean isSolucaoViavel(Solucao s, HashMap<Integer, Vertice> mapaVertices){
		int nVertices = mapaVertices.size();
		ArrayList<Integer> verticesNaSolucao = new ArrayList<>();
		
		//verifica se a quantidade de vertices na solucao eh igual
		//a quantidade de cidades da instancia (vertices no grafo)
		if(s.vertices().size() != nVertices)
			return false;

		for(Integer v : s.vertices()){
			//verifica se o vertice esta presente no grafo
			if(!mapaVertices.containsKey(v))
				return false;
			//verifica se ha algum vertice repetido na solucao
			if(verticesNaSolucao.contains(v))
				return false;
			verticesNaSolucao.add(v);
		}
		return true;
	}
	
	public static Aresta selecionaAresta(List<Aresta> lrc){
		Random random = new Random();
		return lrc.get( random.nextInt(lrc.size()) );
	}
	
	public static void imprimeVerticesSolucao(Solucao s){
		for(Integer v : s.vertices()){
			System.out.print(v+"-");
		}
		System.out.println(s.vertices().get(0));
	}
	
	public static void imprimeSolucao(Solucao s){
		System.out.println("Custo: "+s.custo());

		for(Integer v : s.vertices()){
			System.out.print(v+"-");
		}
		System.out.println(s.vertices().get(0));
	}
	
//	public static void imprimeVerticesSolucaoArquivo(Solucao s) throws IOException{
//		OutputStream out = new FileOutputStream("instancias/saidaVertices.txt");
//		OutputStreamWriter isw = new OutputStreamWriter(out);
//		BufferedWriter bw = new BufferedWriter(isw);
//		for(Integer v : s.vertices()){
//			bw.write(v+"-");
//		}
//		bw.write(""+s.vertices().get(0));
//		bw.close();
//	}
	
	public static void imprimeParametrosArquivo(String nomeArquivo, int maxIteracoesGRASP,int maxIteracoesVNS, double alpha,OutputStream out, OutputStreamWriter isw,BufferedWriter bw) throws IOException{		
		bw.write("-----------------------------------------------------\n");
		bw.write("Arquivo: "+nomeArquivo+" /");
		bw.write(" Max iteracoes GRASP: "+maxIteracoesGRASP+" /");
		bw.write(" Max iteracoes VNS: "+maxIteracoesVNS+" /");
		bw.write(" Alpha: "+alpha+"\n");
		bw.write("-----------------------------------------------------\n");
	}
	
//	public static void imprimeMelhorResultado(Solucao s,String nomeArquivo, int maxIteracoesGRASP,int maxIteracoesVNS, double alpha,OutputStream out, OutputStreamWriter isw,BufferedWriter bw) throws IOException{		
//		bw.write("Melhor solucao:\n");
//		bw.write("-----------------------------------------------------\n");
//		bw.write("Arquivo: "+nomeArquivo+" /");
//		bw.write(" Max iteracoes GRASP: "+maxIteracoesGRASP+" /");
//		bw.write(" Max iteracoes VNS: "+maxIteracoesVNS+" /");
//		bw.write(" Alpha: "+alpha+"\n");
//		bw.write("-----------------------------------------------------\n");
//		
//		bw.write("Custo: "+s.custo()+"\nSolucao:\n");
//		for(Integer v : s.vertices()){
//			bw.write(v+"-");
//		}
//		bw.write(""+s.vertices().get(0)+"\n\n");
//	}
	
	public static void imprimeMelhorResultado(Solucao s,String nomeArquivo, int maxIteracoesGRASP,int maxIteracoesVNS, double alpha, long melhorTempoExecucao, long tempoMedio) throws IOException{
		OutputStream out = null;
		OutputStreamWriter isw = null;
		BufferedWriter bw = null;
		
		try {
			out = new FileOutputStream("instancias/"+nomeArquivo+"Result.txt");
			isw = new OutputStreamWriter(out);
			bw = new BufferedWriter(isw);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		bw.write("Melhor solucao:\n");
		bw.write("-----------------------------------------------------\n");
		bw.write("Arquivo: "+nomeArquivo+" /");
		bw.write(" Max iteracoes GRASP: "+maxIteracoesGRASP+" /");
		bw.write(" Max iteracoes VNS: "+maxIteracoesVNS+" /");
		bw.write(" Alpha: "+alpha+"\n");
		bw.write(" Melhor Tempo: "+melhorTempoExecucao+"ms /");
		bw.write(" Tempo Medio: "+tempoMedio+"ms \n");
		bw.write("-----------------------------------------------------\n");
		
		bw.write("Custo: "+s.custo()+"\nSolucao:\n");
		for(Integer v : s.vertices()){
			bw.write(v+"-");
		}
		bw.write(""+s.vertices().get(0)+"\n\n");
		bw.close();
	}
	
	public static void imprimeResultadoArquivo(Solucao s,long tempoExecucao,OutputStream out, OutputStreamWriter isw,BufferedWriter bw) throws IOException{		
		bw.write("Custo: "+s.custo()+" / Tempo: "+tempoExecucao+"ms\nSolucao:\n");
		for(Integer v : s.vertices()){
			bw.write(v+"-");
		}
		bw.write(""+s.vertices().get(0)+"\n\n");
	}
	
	public static void imprimeResultadoArquivoTESTE(Solucao s,OutputStream out, OutputStreamWriter isw,BufferedWriter bw) throws IOException{		
		bw.write("lololololo Bab\n----------------------\n");
	}
	
	public static Integer cidadeAleatoria(){
		Random random = new Random();
		int cidade;
		
		do{
			cidade = random.nextInt( Grafo.getInstancia().getGrafo().size() );
		}while(cidade == 0);
		
		return (Integer) cidade;
	}	
}
