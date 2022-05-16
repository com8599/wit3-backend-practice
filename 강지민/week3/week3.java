package week3;

import java.util.Scanner;

public class week3 {
    public static void main(String[] args) {
        parentWeek3 pw = new parentWeek3();
        childWeek3 cw = new childWeek3();

        pw.printStar();
        cw.printStar();
    }
}

class parentWeek3 {
    int a = 5;

    public void printStar() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < a; i++) {
            sb.append("*");
            System.out.println(sb);
        }
    }
}

class childWeek3 extends parentWeek3 {

    @Override
    public void printStar() {
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
