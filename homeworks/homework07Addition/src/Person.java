import java.util.ArrayList;
import java.util.StringJoiner;

public class Person {
    private String name;
    private int age;
    private int money;
    private ArrayList<Product> purchases;

    public Person(String name, int age, int money) { //конструктор с обработкой неверных данных
        if(name=="") {
            throw new IllegalArgumentException("Имя покупателя не может бытьт пустым");
        }
        if(age<0) {
            throw new IllegalArgumentException("Возраст не может быть отрицательным числом");
        }
        if(money<0) {
            throw new IllegalArgumentException("Сумма денег не может быть отрицательным числом");
        }

        this.name = name;
        this.age = age;
        this.money = money;
        this.purchases = new ArrayList<>();
    }

    public String getName() { return name; }

    public int getAge() { return age; }

    public int getMoney() { return money; }

    public ArrayList<Product> getPurchases() { return purchases; }

    public void drawMoney(int money) { this.money-=money; }

    public String addProduct(Product product) { //добавление продукта с учетом счета покупателя
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
        if(this.age!=p2.age) return false;
        if(this.money!=p2.money) return false;
        if(!this.purchases.equals(p2.purchases)) return false;
        return true;
    }

    @Override public int hashCode() { //хэш-код
        if (this==null) {
            return 0;
        }
        int res = this.name.hashCode();
        res = 29 * res + this.age;
        res = 29 * res + this.money;
        for(Product product: this.purchases) {
            res = 29 * res + product.hashCode();
        }
        return res;
    }
}
