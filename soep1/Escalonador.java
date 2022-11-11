import java.util.ArrayList;
import java.util.LinkedList;

public class Escalonador{
	private static LinkedList<BCP> tabela_processos;
	private static ArrayList<LinkedList<BCP>> prontos;
	private static LinkedList<BCP> bloqueados;
	private static int n_com, max, terminado;
	private static float instrucoes, trocas, quantum_total;
	private static String logs;

	// Construtor
	Escalonador(LinkedList<BCP> tabela_processos, int n_com){
		Escalonador.tabela_processos = tabela_processos;

		for(int x: ES.getPrioridades()) 
			if(Escalonador.max < x) 
				Escalonador.max = x;

		Escalonador.prontos = new ArrayList<LinkedList<BCP>>(max+1);
		for(int i = 0; i <= max; i++) {
			prontos.add(new LinkedList<BCP>());

		}
		Escalonador.bloqueados = new LinkedList<BCP>();
		Escalonador.n_com = n_com;
		Escalonador.instrucoes = 0;
		Escalonador.trocas = 0;
		Escalonador.quantum_total = 0;
		Escalonador.terminado = 0;
		Escalonador.logs = "";
	}

	public static void executa_sistema() {
		boolean terminou = false;
		boolean resetar = false;
		// Carrega os processos em fila de prontos
		try {
			reseta();
			carregando();

			// Executa
			while(terminado < 10){
				// 6 enfiar atualiza_bloqueados se nao houver nenhum em prontos
				/*
				for(LinkedList<BCP> list: prontos) {
					for(BCP bcp: list) {
						System.out.println(bcp.getNome());
					}
				}
				System.out.println();
*/

				terminou = false;
				resetar = false;
				for(LinkedList<BCP> list: prontos) {
					for(BCP bcp: list) {
						if(bcp.getCredito() == 0 && bloqueados.isEmpty()) { 
							resetar = true;
							break;
						}
						else {
							terminou = executa_processo(bcp);
							atualiza_bloqueados();
							break;
						}
					}
					if(terminou || resetar)
						break;
				}
				if(resetar) {
					reseta();
				}
				/*
				for(LinkedList<BCP> list: prontos)
					for(BCP bcp: list)
					System.out.println(bcp.getNome());
					*/
			}

			// Fim
			fim_sistema();
		}catch(Exception e) {
			e.printStackTrace();
		}

		//Teste.imprime_lista_espera(prontos);
	}

	// Executa o processo o numero maximo permitido definido por quantum atribuido e n_com
	private static boolean executa_processo(BCP bcp) {

		int executado_por_escalonamento = 0;
		trocas++;
		bcp.setEstado("EXECUTANDO");
		quantum_total += bcp.getQuantum();

		// 1.2 Saida 3
		logs = logs.concat("Executando " + bcp.getNome() + "\n");
		
		// 1 5E
		atualiza_bloqueados();

		while(executado_por_escalonamento < bcp.getQuantum() * n_com) {
			executado_por_escalonamento++;
			if(executa_instrucao(bcp, executado_por_escalonamento))
				break;
		}

		// Atualizar creditos e quantuns
		if(bcp.getEstado().compareTo("TERMINOU") == 0)
			return true;
		prontos.get(max - bcp.getCredito()).remove(bcp);
		if(bcp.getCredito() > 2) {
			bcp.setCredito(bcp.getCredito()-2);
			bcp.setQuantum(bcp.getQuantum()+1);
			prontos.get(max - bcp.getCredito()).addFirst(bcp);
		} else {
			bcp.setCredito(0);
			bcp.setQuantum(1);
			prontos.get(max - bcp.getCredito()).addLast(bcp);
		}
		return true;
	}

	// Executa uma linha de comando 
	private static boolean executa_instrucao(BCP bcp, int executado_por_escalonamento) {

		instrucoes++;
		int PC_desta_instrucao = bcp.getPC();
		bcp.setPC(bcp.getPC()+1);

		switch(bcp.codigo.get(PC_desta_instrucao).charAt(0)) {
		case 'C':
			// CMD
			// Nao faz nada
			break;
		case 'E':
			// E/S
			comando_es(bcp, executado_por_escalonamento);
			return true;
		case 'S':
			// SAIDA
			saida(bcp);
			return true;
		case 'X':
			// X=
			bcp.setX(bcp.codigo.get(PC_desta_instrucao));
			break;
		case 'Y':
			// Y=
			bcp.setY(bcp.codigo.get(PC_desta_instrucao));
			break;
		}
		return false;
	}

	private static void reseta() {
		// elimina todo que estah no prontos e bloqueados
		// Reatribui no pronto, usando a tabela_processos
		for(LinkedList<BCP> list: prontos)
			list.clear();
		bloqueados.clear();
		for(BCP bcp: tabela_processos) {
			prontos.get(max - bcp.getPrioridade()).add(bcp);
			bcp.setCredito(bcp.getPrioridade());
		}
	}

	private static void carregando() {
		// 1.2 Saida 1
		// Atualiza o log com carregando
		for(LinkedList<BCP> list: prontos) {
			for(BCP bcp: list) {
				logs = logs.concat("Carregando " + bcp.getNome() + "\n");		
			}
		}		
	}

	private static void comando_es(BCP bcp, int executado_por_escalonamento) {

		// 1 5A
		bcp.setEstado("BLOQUEADO");

		prontos.get(max - bcp.getCredito()).remove(bcp);
		bloqueados.addLast(bcp);

		// 1 5B
		bcp.setTempo_espera(2);

		// 1.2 Saida 2 e 4
		logs = logs.concat("E/S iniciada em " + bcp.getNome() + "\n");
		logs = logs.concat("Interrompendo " + bcp.getNome()	+ " apos " + executado_por_escalonamento +
						   " instrucoes\n");
	}

	private static void atualiza_bloqueados() {

		// 1 5C
		for(BCP bcp: bloqueados) {
			if(bcp.getTempo_espera() == 1) {
				// 5E
				bcp.setEstado("PRONTO");
				bloqueados.remove(bcp);
				// acrescenta este na fila de pronto
				prontos.get(max - bcp.getCredito()).addLast(bcp);
			}
			else {
				bcp.setTempo_espera(1);
			}
		}

	}


	// 7
	private static void saida(BCP bcp) {

		for(LinkedList<BCP> list: prontos)
			list.remove(bcp);
		prontos.get(max - bcp.getCredito()).remove(bcp);
		// 1.2 Saida 5
		logs = logs.concat(bcp.getNome() + " terminado." + bcp.getX() + ". " + bcp.getY() + "\n");

		tabela_processos.remove(bcp);
		bcp.setEstado("TERMINOU");
		terminado++;
	}


	// 1.2 Saida pre-exemplo
	private static void fim_sistema() {
		
		logs = logs.concat("MEDIA DE TROCAS: " + (trocas / 10) + "\n" +
				"MEDIA DE INSTRUCOES: " + (instrucoes / quantum_total) + "\n" +
				"QUANTUM: " + n_com + "\n");
		System.out.println("MEDIA DE TROCAS: " + (trocas / 10) + "\n" +
						   "MEDIA DE INSTRUCOES: " + (instrucoes / quantum_total) + "\n" +
						   "QUANTUM: " + n_com + "\n");
	}
	/*
	private static void imprime_prontos() {
		for(LinkedList<BCP> list: prontos) {
			for(BCP bcp1: list) {
				System.out.println(bcp1.getNome());
			}
		}
		System.out.println();
	}
	*/
	public static String getLogs() {
		return logs;
	}
	public static int getN_com() {
		return n_com;
	}
}
