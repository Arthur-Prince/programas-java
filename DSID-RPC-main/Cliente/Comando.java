package Cliente;
import Servidor.Peca;
import Servidor.PartServidor;

import java.rmi.Naming;
import java.rmi.RemoteException;

import Servidor.PartRepository;

public class Comando {
	
	/*Faz o cliente se conectar a outro servidor e muda o repositorio corrente. Este comando
	recebe o nome de um repositorio e obtem do servico de nomes uma referencia para esse
	repositorio, que passa a ser o repositorio corrente.*/
	public Peca bind(String nomeRepositorio) {
		try {
		Peca corrente = (Peca) Naming.lookup("rmi://127.0.0.1:1099/"+nomeRepositorio);
		
		return corrente;
		}
        catch (Exception e)
        {
            System.out.println(e);
        }
		return null;
	}
	/*Lista as pecas do repositorio corrente*/
	public void listp(Peca p) throws RemoteException{
		for(int i = 0; i < p.getRepositorio().repositorio.size(); i++) {
			System.out.println(p.getRepositorio().repositorio.get(i).getNome());
		}
	}
	/*Busca uma peca por codigo. A busca e efetuada no repositorio corrente. Se encontrada,
	a peca passa a ser a nova peca corrente.*/
	public Peca getp(int codigo, Peca corrente) throws RemoteException{
		for(int i = 0; i < corrente.getRepositorio().repositorio.size(); i++) {
			if(codigo == corrente.getRepositorio().repositorio.get(i).getCodigo()) {
				return corrente.getRepositorio().repositorio.get(i);
			}
		}
		return null;
	}
	/*Mostra atributos da peca corrente.*/
	public void showp(Peca corrente) throws RemoteException {
		System.out.println("Nome da peca: "+corrente.getNome());
		System.out.println("Codigo da peca: "+corrente.getCodigo());
		System.out.println("Descricao da peca: "+corrente.getDescricao());
		
	}
	/* Esvazia a lista de sub-pecas corrente*/
	public void clearlist (Peca corrente) throws RemoteException{
		corrente.getSubcomponente().clear();
	}
	/*Adiciona a lista de sub-pecas corrente n unidades da peca corrente*/
	public void addsubpart(Peca corrente, int quant) throws RemoteException{
		Servidor.subcomponente addsub = new Servidor.subcomponente();
		addsub.setSub(corrente, quant);
		corrente.getSubcomponente().add(addsub);

	}
	/*Adiciona uma pe¸ca ao repositorio corrente. A lista de sub-pe¸cas corrente e usada como
lista de subcomponentes diretos da nova peca. (E so para isto que existe a lista de 
sub-pe¸cas corrente.)*/
	public void addp() {
		
	}
	/*Encerra a execucao do cliente*/
	public void quit() {
		 System.exit(0);
	}
}
