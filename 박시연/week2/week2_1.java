package week2;

public class week2_1 {

    public static void printstart() {
        for (int i = 1; i < 10; i++) {
            for (int j = 0; j < i; j++) {
                System.out.print("*");
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        printstart();
    }
}