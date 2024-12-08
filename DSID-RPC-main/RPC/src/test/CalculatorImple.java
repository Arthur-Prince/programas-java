package test;

import java.rmi.RemoteException;
import java.rmi.server.ObjID;
import java.rmi.server.UnicastRemoteObject;

public class CalculatorImple extends UnicastRemoteObject implements Calculator
{
    Calculator cal;
    protected CalculatorImple() throws RemoteException
    {
        super();
    }
    public long add(long a, long b) throws RemoteException
    {
	System.out.println(a+b);
        return a+b;
    }
    @Override
    public Calculator cal(Calculator c) throws RemoteException {
	this.cal = c;
	System.out.println(c);
	return this;
    }
}