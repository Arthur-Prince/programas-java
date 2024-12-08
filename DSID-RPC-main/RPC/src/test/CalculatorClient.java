package test;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ObjID;
public class CalculatorClient
{
    public static void main(String[] args)
    {
        try
        {
            
            //Registry r = (Registry) LocateRegistry.getRegistry(1098);
            //System.out.println(r);
            //System.out.println(r.list()[0]);
            Calculator c = (Calculator) Naming.lookup("rmi://127.0.0.1:1099/CalculatorService2");
            System.out.println("Adicao : "+c.add(20, 15));
            Registry l = (Registry) LocateRegistry.getRegistry(1099);
            System.out.println(l.REGISTRY_PORT);
            System.out.println();
            //System.out.println(r.lookup("CalculatorService2"));
            //System.out.println(r.list()[1]);
            Calculator p = c.cal(c);
            
            System.out.println(c);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}