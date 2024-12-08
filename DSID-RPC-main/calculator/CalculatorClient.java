package calculator;

import java.rmi.Naming;
public class CalculatorClient
{
    public static void main(String[] args)
    {
        try
        {
            Calculator c = (Calculator) Naming.lookup("rmi://127.0.0.1:1099/CalculatorService");
            System.out.println("Adição : "+c.add(20, 15));
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}