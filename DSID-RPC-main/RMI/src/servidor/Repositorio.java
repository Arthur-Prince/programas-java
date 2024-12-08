package servidor;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;

import interfaces.Part;
import interfaces.PartRepository;

public class Repositorio extends UnicastRemoteObject implements PartRepository {
    
    
    List<Part> parts;
    String partCorrente;
    
    protected Repositorio() throws RemoteException {
	
	super();
	this.parts = new LinkedList<>();
	
    }

    

    @Override
    public String[] listpart() throws RemoteException {
	String[] lista = new String[this.parts.size()];
	for (int i = 0; i < lista.length; i++) {
	    lista[i] = this.parts.get(i).getNome();
	}
	return lista;
    }

    @Override
    public boolean addSubPart(int subsParts)throws RemoteException {
	try {
	    Part p = (Part)Naming.lookup(PartRepository.end.concat(this.partCorrente));
	    return p.addSubPart(subsParts);
	} catch (MalformedURLException | RemoteException | NotBoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	
	return false;
    }

    @Override
    public String getp(int codigo) throws RemoteException {
	
	for (int i = 0; i < this.parts.size(); i++) {
	    if(this.parts.get(i).getCodigo()==codigo) {
		this.partCorrente=this.parts.get(i).getNome();
		return this.partCorrente;
	    }
	}
	

	return null;
    }

    @Override
    public boolean addp(String nome, String descricao)throws RemoteException {
	Part p = (Part) new Peca();
	if(p.criaPart(nome, descricao)) {
	    this.parts.add(p);
	    return true;
	}
	
	return false;
    }

    @Override
    public String[] showp() throws RemoteException {
	String[] rtn = new String[4];
	try {
	    Part p = (Part)Naming.lookup(PartRepository.end.concat(this.partCorrente));
	    rtn[0]=p.getNome();
	    rtn[1]=p.getDescricao();
	    rtn[2]=""+p.getCodigo();
	    rtn[3]=""+p.getQuant();
		
	    
	} catch (MalformedURLException | RemoteException | NotBoundException e) {
	    return null;
	}
	
	return rtn;
	// TODO Auto-generated method stub
	
    }

    @Override
    public boolean ClearList() throws RemoteException {
	try {
	    Part p = (Part)Naming.lookup(PartRepository.end.concat(this.partCorrente));
	    p.apagaLista();
	} catch (MalformedURLException | RemoteException | NotBoundException e) {
	    return false;
	}
	return true;
    }
    
   

}
