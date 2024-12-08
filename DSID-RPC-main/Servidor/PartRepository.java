package Servidor;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import java.rmi.RemoteException;

public class PartRepository  {
	public String nome;
	public List<Peca> repositorio = new ArrayList<Peca>();
	
}
