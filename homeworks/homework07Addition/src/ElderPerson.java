public class ElderPerson extends Person{
    static final int extraDiscount = 5;

    ElderPerson(String name, int age, int money) {
        super(name, age, money);
    }

    @Override public String addProduct(Product product) {
        if(product.getClass()!=DiscountProduct.class) { //только акционные товары
            return "Пенсионер "+this.getName()+" не покупает не акционный товар "+product.getName();
        }
        DiscountProduct discountProduct = (DiscountProduct) product;

        int cost = discountProduct.getExtraDiscountCost(extraDiscount); //цена с учетом дополнительной скидки
        if(cost<=this.getMoney()) {
            this.drawMoney(cost);
            this.getPurchases().add(product);
            return this.getName()+" купил " + product.getName();
        }
        return this.getName()+" не может позволить себе " + product.getName();
    }
}
