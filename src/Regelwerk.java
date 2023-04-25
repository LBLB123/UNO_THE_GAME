import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Regelwerk {
    Scanner scanner = new Scanner(System.in);
    public ArrayList<Integer> reihenfolge;
    public ArrayList<Farbe> color;

    public void reihenfolgeFestlegen() { // spieler index in Array gespeichert
        reihenfolge = new ArrayList<Integer>();
        reihenfolge.add(0, 0); // index , value
        reihenfolge.add(1, 1);
        reihenfolge.add(2, 2);
        reihenfolge.add(3, 3);
    }

    public UnoKarte ersteKarte(UnoDeck hand, int zug, int anzahl) {
        if (zug == 1) {
            Random rand = new Random(); // random zahl wird generriert
            int n = rand.nextInt(anzahl); // random zahl von 0 bis DeckSize wird in n gespeichert
            UnoKarte aktuellekarte = hand.get(n); // n wird in aktuelle karte gespeichert
            System.out.println("Die Aktuelle Karte:");
            System.out.println(aktuellekarte); // ausgabe
            return aktuellekarte;
        }
        return hand.get(1);
    }
    public void aktuelleKarte(UnoKarte letzteKarte) {
        System.out.println("Die Aktuelle Karte:");
        System.out.println(letzteKarte); // ausgabe aktuelle karte
    }

    public int reihenfolge() {
        int aktuell = reihenfolge.get(0); // erster spieler wird gespeichert
        reihenfolge.remove(0); //erster spieler wird geklöscht
        reihenfolge.add(aktuell); // erster spieler wird am ende des arrays eingefügt
        return aktuell;
    }
    public void wunschFestlegen(){
        color = new ArrayList<Farbe>();
        color.add(0,Farbe.Wild);
    }

    public boolean karteLegbar(UnoKarte aktuellekarte, UnoKarte neueKarte, String PlayerName[], UnoDeck deck, SpielerHand spielerHand) {
        if (color.size() == 2) {// wird erst bei einem kartenWunsch genutzt
            System.out.println("Wunchfarbe: " + color.get(1));
            if ((color.get(1) == neueKarte.getFarbe()) || (color.get(0) == neueKarte.getFarbe())) { //  bedingung wunschfarbe selbe wie aktuelle Karten farbe  oder bedingung ist wild auf wild
                color.remove(1); // karte wird gelegt
                return Lege(aktuellekarte, neueKarte, PlayerName, deck, spielerHand);
            }else {
                return false;
            }
        } else { // wird aufgerufen wenn keine wunschfarbe existiert
            return Lege(aktuellekarte, neueKarte, PlayerName, deck, spielerHand);
        }
    }

    public boolean Lege(UnoKarte aktuellekarte, UnoKarte neueKarte,String PlayerName[], UnoDeck deck, SpielerHand spielerHand){
        if ((aktuellekarte.getFarbe() == neueKarte.getFarbe()) || (Farbe.Wild == neueKarte.getFarbe()) || ((aktuellekarte.getFarbe() == Farbe.Wild) && (Farbe.Wild != neueKarte.getFarbe()))) {// Farb kontrolle -> Bedingungen ob legbar oder nicht
            if (neueKarte.getWert() == Wert.Zwei_Ziehen) {
                Ziehen(PlayerName, deck, 2, spielerHand);
            } else if (neueKarte.getWert() == Wert.Aussetzen) {
                aussetzen();
            } else if (neueKarte.getWert() == Wert.Richtungswechsel) {
                richtungaendern();
            } else if (neueKarte.getWert() == Wert.Multicolor) {
                wunschFarbe();
            }else if (neueKarte.getWert() == Wert.Draw_Four) {
                wunschFarbe();
                Ziehen(PlayerName, deck, 4, spielerHand);
            }
            return true;
        } else if ((aktuellekarte.getWert() == neueKarte.getWert()) || (Farbe.Wild == neueKarte.getFarbe()) || ((aktuellekarte.getFarbe() == Farbe.Wild) && (Farbe.Wild != neueKarte.getFarbe()))) {// Wert kontrolle -> (zB gruene vier geht auft blaue vier)
            if (neueKarte.getWert() == Wert.Zwei_Ziehen) {
                Ziehen(PlayerName, deck, 2, spielerHand);
            } else if (neueKarte.getWert() == Wert.Aussetzen) {
                aussetzen();
            } else if (neueKarte.getWert() == Wert.Richtungswechsel) {
                richtungaendern();
            }else if (neueKarte.getWert() == Wert.Multicolor) {
                wunschFarbe();
            }else if (neueKarte.getWert() == Wert.Draw_Four) {
                wunschFarbe();
                Ziehen(PlayerName, deck, 4, spielerHand);
            }
            return true;
        }
        return false;
    }
    public void Ziehen(String PlayerName[], UnoDeck deck, int anzahl,SpielerHand spielerHand){
        int spieler = reihenfolge.get(0);
        spielerHand.befuelleSpieleHand(spieler, deck, anzahl);
    }
    public void aussetzen(){
        int aktuell = reihenfolge.get(0);// spieler wird in aktuell gespeichert
        reihenfolge.remove(0);// nächster spieler wird gelöscht
        reihenfolge.add(aktuell);// wird am ende des arrays hinzugefügt
    }
    public void richtungaendern(){
        int [] richtungswechsel = new int[4];// neuer array richtungswechsel
        for(int i = 0; i < reihenfolge.size(); i++){
            richtungswechsel[i] = reihenfolge.get(i);// kopiervorgang der spieler zu richtungswechsel
        }
        int size = reihenfolge.size();
        for (int i = 0; i < size; i++) {
            reihenfolge.remove(0);// löscht die einträge vom altem array
        }
        for(int i = 3; i >= 0; i--){
            reihenfolge.add(richtungswechsel[i]);// werte werden umgekehrt in den alten array wieder eingefügt
        }
        int zwischen = reihenfolge.get(0); // erster spieler wird in zwischen gespeichert
        reihenfolge.remove(0);// erster spieler wird gelöscht
        reihenfolge.add(zwischen);// erster spieler wird im array neu eingefügtr aber hinten
    }
    public void wunschFarbe(){
        System.out.println("Welche Farbe möchteswt du?(Gruen, Blau, Gelb, Rot)");
        switch (scanner.nextLine().toLowerCase()){
            case "gruen":
                color.add(Farbe.Gruen);// fügt in den array eine gruene Karte hinzu so kann als nächstes nur gruen gelegt werden
                break;
            case "blau":
                color.add(Farbe.Blau);// "
                break;
            case "gelb":
                color.add(Farbe.Gelb);// "
                break;
            case "rot":
                color.add(Farbe.Rot);// "
                break;
        }
    }
}
