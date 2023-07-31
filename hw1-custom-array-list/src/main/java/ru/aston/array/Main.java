package ru.aston.array;

import ru.aston.array.custom.CustomArrayList;
import ru.aston.array.custom.CustomArrayListImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        CustomArrayList<Integer> customList = new CustomArrayListImpl<>();

        // Добавление одиночных элментов в лист
        customList.add(10);
        customList.add(5);
        customList.add(20);
        customList.add(15);

        // Проверка метода getCapacity
        System.out.println("Ёмкость листа: " + customList.getCapacity());

        // Респечатка листа
        System.out.println("Содержимое: " + customList);

        // Тестирование сортировки
        customList.sort(Comparator.naturalOrder());
        System.out.println("Сортировка: " + customList);

        // Добавляем методом addAll другой коллекции
        List<Integer> otherList = new ArrayList<>(Arrays.asList(30, 25, 35));
        customList.addAll(otherList);
        System.out.println("Добавили ArrayList: " + customList);

        // Проверка размера
        System.out.println("Размер: " + customList.size());

        // Проверяем метод get
        int element = customList.get(2);
        System.out.println("Элемент под индексом 2: " + element);

        // Проверяем удаление по индексу
        boolean removed = customList.remove(25);
        System.out.println("Элемент 25 удалён? " + removed);
        System.out.println("Лист после удаления элемента: " + customList);

        // Проверка размера после удаления
        System.out.println("Размер после удаления: " + customList.size());

        // Проверка очистки
        customList.clear();
        System.out.println("Лист после очистки: " + customList);

        // Проверка метода isEmpty
        System.out.println("Лист пустой? " + customList.isEmpty());

        // Проверка размера
        System.out.println("Размер: " + customList.size());

        // Проверка trimToSize
        customList.trimToSize();

        // Проверка метода getCapacity
        System.out.println("Ёмкость после clear() и trimToSize(): " + customList.getCapacity());
    }

}
