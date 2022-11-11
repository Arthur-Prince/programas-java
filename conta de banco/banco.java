public class banco{
	public static void main(String[] args){
		conta conta1 = new conta();
		
		System.out.println(conta1.saldo);
		
		conta.deposito(100);
		System.out.println(conta1.saldo);
		
		conta.saca(50);
		System.out.println(conta1.saldo);
	}
}