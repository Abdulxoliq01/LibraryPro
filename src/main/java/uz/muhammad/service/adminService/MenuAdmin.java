package uz.muhammad.service.adminService;

import uz.muhammad.database.Database;
import uz.muhammad.template.User;
import uz.muhammad.template.UserRole;
import uz.muhammad.utils.Util;

import java.util.UUID;

public class MenuAdmin {
    public Util util = new Util();
    public Database db = Database.getDatabase();

    public void mainMenu(){
        while (true){
            util.clear();
            System.out.println("0. Log out");
            System.out.println("1. Managers menu");
            System.out.println("2. Libraries menu");
            System.out.println("3. Books menu");
            System.out.println("4. Profile edit");
            System.out.print  (" ==> ");
            switch (util.numScan.nextInt()){
                case 0:
                    return;
                case 1:
                    ManagersMenu managersMenu = new ManagersMenu();
                    managersMenu.mainMenu();
                    break;
                case 2:
                    LibrariesMenu libraryMenu = new LibrariesMenu();
                    libraryMenu.mainMenu();
                    break;
                case 3:
                    BooksMenu booksMenu = new BooksMenu();
                    booksMenu.mainMenu();
                    break;
                case 4:
                    ProfileMenu profileMenu = new ProfileMenu();
                    profileMenu.mainMenu();
                    break;
            }
        }
    }
}
