import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Teste {
	
	public static void main(String[] args) throws IOException {
//		rodar();
		testeAlpha();
	}
	
	private static void rodar() throws IOException {
		int nVezes = 1;
//		String nomeArquivo = "lin15.tsp";
		int maxIteracoesGRASP = 5;
		int melhorMaxIteracoesGRASP = -1;
		int maxIteracoesVNS = 5;
		int melhorMaxIteracoesVNS = -1;
		double alpha = 0.5;
		double melhorAlpha = -1;
		int numeroInstancia = 0;
		//tempo
		long tempoInicial;
		long tempoFinal;
		long tempoExecucao;
		long melhorTempoExecucao = -1;
		long tempoTotal = 0;
		long tempoMedio;
		
		//apagar essas variaveis depois
		long tiPrograma;
		long tfPrograma;
		long ttotalPrograma;
		tiPrograma = System.currentTimeMillis();
		
		ArrayList<String> instancias = new ArrayList<>();
//		instancias.add("lin105.tsp");
//		instancias.add("pr107.tsp");
//		instancias.add("pr124.tsp");
//		instancias.add("pr144.tsp");
//		instancias.add("pr152.tsp");
//		instancias.add("rat195.tsp");
//		instancias.add("d198.tsp");
//		instancias.add("pr226.tsp");
//		instancias.add("gil262.tsp");
//		instancias.add("pr264.tsp");
//		instancias.add("pr299.tsp");
//		instancias.add("rd400.tsp");
//		instancias.add("pr439.tsp");
//		instancias.add("pcb442.tsp");
//		instancias.add("d493.tsp");
//		instancias.add("rat575.tsp");
//		instancias.add("p654.tsp");
//		instancias.add("d657.tsp");
//		instancias.add("rat783.tsp");
		
		
		Solucao s = null;
		Solucao sBest;
		sBest = new Solucao(null, Constantes.pesoMaximo,null);

		OutputStream out = null;
		OutputStreamWriter isw = null;
		BufferedWriter bw = null;
		
		try {
			out = new FileOutputStream("instancias/resultados.txt");
			isw = new OutputStreamWriter(out);
			bw = new BufferedWriter(isw);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		while(numeroInstancia < instancias.size()){
			for (int i = 0; i < nVezes; i++) {
				try {
					Utils.imprimeParametrosArquivo(instancias.get(numeroInstancia), maxIteracoesGRASP, maxIteracoesVNS, alpha, out, isw, bw);
					tempoInicial = System.currentTimeMillis();
					s = GRASP.grasp(instancias.get(numeroInstancia), maxIteracoesGRASP, maxIteracoesVNS, alpha, out, isw, bw);
					tempoFinal = System.currentTimeMillis();
					tempoExecucao = tempoFinal - tempoInicial;
					
					if(tempoExecucao < melhorTempoExecucao || melhorTempoExecucao <0){
						melhorTempoExecucao = tempoExecucao;
					}
					
					tempoTotal += tempoExecucao;
					Utils.imprimeResultadoArquivo(s, tempoExecucao, out, isw, bw);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Terminou "+instancias.get(numeroInstancia)+" "+i);
				System.out.println("Custo "+s.custo()+" alpha "+alpha);
				if(s.custo() < sBest.custo()){
					sBest = s;
					melhorAlpha = alpha;
					melhorMaxIteracoesGRASP = maxIteracoesGRASP;
					melhorMaxIteracoesVNS = maxIteracoesVNS;
				}
				alpha = (alpha + 0.1)%1;
			
			}
			tempoMedio = tempoTotal/nVezes;
			Utils.imprimeMelhorResultado(sBest, instancias.get(numeroInstancia), melhorMaxIteracoesGRASP,melhorMaxIteracoesVNS, melhorAlpha, melhorTempoExecucao, tempoMedio);
			numeroInstancia++;
			//zerando os parametros
			alpha = 0.1;
			sBest = new Solucao(null, Constantes.pesoMaximo,null);
			melhorTempoExecucao = -1;
			tempoTotal = 0;
		}
//		Utils.imprimeMelhorResultado(sBest, instancias.get(numeroInstancia), melhorMaxIteracoesGRASP,melhorMaxIteracoesVNS, melhorAlpha);
		bw.close();
		tfPrograma = System.currentTimeMillis();
		ttotalPrograma = tfPrograma - tiPrograma;
		System.out.println("Tempo total de execucao do programa: "+ttotalPrograma);
	}
	
	private static void testeAlpha() throws IOException {
		HashMap<Double, Double> alphas = new HashMap<>();
		HashMap<Double, Integer> melhoresAlphas = new HashMap<>();
		int nVezes = 3;
		int nAlphaRepetido = 1;
//		String nomeArquivo = "lin15.tsp";
		int maxIteracoesGRASP = 5;
		int melhorMaxIteracoesGRASP = -1;
		int maxIteracoesVNS = 5;
		int melhorMaxIteracoesVNS = -1;
		double alpha = 0.1;
		double melhorAlpha = -1;
		int numeroInstancia = 0;
		//tempo
		long tempoInicial;
		long tempoFinal;
		long tempoExecucao;
		long melhorTempoExecucao = -1;
		long tempoTotal = 0;
		long tempoMedio;
		
		//apagar essas variaveis depois
		long tiPrograma;
		long tfPrograma;
		long ttotalPrograma;
		Random rand = new Random();
		tiPrograma = System.currentTimeMillis();
		
		ArrayList<String> instancias = new ArrayList<>();
//		instancias.add("lin105.tsp");
//		instancias.add("pr107.tsp");
//		instancias.add("pr124.tsp");
//		instancias.add("pr144.tsp");
//		instancias.add("pr152.tsp");
//		instancias.add("rat195.tsp");
//		instancias.add("d198.tsp");
//		instancias.add("pr226.tsp");
//		instancias.add("gil262.tsp");
//		instancias.add("pr264.tsp");
//		instancias.add("pr299.tsp");
//		instancias.add("rd400.tsp");
//		instancias.add("pr439.tsp");
//		instancias.add("pcb442.tsp");
		instancias.add("d493.tsp");
		instancias.add("rat575.tsp");
		instancias.add("p654.tsp");
		instancias.add("d657.tsp");
		instancias.add("rat783.tsp");
		
		
		Solucao s = null;
		Solucao sBest;
		sBest = new Solucao(null, Constantes.pesoMaximo,null);

		OutputStream out = null;
		OutputStreamWriter isw = null;
		BufferedWriter bw = null;
		
		try {
			out = new FileOutputStream("instancias/resultados.txt");
			isw = new OutputStreamWriter(out);
			bw = new BufferedWriter(isw);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		while(numeroInstancia < instancias.size()){
			alphas.clear();
			for (int i = 0; i < nVezes; i++) {
				try {
					Utils.imprimeParametrosArquivo(instancias.get(numeroInstancia), maxIteracoesGRASP, maxIteracoesVNS, alpha, out, isw, bw);
					tempoInicial = System.currentTimeMillis();
					s = GRASP.grasp(instancias.get(numeroInstancia), maxIteracoesGRASP, maxIteracoesVNS, alpha, out, isw, bw);
					tempoFinal = System.currentTimeMillis();
					tempoExecucao = tempoFinal - tempoInicial;
					
					if(!alphas.containsKey(alpha)){
						alphas.put(alpha, s.custo());
					}else{
						alphas.put(alpha, alphas.get(alpha) + s.custo());
					}
					
					if(tempoExecucao < melhorTempoExecucao || melhorTempoExecucao <0){
						melhorTempoExecucao = tempoExecucao;
					}
					
					tempoTotal += tempoExecucao;
//					Utils.imprimeResultadoArquivo(s, tempoExecucao, out, isw, bw);
					Utils.imprimeResultadoInstancia(s, instancias.get(numeroInstancia), melhorMaxIteracoesGRASP, melhorMaxIteracoesVNS, alpha, tempoExecucao);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Terminou "+instancias.get(numeroInstancia)+" "+i);
//				System.out.println("Custo "+s.custo()+" alpha "+alpha);
				if(s.custo() < sBest.custo()){
					sBest = s;
					melhorAlpha = alpha;
					melhorMaxIteracoesGRASP = maxIteracoesGRASP;
					melhorMaxIteracoesVNS = maxIteracoesVNS;
				}
				
				if( (i+1) % nAlphaRepetido == 0){
					alpha = (alpha + 0.2)%1.1;
				}
			
			}
			//verificar melhor alpha
			Double menorCustoAux = null;
			Double melhorAlphaAux = null;;
			for( Double chave : alphas.keySet()){
				Double custoAlpha = alphas.get(chave);
				if(menorCustoAux != null){
					if(custoAlpha < menorCustoAux){
						menorCustoAux = custoAlpha;
						melhorAlphaAux = chave;
					}
				}else{
					menorCustoAux = custoAlpha;
					melhorAlphaAux = chave;
				}
			}
			
			//incrementando melhor alpha na lista de melhores alphas
			if(!melhoresAlphas.containsKey(melhorAlphaAux)){
				melhoresAlphas.put(melhorAlphaAux, 1);
			}else{
				melhoresAlphas.put(melhorAlphaAux, melhoresAlphas.get(melhorAlphaAux) + 1);
			}
			
			
			tempoMedio = tempoTotal/nVezes;
			Utils.imprimeMelhorResultado(sBest, instancias.get(numeroInstancia), melhorMaxIteracoesGRASP,melhorMaxIteracoesVNS, melhorAlpha, melhorTempoExecucao, tempoMedio);
			numeroInstancia++;
			//zerando os parametros
			alpha = 0.1;
			sBest = new Solucao(null, Constantes.pesoMaximo,null);
			melhorTempoExecucao = -1;
			tempoTotal = 0;
		}
		
		//rodar lista de alphas
		//vou fazer mais nao. Vou mandar pro arquivo
		/*Double melhorAlphaMedio = null;
		Integer qtdMelhorAlpha = null;
		for(Double chave : melhoresAlphas.keySet()){
			if( (melhorAlphaMedio == null) || (melhoresAlphas.get(chave) > qtdMelhorAlpha) ){
				qtdMelhorAlpha = melhoresAlphas.get(chave);
				melhorAlphaMedio = chave;
			}
		}*/
		Utils.imprimeTesteAlphas("alphas.txt", melhoresAlphas, nVezes, instancias.size());
//		Utils.imprimeMelhorResultado(sBest, instancias.get(numeroInstancia), melhorMaxIteracoesGRASP,melhorMaxIteracoesVNS, melhorAlpha);
		bw.close();
		tfPrograma = System.currentTimeMillis();
		ttotalPrograma = tfPrograma - tiPrograma;
		System.out.println("Tempo total de execucao do programa: "+ttotalPrograma);
	}
	
}
