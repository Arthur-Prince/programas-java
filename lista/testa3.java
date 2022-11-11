import java.util.Scanner;
public class testa{
	public static void imprime(int[][] m){
		for(int i =0;i<m.length;i++){
			for(int j =0;j<m[i].length;j++){
				System.out.print(m[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println();
	}
	public static void imprime (int[] x){
		for(int i=0; i < x.length; i++){
			System.out.print(x[i]+" ");
		}
		System.out.println();
	}
	//converte um inteiro para um arranjo de inteiro
	public static int[] criaVetor(int a,int n){
		int k = 0;
		if(a<0)
			k++;
		Integer seuNumero = a;
		String numeroEmTexto = Integer.toString(seuNumero);
		int[] array = new int[n];
		for (int i = 0; i < numeroEmTexto.length()-k; i++) {
			array[i+n-numeroEmTexto.length()] = Character.getNumericValue(numeroEmTexto.charAt(i));
		}
	}


	//cria um vetor com 23 bits a partir de um double
	//algritmo baseado no algoritmo desse site: https://respostas.guj.com.br/11777-como-converter-um-inteiro-em-um-array
	public static int[] criaVetor(double a){ 
		Double k = a;
		String numeroEmTexto = Double.toString(k);
		int[] array = new int[numeroEmTexto.length()-2];
		for (int i = 2; i < numeroEmTexto.length(); i++) {
			array[i-2] = Character.getNumericValue(numeroEmTexto.charAt(i));
		}
		return array;
	}
	//soma binaria de 2 vetores de mesmo tamanho
	public static int[] soma(int[] a, int[] b){
		int k = a.length;
		int aux;
		int ci=0;
		int[] rtn = new int[k];
		for(int i =0; i <k; i++){
			aux = a[k-i-1]+b[k-i-1]+ci;
			if(aux == 0){
				ci=0;
				rtn[k-i-1]=0;
			}
			if(aux == 1){
				ci=0;
				rtn[k-i-1]=1;
			}
			if(aux == 2){
				ci=1;
				rtn[k-i-1]=0;
			}
			if(aux == 3){
				ci=1;
				rtn[k-i-1]=1;
			}

		}
		return rtn;
	}
	// cria um vetor com complemento de 2 do vetor "a"
	public static int[] criaVetorInverso(int[] a){
		int[] b = new int[a.length];
		int[] aux = new int[a.length];
		b[a.length-1]=1;
		for(int i = 0; i < a.length;i++){ // faz o vetor invertido(troca 1 por 0 e 0 por 1)
			if(a[i]==1)
				aux[i]=0;
			if(a[i]==0)
				aux[i]=1;
		}

		int[] rtn = soma(aux,b); // soma 1 no vetor invertido

		return rtn;
	}
	// faz matriz 3 x n(2*NumeroDeBit+1) como os vetores a e b na posicao de acordo com o algoritmo de booth
	public static int[][] fazMatrizASP(int[] c,int[] b,int n){ 
		
		int[][] m = new int[3][2*n+1];
		//cria um vetor auxiliar de tamanho n para dazer o complento de 2 e para coloca-lo na posicao correta
		int[] a = new int[n];
		for(int i = 0;i<c.length;i++)
			a[a.length-i-1]=c[c.length-i-1];
		int[] invA = criaVetorInverso(a);
		//coloca os vetores "a" e "invA" na posicao correta
		for(int i=0;i<a.length;i++){
			m[0][i+n-a.length] = a[i];
			m[1][i+n-invA.length] = invA[i];
		}
		//coloca o vetor "b" na posicao correta
		for(int i=0;i<b.length;i++){
			m[2][i+2*n-b.length] = b[i];
		}
		return m;
		
	}
	//faz a soma de 2 vetores na linha "a" e "b" e coloca na 3 linha da matriz asp
	public static void fazSoma(int a,int b,int[][] m ){ 
		int[]aux = soma(m[a],m[b]);
		for(int i=0;i<m[a].length;i++){
			m[a][i]=aux[i];
		}
	}
	
	public static void desloca(int[] d){// deloca o vetor para a direita excluindo o ultimo numero e mantem o sinal
		for(int i=d.length-1;i>0;i--){
			d[i] = d[i-1];		//exclui o ultimo numero e coloca o numero na direita
		}
	}
	
	//multiplica 2 numeros em binario com "n" bit de acordo com o algoritmo de booth
	public static int[] multiplicacaoDeBooth(int[] k, int[] m,int n){ 
		int A = 0;
		int S = 1;
		int P = 2;
		int[][] ASP = fazMatrizASP(k,m,n);
		for(int i=1;i<=n;i++){	//faz as modificações no ASP de acordo com o algoritmo de booth
			if(ASP[P][2*n-1]==1 && ASP[P][2*n]==0){	// soma P e S e coloca o resultuado em P
				fazSoma(P,S,ASP);
			}
			if(ASP[P][2*n-1]==0 && ASP[P][2*n]==1){	// soma P e A e coloca o resultuado em P
				fazSoma(P,A,ASP);
			}
			//desloca o numero para a direita
			desloca(ASP[P]);
		}
		//faz contagem de quantos bits o numero tem e retira os 0 a esquerda e o ultimo bits da matriz na linha P
		int contador;
		for(contador=0;ASP[2][contador]==0&&contador<ASP[2].length-1;contador++){}
		int[] retorno = new int[ASP[2].length-contador-1];
		for(int i =0;i<retorno.length;i++)
			retorno[i]=ASP[2][contador+i];
		return retorno;
	}
	//esse metodo auxiliar pega um numero inteiro e converte para binario
	//esse metodo so e usado para auxiliar o expoente portanto cria um arranjo com 8 bits
	 public static int[] converteBin(int numero){
        int d = numero;
        int b;
		int[] vetor = new int[8];
		int i =0;
        while ( d > 0){
            b = d % 2;
			vetor[7-i]=b;
			
            d -= (d/2)+b;
			i++;
        }
		return vetor;
    }
	//esse metodo axiliar faz a soma de 2 expoentes de 8 bits e soma o resultado com  numero 10000001 para ajustar o sinal e a posicao da virgula
	public static int[] somaDeExp(int[] a, int[] b){
		//faz o numero 10000001
		int [] aux = new int[8];
		aux[0]=1;
		aux[7]=1;
		
		int[] s = soma(a,aux);
		aux = soma(b,s);
		return aux;
	}
	
	//recebe como parametro 2 double em binario com apenas um "0" a esqueda da vigula(por exemplo:0,11 ; 0,101 ; 0,11001...)
	//e o expoente dos respectivos numeros(em base 10), ou seja, se passar como parametro(0,11 , 0,101 , 3 , 2) isso significa:
	//0,11*2^11 e 0,101*2^10. E essa funcao multiplica esses 2 numeros
	public static int[] multiplicacaoComPFlutuante(double a, double b,int expoenteA, int expoenteB){
		int[] va = new int[32];
		int[] vb = new int[32];
		int[] retorno = new int[32];
		int[] expA = converteBin(127+expoenteA);
		int[] expB = converteBin(127+expoenteB);
		if(a<0){														//coloca o bit de sinal no vetor va
			va[0]=1;
			a=-a;
		}
		if(b<0){														//coloca o bit de sinal no vetor vb
			vb[0]=1;
			b=-b;
		}
		
		
		//cria o arranjo com os bits de a e b
		int[] vetorA = criaVetor(a);
		int[] vetorB = criaVetor(b);
		//aqui faz a multiplicacao e soma o numero de bits de cada vetor(logo sera adicionado no expoente) e transforma em binario
		int adicaoNoExp=-(vetorA.length+vetorB.length);
		int[] aux = multiplicacaoDeBooth(vetorA,vetorB,23);
		adicaoNoExp += aux.length;
		int[] vetorBin=converteBin(127+adicaoNoExp);
		//coloca esses arranjos de bits em sua posicao no significante do vetor principal
		for(int i=0; i<23;i++){
			if(vetorA.length>i)
				va[9+i]=vetorA[i];
			if(vetorB.length>i)
				vb[9+i]=vetorB[i];
		}
		//coloca o expoente no expoente do vetor princial
		for(int i=0; i<8;i++){
			va[8-i]=expA[7-i];
			vb[8-i]=expB[7-i];
		}
		//aqui faz a soma dos expoentes considerando o resultado da multiplicacao
		int[] auxExp = somaDeExp(expA,expB);
		auxExp = soma(auxExp,vetorBin);
		//aqui arruma o sinal da multiplicacao
		if((va[0]==0&&vb[0]==0)||(va[0]==1&&vb[0]==1))
			retorno[0]=0;
		if((va[0]==0&&vb[0]==1)||(va[0]==1&&vb[0]==0))
			retorno[0]=1;
		//e por fim coloca os resultados no vetor retorno
		for(int i = 0;i<aux.length;i++)
			retorno[9+i]=aux[i];
		
		for(int i = 0;i<8;i++)
			retorno[1+i]=auxExp[i];
		
		
		return retorno;
	}
	//recebe 2 numeros positivos e seus respectivos expoentes e soma eles
	public static int[] somaComFlutuante(int[]a,int[] b,int exA, int exB){
		int[] rtn = new int[32];
		//conta quantas vezes o numero vai deslocar
		int expAux=exB-exA;
		int expBux=exA-exB;
		int[] vetorA = new int[24];
		int[] vetorB = new int[24];
		//faz com que sempre o numero se desloque para direita 
		if(exA>exB)
			exB=exA;
		else
			exA=exB;
		//coloca o numero no vetor q sera feito a soma
		for(int i = 1; i<=a.length&&i<23;i++)
			vetorA[i]=a[i-1];
		for(int i = 1; i<=b.length&&i<23;i++)
			vetorB[i]=b[i-1];
		
		//desloca o menor numero para a direita
		for(int i = 0; i<expAux&&i!=23;i++)
			desloca(vetorA);
		for(int i = 0; i<expBux&&i!=23;i++)
			desloca(vetorB);
		//faz a soma
		int[] rtnAux=soma(vetorA,vetorB);
		//incrementa o expoente caso os 2 numeros tenham o mesmo numero de bits
		exA+=rtnAux[0];
		//faz o vetor que sera retornado retorno
		int[] auxExp = converteBin(127+exA);
		for(int i = 1;i<rtnAux.length;i++)
			rtn[8+i]=rtnAux[i];
		
		for(int i = 0;i<8;i++)
			rtn[1+i]=auxExp[i];
		rtn[0]=0;
		return rtn;
	}
	
	//aqui começa divisão

  public static void imprimeSemSinal (int[] x){
		for(int i=0; i < x.length; i++){
			System.out.print(x[i]+" ");
		}
		System.out.println();
	}

  public static void divisaoInteiros(int [] Q, int [] M, int sinalQ, int sinalM){
    int[] invDiv = new int[M.length];
    int a[] = {0,0,0,0,0,0,0,0};
    int sinal = defineSinal(sinalQ, sinalM);
    for(int i = 0; i < 8;i++){
      shiftLeft(a,Q);
      invDiv = criaVetorInverso(M);
      a = soma(a,invDiv);
      int test = testaSinal(a);
      if(test == 0){//positivo
        switchFirstBit(Q,1);
      }else{
        a = soma(M,a);
        switchFirstBit(Q,0);
      }
    }
    System.out.println("resultado (quociente): ");
    System.out.print(sinal + " || ");
    imprimeSemSinal(Q);
    System.out.println("resto: ");
    imprimeSemSinal(a);
  }

  static int defineSinal(int a, int b){
    if(a+b == 0) return 0;
    if(a+b == 1) return 1;
    if(a+b == 2) return 0;
    else return 0;
  }

  static void shiftLeft(int[] a, int[] q){
    for(int i = 0; i < a.length-1;i++){
      a[i] = a[i+1];
    }
    a[a.length-1] = q[0];
    for(int i = 0; i < q.length-1;i++){
      q[i] = q[i+1];
    }
    q[q.length-1] = 0;
  }

  static int testaSinal(int [] a){
    return a[0];
  }

  static void switchFirstBit(int[]q,int b){
    q[q.length-1] = b;
  }

  public static void divisaoFloat(double dd, double dv, int[] expDd, int[] expDv){
    //se o dividendo for 0, a resposta é 0
    if(dd == 0){
      System.out.println("0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0");
      return;
    }
    //se o divisor for 0, a resposta é inválida
    if(dv == 0){
      System.out.println("Divisão por 0 inválida");
      return;
    }
    double dividido = dd/dv;
    int[] nbin = criaVetorDiv(dividido);
    //subtração do resultado dos expoentes
    int[] inversa = new int[expDd.length];
    int [] resultadoExpoente = soma(expDd, expDv);
    //testaOverflow(resultadoExpoente);
    alocaExpoente(nbin,resultadoExpoente);
    System.out.println("Resultado: ");
    imprime(nbin);
  }

  //cria vetor resultado da divisão dos bits (deixa espaço pro expoente)
  static int [] criaVetorDiv(double n){
    int[] v = criaVetorMant(n);
    int[] vet = new int[32];
    vet[0] = (int)n;
    for(int i = 9; i<32 ;i++){
      vet[i] = v[i-9];
    }
    return vet;
  }

  //cria um vetor de 23 bits da mantissa (baseado no criaVetor())
  static int[] criaVetorMant(double a){ 
		Double k = a;
		String numeroEmTexto = Double.toString(k);
		int[] array = new int[23];
		for (int i = 2; i < numeroEmTexto.length(); i++) {
			array[i-2] = Math.abs((Character.getNumericValue(numeroEmTexto.charAt(i))%2));
		}
    for(int i = numeroEmTexto.length(); i < 23; i++){
      array[i] = 0;
    }
		return array;
	}
  //aloca expoente no espaço correto do vetor resultado
  static void alocaExpoente(int [] a, int [] b){
    for(int i = 1;i < 9;i++){
      a[i] = b[i-1];
    }
  }

  public static void main(String[] args) {
  
  Scanner ler = new Scanner(System.in);
  System.out.println();
  System.out.printf("Digite 0 para soma, 1 para subtração, 2 para multiplicação, 3 para divisão inteira, 4 para divisão float, \n5 para multiplicação float ou 6 para multiplicação float");
  int digito = ler.nextInt();
  /*Soma*/
  if(digito == 0){
	System.out.println();
    System.out.println("SOMA DE INTEIROS");
    System.out.println();
    System.out.printf("Digite a primeira parcela em binário \n");
	System.out.println();
    int a = ler.nextInt();
	System.out.println();
    System.out.printf("Digite a segunda parcela em binário \n");
	System.out.println();
    int b = ler.nextInt();
	System.out.printf("Digite o numero de bits em base 10 \n");
	System.out.println();
    int n = ler.nextInt();
	int[] va = criaVetor(a,n);
	int[] vb = criaVetor(b,n);
	System.out.printf("resposta: \n");
	imprime(soma(va,vb));
  }
  /*Subtração*/
  if(digito == 1){
	System.out.println();
    System.out.println("SUBTRÇÃO DE INTEIROS");
    System.out.println();
    System.out.printf("Digite o minuendo em binário \n");
	System.out.println();
    int a = ler.nextInt();
	System.out.println();
    System.out.printf("Digite o subtraendo em binário(sem sinal) \n");
	System.out.println();
    int b = ler.nextInt();
	System.out.printf("Digite o numero de bits em base 10 \n");
	System.out.println();
    int n = ler.nextInt();
	n++;
	int[] va = criaVetor(a,n);
	int[] vb = criaVetor(b,n);
	vb=criaVetorInverso(vb);
	System.out.printf("resposta: \n");
	imprime(soma(va,vb));
  }
  /*Multiplicação*/
  if(digito == 2){
	System.out.println();
    System.out.println("MULTIPLICAÇÃO DE INTEIROS");
    System.out.println();
    System.out.printf("Digite o multiplicando em binário \n");
	System.out.println();
    int a = ler.nextInt();
	System.out.println();
    System.out.printf("Digite o multiplicador em binário \n");
	System.out.println();
    int b = ler.nextInt();
	System.out.printf("Digite o numero de bits em base 10 \n");
	System.out.println();
    int n = ler.nextInt();
	int[] va = criaVetor(a,n);
	int[] vb = criaVetor(b,n);
	if(a<0)
		va=criaVetorInverso(va);
	if(b<0)
		vb=criaVetorInverso(vb);
	System.out.printf("resposta:\n");
    imprime(multiplicacaoDeBooth(va,vb,n));
  }
  /*Divisão de inteiros*/
  if(digito == 3){
    System.out.println();
    System.out.println("DIVISÃO DE INTEIROS");
    System.out.println("formato: (dividendo/divisor = quociente + resto)");
    System.out.println();
    System.out.printf("Digite o bit de sinal do DIVIDENDO (0 ou 1): \n");
    int bq = ler.nextInt();
    if(bq != 0 && bq != 1){
      System.out.println("BIT INVALIDO (sinal sera 0)");
    }
    System.out.printf("Digite o valor do DIVIDENDO em decimal: (ex: 27)\n");
    int q = ler.nextInt();
    System.out.printf("Digite o bit de sinal do DIVISOR (0 ou 1): \n");
    int bm = ler.nextInt();
    if(bm != 0 && bm != 1){
      System.out.println("BIT INVALIDO");
    }
    System.out.printf("Digite o valor do DIVISOR em decimal: (ex: 3) \n");
    int m = ler.nextInt();
    //aqui começam as chamadas de funções da divisão
    int[] Q = b.converteBin(q);
    int[] M = b.converteBin(m);
    b.divisaoInteiros(Q,M,bq,bm);
  }
  /*Divisão de pontos flutuantes*/
  if(digito == 4){
    System.out.println();
    System.out.println("DIVISÃO DE FLOAT");
    System.out.println();
    System.out.printf("Digite o valor do DIVIDENDO em binario no formato 1.xxxxxx: (ex: 1.0101)\n");
    double d1 = ler.nextDouble();
    System.out.printf("Digite o valor do expoente do dividendo em decimal: (ex: 3) \n");
    int e1 = ler.nextInt();
    int[] exp1 = b.converteBin(e1);
    System.out.printf("Digite o valor do DIVISOR em binario no formato 1.xxxxxx: (ex: 1.1) \n");
    double d2 = ler.nextDouble();
    System.out.printf("Digite o valor do expoente do divisor em decimal: (ex: 2) \n");
    int e2 = ler.nextInt();
    int[] exp2 = b.converteBin(e2);
    b.divisaoFloat(d1,d2,exp1,exp2);
  }
  /*Multiplicacao de pontos flutuantes*/
  if(digito==5){
	System.out.println();
    System.out.println("MULTIPLICAÇÃO DE FLOAT");
    System.out.println();
    System.out.printf("Digite o multiplicando em binário na forma 0.1XXXX... ou -0.1YYYY... \n");
	System.out.println();
    double a = ler.nextDouble();
	System.out.println();
    System.out.printf("Digite o multiplicador em binário na forma 0.1XXXX... ou -0.1YYYY... \n");
	System.out.println();
    double b = ler.nextDouble();
	System.out.printf("Digite o expoente do multiplicando em base10(representa 2^expoente)\n");
	System.out.println();
    int exa = ler.nextInt();
	System.out.printf("Digite o expoente do multiplicador em base10(representa 2^expoente)\n");
	System.out.println();
    int exb = ler.nextInt();
	System.out.printf("resposta: \n");
	imprime(multiplicacaoComPFlutuante(a,b,exa,exb));
  }
  /*adicao de pontos flutuantes*/
  if(digito==6){
	System.out.println();
    System.out.println("SOMA DE FLOAT");
    System.out.println();
    System.out.printf("Digite a primeira parcela em binário na forma 0.1XXXX... \n");
	System.out.println();
    double a = ler.nextInt();
	System.out.println();
    System.out.printf("Digite a segunda parcela em binário na forma 0.1XXXX... \n");
	System.out.println();
    double b = ler.nextInt();
	System.out.printf("Digite o expoente da primeira parcela em base10(representa 2^expoente)\n");
	System.out.println();
    int exa = ler.nextInt();
	System.out.printf("Digite o expoente da segunda parcela em base10(representa 2^expoente)\n");
	System.out.println();
    int exb = ler.nextInt();
	int[] va = criaVetor(a);
	int[] vb = criaVetor(b);
	System.out.printf("resposta: \n");
	imprime(somaComFlutuante(va,vb,exa,exb));
  }
  if(digito < 0||digito > 6){
    System.out.println("Entrada inválida!");
  }
  }
}