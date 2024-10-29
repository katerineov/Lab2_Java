import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Main {
    // метод проверяет, соответствует ли строка дате в формате dd/mm/yyyy
    public static boolean isValidDate(String date) {
        String pattern = "^([0-2]\\d|3[01])/(0\\d|1[0-2])/([1-9]\\d{3})$"; // регулярное выражение для проверки базового формата dd/mm/yyyy
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(date);

        // если строка не соответствует формату dd/mm/yyyy, возвращаем false
        if (!matcher.matches()) {
            return false;
        }

        // парсим день, месяц и год из регулярного выражения
        int day = Integer.parseInt(matcher.group(1));
        int month = Integer.parseInt(matcher.group(2));
        int year = Integer.parseInt(matcher.group(3));

        // проверяем, что год находится в диапазоне от 1600 до 9999
        if (year < 1600 || year > 9999) {
            return false;
        }

        // проверка количества дней в зависимости от месяца
        switch (month) {
            case 2: // февраль
                // если год високосный, в феврале 29 дней
                if (isLeapYear(year)) {
                    return day <= 29;
                } else { // если не високосный, в феврале 28 дней
                    return day <= 28;
                }
            case 4: case 6: case 9: case 11: // апрель/июнь/сентябрь/ноябрь
                // в этих месяцах максимум 30 дней
                return day <= 30;
            default: // все остальные месяцы имеют максимум 31 день
                return day <= 31;
        }
    }

     // метод для проверки, является ли год високосным
    private static boolean isLeapYear(int year) {
        // год високосный, если он делится на 4, но не на 100, или делится на 400
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public static void main(String[] args) {
        // тесты для проверки
        System.out.println(isValidDate("29/02/2000")); // true, так как 2000 год - високосный
        System.out.println(isValidDate("29/02/2001")); // false, так как 2001 год - не високосный
        System.out.println(isValidDate("30/04/2003")); // true, так как апрель имеет 30 дней
        System.out.println(isValidDate("30-04-2003")); // false, неверный разделитель (- вместо /)
        System.out.println(isValidDate("1/1/1899"));   // false, так как год меньше 1600
    }
}