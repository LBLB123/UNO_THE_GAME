import java.util.Scanner;


public class UNO {
    public static void main(String[] args) {
        //Die benötigten Klassen werden aufgerufen
        UnoDeck deck = new UnoDeck();
        Regelwerk regel = new Regelwerk();
        Scanner scanner = new Scanner(System.in);

        // Sobald start geschrieben wurde, wird das ganze spiel erstellt
        System.out.println("Zum Starten, Start schreiben");
        String start = scanner.nextLine();
        if ("start".equals(start.toLowerCase())) {
            deck.befuellen();                       //Befüllt das deck
            deck.mischeHand();                      //mischehand mischt das Deck
            regel.reihenfolgeFestlegen();
            regel.wunschFestlegen();                //

            //Spieler Namen Abfrage und Anzahl der Spieler
            System.out.println("Kurze Information: mind. 2 Player max. 4 Player");
            System.out.println("Enter Player Names:");
            int index;
            int PlayerAnzahl = 0;
            String Antwort;
            String PlayerNameInput;
            String PlayerName[] = {"Joy Bot", "Note Bot", "Quick Bot", "CyBot"};
            //Spielernamen werden befüllt
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

            //Die Klasse Spielerhand wird aufgerufen und die PlayerName Paramentern werden übergeben
            SpielerHand spielerHand = new SpielerHand(PlayerName[0], PlayerName[1], PlayerName[2], PlayerName[3]);
            spielerHand.befuelleSpieleHand(0,deck,7);
            spielerHand.befuelleSpieleHand(1,deck,7);
            spielerHand.befuelleSpieleHand(2,deck,7);
            spielerHand.befuelleSpieleHand(3,deck,7);




            //GameLoop:
            boolean win = false;
            int zug = 1;
            UnoKarte aktuelleKarte = regel.ersteKarte(deck,zug,deck.hand.size());
            int legeKarte;
            boolean geht = false;   //Das boolean hilft bei der kontrolle, ob die Karte legbar ist!!!!
            while (!win) {
                //Zug wird gestartet, sobald start geschrieben wird
                System.out.println("Zum Zug Starten, Start schreiben");
                if("start" .equals(scanner.nextLine().toLowerCase())) {
                    System.out.println("Die Aktuelle Karte: \n" + aktuelleKarte);
                    zug++;
                    int spieler = regel.reihenfolge();
                    spielerHand.zeigeHaende(spieler);
                    System.out.println("legen oder ziehen");
                    //Karte Legen
                    if ("legen".equals(scanner.nextLine().toLowerCase())) {
                        System.out.println("Welche Karte?(Index)");
                        legeKarte = Integer.parseInt(scanner.nextLine());
                        UnoKarte neueKarte = spielerHand.spielerHaende.get(spieler).get(legeKarte-1);
                        geht = true;
                        //Wenn Karte legbar, wird Karte gelegt
                        if (geht == regel.karteLegbar(aktuelleKarte, neueKarte,PlayerName, deck, spielerHand)) {
                            spielerHand.entferneKarteHand(spieler, legeKarte, deck, zug);
                            System.out.println("karte wurde gelegt");
                            aktuelleKarte =neueKarte;
                            geht = false;
                            //Karte kann nicht gelegt werden
                        }else {
                            while(geht == true) {
                                System.out.println("Die Aktuelle Karte: \n" + aktuelleKarte);
                                System.out.println("karte wurde nicht gelegt");
                                System.out.println("Weiterhin legen?(ja/nein)");
                                //Karte legen wenn ja geschrieben wird
                                if ("ja".equals(scanner.nextLine().toLowerCase())) {
                                    System.out.println("Welche Karte?(Index)");
                                    legeKarte = Integer.parseInt(scanner.nextLine());
                                    neueKarte = spielerHand.spielerHaende.get(spieler).get(legeKarte-1);
                                    //Wenn Karte legbar, wird Karte gelegt
                                    if (geht == regel.karteLegbar(aktuelleKarte, neueKarte,PlayerName,deck, spielerHand)) {
                                        spielerHand.entferneKarteHand(spieler, legeKarte, deck, zug);
                                        aktuelleKarte = neueKarte;
                                        System.out.println("karte wurde gelegt");
                                        geht = false;
                                    }
                                    // Karte ziehen wenn ja nicht geschrieben wird
                                } else {
                                    spielerHand.befuelleSpieleHand(spieler, deck, 1);
                                    System.out.println("Karte wurde gezogen");
                                    geht = false;
                                }
                            }
                        }
                        //Karte Ziehen
                    } else {
                        spielerHand.befuelleSpieleHand(spieler, deck, 1);
                        int size = spielerHand.spielerHaende.get(spieler).size();
                        System.out.println("Die Aktuelle Karte: \n" + aktuelleKarte);
                        System.out.println("Gezogene Karte: " + spielerHand.spielerHaende.get(spieler).get(size - 1));
                        System.out.println("legen oder behalten?");
                        //Gezogene Karte legen
                        if ("legen".equals(scanner.nextLine().toLowerCase())) {
                            UnoKarte neueKarte = spielerHand.spielerHaende.get(spieler).get(size - 1);
                            System.out.println(neueKarte);
                            geht = true;
                            //Karte ist legbar
                            if (geht == regel.karteLegbar(aktuelleKarte, neueKarte,PlayerName, deck, spielerHand)) {
                                spielerHand.entferneKarteHand(spieler, size, deck, zug);
                                System.out.println("karte wurde gelegt");
                                aktuelleKarte =neueKarte;
                                geht = false;
                                //Karte ist nicht legbar
                            }else {
                                System.out.println("Karte wurde nicht gelegt");
                            }
                        }
                    }
                    //Wenn nur eine Karte vorhanden -> UNO
                    if(spielerHand.spielerHaende.get(spieler).size() == 1){
                        System.out.println("Möchtest du Uno sagen?");
                        //Wenn uno eingegeben wird -> weitermachen
                        if("uno" .equals(scanner.nextLine().toLowerCase())){
                            continue;
                            // Falls nicht -> 1 ziehen
                        }else {
                            spielerHand.befuelleSpieleHand(spieler, deck, 1);
                        }
                    }
                    //Spieler gewinnt wenn 0 Karten in der Hand ist und Spieler werden nach den Anzahl der Karten gewertet
                    if(spielerHand.spielerHaende.get(spieler).size() == 0){
                        spielerHand.ranking();
                        win = true;
                    }
                }
            }
        }
    }
}

