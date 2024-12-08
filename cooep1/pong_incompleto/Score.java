import java.awt.*;

/**
	Esta classe representa um placar no jogo. A classe princial do jogo (Pong)
	instancia dois objeto deste tipo, cada um responsável por gerenciar a pontuação
	de um player, quando a execução é iniciada.
*/

public class Score {
	//pontuacao do player
	private int score;
	//nome do Player
	private String id;
	//lado em que o player esta(direita ou esquerda)
	private int lado;
	//cor do da pontuacao
	private Color color;
	/**
		Construtor da classe Score.

		@param playerId uma string que identifica o player ao qual este placar está associado.
	*/

	public Score(String playerId){
		//carrega variaveis
		this.score = 0;
		this.id = playerId;
		//Player 1 a esquerda e Player 2 a direita
		if(playerId.equals("Player 1")){
			this.lado = GameLib.ALIGN_LEFT;
			this.color = Color.GREEN;
		}
		if(playerId.equals("Player 2")){
			this.lado = GameLib.ALIGN_RIGHT;
			this.color = Color.BLUE;
		}
	}

	/**
		Método de desenho do placar.
	*/

	public void draw(){
		GameLib.setColor(color);
		String pontuacao = this.id+":"+ this.score;
		GameLib.drawText(pontuacao, 70, lado);			
	}

	/**
		Método que incrementa em 1 unidade a contagem de pontos.
	*/

	public void inc(){
		//incrementa a pontuacao
		this.score++;
	}

	/**
		Método que devolve a contagem de pontos mantida pelo placar.

		@return o valor inteiro referente ao total de pontos.
	*/

	public int getScore(){

		return score;
	}
}
