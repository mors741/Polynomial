package polynomial;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

class BinFile {
	public static int readInt(FileInputStream in, int intLen, int totalLen){
		int res =0;
		try{
			int ch[]=new int[intLen];
			for (int i = 0; i<ch.length;i++)
				ch[i] = in.read();
			for (int i = ch.length; i<totalLen;i++)
				in.read();
			for (int i=ch.length-1; i>=0;i--)
				res+=(ch[i]<<(8*i));
			return res;
		}
		catch (FileNotFoundException e){
			System.out.println("File not found");
			return 0;
		}
		catch (IOException e){
			System.out.println("Read error");
			return 0;
		}
	}
	public static double readDouble(FileInputStream in, int doubleLen, int totalLen){
		double res =0.0;
		try{
			long ch[]=new long[doubleLen];
			for (int i = 0; i<ch.length;i++)
				ch[i] = in.read();
			for (int i = ch.length; i<totalLen;i++)
				in.read();
			long resT=0;
			for (int i=ch.length-1; i>=0;i--)
				resT+=(ch[i]<<(8*i));
			res = Double.longBitsToDouble(resT);
			return res;
		}
		catch (FileNotFoundException e){
			System.out.println("File not found");
			return 0;
		}
		catch (IOException e){
			System.out.println("Read error");
			return 0;
		}
	}
}
