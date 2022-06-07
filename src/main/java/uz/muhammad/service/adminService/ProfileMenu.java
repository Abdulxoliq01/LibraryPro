package uz.muhammad.service.adminService;

import uz.muhammad.database.Database;
import uz.muhammad.template.User;
import uz.muhammad.template.UserRole;
import uz.muhammad.utils.Util;

public class ProfileMenu {
    public Util util = new Util();
    public Database db = Database.getDatabase();

    public void mainMenu(){
        while (true){
            util.clear();
            System.out.println("0. Exit");
            System.out.println("1. Info");
            System.out.println("2. Edit profil");
            System.out.print  ("  ==> ");
            switch (util.numScan.nextInt()){
                case 0:
                    return;
                case 1:
                    info();
                    break;
                case 2:
                    editProfile();
                    break;
            }
        }
    }

    private void info() {
        util.clear();
        util.red("* You are boss admin");
        System.out.println();
        System.out.println("* You have " + db.libraries.size() + " libraries");

        int amountOfManagers = 0;
        for (User user : db.users) {
            if(user.getRole().equals(UserRole.MANAGER))
                amountOfManagers++;
        }
        System.out.println("* The number of all managers is " + amountOfManagers);

        System.out.println("* The number of all users is " + (db.users.size() - 1 - amountOfManagers));
        System.out.println("* The number of all books is " + db.books.size());
        System.out.print("  ==> ");
        util.numScan.nextInt();
    }

    private void editProfile() {
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
}
