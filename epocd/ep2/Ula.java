package ep2;
/*
    Implementado por: Mauricio Mori Dantas Santana 7991170
*/

class Ula{
    // Valor de entrada
    private int ULA;
    private int Y;
    // Flags
    //bit 0: Flag de sinal(1se negativo, 0 se positivo)
    //bit 1: Flag de igualdade(1 se igual, 0 se diferente)
    //bit 2: Flag de erro(1 se da qualquer erro, 0 se ta tudo ok)
    private boolean[] Flags = new boolean[3];

    // Construtor
    Ula(int ULA, int Y){
        this.ULA = ULA;
        this.Y = Y;
        atualizaFlags();
    }

    private void atualizaFlags(){
		// Flag de sinal
        if(ULA < 0)
            this.Flags[0] = true;
        else
            this.Flags[0] = false;
        // Flag de igualdade
        if(ULA != 0)
            this.Flags[1] = true;
        else
            this.Flags[1] = false;
        // Flag de erro
        if(ULA < -0xffff || ULA > 0xffff)
            this.Flags[2] = true;
        else
            this.Flags[2] = false;

    }
    // Metodos para operacoes aritmeticas
	
	
	public void add1(){
        int aux = this.ULA + 1;

        if(aux > 65535 || aux < -65535){
            this.Flags[2] = true;
        }
        else{
            this.ULA = aux;
        }
    }
	
    public void add(){
        int aux = this.ULA + Y;

        if(aux > 65535 || aux < -65535){
            this.Flags[2] = true;
        }
        else{
            atualizaFlags();
            this.ULA = aux;
        }
    }

    public void sub(){
        int aux = this.ULA - Y;

        if(aux > 65535 || aux < -65535){
            this.Flags[2] = true;
        }
        else{
            atualizaFlags();
            this.ULA = aux;
        }
    }

    public void mul(){
        int aux = this.ULA * Y;

        if(aux > 65535 || aux < -65535){
            this.Flags[2] = true;
        }
        else{
            atualizaFlags();
            this.ULA = aux;
        }
    }

    public void div(){
        // Se o divisor for 0, ativa Flag de erro, e retorna sem modificar o x
        if(Y == 0){
            this.Flags[2] = true;
        }
        else{
            this.ULA = this.ULA / Y;
			atualizaFlags();
        }
    }

    public void mod(){
        // Se o divisor for 0, ativa Flag de erro, e retorna sem modificar o x
        if(Y == 0){
            this.Flags[2] = true;
        }
        else{
            this.ULA = ULA % Y;
            atualizaFlags();
        }
    }

    // Faz comparacao entre ULA e Y
    // Conforme o resultado, modifica o valor de Flags[]
    public void cmp(){
		// Flag de sinal(1se negativo, 0 se positivo)
        if(ULA < Y)
            this.Flags[0] = true;
        else
            this.Flags[0] = false;
        // Flag de igualdade(1 se igual, 0 se diferente)
        if(ULA == Y)
            this.Flags[1] = false;
        else
            this.Flags[1] = true;
    }

    // getters e setters
    public int getY(){
        return this.Y;
    }

    public void setY(int Y){
        this.Y = Y;
    }
    public int getULA(){
        return this.ULA;
    }

    public void setULA(int ULA){
        this.ULA = ULA;
    }
    public boolean[] getFlags(){
        return this.Flags;
    }
}
