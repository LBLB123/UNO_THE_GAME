import java.util.ArrayList;
import java.util.Collections;

public class UnoDeck {
    protected ArrayList<UnoKarte> hand;

    public UnoDeck() {
        hand = new ArrayList<UnoKarte>();
    }

    public void fuegeKarteHinzu(UnoKarte karte) {
        hand.add(karte);
        //Diese Methode fügt Karten in die UnoDeck (Algemeine Deck) hinzu
    }
    public UnoKarte get(int index){
        return hand.get(index);
        //Diese methode gibt einen Wert aus der UnoHand zurück
    }

    public void mischeHand() {
        Collections.shuffle(hand);
        //Diese Methode mischt die UnoHand
    }

    public void zeigeDeck() {
        for (UnoKarte karte : hand) {
            System.out.println(karte);
            //Diese Methode gibt das Array der UnoHand aus
        }
    }
    public Wert wertVonZahl(int zahl) {
        switch (zahl) {
            case 0:
                return Wert.Null;
            case 1:
                return Wert.Eins;
            case 2:
                return Wert.Zwei;
            case 3:
                return Wert.Drei;
            case 4:
                return Wert.Vier;
            case 5:
                return Wert.Fuenf;
            case 6:
                return Wert.Sechs;
            case 7:
                return Wert.Sieben;
            case 8:
                return Wert.Acht;
            case 9:
                return Wert.Neun;
            default:
                throw new IllegalArgumentException("Ungültige Zahl: " + zahl);

                //Diese Methode Wandelt Natürliche Zahlen von 0 bis 9 in wert um
        }
    }


    public void befuellen() {
        for (Farbe farbe : Farbe.values()) {
            if (farbe == Farbe.Wild) {
                continue;
            }
            for (int zahl = 0; zahl <= 9; zahl++) {
                fuegeKarteHinzu(new UnoKarte(farbe, wertVonZahl(zahl)));
                if (zahl != 0) {
                    fuegeKarteHinzu(new UnoKarte(farbe, wertVonZahl(zahl)));
                }
            }
            for (int i = 0; i < 2; i++) {
                fuegeKarteHinzu(new UnoKarte(farbe, Wert.Richtungswechsel));
                fuegeKarteHinzu(new UnoKarte(farbe, Wert.Aussetzen));
                fuegeKarteHinzu(new UnoKarte(farbe, Wert.Zwei_Ziehen));
            }
        }
        for (int i = 0; i < 4; i++) {
            fuegeKarteHinzu(new UnoKarte(Farbe.Wild, Wert.Multicolor));
            fuegeKarteHinzu(new UnoKarte(Farbe.Wild, Wert.Draw_Four));
        }
        //Diese Methode verbindet die Farben und die Zahlen der jeweiligen enums
    }
}

