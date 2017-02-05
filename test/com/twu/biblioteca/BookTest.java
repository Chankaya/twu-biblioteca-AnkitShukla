package com.twu.biblioteca;


import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class BookTest {
    @Test
    public void TestForBookObject()
    {
       System.out.println(new Books(1,"Tryst To Destiny","Jawaharlal Nehru","15/07/1947").toString());
    }
   @Test
    public void TestForBookDisplay()
   {
       ArrayList<Books> testList=new ArrayList<Books>();
       testList.add(new Books(1,"Compiler Design","Aho,Ullman","1950"));
       testList.add(new Books(2,"Operating System","Willam Stallings","1960"));
       testList.add(new Books(3,"Alorithms","Cormen","1964"));
       BibliotecaApp testObj=new BibliotecaApp();
       testObj.BookList=testList;
       assertEquals(1,testObj.TestChoice(1));

   }
}
