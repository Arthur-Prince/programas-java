package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PartRepository extends Remote{
    
    public static final String end = "rmi://127.0.0.1:1099/";
    
    
    //public void criaPart(String nome, String descricao)throws RemoteException;
    
    //retorna o nome da lista de repositorios
    public String[] listpart()throws RemoteException;
    //adiciona lista de sub-parts na part corrente
    public boolean addSubPart(int subsParts)throws RemoteException;
    //busca part por codigo e se encontrar muda a part corrente para essa part(se nao retorna null
    public String getp(int codigo)throws RemoteException;
    //adiciona uma part no repositorio
    public boolean addp(String nome, String descricao)throws RemoteException;
    //mostra atributos da part
    public String[] showp()throws RemoteException;
    //apaga sub part da part corrente
    public boolean ClearList()throws RemoteException;
}
