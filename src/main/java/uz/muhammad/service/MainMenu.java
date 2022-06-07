package uz.muhammad.service;

import uz.muhammad.database.Database;
import uz.muhammad.exception.InvalidUsernameOrPassword;
import uz.muhammad.service.adminService.MenuAdmin;
import uz.muhammad.service.managerService.MenuManager;
import uz.muhammad.service.userService.MenuUser;
import uz.muhammad.template.User;
import uz.muhammad.utils.Util;

public class MainMenu {
    public Util util = new Util();
    public Database db = Database.getDatabase();
    public MenuAdmin menuAdmin = new MenuAdmin();
    public MenuManager menuManager = new MenuManager();
    public MenuUser menuUser = new MenuUser();

    public void mainMenu(){
        loader();
        while (true){
            util.clear();
            System.out.println(util.middle("Online library"));
            System.out.println("0. Exit");
            System.out.println("1. Login");
            System.out.print(" ==> ");
            switch (util.numScan.nextInt()){
                case 0:
                    return;
                case 1:
                    login();
                    break;
            }
        }
    }

    private void login() {
        util.clear();
        System.out.println(util.middle("Login"));
        System.out.print("Username: ");
        String username = util.strScan.nextLine();
        System.out.print("Password: ");
        String password = util.strScan.nextLine();

        for (User user : db.users) {
            if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                util.green(util.middle("Successfully logged in"));
                util.pause();
                db.currentUser = user;
                switch (user.getRole()){
                    case ADMIN -> menuAdmin.mainMenu();
                    case MANAGER -> menuManager.mainMenu();
                    case USER -> menuUser.mainMenu();
                }
                return;
            }
        }
        try {
            throw new InvalidUsernameOrPassword();
        } catch (InvalidUsernameOrPassword e) {
            e.printStackTrace();
            util.pause();
        }
    }

    private void loader() {
        db.getUsers();
        db.getLibraries();
        db.getBooks();
        //db.getUsers0();
    }
}
