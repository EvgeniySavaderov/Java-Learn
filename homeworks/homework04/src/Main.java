import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        System.out.println("номер задачи(1-3) :"); //выбор задачи
        Scanner console = new Scanner(System.in).useDelimiter("\\n");
        int task = console.nextInt();
        switch(task){
            case 1:
                String keyboard = "qwertyuiopasdfghjklzxcvbnm"; //клавиатура
                System.out.print("Буква: ");
                String letter = console.next();

                char nextLetter = keyboard.charAt(keyboard.indexOf(letter)-1>=0 ? keyboard.indexOf(letter)-1: 25); //выбор предыдущего символа с циклом
                System.out.println("Предыдущая буква: "+nextLetter);
                break;
            case 2:
                System.out.print("Строка: ");
                String s2 = console.next();

                String regex = ">>-->|<--<<"; //паттерн "стрел"
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(s2);
                int numArrows = 0;
                while(matcher.find()) {
                    numArrows++;
                }
                System.out.println("Число стрелок: "+numArrows);
                break;
            case 3:
                System.out.print("Строка: ");
                String s3 = console.next();
                String[] res = s3.split(" "); //разделение по пробелу
                char[] res0 = res[0].toCharArray();
                char[] res1 = res[1].toCharArray();
                Arrays.sort(res0); //сортировка
                Arrays.sort(res1);
                String resString = new String(res0)+ " " + new String(res1);
                System.out.println("Новая строка: "+resString);
                break;
        }
    }
}