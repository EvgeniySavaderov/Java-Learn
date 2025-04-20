import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String[]> credentials = new ArrayList<>(); //список вводов параметров
        credentials.add(new String[] {"Vasya20", "g8ddd_4", "g8ddd_4"});
        credentials.add(new String[] {"Вася", "g8d4", "g8d4"});
        credentials.add(new String[] {"Grisha22", "пхим4", "пхим4"});
        credentials.add(new String[] {"Boriiiiiiiiiiiiiiiiiiiiis19", "g8ddd_4", "g8ddd_4"});
        credentials.add(new String[] {"Boriiiiiiiiiiiiiiiiiiiiis19", "g8ddd_4", "g8ddd_4"});
        credentials.add(new String[] {"Tolya", "asf5", "ghb2"});
        for(String[] cred: credentials) {
            System.out.println(cred[0]+" "+cred[1]+" "+cred[2]);
            System.out.println("Корректный ввод?: "+User.validate(cred[0], cred[1], cred[2]));
        }
    }
}