import java.util.ArrayList;
import java.util.Random;

public class SpielerHand{
    private String[] spielerNamen;
    public ArrayList<ArrayList<UnoKarte>> spielerHaende;

    public SpielerHand(String spieler1, String spieler2, String spieler3, String spieler4) { //1
        spielerNamen = new String[] {spieler1, spieler2, spieler3, spieler4}; // in spielerNamen werden alle spielernamen die übergeben worden gespeichert
        spielerHaende = new ArrayList<ArrayList<UnoKarte>>(); // erstellen vom 2 dimesionalem array
        for (int i = 0; i < 4; i++) {
            spielerHaende.add(new ArrayList<UnoKarte>()); //in dem einen array 4 weitere arrays erstellen
            //Diese Methode erstellt zu jedem Spieler eine ArrayListe
        }
    }

    public void fuegeKarteHinzu(int spielerIndex, UnoDeck deck, int kartenIndex) {//3
        UnoKarte karte = deck.get(kartenIndex); // karte wird erstellt mit der random n als kartenIndex
        spielerHaende.get(spielerIndex).add(karte); //spielerhand wird karte hinzugefügt
        deck.hand.remove(kartenIndex);//karte wird aus dem deck entfernt
        //Diese Fuktion fügt karten in die Hand des jeweiligen spielers
    }

    public void entferneKarteHand(int spielerIndex, int kartenIdex, UnoDeck deck, int zug){//5
        Regelwerk regel = new Regelwerk();
        kartenIdex = kartenIdex-1; // Abfrage auf der konsole ist von 1-7 aber im array ist es von 0-6 deswegen -1
        regel.aktuelleKarte(spielerHaende.get(spielerIndex).get(kartenIdex)); // aktuelle karte wird ausgegeben
        deck.hand.add(spielerHaende.get(spielerIndex).get(kartenIdex));//karte wird dem deck hinzugefügt
        spielerHaende.get(spielerIndex).remove(kartenIdex); //karte wird aus der hand des spielers entfernt
        //Diese Methode entfernt eine Karte aus der Hand des Spielers
    }

    public void zeigeHaende(int indexSpieler) {//4
        System.out.println(spielerNamen[indexSpieler] + ":");
        int j = 1;// Index der Karten auf der hand
        for (UnoKarte karte : spielerHaende.get(indexSpieler)) {
            System.out.print("Index: " + j +"\t");
            System.out.println(karte);//Karte wird ausgegeben
            j++;//zahl wird erhöht für die nächste Karte
        }
        System.out.println();
        //Diese Methode zeigt die Hand des Spielers
    }
    public void befuelleSpieleHand(int spielerIndex, UnoDeck deck, int anzahl){//2
        Random rand = new Random(); // random zahl wird in rand gespeichert
        for (int i = 0; i < anzahl; i++) {
            int n = rand.nextInt(deck.hand.size()); // random zahl von 0 bis DeckSize
            fuegeKarteHinzu(spielerIndex, deck, n); // methode wird aufgerufen
        }
        //Diese Methode gibt die Anzhal der Karten die der Spieler bekommen soll und wählt die zufähligge karte aus
    }
    public void ranking(){//6
        for (int i = 0; i < spielerHaende.size(); i++){
            System.out.println(spielerNamen[i] + " Menge an Karten: " +spielerHaende.get(i).size());// spieler werden azsgegeben und die menge an karten in der jeweiligen hand aber nicht sortiert
        }
    }
}


