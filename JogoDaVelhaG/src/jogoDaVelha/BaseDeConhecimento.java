package jogoDaVelha;

import java.util.ArrayList;

public class BaseDeConhecimento {

	private static final int REMOVEPOSICAO = 55;
	private int tabuleiro[][] = new int[3][3];
	private int base[][] = new int[3][3];
	private String simboloMaquina;
	private String simboloHumano;
	private int posicao;
	private int aux[] = new int[9];
	private int rodada;
	
    public BaseDeConhecimento() {
		super();
		inicializandoBase();
		inicializandoBaseAuxiliar();
	}

	private void inicializandoBaseAuxiliar() {
		for (int cont = 0; cont <= 8; cont++) {
            aux[cont]= cont + 1;
        }
	}
    
    private void inicializandoBase() {
        int aux = 1;
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                base[x][y] = aux;
                aux++;
            }
        }
    }
    
	public int testaFim(int base[][]) {
		int possivelGanhador = possivelGanhador(base);
        if ( possivelGanhador == 0) {
        	int definicaoGanhador = defineGanhador(base);
            if (definicaoGanhador != 1) {
                return (definicaoGanhador);
            }
        }else if (possivelGanhador == 1){
        	return 2;
        }
        return 1;
    }

    public int defineGanhador(int matriz[][]) {
        for (int i = 0; i < 3; i++) // testando linha
        {
            if ((matriz[i][0] == matriz[i][1]) && (matriz[i][1] == matriz[i][2]) && (matriz[i][1] != 0)) {
                // System.out.println("O ganhador é linha "+matriz[i][0]);
                return matriz[i][0];
            }
        }
        for (int j = 0; j < 3; j++) // testando coluna
        {
            if ((matriz[0][j] == matriz[1][j]) && (matriz[1][j] == matriz[2][j]) && (matriz[1][j] != 0)) {
                // System.out.println("O ganhador é coluna "+matriz[0][j]);
                return matriz[0][j];
            }
        }
        if ((matriz[2][0] == matriz[1][1]) && (matriz[1][1] == matriz[0][2]) && (matriz[1][1] != 0)) { // testando diagonal secundaria
            // System.out.println("O ganhador é diagonal secundaria "+matriz[2][0]);
            return matriz[2][0];
        }
        if ((matriz[0][0] == matriz[1][1]) && (matriz[0][0] == matriz[2][2]) && (matriz[0][0] != 0)) { // testando diagonal principal
            // System.out.println("O ganhador é diagonal principal "+matriz[0][0]);
            return matriz[0][0];
        }
        return 1;
    }

    public int possivelGanhador(int base[][]) {
        int maquina = 0, homem = 0, ganhopossivel = 8;
        for (int i = 0; i < 3; i++) { // testando linha
            for (int j = 0; j < 3; j++) {
                if (base[i][j] == 10) {
                    maquina++;
                } else if (base[i][j] == 11) {
                    homem++;
                }
            }
            if (maquina >=1 && homem >= 1) {
                ganhopossivel--;
            }
        }
        maquina = homem = 0;
        for (int i = 0; i < 3; i++) { // testando coluna
            for (int j = 0; j < 3; j++) {
                if (base[j][i] == 10) {
                    maquina++;
                } else if (base[j][i] == 11) {
                    homem++;
                }
            }
            if (maquina >=1 && homem >= 1) {
                ganhopossivel--;
            }
        }
        maquina = homem = 0;
        for (int i = 0; i < 3; i++) {
            if (base[i][i] == 10) {
                maquina++;
            } else if (base[i][i] == 11) {
                homem++;
            }
        }
        if (maquina >=1 && homem >= 1) {
            ganhopossivel--;
        }
        maquina = homem = 0;
        for (int i = 2,j=0; i >= 0; i--,j++) {
            if (base[i][j] == 10) {
                maquina++;
            } else if (base[i][j] == 11) {
                homem++;
            }
        }
        if (maquina >=1 && homem >= 1) {
            ganhopossivel--;
        }
        if (ganhopossivel == 0) {
            return 1;
        }
        return 0;
    }

	public void setRodadaInicial(int rodada) {
		this.rodada = rodada;
	}
	
	public boolean validaPosicao() {
        for (int proc = 0; proc <= 8; proc++) {
            if (aux[proc] == this.posicao) {
                return true;
            }
        }
        return false;
    }

	public int[][] getBase() {
		return base;
	}

	public void removePosicao(int posicao) {
		for (int i = 0; i <= 8; i++) {
            if (aux[i] == posicao) {
                this.aux[i] = REMOVEPOSICAO;
            }
        }
	}
	
	public int getPosicao() {
		return posicao;
	}

	public void setPosicao(int posicao) {
		this.posicao = posicao;
	}

	public int[][] getTabuleiro() {
        return tabuleiro;
    }
	
	public int[] getAux() {
		return aux;
	}

	public void setAux(int[] aux) {
		this.aux = aux;
	}
	
	public int getRodada() {
		return rodada;
	}
	
	public void mudaRodada() {
		if(rodada ==  JogadorEnum.HUMANO.getId()){
			this.rodada = JogadorEnum.MAQUINA.getId();
		}else{
			this.rodada = JogadorEnum.HUMANO.getId();
		}
	}

    public String getSimboloHumano() {
		return simboloHumano;
	}

	public void setSimboloHumano(String simboloHumano) {
		this.simboloHumano = simboloHumano;
	}

    public String getSimboloMaquina() {
		return simboloMaquina;
	}

	public void setSimboloMaquina(String simboloMaquina) {
		this.simboloMaquina = simboloMaquina;
	}
}
