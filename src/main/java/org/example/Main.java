package org.example;

public class Main {
    public static void main(String[] args) {
        Method method = new Method();
        Menu menu = new Menu(method);
        menu.displayMenu();
    }
}
