package ep2;
import java.util.LinkedList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Memoria {

	private LinkedList<Integer> memoria;
	public int MAR;
	public int MBR;

	public Memoria(File f) {
		memoria = new LinkedList<Integer>();
		try {
			inicializaMemoria(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	// adiciona cada linha do arquivo entrada.txt na lista ligada, o txt deve ter
	// cada posição da memoria em uma linha diferente do arquivo
	private void inicializaMemoria(File file) throws FileNotFoundException {

		Scanner fileScanner = new Scanner(file);
		int h = 0x10;

		while (fileScanner.hasNextLine()) {
			String line = fileScanner.nextLine();
			line = line.replace(',', ' ');
			int funcao = 0x0;
			int p1 = 0x0;
			int p2 = 0x0;
			int p1const = 0;
			int p2const = 0;

			Scanner lineScanner = new Scanner(line);
			if (lineScanner.hasNext()) {
				String f = lineScanner.next();
				switch (f) {
				
				case "mov":
					funcao = 0x1;
					break;
				case "add":
					funcao = 0x2;
					break;
				case "sub":
					funcao = 0x3;
					break;
				case "mul":
					funcao = 0x4;
					break;
				case "div":
					funcao = 0x5;
					break;
				case "mod":
					funcao = 0x6;
					break;
				case "cmp":
					funcao = 0x7;
					break;
				case "jmp":
					funcao = 0x8;
					break;
				case "je":
					funcao = 0x9;
					break;
				case "jne":
					funcao = 0xA;
					break;
				case "jg":
					funcao = 0xB;
					break;
				case "jge":
					funcao = 0xC;
					break;
				case "jl":
					funcao = 0xD;
					break;
				case "jle":
					funcao = 0xE;
					break;
				default:
					System.out.println("   erro de compilacao   ");
				}
				
			}
			if (lineScanner.hasNext()) {
				String pe1 = lineScanner.next();
				switch (pe1) {
				case "ax":
					p1 = 0x1;
					break;
				case "bx":
					p1 = 0x2;
					break;
				case "cx":
					p1 = 0x3;
					break;
				case "dx":
					p1 = 0x4;
					break;
				case "[ax]":
					p1 = 0x5;
					break;
				case "[bx]":
					p1 = 0x6;
					break;
				case "[cx]":
					p1 = 0x7;
					break;
				case "[dx]":
					p1 = 0x8;
					break;
				default:
					if (pe1.charAt(0) == '[') {
						pe1 = pe1.replace("[", "");
						pe1 = pe1.replace("]", "");
						p1 = 0xA;
					} else {
						p1 = 0x9;
					}
					p1const = Integer.parseInt(pe1,16);
					break;
				}
			}
			if (lineScanner.hasNext()) {
				String pe2 = lineScanner.next();
				switch (pe2) {
				case "ax":
					p2 = 0x1;
					break;
				case "bx":
					p2 = 0x2;
					break;
				case "cx":
					p2 = 0x3;
					break;
				case "dx":
					p2 = 0x4;
					break;
				case "[ax]":
					p2 = 0x5;
					break;
				case "[bx]":
					p2 = 0x6;
					break;
				case "[cx]":
					p2 = 0x7;
					break;
				case "[dx]":
					p2 = 0x8;
					break;
				default:
					if (pe2.charAt(0) == '[') {
						p2 = 0xA;
						pe2 = pe2.replace("[", "");
						pe2 = pe2.replace("]", "");
					} else {
						p2 = 0x9;
					}
					p2const = Integer.parseInt(pe2,16);
					break;
				}
			}
			if (lineScanner.hasNext()) {
				System.out.println("A entrada tem algo de errado, conserte e tente novamente.");
				System.exit(1);
			}

			lineScanner.close();

			int mem = funcao * h * h + p1 * h + p2;

			memoria.add(mem);
			if (p1 == 0x9 || p1 == 0xA) {
				memoria.add(p1const);
			}
			if (p2 == 0x9 || p2 == 0xA) {
				memoria.add(p2const);
			}
		}
		fileScanner.close();
	}

	// escreve o que esta no endereço “MAR” no MBR
	void read() {
		if(MAR<memoria.size())
			MBR = memoria.get(MAR);
		else{
			MBR = 0;
		}
	}

	// escreve o que esta no MBR na memoria no endereço do MAR
	void write() {
		if(MAR<memoria.size())
			memoria.set(MAR, MBR);
		else{
			int i=memoria.size();
			while(i<MAR){
				memoria.add(0);
				i++;
			}
			memoria.add(MBR);
		}
	}

	//imprime a memoria na base 16
	public void imprimeMemoria(int pc) {
		int i=0;
		for(Integer x : memoria) {
			System.out.println((String.format("%x", x)));
			if(i==pc){
				System.out.println("------");
			}
			i++;
		}
	}
}
