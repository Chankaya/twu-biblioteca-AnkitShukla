package com.twu.biblioteca;


import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ExampleTest {

    @Test
    public void testForChoiceOne() {
        assertEquals(1,new BibliotecaApp().TestChoice(1));
    }

    @Test
    public void testForChoiceTwo() {
        assertEquals(2,new BibliotecaApp().TestChoice(2));
    }
    @Test
    public void testForChoiceThree() {
        assertEquals(-1,new BibliotecaApp().TestChoice(3));
    }

    @Test
    public void testForChoiceSix() {
        assertEquals(-1,new BibliotecaApp().TestChoice(6));
    } @Test
    public void testForChoiceMinusThree() {
        assertEquals(-1,new BibliotecaApp().TestChoice(-3));
    }

}
