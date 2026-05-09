package org.example;

import java.util.Scanner;

public class TicTacToe {

    // 🔴 Attribute aus dem UML-Diagramm
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Board board;

    /**
     * 🟢 Konstruktor: Bereitet das Spiel vor.
     */
    public TicTacToe() {
        player1 = new Player('X');
        player2 = new Player('O');
        board = new Board();
        currentPlayer = player1; // X beginnt immer
    }

    /**
     * 🟢 Die Haupt-Spielschleife.
     */
    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean gameRunning = true;

        while (gameRunning) {
            // 1. Spielfeld und aktuellen Spieler anzeigen
            System.out.println("Current Player: " + currentPlayer.getMarker());
            board.print();

            // 2. Eingabe vom User lesen
            System.out.print("row (0-2): ");
            int row = scanner.nextInt();
            System.out.print("column (0-2): ");
            int col = scanner.nextInt();

            // 3. Zug ausführen (Validierung lassen wir für die Kürze weg)
            if (board.isCellEmpty(row, col)) {
                board.place(row, col, currentPlayer.getMarker());

                // 4. Prüfen, ob jemand gewonnen hat
                if (hasWinner()) {
                    board.print();
                    System.out.println("Player " + currentPlayer.getMarker() + " wins!");
                    gameRunning = false;
                }
                // 5. Prüfen auf Unentschieden
                else if (board.isFull()) {
                    board.print();
                    System.out.println("It's a draw!");
                    gameRunning = false;
                }
                // 6. Spieler wechseln
                else {
                    switchCurrentPlayer();
                }
            } else {
                System.out.println("This cell is already taken! Try again.");
            }
        }
        scanner.close();
    }

    /**
     * 🔴 Wechselt zwischen Player 1 und Player 2.
     */
    private void switchCurrentPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    /**
     * 🔴 Prüft, ob der aktuelle Spieler das Spiel gewonnen hat.
     */
    private boolean hasWinner() {
        char marker = currentPlayer.getMarker(); // Nach diesem Marker suchen wir ('X' oder 'O')

        // 1. Reihen (Horizontal) prüfen
        for (int row = 0; row < 3; row++) {
            if (board.getCell(row, 0) == marker &&
                    board.getCell(row, 1) == marker &&
                    board.getCell(row, 2) == marker) {
                return true; // Gewonnen in einer Reihe
            }
        }

        // 2. Spalten (Vertikal) prüfen
        for (int col = 0; col < 3; col++) {
            if (board.getCell(0, col) == marker &&
                    board.getCell(1, col) == marker &&
                    board.getCell(2, col) == marker) {
                return true; // Gewonnen in einer Spalte
            }
        }

        // 3. Diagonalen prüfen
        // Diagonale von links oben nach rechts unten
        if (board.getCell(0, 0) == marker &&
                board.getCell(1, 1) == marker &&
                board.getCell(2, 2) == marker) {
            return true;
        }

        // Diagonale von rechts oben nach links unten
        if (board.getCell(0, 2) == marker &&
                board.getCell(1, 1) == marker &&
                board.getCell(2, 0) == marker) {
            return true;
        }

        // Wenn nichts zutrifft, hat noch niemand gewonnen
        return false;
    }
}