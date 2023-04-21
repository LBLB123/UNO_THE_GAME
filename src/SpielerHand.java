import java.util.ArrayList;
import java.util.Random;

public class SpielerHand {
    private String[] spielerNamen;
    public ArrayList<ArrayList<UnoKarte>> spielerHaende;

    public SpielerHand(String spieler1, String spieler2, String spieler3, String spieler4) {
        spielerNamen = new String[] {spieler1, spieler2, spieler3, spieler4};
        spielerHaende = new ArrayList<ArrayList<UnoKarte>>();
        for (int i = 0; i < 4; i++) {
            spielerHaende.add(new ArrayList<UnoKarte>());
            //Diese Methode erstellt zu jedem Spieler eine ArrayListe
        }
    }

    public void fuegeKarteHinzu(int spielerIndex, UnoDeck hand, int kartenIndex) {
        UnoKarte karte = hand.get(kartenIndex);
        spielerHaende.get(spielerIndex).add(karte);
        //Diese Fuktion fügt karten in die Hand des jeweiligen spielers
    }

    public void entferneKarteHand(int spielerIndex, int kartenIdex, UnoDeck hand, int zug){
        Regelwerk regel = new Regelwerk();
        kartenIdex = kartenIdex-1;
        regel.aktuelleKarte(spielerHaende.get(spielerIndex).get(kartenIdex));
        spielerHaende.get(spielerIndex).remove(kartenIdex);
        //Diese Methode entfernt eine Karte aus der Hand des Spielers
    }

    public void zeigeHaende(int indexSpieler) {
            System.out.println(spielerNamen[indexSpieler] + ":");
            int j = 1;
            for (UnoKarte karte : spielerHaende.get(indexSpieler)) {
                System.out.print("Index: " + j +"\t");
                System.out.println(karte);
                j++;
            }
            System.out.println();
        //Diese Methode zeigt die Hand des Spielers
    }
    public void befuelleSpieleHand(int spielerIndex, UnoDeck hand, int anzahl){
        Random rand = new Random();
        for (int i = 0; i < anzahl; i++) {
            int n = rand.nextInt(53);
            fuegeKarteHinzu(spielerIndex, hand, n);
        }
        //Diese Methode gibt die Anzhal der Karten die der Spieler bekommen soll und wählt die zufähligge karte aus
    }
}
