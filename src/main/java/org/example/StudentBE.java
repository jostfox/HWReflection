package org.example;

//В данном классе должно быть получено только поле name
//префикс BE
@StudentFilter("BE")
public class StudentBE extends AbstractStudent{

    @StudentFilter("")
    private final String name;

    private final String surname;

    public StudentBE() {
        name = NameUtil.getRandomName();
        surname = NameUtil.getRandomName();
    }

    @Override
    public String toString() {
        return "StudentBE{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
