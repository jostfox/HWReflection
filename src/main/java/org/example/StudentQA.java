package org.example;

//В данном классе должно быть получено только поле number
//префикс QA
@StudentFilter("QA")
public class StudentQA extends AbstractStudent{
    private final String name;
    private final String surname;

    @StudentFilter("")
    private String number;

    public StudentQA() {
        name = NameUtil.getRandomName();
        surname = NameUtil.getRandomName();
        number = NameUtil.getRandomNumber();
    }

    @Override
    public String toString() {
        return "StudentQA{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
