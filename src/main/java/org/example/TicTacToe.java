package org.example;

import java.util.Scanner;

public class TicTacToe {

    final Player player1;
    final Player player2;
    Player currentPlayer;
    final Board board;

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.start();
    }

    public TicTacToe() {
        player1 = new Player('X');
        player2 = new Player('O');
        board = new Board();
        currentPlayer = player1;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean keepPlaying = true;

        while (keepPlaying) {
            board.clear();
            currentPlayer = player1;
            boolean gameOver = false;

            while (!gameOver) {
                printStatus();
                gameOver = processTurn(scanner);
            }

            System.out.println();
            System.out.println("=== GAME OVER ===");
            System.out.print("Do you want to play again? (y/n): ");
            String response = scanner.next().trim().toLowerCase();

            if (!response.equals("y")) {
                keepPlaying = false;
                System.out.println("Thank you for playing! See you next time.");
            }
        }
        scanner.close();
    }

    void printStatus() {
        System.out.println("Current Player: " + currentPlayer.getMarker());
        board.print();
    }

    boolean processTurn(Scanner scanner) {
        int row = -1;
        int col = -1;
        boolean validSelection = false;

        while (!validSelection) {
            System.out.print("row (0-2): ");
            row = scanner.nextInt();
            System.out.print("column (0-2): ");
            col = scanner.nextInt();

            if (row < 0 || row > 2 || col < 0 || col > 2) {
                System.out.println("Invalid input! Coordinates must be between 0 and 2.");
            } else if (!board.isCellEmpty(row, col)) {
                System.out.println("This cell is already taken! Try again.");
            } else {
                validSelection = true;
            }
        }

        board.place(row, col, currentPlayer.getMarker());
        return checkEndConditions();
    }

    boolean checkEndConditions() {
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

    void switchCurrentPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    public boolean hasWinner() {
        return checkRows() || checkColumns() || checkDiagonals();
    }

    boolean checkRows() {
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

    boolean checkColumns() {
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

    boolean checkDiagonals() {
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