
package polynomial;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author evgeny
 *
 */

public class SquarePolynomial extends Polynomial {

	public SquarePolynomial(int[] input) {
		super(input);
	}
	public SquarePolynomial(String input) {
		super(input);
	}
	public SquarePolynomial(String fileName, int num){
		super(fileName);
		
	}
	public void fillFromFile(String fileName){
		FileInputStream in;
		 try {
			 in = new FileInputStream("input.bin");
			 double temp;
			 for (int i = 0; i<3; i++){
				 temp= BinFile.readDouble(in, 8,8);
				 System.out.println(temp);
			 }
			 System.out.println(BinFile.readInt(in, 4,8));
			 in.close();
		 }
		 catch(IOException e){
			 System.out.println("Error"+e);
		 }
			 
	}

	
	public void findRoots(){
		double D = poly[1]*poly[1]-4*poly[0]*poly[2];
		System.out.println("x1="+(-poly[1]- Math.sqrt(D))/(2*poly[2]));
		System.out.println("x2="+(-poly[1]+ Math.sqrt(D))/(2*poly[2]));
	}
}
