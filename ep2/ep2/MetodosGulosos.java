package ep2;

/*********************************************************************/
/** ACH 2002 - Introducao a Ciencia da Computacao II                **/
/** EACH-USP - Segundo Semestre de 2010                             **/
/**                                                                 **/
/** <turma> - <nome do professor>                                   **/
/**                                                                 **/
/** Terceiro Exercicio-Programa                                     **/
/**                                                                 **/
/** <nome do(a) aluno(a)> <numero USP>                              **/
/**                                                                 **/
/*********************************************************************/


/** 
	COMENTARIOS GERAIS
	
	Seguindo os criterios de selecao, um objeto só poderá ser colocado na 
	mochila caso ela suporte o total de peso.
	
	O total de peso ao se colocar um objeto (do tipo Objeto) é dado por 
	mochila.getPesoUsado() + objeto.getPeso()
	
	Colocar um objeto na mochila significa alterar os seguintes campos da mochila:
	
	pesoUsado,
	
	valorDentroDaMochila, e 
	
	numObjetosNaMochila.
*/
public abstract class MetodosGulosos {

	/**
		Este método deve implementar um algoritmo guloso que selecione objetos
		da listaDeObjetosDisponiveis a serem colocados na mochila, de acordo
		com o critério 'objetos de menor peso primeiro'. Caso dois objetos
		tenham o mesmo peso, o critério de desempate será 'objetos de maior 
		valor primeiro' (apenas para os empates em peso).
		
		@param pesoMaximoDaMochila Peso máximo suportado pela mochila
		@param listaDeObjetosDisponiveis Arranjo de objetos considerados no problema
		
		@return Mochila carregada conforme essa estratégia
	 */
	public static Mochila utilizaMenorPeso(double pesoMaximoDaMochila, Objeto[] listaDeObjetosDisponiveis) {

		Mochila mochila = new Mochila(pesoMaximoDaMochila);
		mochila.setPesoUsado(0.00);
		mochila.setValorDentroDaMochila(0.00);
		mochila.setNumObjetosNaMochila(0);
		int objdisp = listaDeObjetosDisponiveis.length; 
		while (true){
			int primeirodisp = 0;
			if (listaDeObjetosDisponiveis[primeirodisp] == null){
				while (listaDeObjetosDisponiveis[primeirodisp] == null) primeirodisp++;
			}
			Objeto leve = listaDeObjetosDisponiveis[primeirodisp];
			int i;
			if (primeirodisp < listaDeObjetosDisponiveis.length - 1)	i = primeirodisp + 1;
			else	i = primeirodisp; 
			int pos = i;
			for(; i<listaDeObjetosDisponiveis.length ; i++){
				if ((listaDeObjetosDisponiveis[i] != null) && (listaDeObjetosDisponiveis[i].getPeso() <= leve.getPeso())){
					if (listaDeObjetosDisponiveis[i].getPeso() < leve.getPeso()){
						pos = i;
						leve = listaDeObjetosDisponiveis[i];
					}
					else {
						if (listaDeObjetosDisponiveis[i].getValor() > leve.getValor()){
							pos = i;
							leve = listaDeObjetosDisponiveis[i];
						}
					}
				}	
			}
			if ((mochila.getPesoUsado() + leve.getPeso()) <= pesoMaximoDaMochila){
				listaDeObjetosDisponiveis[pos] = null;
				objdisp--;
				mochila.setPesoUsado(mochila.getPesoUsado() + leve.getPeso());
				mochila.setValorDentroDaMochila(mochila.getValorDentroDaMochila() + leve.getValor());
				mochila.setNumObjetosNaMochila(mochila.getNumObjetosNaMochila() + 1);
			}
			else break;
			if (objdisp < 1) break;
		}

		 return mochila;
	}

	
	/** 
		Este método deve implementar um algoritmo guloso que selecione objetos
		da listaDeObjetosDisponiveis a serem colocados na mochila, de acordo
		com o critério 'objetos de maior valor primeiro'. Caso dois objetos
		tenham o mesmo valor, o critério de desempate sera 'objetos de menor peso
		primeiro' (apenas para os empates em valor).
		
		@param pesoMaximoDaMochila Peso máximo suportado pela mochila
		@param listaDeObjetosDisponiveis Arranjo de objetos considerados no problema
		
		@return Mochila carregada conforme essa estratégia
	 */
	public static Mochila utilizaMaiorValor(double pesoMaximoDaMochila,	Objeto[] listaDeObjetosDisponiveis) {
		Mochila mochila = new Mochila(pesoMaximoDaMochila);
		mochila.setPesoUsado(0.00);
		mochila.setValorDentroDaMochila(0.00);
		mochila.setNumObjetosNaMochila(0);
		int objdisp = listaDeObjetosDisponiveis.length; 
		while (true){
			int primeirodisp = 0;
			if (listaDeObjetosDisponiveis[primeirodisp] == null){
				while (listaDeObjetosDisponiveis[primeirodisp] == null) primeirodisp++;
			}
			Objeto valioso = listaDeObjetosDisponiveis[primeirodisp];
			int i;
			if (primeirodisp < listaDeObjetosDisponiveis.length - 1)	i = primeirodisp + 1;
			else	i = primeirodisp; 
			int pos = i;
			for(; i<listaDeObjetosDisponiveis.length ; i++){
				if ((listaDeObjetosDisponiveis[i] != null) && (listaDeObjetosDisponiveis[i].getValor() >= valioso.getValor())){
					if (listaDeObjetosDisponiveis[i].getPeso() < valioso.getPeso()){
						pos = i;
						valioso = listaDeObjetosDisponiveis[i];
					}
					else {
						if (listaDeObjetosDisponiveis[i].getValor() > valioso.getValor()){
							pos = i;
							valioso = listaDeObjetosDisponiveis[i];
						}
					}
				}	
			}
			if ((mochila.getPesoUsado() + valioso.getPeso()) <= pesoMaximoDaMochila){
				listaDeObjetosDisponiveis[pos] = null;
				objdisp--;
				mochila.setPesoUsado(mochila.getPesoUsado() + valioso.getPeso());
				mochila.setValorDentroDaMochila(mochila.getValorDentroDaMochila() + valioso.getValor());
				mochila.setNumObjetosNaMochila(mochila.getNumObjetosNaMochila() + 1);
			}
			else break;
			if (objdisp < 1) break;
		}
		return mochila;
	}

	
	/**
		Este método deve implementar um algoritmo guloso que selecione objetos
		da listaDeObjetosDisponiveis a serem colocados na mochila, de acordo
		com o critério 'objetos de maior valor/peso primeiro (valor dividido por
		peso primeiro)'. Caso dois objetos tenham o mesmo valor/peso, o critério
		de desempate sera 'objetos de maior peso primeiro' (apenas para os empates).
		
		@param pesoMaximoDaMochila Peso máximo suportado pela mochila
		@param listaDeObjetosDisponiveis Arranjo de objetos considerados no problema
		
		@return Mochila carregada conforme essa estratégia
	 */
	public static Mochila utilizaMaiorValorDivididoPorPeso(double pesoMaximoDaMochila, Objeto[] listaDeObjetosDisponiveis) {
		Mochila mochila = new Mochila(pesoMaximoDaMochila);
		mochila.setPesoUsado(0.00);
		mochila.setValorDentroDaMochila(0.00);
		mochila.setNumObjetosNaMochila(0);
		int objdisp = listaDeObjetosDisponiveis.length; 
		while (true){
			int primeirodisp = 0;
			if (listaDeObjetosDisponiveis[primeirodisp] == null){
			while (listaDeObjetosDisponiveis[primeirodisp] == null) primeirodisp++;
			}
			Objeto valpes = listaDeObjetosDisponiveis[primeirodisp];
			int i;
			if (primeirodisp < listaDeObjetosDisponiveis.length - 1)	i = primeirodisp + 1;
			else	i = primeirodisp; 
			int pos = i;
			for(; i<listaDeObjetosDisponiveis.length ; i++){
				if ((listaDeObjetosDisponiveis[i] != null) && ((listaDeObjetosDisponiveis[i].getValor()/listaDeObjetosDisponiveis[i].getPeso()) >= (valpes.getValor()/valpes.getPeso()))){
					if (listaDeObjetosDisponiveis[i].getPeso() < valpes.getPeso()){
						pos = i;
						valpes = listaDeObjetosDisponiveis[i];
					}
					else {
						if (listaDeObjetosDisponiveis[i].getValor() > valpes.getValor()){
							pos = i;
							valpes = listaDeObjetosDisponiveis[i];
						}
					}
				}
			}
			if ((mochila.getPesoUsado() + valpes.getPeso()) <= pesoMaximoDaMochila){
				listaDeObjetosDisponiveis[pos] = null;
				objdisp--;
				mochila.setPesoUsado(mochila.getPesoUsado() + valpes.getPeso());
				mochila.setValorDentroDaMochila(mochila.getValorDentroDaMochila() + valpes.getValor());
				mochila.setNumObjetosNaMochila(mochila.getNumObjetosNaMochila() + 1);
			}
			else break;
			if (objdisp < 1) break;
		}
		return mochila;
	}

	
}
