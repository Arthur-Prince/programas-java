package Servidor;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import Interfaces.Part;

import java.util.ArrayList;

//Cada peca sera representada por um objeto cuja interface e Part.
public class Peca extends UnicastRemoteObject implements Part
{
	int codigo;
	String nome;
	String descricao;
	List<subcomponente> ex = new ArrayList<subcomponente>();
	PartRepository rep = new PartRepository();
    protected Peca() throws RemoteException
    {
        super();
    }
	public int getCodigo() throws RemoteException{
		return this.codigo;
	}
	public String getNome() throws RemoteException {
		return this.nome;
	}
	public String getDescricao() throws RemoteException {
		return this.descricao;
	}
	public PartRepository getRepositorio() throws RemoteException {
		return this.rep;
	}
	public List<subcomponente> getSubcomponente() throws RemoteException{
		return this.ex;
	}
    public void insertPart(Part a) throws RemoteException{
    	
    }
    public void deletePart(Part a) throws RemoteException{
    	
    }

    public Part RecoverPart(int cod) throws RemoteException{
    	return null;
    }
    
    public List<Part> obterParts() throws RemoteException{
    	return null;

    }
    //busca se ha local para conectar caso contrario vira servidor pai
    public Part conectPart() throws RemoteException{
    	return null;

    }

}