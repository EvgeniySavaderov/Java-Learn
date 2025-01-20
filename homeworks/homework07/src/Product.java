import java.util.regex.Pattern;

public class Product {
    private String name;
    private int cost;

    static final String noNumbers = "\\d+";

    public Product(String name, int cost) { //конструктор с обработкой неверных данных
        if(name.length()<3 || Pattern.matches(noNumbers, name)) {
            throw new IllegalArgumentException("Недопустимое имя продукта!");
        }
        if(cost<=0) {
            throw new IllegalArgumentException("Недопустимая стоимость продукта!");
        }
        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return this.name;
    } //getters

    public int getCost() {
        return this.cost;
    }

    @Override public String toString() {
        return this.name+" Цена: "+this.cost;
    } //вывод в строку

    @Override public boolean equals(Object o) { //равенство объектов
        if (this == o) return true; //проверка на идентичность классов и ссылки
        if (o == null || getClass() != o.getClass()) return false;

        Product p2 = (Product) o;
        if(this.name!= p2.name) return false;
        if(this.cost!= p2.cost) return false;
        return true;
    }

    @Override public int hashCode() { //хэш-код
        if (this==null) {
            return 0;
        }
        int res = this.name.hashCode();
        res = 29 * res + this.cost;
        return res;
    }
}
