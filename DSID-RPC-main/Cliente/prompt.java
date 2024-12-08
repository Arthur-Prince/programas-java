package Cliente;
import Servidor.PartRepository;
import Servidor.Peca;
import Servidor.subcomponente;

import java.rmi.Naming;
import java.util.ArrayList;
import java.util.List;

import Interfaces.Part;

public class prompt {
    public static void main(String[] args)
    {
        try
        {
        	Peca corrente = (Peca) Naming.lookup("rmi://127.0.0.1:1099/filho1");

            
            /*Provavelmente o mais facil e escrever um cliente com uma interface tipo linha de comando.
            Uma possibilidade e um cliente “linha de comando” que mantenha tres variaveis:*/
            Comando coman = new Comando();
            //uma referencia ao repositorio com o qual toda interacao ocorre;
            int repositorioCorrente;
            
            //uma referencia a peca com a qual toda interacao ocorre;
            
            //usada exclusivamente quando uma nova peca e adicionada ao repositorio corrente.
            List<subcomponente> subpecasCorrente = new ArrayList<subcomponente>();
            
            // Examinando o nome do repositorio e o numero de pecas nele contidas,
            
            //Listando as pecas no repositorio,
            coman.listp(corrente);
            //Buscando uma peca (por codigo de peca) no repositorio
            
            //Adicionando ao repositorio novas pecas (primitivas ou agregadas);
            
            
            
            /*Tendo uma referencia a uma peca, referencia essa previamente obtida como resultado
			de uma busca num repositorio, interaja com a peca:*/
            
            //Examinando o nome e a descricao da peca
            
            //Obtendo o (nome do) repositorio que a contem,
            
            //Verificando se a peca e primitiva ou agregada
            
            //Obtendo o numero de subcomponentes diretos e primitivos da peca,
            
            //Listando suas sub-pecas.

         
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
