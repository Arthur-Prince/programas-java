package test;

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ObjID;
import java.util.Iterator;
public class CalculatorServer
{
	
    CalculatorServer()
    {
        try
        {
            //System.setProperty("java.rmi.server.hostname", "127.0.0.1");
            Registry o = (Registry)LocateRegistry.createRegistry(1099);
            ObjID id = new ObjID();
        	
            Calculator c = new CalculatorImple();
            Naming.bind("CalculatorService",(Remote) c);
            System.out.println(o);
            
            
            
        }
        catch (Exception e)
        {
            System.out.println("relaxa");
            e.printStackTrace();
            Calculator c2;
	    try {
		c2 = new CalculatorImple();
		//Naming.bind("CalculatorService3",(Remote) c2);
		
		Calculator c = (Calculator) Naming.lookup("rmi://127.0.0.1:1099/CalculatorService");
		Registry l = (Registry) LocateRegistry.getRegistry(1099);
		l.bind("teste4", c2);
		String[] s = Naming.list("CalculatorService");
		for (int i = 0; i < s.length; i++) {
		    System.out.println(s[i]);
		}
		

	    } catch (Exception e1) {
		// TODO Auto-generated catch block
		System.out.println("deu merda");
		e1.printStackTrace();
		System.out.println("deu merda");
	    }
            
        }

    }
    public static void main(String[] args)
    {
	CalculatorServer c = new CalculatorServer();
	System.out.println(3);
	
        System.out.println(c);

    }
    
}