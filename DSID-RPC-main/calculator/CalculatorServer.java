package calculator;

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
public class CalculatorServer
{
	
    CalculatorServer()
    {
        try
        {
        	System.setProperty("java.rmi.server.hostname", "127.0.0.1");
        	LocateRegistry.createRegistry(1099);
            Calculator c = new CalculatorImple();
            Naming.bind("CalculatorService",(Remote) c);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
    public static void main(String[] args)
    {
        new CalculatorServer();
    }
}