import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Map<String, Person> mapPerson = new HashMap<>();
        Map<String, Product> mapProduct = new HashMap<>();
        PrintWriter printWriter = null;

        try (FileInputStream in = new FileInputStream("in.txt"); //использование входного и выходного stream
                FileOutputStream out = new FileOutputStream("out.txt")) {
            Scanner scanner = new Scanner(in).useDelimiter("\\n");
            printWriter = new PrintWriter(out);

            String s = scanner.next();
            Pattern pattern = Pattern.compile("[a-zA-Zа-яА-Я0-9][a-zA-Zа-яА-Я0-9 ]* = -?\\d+"); //regex-паттерн покупалтеля
            Matcher matcher = pattern.matcher(s);
            while(matcher.find()) {
                int start = matcher.start();
                int end = matcher.end();
                String[] res = s.substring(start, end).split(" = ");
                try {
                    mapPerson.put(res[0], new Person(res[0], Integer.parseInt(res[1])));
                }
                catch(IllegalArgumentException e) {
                    printWriter.println(e.getMessage());
                }
            }

            s = scanner.next();
            pattern = Pattern.compile("[a-zA-Zа-яА-Я0-9][a-zA-Zа-яА-Я0-9 ]* = -?\\d+"); //regex-паттерн продукта
            matcher = pattern.matcher(s);
            while(matcher.find()) {
                int start = matcher.start();
                int end = matcher.end();
                String[] res = s.substring(start, end).split(" = ");
                try {
                    mapProduct.put(res[0], new Product(res[0], Integer.parseInt(res[1])));
                }
                catch(IllegalArgumentException e) {
                    printWriter.println(e.getMessage());
                }
            }

            s = scanner.next();
            while(!s.equals("END")) {
                String[] res = s.split("; |\\r");
                if(res.length==2 && mapPerson.containsKey(res[0]) && mapProduct.containsKey(res[1])) { //добавление продукта
                    Person person = mapPerson.get(res[0]);
                    Product product = mapProduct.get(res[1]);
                    printWriter.println(person.AddProduct(product));
                }
                s = scanner.next();
            }

            printWriter.println();
            for(Map.Entry<String, Person> entry: mapPerson.entrySet()) { //вывод в файл
                printWriter.println(entry.getValue());
            }
            printWriter.flush();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if(printWriter!=null) {
                printWriter.close();
            }
        }
    }
}