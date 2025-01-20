import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Map<String, Person> mapPerson = new HashMap<>(); //генерализованные сеты покупателей и продуктов
        Map<String, Product> mapProduct = new HashMap<>();

        Scanner scanner = new Scanner(System.in).useDelimiter("\\n");
        String s = scanner.next();
        Pattern pattern = Pattern.compile("[a-zA-Zа-яА-Я]+, -?\\d+ = -?\\d+;"); //regex-паттерны покупателей
        Pattern pattern2 = Pattern.compile("[a-zA-Zа-яА-Я]+, -?\\d+ = -?\\d+, -?\\d+;");

        Matcher matcher = pattern.matcher(s);
        while(matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            String[] res = s.substring(start, end).split(" = |, |;");
            try {
                int age = Integer.parseInt(res[1]);
                if(age<18) { //классификация объекта в зависмости от парамета age
                    mapPerson.put(res[0], new ChildPerson(res[0], Integer.parseInt(res[1]), Integer.parseInt(res[2])));
                }
                else if(age<65) {
                    mapPerson.put(res[0], new AdultPerson(res[0], Integer.parseInt(res[1]), Integer.parseInt(res[2])));
                }
                else {
                    mapPerson.put(res[0], new ElderPerson(res[0], Integer.parseInt(res[1]), Integer.parseInt(res[2])));
                }
            }
            catch(IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        matcher = pattern2.matcher(s);
        while(matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            String[] res = s.substring(start, end).split(" = |, |;");
            try {
                int age = Integer.parseInt(res[1]);
                if(age>18 && age<65) {
                    mapPerson.put(res[0], new AdultPerson(res[0], Integer.parseInt(res[1]), Integer.parseInt(res[2]), Integer.parseInt(res[3])));
                }
            }
            catch(IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        pattern = Pattern.compile("[a-zA-Zа-яА-Я0-9][a-zA-Zа-яА-Я0-9 ]* = -?\\d+(, -?\\d+)?(, Не для детей)?;"); //regex-паттерн продуктов
        s = scanner.next();
        matcher = pattern.matcher(s);
        while(matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            String[] res = s.substring(start, end).split(" = |, |;");
            try {
                mapProduct.put(res[0], chooseConstructProduct(res));
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
                System.out.println(person.addProduct(product));
            }
            s = scanner.next();
        }
        System.out.println();

        for(Map.Entry<String, Person> entry: mapPerson.entrySet()) {
            System.out.println(entry.getValue());
        }
    }

    static Product chooseConstructProduct(String[] res) { //выбор конструктора продукта
        if(res.length==2) {
            return new Product(res[0], Integer.parseInt(res[1]));
        }
        if(res.length==3 && !res[2].equals("Не для детей")) {
            return new DiscountProduct(res[0], Integer.parseInt(res[1]), Integer.parseInt(res[2]));
        }
        if(res.length==3 && res[2].equals("Не для детей")) {
            return new Product(res[0], Integer.parseInt(res[1]), false);
        }
        if(res.length==4) {
            return new DiscountProduct(res[0], Integer.parseInt(res[1]), Integer.parseInt(res[2]), false);
        }
        System.out.println(res[3]);
        return null;
    }
}