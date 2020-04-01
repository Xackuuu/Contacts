package contacts;

import java.io.IOException;
import java.io.Serializable;

public class Person extends Contact implements Serializable{
    String surname;
    String birthDate;
    String gender;

    private static final long serialVersionUID = 7L;

    public Person(int id, String name, String surname, String birthDate, String gender, String number, String dateCreated, String dateEdit) {
        super(id, name, number, dateCreated, dateEdit);
        this.surname = surname;
        this.birthDate = birthDate;
        this.gender = gender;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    @Override
    Contact build() {
        return new Person(id, name, surname, birthDate, gender, number, dateCreated, dateEdit);
    }

    @Override
    public String toString() {
        return "Name: " + name + "\n" +
                "Surname: " + surname + "\n" +
                "Birth date: " + birthDate + "\n" +
                "Gender: " + gender + "\n" +
                "Number: " + number + "\n" +
                "Time created: " + dateCreated + "\n" +
                "Time last edit: " + dateEdit;
    }
    public void record(Contact type) throws IOException {
        System.out.println();
        System.out.print("[record] Enter action (edit, delete, menu): ");
        String action = Main.input();
        if (action.equals("edit")){
            System.out.println();
            edit((Person) type);
        } else if (action.equals("delete")){
            System.out.println();
            Main.delete(type);
        } else if (action.equals("menu")){
            System.out.println();
            Main.mainMenu();
        }
    }
    public void edit(Person person) throws IOException {
        System.out.print("Select a field (name, surname, birth, gender, number): ");
        String select = Main.input();
        if ("name".equals(select)){
            System.out.print("Enter name: ");
            person.setName(Main.input());
        } else if ("surname".equals(select)){
            System.out.print("Enter surname: ");
            person.setSurname(Main.input());
        } else if ("birth".equals(select)){
            System.out.print("Enter birth date: ");
            String bD = Main.input();
            if (Main.checkDate(bD)){
                person.setBirthDate(bD);
            } else {
                System.out.println("Bad birth date!");
                person.setBirthDate("[no data]");
            }
        } else if ("gender".equals(select)){
            System.out.print("Enter the gender (M, F): ");
            String gender = Main.input();
            if (gender.equals("M") || gender.equals("m")) {
                gender = "M";
            } else if (gender.equals("F") || gender.equals("f")) {
                gender = "F";
            } else {
                System.out.println("Bad gender");
                gender = "[no data]";
            }
            person.setGender(gender);
        } else if ("number".equals(select)){
            System.out.print("Enter number: ");
            String number = Main.input();
            if (Main.checkNumber(number)){
                person.setNumber(number);
            } else {
                System.out.println("Wrong format number");
                person.setNumber(number);
            }
        }

        System.out.println("Saved");
        person.setDateEdit(Main.time());
        System.out.println(person.toString());
        record(person);
    }

    @Override
    String getFields() {
        String fields = getName() + " " + getSurname() + " " + getNumber();
        return fields;
    }
}