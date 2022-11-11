import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.io.File;

public class PontuacaoDaCorrida {
	
	public static void imprime(double[][] k) {
		for(int i=0;i<k.length;i++){
			for(int j =0; j<k[i].length;j++){
				System.out.print(k[i][j]+ "     ");
			}
			System.out.println();
		}
	}
	public static void imprime(double[] k){
		for(double d:k){
			System.out.print(d+"  ");
		}
	}
	
	
	public static void insercao(double[][] a) {
		for(int i=1; i<a.length; i++) {
			double aux0 = a[i][0];
			double aux1 = a[i][1];
			int j = i;
			while ((j > 0) && (aux0 < a[j-1][0])) {
				a[j][0] = a[j-1][0];
				a[j][1] = a[j-1][1];
				j--;

			}
			a[j][0] = aux0;
			a[j][1] = aux1;
		}
	}
	
	public static double[][] pegaPonto() throws FileNotFoundException{
		File File = new File("pontos.txt");
		Scanner pontos= new Scanner(File);
		int n=0;
		while(pontos.hasNextLine()){ 
			n++;
			if(n>= 4)
				break;
		}
		double[][] conjuntoDePontos= new double[n][2];
		
		for(int i=0;i<conjuntoDePontos.length && pontos.hasNextLine();i++) {
			String ponto = pontos.nextLine();
			String[] numero = ponto.split(",");
			conjuntoDePontos[i][0] = Double.parseDouble(numero[0])/100;
			conjuntoDePontos[i][1] = Double.parseDouble(numero[1])/100;
		}
		pontos.close();
		return conjuntoDePontos;
		
	}
	
	public static void metodoDeGauss() throws FileNotFoundException {
		//faz a matriz tal que Ax=b com x sendo o coeficiente de f
		double[][]pontos = pegaPonto();		//contem todos os pontos
		double[][]a = new double [pontos.length][pontos.length+1]; 
		// coloca os pontos da matriz:
		for(int j =0;j<a.length;j++){
			for(int i =0; i<a[j].length-1; i++){
				a[j][i]= Math.pow(pontos[j][0],i+1);
			}
			a[j][a[j].length-1] = pontos[j][1];
		}
		/*temos:	
		|a11  a12  a13  ... a1n|		|k1 |		|y1|
		|a21  a21  a23  ... a2n|		|k2 |		|y2|
		|:                   : |	x	|:  |	=	|: |
		|an1  an2  an3  ... ann|		|kn |		|yn|
		
		com aij = xi^j 
		
		tambem tem-se a notacao:
		
		|a11  a12  a13  ... a1n|a1,n+1|
		|a21  a21  a23  ... a2n|a2,n+1|
		|:                   : |:     |
		|an1  an2  an3  ... ann|an,n+1|
		
		pelo metodo de gauss det|a11  a1n| = am-1,n-1 com amn pertencente a uma matriz Aj-1,i-1 equivalente a Aij e 2 < m < j , 2 < n < i.
								|am1  amn|

		*/
		double[][][] matrizes= new double[a.length][][];
		matrizes[0]=a;
		System.out.println("matriz[0]:");
		imprime(matrizes[0]);
		System.out.println("\r\nultmo elemento de matriz[0]]:");
		System.out.println(matrizes[0][matrizes[0].length-1][matrizes[0][matrizes[0].length-1].length-2]);
		for(int l =1;l<a.length;l++){
			//cria uma matriz Aj-1,i-1.
			double aux[][]= new double [matrizes[l-1].length-1][matrizes[l-1][0].length-1];
			for(int j=0; j<aux.length;j++){
				for(int i =0; i<aux[j].length;i++){
					//no caso das matrizes 1 < m < j , 1 < n < i porque considera-se a primeira posicao o index 0.
					double det = ( matrizes[l-1][0][0]*matrizes[l-1][j+1][i+1] ) - ( matrizes[l-1][0][i+1]*matrizes[l-1][j+1][0] );
					aux[j][i]=det;
				}
			}
			matrizes[l]=aux;
			System.out.println("\r\n\r\n\r\nmatriz["+l+"]:");
			
			imprime(matrizes[l]);
			System.out.println();
		}
		
		
		/*
			agora temos n matrizes Ai,i+1 ,i = 1,2,3...n . Sendo todas semelhantes(os coeficientes tem o mesmo valor):
			
			|a11  a12  a13  ... a1n|a1,n+1|
			|a21  a21  a23  ... a2n|a2,n+1|
			|:                   : |:     |
			|an1  an2  an3  ... ann|an,n+1|-> 1
			
			|a11  a12  a13  ... a1n|a1,n+1|
			|a21  a21  a23  ... a2n|a2,n+2|
			|:                   : |:     |
			|an-1,1  an-1,2  an-1,3  ... an-1,n-1|an-1,n|-> 2
			:
			|a11  a12|-> n
			
			a partir da matriz n temos então: {a11*kn = a12} => {kn = a12/a11}.
			
			Do mesmo modo na matriz n-1 temos: {a11*kn-1+a12*kn = a13} ou {a21*kn-1+a22*kn = a23} =>
			kn-1 = (a13 - a12*kn)/a11 e kn-1 = (a23 - a22*kn)/a21.
			
			ou seja:
			
			ki = (aj,i+1 - (aj,i*kn + aj,i-1*kn-1 + ... + aj,2))/aj,1
			com j=<n-i+1 e 0<i<n
			
			
		*/
		
		double[] solucao= new double[matrizes.length];
		solucao[0] = matrizes[matrizes.length-1][0][1]/matrizes[matrizes.length-1][0][0];
		System.out.println("solucao[o]:"+solucao[0]);
		for(int i=1;i<matrizes.length;i++){
			int soma = 0;
			for (int j=i;j>=0;j--){
				soma+=matrizes[matrizes.length-1-i][0][j]*solucao[i-j];
			}
			System.out.println("soma:"+soma);
			double aux =(matrizes[matrizes.length-1-i][0][matrizes[matrizes.length-1-i][0].length-1]-soma)/matrizes[matrizes.length-1-i][0][0];
			
			if(Math.pow(300,-i)<Math.abs(aux))
				solucao[i]=aux;
			System.out.println("solucao["+i+"]: "+ solucao[i]);
		}
		imprime(solucao);
		System.out.println();
		for(int i=0;i<solucao.length;i++){
			if(solucao[i]!=0)
				System.out.print(solucao[i]+"x^("+(solucao.length-i)+")+");
		}
		
	}
	public static void main(String[] args) throws FileNotFoundException {
		double k[][] = pegaPonto();
		long start_time = System.currentTimeMillis();
		imprime(k);
		metodoDeGauss();
		long end_time = System.currentTimeMillis();
		long difference = end_time-start_time;
		System.out.println();
		System.out.println(difference);
	}
}
