package ep2;

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//  bit 0: escreve o registrador 1 (presente no IR) no barramento interno        **funcoes**                                                         //
//  bit 1: escreve o registrador 2 (presente no IR) no barramento interno        bit 21: soma                                                        //
//  bit 2: MBR entra no barramento interno                                       bit 22: subtracao                                                   //
//  bit 3: ULA entra no barramento interno                                       bit 23: multiplicacao                                               //
//  bit 4: IR entra no barramento interno                                        bit 24: divisao                                                     //
//  bit 5: PC entra no barramento interno                                        bit 25: modulacao(resto da divisao)                                 //
//  bit 6: END entra no barramento interno                                       bit 26: comparacao                                                  // 
//  bit 7: le o que esta no barramento e coloca no registrador 1                 bit 27: soma+1(ULA++)                                               //
//  bit 8: le o que esta no barramento e coloca no registrador 2                 bit 28: read                                                        //
//  bit 9: do barramento interno vai para o IR                                   bit 29: write                                                       // 
//  bit 10: do barramento interno vai para o PC                                  **jump na memoria de controle**                                     //
//  bit 11: do barramento interno vai para o MBR                                 bit 30: pule sempre para o endereço do ultimo bit                   //
//  bit 12: do barramento interno vai para o Y                                   bit 31: pule se cumprir as condicoes para o endereco do ultimo bit  //
//  bit 13: do barramento interno vai para o MAR                                 **condicoes**                                                       //
//  bit 14: do barramento interno vai para a ULA                                 bit 32: flag de sinal                                               // 
//  bit 15: do barramento interno vai para END                                   bit 33: flag de igualdade                                           //
//  bit 16: MAR entra no barramento externo                                      bit 34: flag de erro                                                //
//  bit 17: MBR entra no barramento externo                                      **endereco**                                                        //
//  bit 18: Memoria entra no barramento externo                                  bit 35: endereço do IR                                              // 
//  bit 19: do barramento externo vai para o MBR                                 bit 36:endereço                                                     //
//  bit 20: do barramento externo vai para a memoria                                                                                                 //
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    


public class MemoriaDaUC {
    
    int M[][] = new int[141][37];
    
