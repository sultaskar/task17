import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.FileHandler;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List<Book> books = new ArrayList<>();
        int userChoice = 0;
        File library = new File("library.txt");
        try {
            if (!(library.exists())) {
                library.createNewFile();
            }
        }catch(IOException e3){
            System.out.println("Ошибка");
        }

        while (userChoice != 5) {
            System.out.println("Выберите:\n1. Добавить книгу\n2. Сохранить книгу" +
                    "\n3. Восстановить содержимое библиотеки\n4. Выход");
            try {
                userChoice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Неверный формат ввода");
            }
            switch (userChoice) {
                case 1:
                    System.out.println("Введите название книги");
                    String bookTitle = scanner.next();
                    System.out.println("Введите автора книги");
                    String bookAuthor = scanner.next();
                    System.out.println("Введите год издания");
                    int bookIssue = scanner.nextInt();
                    books.add(new Book(bookTitle, bookAuthor, bookIssue));
                    break;
                case 2:
                    try (OutputStream output = new FileOutputStream(library)) {
                        ObjectOutputStream oos = new ObjectOutputStream(output);
                        oos.writeObject(books);
                    } catch (IOException e2) {
                        System.out.println("Ошибка записи в файл");
                    }
                    break;
                case 3:
                    try (InputStream input = new FileInputStream(library)) {
                        ObjectInputStream ois = new ObjectInputStream(input);
                        //byte buf[] = new byte[(int) library.length()];
                        while (ois.available() > 0) {
                            books = (List<Book>) ois.readObject();
                        }
                    } catch (Exception e3) {
                        System.out.println("Какая то ошибка");
                    }
                case 4:
                    for (Book book : books) {
                        System.out.println(book.toString());
                    }
            }


        }
    }
}
