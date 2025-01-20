public class DiscountProduct extends  Product{
    private int discount;

    public DiscountProduct(String name, int cost, int discount) { //конструкторы с добавлением параметра скидки
        super(name, cost, true);
        if(discount<0) {
            throw new IllegalArgumentException("Недопустимая скидка продукта!");
        }
        this.discount = discount;
    }

    public DiscountProduct(String name, int cost, int discount, boolean isKidsAllowed) {
        super(name, cost, isKidsAllowed);
        if(discount<0) {
            throw new IllegalArgumentException("Недопустимая скидка продукта!");
        }
        this.discount = discount;
    }

    @Override public int getCost() {
        return (int) (super.getCost() / 100.0 * (100-this.discount));
    } //цена со скикдкой

    public int getExtraDiscountCost(int extraDiscount) { //цена с дополнительной скидкой
        return (int) (super.getCost() / 100.0 * (100-this.discount-extraDiscount));
    }

    public int getBaseCost() {
        return super.getCost();
    }

    public int getDiscount() {
        return discount;
    }

    @Override public String toString() {
        return this.getName()+" Цена: "+this.getCost()+" Скидка: "+this.getDiscount()+" Доступно для детей: "+this.getIsKidsAllowed();
    }

    @Override public boolean equals(Object o) { //равенство объектов
        if (this == o) return true; //проверка на идентичность классов и ссылки
        if (o == null || getClass() != o.getClass()) return false;

        if(!super.equals(o)) return false;
        DiscountProduct p2 = (DiscountProduct) o;
        if(this.discount!=p2.discount) return false;
        return true;
    }

    @Override public int hashCode() { //хэш-код
        if (this==null) {
            return 0;
        }

        int res = super.hashCode();
        res = 29 * res + this.discount;
        return res;
    }
}
