import java.util.StringJoiner;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // задача 1
        int[] nums = new int[]{1, 2, 3, 4};
        StringJoiner sj = new StringJoiner("   ");
        for (int i = 0; i < 4; i++) {
            sj.add(Integer.toString(nums[i]));
        }
        String s = sj.toString();
        System.out.println(s);
        System.out.println();

        //задача 2
        System.out.println("камень-0, ножницы-1, бумага-2");

        Scanner console = new Scanner(System.in); //ввод чисел
        System.out.println("ход Васи:");
        int vasya = console.nextInt();
        System.out.println("ход Пети:");
        int petya = console.nextInt();

        if(vasya==petya) { // определение победителя
            System.out.println("Ничья");
        }
        else if((vasya+1)%3==petya) {
            System.out.println("Вася выиграл");
        }
        else {
            System.out.println("Петя выиграл");
        }
    }
}