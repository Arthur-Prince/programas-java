import java.awt.*;

/**
	Esta classe representa a bola usada no jogo. A classe princial do jogo (Pong)
	instancia um objeto deste tipo quando a execução é iniciada.
*/

public class Ball {
	//contem o eixo x de onde a bola nasce
	private double CentroX;
	//contem o eixo y de onde a bola nasce
	private double CentroY;
	//contem a posicao da bola no eixo x
	private double cx;
	//contem a posicao da bola no eixo y
	private double cy;
	//contem a largura da bola
	private double width;
	//contem a altura da bola
	private double height;
	//contem a cor da bola
	private Color color;
	//contem a velocidade da bola
	private double speed;
	//contem a velocidade que a bola anda no eixo x
	private double sentidoX;
	//contem a velocidade que a bola anda no eixo y
	private double sentidoY;
	

	/**
		Construtor da classe Ball. Observe que quem invoca o construtor desta classe define a velocidade da bola 
		(em pixels por millisegundo), mas não define a direção deste movimento. A direção do movimento é determinada 
		aleatóriamente pelo construtor.

		@param cx coordenada x da posição inicial da bola (centro do retangulo que a representa).
		@param cy coordenada y da posição inicial da bola (centro do retangulo que a representa).
		@param width largura do retangulo que representa a bola.
		@param height altura do retangulo que representa a bola.
		@param color cor da bola.
		@param speed velocidade da bola (em pixels por millisegundo).
	*/

	public Ball(double cx, double cy, double width, double height, Color color, double speed){
		//carrega as variaveis da classe
		this.cx = cx;
		this.cy = cy;
		this.CentroX = cx;
		this.CentroY = cy;
		this.width = width;
		this.height = height;
		this.color = color;
		this.speed = speed;
		defineDirecao();
	
	}


	/**
		Método chamado sempre que a bola precisa ser (re)desenhada.
	*/

	public void draw(){
		GameLib.setColor(this.color);
		GameLib.fillRect(this.cx, this.cy, this.width, this.height);
	}

	/**
		Método chamado quando o estado (posição) da bola precisa ser atualizado.
		
		@param delta quantidade de millisegundos que se passou entre o ciclo anterior de atualização do jogo e o atual.
	*/

	public void update(long delta){
		//a posicao da bola e definida de acordo com sua velocidade vezes o tempo somado a posicao anterior(considerando o sentido)
		this.cx = this.cx + this.speed * this.sentidoX * delta;
		this.cy = this.cy + this.speed * this.sentidoY * delta;

	}

	/**
		Método chamado quando detecta-se uma colisão da bola com um jogador.
	
		@param playerId uma string cujo conteúdo identifica um dos jogadores.
	*/

	public void onPlayerCollision(String playerId){
		//se a bolar tocar no player ela muda o sentido do eixo x
		if((playerId.equals("Player 1")&&this.sentidoX<0)||(playerId.equals("Player 2")&&this.sentidoX>0)){
			this.sentidoX = -1*this.sentidoX;
		}
		
	}

	/**
		Método chamado quando detecta-se uma colisão da bola com uma parede.

		@param wallId uma string cujo conteúdo identifica uma das paredes da quadra.
	*/

	public void onWallCollision(String wallId){
		//se a bola tocar nas paredes de cima ou de baixo elas mudam o sentido do eixo Y
		if((wallId.equals("Top")&&sentidoY<0)||(wallId.equals("Bottom")&&sentidoY>0)){
			this.sentidoY = -1*this.sentidoY;
			
		}
		//caso toquem nas laterais ela "renasce" no meio com uma sentido aleatorio
		if(wallId.equals("Left") || wallId.equals("Right")){
			this.cx = this.CentroX;
			this.cy = this.CentroY;
			defineDirecao();
				
		}
		
	}

	/**
		Método que verifica se houve colisão da bola com uma parede.

		@param wall referência para uma instância de Wall contra a qual será verificada a ocorrência de colisão da bola.
		@return um valor booleano que indica a ocorrência (true) ou não (false) de colisão.
	*/
	
	public boolean checkCollision(Wall wall){
		String id = wall.getId();
		double wx = wall.getCx();
		double wy = wall.getCy();
		double ww = wall.getWidth();
		double wh = wall.getHeight();
		
		//se a aresta inferior da parede de cima esta numa altura menor que a aresta superior da bola retorna true 
		if(wy + wh/2 > this.cy - this.height/2 && id.equals("Top"))		return true;
		//se a aresta superior da parede de baixo esta numa altura maior que a aresta inferior da bola retorna true
		if(wy - wh/2 < this.cy + this.height/2 && id.equals("Bottom"))	return true;
		//se a aresta direita da parede esquerda esta mais a esqueda que a aresta esquerda da bola retorna true
		if(wx + ww/2 > this.cx - this.width/2 && id.equals("Left"))		return true;
		//se a aresta esquerda da parede direita esta mais a direita que a aresta direita da bola retorna true
		if(wx - ww/2 < this.cx + this.width/2 && id.equals("Right"))	return true;
		//se não retorna false
		return false;
		
	}

	/**
		Método que verifica se houve colisão da bola com um jogador.

		@param player referência para uma instância de Player contra o qual será verificada a ocorrência de colisão da bola.
		@return um valor booleano que indica a ocorrência (true) ou não (false) de colisão.
	*/	

	public boolean checkCollision(Player player){
		String id = player.getId();
		double px = player.getCx();
		double py = player.getCy();
		double pw = player.getWidth();
		double ph = player.getHeight();
		//se as arestas laterais da bola nao estao no intervalo x de onde o player pode estar 
		//se a bola nao esta numa posicao onde o player pode rebate-la
		if((px - pw/2 > this.cx + this.width/2 || px + pw/2 < this.cx - this.width/2) && id.equals("Player 1"))	return false;
		if((px + pw/2 < this.cx - this.width/2 || px - pw/2 > this.cx + this.width/2) && id.equals("Player 2"))	return false;
		//se o player consegue tocar na bola
		if(py + ph/2 > this.cy - this.height/2 && py - ph/2 < this.cy + this.height/2)	return true;
		

		return false;
	}
	/*
	 *	esse metodo e usado para definir a direcao da bola de maneira aleatoria 
	 *
	*/
	private void defineDirecao(){
		double pi = Math.PI;
		double random = Math.random()*2*pi;
		double cos =  Math.cos(random);
		double sen =  Math.sin(random);
		if(Math.abs(sen) > 0.9|| Math.abs(cos)>0.9){
			int sinalX = getSinal(cos);
			int sinalY = getSinal(sen);
			this.sentidoX = 0.7*sinalX;
			this.sentidoY = 0.7*sinalY;
		}else{
			this.sentidoX = cos;
			this.sentidoY = sen;
		}
	}
	
	/*
	 *	esse metodo recebe um numero e retorna -1 se for menor que zero e +1 se maior ou igual a zero
	 *
	*/
	private int getSinal(double n){
		if(n<0)
			return -1;
		else
			return +1;
		
	}

	/**
		Método que devolve a coordenada x do centro do retângulo que representa a bola.
		@return o valor double da coordenada x.
	*/
	
	public double getCx(){

		return this.cx;
	}

	/**
		Método que devolve a coordenada y do centro do retângulo que representa a bola.
		@return o valor double da coordenada y.
	*/

	public double getCy(){

		return this.cy;
	}

	/**
		Método que devolve a velocidade da bola.
		@return o valor double da velocidade.

	*/

	public double getSpeed(){

		return this.speed;
	}

}
