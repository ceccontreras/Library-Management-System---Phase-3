/**
 * Book class represents a book in the library.
 *
 * Name: Carlos Campos
 * Course: Software Development
 * Date: November 15, 2024
 *
 * This class stores information about a book, including its title, author,
 * barcode, genre, status, and due date.
 */
import java.time.LocalDate;

public class Book {
    private String barcode;
    private String title;
    private String author;
    private String genre;
    private String status;
    private LocalDate dueDate;

    public Book(String barcode, String title, String author, String genre, String status, LocalDate dueDate) {
        this.barcode = barcode;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.status = status;
        this.dueDate = dueDate;
    }

    // Getters
    public String getBarcode() { return barcode; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getGenre() { return genre; }
    public String getStatus() { return status; }
    public LocalDate getDueDate() { return dueDate; }

    // Setters
    public void setStatus(String status) { this.status = status; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }

    @Override
    public String toString() {
        return "Barcode: " + barcode + ", Title: " + title + ", Author: " + author +
                ", Genre: " + genre + ", Status: " + status + ", Due Date: " + dueDate;
    }
}
