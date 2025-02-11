import java.util.regex.Pattern;

public class Product {
    private String name;
    private int cost;
    private boolean isKidsAllowed; //доступео для детей

    static final String noNumbers = "\\d+";

    public Product(String name, int cost) { //конструкторы с обработкой неверных данных
        if(name.length()<3 || Pattern.matches(noNumbers, name)) {
            throw new IllegalArgumentException("Недопустимое имя продукта!");
        }
        if(cost<=0) {
            throw new IllegalArgumentException("Недопустимая стоимость продукта!");
        }
        this.name = name;
        this.cost = cost;
        isKidsAllowed = true;
    }

    public Product(String name, int cost, boolean isKidsAllowed) {
        if(name.length()<3 || Pattern.matches(noNumbers, name)) {
            throw new IllegalArgumentException("Недопустимое имя продукта!");
        }
        if(cost<=0) {
            throw new IllegalArgumentException("Недопустимая стоимость продукта!");
        }
        this.name = name;
        this.cost = cost;
        this.isKidsAllowed = isKidsAllowed;
    }

    public String getName() {
        return this.name;
    }

    public int getCost() {
        return this.cost;
    }

    public boolean getIsKidsAllowed() {
        return this.isKidsAllowed;
    }

    @Override public String toString() {
        return this.name+" Цена: "+this.cost+" Доступно для детей: "+this.isKidsAllowed;
    }

    @Override public boolean equals(Object o) { //равенство объектов
        if (this == o) return true; //проверка на идентичность классов и ссылки
        if (o == null || getClass() != o.getClass()) return false;

        Product p2 = (Product) o;
        if(this.name!=p2.name) return false;
        if(this.cost!=p2.cost) return false;
        if(this.isKidsAllowed!= p2.isKidsAllowed) return false;
        return true;
    }

    @Override public int hashCode() { //хэш-код
        if (this==null) {
            return 0;
        }
        int res = this.name.hashCode();
        res = 29 * res + this.cost;
        res = 29 * res + (this.isKidsAllowed ? 1: 0);
        return res;
    }
}
