public class printStar {
    public static void main(String[] args) {
        // 방법 1
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < 11; i++) {
            sb.append("*");
            System.out.println(sb);
        }

        // 방법 2
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
