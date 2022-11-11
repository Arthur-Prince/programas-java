import java.util.ArrayList;

public class BCP {
	private int PC, quantum, credito, tempo_espera;
	
	private String X, Y;
	
	private final int prioridade;
	private String nome, estado;
	public ArrayList<String> codigo;
	
	BCP(int prioridade, String nome, ArrayList<String> codigo){
		this.X = "0";
		this.Y = "0";
		this.PC = 0;
		this.quantum = 1;
		this.credito = 0;
		this.tempo_espera = 0;
		this.prioridade = prioridade;
		this.nome = nome;
		this.estado = "PRONTO";
		this.codigo = codigo;
	}
	
		
	

	public int getCredito() {
		return credito;
	}




	public void setCredito(int credito) {
		this.credito = credito;
	}




	public int getTempo_espera() {
		return tempo_espera;
	}




	public void setTempo_espera(int tempo_espera) {
		this.tempo_espera = tempo_espera;
	}




	public String getX() {
		return X;
	}
	public void setX(String x) {
		X = x;
	}
	public String getY() {
		return Y;
	}
	public void setY(String y) {
		Y = y;
	}
	public int getPC() {
		return PC;
	}
	public void setPC(int pC) {
		PC = pC;
	}
	public int getQuantum() {
		return quantum;
	}
	public void setQuantum(int quantum) {
		this.quantum = quantum;
	}
	public int getPrioridade() {
		return prioridade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getNome() {
		return nome;
	}
}
