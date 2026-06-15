package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    public void testPlayerConstructor_ValidMarkerX_ShouldInitializeMarker() {
        Player player = new Player('X');
        assertEquals('X', player.getMarker());
    }

    @Test
    public void testPlayerConstructor_InvalidMarkerSpace_ShouldInitializeMarker() {
        Player player = new Player(' ');
        assertEquals(' ', player.getMarker());
    }

    @Test
    public void testGetMarker_ValidMarkerO_ShouldReturnMarkerO() {
        Player player = new Player('O');
        assertEquals('O', player.getMarker());
    }

    @Test
    public void testGetMarker_NullMarker_ShouldReturnNullMarker() {
        Player player = new Player('\0');
        assertEquals('\0', player.getMarker());
    }
}
