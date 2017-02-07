package com.twu.biblioteca;

import java.util.ArrayList;

public class Member {
   private String name;
   private String id;
   private String password;
   private int noOfIssuedBook;
   private int noOfIssuedMovie;
   private int PermissibleLimitOnIssue;
   private int PermissibleLimitOnMovie;
    ArrayList<Books> bookOnAccount;
    ArrayList<Movie> movieOnAccount;
    Member(String name,String id,String password)
    {
        this.name=name;
        this.id=id;
        this.password=password;
        noOfIssuedBook=0;
        noOfIssuedMovie=0;
        ChangeIssueLimitMovie(1);
        ChangePermissibleIssueLimit(2);
        bookOnAccount=new ArrayList<Books>();
        movieOnAccount=new ArrayList<Movie>();
    }

    void ChangeIssueLimitMovie(int limit) {
        PermissibleLimitOnMovie=limit;
    }

    public Member() {

    }

    public void ChangePermissibleIssueLimit(int limit) {
        PermissibleLimitOnIssue=limit;
    }
    public String getId()
    {return id;}
    public String getName()
    {return name;}
    public void setId()
    {this.id=id;}
    public void setName()
    {this.name=name;}
    public void Issue(Movie mov)
    {
        if(noOfIssuedMovie<PermissibleLimitOnMovie)
        {  movieOnAccount.add(mov);
            noOfIssuedMovie++;
            mov.setIssuer(this);
            mov.setIssued(true);
            System.out.println("Dear "+ name +" you can check out with "+mov.getMovieName()+" by "+mov.getMovieDirector());
        }
        else
        {
            System.out.println("Sorry you have crossed your issue limit.");
        }
    }

    public void Issue(Books book)
    {
        if(noOfIssuedBook<PermissibleLimitOnIssue)
        {  bookOnAccount.add(book);
           noOfIssuedBook++;
           book.setIssuer(this);
           book.setIssued(true);
            System.out.println("Dear "+ name +" you can check out with "+book.getBookName() +" by "+book.getBookAuthor());
        }
        else
        {
            System.out.println("Sorry you have crossed your issue limit.");
        }
    }
    public ArrayList<Movie> displayMoviesOnAccount()
    {   if(!movieOnAccount.isEmpty()) {
        System.out.println("\tMovies on your account");
        System.out.println(String.format("%-5s  %-30s  %-20s  %-6s", "M.ID.", "   Movie Name", "   Director", "Rating") + "\n");
        for (Movie mov : movieOnAccount) {
            System.out.println(mov.toString());
        }
    }
     else
         System.out.println("There are no movies on your account presently.");
        return movieOnAccount;
    }
    public ArrayList<Books> displayBooksOnAccount()
    {   if(!bookOnAccount.isEmpty()) {
        System.out.println("\tBooks on your account");
        System.out.println(String.format("%-5s  %-20s  %-20s  %-4s", "B.ID.", "   Book Name", "    Author", "Year") + "\n");
        for (Books book : bookOnAccount) {
            System.out.println(book.toString());
        }
    }
    else
        System.out.println("There are no books on your acount currently.");
        return bookOnAccount;
    }
    public void BookReturn(Books book)
    {
      bookOnAccount.remove(book);
      noOfIssuedBook--;
      book.setIssued(false);
    }
    public void MovieReturn(Movie mov)
    {
        movieOnAccount.remove(mov);
        noOfIssuedMovie--;
        mov.setIssued(false);
    }
    public boolean autehenticate(String id,String password)
    {
        if(this.id.equalsIgnoreCase(id) && this.password.equals(password))
        {
            return true;
        }
        else
            return false;
    }
}
