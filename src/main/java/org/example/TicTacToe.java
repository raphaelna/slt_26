package org.example;

import java.util.Scanner;

/**
 * Diese Klasse steuert die Logik und den Ablauf eines Tic-Tac-Toe Spiels.
 */
public class TicTacToe {

    private final Player player1;
    private final Player player2;
    private Player currentPlayer;
    private final Board board;

    /**
     * Konstruktor: Bereitet das Spiel vor und setzt den Startspieler.
     */
    public TicTacToe() {
        player1 = new Player('X');
        player2 = new Player('O');
        board = new Board();
        currentPlayer = player1;
    }

    /**
     * Startet die Haupt-Spielschleife.
     */
    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean gameOver = false;

        while (!gameOver) {
            printStatus();
            gameOver = processTurn(scanner);
        }
        scanner.close();
    }

    /**
     * Gibt den aktuellen Status auf der Konsole aus.
     */
    private void printStatus() {
        System.out.println("Current Player: " + currentPlayer.getMarker());
        board.print();
    }

    /**
     * Verarbeitet die Eingabe eines Spielers und führt den Zug aus.
     * @param scanner Scanner für die Konsoleneingabe.
     * @return true, wenn das Spiel beendet ist, sonst false.
     */
    private boolean processTurn(Scanner scanner) {
        System.out.print("row (0-2): ");
        int row = scanner.nextInt();
        System.out.print("column (0-2): ");
        int col = scanner.nextInt();

        if (!board.isCellEmpty(row, col)) {
            System.out.println("This cell is already taken! Try again.");
            return false;
        }

        board.place(row, col, currentPlayer.getMarker());
        return checkEndConditions();
    }

    /**
     * Prüft, ob eine Gewinn- oder Unentschieden-Bedingung erfüllt ist.
     * @return true, wenn das Spiel zu Ende ist.
     */
    private boolean checkEndConditions() {
        if (hasWinner()) {
            board.print();
            System.out.println("Player " + currentPlayer.getMarker() + " wins!");
            return true;
        }
        if (board.isFull()) {
            board.print();
            System.out.println("It's a draw!");
            return true;
        }
        switchCurrentPlayer();
        return false;
    }

    /**
     * Wechselt den aktuellen Spieler.
     */
    private void switchCurrentPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    /**
     * Prüft, ob der aktuelle Spieler gewonnen hat.
     * @return true, wenn eine Reihe, Spalte oder Diagonale komplett ist.
     */
    private boolean hasWinner() {
        return checkRows() || checkColumns() || checkDiagonals();
    }

    /**
     * Prüft alle horizontalen Reihen auf einen Sieg.
     * @return true bei drei gleichen Markern in einer Reihe.
     */
    private boolean checkRows() {
        char marker = currentPlayer.getMarker();
        for (int i = 0; i < 3; i++) {
            if (board.getCell(i, 0) == marker
                    && board.getCell(i, 1) == marker
                    && board.getCell(i, 2) == marker) {
                return true;
            }
        }
        return false;
    }

    /**
     * Prüft alle vertikalen Spalten auf einen Sieg.
     * @return true bei drei gleichen Markern in einer Spalte.
     */
    private boolean checkColumns() {
        char marker = currentPlayer.getMarker();
        for (int i = 0; i < 3; i++) {
            if (board.getCell(0, i) == marker
                    && board.getCell(1, i) == marker
                    && board.getCell(2, i) == marker) {
                return true;
            }
        }
        return false;
    }

    /**
     * Prüft die beiden Diagonalen auf einen Sieg.
     * @return true bei drei gleichen Markern in einer Diagonale.
     */
    private boolean checkDiagonals() {
        char marker = currentPlayer.getMarker();
        boolean mainDiag = board.getCell(0, 0) == marker
                && board.getCell(1, 1) == marker
                && board.getCell(2, 2) == marker;
        boolean antiDiag = board.getCell(0, 2) == marker
                && board.getCell(1, 1) == marker
                && board.getCell(2, 0) == marker;
        return mainDiag || antiDiag;
    }
}