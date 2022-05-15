package week1;

public class printStar2 {
    public static void main(String[] args) {
        // 다이아몬드 상단
        for (int i = 0; i < 5; i++) {
            for (int j = i; j < 5; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < i; j++) {
                System.out.print("*");
            }
            for (int j = 0; j < i - 1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }

        // 다이아몬드 하단
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < i; j++) {
                System.out.print(" ");
            }
            for (int j = i; j < 5; j++) {
                System.out.print("*");
            }
            for (int j = i+1; j < 5; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
