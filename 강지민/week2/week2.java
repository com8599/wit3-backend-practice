package week2;

import java.util.Scanner;

public class week2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("입력받을 수 : ");
        int a = sc.nextInt();
        printStar(a);
        printStar2(a);
    }

    public static void printStar(int a) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < a; i++) {
            sb.append("*");
            System.out.println(sb);
        }
    }

    public static void printStar2(int a) {
        // 다이아몬드 상단
        for (int i = 0; i < a; i++) {
            for (int j = i; j < a; j++) {
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
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < i; j++) {
                System.out.print(" ");
            }
            for (int j = i; j < a; j++) {
                System.out.print("*");
            }
            for (int j = i + 1; j < a; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
