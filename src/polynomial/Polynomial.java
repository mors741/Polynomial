package polynomial;

public class Polynomial {
	private int poly[] = new int [100];
	private int powTen (int n){
		int res = 1;
		for (int i = 0; i < n; i++)
			res = res * 10;
		return res;
	}
	public Polynomial(int input[]) {
		for (int i = 0; i < 100; i++)
			poly[i]=input[i];
	}
	public Polynomial(String input) {
		int j,num=0,pow =0;
		int length = input.length();
		for (int i = 0; i < length; i++)
			if (input.charAt(i) == '^'){
				if (i==1 || input.charAt(i-2) == '+')
					num = 1;
				else if (input.charAt(i-2) == '-')
					num = -1;
				else{
					j = i-2;
					while (j != -1 && input.charAt(j) != '+' && input.charAt(j) != '-'){
						num = num + ((int)input.charAt(j)-48) * powTen(i-j-2);
						j--;
					}
					if (j!=-1 && input.charAt(j) == '-')
						num=-num;
				}
				pow = (int)input.charAt(i+1)-48;
				if (i+2 != length && input.charAt(i+2)!='+' && input.charAt(i+2)!='-')
					pow = 10*pow + (int)input.charAt(i+2)-48;
				poly[pow]=num;
				num = 0;
				pow = 0;
			}		
	}
	public int getNum(int pow){
		return poly[pow];
	}
	public String toString(){
		String res = "";
		for (int i = 99; i >=0; i--)
			if (poly[i]>0 && res == "" || poly[i]<0)
				res = res + poly[i]+"x^"+i;
			else if (poly[i]>0)
				res = res +"+"+ poly[i]+"x^"+i;
		if (res == "")
			res = res + 0;
		return res;
	}
	public void copyFrom(Polynomial from) {
		for (int i = 0; i < 100; i++)
			poly[i]=from.getNum(i);
	}
	public boolean equals(Polynomial to){
		for (int i = 0; i < 100; i++)
			if (poly[i]!=to.getNum(i))
				return false;
		return true;
	}
	private int getSize(){
		for (int i = 99; i>-1; i--)
			if (poly[i] != 0)
				return i+1;
		return 0;
	}
	public int maxPow(){
		return getSize()-1;
	}
	public void changeSign (){
		int length = getSize();
		for (int i = 0; i < length; i++)
			poly[i]=-poly[i];
	}
	public void add(Polynomial p){
		int length = getSize();
		for (int i = 0; i < length; i++)
			poly[i]=poly[i]+p.getNum(i);
	}
	public void subtract(Polynomial p){
		int length = getSize();
		for (int i = 0; i < length; i++)
			poly[i]=poly[i]-p.getNum(i);
	}
	public static void main(String[] args) {
		Polynomial p = new Polynomial("-x^1+3x^2+123x^9-33x^16");
		System.out.println(p);
		System.out.println(p.maxPow());
	}
}