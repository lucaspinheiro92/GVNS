import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;

public class TesteEscrita {
	
	public static void main(String[] args) throws IOException {
//		testeEscrita();
		testeHash();
	}
	
	private static void testeHash(){
		HashMap<Double, Double> alphas = new HashMap<>();
	
		
		for (int i = 0; i < 5; i++) {
			System.out.println("tigre");
			alphas.clear();
			double alpha = 0.1;
			Double custo;
			for (int j = 0; j < 3; j++) {
				if(!alphas.containsKey(alpha)){
					alphas.put(alpha, 10.0);
				}else{
					custo = alphas.get(alpha);
					alphas.put(alpha, custo+10.0);
				}
			}
			alpha++;
		}
	}
	
	private static void testeEscrita()throws IOException {
		OutputStream out = null;
		OutputStreamWriter isw = null;
		BufferedWriter bw = null;
		try {
			out = new FileOutputStream("testeEscrita.txt",true);
			isw = new OutputStreamWriter(out);
			bw = new BufferedWriter(isw);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		bw.write("Haha\n");
		bw.flush();
		
		bw.write("Bololo\n");
		bw.flush();
		
		bw.write("Hahahaha\n");
		bw.flush();
	}
	
}
