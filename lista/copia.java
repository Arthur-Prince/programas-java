public class copia{
	public void imprime(int[][] m){
		for(int i =0;i<m.length;i++){
			for(int j =0;j<m[i].length;j++){
				System.out.print(m[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println();
	}
	public void imprime (int[] x){
		for(int i=0; i < x.length; i++){
			System.out.print(x[i]+" ");
		}
		System.out.println();
	}
	/*
	public void fazVetor(float a,float b, int[] va, int[] vb){
		if(a<0)														//coloca o bit de sinal no vetor va
			va[0]=1;
		if(b<0)														//coloca o bit de sinal no vetor vb
			vb[0]=1;
		
		
		int na= tamanho(a);
		int nb= tamanho(b);
		if(na>23||nb>23)
			return;
		
		//converte float para int
		Float c = a*Math.pow(10,na);
		Float d = b*Math.pow(10,nb);
		int auxA=c.intvalue();
		int auxB=d.intvalue();
		//e coloca esse numero em um vetor
		int[] vetorA = criaVetor(auxA,na);
		int[] vetorB = criaVetor(auxB,nb);
		for(int i = 1;i<=na;i++){
			va[32-i]=vetorA[na-i];
		}
		for(int i = 1;i<=nb;i++){
			vb[32-i]=vetorB[nb-i];
		}
		int[] expA = bin(na);
		int[] expB = bin(nb);
	}
	
	*/
	
	//cria um array a patir de um inteiro "a" e o numero de bits que sera usado "n"
	//algritmo baseado nesse site: https://respostas.guj.com.br/11777-como-converter-um-inteiro-em-um-array
	public int[] criaVetor(int a,int n){ 
		Integer b = a;
		String numero = Integer.toString(b); //cria uma string do numero
		int[] array = new int[n];
		for (int i = 0; i < numero.length()&& i < n; i++) {
			array[n+i-numero.length()] = Character.getNumericValue(numero.charAt(i)); //coloca o numero no array
		}
		return array;
	}
	//cria um array com 23 bits a partir de um float
	public int[] criaVetor(float a){ 
		Float k = a;
		String numeroEmTexto = Float.toString(k);
		int[] array = new int[numeroEmTexto.length-2];
		for (int i = 2; i < numeroEmTexto.length(); i++) {
			array[i-2] = Character.getNumericValue(numeroEmTexto.charAt(i));
		}
	}
	//soma binaria de 2 vetores de mesmo tamanho
	public int[] soma(int[] a, int[] b){
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
	public int[] criaVetorInverso(int[] a){ 
		int[] b = new int[a.length];
		int[] aux = new int[a.length];
		b[a.length-1]=1;
		for(int i = 0; i < a.length;i++){ // faz o vetor invertido(troca 1 por 0 e 0 por 1)
			if(a[i]==1)
				aux[i]=0;
			if(a[i]==0)
				aux[i]=1;
		}
		imprime(a);
		imprime(aux);
		imprime(b);
		int[] rtn = soma(aux,b); // soma 1 no vetor invertido
		imprime(rtn);
		return rtn;
	}
	// faz matriz 3 x n(2*NumeroDeBit+1) como os vetores a e b na posicao de acordo com o algoritmo de booth
	public int[][] fazMatrizASP(int[] a,int[] b,int n){ 
		int[][] m = new int[3][2*n+1];
		int[] invA = criaVetorInverso(sa);
		
		for(int i=0;i<a.length;i++){
			m[0][i+n-a.length] = a[i];
			m[1][i+n-invA.length] = invA[i];
		}
		for(int i=0;i<b.length;i++){
			m[2][i+2*n-b.length] = b[i];
		}
		return m;
		
	}
	//faz a soma de 2 vetores na linha "a" e "b" e coloca na 3 linha da matriz asp
	public void fazSoma(int a,int b,int[][] m ){ 
		int[]aux = soma(m[a],m[b]);
		for(int i=0;i<m[a].length;i++){
			m[a][i]=aux[i];
		}
	}
	public int retorno(int[][]m,int n){ // transforma um array em um inteiro
		int k = m[2].length-1;
		int aux = 0;
		for(int i=0;i<k;i++){
			aux += m[2][i]*Math.pow(10,k-i-1);
		}
		for(int k=0;k<2*n;k++){
			if(m[2][k]==1){
				n=k;
				break;
			}
		}
		return aux;
	}
	public void desloca(int[] d){// deloca o vetor para a direita excluindo o ultimo numero e mantem o sinal
		for(int i=d.length-1;i>0;i--){
			d[i] = d[i-1];		//exclui o ultimo numero e coloca o numero na direita
		}
	}
	
	//multiplica 2 numeros em binario com "n" bit de acordo com o algoritmo de booth
	public int[] multiplicacaoDeBooth(int[] k, int[] m,int n){ 
		int A = 0;
		int S = 1;
		int P = 2;
		int[][] ASP = fazMatrizASP(k,m,n);
		imprime(ASP);
		for(int i=1;i<=n;i++){	//faz as modificações no ASP de acordo com o algoritmo de booth
			if(ASP[P][2*n-1]==1 && ASP[P][2*n]==0){	// soma P e S e coloca o resultuado em P
				fazSoma(P,S,ASP);
			}
			if(ASP[P][2*n-1]==0 && ASP[P][2*n]==1){	// soma P e A e coloca o resultuado em P
				fazSoma(P,A,ASP);
			}
			
			desloca(ASP[P]);
		}
		//na funcao retorno o n sera modificado para guardar quantos 0 a esquerda tem(util para ponto flutuante)
		return ASP[2];
	}
	//baseado em https://www.guj.com.br/t/transforma-decimal-em-binario/47061
	 public int[] converteBin(int numero){
        int d = numero;
        int b;
		int contador;
		for(contador =0;Math.pow(2,contador)<numero||contador>8;contador++){}
		int[] vetor = new int[8];
		int i =0;
        while ( d > 1){
            b = d % 2;
			vetor[8-contador+i]=b;
            d -= d / 2 ;
        }
		return vetor;
    }
	
	public void alinha(int[] l){
		int i=0;
		while(l[10+i]==0)
			i++;
		int[] bin = converteBin(127-i);
		int[] aux = new int[8];
		for(int i=0;i<8;i++){
			aux[i]=l[i+1];
		}
		int[] rtn = somaDeExp(aux,bin);
		for(int i=0;i<8;i++){
			l[i+1]=rtn[i];
		}
	}
	
	public int[] somaDeExp(int[] a, int[] b){
		int [] aux = new int[8];
		aux[0]=1;
		aux[7]=1;
		aux = soma(a,aux);
		aux = soma(b,aux);
		return aux;
	}
	
	//recebe como parametro 2 float em binario com apenas um "0" a esqueda da vigula(por exemplo:0,11 ; 0,101 ; 0,11001...)
	//e o expoente dos respectivos numeros(em base 10), ou seja, se passar como parametro(0,11 , 0,101 , 3 , 2) isso significa:
	//0,11*2^11 e 0,101*2^10. E essa funcao multiplica esses 2 numeros
	public float multiplicacaoComPFlutuante(float a, float b,int exA, int exB){
		int[] va = new int[32];
		int[] vb = new int[32];
		int[] retorno = new int[32];
		int[] expA = converteBin(126+exA);
		int[] expB = converteBin(126+exB);
		if(a<0)														//coloca o bit de sinal no vetor va
			va[0]=1;
		if(b<0)														//coloca o bit de sinal no vetor vb
			vb[0]=1;
		
		//cria o arranjo com os bits de a e b(todos os 23)
		int[] vetorA = criaVetor(a);
		int[] vetorB = criaVetor(b);
		//coloca esses arranjos de bits em sua posicao no vetor principal
		for(int i=0; i<23;i++){
			if(vetorA.length>i)
				va[10+i]=vetorA[i];
			if(vetorB.length>i)
				vb[10+i]=vetorB[i];
		}
		
		for(int i=0; i<8;i++){
			va[8-i]=expA[7-i];
			vb[8-i]=expB[7-i];
		}
		alinha(va);
		alinha(vb);
		
		int[] aux = multiplicacaoDeBooth(vetorA,vetorB,23);
		int[] auxExp = somaDeExp(expA,expB);
		if((va[0]==0&&vb[0]==0)||(va[0]==1&&vb[0]==1))
			retorno[0]=0;
		if((va[0]==0&&vb[0]==1)||(va[0]==1&&vb[0]==0))
			retorno[0]=1;
		for(int i = 0;i<23;i++)
			retorno[10+i]=aux[i];
		
		for(int i = 0;i<8;i++)
			retorno[1+i]=aux[i];
		
		alinha(retorno);
		
		return retorno;
		}
		
		
	}

}