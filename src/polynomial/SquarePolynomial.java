
package polynomial;

import java.io.EOFException;
import java.io.FileInputStream;

public class SquarePolynomial extends Polynomial {
	public SquarePolynomial(int[] input) {
		super(input);
	}
	public SquarePolynomial(String input) {
		super(input);
	}
	public void fillFromFile(String fileName, int num){
		FileInputStream in;
		 try {
			 in = new FileInputStream(fileName);
			 in.skip(32*num);
			 if (in.available()<32)
				 throw new EOFException("While reading has crossed the border of the file");
			 for (int i = 2; i>=0; i--)
				 poly[i]=BinFile.readDouble(in, 8,8);
			 BinFile.readInt(in, 4,8);
			 in.close();
		 } 
		 catch (Exception e){
			 System.out.println(e);
		 }
	}
	public void findRoots(){
		double D = poly[1]*poly[1]-4*poly[0]*poly[2];
		if (D<0)
			System.out.println("No real roots");
		else{
			System.out.println("x1="+(-poly[1]- Math.sqrt(D))/(2*poly[2]));
			System.out.println("x2="+(-poly[1]+ Math.sqrt(D))/(2*poly[2]));
		}
	}
}