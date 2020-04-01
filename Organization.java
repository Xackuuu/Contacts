package contacts;

import java.io.IOException;
import java.io.Serializable;

public class Organization extends Contact implements Serializable{
    String address;

    private static final long serialVersionUID = 7L;

    public void edit(Organization organization) throws IOException {
        System.out.print("Select a field (name, address, number): ");
        String select = Main.input();
        if ("name".equals(select)){
            System.out.print("Enter name: ");
            organization.setName(Main.input());
        } else if ("address".equals(select)){
            System.out.print("Enter address: ");
            organization.setAddress(Main.input());
        } else if ("number".equals(select)){
            System.out.print("Enter number: ");
            String number = Main.input();
            if (Main.checkNumber(number)){
                organization.setNumber(number);
            } else {
                System.out.println("Wrong format number");
                organization.setNumber("[no data]");
            }
        }

        System.out.println("Saved");
        organization.setDateEdit(Main.time());
        System.out.println(organization.toString());
        record(organization);
    }
    public Organization(int id, String name, String address, String number, String dateCreated, String dateEdit) {
        super(id, name, number, dateCreated, dateEdit);
        this.address = address;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    Contact build() {
        return new Organization(id, name, address, number, dateCreated, dateEdit);
    }
    public void record(Contact type) throws IOException {
        System.out.println();
        System.out.print("[record] Enter action (edit, delete, menu): ");
        String action = Main.input();
        if (action.equals("edit")){
            System.out.println();
            edit((Organization) type);
        } else if (action.equals("delete")){
            Main.delete(type);
        }
        System.out.println();
        Main.mainMenu();
    }
    @Override
    public String toString() {
        return "Organization name: " + getName() +"\n" +
                "Address: " + getAddress() + "\n" +
                "Number: "+ getNumber() + "\n" +
                "Time created: "+ getDateCreated() +"\n" +
                "Time last edit: " + getDateEdit();
    }
    @Override
    public String getFields() {
        String fields = getName() + " " + getNumber() + " " + getAddress();
        return fields;
    }
}
