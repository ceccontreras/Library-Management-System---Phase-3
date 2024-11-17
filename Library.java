/**
 * Library class manages interactions with the SQLite database.
 *
 * Name: Carlos Campos
 * Course: Software Development
 * Date: November 15, 2024
 */
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private Connection connection;

    public Library() {
        connectToDatabase();
    }

    private void connectToDatabase() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:/Users/carloscampos/Documents/SoftwareDev - Valencia College/Phase2/identifier.sqlite");
        } catch (SQLException e) {
            System.out.println("Error connecting to database: " + e.getMessage());
        }
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM books";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                books.add(new Book(
                        rs.getString("barcode"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("genre"),
                        rs.getString("status"),
                        rs.getString("due_date") != null ? LocalDate.parse(rs.getString("due_date")) : null
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving books: " + e.getMessage());
        }
        return books;
    }

    public boolean removeBookByBarcode(String barcode) {
        String query = "DELETE FROM books WHERE barcode = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, barcode);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error removing book: " + e.getMessage());
        }
        return false;
    }

    public boolean removeBookByTitle(String title) {
        String query = "DELETE FROM books WHERE title = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, title);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error removing book: " + e.getMessage());
        }
        return false;
    }

    public boolean checkOutBook(String title) {
        String query = "UPDATE books SET status = 'checked out', due_date = ? WHERE title = ? AND status = 'checked in'";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, LocalDate.now().plusWeeks(4).toString());
            pstmt.setString(2, title);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error checking out book: " + e.getMessage());
        }
        return false;
    }

    public boolean checkInBook(String title) {
        String query = "UPDATE books SET status = 'checked in', due_date = NULL WHERE title = ? AND status = 'checked out'";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, title);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error checking in book: " + e.getMessage());
        }
        return false;
    }
}
