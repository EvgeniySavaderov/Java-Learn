public class Product {
    private String name;
    private int cost;


    public Product(String name, int cost) {
        if(name=="") {
            throw new IllegalArgumentException("Имя продукта не может бытьт пустым");
        }
        if(cost<0) {
            throw new IllegalArgumentException("Цена не может быть отрицательным числом");
        }
        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return this.name;
    }

    public int getCost() {
        return this.cost;
    }

    @Override public String toString() {
        return this.name+" Цена: "+this.cost;
    }

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
