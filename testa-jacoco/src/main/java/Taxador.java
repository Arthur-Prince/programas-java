
public class Taxador {
    public int calcula_taxa_desconto(String tipo_cliente, int valor_compra) {

	   int taxa = 0;

	   if (valor_compra >= 500 || tipo_cliente.equals("ouro"))

	       taxa = 15;

	   else {

	       if (tipo_cliente.equals("prata") || valor_compra >=400)

	           taxa = 10;

	       else

	           if (valor_compra>=200 || tipo_cliente.equals("bronze"))

	               taxa = 5;
	   }
	   

	   return taxa;
    }	
    
    
    
}
