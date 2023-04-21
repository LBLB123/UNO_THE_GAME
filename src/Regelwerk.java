import java.util.ArrayList;
import java.util.Random;

public class Regelwerk {

    public ArrayList<Integer> reihenfolge;

    public void reihenfolgeFestlegen(){
        reihenfolge = new ArrayList<Integer>();
        reihenfolge.add(0,0);
        reihenfolge.add(1,1);
        reihenfolge.add(2,2);
        reihenfolge.add(3,3);
    }

    public UnoKarte ersteKarte(UnoDeck hand, int zug){
        if(zug == 1) {
            Random rand = new Random();
            int n = rand.nextInt(53);
            UnoKarte aktuellekarte = hand.get(n);
            System.out.println("Die Aktuelle Karte:");
            System.out.println(aktuellekarte);
            return aktuellekarte;
        }
        return hand.get(1);
    }
    public void aktuelleKarte(UnoKarte letzteKarte){
        System.out.println("Die Aktuelle Karte:");
        System.out.println(letzteKarte);
    }

    public int reihenfolge(){
        int aktuell = reihenfolge.get(0);
        reihenfolge.remove(0);
        reihenfolge.add(aktuell);
        return aktuell;

    }
    public Boolean karteLegbar(UnoKarte aktuellekarte, UnoKarte neueKarte,String PlayerName[]){
        if(aktuellekarte.getFarbe() == neueKarte.getFarbe()){
            if(neueKarte.getWert() == Wert.Zwei_Ziehen){
                UnoDeck deck = new UnoDeck();
                SpielerHand spielerHand = new SpielerHand(PlayerName[0], PlayerName[1], PlayerName[2], PlayerName[3]);
                spielerHand.befuelleSpieleHand(reihenfolge()+1,deck,2);
            } else if (neueKarte.getWert() == Wert.Aussetzen) {
                int aktuell = reihenfolge.get(0);
                reihenfolge.remove(0);
                reihenfolge.add(aktuell);
            } else if (neueKarte.getWert() == Wert.Richtungswechsel) {
                int [] richtungswechsel = new int[4];
                for(int i = 0; i < reihenfolge.size(); i++){
                    richtungswechsel[i] = reihenfolge.get(i);
                    System.out.println(richtungswechsel[i]);
                }
                int size = reihenfolge.size();
                for(int i = 0; i < size; i++){
                    reihenfolge.remove(0);
                    System.out.println(reihenfolge);
                }
                for(int i = 3; i >= 0; i--){
                    reihenfolge.add(richtungswechsel[i]);
                    System.out.println(reihenfolge);
                }
                int zwischen = reihenfolge.get(0);
                reihenfolge.remove(0);
                reihenfolge.add(zwischen);
            }
            return true;
        }
        if (aktuellekarte.getWert() == neueKarte.getWert()){
                if (neueKarte.getWert() == Wert.Zwei_Ziehen) {
                    UnoDeck deck = new UnoDeck();
                    SpielerHand spielerHand = new SpielerHand(PlayerName[0], PlayerName[1], PlayerName[2], PlayerName[3]);
                    spielerHand.befuelleSpieleHand(reihenfolge() + 1, deck, 2);
                }else if (neueKarte.getWert() == Wert.Aussetzen) {
                    int aktuell = reihenfolge.get(0);
                    reihenfolge.remove(0);
                    reihenfolge.add(aktuell);
                }else if (neueKarte.getWert() == Wert.Richtungswechsel) {
                    int [] richtungswechsel = new int[4];
                    for(int i = 0; i < reihenfolge.size(); i++){
                        richtungswechsel[i] = reihenfolge.get(i);
                    }
                    int size = reihenfolge.size();
                    for(int i = 0; i < size;i++){
                        reihenfolge.remove(0);
                    }
                    for(int i = 3; i >= 0; i--){
                        reihenfolge.add(richtungswechsel[i]);
                    }
                    int zwischen = reihenfolge.get(0);
                    reihenfolge.remove(0);
                    reihenfolge.add(zwischen);
                }
            return true;
        }
        if (neueKarte.getFarbe() == Farbe.Wild){
            if(neueKarte.getWert() == Wert.Multicolor){

            }
            return true;
        } else {
            return false;
        }
    }
}
