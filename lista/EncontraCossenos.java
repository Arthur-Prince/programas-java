public class EncontraCossenos{
	
	// resolve o cosseno a partir do angulo
	//quanto maior a precisão mais preciso
	public static double cos( double x ){
		double polinomio =0;
		if( x!= 0){
			for(int k = 0; k/2 <= 6; k += 2){
				//faz polinomio
				double monomio = 0;
				monomio = (Math.pow(-1 , k/2) * Math.pow(x , k))/(FazFatorial.fatorial(k));
				
				polinomio += monomio;
				
			}
		
			return polinomio;
		}else
			return 1;
	}
	public static void main(String[] args){
		System.out.println(cos(1));
	}
}