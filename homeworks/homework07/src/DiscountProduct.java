import java.util.regex.Pattern;

public class DiscountProduct extends  Product{
    protected int discount; //скидка

    public DiscountProduct(String name, int cost, int discount) {
        super(name, cost);
        this.discount = discount;
    }

    public int getDiscount() {
        return discount;
    }

    @Override public String toString() {
        return this.getName()+" Цена: "+this.getCost()+" Скидка: "+this.discount;
    }

    @Override public boolean equals(Object o) { //равенство объектов
        if (this == o) return true; //проверка на идентичность классов и ссылки
        if (o == null || getClass() != o.getClass()) return false;

        DiscountProduct p2 = (DiscountProduct) o;
        if(this.getName()!= p2.getName()) return false;
        if(this.getCost()!= p2.getCost()) return false;
        if(this.discount!=p2.discount) return false;
        return true;
    }

    @Override public int hashCode() { //хэш-код
        if (this==null) {
            return 0;
        }
        int res = this.getName().hashCode();
        res = 29 * res + this.getCost();
        res = 29 * res + this.discount;
        return res;
    }
}
