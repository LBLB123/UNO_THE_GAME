import java.sql.SQLOutput;
import java.util.Scanner;

public class UNO {
    public static void main(String[] args) {
        UnoDeck deck = new UnoDeck();
        Regelwerk regel = new Regelwerk();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Zum Starten, Start schreiben");
        String start = scanner.nextLine();
        if ("start".equals(start.toLowerCase())) {
            deck.befuellen();
            deck.mischeHand();
            regel.reihenfolgeFestlegen();

            //Spiler Namen Abfrage und Anzahl der Spieler

            System.out.println("Kurze Information: mind. 2 Player max. 4 Player");
            System.out.println("Enter Player Names:");
            int index;
            int PlayerAnzahl = 0;
            String Antwort;
            String PlayerNameInput;
            String PlayerName[] = {"Joy Bot","Note Bot","Quick Bot","CyBot"};
            for (index = 0; index < 4; ) {
                PlayerAnzahl++;
                PlayerNameInput = scanner.nextLine();
                PlayerName[index] = PlayerNameInput;
                index++;
                if (index >= 2) {
                    System.out.println("Sind das die Maximalen Spieler?");
                    Antwort = scanner.nextLine();
                    if ("ja".equals(Antwort.toLowerCase())) {
                        index = 4;
                    } else if (index != 4) {
                        System.out.println("Weitere Spieler:");
                    }
                }
            }

            SpielerHand spielerHand = new SpielerHand(PlayerName[0], PlayerName[1], PlayerName[2], PlayerName[3]);
            spielerHand.befuelleSpieleHand(0,deck,7);
            spielerHand.befuelleSpieleHand(1,deck,7);
            spielerHand.befuelleSpieleHand(2,deck,7);
            spielerHand.befuelleSpieleHand(3,deck,7);


            //GameLoop:
            boolean win = false;
            int zug = 1;
            UnoKarte aktuelleKarte = regel.ersteKarte(deck,zug);
            int legeKarte;
            boolean geht = false;
            while (!win) {
                System.out.println("Zum Zug Starten, Start schreiben");
                if("start" .equals(scanner.nextLine().toLowerCase())) {
                    zug++;
                    int spieler = regel.reihenfolge();
                    spielerHand.zeigeHaende(spieler);
                    System.out.println("legen oder ziehen");
                        if ("legen".equals(scanner.nextLine().toLowerCase())) {
                            System.out.println("Welche Karte?(Index)");
                            legeKarte = Integer.parseInt(scanner.nextLine());
                            UnoKarte neueKarte = spielerHand.spielerHaende.get(spieler).get(legeKarte-1);
                            geht = true;
                            if (geht == regel.karteLegbar(aktuelleKarte, neueKarte,PlayerName)) {
                                spielerHand.entferneKarteHand(spieler, legeKarte, deck, zug);
                                System.out.println("karte wurde gelegt");
                                geht = false;
                            }else {
                                while(geht == true) {
                                    System.out.println("karte wurde nicht gelegt");
                                    System.out.println("Weiterhin legen?(ja/nein)");
                                    if ("ja".equals(scanner.nextLine().toLowerCase())) {
                                        System.out.println("Welche Karte?(Index)");
                                        legeKarte = Integer.parseInt(scanner.nextLine());
                                        neueKarte = spielerHand.spielerHaende.get(spieler).get(legeKarte-1);
                                        if (geht == regel.karteLegbar(aktuelleKarte, neueKarte,PlayerName)) {
                                            spielerHand.entferneKarteHand(spieler, legeKarte, deck, zug);
                                            System.out.println("karte wurde gelegt");
                                            geht = false;
                                        }
                                    }else {
                                        spielerHand.befuelleSpieleHand(spieler, deck, 1);
                                        System.out.println("Karte wurde gezogen");
                                        geht = false;
                                    }
                                }
                            }
                        } else {
                            spielerHand.befuelleSpieleHand(spieler, deck, 1);
                            int size = spielerHand.spielerHaende.get(spieler).size();
                            System.out.println("Gezogene Karte: " + spielerHand.spielerHaende.get(spieler).get(size - 1));
                            System.out.println("legen oder behalten?");
                            if ("legen".equals(scanner.nextLine().toLowerCase())) {
                                UnoKarte neueKarte = spielerHand.spielerHaende.get(spieler).get(size - 1);
                                System.out.println(neueKarte);
                                geht = true;
                                if (geht == regel.karteLegbar(aktuelleKarte, neueKarte,PlayerName)) {
                                    spielerHand.entferneKarteHand(spieler, size, deck, zug);
                                    System.out.println("karte wurde gelegt");
                                    geht = false;
                                }else {
                                    System.out.println("Karte wurde nicht gelegt");
                                }
                            }
                        }
                    if(spielerHand.spielerHaende.get(spieler).size() == 1){
                        System.out.println("Möchtest du Uno sagen?");
                        if("uno" .equals(scanner.nextLine().toLowerCase())){
                            continue;
                        }else {
                            spielerHand.befuelleSpieleHand(spieler, deck, 1);
                        }
                    }
                    if(spielerHand.spielerHaende.get(spieler).size() == 0){
                        win = true;
                    }
                }
            }
        }
    }
}


