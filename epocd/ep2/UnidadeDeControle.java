package ep2;
import java.io.File;

public class UnidadeDeControle{
	/*
		nessa variavel contera o comando a ser traduzido para executar a matriz
	*/
	public int IR;
	/*
		nessa variavel contera o endereco da proxima linha que sera lida(no caso a 	propria linha)
	*/
	public int PC;
	/*
		endereço auxiliar para ajuda na funcao mov [c],c
	*/
	public int END;
	/*
		aqui contera a interpretacao de todos os comandos em relacao a qual codigo executar
	*/
	public MemoriaDaUC UC;
	/*
		aqui contem todas as operações aritmeticas
	*/
	public Ula ula;
	/*
		aqui contem o arquivo compilado
	*/
	public Memoria memoria;
	/*
		contem os registradores
	*/
	public Registradores reg;
	
	//o construtor inicializa as variaveis
	public UnidadeDeControle(){
		File file = new File("entrada.txt");
		this.reg=new Registradores();
		this.memoria=new Memoria(file);
		this.UC=new MemoriaDaUC();
		this.ula=new Ula(0,0);
		this.END=0x0000;
		this.PC=0x0000;
		this.IR=0x0000;
	}
	
	
	/*
		executa a linha "car" da memoria da UC(pode modificar o car tbm para o endereço-1
	*/
	public void excuta1LinhaDaMatriz(int car,int[] IRDec){
		System.out.println("executando a linha: " + car);
		int[] cbr=new int[35];
		int barramentoInterno=0x0;
		int barramentoExterno=0x0;
		
		//monta o cbr
		for(int i=2;i<=6;i++){
			cbr[i+2]=UC.M[car][i];
		}
		for(int i=9;i<=29;i++){
			cbr[i+4]=UC.M[car][i];
		}
		if(UC.M[car][0]==1)
			cbr[IRDec[1]]=1;
		if(UC.M[car][1]==1)
			cbr[IRDec[2]]=1;
		if(UC.M[car][7]==1)
			cbr[IRDec[1]+9]=1;
		if(UC.M[car][8]==1)
			cbr[IRDec[2]+9]=1;
		
		
			// algum registrador entra no barramento interno
			if(1==cbr[0]){
				barramentoInterno=reg.getAx();
				System.out.print("ax -> ");
			}
			if(1==cbr[1]){
				barramentoInterno=reg.getBx();
				System.out.print("bx -> ");
			}
			if(1==cbr[2]){
				barramentoInterno=reg.getCx();
				System.out.print("cx -> ");
			}
			if(1==cbr[3]){
				barramentoInterno=reg.getDx();
				System.out.print("dx -> ");
			}
			if(1==cbr[4]){
				barramentoInterno=memoria.MBR;
				System.out.print("MBR -> ");
			}
			if(1==cbr[5]){
				barramentoInterno=ula.getULA();
				System.out.print("ULA -> ");
			}
			if(1==cbr[6]){
				barramentoInterno=IR;
				System.out.print("IR -> ");
			}
			if(1==cbr[7]){
				barramentoInterno=PC;
				System.out.print("PC -> ");
			}
			if(1==cbr[8]){
				barramentoInterno=END;
				System.out.print("END -> ");
			}	
			//do barramento interno vai para os registradores
			
			if(1==cbr[9]){
				reg.setAx(barramentoInterno);
				System.out.print("ax, ");
			}
			if(1==cbr[10]){
				reg.setBx(barramentoInterno);
				System.out.print("bx, ");
			}
			if(1==cbr[11]){
				reg.setCx(barramentoInterno);
				System.out.print("cx, ");
			}
			if(1==cbr[12]){
				reg.setDx(barramentoInterno);
				System.out.print("dx, ");
			}
			if(1==cbr[13]){
				IR=barramentoInterno;
				System.out.print("IR, ");
			}
			if(1==cbr[14]){
				PC=barramentoInterno;
				System.out.print("PC, ");
			}
			if(1==cbr[15]){
				memoria.MBR=barramentoInterno;
				System.out.print("MBR, ");
			}
			if(1==cbr[16]){
				ula.setY(barramentoInterno);
				System.out.print("Y, ");
			}
			if(1==cbr[17]){
				memoria.MAR=barramentoInterno;
				System.out.print("MAR, ");
			}
			if(1==cbr[18]){
				ula.setULA(barramentoInterno);
				System.out.print("ULA, ");
			}
			if(1==cbr[19]){
				END=barramentoInterno;
				System.out.print("END, ");
			}	
			System.out.println("\nbarramentoInterno = "+ (String.format("%x", barramentoInterno)));



			//algum registrador entra no barramento externo
			
			if(1==cbr[20]){
				barramentoExterno=memoria.MAR;
				System.out.print("MAR -> ");
			}
			if(1==cbr[21]){
				barramentoExterno=memoria.MBR;
				System.out.print("MBR -> ");
			}
			if(1==cbr[22]){
				barramentoExterno=memoria.MBR;
				System.out.print("Memoria -> ");
			}


			
			//do barramento externo vai para algum registrador
			
			if(1==cbr[23]){
				
				System.out.println("MBR");
			}
			if(1==cbr[24]){
				System.out.println("memoria");
				//nao faz nada pq nao existe o reg memoria
			}	
			System.out.println("barramentoExterno = "+ (String.format("%x", barramentoExterno)));
			
			
			//funcoes aritmeticas	
			
			if(1==cbr[25]){
				ula.add();
				System.out.println("sinal de soma");
			}
			if(1==cbr[26]){
				ula.sub();
				System.out.println("sinal de subtracao");
			}
			if(1==cbr[27]){
				ula.mul();
				System.out.println("sinal de multiplicacao");
			}
			if(1==cbr[28]){
				ula.div();
				System.out.println("sinal de divisao");
			}
			if(1==cbr[29]){
				ula.mod();
				System.out.println("sinal de modulacao");
			}
			if(1==cbr[30]){
				ula.cmp();
				System.out.println("sinal de comparacao");
			}
			if(1==cbr[31]){
				ula.add1();
				System.out.println("sinal de incrementacao");
			}
			if(1==cbr[32]){
				memoria.read();
				System.out.println("sinal de leitura");
			}
			if(1==cbr[33]){
				memoria.write();	
				System.out.println("sinal de escrita");
			}
	}
	
	
	//jumps na memoria de controle(faz o car mudar)
	public int jump(int linha, int[] IRDec){
		boolean[] flags = ula.getFlags();
		int car=linha;
		//mesnsagem de erro
		if(UC.M[car][34]==1 && flags[2])
				System.out.println("\nerro em alguma operacao aritmetica");

		//jump obrigatorio
		if(UC.M[car][30]==1){
			System.out.print("\njump obrigatorio para a linha: ");
			if(UC.M[car][35]==1){
				System.out.println(IRDec[0]+"\n");
				return IRDec[0];
			}
			else{
				System.out.println(UC.M[car][36]+"\n");
				return UC.M[car][36];
			}
		}
		//jump condicional
		if(UC.M[car][31]==1){
			boolean condicao=true;
			System.out.print("\njump condicional, condicao: ");
			//condicao de sinal
			if(UC.M[car][32]==1){
				condicao=flags[0];
				System.out.print("sinal, ");
			}
			//condicao de igualdade
			if(UC.M[car][33]==1){
				if(condicao){
					condicao=flags[1];
				}
				System.out.print("igualdade");
			}
			System.out.println("\ncondicao = " + condicao+"\n");
			if(condicao){
				System.out.println("jump para a linha "+UC.M[car][36]);
				return UC.M[car][36];
			}
		}
		System.out.println("/***************************************/");
		System.out.println("/***************************************/");
		return car+1;
		
	}

	
	/*
		decodifica o IR de acordo com as especificações da compilação
	*/
	public int[] decodificaIR(){
		int[] ir= new int[3];
		int f = this.IR;
		int h=0x10;
		int p2=f % h;
		f=(f-p2)/h;
		int p1=f % h;
		f=(f-p1)/h;
		if(p2<=8){
			ir[2]=(p2-1)%4;
		}
		if(p1<=8){
			ir[1]=(p1-1)%4;
		}
		
		//
		switch(f){
			//não faz nada
			case 0:
				break;
			//função mov
			case 1:
				//mov r1...
				if(p1<=4&&p1>0){
					//mov r1,r2
					if(p2<=4 && p2>0)
						ir[0]=36;
					else{
						//mov r1,[r2]
						if(p2<=8)
							ir[0]=38;
						else{
							//mov r1,c
							if(p2==9)
								ir[0]=43;
							else{
								//mov r1,[c]
								if(p2==0xA)
									ir[0]=28;
								else{
									System.out.println("erro na execucao funcao mov r1...");
								}
							}
						}
					}
				}else{
					//mov[r1]...
					if(p1<=8){
						//mov [r1],r2
						if(p2<=4 && p2>0)
							ir[0]=16;
						else{
							//mov [r1],c
							if(p2==9)
								ir[0]=48;
							else{
								System.out.println("erro na execucao da funcao mov [r1]...");
							}
						}
					}else{
						//mov [c]...
						if(p1==0xA){
							//mov [c],r1
							if(p2<=4 && p2>0)
								ir[0]=21;
							else{
								//mov [c],c
								if(p2==9)
									ir[0]=5;
								else{
									System.out.println("erro na execucao da funcao mov [c]...");
								}
							}
						}else{
							System.out.println("erro na execucao da funcao mov");
						}
					}
				}
				break;
			//add
			case 2:
				//add r1...
				if(p1<=4 && p1>0){
					//add r1,r2
					if(p2<=4 && p2>0)
						ir[0]=60;
					else{
						//add r1,c
						if(p2==9)
							ir[0]=53;
						else{
							System.out.println("erro na funcao add");
						}
					}
				}else{
					System.out.println("erro na funcao add");
				}
				break;
			//sub
			case 3:
				//sub r1...
				if(p1<=4 && p1>0){
					//sub r1,r2
					if(p2<=4 && p2>0)
						ir[0]=71;
					else{
						//sub r1,c
						if(p2==9)
						ir[0]=64;
						else{
							System.out.println("erro na funcao sub");
						}
					}
				}else{
					System.out.println("erro na funcao sub");
				}
				break;
			//mul
			case 4:
				//mul r1...
				if(p1<=4 && p1>0){
					//mul r1,r2
					if(p2<=4 && p2>0)
						ir[0]=82;
					else{
						//mul r1,c
						if(p2==9)
							ir[0]=75;
						else{
							System.out.println("erro na funcao mul");
						}
					}
				}else{
					System.out.println("erro na funcao mul");
				}
				break;
			//div
			case 5:
				//div r1...
				if(p1<=4 && p1>0){
					//div r1,r2
					if(p2<=4 && p2>0)
						ir[0]=93;
					else{
						//div r1,c
						if(p2==9)
							ir[0]=86;
						else{
							System.out.println("erro na funcao div");
						}
					}
				}else{
					System.out.println("erro na funcao div");
				}
				break;
			//mod
			case 6:
				//mod r1...
				if(p1<=4 && p1>0){
					//mod r1,r2
					if(p2<=4 && p2>0)
						ir[0]=104;
					else{
						//mod r1,c
						if(p2==9)
							ir[0]=97;
						else{
							System.out.println("erro na funcao mod");
						}
					}
				}else{
					System.out.println("erro na funcao mod");
				}
				break;
			//cmp
			case 7:
				//cmp r1...
				if(p1<=4 && p1>0){
					//cmp r1,r2
					if(p2<=4 && p2>0)
						ir[0]=115;
					else{
						//cmp r1,c
						if(p2==9)
							ir[0]=108;
						else{
							System.out.println("erro na funcao cmp");
						}
					}
				}else{
					System.out.println("erro na funcao cmp");
				}
				break;
			//jmp
			case 8:
				//jmp c
				if(p1==9)
					ir[0]=119;
				else{
					System.out.println("erro na funcao jmp");
				}
				break;
			//je
			case 9:
				//je c
				if(p1==9)
					ir[0]=124;
				else{
					System.out.println("erro na funcao je");
				}
				break;
			//jne
			case 0xA:
				//jne c
				if(p1==9)
					ir[0]=136;
				else{
					System.out.println("erro na funcao jne");
				}
				break;
			//jg
			case 0xB:
				//jg c
				if(p1==9)
					ir[0]=131;
				else{
					System.out.println("erro na funcao jg");
				}
				break;
			//jge
			case 0xC:
				//jge c
				if(p1==9)
					ir[0]=134;
				else{
					System.out.println("erro na funcao jge");
				}
				break;
			//jl
			case 0xD:
				//jl c
				if(p1==9)
					ir[0]=126;
				else{
					System.out.println("erro na funcao jl");
				}
				break;
			//jle
			case 0xE:
				//jle c
				if(p1==9)
					ir[0]=128;
				else{
					System.out.println("erro na funcao jle");
				}
				break;
			default:
				System.out.println("isso nao e uma funcao(provavelmente e so um numero)");
			
		}
		return ir;
	}
	
}