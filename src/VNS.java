import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class VNS {
	
	private static Solucao shaking(Solucao s, int k){
		int v1;
		int v2;
		int i;
		ArrayList<Integer> listaShuffle = new ArrayList<>();
		
		for(i=0; i<s.vertices().size(); i++){
			listaShuffle.add(i);
		}
		//listaShuffle serve pra sortear os indices das cidades a serem trocadas
		Collections.shuffle(listaShuffle);
		
		for(i=0; i<(k*2); i=i+2){
			v1 = listaShuffle.get(i);
			v2 = listaShuffle.get(i+1);
			
			s = troca(s, v1, v2);
		}
		s.atualizarCusto();
		
		return s;
	}
	
	private static Solucao troca(Solucao s, int a, int b){
		Integer aux;
		aux = s.vertices().get(a);
		s.vertices().set(a, s.vertices().get(b));
		s.vertices().set(b, aux);
		
		return s;
	}

	//retorna a melhor solucao
	private static Solucao troca(Solucao s, int a, int b, int c){
		
		Solucao s1;
		Solucao s2;
		
		s1 = troca(s.clone(), a, b);
		s1 = troca(s1, b, c);
		s1.atualizarCusto();
		
		s2 = troca(s.clone(), a, c);
		s2 = troca(s2, b, c);
		s2.atualizarCusto();
		
		if(s1.custo() < s2.custo()){
			return s1;
		}
		
		return s2;
	}
	
	private static Solucao buscaLocal(Solucao s, int k){
		switch (k) {
		case 1:
			return vizinhanca1(s);
		case 2:
			vizinhanca2(s);
		default:
			return s;
		}
	}
	
	private static Solucao buscaLocalRapida(Solucao s, int k, int maxIteracoes){
		switch (k) {
		case 1:
			return vizinhanca1Rapida(s, maxIteracoes);
		case 2:
			vizinhanca2Rapida(s, maxIteracoes);
		default:
			return s;
		}
	}
	
	private static Solucao vizinhanca1(Solucao s){
		//com k=2
		Solucao sBest = s.clone();
		Solucao s1;
		int i = 0;
		int j = 0;
		for(i = 0; i < s.vertices().size()-1; i++){
			for(j = i+1; j < s.vertices().size(); j++){
				s1 = troca(s.clone(), i, j);

				s1.atualizarCusto();
				if(s1.custo() < sBest.custo()){
					sBest = s1;
				}
			}
		}
		
		return sBest;
	}
	
	private static Solucao vizinhanca1Rapida(Solucao s,int maxIteracoes){
		//com k=2
		
		Solucao sBest = s.clone();
		Solucao s1;
		Random random = new Random();
		int i1;
		int i2;

		
		int i = 0;
		
		while(i <= maxIteracoes){

			i1 = random.nextInt(s.vertices().size());
			do{
				i2 = random.nextInt(s.vertices().size());
			}while(i2 == i1);
				
			s1 = troca(s.clone(), i1, i2);

			s1.atualizarCusto();
			if(s1.custo() < sBest.custo()){
				sBest = s1;
				i = 1;
			}else{
				i++;
			}
		}
		
		return sBest;
	}
	
	private static Solucao vizinhanca2(Solucao s){
		//com k=2
		Solucao sBest = new Solucao(null, Constantes.pesoMaximo, null);
		Solucao s1;
		int i = 0;
		int j = 0;
		int l = 0;
		for(i = 0; i < s.vertices().size()-2; i++){
			for(j = i+1; j < s.vertices().size()-1; j++){
				for(l = j+1; l < s.vertices().size(); l++){
					
					s1 = troca(s.clone(), i, j, l);
					if(s1.custo() < sBest.custo()){
						sBest = s1;
					}
					
				}
			}
		}
		
		return sBest;
	}
	
	private static Solucao vizinhanca2Rapida(Solucao s, int maxIteracoes){
		Solucao sBest = new Solucao(null, Constantes.pesoMaximo, null);
		Solucao s1;
		int i = 0;

		Random random = new Random();
		int i1;
		int i2;
		int i3;

		while(i < maxIteracoes){					
			i1 = random.nextInt(s.vertices().size());
			do{
				i2 = random.nextInt(s.vertices().size());
			}while(i2 == i1);
					
			do{
				i3 = random.nextInt(s.vertices().size());
			}while(i3 == i1 || i3 == i2);
					
					
			s1 = troca(s.clone(), i1, i2, i3);
			if(s1.custo() < sBest.custo()){
				sBest = s1;
				i = 1;
			}else{
				i++;
			}
		}
		return sBest;
	}
	
	private static Solucao doisOpt(Solucao s){
		Solucao sBest = s.clone();
		boolean melhorou = true;
		int i;
		int k;
		int nVertices = s.vertices().size();
		
//		System.out.println("2-Opt:");
		while(melhorou){
			melhorou = false;
			for(i = 0; i < nVertices-3; i++){
				for(k = i+3; k < nVertices; k++){
					
					if(custoCaminho(sBest, i, k, true) < custoCaminho(sBest, i, k, false)){
						sBest = flip(sBest, i, k);
						melhorou = true;
						break;
					}
				}
				if(melhorou){
//					System.out.print(" m");
					break;
				}
			}
		}
		return sBest;
	}
	
	private static double custoCaminho(Solucao s, int a, int b, boolean flip){
		Solucao s1 = new Solucao();

		
		int i;
		int j = b-1;
		
		s1.vertices().add(s.vertices().get(a));
		if(!flip){
			for(i=a+1; i<b; i++){
				s1.vertices().add(s.vertices().get(i));	
			}
		}else{
			for(i=a+1; i<b; i++){
				s1.vertices().add(s.vertices().get(j));
				j--;
			}
		}
		s1.vertices().add(s.vertices().get(b));
		
		return s1.custo();
	}
	
	private static Solucao flip(Solucao s, int a, int b){
		//a = ORIGEM da primeira aresta selecionada
		//b = DESTINO da segunda aresta selecionada
		//logo, b >= a+3
		Solucao s1 = new Solucao();
		Solucao s2 = new Solucao();
		Solucao sf = s.clone();
		
		int i;
		int j = b-1;
		
		s1.vertices().add(s.vertices().get(a));
		s2.vertices().add(s.vertices().get(a));
		
		for(i=a+1; i<b; i++){
			s1.vertices().add(s.vertices().get(i));
			s2.vertices().add(s.vertices().get(j));
			j--;
		}
		s1.vertices().add(s.vertices().get(b));
		s2.vertices().add(s.vertices().get(b));
		
		//mesmo tendo esse trabalho de novo, eh menos custoso do que
		//comparar os custos da solucao inteira
		if(s2.custo() < s1.custo()){
			j = b-1;
			for(i=a+1; i<b; i++){
				//sf eh um clone de s, entao aqui eh o flip entre a e b na solucao a ser retornada
				sf.vertices().set(i, s.vertices().get(j));
				j--;
			}			
		}
		sf.atualizarCusto();
		return sf;
	}
	
	private static Solucao doisOptJump(Solucao s, int k){
		int tamanhoBloco;
		int tamanhoSolucao;
		int i;
		int vInicial;
		int proximoBloco;
		tamanhoSolucao = s.vertices().size();
		Random random = new Random();
		vInicial = random.nextInt(tamanhoSolucao);
		
		tamanhoBloco = tamanhoSolucao/k;
		
		for(i=0; i<k; i++){
			proximoBloco = (vInicial+(i*tamanhoBloco))%tamanhoSolucao;
			s = doisOptParcial(s, proximoBloco, tamanhoBloco);
		}
		
		return s;
	}
	
	private static Solucao doisOptParcial(Solucao s, int inicioBloco, int tamanhoBloco){
		Solucao sBest = s.clone();
		boolean melhorou = true;
		int i;
		int k;
		int inicio;
		int fim;
		int nVertices;
		nVertices = s.vertices().size();
		
		while(melhorou){
			melhorou = false;
			for(i = 0; i < tamanhoBloco-3; i++){
				inicio = (inicioBloco+i)%nVertices;
				for(k = i+3; k < tamanhoBloco; k++){
					fim = (inicioBloco+k)%nVertices;
					if(custoCaminho(sBest, inicio, fim, true) < custoCaminho(sBest, inicio, fim, false)){
						sBest = flip(sBest, inicio, fim);
						melhorou = true;
						break;
					}
				}
				if(melhorou){
					break;
				}
			}
		}
		return sBest;
	}
	
	public static Solucao vns(Solucao s, int nIteracoes){
		int i = 0;
		int k;
		int kMax = 5;
		Solucao s1;
		Solucao s2;
		
		while(i <= nIteracoes){
			k = 1;
//			System.out.println("\nvns i: "+i);
			while(k <= kMax){				
//				System.out.print("vns k: "+k);
				s1 = shaking(s.clone(), k);
//				s2 = doisOpt(s1.clone());
				s2 = buscaLocalRapida(s1.clone(), 1, nIteracoes);
				
				if(s2.custo() < s.custo()){
					s = s2;
					k = 1;
				}else{
					k++;
				}
			}
			i++;
		}	
		return s;
	}
	
	public static Solucao vnsJump(Solucao s, int nIteracoes){
		int i = 0;
		int k;
		int kMax = 5;
		Solucao s1;
		Solucao s2;
		
		while(i <= nIteracoes){
			k = 1;
			System.out.println("\nvns i: "+i);
			while(k <= kMax){				
				System.out.print("vns k: "+k);
				s1 = shaking(s.clone(), k);
				s2 = doisOptJump(s1.clone(), k);
//				s2 = buscaLocalRapida(s1.clone(), 1, nIteracoes);
				
				if(s2.custo() < s.custo()){
					s = s2;
					k = 1;
				}else{
					k++;
				}
			}
			i++;
		}	
		return s;
	}
	
}
