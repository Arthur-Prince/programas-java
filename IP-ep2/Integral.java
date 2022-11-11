/*******************************************************************/
/** ACH2001 - Introducao a Programacao                            **/
/** EACH-USP - Primeiro Semestre de 2015                          **/
/**                                                               **/
/** Segundo Exercicio-Programa                                    **/
/**                                                               **/
/** <Arthur Prince de Almeida>  <10782990>                                                **/
/**                                                               **/
/*******************************************************************/

class Integral {
	/**
		Retorna a área sob a curva definida pela função f, entre os pontos
		a e b, pela regra dos trapézios, aproximando a curva por n trapézios.
		
		O método retorna -1 caso n não seja maior ou igual a 1.
	*/
	static double resolve(Funcao f, double a, double b, int n) {
		if(n<=0)
			return -1;
		double aux=0;
		
		double areaParcial;
		double k = (b-a)/n;
		for(int i = 0 ; i < n; i++){
			areaParcial = (f.valor(a + i*k) + f.valor(a + (i + 1)*k))*k/2;
			aux += areaParcial;
		}
		return aux;
	}
}
