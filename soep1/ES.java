import java.io.*;
import java.util.LinkedList;
import java.util.ArrayList;

// classe que cuidade entradas e saidas do sistema
public class ES{
	private static int[] prioridades;
	
	ES() {
		ES.prioridades = ler_prioridades();
	}
	// ler o arquivo quantum.txt
	// Saida: Valor do quantum para este sistema
	public static int ler_quantum(){
		try{
			File file = new File("processos/quantum.txt");
			FileReader fr = new FileReader(file);
			BufferedReader reader = new BufferedReader(fr);

			int n_com = Integer.parseInt(reader.readLine());
			reader.close();

			return n_com;

		}catch(Exception e){
			System.out.println(e);
			return 0;
		}
	}

	// ler o arquivo prioridade
	// Saida: Lista com tamanho 10 que contem as prioridades dos programas
	private static int[] ler_prioridades(){
		String text;
		int[] prioridades = new int[10];
		
		try{
			File file = new File("processos/prioridades.txt");
			FileReader fr = new FileReader(file);	
			BufferedReader reader = new BufferedReader(fr);


			for(int i = 0; (text = reader.readLine()) != null; i++){
				prioridades[i] = Integer.parseInt(text);
			}
			
			reader.close();	
			return prioridades;
			
		}catch(Exception e){
			System.out.println(e);
			return null;
		}
	}

	// Carrega os 10 arquivos-instrucoes, e retorna o conteudo 
	// Saida: LinkedList<String> contendo as instrucoes
	private static BCP ler_codigo(String nome_arquivo, int prioridade){
		ArrayList<String> codigo = new ArrayList<String>();
		String text, nome_processo;

		try{
			File file = new File(nome_arquivo);
			FileReader fr = new FileReader(file);
			BufferedReader reader = new BufferedReader(fr);
			
			// Carrega cada linha de instrucao e adiciona na String codigo
			// quando chegar no fim do arquivo, termina
			nome_processo = reader.readLine();
			while((text = reader.readLine()) != null) {
				codigo.add(text);
			}
			
			reader.close();
			
			return new BCP(prioridade, nome_processo, codigo);
			
		}catch(Exception e){
			System.out.println(e);
			return null;
		}
	}
	
	public static void carrega_tabela_processos(LinkedList<BCP> tabela_processos){
		for(int i = 1; i <= 10; i++) {
			tabela_processos.add(ler_codigo(String.format("processos/%02d.txt", i), prioridades[i-1]));
		}
	}
	
	
	// cria um logfile e imprime todo o processo no arquivo de uma vez
	public static void cria_logfile(){
		try{
			File file = new File("log" + String.format("%02d", Escalonador.getN_com()) + ".txt");
			FileWriter fw = new FileWriter(file);
			BufferedWriter writer = new BufferedWriter(fw);			
			
			writer.write(Escalonador.getLogs());
			
			writer.close();
			
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	public static int[] getPrioridades() {
		return prioridades;
	}
}
