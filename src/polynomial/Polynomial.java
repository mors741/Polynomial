package polynomial;

public class Polynomial {
	private double poly[] = new double [100];
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
	public double getNum(int pow){
		return poly[pow];
	}
	public String toString(){
		String res = "";
		for (int i = 99; i >=0; i--)
			if (poly[i]>0 && res == "")
				res = res + poly[i]+"x^"+i;
			else if (poly[i]<0)
				res = res + " "+ poly[i]+"x^"+i;
			else if (poly[i]>0)
				res = res +" +"+ poly[i]+"x^"+i;
		if (res == "")
			res = res + 0;
		return res;
	}
	public Polynomial clone() {
		Polynomial res = new Polynomial("");
		for (int i = 0; i < 100; i++)
			res.poly[i]=poly[i];
		return res;
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
	public Polynomial add(Polynomial p){
		Polynomial res = new Polynomial("");
		for (int i = 0; i < 100; i++)
			res.poly[i]=poly[i]+p.poly[i];
		return res;
	}
	public Polynomial subtract(Polynomial p){
		Polynomial res = new Polynomial("");
		for (int i = 0; i < 100; i++)
			res.poly[i]=poly[i]-p.getNum(i);
		return res;

	}
	private Polynomial powerUp (int n){
		Polynomial res = new Polynomial("");
		for (int i = getSize(); i>=0;i--)
			res.poly[i+n]= poly[i];
		for (int i = 0; i<n;i++)
			res.poly[i]=0;
		return res;
	}
	private Polynomial multByConst(double n){
		Polynomial res = new Polynomial("");
		for (int i = 0; i<100; i++)
			res.poly[i]=poly[i]*n;
		return res;
	}
	public Polynomial multipy(Polynomial multiplier){
		Polynomial res = new Polynomial("");
		int length = multiplier.getSize();
		if (length == 0) 
			return res;
		res = res.add(this.multByConst(multiplier.poly[length-1]));
		for (int i = length-2; i>=0 ; i--){
			res.powerUp(1);
			res = res.add(this.multByConst(multiplier.poly[i]));
		}	
		return res;
	}
	public Polynomial divide(Polynomial divisor){
		Polynomial res = new Polynomial("");
		Polynomial mod = clone();
		int modLen = mod.getSize();
		int divisorLen = divisor.getSize();
		for (int i = modLen - divisorLen; i >=0  ;i--){
			res.poly[i]=mod.poly[modLen-1]/divisor.poly[divisorLen-1];
			mod = mod.subtract(divisor.powerUp(modLen-divisorLen).multByConst(res.poly[i]));
			modLen--;
		}		
		return res;
	}
	public Polynomial mod(Polynomial divisor){
		Polynomial res = new Polynomial("");
		Polynomial mod = clone();
		int modLen = mod.getSize();
		int divisorLen = divisor.getSize();
		for (int i = modLen - divisorLen; i >=0  ;i--){
			res.poly[i]=mod.poly[modLen-1]/divisor.poly[divisorLen-1];
			mod = mod.subtract(divisor.powerUp(modLen-divisorLen).multByConst(res.poly[i]));
			modLen--;
		}		
		return mod;
	}
	public static void main(String[] args) {
		Polynomial p1 = new Polynomial("1x^3-12x^2-42x^0");
		Polynomial p2 = new Polynomial("1x^1-3x^0");
		System.out.println(p1+"      /      "+p2);
		System.out.println("res: "+ p1.divide(p2));
		System.out.println("mod: "+ p1.mod(p2));
	}
}
