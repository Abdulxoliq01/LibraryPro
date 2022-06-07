package uz.muhammad.service.managerService;

import uz.muhammad.database.Database;
import uz.muhammad.exception.InvalidUsername;
import uz.muhammad.exception.NotActiveUser;
import uz.muhammad.template.Book;
import uz.muhammad.template.Library;
import uz.muhammad.template.User;
import uz.muhammad.template.UserRole;
import uz.muhammad.utils.Util;

public class MenuManager {
    public Util util = new Util();
    public Database db = Database.getDatabase();

    public void mainMenu(){
        if(!db.currentUser.isActive()) {
            try {
                throw new NotActiveUser();
            } catch (NotActiveUser e) {
                e.printStackTrace();
                util.pause();
                return;
            }
        }

        while (true){
            util.clear();
            util.green("Manager menu");
            System.out.println();
            System.out.println("0. Exit");
            System.out.println("1. Show library");
            System.out.println("2. Add book");
            System.out.println("3. Show books");
            System.out.println("4. Delete book");
            System.out.println("5. Profile edit");
            System.out.println("6. Add user");
            System.out.print("  => ");
            switch (util.numScan.nextInt()){
                case 0:
                    return;
                case 1:
                    showLibrary();
                    break;
                case 2:
                    addBook();
                    break;
                case 3:
                    showBooks();
                    util.numScan.nextInt();
                    break;
                case 4:
                    deleteBook();
                    break;
                case 5:
                    profileEdit();
                    break;
                case 6:
                    addUser();
                    break;
            }
        }
    }

    private void showLibrary() {
        showBooks();
        Library library = db.getLibraryFromUser(db.currentUser);
        System.out.println();
        util.red(util.middle("About library"));
        System.out.println();

        util.blue("Library name: ");
        System.out.println(library.getName());

        util.blue("Amount of books: ");
        System.out.println(library.getBooks().size());

        util.blue("Manager: ");
        System.out.println(library.getManager().getUsername());

        System.out.print(" => ");
        util.numScan.nextInt();
    }

    private void addBook() {
        util.clear();
        Book book = new Book();

        System.out.print("Enter book name: ");
        book.setName(util.strScan.nextLine());

        System.out.print("Enter author: ");
        book.setAuthor(util.strScan.nextLine());

        System.out.print("Enter amount: ");
        book.setAmount(util.numScan.nextInt());

        Library library = db.getLibraryFromUser(db.currentUser);
        library.getBooks().add(book);


        db.setLibraries();
        db.getBooks();
        db.setBooks();

        util.green(util.middle("Successfully added"));
        util.pause();
    }

    private void showBooks() {
        util.clear();
        util.blue(util.left("Book name", 30) + util.left("Author", 30) +
                "Amount");
        System.out.println();

        Library library = db.getLibraryFromUser(db.currentUser);
        for (Book book : library.getBooks()) {
            System.out.println(util.left(" * " + book.getName(), 30) +
                    util.left(book.getAuthor(), 30) + book.getAmount());
        }
    }

    private void deleteBook() {
        util.clear();
        util.green(util.middle("Delete book"));
        util.yellow("All books you have from library ");
        Library library = db.getLibraryFromUser(db.currentUser);
        util.red(library.getName());
        System.out.println();

        showBooks();
        util.blue("Enter book name to delete: ");
        String bookName = util.strScan.nextLine();

        for (Book book : library.getBooks()) {
            if(book.getName().equals(bookName)){
                library.getBooks().remove(book);
                util.green(util.middle("Successfully deleted"));
                util.pause();
                db.setLibraries();
                db.getBooks();
                db.setBooks();
                return;
            }
        }
        try {
            throw new InvalidUsername();
        } catch (InvalidUsername e) {
            e.printStackTrace();
            util.pause();
        }
    }

    private void profileEdit() {
        util.clear();
        util.red("Current username is " + db.currentUser.getUsername());
        System.out.println();
        util.red("Current password is " + db.currentUser.getPassword());
        System.out.println();
        util.blue("New username: ");
        db.currentUser.setUsername(util.strScan.nextLine());
        util.blue("New password: ");
        db.currentUser.setPassword(util.strScan.nextLine());
        util.green(util.middle("Successfully edited"));
        util.pause();

        db.setUsers();
    }

    private void addUser(){
        util.clear();
        System.out.println(util.middle("Add manager"));
        User user = new User();
        System.out.print("Username: ");
        user.setUsername(util.strScan.nextLine());
        System.out.print("Password: ");
        user.setPassword(util.strScan.nextLine());
        user.setActive(true);
        user.setRole(UserRole.USER);
        user.setHaveLibrary(false);
        db.users.add(user);
        db.setUsers();

        util.green(util.middle("Successfully added"));
        util.pause();
    }
}
