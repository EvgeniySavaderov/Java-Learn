public class AdultPerson extends Person{
    private int credit;

    public AdultPerson(String name, int age, int money, int credit) { //конструкторы с добавлением параметра кредита
        super(name, age, money);
        if(credit<0)
            throw new IllegalArgumentException("Кредит не может быть отрицательным числом");
        this.credit = credit;
    }

    public AdultPerson(String name, int age, int money) {
        super(name, age, money);
        this.credit = 0;
    }

    @Override public String addProduct(Product product) { //покупка с учетом кредита
        int cost = product.getCost();
        if(cost<=this.getMoney()) {
            this.drawMoney(cost);
            this.getPurchases().add(product);
            return this.getName()+" купил " + product.getName();
        }
        if(cost<=this.credit) {
            this.credit -= cost;
            this.getPurchases().add(product);
            return this.getName()+" купил в кредит " + product.getName();
        }
        return this.getName()+" не может позволить себе " + product.getName();
    }

    @Override public boolean equals(Object o) { //равенство объектов
        if (this == o) return true; //проверка на идентичность классов и ссылки
        if (o == null || getClass() != o.getClass()) return false;

        if(!super.equals(o)) return false;
        AdultPerson p2 = (AdultPerson) o;
        if(this.credit!=p2.credit) return false;
        return true;
    }

    @Override public int hashCode() { //хэш-код
        if (this==null) {
            return 0;
        }

        int res = super.hashCode();
        res = 29 * res +this.credit;
        return res;
    }
}
