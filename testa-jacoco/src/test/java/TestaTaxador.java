import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestaTaxador {
    
    public Taxador taxador = new Taxador();
    
    
    @Test
    public void testecomum199(){
	assertEquals(0, this.taxador.calcula_taxa_desconto("comum",199));
    }
    
    @Test
    public void testeBronze199(){
	assertEquals(5, this.taxador.calcula_taxa_desconto("bronze",199));
    }
    
    @Test
    public void testeBronze399(){
	assertEquals(5, this.taxador.calcula_taxa_desconto("bronze",399));
    }
    
    @Test
    public void testeBronze499(){
	assertEquals(10, this.taxador.calcula_taxa_desconto("bronze",499));
    }
    
    @Test
    public void testeBronze500(){
	assertEquals(15, this.taxador.calcula_taxa_desconto("bronze",500));
    }
    
    @Test
    public void testePrata499(){
	assertEquals(10, this.taxador.calcula_taxa_desconto("prata",499));
    }
    
    @Test
    public void testeOuro(){
	assertEquals(15, this.taxador.calcula_taxa_desconto("ouro",499));
    }
    
}
