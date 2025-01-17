package catalogo.biblioteca;

public enum Periodicita {
    SETTIMANALE, MENSILE, SEMESTRALE;

    public static Periodicita fromString(String value) {
        for (Periodicita p : Periodicita.values()) {
            if (p.name().equalsIgnoreCase(value)) {
                return p;
            }
        }
        throw new IllegalArgumentException("Periodicità non valida: " + value);
    }
}
