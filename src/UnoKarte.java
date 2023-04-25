public class UnoKarte {
    private Farbe farbe;
    private Wert wert;

    public UnoKarte(Farbe farbe, Wert wert) {
        this.farbe = farbe;
        this.wert = wert;
    }

    public Farbe getFarbe() {
        return farbe;
    }

    public Wert getWert() {
        return wert;
    }


    public String toString() {
        return farbe.toString() + " " + wert.toString();
    }
}

enum Farbe {
    Rot, Blau, Gruen, Gelb, Wild
    //Das sind die jeweiligen Farben der karten
}

enum Wert {
    Null, Eins, Zwei, Drei, Vier, Fuenf, Sechs, Sieben, Acht, Neun, Aussetzen, Richtungswechsel, Zwei_Ziehen, Multicolor, Draw_Four
    //Das sind die eigenschaften der jeweiligen Karten
}
