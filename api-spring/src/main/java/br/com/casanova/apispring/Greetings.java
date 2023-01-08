package br.com.casanova.apispring;

public class Greetings {

    private final long ID;
    private final String contente;

    public Greetings(long ID, String contente) {
        this.ID = ID;
        this.contente = contente;
    }

    public long getID() {
        return ID;
    }

    public String getContente() {
        return contente;
    }
}
