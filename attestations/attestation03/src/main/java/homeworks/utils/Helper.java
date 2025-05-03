package homeworks.utils;

import homeworks.model.PizzaBase;

import java.util.List;

public class Helper {
    private static double getMul(String size) { //определение размера
        return switch (size) {
            case "small" -> 0.75;
            case "big" -> 1.2;
            case "extreme" -> 1.5;
            default -> 1;
        };
    }

    public static Integer countCost(List<String> sizes, List<Integer> amount, List<PizzaBase> pizzaBaseList) { //расчет цены заказа
        int n = sizes.size();
        double res = 0;
        for(int i=0; i<n; i++) {
            PizzaBase pizzaBase = pizzaBaseList.get(i);
            if(pizzaBase!=null) {
                res += pizzaBase.getBaseCost() * amount.get(i) * getMul(sizes.get(i));
            }
        }
        return (int) res;
    }
}
