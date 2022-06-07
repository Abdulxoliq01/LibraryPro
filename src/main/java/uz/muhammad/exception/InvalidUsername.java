package uz.muhammad.exception;

public class InvalidUsername extends Exception {
    public InvalidUsername(){
        super("Username not found");
    }
}
