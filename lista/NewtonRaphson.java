/*********************************************************************/
/**   <Arthur Prince de Almeida>                   <10782990>       **/
/**                                                                 **/
/**   <02/05/2018>                                                  **/
/*********************************************************************/
class NewtonRaphson {

	static double[] depositos;
	static int[] datas;
	
	
	static double newton(double epsilon) {
		if(epsilon <= 0 || epsilon >= 1 )
			return (-1);
		else{
			double juros = 0.5;
			double jurosLinha = juros;
			
			do {
				double polinomiof = 0;
				double polinomiodf = 0;
				double monomiof = 0;
				double monomiodf = 0;
				
				juros = jurosLinha;
				
				for(int i=0 ; i < (depositos.length-1) ; i++){
					
					//função f(j)
					monomiof = depositos[i] * Math.pow( (1 + juros) , datas[depositos.length - 1] - datas[i] );
					
					polinomiof += monomiof;
					
					//derivada de f(j)
					monomiodf = ( datas[depositos.length - 1] - datas[i] ) * depositos[i] * Math.pow( (1 + juros) , datas[depositos.length - 1] - datas[i] - 1 );
					
					polinomiodf += monomiodf;
					
				}
				polinomiof -= depositos[depositos.length-1];
				
				jurosLinha = juros - polinomiof / polinomiodf;
				
			
			} while(epsilon < Math.abs(jurosLinha - juros));
			
			return (juros);
			
		}
	}
	
	public static void main(String[] args) {
		
		
		depositos = new double[5]; // numero de depositos + 1
		
		depositos[0] = 2000.0; // deposito 1
		depositos[1] = 123.5; // deposito 2
		depositos[2] = 358.5; //deposito 3
		depositos[3] = 23.0; // deposito 4
		depositos[4] = 3500.68; //saldo
		
		datas = new int[5]; // numero de depositos + 1
		
		datas[0] = 1; // data do deposito 1
		datas[1] = 3; // data do deposito 2
		datas[2] = 5; // data do deposito 3
		datas[3] = 6; // data do deposito 4
		datas[4] = 10; // data que olhou o saldo
		
		// o 0.001 é a precisão do juros
		System.out.println(newton(0.001));
	}
}