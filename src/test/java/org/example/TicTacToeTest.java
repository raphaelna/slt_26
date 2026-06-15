package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import org.junit.jupiter.api.Test;

public class TicTacToeTest {

    @Test
    public void testMain_ValidInputGameFlow_ShouldExecuteSuccessfully() {
        String input = "0\n0\n1\n0\n0\n1\n1\n1\n0\n2\nn\n";
        InputStream sysIn = System.in;
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        try {
            TicTacToe.main(new String[0]);
        } finally {
            System.setIn(sysIn);
        }
    }

    @Test
    public void testMain_MissingInput_ShouldThrowException() {
        InputStream sysIn = System.in;
        System.setIn(new ByteArrayInputStream(new byte[0]));
        try {
            assertThrows(NoSuchElementException.class, () -> {
                TicTacToe.main(new String[0]);
            });
        } finally {
            System.setIn(sysIn);
        }
    }

    @Test
    public void testTicTacToeConstructor_ValidInitialization_ShouldSetStartingState() {
        TicTacToe game = new TicTacToe();
        assertEquals('X', game.player1.getMarker());
        assertEquals('O', game.player2.getMarker());
        assertEquals(game.player1, game.currentPlayer);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertTrue(game.board.isCellEmpty(i, j));
            }
        }
    }

    @Test
    public void testTicTacToeConstructor_BoardState_ShouldNotBeNull() {
        TicTacToe game = new TicTacToe();
        assertNotNull(game.board);
        assertNotNull(game.player1);
        assertNotNull(game.player2);
    }

    @Test
    public void testStart_ValidGameXWins_ShouldEndGameAndExit() {
        String input = "0\n0\n1\n0\n0\n1\n1\n1\n0\n2\nn\n";
        InputStream sysIn = System.in;
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        try {
            TicTacToe game = new TicTacToe();
            game.start();
        } finally {
            System.setIn(sysIn);
        }
    }

    @Test
    public void testStart_MissingInput_ShouldThrowException() {
        InputStream sysIn = System.in;
        System.setIn(new ByteArrayInputStream(new byte[0]));
        try {
            TicTacToe game = new TicTacToe();
            assertThrows(NoSuchElementException.class, game::start);
        } finally {
            System.setIn(sysIn);
        }
    }

    @Test
    public void testPrintStatus_ValidState_ShouldPrintCurrentPlayerAndBoard() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(out));
        try {
            TicTacToe game = new TicTacToe();
            game.printStatus();
        } finally {
            System.setOut(originalOut);
        }
        String expected = ("Current Player: X" + System.lineSeparator()
                + "| | | |" + System.lineSeparator()
                + "| | | |" + System.lineSeparator()
                + "| | | |" + System.lineSeparator());
        assertEquals(expected, out.toString());
    }

    @Test
    public void testPrintStatus_NullCurrentPlayer_ShouldThrowNullPointerException() {
        TicTacToe game = new TicTacToe();
        game.currentPlayer = null;
        assertThrows(NullPointerException.class, game::printStatus);
    }

    @Test
    public void testProcessTurn_ValidCoordinates_ShouldPlaceMarkerAndReturnFalse() {
        TicTacToe game = new TicTacToe();
        Scanner scanner = new Scanner("0\n0\n");
        boolean result = game.processTurn(scanner);
        assertFalse(result);
        assertEquals('X', game.board.getCell(0, 0));
    }

    @Test
    public void testProcessTurn_NonNumericInput_ShouldThrowInputMismatchException() {
        TicTacToe game = new TicTacToe();
        Scanner scanner = new Scanner("abc\n");
        assertThrows(InputMismatchException.class, () -> {
            game.processTurn(scanner);
        });
    }

    @Test
    public void testCheckEndConditions_NoEndCondition_ShouldSwitchPlayerAndReturnFalse() {
        TicTacToe game = new TicTacToe();
        boolean result = game.checkEndConditions();
        assertFalse(result);
        assertEquals(game.player2, game.currentPlayer);
    }

    @Test
    public void testCheckEndConditions_DrawCondition_ShouldReturnTrue() {
        TicTacToe game = new TicTacToe();
        game.board.place(0, 0, 'X');
        game.board.place(0, 1, 'O');
        game.board.place(0, 2, 'X');
        game.board.place(1, 0, 'X');
        game.board.place(1, 1, 'O');
        game.board.place(1, 2, 'O');
        game.board.place(2, 0, 'O');
        game.board.place(2, 1, 'X');
        game.board.place(2, 2, 'X');
        boolean result = game.checkEndConditions();
        assertTrue(result);
    }

    @Test
    public void testSwitchCurrentPlayer_FromPlayer1ToPlayer2_ShouldSwitchToPlayer2() {
        TicTacToe game = new TicTacToe();
        game.switchCurrentPlayer();
        assertEquals(game.player2, game.currentPlayer);
    }

    @Test
    public void testSwitchCurrentPlayer_UnknownPlayer_ShouldFallbackToPlayer1() {
        TicTacToe game = new TicTacToe();
        game.currentPlayer = new Player('?');
        game.switchCurrentPlayer();
        assertEquals(game.player1, game.currentPlayer);
    }

    @Test
    public void testHasWinner_WinnerExists_ShouldReturnTrue() {
        TicTacToe game = new TicTacToe();
        game.board.place(0, 0, 'X');
        game.board.place(0, 1, 'X');
        game.board.place(0, 2, 'X');
        assertTrue(game.hasWinner());
    }

    @Test
    public void testHasWinner_NoWinner_ShouldReturnFalse() {
        TicTacToe game = new TicTacToe();
        assertFalse(game.hasWinner());
    }

    @Test
    public void testCheckRows_WinningRow_ShouldReturnTrue() {
        TicTacToe game = new TicTacToe();
        game.board.place(1, 0, 'X');
        game.board.place(1, 1, 'X');
        game.board.place(1, 2, 'X');
        assertTrue(game.checkRows());
    }

    @Test
    public void testCheckRows_NoWinningRow_ShouldReturnFalse() {
        TicTacToe game = new TicTacToe();
        assertFalse(game.checkRows());
    }

    @Test
    public void testCheckColumns_WinningColumn_ShouldReturnTrue() {
        TicTacToe game = new TicTacToe();
        game.board.place(0, 2, 'X');
        game.board.place(1, 2, 'X');
        game.board.place(2, 2, 'X');
        assertTrue(game.checkColumns());
    }

    @Test
    public void testCheckColumns_NoWinningColumn_ShouldReturnFalse() {
        TicTacToe game = new TicTacToe();
        assertFalse(game.checkColumns());
    }

    @Test
    public void testCheckDiagonals_WinningDiagonal_ShouldReturnTrue() {
        TicTacToe game = new TicTacToe();
        game.board.place(0, 0, 'X');
        game.board.place(1, 1, 'X');
        game.board.place(2, 2, 'X');
        assertTrue(game.checkDiagonals());
    }

    @Test
    public void testCheckDiagonals_NoWinningDiagonal_ShouldReturnFalse() {
        TicTacToe game = new TicTacToe();
        assertFalse(game.checkDiagonals());
    }
}
