package com.twu.biblioteca;
import java.io.*;
import java.util.ArrayList;

public class BibliotecaApp {
   static private ArrayList<Books> BookList;
   static private ArrayList<Member> MemberList;
    public static void main(String[] args)throws IOException {
        DisplayWelcomeMessage();
        //for run a dummy set of books and members is generated later can be linked to database
        BookList=new ArrayList<Books>();
        BookList.add(new Books(1,"Compiler Design","Aho,Ullman","1950"));
        BookList.add(new Books(2,"Operating System","Willam Stallings","1960"));
        BookList.add(new Books(3,"Alorithms","Cormen","1964"));
        MemberList=new ArrayList<Member>();
        MemberList.add(new Member("Ankit","M001"));
        MemberList.add(new Member("Ankur","M002"));
        DisplayMainMenu();

    }

    private static void DisplayMainMenu()throws IOException
    {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int choice;int r;
        while(true)
        {
            System.out.println("\n\t\tMain Menu");
            System.out.println("\n1.List the Books\n2.Check out a book\n3.Return book\n4.Exit");
            System.out.println("Enter your choice:");
            choice=Integer.parseInt(br.readLine());
            r=TestChoice(choice);
            if(r==4)
             System.exit(1);
        }
    }

    public static int TestChoice(int choice)throws IOException {
        switch(choice)
        {
            case 1:
                DisplayListOfBooks(BookList);
                return choice;
            case 2:
                Issue();
                return choice;
            case 3:
                Return();
                return choice;
            case 4:
                System.out.println("Thank you for visiting us.");
               return choice;

            default:
                System.out.println("Enter a valid choice.Please try again!");
                return -1;
        }
    }

    private static void Return()throws IOException {
        int returnToMainMenu=0;
        do {
            System.out.println("Please enter you member id:");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String memId = br.readLine();
            boolean match = false;
            Member memMatch = new Member();
            for (Member mem : MemberList) {
                if (mem.getId().equalsIgnoreCase( memId))
                {
                    match = true;
                    memMatch = mem;
                    break;
                }
            }
            if (match) {
                ArrayList<Books> UserBooks;
                UserBooks = memMatch.displayBooksOnAccount();
                if(UserBooks.isEmpty())
                    System.out.println("Your account is not holding any books.");
                else {
                    System.out.println("Enter the book id you want to return:");
                    int id = Integer.parseInt(br.readLine());
                    boolean bMatch = false;
                    for (Books b : UserBooks) {
                        if (b.getBookId() == id) {
                            bMatch = true;
                            memMatch.BookReturn(b);
                            System.out.println("Dear "+memMatch.getName()+" you have successfully returned "+b.getBookName()+" by"+b.getBookAuthor()+".");
                            break;
                        }
                    }
                    if (bMatch == false)
                        System.out.println("You have entered wrong book Id.Try Again");
                }
            } else
                System.out.println("You have entered Invalid user Id .Try Again");
            do {
                System.out.println("Do you want to return to main menu?(press 1 for yes and 0 for no)");
                returnToMainMenu = Integer.parseInt(br.readLine());
            }while(!(returnToMainMenu==0 || returnToMainMenu==1));
        }while(returnToMainMenu==0);
    }

    private static void Issue() throws IOException {
        int returnToMainMenu=0;
        do {
            System.out.println("Enter the name of book:");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String str = br.readLine();
            //Find the list of matching books

            ArrayList<Books> matchList = BookListMatch(str, BookList);
            //If match is not found then user may remain in this sub menu
            if (matchList.isEmpty()) {
                System.out.println("Sorry no match found please try again");
            }
            else {
                System.out.println("\n\t\tResultant matches\n");
                DisplayListOfBooks(matchList);
                System.out.println("\nEnter the id of book your want to issue:");
                int id = Integer.parseInt(br.readLine());
                Books iterator;
                //match checks for book match
                int match=0;
                for (Books aMatchList : matchList) {
                    iterator = aMatchList;
                    if (iterator.getBookId() == id) {
                        match = 1;
                        ValidateUserId(br, iterator);
                    }

                }
                if(match==0)
                    System.out.println("Sorry you have entered wrong book id.Try Again");
                do {
                    System.out.println("Do you want to return to main menu?(press 1 for yes and 0 for no)");
                    returnToMainMenu = Integer.parseInt(br.readLine());
                }while(!(returnToMainMenu==0 || returnToMainMenu==1));

            }
        }while(returnToMainMenu==0);
    }

    private static void ValidateUserId(BufferedReader br, Books iterator) throws IOException {
        System.out.println("Enter your member id to issue:");
        String MemId = br.readLine();
        boolean match=false;
        for (Member mem : MemberList) {
            if (mem.getId().equalsIgnoreCase(MemId)) {
                mem.Issue(iterator);
                match=true;
            }
        }
        if(match==false) {
            System.out.println("You have entered the wrong member id.Please Try again.");
        }
    }

    private static ArrayList<Books> BookListMatch(String str, ArrayList<Books> CheckAgainstList) {
        Books itr;
        ArrayList<Books> matchList=new ArrayList<Books>();
        for (Books aCheckAgainstList : CheckAgainstList) {
            itr = aCheckAgainstList;
            if ((itr.getBookName().toLowerCase()).contains(str.toLowerCase()) ) {
                if(!itr.getIssued())
                matchList.add(itr);
            }
        }
        return matchList;
    }

    private static void DisplayListOfBooks(ArrayList<Books> BookList) {

            System.out.println("\t\tBook List");
            System.out.println(String.format("%-5s  %-20s  %-20s  %-4s", "B.ID.", "   Book Name", "    Author", "Year") + "\n");
            Books itr;
            for (Books aBookList : BookList) {
                itr = aBookList;
                if (!itr.getIssued())
                    System.out.println(itr.toString());
            }

    }

    private static void DisplayWelcomeMessage() {
        System.out.println("\tWelcome to Bangalore Public Library\n\t\t\tEnjoy Reading");
    }
}