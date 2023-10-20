package pl.javastart.task;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Library {
    private static final int LIBRARY_SIZE = 3;
    private static final Scanner sc = new Scanner(System.in);

    private final Book[] books = new Book[LIBRARY_SIZE];
    private int currentBooksNumber = 0;

    void run() {
        System.out.println("Podaj informację o trzech unikalnych książkach");
        fillInBooksArray();
        printBooks();
        closeScanner();
    }

    private void printBooks() {
        System.out.println("Obiekty zapisane w tablicy:");

        for (Book book : books) {
            System.out.println(book);
        }
    }

    private void fillInBooksArray() {
        Book currentBook;

        do {
            try {
                currentBook = readBookDetails();

                if (isDuplicatedBook(currentBook)) {
                    System.out.println("Duplikat");
                } else {
                    books[currentBooksNumber++] = currentBook;
                }
            } catch (InputMismatchException e) {
                System.out.println("Wprowadziłeś dane w złym formacie, spróbuj ponownie");
            } finally {
                clearBuffer();
            }

        } while (currentBooksNumber < LIBRARY_SIZE);
    }

    private boolean isDuplicatedBook(Book book) {
        for (int i = 0; i < currentBooksNumber; i++) {
            if (book.equals(books[i])) {
                return true;
            }
        }

        return false;
    }

    private Book readBookDetails() {
        System.out.println("Podaj tytuł");
        String title = sc.nextLine();
        System.out.println("Podaj ilość stron");
        int pages = sc.nextInt();
        return new Book(title, pages);
    }

    private void closeScanner() {
        sc.close();
    }

    private void clearBuffer() {
        sc.nextLine();
    }

}
