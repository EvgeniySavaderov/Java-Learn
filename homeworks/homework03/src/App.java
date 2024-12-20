public class App {
    public static void main(String[] args) {
        //создание 3 объектов "телевизор"  и взаимодействие
        TV tv1 = new TV("Samsung", 15, true);
        System.out.println(tv1);

        TV tv2 = new TV("LG", 25, false);
        tv2.changeState();
        tv2.nextChannel();
        tv2.nextChannel();
        System.out.println(tv2);

        TV tv3 = new TV("Huawei", 35, true);
        tv3.changeState();
        tv3.goToChannel(43);
        System.out.println(tv3);

        System.out.println();

        TV tv4 = new TV(); //генерация 3 случайных "телевизоров"
        TV tv5 = new TV();
        TV tv6 = new TV();
        System.out.println(tv4);
        System.out.println(tv5);
        System.out.println(tv6);
    }
}