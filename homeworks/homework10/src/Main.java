import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random rnd = new Random();

        int[] arr = new int[10]; //генерация случайного массива
        for(int i=0; i<10; i++) {
            arr[i] = rnd.nextInt(1000);
        }
        for(int i=0; i<10; i++) {
            System.out.print(arr[i]+" ");
        }
        System.out.println();
        System.out.println("Чётные числа");

        ByCondition isEven = num -> num % 2 == 0; //условие чентности
        int[] res1 = Sequence.filter(arr, isEven);
        for(int i=0; i<res1.length; i++) {
            System.out.print(res1[i]+" ");
        }
        System.out.println();
        System.out.println("Числа с чётной суммой цифр");

        ByCondition isSumDigitsEven = num -> {num = Math.abs(num); int sumDigit = 0; while (num > 0) { //условие четности суммы цифр
            sumDigit += num % 10;
            num /= 10;
        }
        return sumDigit % 2 == 0;
        };

        int[] res2 = Sequence.filter(arr, isSumDigitsEven);
        for(int i=0; i<res2.length; i++) {
            System.out.print(res2[i]+" ");
        }
    }
}