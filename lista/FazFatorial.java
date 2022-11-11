public class FazFatorial{
	
	//faz fatoriais de n
	public static int fatorial( int n){
		int j=1;
		for(int i = 1 ; i <= n ; i++){
			j *= i;
		}
		//n!
		return j;

		} 
}