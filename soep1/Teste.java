import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class Teste {
	LinkedList<BCP> tabela_processos;

	Teste(LinkedList<BCP> tabela_processos){
		this.tabela_processos = tabela_processos;
	}

	public static void imprime_lista_espera(ArrayList<LinkedList<BCP>> lista) {
		for(LinkedList<BCP> list: lista) {
			for(BCP bcp: list) {
				System.out.println("Nome: " + bcp.getNome() + " credito: " + bcp.getPrioridade());
			}
		}
	}

	public static void imprime_all_bcp(LinkedList<BCP> tabela_processos) {
		String saida = "";

		for(BCP bcp: tabela_processos) {
			saida = saida.concat(bcp.getNome() + "\n");
			for(String text: bcp.codigo)
				saida = saida.concat(text + "\n");
			saida = saida.concat("\n\n");

		}
		try{
			File file = new File("conteudo_dos_bcps.txt");
			FileWriter fw = new FileWriter(file);
			BufferedWriter writer = new BufferedWriter(fw);			

			writer.write(saida);

			writer.close();

		}catch(Exception e){
			System.out.println(e);
		}
	}
}
