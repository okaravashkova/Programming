// если класс паблик, то имя класса обязано совпадать с именем программы

public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
    /* в программе может быть несколько классов, если
     они вложены в основной     */ 
    class Vlozhen{}
}

// или если они написаны тут без слова паблик

class Nepublic{}