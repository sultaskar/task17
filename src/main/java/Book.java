import java.io.File;
import java.io.FileInputStream;
import java.io.*;

public class Book implements Serializable{

    String title;
    String author;
    int yearIssued;

    public Book(String title, String author, int yearIssued) {
        this.title = title;
        this.author = author;
        this.yearIssued = yearIssued;
    }

    @Override
    public String toString() {
        return (this.title + " " + this.author + " " + this.yearIssued);
    }
}
