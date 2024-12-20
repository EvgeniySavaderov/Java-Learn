import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("номер задачи(1-4) :");
        Scanner console = new Scanner(System.in);
        int task = console.nextInt();
        switch (task) { //выбор задачи
            case 1:
                System.out.print("Введите степень в градусах Фаренгейта: ");
                double t_farenheit = console.nextInt();
                double t_celsiy = (t_farenheit - 32) / 1.8; //преобразование
                System.out.println(String.format("%.2f градусов по Фаренгейту равна %.2f по Цельсию", t_farenheit, t_celsiy));
                break;
            case 2:
                System.out.print("Введите первое число: ");
                int n1 = console.nextInt();
                System.out.print("Введите второе число: ");
                int n2 = console.nextInt();

                int sum = n1 + n2, dif = n1 - n2, prod = n1 * n2, range = Math.abs(n1 - n2), max = Math.max(n1, n2), min = Math.min(n1, n2); //вычисление
                double mean = (n1 + n2) / 2.0;

                System.out.println(String.format("Сумма двух целых чисел: %d", sum)); //вывод
                System.out.println(String.format("Разница двух целых чисел: %d", dif));
                System.out.println(String.format("Произведение из двух целых чисел: %d", prod));
                System.out.println(String.format("Среднее двух целых чисел: %.1f", mean));
                System.out.println(String.format("Расстояние двух целых чисел: %d", range));
                System.out.println(String.format("Максимальное целое число: %d", max));
                System.out.println(String.format("Минимальное целое число: %d", min));
                break;

            case 3:
                System.out.print("Исходная строка: ");
                String s = console.next();
                System.out.print("Сколько раз вывести строку? ");
                int n = console.nextInt();

                String repeat = ""; //повтор строки
                for (int i = 0; i < n; i++) {
                    repeat += s;
                }
                System.out.println(String.format("После повторения %d раз: %s", n, repeat));
                break;

            case 4:
                System.out.print("Введите число строк и столбцов сетки: ");
                int l = console.nextInt();
                System.out.print("Введите повторяемый элемент сетки: ");
                String c = console.next();

                String row = ""; //генерация строки
                for (int i = 0; i < l; i++) {
                    row += c;
                }

                String table = row; //генерация сетки
                for (int i = 1; i < l; i++) {
                    table+="\n"+row;
                }

                System.out.println(String.format("Сетка по запросу %1$d на %1$d", l));
                System.out.println(table);
                break;
        }
    }
}