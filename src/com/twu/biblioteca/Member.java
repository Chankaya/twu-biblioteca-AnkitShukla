package com.twu.biblioteca;

import java.util.ArrayList;

public class Member {
   private String name;
   private String id;
   private int noOfIssuedBook;
   private int PermissibleLimitOnIssue;
    ArrayList<Books> bookOnAccount;
    Member(String name,String id)
    {
        this.name=name;
        this.id=id;
        noOfIssuedBook=0;
        ChangePermissibleIssueLimit(2);
        bookOnAccount=new ArrayList<Books>();
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
    public ArrayList<Books> displayBooksOnAccount()
    {   if(!bookOnAccount.isEmpty()) {
        System.out.println("\tBooks on your account");
        System.out.println(String.format("%-5s  %-20s  %-20s  %-4s", "B.ID.", "   Book Name", "    Author", "Year") + "\n");
        for (Books book : bookOnAccount) {
            System.out.println(book.toString());
        }
    }
        return bookOnAccount;
    }
    public void BookReturn(Books book)
    {
      bookOnAccount.remove(book);
      noOfIssuedBook--;
      book.setIssued(false);
    }
}
