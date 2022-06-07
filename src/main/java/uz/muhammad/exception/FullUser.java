package uz.muhammad.exception;

public class FullUser extends Exception {
    public FullUser(){
        super("You have the maximum number of books (5)");
    }
}
