package modules.types;

public enum KontoTransaktionsType {
    ALL(""),
    NEDSKRIVNING("Nedskrivning af fordring"),
    OPRETTELSE("Oprettelse af fordring");
    
    private String text;
    
    private KontoTransaktionsType(String text) {
        this.text = text;
    }

    public String getTypeString(){
        return text;
    }
}
