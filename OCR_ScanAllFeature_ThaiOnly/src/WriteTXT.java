import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class WriteTXT {

	private static BufferedReader buff;
	
	
	public static void readX() {
		try {

			buff = new BufferedReader(new FileReader(new File("TextInput.txt")));
			String codeOneLine = null;
			boolean eof = false;

			while (!eof) {
				codeOneLine = buff.readLine();
				if (codeOneLine != null) {
//					codeOneLine นำไปใช้ได้เลย
				} else
					eof = true;
			}
		} catch (ArrayIndexOutOfBoundsException oob) {
			System.out.println("  <--- Systax error here.");
		} catch (FileNotFoundException fnf) {
			System.out.println("--- File not found ---");
		} catch (Exception ex) {
			System.out.println("--- Exception --- " + ex);
		}
	}
	
//	public static void writeX() {
//		try{
//			FileWriter fstream = new FileWriter("strBuff.txt");
//			BufferedWriter out = new BufferedWriter(fstream);
//			out.write( Main.strBuff.toString() );
//			out.close();
//			
//			FileWriter fstreamOfFeatureFile = new FileWriter("featureFile.txt");
//			BufferedWriter outOfFeatureFile = new BufferedWriter(fstreamOfFeatureFile);
//			outOfFeatureFile.write( Main.featureBuffer.toString() );
//			outOfFeatureFile.close();
//		}
//		catch(Exception exx){
//			System.out.println("cannot write");
//		}
//	}
	
	public static void lineByLine(String stringNeedToWrite){
		try {
		    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("feature.txt", true)));
		    out.print(stringNeedToWrite);
		    out.close();
		} catch (IOException e) {
		    //oh noes!
		}
	}
}