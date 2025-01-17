package catalogo.biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Archivio {
    private List<Prodotto> catalogo = new ArrayList<>();

    public void aggiungiProdotto(Prodotto prodotto) throws IllegalArgumentException {
        if (catalogo.stream().anyMatch(p -> p.getIsbn().equals(prodotto.getIsbn()))) {
            throw new IllegalArgumentException("Prodotto con ISBN " + prodotto.getIsbn() + " già esistente.");
        }
        catalogo.add(prodotto);
    }

    public Prodotto ricercaPerISBN(String isbn) throws ISBNNonTrovatoException {
        return catalogo.stream()
                .filter(p -> p.getIsbn().equals(isbn))
                .findFirst()
                .orElseThrow(() -> new ISBNNonTrovatoException("Prodotto con ISBN " + isbn + " non trovato."));
    }

    public void rimuoviProdotto(String isbn) throws ISBNNonTrovatoException {
        Prodotto prodotto = ricercaPerISBN(isbn);
        catalogo.remove(prodotto);
    }

    public List<Prodotto> ricercaPerAnnoPubblicazione(int anno) {
        return catalogo.stream()
                .filter(p -> p.getAnnoPubblicazione() == anno)
                .collect(Collectors.toList());
    }

    public List<Libro> ricercaPerAutore(String autore) {
        return catalogo.stream()
                .filter(p -> p instanceof Libro)
                .map(p -> (Libro) p)
                .filter(l -> l.getAutore().equals(autore))
                .collect(Collectors.toList());
    }

    public void aggiornaProdotto(String isbn, Prodotto nuovoProdotto) throws ISBNNonTrovatoException {
        rimuoviProdotto(isbn);
        aggiungiProdotto(nuovoProdotto);
    }

    public void statistiche() {
        long numeroLibri = catalogo.stream().filter(p -> p instanceof Libro).count();
        long numeroRiviste = catalogo.stream().filter(p -> p instanceof Rivista).count();
        Optional<Prodotto> prodottoConPiuPagine = catalogo.stream().max((p1, p2) -> Integer.compare(p1.getNumeroPagine(), p2.getNumeroPagine()));
        double mediaPagine = catalogo.stream().mapToInt(Prodotto::getNumeroPagine).average().orElse(0);

        System.out.println("Numero totale di libri: " + numeroLibri);
        System.out.println("Numero totale di riviste: " + numeroRiviste);
        prodottoConPiuPagine.ifPresent(p -> System.out.println("Prodotto con il maggior numero di pagine: " + p));
        System.out.println("Media delle pagine: " + mediaPagine);
    }
}
