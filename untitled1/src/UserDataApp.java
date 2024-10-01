import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class UserDataApp {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Введите данные через пробел (Фамилия Имя Отчество дата рождения(dd.MM.yyyy) номер телефона пол(f или m):");
            String input = scanner.nextLine();

            // Разбиваем введенную строку на части
            String[] parts = input.split(" ");
            if (parts.length != 6) {
                if (parts.length < 6) {
                    System.err.println("Ошибка: введено меньше данных, чем требуется.");
                } else {
                    System.err.println("Ошибка: введено больше данных, чем требуется.");
                }
                return;
            }

            try {
                String lastName = parts[0];
                String firstName = parts[1];
                String middleName = parts[2];
                String birthDateStr = parts[3];
                String phoneNumberStr = parts[4];
                String gender = parts[5];

                // Парсинг даты рождения
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                Date birthDate = dateFormat.parse(birthDateStr);

                // Проверка номера телефона
                long phoneNumber = Long.parseLong(phoneNumberStr);

                // Проверка пола
                if (!gender.equals("f") && !gender.equals("m")) {
                    throw new IllegalArgumentException("Ошибка: неверный формат пола.");
                }

                // Запись данных в файл
                String fileName = lastName + ".txt";
                try (FileWriter writer = new FileWriter(fileName, true)) { // true для добавления в файл
                    writer.write("Фамилия:" + lastName + ",Имя:" + firstName + "Отчество:" + middleName + ",дата рождения:" + birthDateStr + "номер телефона:" + phoneNumber + ",пол:" + gender + "\n");
                    System.out.println("Данные успешно записаны в файл " + fileName);
                } catch (IOException e) {
                    System.err.println("Ошибка при записи в файл: " + e.getMessage());
                    e.printStackTrace();
                }
            } catch (ParseException e) {
                System.err.println("Ошибка: неверный формат даты рождения.");
                e.printStackTrace();
            } catch (NumberFormatException e) {
                System.err.println("Ошибка: неверный формат номера телефона.");
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.err.println("Произошла ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }
}