public class EncontraPrimos{
	public static int[] primos(int n){
		int[] lista = new int[n];
		lista[0] = 2;
		int aux = 3;
		
		
		for(int k = 1; k < n ; k++){
			for(int i = 0 ;; i++){
				for(int j = aux ;; j++){
					if(j % lista[i] != 0){
						aux = j;
						break;
					}else{
						i = 0;
					}
				}
				if(lista[i+1] == 0){
					lista[i+1] = aux;
					break;
				}
				
			}
		}
		
		return lista;
		
	}
	
	public static void imprime (int[] x){
		for(int i=0; i < x.length; i++){
			System.out.println(x[i]);
		}
	}
	
	
	
	public static void main (String[] args){
		Integer a = new Integer(1111);
		
		byte c = a.byteValue();
		System.out.println(c);
		
		System.out.println();
		System.out.println();
		long t1=System.currentTimeMillis();
		imprime(primos(1000));
		long t2= System.currentTimeMillis();
		System.out.println(t2 - t1);
		System.out.println(primos(100)[0]);
		System.out.println(primos(1)[(int)Math.round(1 * Math.random())]);
		System.out.println();
		System.out.println();
		
	}
}