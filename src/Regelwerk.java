import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Regelwerk {
    Scanner scanner = new Scanner(System.in);
    public ArrayList<Integer> reihenfolge;
    public ArrayList<Farbe> color;

    public void reihenfolgeFestlegen() {
        reihenfolge = new ArrayList<Integer>();
        reihenfolge.add(0, 0);
        reihenfolge.add(1, 1);
        reihenfolge.add(2, 2);
        reihenfolge.add(3, 3);
    }

    public UnoKarte ersteKarte(UnoDeck hand, int zug, int anzahl) {
        if (zug == 1) {
            Random rand = new Random();
            int n = rand.nextInt(anzahl);
            UnoKarte aktuellekarte = hand.get(n);
            System.out.println("Die Aktuelle Karte:");
            System.out.println(aktuellekarte);
            return aktuellekarte;
        }
        return hand.get(1);
    }
    public void aktuelleKarte(UnoKarte letzteKarte) {
        System.out.println("Die Aktuelle Karte:");
        System.out.println(letzteKarte);
    }

    public int reihenfolge() {
        int aktuell = reihenfolge.get(0);
        reihenfolge.remove(0);
        reihenfolge.add(aktuell);
        return aktuell;
    }
    public void wunschFestlegen(){
        color = new ArrayList<Farbe>();
        color.add(0,Farbe.Wild);
    }

    public boolean karteLegbar(UnoKarte aktuellekarte, UnoKarte neueKarte, String PlayerName[], UnoDeck deck, SpielerHand spielerHand) {
        if (color.size() == 2) {
            System.out.println("Wunchfarbe: " + color.get(1));
            if ((color.get(1) == neueKarte.getFarbe()) || (color.get(0) == neueKarte.getFarbe())) {
                color.remove(1);
                return Lege(aktuellekarte, neueKarte, PlayerName, deck, spielerHand);
            }else {
                return false;
            }
        } else {
            return Lege(aktuellekarte, neueKarte, PlayerName, deck, spielerHand);
        }
    }

    public boolean Lege(UnoKarte aktuellekarte, UnoKarte neueKarte,String PlayerName[], UnoDeck deck, SpielerHand spielerHand){
        if ((aktuellekarte.getFarbe() == neueKarte.getFarbe()) || (Farbe.Wild == neueKarte.getFarbe()) || ((aktuellekarte.getFarbe() == Farbe.Wild) && (Farbe.Wild != neueKarte.getFarbe()))) {
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
        } else if ((aktuellekarte.getWert() == neueKarte.getWert()) || (Farbe.Wild == neueKarte.getFarbe()) || ((aktuellekarte.getFarbe() == Farbe.Wild) && (Farbe.Wild != neueKarte.getFarbe()))) {
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
        int aktuell = reihenfolge.get(0);
        reihenfolge.remove(0);
        reihenfolge.add(aktuell);
    }
    public void richtungaendern(){
        int [] richtungswechsel = new int[4];
        for(int i = 0; i < reihenfolge.size(); i++){
            richtungswechsel[i] = reihenfolge.get(i);
        }
        int size = reihenfolge.size();
        for (int i = 0; i < size; i++) {
            reihenfolge.remove(0);
        }
        for(int i = 3; i >= 0; i--){
            reihenfolge.add(richtungswechsel[i]);
        }
        int zwischen = reihenfolge.get(0);
        reihenfolge.remove(0);
        reihenfolge.add(zwischen);
    }
    public void wunschFarbe(){
        System.out.println("Welche Farbe möchteswt du?(Gruen, Blau, Gelb, Rot)");
        switch (scanner.nextLine().toLowerCase()){
            case "gruen":
                color.add(Farbe.Gruen);
                break;
            case "blau":
                color.add(Farbe.Blau);
                break;
            case "gelb":
                color.add(Farbe.Gelb);
                break;
            case "rot":
                color.add(Farbe.Rot);
                break;
        }
    }
}
