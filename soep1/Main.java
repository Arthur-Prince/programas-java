import java.util.LinkedList;

class Main {
	public static void main(String[] args) {
		/* Carrega os 12 arquivos e cria uma tabela que comtem os 10 BCP*/
		new ES();
		LinkedList<BCP> tabela_processos = new LinkedList<BCP>();
		ES.carrega_tabela_processos(tabela_processos);
		new Escalonador(tabela_processos, ES.ler_quantum());

		// Executa o sistema
		Escalonador.executa_sistema();
		
		/* Imprime em logfile todos os processos executados, etc */
		ES.cria_logfile();
		/*
		new Teste(tabela_processos);
		Teste.imprime_all_bcp(tabela_processos);
		*/
	}
}
