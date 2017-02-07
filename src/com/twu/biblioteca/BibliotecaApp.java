package com.twu.biblioteca;
import java.io.*;
import java.util.ArrayList;

public class BibliotecaApp {
   static private ArrayList<Books> BookList;
   static private ArrayList<Movie> MovieList;
   static private ArrayList<Member> MemberList;
    public static void main(String[] args)throws IOException {
        DisplayWelcomeMessage();
        //for run a dummy set of books  members and movies is generated later can be linked to database
        BookList=new ArrayList<Books>();
        BookList.add(new Books(1,"Compiler Design","Aho,Ullman","1950"));
        BookList.add(new Books(2,"Operating System","Willam Stallings","1960"));
        BookList.add(new Books(3,"Algorithms","Cormen","1964"));
        MemberList=new ArrayList<Member>();
        MemberList.add(new Member("Ankit","M001","admin"));
        MemberList.add(new Member("Ankur","M002","user"));
        MovieList=new ArrayList<Movie>();
        MovieList.add(new Movie(1,"Resident Evil","Martin J",5));
        MovieList.add(new Movie(2,"Kabil","Rakesh Roshan",8));
        DisplayMainMenu();

    }

    private static void DisplayMainMenu()throws IOException
    {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int choice;int r;
        while(true)
        {
            System.out.println("\n\t\tMain Menu");
            System.out.println("\n1.List the Books\n2.List of Movies\n3.Log in(acc details,issue,return)\n4.Exit");
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
                DisplayListOfMovies(MovieList);
                return choice;
            case 3:
                 login();
                return choice;
            case 4:
                System.out.println("Thank you for visiting us.");
               return choice;

            default:
                System.out.println("Enter a valid choice.Please try again!");
                return -1;
        }
    }

