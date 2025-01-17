package catalogo.biblioteca;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Archivio archivio = new Archivio();
        boolean continua = true;

        while (continua) {
            System.out.println("Scegli un'operazione:");
            System.out.println("1. Aggiungi Prodotto");
            System.out.println("2. Ricerca per ISBN");
            System.out.println("3. Rimuovi Prodotto");
            System.out.println("4. Ricerca per Anno Pubblicazione");
            System.out.println("5. Ricerca per Autore");
            System.out.println("6. Aggiorna Prodotto");
            System.out.println("7. Statistiche");
            System.out.println("8. Esci");
            int scelta = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (scelta) {
                    case 1:
                        System.out.println("1. Libro\n2. Rivista");
                        int tipoProdotto = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Inserisci ISBN: ");
                        String isbn = scanner.nextLine();
                        System.out.print("Inserisci Titolo: ");
                        String titolo = scanner.nextLine();
                        System.out.print("Inserisci Anno di Pubblicazione: ");
                        int anno = scanner.nextInt();
                        System.out.print("Inserisci Numero di Pagine: ");
                        int pagine = scanner.nextInt();
                        scanner.nextLine();

                        if (tipoProdotto == 1) {
                            System.out.print("Inserisci Autore: ");
                            String autore = scanner.nextLine();
                            System.out.print("Inserisci Genere: ");
                            String genere = scanner.nextLine();
                            Libro libro = new Libro(isbn, titolo, anno, pagine, autore, genere);
                            archivio.aggiungiProdotto(libro);
                        } else {
                            System.out.print("Inserisci Periodicità (SETTIMANALE, MENSILE, SEMESTRALE): ");
                            String periodicitaStr = scanner.nextLine();
                            Rivista.Periodicita periodicita = Rivista.Periodicita.valueOf(periodicitaStr.toUpperCase());
                            Rivista rivista = new Rivista(isbn, titolo, anno, pagine, periodicita);
                            archivio.aggiungiProdotto(rivista);
                        }
                        break;
                    case 2:
                        System.out.print("Inserisci ISBN per ricerca: ");
                        isbn = scanner.nextLine();
                        Prodotto prodotto = archivio.ricercaPerISBN(isbn);
                        System.out.println(prodotto);
                        break;
                    case 3:
                        System.out.print("Inserisci ISBN per rimuovere: ");
                        isbn = scanner.nextLine();
                        archivio.rimuoviProdotto(isbn);
                        System.out.println("Prodotto rimosso.");
                        break;
                    case 4:
                        System.out.print("Inserisci Anno per ricerca: ");
                        int annoRicerca = scanner.nextInt();
                        scanner.nextLine();
                        archivio.ricercaPerAnnoPubblicazione(annoRicerca).forEach(System.out::println);
                        break;
                    case 5:
                        System.out.print("Inserisci Autore per ricerca: ");
                        String autoreRicerca = scanner.nextLine();
                        archivio.ricercaPerAutore(autoreRicerca).forEach(System.out::println);
                        break;
                    case 6:
                        System.out.print("Inserisci ISBN per aggiornare: ");
                        isbn = scanner.nextLine();
                        System.out.print("Inserisci nuovo Titolo: ");
                        titolo = scanner.nextLine();
                        System.out.print("Inserisci nuovo Anno: ");
                        anno = scanner.nextInt();
                        System.out.print("Inserisci nuovo Numero Pagine: ");
                        pagine = scanner.nextInt();
                        scanner.nextLine();
                        Prodotto nuovoProdotto = archivio.ricercaPerISBN(isbn);
                        break;
                    case 7:
                        archivio.statistiche();
                        break;
                    case 8:
                        continua = false;
                        break;
                    default:
                        System.out.println("Scelta non valida.");
                }
            } catch (Exception e) {
                System.out.println("Errore: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
