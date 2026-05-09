package org.example;

/**
 * Repräsentiert das 3x3 Spielfeld für Tic-Tac-Toe.
 */
public class Board {

    // 🔴 Das 2D-Array, das die 9 Felder speichert (3 Reihen, 3 Spalten)
    private char[][] cells;

    /**
     * 🟢 Konstruktor: Erstellt das Spielfeld und leert es direkt zu Beginn.
     */
    public Board() {
        cells = new char[3][3];
        clear(); // Füllt das Array mit Leerzeichen
    }

    /**
     * 🟢 Prüft, ob eine bestimmte Zelle noch frei ist.
     *
     * @param x Reihe (0-2)
     * @param y Spalte (0-2)
     * @return true, wenn die Zelle ein Leerzeichen enthält, sonst false
     */
    public boolean isCellEmpty(int x, int y) {
        return cells[x][y] == ' ';
    }

    /**
     * 🟢 Setzt den Marker (X oder O) an die gewünschte Position.
     *
     * @param x      Reihe (0-2)
     * @param y      Spalte (0-2)
     * @param marker Das Zeichen des Spielers
     */
    public void place(int x, int y, char marker) {
        cells[x][y] = marker;
    }

    /**
     * 🟢 Prüft, ob das gesamte Spielfeld voll ist (wichtig für Unentschieden).
     *
     * @return true, wenn keine Zelle mehr leer ist
     */
    public boolean isFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (cells[i][j] == ' ') {
                    return false; // Sobald ein leeres Feld gefunden wird, ist es nicht voll
                }
            }
        }
        return true; // Keine leeren Felder gefunden
    }

    /**
     * 🟢 Setzt alle Zellen wieder auf Leerzeichen zurück.
     */
    public void clear() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cells[i][j] = ' ';
            }
        }
    }

    /**
     * 🟢 Zeichnet das Spielfeld genau so, wie in deinem Beispiel gewünscht.
     */
    public void print() {
        System.out.println("▁▁▁▁▁▁"); // Obere Dekoration

        for (int i = 0; i < 3; i++) {
            System.out.print("|");
            for (int j = 0; j < 3; j++) {
                // Druckt den Inhalt der Zelle (Leerzeichen, X oder O) und den Trennstrich
                System.out.print(cells[i][j] + "|");
            }
            System.out.println(); // Zeilenumbruch nach jeder fertigen Reihe
        }

        System.out.println("▔▔▔▔"); // Untere Dekoration
    }

    /**
     * 🟢 Gibt den Marker (X, O oder Leerzeichen) an einer bestimmten Position zurück.
     *
     * @param x Reihe (0-2)
     * @param y Spalte (0-2)
     * @return Der Marker in der Zelle.
     */
    public char getCell(int x, int y) {
        return cells[x][y];
    }
}