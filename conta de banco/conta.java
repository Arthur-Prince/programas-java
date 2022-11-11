public class conta{
	private double saldo = 200;
	
	public double getsaldo() {
		return this.saldo;
	}
	
	public void deposita(double deposito){
		this.saldo += deposito;
		System.out.println("voce tem" + deposito);
		System.out.println("voce tem" + saldo);
	}
	
	public void saca(double saque){
		this.saldo -= saque;
		System.out.println("voce tem" + saque);
		System.out.println("voce tem" + saldo);
	}
}