package org.example;

public class Result extends AbstractStudent{

    //В данном классе должен быть вызван метод printStudentsInfo

        public Result() {
            //
        }

        @StudentFilter("")
        private void printStudentsInfo(String info) {
            System.out.println(info);
        }
}
