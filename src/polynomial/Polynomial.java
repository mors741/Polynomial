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
		int j,num =0;
		for (int i = 0; i < input.length(); i++)
			if (input.charAt(i) == '^'){
				j = i-2;
				while (j != -1 && input.charAt(j) != '+' && input.charAt(j) != '-'){
					num = num + ((int)input.charAt(j)-48) * powTen(i-j-2);
					j--;
				}
				if (j!=-1 && input.charAt(j) == '-')
					num=-num;
				poly[(int)input.charAt(i+1)-48]=num;
				num = 0;
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
	public static void main(String[] args) {
		Polynomial p = new Polynomial("-1x^1+3x^2+123x^99-33x^6");
		Polynomial copy = new Polynomial("");
		System.out.println(p);
		System.out.println(copy.equals(p));
	}
}
