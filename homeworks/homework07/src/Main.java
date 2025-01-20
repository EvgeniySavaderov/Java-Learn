import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in).useDelimiter("\\n");
        String patternProducts = "[a-zA-Zа-яА-Я0-9][a-zA-Zа-яА-Я0-9 ]* = -?\\d+"; //regex патерны ввода продуктов
        String patternDiscountProducts = "[a-zA-Zа-яА-Я0-9][a-zA-Zа-яА-Я0-9 ]* = -?\\d+, \\d+%";

        ArrayList<Product> products = new ArrayList<>(); //списки продуктов и дисконтных продуктов
        ArrayList<DiscountProduct> discountProducts = new ArrayList<>();

        String s = scanner.next();
        while(!s.equals("END")) { //обработка ввода
            if(Pattern.matches(patternProducts, s)) {
                String[] res = s.split(" = ");
                try {
                    products.add(new Product(res[0], Integer.parseInt(res[1])));
                }
                catch(IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            } else if (Pattern.matches(patternDiscountProducts, s)) {
                String[] res = s.split(" = |, |%");
                try {
                    discountProducts.add(new DiscountProduct(res[0], Integer.parseInt(res[1]), Integer.parseInt(res[2])));
                }
                catch(IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
            s = scanner.next();
        }

        StringJoiner sj = new StringJoiner(", "); //вывод данных
        for(Product p: products) {
            sj.add(p.getName());
        }
        if(products.size()>0) {
            System.out.println("Обычные продукты: "+sj);
        }
        sj = new StringJoiner(", ");
        for(DiscountProduct p: discountProducts) {
            sj.add(p.getName());
        }
        if(discountProducts.size()>0) {
            System.out.println("Акционные продукты: "+sj);
        }
    }
}