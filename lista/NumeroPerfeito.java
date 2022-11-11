public class NumeroPerfeito{
	
	public static long[] numerosPerfeitos(int n){
		long[] lista = new long[n];
		long aux = 0;
		int contador = 2;
		//coloca na lista
		for(int k = 0; k < n ; k++){
			
			// varia o expoente
			for(int i = contador ;i < 42;i++){
				boolean primo = true;
				
				// testa se 2^i -1 é primo
				for(int j = 3 ; j <= (int)Math.sqrt(Math.pow(2, i) -1) ; j+=2){
					if((Math.pow(2, i) -1) % j == 0|| j == Math.pow(2,16)){
						primo = false;
						break;
					}
				}
				if (primo){
					aux = (long)((Math.pow(2 , i) - 1) * (Math.pow(2 , i-1)));
					contador = ++i;
					break;
				}
				
			}
			lista[k] = aux;
		}
		return lista;
	}
	public static void imprime (long[] x){
		for(int i=0; i < x.length; i++){
			System.out.println(x[i]);
		}
	}
	
	public static void main(String[] args){
		long t1 = System.currentTimeMillis();
		imprime(numerosPerfeitos(8));
		long t2 = System.currentTimeMillis();
		System.out.println(t2-t1);
	}
}