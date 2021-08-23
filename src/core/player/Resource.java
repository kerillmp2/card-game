package core.player;

public enum Resource {
    MANA("Мана", "Маны"),
    PHYSICAL("Физический", "Физического"),
    FIRE("Огненный", "Огненного"),
    WATER("Водный", "Водного"),
    WIND("Ветренный", "Ветренного"),
    ELECTRIC("Электрический", "Электрического"),
    PSY("Психический", "Психического"),
    ICE("Ледяной", "Ледяного"),
    SLIME("Слизневый", "Слизневого"),
    DARK("Тёмный", "Тёмного"),
    LIGHT("Свтелый", "Светлого");

    private String name;
    private String rod;

    Resource(String name, String rod) {
        this.name = name;
        this.rod = rod;
    }

    public String getName(){ return this.name; }
    public String getRod(){ return this.rod; }
}