    private static void login()throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        boolean valid=false;
        int choice=0;
        Member memb=new Member();
       outer: for(int i=0;i<3;i++)
        {
            System.out.println("Enter your member id:");
            String name=br.readLine();
            System.out.println("Enter your password:");
            String pass=br.readLine();
            for (Member mem : MemberList) {
                if (mem.autehenticate(name,pass)) {
                    valid=true;
                    memb=mem;
                    break outer;
                }
            }
            if(valid == false ) {
                if(i!=2)
                System.out.println("You have entered wrong credentials.Please Try again.("+(2-i)+"chances left)");
                else {
                    System.out.println("Sorry you are now redirected to main menu.You have crossed allowable tries");
                    return;
                }
            }
        }
        System.out.println("Welcome "+memb.getName()+" ,you can avail following options:");
        while(true)
        {
            System.out.println("\n1.Display book on my account\n2.Display movies on my account\n3.Issue book\n4.Return Book");
            System.out.println("5.Issue Movie\n6.Return Movie\n7.Log out and Return to main menu");
            System.out.println("Please enter your choice:");
            choice=Integer.parseInt(br.readLine());
            switch(choice)
            {
                case 1:
                    memb.displayBooksOnAccount();
                    break;
                case 2:
                     memb.displayMoviesOnAccount();
                     break;
                case 3:
                    IssueBook(memb);
                    break;
                case 4:
                    ReturnBook(memb);
                    break;
                case 5:
                    IssueMovie(memb);
                    break;
                case 6:
                    ReturnMovie(memb);
                    break;
                case 7:
                     return;
                default:
                    System.out.println("Please enter a valid choice.");
            }
        }
    }

    private static void ReturnMovie(Member memb)throws IOException {
        int returnToPrvMenu=0;
        do {

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


            ArrayList<Movie> UserMovies;
            UserMovies = memb.displayMoviesOnAccount();
            if(UserMovies.isEmpty())
                System.out.println("Your account is not holding any movies.");
            else {
                System.out.println("Enter the movie id you want to return:");
                int id = Integer.parseInt(br.readLine());
                boolean mMatch = false;
                for (Movie mov: UserMovies) {
                    if (mov.getMovieId() == id) {
                        mMatch = true;
                        memb.MovieReturn(mov);
                        System.out.println("Dear "+memb.getName()+" you have successfully returned "+mov.getMovieName()+" by"+mov.getMovieDirector()+".");
                        break;
                    }
                }
                if (mMatch == false)
                    System.out.println("You have entered wrong movie Id.Try Again");
            }

            do {
                System.out.println("Do you want to return to previous menu?(press 1 for yes and 0 for no)");
                returnToPrvMenu = Integer.parseInt(br.readLine());
            }while(!(returnToPrvMenu==0 || returnToPrvMenu==1));
        }while(returnToPrvMenu==0);
    }

    private static void IssueMovie(Member memb)throws IOException {
        int returnToPrevMenu=0;
        do {
            System.out.println("Enter the name of movie:");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String str = br.readLine();
            //Find the list of matching books

            ArrayList<Movie> matchList = MovieListMatch(str, MovieList);
            //If match is not found then user may remain in this sub menu
            if (matchList.isEmpty()) {
                System.out.println("Sorry no match found please try again");
            }
            else {
                System.out.println("\n\t\tResultant matches\n");
                DisplayListOfMovies(matchList);
                System.out.println("\nEnter the id of movie your want to issue:");
                int id = Integer.parseInt(br.readLine());
                Movie iterator;
                //match checks for book match
                int match=0;
                for (Movie aMatchList : matchList) {
                    iterator = aMatchList;
                    if (iterator.getMovieId() == id) {
                        match = 1;
                        memb.Issue(iterator);
                    }

                }
                if(match==0)
                    System.out.println("Sorry you have entered wrong movie id.Try Again");
                do {
                    System.out.println("Do you want to return to previous menu?(press 1 for yes and 0 for no)");
                    returnToPrevMenu = Integer.parseInt(br.readLine());
                }while(!(returnToPrevMenu==0 || returnToPrevMenu==1));

            }
        }while(returnToPrevMenu==0);
    }

    private static ArrayList<Movie> MovieListMatch(String str, ArrayList<Movie> CheckAgainstList) {
        Movie itr;
        ArrayList<Movie> matchList=new ArrayList<Movie>();
        for (Movie aCheckAgainstList : CheckAgainstList) {
            itr = aCheckAgainstList;
            if ((itr.getMovieName().toLowerCase()).contains(str.toLowerCase()) ) {
                if(!itr.getIssued())
                    matchList.add(itr);
            }
        }
        return matchList;
    }


    private static void DisplayListOfMovies(ArrayList<Movie> MovieList) {
        System.out.println("\t\tMovie List");
        System.out.println(String.format("%-5s  %-30s  %-20s  %-6s", "M.ID.", "   Movie Name", "   Director", "Rating") + "\n");
        Movie itr;
        for (Movie aList : MovieList) {
            itr = aList;
            if (!itr.getIssued())
                System.out.println(itr.toString());
        }
    }

    private static void ReturnBook(Member memb)throws IOException {
        int returnToPrvMenu=0;
        do {

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


                ArrayList<Books> UserBooks;
                UserBooks = memb.displayBooksOnAccount();
                if(UserBooks.isEmpty())
                    System.out.println("Your account is not holding any books.");
                else {
                    System.out.println("Enter the book id you want to return:");
                    int id = Integer.parseInt(br.readLine());
                    boolean bMatch = false;
                    for (Books b : UserBooks) {
                        if (b.getBookId() == id) {
                            bMatch = true;
                            memb.BookReturn(b);
                            System.out.println("Dear "+memb.getName()+" you have successfully returned "+b.getBookName()+" by"+b.getBookAuthor()+".");
                            break;
                        }
                    }
                    if (bMatch == false)
                        System.out.println("You have entered wrong book Id.Try Again");
                  }

            do {
                System.out.println("Do you want to return to previous menu?(press 1 for yes and 0 for no)");
                returnToPrvMenu = Integer.parseInt(br.readLine());
            }while(!(returnToPrvMenu==0 || returnToPrvMenu==1));
        }while(returnToPrvMenu==0);
    }

    private static void IssueBook(Member memb) throws IOException {
        int returnToPrevMenu=0;
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
                        memb.Issue(iterator);
                    }

                }
                if(match==0)
                    System.out.println("Sorry you have entered wrong book id.Try Again");
                do {
                    System.out.println("Do you want to return to previous menu?(press 1 for yes and 0 for no)");
                    returnToPrevMenu = Integer.parseInt(br.readLine());
                }while(!(returnToPrevMenu==0 || returnToPrevMenu==1));

            }
        }while(returnToPrevMenu==0);
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