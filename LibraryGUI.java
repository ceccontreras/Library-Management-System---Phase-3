import javax.swing.*;
import java.awt.*;
import java.util.List;

public class LibraryGUI {
    private Library library;
    private JFrame frame;
    private JTextArea outputArea;
    private JTextField inputField;

    public LibraryGUI() {
        library = new Library();

        // GUI Setup
        frame = new JFrame("Library Management System");
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        inputField = new JTextField(20);
        JButton displayButton = new JButton("Display Books");
        JButton removeByBarcodeButton = new JButton("Remove by Barcode");
        JButton removeByTitleButton = new JButton("Remove by Title");
        JButton checkOutButton = new JButton("Check Out Book");
        JButton checkInButton = new JButton("Check In Book");
        JButton exitButton = new JButton("Exit");

        outputArea = new JTextArea(20, 50);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Add Components
        frame.add(new JLabel("Enter input:"));
        frame.add(inputField);
        frame.add(displayButton);
        frame.add(removeByBarcodeButton);
        frame.add(removeByTitleButton);
        frame.add(checkOutButton);
        frame.add(checkInButton);
        frame.add(exitButton);
        frame.add(scrollPane);

        // Event Listeners
        displayButton.addActionListener(e -> displayBooks());
        removeByBarcodeButton.addActionListener(e -> removeBookByBarcode());
        removeByTitleButton.addActionListener(e -> removeBookByTitle());
        checkOutButton.addActionListener(e -> checkOutBook());
        checkInButton.addActionListener(e -> checkInBook());
        exitButton.addActionListener(e -> frame.dispose());

        frame.setVisible(true);
    }

    private void displayBooks() {
        List<Book> books = library.getAllBooks();
        outputArea.setText("Books in Library:\n");
        books.forEach(book -> outputArea.append(book + "\n"));
    }

    private void removeBookByBarcode() {
        String barcode = inputField.getText();
        if (library.removeBookByBarcode(barcode)) {
            outputArea.append("Book with barcode " + barcode + " removed successfully.\n");
        } else {
            outputArea.append("Error: Book with barcode " + barcode + " not found.\n");
        }
    }

    private void removeBookByTitle() {
        String title = inputField.getText();
        if (library.removeBookByTitle(title)) {
            outputArea.append("Book with title '" + title + "' removed successfully.\n");
        } else {
            outputArea.append("Error: Book with title '" + title + "' not found.\n");
        }
    }

    private void checkOutBook() {
        String title = inputField.getText();
        if (library.checkOutBook(title)) {
            outputArea.append("Book with title '" + title + "' checked out successfully.\n");
        } else {
            outputArea.append("Error: Book with title '" + title + "' not found or already checked out.\n");
        }
    }

    private void checkInBook() {
        String title = inputField.getText();
        if (library.checkInBook(title)) {
            outputArea.append("Book with title '" + title + "' checked in successfully.\n");
        } else {
            outputArea.append("Error: Book with title '" + title + "' not found or already checked in.\n");
        }
    }

    public static void main(String[] args) {
        new LibraryGUI();
    }
}
