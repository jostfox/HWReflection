package org.example;

/*
1.В классы можно добавлять только аннотации над методами или полями и классам
Добавлять что-то еще нельзя.
2.Определять какие поля должны быть использованы в результате, можно только
по аннотациям. Аналогично , какой метод нужно вызвать только по аннотации.
3. instanceof _ не используем
4. Указывать явно имена классов или полей и методов - нельзя. Только по аннотации
Для того что бы понять есть ли там аннотация - используйте isAnnotationPresent
5. Должна быть только одна собственная аннотация для всех полей, классов и метода.
 */

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class StringCollectorApp {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        List<String> strings = Arrays.asList("org.example.StudentBE",
                "org.example.StudentFE",
                "org.example.StudentQA");

        List<AbstractStudent> studentList = new ArrayList<>();

        //Создаем список из 20 студентов с помощью reflection
        for (int i = 0; i < 20; i++) {
            int index = ThreadLocalRandom.current().nextInt(0, 3);
            String className = strings.get(index);
            AbstractStudent student = (AbstractStudent) Class.forName(className).getDeclaredConstructor().newInstance();
            studentList.add(student);
        }

        // Полный список созданных студентов. Для проверки...
        for (AbstractStudent students : studentList) {
            System.out.println(students);
        }

        stringCollectorHandler(studentList);
    }


    //Здесь написать логику , которая переберет все объекты в списке
    //определит по аннотации к какому типу относится данный объект
    //(каждый класс помеченный аннотацией, должен иметь поле префикс в аннотации)


    //Вся собранная информация должна добавиться в одну строку.
    //Например : из объекта типа StudentBE - берем префикс из аннотации класса + добавляем
    //значение из поля , которое должно быть помечено этой же аннотацией в классе
    //(внутри каждого класса прописано, какие поля нужно пометить и какой префикс должен быть)
    //что бы получить значение поля через reflection api - используем метод get у поля, в который
    //передаем тот объект у которого получаем. Не забывайте, если поли приватное, то нужно дать к нему доступ.

    //После того как вся информация из полей будет добавлена в результирующую строку, создать
    //объект типа Result и вызвать у него метод помеченный аннотацией.
    //в метод передать результирующую строку

    private  static String stringCollector(List<AbstractStudent> list) throws IllegalAccessException {
        List<String> symbolList = new ArrayList<>();
        for (AbstractStudent student : list) {
            symbolList.add(prefixIdent(student) + fieldValue(student));
        }
        return String.join(" ", symbolList);
    }

    private static String prefixIdent(AbstractStudent student) {
        StudentFilter annotation = student.getClass().getAnnotation(StudentFilter.class);
        return annotation.value();
    }

    private static String fieldValue(AbstractStudent student) throws IllegalAccessException {
        Field[] fields = student.getClass().getDeclaredFields();
        for (Field elem : fields) {
            if (elem.isAnnotationPresent(StudentFilter.class)) {
                elem.setAccessible(true);
                return elem.get(student).toString();
            }
        }
        return null;
    }

    public static void stringCollectorHandler(List<AbstractStudent> list) throws IllegalAccessException, InvocationTargetException {
        Result result = new Result();
        Method[] methods = result.getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(StudentFilter.class)) {
                method.setAccessible(true);
                method.invoke(result, stringCollector(list));
            }
        }
    }
}











