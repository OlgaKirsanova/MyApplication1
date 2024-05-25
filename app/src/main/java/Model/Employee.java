package Model;

public class Employee {
    private int id;
    private String name;
    private String surname;
//добавляем конструкторы в класс
    public Employee(String olga, String kirsanova) {
    }

    public Employee(int id) {
        this.id = id;
        this.name = name;
        this.surname =surname;
    }

    public Employee(String name) {
        this.name = name;
        this.surname =surname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String name) {
        this.surname = surname;

    }



}

