package servidor;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;

import interfaces.Part;
import interfaces.PartRepository;

public class Peca extends UnicastRemoteObject implements Part {
    
    
    
    public int codigo;
    public String nome;
    public String descricao;
    public List<String> subComp;
    public int quant;
    
    
    protected Peca() throws RemoteException {
	
	super();
	// TODO Auto-generated constructor stub
    }

    
    public boolean criaPart(String nome, String descricao) throws RemoteException {
	try {
	    Naming.bind(nome, this);
	    this.codigo = nome.concat(descricao).hashCode();
	    this.nome = nome;
	    this.descricao = descricao;
	    this.subComp = new LinkedList<>();
	    this.quant = 0;
	    return true;
	} catch (MalformedURLException | RemoteException | AlreadyBoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	return false;
    }




    @Override
    public boolean addSubPart(int n) throws RemoteException {
	for (int i = 0; i < n; i++) {
	    String nome = this.nome+"/"+this.quant;
	    String descricao = this.descricao;
	    Peca p = new Peca();
	    if(!p.criaPart(nome, descricao)) {
		this.apagaLista();
		return false;
	    }
	    this.subComp.add(nome);
	    quant++;
	    
	}
	return true;
    }


    @Override
    public int getCodigo() throws RemoteException {
	// TODO Auto-generated method stub
	return this.codigo;
    }


    @Override
    public String getNome() throws RemoteException {
	// TODO Auto-generated method stub
	return this.nome;
    }


    @Override
    public String getDescricao() throws RemoteException {
	// TODO Auto-generated method stub
	return this.descricao;
    }


    @Override
    public List<String> getSubcomponente() throws RemoteException {

	return this.subComp;
    }


    @Override
    public int getQuant() throws RemoteException {
	// TODO Auto-generated method stub
	return this.quant;
    }





    private boolean verificaNome(String nome) {
	
	String[] rep;
	
	try {
	    rep= Naming.list(this.nome);
	} catch (RemoteException | MalformedURLException e) {
	    return false;
	}

	for (int j = 0; j < rep.length; j++) {
	    if(nome == rep[j])
		break;
	    if(j == rep.length-1)
		return false;
	    }
	
	
	return true;
    }


    @Override
    public void apagaLista() throws RemoteException {
	for (int i = 0; i < this.subComp.size(); i++) {
	    try {
		Naming.unbind(this.subComp.get(i));
	    } catch (RemoteException | MalformedURLException | NotBoundException e) {

	    }
	}
	this.subComp.clear();
	this.quant=0;
	
    }






   
    

}
