package org.example;

/**
 * Repräsentiert einen Spieler im Tic-Tac-Toe Spiel.
 */
public class Player {

    // 🔴 Das rote Quadrat im UML: Ein privates Attribut für das Zeichen (X oder O)
    private char marker;

    /**
     * 🟢 Der grüne Kreis im UML: Der Konstruktor.
     * Er wird aufgerufen, wenn ein neuer Spieler erstellt wird.
     *
     * @param marker Das Zeichen, das der Spieler nutzen soll.
     */
    public Player(char marker) {
        this.marker = marker;
    }

    /**
     * 🟢 Ein weiterer grüner Kreis: Eine öffentliche Methode (Getter).
     * Damit andere Klassen wissen, welches Zeichen dieser Spieler hat.
     *
     * @return Das Zeichen des Spielers.
     */
    public char getMarker() {
        return marker;
    }
}