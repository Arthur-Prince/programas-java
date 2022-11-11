import java.util.Scanner;
import ep2.*;

public class Teste {
	public static void main(String[] args) {
		UnidadeDeControle uc= new UnidadeDeControle();
		System.out.println("digete 1 para executar seu codigo(tempo por tempo)");
		Scanner sc = new Scanner(System.in);
		int i=sc.nextInt();
		while(i==1){
			int car=0;
			int[] IRdec = new int[3];
			boolean marca = true;
			while(marca){
				System.out.println("caso queira parar a execucao em qualquer tempo digite 2\ncaso contrario digite 1");
				System.out.println("para olhar os registradores digite:\n3 para ax; 4 para bx; 5 para cx; 6 para dx;");
				System.out.println("e para olhar a memoria digite 7");
				i=sc.nextInt();
				System.out.println();
				if(i==1)
					marca=false;
				if(i==2){
					break;
				}
				if(i==3){
					System.out.println("\nax= "+ (String.format("%x", uc.reg.getAx())));
				}
				if(i==4){
					System.out.println("\nbx= "+ (String.format("%x", uc.reg.getBx())));
				}
				if(i==5){
					System.out.println("\ncx= "+ (String.format("%x", uc.reg.getCx())));
				}
				if(i==6){
					System.out.println("\ndx= "+ (String.format("%x", uc.reg.getDx())));
				}
			
				if(i==7){
					System.out.println();
					uc.memoria.imprimeMemoria(uc.PC);
				}
				System.out.println();
			}
			if(i==2)
				break;
			//busca inicial
			while(car<4){
				uc.excuta1LinhaDaMatriz(car,IRdec);
				car=uc.jump(car,IRdec);
				i=sc.nextInt();
				if(i==2){
					break;
				}
			}
			if(i==2)
				break;
			//funcao
			IRdec=uc.decodificaIR();
			while(car!=0){
				uc.excuta1LinhaDaMatriz(car,IRdec);
				car=uc.jump(car,IRdec);
				i=sc.nextInt();
				if(i==2){
					break;
				}
			}
			
		}
		sc.close();
		
	}
}
