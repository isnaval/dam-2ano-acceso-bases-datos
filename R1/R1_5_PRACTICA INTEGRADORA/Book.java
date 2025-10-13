public class Book {
    private String isbn;
    private String title;
    private String author;
    private int numPages;
    private int year;


    public Book() {

    }

    public Book(String isbn, String title, String author, int numPages, int year) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.numPages = numPages;
        this.year = year;
    }

    public int getNumPages() {
        return numPages;
    }

    public void setNumPages(int numPages) {
        this.numPages = numPages;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", numPages=" + numPages +
                ", year=" + year +
                '}';
    }
}
