package com.twu.biblioteca;

public class Books {
    private int id;
    private boolean issued;
    private String name="";
    private String author="";
    private String yearOfPublication="";
    private Member issuer;
    Books(int id,String name,String author,String year)
    {
        this.id=id;
        this.name=name;
        this.author=author;
        this.yearOfPublication=year;
        issued=false;
    }
    public void setBookId(int id)
    {
        this.id=id;
    }
    public void setBookName(String name)
    {
        this.name=name;
    }
    public void setBookAuthor(String author)
    {
        this.author=author;
    }
    public void setYearOfPublication(String year)
    {
        this.yearOfPublication=year;
    }
    public void setIssued(boolean issued)
    {
        this.issued=issued;
    }
    public int getBookId()
    {
        return id;
    }
    public String getBookName()
    {
        return name;
    }
    public String getBookAuthor()
    {
        return author;
    }
    public String getYearOfPublication()
    {
        return yearOfPublication;
    }
    public boolean getIssued()
    {
        return issued;
    }
    public void setIssuer(Member memb)
    {
        this.issuer=memb;
    }
    public String toString() {
        return String.format( "%3s    %-20s  %-20s  %-4s",id, name,author,yearOfPublication );
    }

}
