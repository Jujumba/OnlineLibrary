package farid.guliev.onlinelibrary.models;

import javax.persistence.*;

@Entity
@Table(name = "Book")
public class Book {
    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "holder_id", referencedColumnName = "id")
    private Person owner;
    @Column(name = "title")
    private String title;
    @Column(name = "year")
    private int year;
    @Column(name = "author")
    private String author;

    public Book() {

    }

    public Book(int id, String title, int year, String author) {
    }
    public Book(int id, String title, int year, String author, int holder_id) {
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
