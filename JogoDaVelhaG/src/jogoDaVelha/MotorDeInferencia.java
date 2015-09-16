package jogoDaVelha;

import java.util.Random;

public class MotorDeInferencia {

	Random aleatorio = new Random();
	BaseDeConhecimento baseDeConhecimento = new BaseDeConhecimento();
	
    public MotorDeInferencia() {
    	baseDeConhecimento = new BaseDeConhecimento();
	}

	public void realizaJogada(BaseDeConhecimento baseDeConhecimento) throws Exception{
		
		this.baseDeConhecimento = baseDeConhecimento;
		if(baseDeConhecimento.getRodada()==JogadorEnum.HUMANO.getId()){
			if (!baseDeConhecimento.validaPosicao()) throw new Exception("Posição inválida. Tente novamente");			
		}
    	if (baseDeConhecimento.getRodada() == JogadorEnum.MAQUINA.getId()) {
    		while(!baseDeConhecimento.validaPosicao()){
    			baseDeConhecimento.setPosicao(jogadaAleatoria());
    		}
        }
        efetuaJogada(baseDeConhecimento.getPosicao(), baseDeConhecimento.getRodada());
        baseDeConhecimento.removePosicao(baseDeConhecimento.getPosicao());
    }
	
	private int jogadaAleatoria() {
		int posicao;
		posicao = aleatorio.nextInt(9);
		return posicao;
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
        baseDeConhecimento.mudaRodada();
    }
  }
