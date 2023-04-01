package org.sl.util;

import org.sl.entity.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StudentCreator {

    static String[] maleNames = {
            "Александр", "Андрей", "Артем", "Борис", "Вадим",
            "Валентин", "Василий", "Виктор", "Виталий", "Владимир",
            "Вячеслав", "Геннадий", "Георгий", "Даниил", "Денис",
            "Дмитрий", "Евгений", "Егор", "Иван", "Игорь",
            "Кирилл", "Константин", "Леонид", "Максим", "Михаил",
            "Никита", "Николай", "Олег", "Павел", "Петр",
            "Роман", "Руслан", "Семен", "Сергей", "Станислав",
            "Степан", "Тимур", "Федор", "Филипп", "Юрий",
            "Яков", "Ян", "Ярослав"
    };

    static String[] femaleNames = {
            "Александра", "Алина", "Алиса", "Анастасия", "Анжелика",
            "Анна", "Арина", "Валерия", "Варвара", "Василиса",
            "Вера", "Вероника", "Виктория", "Галина", "Дарья",
            "Евгения", "Екатерина", "Елена", "Жанна", "Зинаида",
            "Зоя", "Инна", "Ирина", "Карина", "Кира",
            "Кристина", "Лариса", "Лидия", "Любовь", "Маргарита",
            "Марина", "Мария", "Надежда", "Наталья", "Оксана",
            "Ольга", "Полина", "Раиса", "Регина", "Римма",
            "Светлана", "София", "Тамара", "Татьяна", "Ульяна",
            "Фаина", "Фатима", "Элеонора", "Элина", "Эльвира"
    };

    public static List<Student> create(int count) {
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            students.add(Student.builder()
                    .name(getRandomName())
                    .mark(new Random().nextInt(1, 6))
                    .build());
        }
        return students;
    }

    private static String getRandomName() {
        Random random = new Random();
        boolean isMale = random.nextBoolean();
        String[] names = isMale ? maleNames : femaleNames;
        int index = random.nextInt(names.length);
        return names[index];
    }
}
