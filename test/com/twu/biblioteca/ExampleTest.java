package com.twu.biblioteca;


import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ExampleTest {

    @Test
    public void testForChoiceOne() throws IOException {
        assertEquals(1,new BibliotecaApp().TestChoice(1));
    }

    @Test
    public void testForChoiceTwo()throws IOException{
        assertEquals(2,new BibliotecaApp().TestChoice(2));
    }
    @Test
    public void testForChoiceThree()throws IOException {
        assertEquals(3,new BibliotecaApp().TestChoice(3));
    }

    @Test
    public void testForChoiceSix()throws IOException {
        assertEquals(4,new BibliotecaApp().TestChoice(6));
    } @Test
    public void testForChoiceMinusThree()throws IOException {
        assertEquals(-1,new BibliotecaApp().TestChoice(-3));
    }

}
