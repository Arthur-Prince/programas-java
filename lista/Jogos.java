public class Jogos{
	public static void torreDeHanoi(char ini,char aux ,char fim, int andares){
		if(andares == 1)
			System.out.println("move de "+ ini + " para "+ fim);
		else{
			torreDeHanoi(ini,fim,aux,andares-1);
			torreDeHanoi(ini,aux,fim,1);
			torreDeHanoi(aux,ini,fim,andares-1);
		}
	}
	public static void main(String[] args){
		torreDeHanoi('A','B','C',3);
	}
}