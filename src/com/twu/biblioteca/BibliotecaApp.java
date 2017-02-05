package com.twu.biblioteca;
import java.io.*;
import java.util.ArrayList;

public class BibliotecaApp {
   static  ArrayList<Books> BookList;
    public static void main(String[] args)throws IOException {
        DisplayWelcomeMessage();
        DisplayMainMenu();

    }

    private static void DisplayMainMenu()throws IOException
    {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int choice=0;int r;
        while(true)
        {
            System.out.println("\n\t\tMain Menu");
            System.out.println("\n1.List the Books\n2.Exit");
            System.out.println("Enter your choice:");
            choice=Integer.parseInt(br.readLine());
            r=TestChoice(choice);
            if(r==2)
             System.exit(1);
        }
    }

    public static int TestChoice(int choice) {
        switch(choice)
        {
            case 1:
                DisplayListOfBooks();
                return choice;
            case 2:
                System.out.println("Thank you for visiting us.");
               return choice;
            default:
                System.out.println("Enter a valid choice.Please try again!");
                return -1;
        }
    }

    private static void DisplayListOfBooks() {
        System.out.println("\t\tBook List");
        System.out.println(String.format("%-5s  %-20s  %-20s  %-4s","S.No.","   Book Name","    Author","Year")+"\n");
        Books itr;
        //for run a dummy set of books is generated later linked to database
        BookList=new ArrayList<Books>();
        BookList.add(new Books(1,"Compiler Design","Aho,Ullman","1950"));
        BookList.add(new Books(2,"Operating System","Willam Stallings","1960"));
        BookList.add(new Books(3,"Alorithms","Cormen","1964"));
        for(int i=0;i<BookList.size();i++) {
            itr =BookList.get(i);
            System.out.println(itr.toString());
        }

    }

    private static void DisplayWelcomeMessage() {
        System.out.println("\tWelcome to Bangalore Public Library\n\t\t\tEnjoy Reading");
    }
}