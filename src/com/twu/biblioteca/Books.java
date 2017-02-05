package com.twu.biblioteca;

public class Books {
    int id;
    boolean issued;
    String name="";
    String author="";
    String yearOfPublication="";
    Books(int id,String name,String author,String year)
    {
        this.id=id;
        this.name=name;
        this.author=author;
        this.yearOfPublication=year;
    }
    private void setBookId(int id)
    {
        this.id=id;
    }
    private void setBookName(String name)
    {
        this.name=name;
    }
    private void setBookAuthor(String author)
    {
        this.author=author;
    }
    private void yearOfPublication(String year)
    {
        this.yearOfPublication=year;
    }
    private void setIssued(boolean issued)
    {
        this.issued=issued;
    }
    public String toString() {
        return String.format( "%3s    %-20s  %-20s  %-4s",id, name,author,yearOfPublication );
    }
}
