import java.util.ArrayList;
import java.util.StringJoiner;

public class Person {
    private String name;
    private int money;
    private ArrayList<Product> purchases; //список покупок

    public Person(String name, int money) { //конструктор с обработкой не верных значений
        if(name=="") {
            throw new IllegalArgumentException("Имя покупателя не может бытьт пустым");
        }
        if(money<0) {
            throw new IllegalArgumentException("Сумма денег не может быть отрицательным числом");
        }

        this.name = name;
        this.money = money;
        this.purchases = new ArrayList<>();
    }

    public String AddProduct(Product product) { //добавление продукта с возвратом строки с результатом
        int cost = product.getCost();
        if(cost<=this.money) {
            this.money -= cost;
            this.purchases.add(product);
            return this.name+" купил " + product.getName();
        }
        return this.name+" не может позволить себе " + product.getName();
    }

    @Override public String toString() {
        String res = this.name+" - ";

        if(this.purchases.size()>0) {
            StringJoiner sj = new StringJoiner(", ");
            for(Product product: this.purchases) {
                sj.add(product.getName());
            }
            res+=sj;
            return res;
        }

        res += "Ничего не куплено";
        return res;
    }

    @Override public boolean equals(Object o) { //равенство объектов
        if (this == o) return true; //проверка на идентичность классов и ссылки
        if (o == null || getClass() != o.getClass()) return false;

        Person p2 = (Person) o;
        if(this.name!=p2.name) return false;
        if(this.money!=p2.money) return false;
        if(!this.purchases.equals(p2.purchases)) return false;
        return true;
    }

    @Override public int hashCode() { //хэш-код
        if (this==null) {
            return 0;
        }

        int res = this.name.hashCode();
        res = 29 * res + this.money;
        for(Product product: this.purchases) {
            res = 29 * res + product.hashCode();
        }
        return res;
    }
}