    public MemoriaDaUC()
    {
        
        ////////////////////////////////////////////
        ////        Ordem das Funções           ////
        ////                                    ////
        ////  MOV [C],C         Linhas:5-15     ////
        ////  MOV [R1],R2       Linhas:16-20    ////
        ////  MOV [C],R1        Linhas:21-27    ////
        ////  MOV R1,[C]        Linhas:28-35    ////
        ////  MOV R1,R2         Linhas:36-37    ////
        ////  MOV R1,[R2]       Linhas:38-42    ////
        ////  MOV R1,C          Linhas:43-47    ////
        ////  MOV [R1],C        Linhas:48-52    ////
        ////                                    ////
        ////  ADD R1,C          Linhas:53-59    ////
        ////  ADD R1,R2         Linhas:60-63    ////
        ////                                    ////
        ////  SUB R1,C          Linhas:64-70    ////
        ////  SUB R1,R2         Linhas:71-74    ////
        ////                                    ////
        ////  MUL R1,C          Linhas:75-81    ////
        ////  MUL R1,R2         Linhas:82-85    ////
        ////                                    ////
        ////  DIV R1,C          Linhas:86-92    ////
        ////  DIV R1,R2         Linhas:93-96    ////
        ////                                    ////
        ////  MOD R1,C          Linhas:97-103   ////
        ////  MOD R1,R2         Linhas:104-107  ////
        ////                                    ////
        ////  CMP R1,C          Linhas:108-114  ////
        ////  CMP R1,R2         Linhas:115-118  ////
        ////                                    ////
        ////  JMP               Linhas:119-123  ////
        ////  JE                Linhas:124-125  ////
        ////  JL                Linhas:126-127  ////
        ////  JLE               Linhas:128-130  ////
        ////  JG                Linhas:131-133  ////
        ////  JGE               Linhas:134-135  ////
        ////  JNE               Linhas:136-137  ////
		////  CONDICAO NEGADA   Linhas:138-140  ////
        ////////////////////////////////////////////
        
    ////CICLO DE BUSCA///////////////////////////////////////////////////////////
	 
	 
		//T1
		M[0][5]     = 1;  //PC->Barramento Interno
        M[0][13]    = 1;  //Barramento Interno->MAR
        M[0][14]    = 1;  //Barramento Interno->ULA
        M[0][27]    = 1;  //Comando ULA++
        
        //T2
        M[1][16]    = 1;  //MAR->Barramento Externo
        M[1][20]    = 1;  //Barramento Externo->Memória
        M[1][28]    = 1;  //Comando Read
		M[1][3]	    = 1;  //ULA->barramento interno
		M[1][10]    = 1;  //barramento interno->PC	
        
        //T3
        M[2][18]    = 1;  //Memória->Barramento Externo
        M[2][19]    = 1;  //Barramento Externo->MBR
		
		//T4
		M[3][2]     = 1;  //MBR->barramento interno
		M[3][9]	    = 1;  //barramento interno->IR
		
		//T5
		M[4][30]    = 1;  //pule sempre
		M[4][35]    = 1;  //para o endereco do IR
        
     
    ////FUNÇÕES-MOV/////////////////////////////////////////////////////////////
        
        
        ////  MOV [C],C  ////
               
        //T1
        M[5][5]     = 1;  //PC->Barramento Interno
        M[5][13]    = 1;  //Barramento Interno->MAR
        M[5][14]    = 1;  //Barramento Interno->ULA
        M[5][27]    = 1;  //Comando ULA++
        
        //T2
        M[6][16]    = 1;  //MAR->Barramento Externo
        M[6][20]    = 1;  //Barramento Externo->Memória
        M[6][28]    = 1;  //Comando Read
        M[6][3]	    = 1;  //ULA->barramento interno
		M[6][10]    = 1;  //barramento interno->PC
		
        //T3
        M[7][18]    = 1;  //Memória->Barramento Externo
        M[7][19]    = 1;  //Barramento Externo->MBR

        //T4
        M[8][2]     = 1;  //Memória->Barramento Interno
        M[8][15]    = 1;  //Barramento Interno->END
        
        //T5
        M[9][5]     = 1;  //PC->Barramento Interno
        M[9][13]    = 1;  //Barramento Interno->MAR
        M[9][14]    = 1;  //Barramento Interno->ULA
        M[9][27]    = 1;  //Comando ULA++
        
        //T6
        M[10][16]   = 1;  //MAR->Barramento Externo
        M[10][20]   = 1;  //Barramento Externo->Memória
        M[10][28]   = 1;  //Comando Read
        M[10][3]    = 1;  //ULA->barramento interno
		M[10][10]   = 1;  //barramento interno->PC
		
        //T7
        M[11][18]   = 1;  //Memória->Barramento Externo        
        M[11][19]   = 1;  //Barramento Externo->MBR
        
        //T8
        M[12][6]    = 1;  //END->Barramento Interno    
        M[12][13]   = 1;  //Barramento Interno->MAR

        //T9
        M[13][16]   = 1;  //MAR->Barramento Externo 
        M[13][20]   = 1;  //Barramento Externo->Memória
    
        //T10
        M[14][17]   = 1;  //MBR->Barramento Externo
        M[14][20]   = 1;  //Barramento Externo->Memória
        M[14][29]   = 1;  //Comando Write
        
        //T11
        M[15][30]   = 1;  //Pule sempre para o Endereço do último Bit
        
        
        
        ////  MOV [R1],R2  ////
        
        //T1
        M[16][1]    = 1;  //R2->Barramento Interno
        M[16][11]   = 1;  //Barramento Interno->MBR
        
        //T2
        M[17][0]    = 1;  //R1->Barramento Interno
        M[17][13]   = 1;  //Barramento Interno->MAR
        
        //T3
        M[18][16]   = 1;  //MAR->Barramento Externo
        M[18][20]   = 1;  //Barramento Externo->Memória
        
        //T4
        M[19][17]   = 1;  //MBR->Barramento Externo
        M[19][20]   = 1;  //barramento externo->Memória
        M[19][29]   = 1;  //Comando Write
        
        //T5
        M[20][30]   = 1;  //Pule sempre para o Endereço do último Bit
        
        
        
	////  MOV [C],R1  ////
        
        //T1
        M[21][5]    = 1;  //PC->Barramento Interno
        M[21][13]   = 1;  //Barramento Interno->MAR
        M[21][14]   = 1;  //Barramento Interno->ULA
        M[21][27]   = 1;  //Comando ULA++
        
        //T2
        M[22][16]   = 1;  //MAR->Barramento Externo
        M[22][20]   = 1;  //Barramento Externo->Memória
        M[22][3]    = 1;  //ULA->Barramento Interno
        M[22][10]   = 1;  //Barramento Interno->PC
        M[22][28]   = 1;  //Comando Read
		
        //T3
        M[23][18]   = 1;  //Memória->Barramento Externo
        M[23][19]   = 1;  //Barramento Externo->MBR
        
        //T4
        M[24][2]    = 1;  //MBR->Barramento Interno
        M[24][13]   = 1;  //Barramento Interno->MAR

        //T5
        M[25][16]   = 1;  //MAR->Barramento Externo
        M[25][20]   = 1;  //Barramento Externo->Memória
        M[25][0]    = 1;  //R1->Barramento Interno
        M[25][11]   = 1;  //Barramento Interno->MBR
        
        //T6
        M[26][17]   = 1;  //MBR->Barramento Externo
        M[26][20]   = 1;  //Barramento Externo->Memória
        M[26][29]   = 1;  //Comando Write

        //T7
        M[27][30]   = 1;  //Pule sempre para o Endereço do último Bit
        
        
        
        ////  MOV R1,[C]  ////
        
        //T1
        M[28][5]    = 1;  //PC->Barramento Interno
        M[28][13]   = 1;  //Barramento Interno->MAR
        M[28][14]   = 1;  //Barramento Interno->ULA
        M[28][27]   = 1;  //Comando ULA++
        
        //T2
        M[29][16]   = 1;  //MAR->Barramento Externo
        M[29][20]   = 1;  //Barramento Externo->Memória
        M[29][3]    = 1;  //ULA->Barramento Interno
        M[29][10]   = 1;  //Barramento Interno->PC
		M[29][28]   = 1;  //Comando Read
        
        //T3
        M[30][18]   = 1;  //Memória->Barramento Externo
        M[30][19]   = 1;  //Barramento Externo->MBR
        
        //T4
        M[31][2]    = 1;  //MBR->Barramento Interno
        M[31][13]   = 1;  //Barramento Interno->MAR

        //T5
        M[32][16]   = 1;  //MAR->Barramento Externo
        M[32][20]   = 1;  //Barramento Externo->Memória
		M[32][28]   = 1;  //Comando Read
        
        //T6
        M[33][18]   = 1;  //Memória->Barramento Externo
        M[33][19]   = 1;  //Barramento Externo->MBR

        //T7
        M[34][2]    = 1;  //MBR->Barramento Interno
        M[34][7]    = 1;  //Barramento Interno->R1
        
        //T8
        M[35][30]   = 1;  //Pule sempre para o Endereço do último Bit
        
        
        
        ////  MOV R1,R2  ////
        
        //T1
        M[36][1]    = 1;  //R2->Barramento Interno
        M[36][7]    = 1;  //Barramento Interno->R1
        
        //T2
        M[37][30]   = 1;  //Pule sempre para o Endereço do último Bit
        
        
        
        ////  MOV R1,[R2]  ////
        
        //T1
        M[38][1]    = 1;  //R2->Barramento Interno
        M[38][13]   = 1;  //Barramento Interno->MAR
        
        //T2
        M[39][16]   = 1;  //MAR->Barramento Externo
        M[39][20]   = 1;  //Barramento Externo->Memória
		M[39][28]   = 1;  //Comando Read
		
        //T3
        M[40][18]   = 1;  //Memória->Barramento Externo
        M[40][19]   = 1;  //Barramento Externo->MBR
        
        //T4
        M[41][2]    = 1;  //MBR->Barramento Interno
        M[41][7]    = 1;  //Barramento Interno->R1
        
        //T5
        M[42][30]   = 1;  //Pule sempre para o Endereço do último Bit
        
        
        
        ////  MOV R1,C  ////
         
        //T1
        M[43][5]    = 1;  //PC->Barramento Interno
        M[43][13]   = 1;  //Barramento Interno->MAR
        M[43][14]   = 1;  //Barramento Interno->ULA
        M[43][27]   = 1;  //Comando ULA++
        
        //T2
        M[44][16]   = 1;  //MAR->Barramento Externo
        M[44][20]   = 1;  //Barramento Externo->Memória
        M[44][3]    = 1;  //ULA->Barramento Interno
        M[44][10]   = 1;  //Barramento Interno->PC
        M[44][28]   = 1;  //Comando Read
		
        //T3
        M[45][18]   = 1;  //Memória->Barramento Externo
        M[45][19]   = 1;  //Barramento Externo->MBR
        
        //T4
        M[46][2]    = 1;  //MBR->Barramento Interno
        M[46][7]    = 1;  //Barramento Interno->R1
        
        //T5
        M[47][30]   = 1;  //Pule sempre para o Endereço do último Bit
        
        
        
        ////  MOV [R1],C  ////
          
        //T1
        M[48][5]    = 1;  //PC->Barramento Interno
        M[48][13]   = 1;  //Barramento Interno->MAR
        M[48][14]   = 1;  //Barramento Interno->ULA
        M[48][27]   = 1;  //Comando ULA++
        
        //T2
        M[49][16]   = 1;  //MAR->Barramento Externo
        M[49][20]   = 1;  //Barramento Externo->Memória
        M[49][3]    = 1;  //ULA->Barramento Interno
        M[49][10]   = 1;  //Barramento Interno->PC
		M[49][28]   = 1;  //Read
        
        //T3
        M[50][18]   = 1;  //Memória->Barramento Externo
        M[50][19]   = 1;  //Barramento Externo->MBR
        M[50][0]    = 1;  //R1->Barramento Interno
        M[50][13]   = 1;  //Barramento Interno->MAR
        
        //T4
        M[51][16]   = 1;  //MAR->Barramento Externo
        M[51][20]   = 1;  //Barramento Externo->Memória
        
        //T5
		M[52][17]   = 1;  //MBR->Barramento Externo
        M[52][20]   = 1;  //Barramento Externo->Memória
        M[52][29]   = 1;  //Comando Write
        M[52][30]   = 1;  //Pule sempre para o Endereço do último Bit
        
        
        
    ////FUNÇÕES-ADD///////////////////////////////////////////////////////////// 
        
    
        ////  ADD R1,C  ////
           
        //T1
        M[53][5]    = 1;  //PC->Barramento Interno
        M[53][13]   = 1;  //Barramento Interno->MAR
        M[53][14]   = 1;  //Barramento Interno->ULA
        M[53][27]   = 1;  //Comando ULA++
        
        //T2
        M[54][16]   = 1;  //MAR->Barramento Externo
        M[54][20]   = 1;  //Barramento Externo->Memória
        M[54][3]    = 1;  //ULA->Barramento Interno
        M[54][10]   = 1;  //Barramento Interno->PC
		M[54][28]   = 1;  //Comando Read
        
        //T3
        M[55][18]   = 1;  //Memória->Barramento Externo
        M[55][19]   = 1;  //Barramento Externo->MBR
        
        //T4
        M[56][2]    = 1;  //MBR->Barramento Interno
        M[56][12]   = 1;  //Barramento Interno->Y
        
        //T5
        M[57][0]    = 1;  //R1->Barramento Interno
        M[57][14]   = 1;  //Barramento Interno->ULA
        M[57][21]   = 1;  //Operação Soma 
        
        //T6
        M[58][3]    = 1;  //ULA->Barramento Interno
        M[58][7]    = 1;  //Barramento Interno->R2
        
        //T7
        M[59][30]   = 1;  //Pule sempre para o Endereço do último Bit
        
        
        
        ////  ADD R1,R2  ////
         
        //T1
        M[60][1]    = 1;  //R2->Barramento Interno
        M[60][12]   = 1;  //Barramento Interno->Y
        
        //T2
        M[61][0]    = 1;  //R1->Barramento Interno
        M[61][14]   = 1;  //Barramento Interno->ULA
        M[61][21]   = 1;  //Operacao Soma
        
        //T3
        M[62][3]    = 1;  //ULA->Barramento Interno
        M[62][7]    = 1;  //Barramento Interno->R1
        M[62][34]   = 1;  //Flag de Erro 
        
        //T4
        M[63][30]   = 1;  //Pule sempre para o Endereço do último Bit
        
        
        
    ////FUNÇÕES-SUB/////////////////////////////////////////////////////////////
        
        
        ////  SUB R1,C  ////
           
        //T1
        M[64][5]    = 1;  //PC->Barramento Interno
        M[64][13]   = 1;  //Barramento Interno->MAR
        M[64][14]   = 1;  //Barramento Interno->ULA
        M[64][27]   = 1;  //Comando ULA++
        
        //T2
        M[65][16]   = 1;  //MAR->Barramento Externo
        M[65][20]   = 1;  //Barramento Externo->Memória
        M[65][3]    = 1;  //ULA->Barramento Interno
        M[65][10]   = 1;  //Barramento Interno->PC
        M[65][28]   = 1;  //Comando Read
		
        //T3
        M[66][18]   = 1;  //Memória->Barramento Externo
        M[66][19]   = 1;  //Barramento Externo->MBR
        
        //T4
        M[67][2]    = 1;  //MBR->Barramento Interno
        M[67][12]   = 1;  //Barramento Interno->Y
        
        //T5
        M[68][0]    = 1;  //R1->Barramento Interno
        M[68][14]   = 1;  //Barramento Interno->ULA
        M[68][22]   = 1;  //Operação Subtração
        
        //T6
        M[69][3]    = 1;  //ULA->Barramento Interno
        M[69][7]    = 1;  //Barramento Interno->R2
        
        //T7
        M[70][30]   = 1;  //Pule sempre para o Endereço do último Bit
        
        
        
        ////  SUB R1,R2  ////
         
        //T1
        M[71][1]    = 1;  //R2->Barramento Interno
        M[71][12]   = 1;  //Barramento Interno->Y
        
        //T2
        M[72][0]    = 1;  //R1->Barramento Interno
        M[72][14]   = 1;  //Barramento Interno->ULA
        M[72][22]   = 1;  //Operacao Subtração
        
        //T3
        M[73][3]    = 1;  //ULA->Barramento Interno
        M[73][7]    = 1;  //Barramento Interno->R1
        M[73][34]   = 1;  //Flag de Erro 
        
        //T4
        M[74][30]   = 1;  //Pule sempre para o Endereço do último Bit
        
        
        
    ////FUNÇÕES-MUL/////////////////////////////////////////////////////////////
        
        
        ////  MUL R1,C  ////
           
        //T1
        M[75][5]    = 1;  //PC->Barramento Interno
        M[75][13]   = 1;  //Barramento Interno->MAR
        M[75][14]   = 1;  //Barramento Interno->ULA
        M[75][27]   = 1;  //Comando ULA++
        
        //T2
        M[76][16]   = 1;  //MAR->Barramento Externo
        M[76][20]   = 1;  //Barramento Externo->Memória
        M[76][3]    = 1;  //ULA->Barramento Interno
        M[76][10]   = 1;  //Barramento Interno->PC
		M[76][28]   = 1;  //Comando Read
        
        //T3
        M[77][18]   = 1;  //Memória->Barramento Externo
        M[77][19]   = 1;  //Barramento Externo->MBR 
        
        //T4
        M[78][2]    = 1;  //MBR->Barramento Interno
        M[78][12]   = 1;  //Barramento Interno->Y
        
        //T5
        M[79][0]    = 1;  //R1->Barramento Interno
        M[79][14]   = 1;  //Barramento Interno->ULA
        M[79][23]   = 1;  //Operação Multiplicação
        
        //T6
        M[80][3]    = 1;  //ULA->Barramento Interno
        M[80][7]    = 1;  //Barramento Interno->R2
        
        //T7
        M[81][30]   = 1;  //Pule sempre para o Endereço do último Bit
        
        
        
        ////  MUL R1,R2  ////
         
        //T1
        M[82][1]    = 1;  //R2->Barramento Interno
        M[82][12]   = 1;  //Barramento Interno->Y
        
        //T2
        M[83][0]    = 1;  //R1->Barramento Interno
        M[83][14]   = 1;  //Barramento Interno->ULA
        M[83][23]   = 1;  //Operacao Multiplicação
        
        //T3
        M[84][3]    = 1;  //ULA->Barramento Interno
        M[84][7]    = 1;  //Barramento Interno->R1
        M[84][34]   = 1;  //Flag de Erro 
        
        //T4
        M[85][30]   = 1;  //Pule sempre para o Endereço do último Bit
        
        
        
    ////FUNÇÕES-DIV/////////////////////////////////////////////////////////////
        
        
        ////  DIV R1,C  ////
           
        //T1
        M[86][5]    = 1;  //PC->Barramento Interno
        M[86][13]   = 1;  //Barramento Interno->MAR
        M[86][14]   = 1;  //Barramento Interno->ULA
        M[86][27]   = 1;  //Comando ULA++
        
        //T2
        M[87][16]   = 1;  //MAR->Barramento Externo
        M[87][20]   = 1;  //Barramento Externo->Memória
        M[87][3]    = 1;  //ULA->Barramento Interno
        M[87][10]   = 1;  //Barramento Interno->PC
        M[87][28]   = 1;  //Comando Read
		
        //T3
        M[88][18]   = 1;  //Memória->Barramento Externo
        M[88][19]   = 1;  //Barramento Externo->MBR
        
        //T4
        M[89][2]    = 1;  //MBR->Barramento Interno
        M[89][12]   = 1;  //Barramento Interno->Y
        
        //T5
        M[90][0]    = 1;  //R1->Barramento Interno
        M[90][14]   = 1;  //Barramento Interno->ULA
        M[90][24]   = 1;  //Operação Divisão
        
        //T6
        M[91][3]    = 1;  //ULA->Barramento Interno
        M[91][7]    = 1;  //Barramento Interno->R2
        
        //T7
        M[92][30]   = 1;  //Pule sempre para o Endereço do último Bit
        
        
        
        ////  DIV R1,R2  ////
         
        //T1
        M[93][1]    = 1;  //R2->Barramento Interno
        M[93][12]   = 1;  //Barramento Interno->Y
        
        //T2
        M[94][0]    = 1;  //R1->Barramento Interno
        M[94][14]   = 1;  //Barramento Interno->ULA
        M[94][24]   = 1;  //Operacao Divisão
        
        //T3
        M[95][3]    = 1;  //ULA->Barramento Interno
        M[95][7]    = 1;  //Barramento Interno->R1
        M[95][34]   = 1;  //Flag de Erro 
        
        //T4
        M[96][30]   = 1;  //Pule sempre para o Endereço do último Bit
        
        
        
    ///FUNÇÕES-MOD//////////////////////////////////////////////////////////////
        
        
        ////  MOD R1,C  ////
           
        //T1
        M[97][5]    = 1;  //PC->Barramento Interno
        M[97][13]   = 1;  //Barramento Interno->MAR
        M[97][14]   = 1;  //Barramento Interno->ULA
        M[97][27]   = 1;  //Comando ULA++
        
        //T2
        M[98][16]   = 1;  //MAR->Barramento Externo
        M[98][20]   = 1;  //Barramento Externo->Memória
        M[98][3]    = 1;  //ULA->Barramento Interno
        M[98][10]   = 1;  //Barramento Interno->PC
        M[98][28]   = 1;  //Comando Read
		
        //T3
        M[99][18]   = 1;  //Memória->Barramento Externo
        M[99][19]   = 1;  //Barramento Externo->MBR
        
        //T4
        M[100][2]   = 1;  //MBR->Barramento Interno
        M[100][12]  = 1;  //Barramento Interno->Y
        
        //T5
        M[101][0]   = 1;  //R1->Barramento Interno
        M[101][14]  = 1;  //Barramento Interno->ULA
        M[101][25]  = 1;  //Operação Modulação (Resto da Divisão)
        
        //T6
        M[102][3]   = 1;  //ULA->Barramento Interno
        M[102][7]   = 1;  //Barramento Interno->R2
        
        //T7
        M[103][30]  = 1;  //Pule sempre para o Endereço do último Bit
        
        
        
        ////  MOD R1,R2  ////
         
        //T1
        M[104][1]   = 1;  //R2->Barramento Interno
        M[104][12]  = 1;  //Barramento Interno->Y
        
        //T2
        M[105][0]   = 1;  //R1->Barramento Interno
        M[105][14]  = 1;  //Barramento Interno->ULA
        M[105][25]  = 1;  //Operacao Modulação (Resto da Divisão)
        
        //T3
        M[106][3]    = 1;  //ULA->Barramento Interno
        M[106][7]    = 1;  //Barramento Interno->R1
        M[106][34]   = 1;  //Flag de Erro
        
        //T4
        M[107][30]   = 1;  //Pule sempre para o Endereço do último Bit
        
        
        
    ///FUNÇÕES-CMP//////////////////////////////////////////////////////////////
        
        
        ////  CMP R1,C  ////
           
        //T1
        M[108][5]    = 1;  //PC->Barramento Interno
        M[108][13]   = 1;  //Barramento Interno->MAR
        M[108][14]   = 1;  //Barramento Interno->ULA
        M[108][27]   = 1;  //Comando ULA++
        
        //T2
        M[109][16]   = 1;  //MAR->Barramento Externo
        M[109][20]   = 1;  //Barramento Externo->Memória
        M[109][3]    = 1;  //ULA->Barramento Interno
        M[109][10]   = 1;  //Barramento Interno->PC
        M[109][28]   = 1;  //Comando Read
		
        //T3
        M[110][18]   = 1;  //Memória->Barramento Externo
        M[110][19]   = 1;  //Barramento Externo->MBR
        
        //T4
        M[111][2]    = 1;  //MBR->Barramento Interno
        M[111][12]   = 1;  //Barramento Interno->Y
        
        //T5
        M[112][0]    = 1;  //R1->Barramento Interno
        M[112][14]   = 1;  //Barramento Interno->ULA
        M[112][26]   = 1;  //Operação Comparação
        
        //T6
        M[113][3]    = 1;  //ULA->Barramento Interno
        M[113][7]    = 1;  //Barramento Interno->R2
        
        //T7
        M[114][30]   = 1;  //Pule sempre para o Endereço do último Bit
        
        
        
        ////  CMP R1,R2  ////
         
        //T1
        M[115][1]    = 1;  //R2->Barramento Interno
        M[115][12]   = 1;  //Barramento Interno->Y
        
        //T2
        M[116][0]    = 1;  //R1->Barramento Interno
        M[116][14]   = 1;  //Barramento Interno->ULA
        M[116][26]   = 1;  //Operacao Comparação
        
        //T3
        M[117][3]    = 1;  //ULA->Barramento Interno
        M[117][7]    = 1;  //Barramento Interno->R1
        M[117][34]   = 1;  //Flag de Erro
        
        //T4
        M[118][30]   = 1;  //Pule sempre para o Endereço do último Bit

        

    ///FUNÇÕES-JMP//////////////////////////////////////////////////////////////
        
        
        ////  JMP  ////    
        
        //T1
        M[119][5]    = 1;  //PC->Barramento Interno
        M[119][13]   = 1;  //Barramento Interno->MAR
        
        //T2
        M[120][16]   = 1;  //MAR->Barramento Externo
        M[120][20]   = 1;  //Barramento Externo->Memória
        M[120][28]   = 1;  //Comando Read
		
        //T3
        M[121][18]   = 1;  //Memória->Barramento Externo
        M[121][19]   = 1;  //Barramento Externo->MBR
        
        //T4
        M[122][2]    = 1;  //MBR->Barramento Interno
        M[122][10]   = 1;  //Barramento Interno->PC
        
        //T5
        M[123][30]   = 1;  //Pule sempre para o Endereço do último Bit
        
        
        ////  JE  ////    
        
        //T1
        M[124][31]   = 1;  //Pule se cumprir as condicoes (para o endereco do ultimo bit)
        M[124][32]   = 1;  //Flag de Sinal
        M[124][36]	 = 138;	 //Endereço da falha nas condições
		
        //T2
        M[125][36]   = 119;  //Endereço recebe o endereço do JMP
        M[125][30]   = 1;    //Pule sempre para o Endereço do último Bit
        
        
        
        ////  JL  ////    
        
        //T1
        M[126][31]   = 1;    //Pule se cumprir as condicoes (para o endereco do ultimo bit)
        M[126][32]   = 1;    //Flag de Sinal
        M[126][33]   = 1;    //Flag de Igualdade
        M[126][36]   = 119;  //Endereço recebe o endereço do JMP
        
        //T2
        M[127][30]   = 1;    //Pule sempre para o Endereço do último Bit
        M[127][36]	 = 138;	 //Endereço da falha nas condições
        
        ////  JLE  ////    
        
        //T1
        M[128][31]   = 1;    //Pule se cumprir as condicoes (para o endereco do ultimo bit)
        M[128][32]   = 1;    //Flag de Sinal
        M[128][33]   = 1;    //Flag de Igualdade
        M[128][36]   = 119;  //Endereço recebe o endereço do JMP
        
        //T2
		M[129][31]   = 1;    //Pule se cumprir as condicoes (para o endereco do ultimo bit)
        M[129][33]   = 1;    //Flag de Igualdade
        M[129][36]	 = 138;	 //Endereço da falha nas condições
		
        //T3
        M[130][36]   = 119;  //Endereço recebe o endereço do JMP
        M[130][30]   = 1;    //Pule sempre para o Endereço do último Bit
        
        
        
        ////  JG  ////    
        
        //T1
        M[131][31]   = 1;    //Pule se cumprir as condicoes (para o endereco do ultimo bit)
        M[131][32]   = 1;    //Flag de Sinal
        M[131][33]   = 1;    //Flag de Igualdade
		M[131][36]	 = 138;	 //Endereço da falha nas condições
        
        //T2
		M[132][31]   = 1;    //Pule se cumprir as condicoes (para o endereco do ultimo bit)
        M[132][33]   = 1;    //Flag de Igualdade
        M[132][36]   = 119;  //Endereço recebe o endereço do JMP
        
        //T3
        M[133][30]   = 1;    //Pule sempre para o Endereço do último Bit
        M[133][36]	 = 138;	 //Endereço da falha nas condições
        
        
        ////  JGE  ////    
        
        //T1
        M[134][31]   = 1;    //Pule se cumprir as condicoes (para o endereco do ultimo bit)
        M[134][32]   = 1;    //Flag de Sinal
		M[134][36]	 = 138;	 //Endereço da falha nas condições
        
        //T2
        M[135][36]   = 119;  //Endereço recebe o endereço do JMP
        M[135][30]   = 1;    //Pule sempre para o Endereço do último Bit
        
        
        
        ////  JNE  ////    
        
        //T1
        M[136][31]   = 1;    //Pule se cumprir as condicoes (para o endereco do ultimo bit)
        M[136][32]   = 1;    //Flag de Sinal
        M[136][36]   = 119;  //Endereço recebe o endereço do JMP
        
        //T2
        M[137][30]   = 1;    //Pule sempre para o Endereço do último Bit
		M[137][36]	 = 138;	 //Endereço da falha nas condições
		
		////  FALHA NA CONDICAO DO JUMP  ////
		
		//T1
		M[138][5]   = 1;  //PC->Barramento Interno
		M[138][14]   = 1;  //Barramento Interno->ULA
        M[138][27]   = 1;  //Comando ULA++
		
		//T2
		M[139][3]    = 1;  //ULA->Barramento Interno
        M[139][10]   = 1;  //Barramento Interno->PC
		
		//T3
		M[140][30]   = 1;  //Pule sempre para o Endereço do último Bit
    }
}