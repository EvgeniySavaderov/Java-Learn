public class ChildPerson extends Person{
    public ChildPerson(String name, int age, int money) {
        super(name, age, money);
    }

    @Override public String addProduct(Product product) {//обработка некоректного возраста и товара
        if(this.getAge() < 6) {
            return "Ребёнок "+this.getName()+" не может покупать товары";
        }
        if(!product.getIsKidsAllowed()) {
            return "Товар "+product.getName()+" недоступен для ребёнка "+this.getName();
        }

        return super.addProduct(product);
    }
}
