package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoardTest {

    private Board board;

    @BeforeEach
    public void setUp() {
        board = new Board();
    }

    @Test
    public void testBoardConstructor_Initialization_ShouldBeEmpty() {
        Board newBoard = new Board();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(' ', newBoard.getCell(i, j));
            }
        }
    }

    @Test
    public void testBoardConstructor_NewBoard_ShouldNotContainMarkers() {
        Board newBoard = new Board();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                char cell = newBoard.getCell(i, j);
                assertNotEquals('X', cell);
                assertNotEquals('O', cell);
            }
        }
    }

    @Test
    public void testIsCellEmpty_CellIsEmpty_ShouldReturnTrue() {
        assertTrue(board.isCellEmpty(0, 0));
    }

    @Test
    public void testIsCellEmpty_OutOfBounds_ShouldThrowException() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            board.isCellEmpty(-1, 0);
        });
    }

    @Test
    public void testPlace_ValidCoordinates_ShouldPlaceMarker() {
        board.place(1, 1, 'X');
        assertEquals('X', board.getCell(1, 1));
    }

    @Test
    public void testPlace_OutOfBounds_ShouldThrowException() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            board.place(3, 1, 'X');
        });
    }

    @Test
    public void testIsFull_BoardIsFull_ShouldReturnTrue() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board.place(i, j, 'X');
            }
        }
        assertTrue(board.isFull());
    }

    @Test
    public void testIsFull_BoardIsEmpty_ShouldReturnFalse() {
        assertFalse(board.isFull());
    }

    @Test
    public void testClear_FullBoard_ShouldBeEmpty() {
        board.place(0, 0, 'X');
        board.place(1, 1, 'O');
        board.clear();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertTrue(board.isCellEmpty(i, j));
            }
        }
    }

    @Test
    public void testClear_AlreadyEmptyBoard_ShouldKeepBoardEmpty() {
        board.clear();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertTrue(board.isCellEmpty(i, j));
            }
        }
    }

    @Test
    public void testPrint_ValidBoard_ShouldPrintCorrectGrid() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(out));
        try {
            board.print();
        } finally {
            System.setOut(originalOut);
        }
        String expected = "| | | |\n| | | |\n| | | |\n"
                .replace("\n", System.lineSeparator());
        assertEquals(expected, out.toString());
    }

    @Test
    public void testPrint_BoardWithInvalidCharacters_ShouldPrintThoseCharacters() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(out));
        try {
            board.place(0, 0, '?');
            board.print();
        } finally {
            System.setOut(originalOut);
        }
        String expected = "|?| | |\n| | | |\n| | | |\n"
                .replace("\n", System.lineSeparator());
        assertEquals(expected, out.toString());
    }

    @Test
    public void testGetCell_ValidCoordinates_ShouldReturnPlacedMarker() {
        board.place(2, 2, 'O');
        assertEquals('O', board.getCell(2, 2));
    }

    @Test
    public void testGetCell_OutOfBounds_ShouldThrowException() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            board.getCell(0, 3);
        });
    }
}
