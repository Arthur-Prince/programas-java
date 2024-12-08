package servidor;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class test {

    

    public static void main(String[] args) {

	Peca p;
	
	try {
	    Registry o = (Registry)LocateRegistry.createRegistry(1099);
	    p = new Peca();
	    p.criaPart("a", "b");
	    System.out.println(p.codigo);
	    System.out.println(o.list()[0]);
	} catch (RemoteException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

}
