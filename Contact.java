package contacts;

import java.io.IOException;
import java.io.Serializable;

public class Contact implements Serializable {
    int id;
    String number;
    String name;
    String dateCreated;
    String dateEdit;

    private static final long serialVersionUID = 7L;
    public Contact( int id, String name, String number, String dateCreated, String dateEdit) {
        this.id = id;
        this.number = number;
        this.name = name;
        this.dateCreated = dateCreated;
        this.dateEdit = dateEdit;
    }
    public String getNumber() {
        return number;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDateCreated() {
        return dateCreated;
    }
    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }
    public String getDateEdit() {
        return dateEdit;
    }
    public void setDateEdit(String dateEdit) {
        this.dateEdit = dateEdit;
    }
    Contact build(){
        return new Contact(id, name, number, dateCreated, dateEdit);
    }
    void record(Contact type) throws IOException {
    }
    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", dateCreated='" + dateCreated + '\'' +
                ", dateEdit='" + dateEdit + '\'' +
                '}';
    }
    String getFields(){
        return null;
    }
}
