package org.example;

//В данном классе должно быть получено только поле surname
//префикс FE
@StudentFilter("FE")
public class StudentFE extends AbstractStudent {
    private final String name;

    @StudentFilter("")
    private final String surname;

    public StudentFE() {
        name = NameUtil.getRandomName();
        surname = NameUtil.getRandomName();
    }

    @Override
    public String toString() {
        return "StudentFE{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
