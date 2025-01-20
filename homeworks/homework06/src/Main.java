import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Map;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Map<String, Person> mapPerson = new HashMap<>(); //словарь значений покупателей и покупок по имени
        Map<String, Product> mapProduct = new HashMap<>();

        Scanner scanner = new Scanner(System.in).useDelimiter("\\n");
        String s = scanner.next();
        Pattern pattern = Pattern.compile("[a-zA-Zа-яА-Я0-9][a-zA-Zа-яА-Я0-9 ]* = -?\\d+"); //воод данных покупателей
        Matcher matcher = pattern.matcher(s);
        while(matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            String[] res = s.substring(start, end).split(" = ");
            try {
                mapPerson.put(res[0], new Person(res[0], Integer.parseInt(res[1])));
            }
            catch(IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        s = scanner.next();
        pattern = Pattern.compile("[a-zA-Zа-яА-Я0-9][a-zA-Zа-яА-Я0-9 ]* = -?\\d+"); //ввод данных продуктов
        matcher = pattern.matcher(s);
        while(matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            String[] res = s.substring(start, end).split(" = ");
            try {
                mapProduct.put(res[0], new Product(res[0], Integer.parseInt(res[1])));
            }
            catch(IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        s = scanner.next();
        while(!s.equals("END")) { //обработка покупок
            String[] res = s.split("; ");
            if(res.length==2 && mapPerson.containsKey(res[0]) && mapProduct.containsKey(res[1])) {
                Person person = mapPerson.get(res[0]);
                Product product = mapProduct.get(res[1]);
                System.out.println(person.AddProduct(product));
            }
            s = scanner.next();
        }

        System.out.println(); //вывод покупок клиентов
        for(Map.Entry<String, Person> entry: mapPerson.entrySet()) {
            System.out.println(entry.getValue());
        }
    }
}