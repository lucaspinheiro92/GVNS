import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class TesteEscrita {
	
	public static void main(String[] args) throws IOException {
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
