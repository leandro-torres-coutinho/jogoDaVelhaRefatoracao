package jogoDaVelha;

import java.util.Random;
import java.util.Scanner;

public class Jogo extends BaseDeConhecimento{

    BaseDeConhecimento baseDeConhecimento;

    int ganhador = 0;
    Scanner sc = new Scanner(System.in);

    public Jogo() {
    	baseDeConhecimento = new BaseDeConhecimento();
    }

    public void Joga() {
    	
        while (baseDeConhecimento.getRodada() == 0) {
            System.out.println("Quem inicia o jogo? Jogador ou Máquina? (J/M)");
            switch (sc.nextLine()) {
                case "J":
                case "j":
                    baseDeConhecimento.setRodadaInicial(JogadorEnum.HUMANO.getId()); 
                    break;
                case "M":
                case "m":
                	baseDeConhecimento.setRodadaInicial(JogadorEnum.MAQUINA.getId()); 
                    break;
                default:
                    System.out.println("Comando não reconhecido");
            }
        }
        
        System.out.println("Jogador joga com O ou X? (O/X)");
        switch (sc.nextLine()) {
	        case "O":
	        case "o":
	        case "0":
	        	baseDeConhecimento.setSimboloHumano("O");
	        	baseDeConhecimento.setSimboloMaquina("X");
	            break;
	        case "X":
	        case "x":
	        	baseDeConhecimento.setSimboloHumano("X");
	        	baseDeConhecimento.setSimboloMaquina("O");
	            break;
	        default:
	            System.out.println("Comando não reconhecido");
        }
        
        MotorDeInferencia motorDeInferencia = new MotorDeInferencia();
        while (ganhador == 0) {
        	if(baseDeConhecimento.getRodada() == JogadorEnum.HUMANO.getId()){
	    		imprimeBase();
	    		System.out.println("Digite o número da posição em que deseja jogar");
	            baseDeConhecimento.setPosicao(Integer.parseInt(sc.nextLine()));
        	}
        	try{
	            motorDeInferencia.round(baseDeConhecimento);
        	}catch(Exception e){
        		System.out.println(e.getMessage());
        	}finally{
        		if(baseDeConhecimento.getRodada() == JogadorEnum.HUMANO.getId())
        			System.out.println("Jogada da máquina:");
        		imprimeTabuleiro();
            	int[][] tabuleiro = baseDeConhecimento.getTabuleiro();
            	int testaFim = baseDeConhecimento.testaFim(tabuleiro);
            	if (testaFim != 1) {
                    ganhador = testaFim;
                } else if (testaFim == 2) {
                    ganhador = testaFim;
                    System.out.println("O jogo terminou. Deu velha");
                }
        	}
        }
        if (ganhador == 2) {
            System.out.println("Deu velha.");
        } else if (ganhador != 0) {
            System.out.println("O Jogo acabou.");
            System.out.println(defineGanhador() + " ganhou");
        }
    }

   

    public void efetuaJogada(int posicao, int jogador) {
    	int[][] base = baseDeConhecimento.getBase();
    	int[][] tabuleiro = baseDeConhecimento.getTabuleiro();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (base[i][j] == posicao) {
                    if (tabuleiro[i][j] != JogadorEnum.HUMANO.getId() && tabuleiro[i][j] != JogadorEnum.MAQUINA.getId()) {
                        tabuleiro[i][j] = jogador;
                    }
                }
            }
        }
    }

    public String defineGanhador() {
    	int[][] tabuleiro = baseDeConhecimento.getTabuleiro();
    	int testaFim = baseDeConhecimento.testaFim(tabuleiro);
        if (testaFim != JogadorEnum.HUMANO.getId()) {
            return "Máquina";
        } else if (testaFim != JogadorEnum.MAQUINA.getId()) {
            return "Jogador";
        }
        return "velha";
    }
    
    public void imprimeTabuleiro() {
        System.out.println("Tabuleiro: ");
        int[][] tabuleiro = baseDeConhecimento.getTabuleiro();
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (tabuleiro[x][y] == JogadorEnum.HUMANO.getId()) {
                    System.out.print(baseDeConhecimento.getSimboloHumano());
                } else if (tabuleiro[x][y] == JogadorEnum.MAQUINA.getId()) {
                    System.out.print(baseDeConhecimento.getSimboloMaquina());
                } else if (tabuleiro[x][y] == 0) {
                    System.out.print(" ");
                } else {
                    System.out.print(tabuleiro[x][y]);
                }
                if (y < 2) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public void imprimeBase() {
        System.out.println("Base: ");
        int[][] base = getBase();
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (base[x][y] == JogadorEnum.HUMANO.getId()) {
                    System.out.print(baseDeConhecimento.getSimboloHumano());
                } else if (base[x][y] == JogadorEnum.MAQUINA.getId()) {
                    System.out.print(baseDeConhecimento.getSimboloMaquina());
                } else //System.out.print(" ");
                {
                    System.out.print(base[x][y]);
                }
                if (y < 2) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
        }
    }
}
