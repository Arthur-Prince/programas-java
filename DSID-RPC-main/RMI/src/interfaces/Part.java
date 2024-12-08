package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;


public interface Part extends Remote{
    

    //public boolean bind(String nome)throws RemoteException;
    
    //public void quit(String nome)throws RemoteException;
    public boolean criaPart(String nome, String descricao) throws RemoteException;
    public void apagaLista()throws RemoteException;
    public boolean addSubPart(int n) throws RemoteException;
    
    public int getCodigo() throws RemoteException;
    public String getNome() throws RemoteException;
    public String getDescricao() throws RemoteException;
    public List<String> getSubcomponente() throws RemoteException;
    public int getQuant() throws RemoteException;

}
