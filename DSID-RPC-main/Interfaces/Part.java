package Interfaces;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.*;

import Servidor.subcomponente;

public interface Part extends Remote{
	public int getCodigo() throws RemoteException;
	public String getNome() throws RemoteException;
	public String getDescricao() throws RemoteException;
	public List<subcomponente> getSubcomponente() throws RemoteException;
	
    public void insertPart(Part a) throws RemoteException;
    public void deletePart(Part a) throws RemoteException;

    public Part RecoverPart(int cod) throws RemoteException;
    public List<Part> obterParts() throws RemoteException;
    public Part conectPart() throws RemoteException;
}
