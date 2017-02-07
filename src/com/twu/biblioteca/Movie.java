package com.twu.biblioteca;


import sun.security.x509.IssuerAlternativeNameExtension;

public class Movie {
    private int id;
    private String name;
    private String director;
    private int rating;
    private Member issuer;
    private boolean issued;
    Movie(int id,String name,String director,int rating)
    {   this.id=id;
        this.name=name;
        this.director=director;
        this.rating=rating;
        issued=false;
    }
    public int getMovieId()
    {
        return id;
    }
    public String getMovieName()
    {
        return name;
    }
    public String getMovieDirector()
    {
        return director;
    }
    public boolean getIssued()
    {
        return issued;
    }
    public String toString() {
        return String.format( "%3s    %-30s  %-20s  %-6s",id, name,director,rating );
    }
    public void setIssuer(Member mem)
    {
        this.issuer=mem;
    }
    public void setIssued(boolean set)
    {
        this.issued=set;
    }
}
