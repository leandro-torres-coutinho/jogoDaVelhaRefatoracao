package jogoDaVelha;

public enum JogadorEnum { 
	HUMANO(10),
    MAQUINA(11);
    
    private final int id;

    private JogadorEnum(int id) {
        this.id = id;
    }
    
    public int getId() {
        return this.id;
    }
};


