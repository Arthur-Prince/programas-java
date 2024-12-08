package cliente;

import java.rmi.Naming;
import java.util.ArrayList;
import java.util.List;

import servidor.Peca;
import servidor.Repositorio;

import java.util.Scanner;



public class main {

	//string hash
	int StringHash(String nome) {
		return nome.hashCode();
	}
	
    public static void main(String[] args)
    {
    	System.out.println("Envie o nome da peca");
        Scanner scan = new Scanner(System.in);
        String nomePeca = scan.nextLine(); 
	    try
	    {
	    	
	    	Repositorio rep = (Repositorio) Naming.lookup("rmi://127.0.0.1:1099/"+nomePeca);
	    	prompt promp = new prompt();
	        boolean loop = true;
	    	while(loop) {
		    	System.out.println("");
		    	System.out.println("-----------------------------Lista de comandos----------------------------------------");
		    	System.out.println("--------------------------------------------------------------------------------------");
		    	System.out.println("b (se conecta a outro servidor com nome inserido e muda o repositorio corrente)");
		    	System.out.println("");
		    	System.out.println("l (Lista as pecas do repositorio corrente)");
		    	System.out.println("");
		    	System.out.println("s (Mostra atributos da peca corrente)");
		    	System.out.println("");
		    	System.out.println("g (Escreve nome da peca a se tornar peca corrente)");
		    	System.out.println("");
		    	System.out.println("c (Esvazia a lista de sub-pecas corrente )");
		    	System.out.println("");
		    	System.out.println("a (Adiciona a lista de sub-pecas corrente n unidades da peca corrente)");
		    	System.out.println("");
		    	System.out.println("d (Adiciona uma peca ao repositorio corrente)");
		    	System.out.println("");
		    	System.out.println("q (Encerra a execucao do cliente)");
		    	System.out.println("");
		    	System.out.println("--------------------------------------------------------------------------------------");
		        char comando = scan.next().charAt(0); 
		        System.out.println("");
			    switch(comando) {
			        case 'b':
			        	System.out.println("Insira o nome do servidor:");
			        	String nomeServidor = scan.nextLine(); 
			        	promp.bind(nomeServidor);
			        	break;
			        case 'l':
			        	promp.listp(rep);
			        	break;
			        case 's':
			        	promp.showp(rep);
			        	break;
			        case 'g':
			        	System.out.println("Insira o codigo da peca:");
			        	int cod = scan.nextInt();
			        	promp.getp(cod, rep);
			        	break;
			        case 'c':
			        	promp.clearlist(rep);
			        	System.out.println("Lista esvaziada ");
			        	break;
			        case 'a':
			        	System.out.println("Insira a quantidade da peca:");
			        	int quant = scan.nextInt();
			        	promp.addsubpart(rep, quant);
			        	break;
			        case 'd':
			        	System.out.println("Insira o nome da peca a ser adicionada: ");
			        	String pecaAdd = scan.nextLine(); 
			        	System.out.println("Insira a descricao da peca a ser adicionada: ");
			        	String pecaDesc = scan.nextLine(); 
			        	promp.addp(rep, pecaAdd, pecaDesc);
			        	break;
			        	case 'q':
			        	promp.quit();
			        	break;
			        default:
			        	System.out.println("COMANDO INVALIDO");
		        }
	    	}
	    }
	    catch (Exception e)
	    {
	        System.out.println(e);
	    }
    }
}