package Servidor;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.RMISecurityManager;
import java.rmi.Remote;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import Interfaces.Part;

public class PartServidor{
    		
    public PartServidor()
    {
    	
        try
        {
        	
        	
        	System.setProperty("java.rmi.server.hostname", "127.0.0.1");
        	LocateRegistry.createRegistry(1099);
            
            Peca c = new Peca();
            
            Naming.bind("partServidor",(Remote) c);
         
            
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void main(String[] args)
    {
        new PartServidor();
    }
}
