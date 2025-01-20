import java.util.Arrays;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        TV tv1 = new TV("Samsung", 20, true, true, 23, 60); //равенсто объектов
        TV tv2 = new TV("Sam"+"sung", 10+10, true, true, 23, 60);
        TV tv3 = new TV("Huawei", 70, false, true, 5, 33);
        System.out.println(tv1.equals(tv2));
        System.out.println(tv1.equals(tv3));

        System.out.println(tv1.hashCode()); //хэш коды
        System.out.println(tv2.hashCode());
        System.out.println(tv3.hashCode());
        System.out.println();


        TV[] televisors= new TV[10]; //создание списка 10 телевизоров
        for(int i=0; i<10; i++) {
            televisors[i] = new TV();
            System.out.println(televisors[i]);
        }
        System.out.println();

        Scanner console = new Scanner(System.in).useDelimiter("\\n"); //фильтр по громкости
        System.out.print("Допустимая громкость: ");
        int allowedVolume = console.nextInt();
        System.out.println("Подходящие телевизоры: ");
        for(TV tv: televisors) {
            if(tv.isAllowedVolume(allowedVolume)) {
                System.out.println(tv);
            }
        }
        System.out.println();

        Arrays.sort(televisors); //сортировка по каналу
        System.out.println("Отсортированные телевизоры: ");
        for(TV tv: televisors) {
            System.out.println(tv);
        }
    }
}