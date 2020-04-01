package contacts;
import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main implements SerializationUtils{
    static List<Contact> myContact = new ArrayList<>();
    static String fileName;
    static int counter;
    private static final long serialVersionUID = 7L;

    static String input() {
        Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            return input;
    }
    static void add() throws IOException {
        System.out.println();
        System.out.print("Enter the type (person, organization): ");
        String type = input().toLowerCase();
        if (type.equals("person")) {
            addPerson();
        } else if (type.equals("organization")) {
            addOrganization();
        }
    }
    static void mainMenu() throws IOException {
        System.out.print("[menu] Enter action (add, list, search, count, exit): ");
        String input = input().toLowerCase();
        if ("add".equals(input)){
            add();
            return;
        } else if ("list".equals(input)){
            list();
            return;
        } else if ("search".equals(input)){
            search();
            return;
        } else if ("count".equals(input)){
            count();
            return;
        } else if ("exit".equals(input)){
            System.exit(0);
        }

    }
    static void search() throws IOException {
        Map<Integer, Contact> contactMap = new LinkedHashMap<>();
        Contact contact = null;
        System.out.print("Enter search query: ");
        Pattern pattern = Pattern.compile(input(), Pattern.CASE_INSENSITIVE);
        int c = 0;
        for (Contact xxx : myContact) {
            contact = xxx;
            String search = "";
            if (contact.getClass() == Organization.class){
                search = contact.getFields();
            } else if (contact.getClass() == Person.class){
                search = contact.getFields();
            }
            Matcher matcher = pattern.matcher(search);
            if (matcher.find()) {
                c++;
                if (xxx.getClass() == Organization.class) {
                    contactMap.put(c, contact);
                } else if (xxx.getClass() == Person.class) {
                    contactMap.put(c, contact);
                }
            }
        }
        for (Map.Entry<Integer, Contact> ddd : contactMap.entrySet()) {
            contact = ddd.getValue();
            if (contact.getClass() == Organization.class){
                System.out.println(ddd.getKey() + ". " + ddd.getValue().getName());
            }else if (contact.getClass() == Person.class){
                System.out.println(ddd.getKey() + ". " + contact.getName() + " " + ((Person) contact).getSurname());
            }
        }
        System.out.println(String.format("Found %d result:", contactMap.size()));
        System.out.print("[search] Enter action ([number], back, again): ");
        String select = input();
        if (select.equals("back")){
            mainMenu();
        } else if (select.equals("again")){
            search();
        }
        System.out.println(contactMap.get(Integer.parseInt(select)).toString());
        contact.record(contact);

    }
    static boolean checkNumber(String input) {
        Pattern pattern = Pattern.compile("^[+]?([0-9]{1,3})*(-|\\s)*([(]?[\\w]{2,}[)]?)*(\\s|-)*((\\w){2,})*(\\s|-)*([\\w]{2,})*(\\s|-)*((\\w){2,})*");
        Matcher matcher = pattern.matcher(input);
        boolean b = matcher.matches();
        return b;
    }
    static boolean checkDate(String input) {
        Pattern pattern = Pattern.compile("[[0-9]{4,4}]-?[0-9]{2,2}-?[0-9]{2,2}");
        Matcher matcher = pattern.matcher(input);
        boolean b = matcher.matches();
        return b;
    }
    static String time() {
        LocalDateTime dateTime = LocalDateTime.now();
        int year = dateTime.getYear();
        int month = dateTime.getMonthValue();
        int day = dateTime.getDayOfMonth();
        int hour = dateTime.getHour();
        int min = dateTime.getMinute();
        LocalDateTime now = LocalDateTime.of(year, month, day, hour, min);
        return now + "";
    }
    static void addOrganization() throws IOException {
        String dateCreated = time();
        String dateEdit = dateCreated;
        System.out.print("Enter the organization name: ");
        String name = input();
        System.out.print("Enter the address: ");
        String address = input();
        System.out.print("Enter the number: ");
        String number = input();
        if (!checkNumber(number)) {
            System.out.println("Wrong number format!");
            number = "[no data]";
        }
        myContact.add(new Organization(counter, name, address, number, dateCreated, dateEdit));
        System.out.println("The record added.");
        System.out.println("");
        mainMenu();
    }
    static void addPerson() throws IOException {
        Contact contact = null;
        String dateCreated = time();
        String dateEdit = dateCreated;
        System.out.print("Enter the name: ");
        String name = input();
        System.out.print("Enter the surname: ");
        String surname = input();
        System.out.print("Enter the birth date: ");
        String birthDate = input();
        if (!checkDate(birthDate)) {
            System.out.println("Bad birth date!");
            birthDate = "[no data]";
        }
        System.out.print("Enter the gender (M, F): ");
        String gender = input();
        if (gender.equals("M") || gender.equals("m")) {
            gender = "M";
        } else if (gender.equals("F") || gender.equals("f")) {
            gender = "F";
        } else {
            System.out.println("Bad gender");
            gender = "[no data]";
        }
        System.out.print("Enter the number: ");
        String number = input();
        if (!checkNumber(number)) {
            System.out.println("Wrong number format!");
            number = "[no data]";
        }
        contact = new Person(counter, name, surname, birthDate, gender, number, dateCreated, dateEdit);
        myContact.add(contact);
        System.out.println("The record added.");
        System.out.println("");
        mainMenu();
    }
    static void delete(Contact type) throws IOException {
        if (myContact.size() == 0) {
            System.out.println("No records to remove!");
        } else {
            myContact.remove(type);
        }
        mainMenu();
    }
    static void count() throws IOException {
        System.out.println(String.format("The Phone Book has %d records.", myContact.size()));
        System.out.println();
        mainMenu();
    }
    static void list() throws IOException {
        Map<Integer, Contact> contactMap = new LinkedHashMap<>();
        Contact contact = null;
        if (myContact.size() == 0) {
            System.out.println("No records to remove!");
        } else if (myContact.size() > 0) {
            int c = 0;
            for (Contact x : myContact) {
                c++;
                if (x.getClass() == Organization.class) {
                    contactMap.put(c, x);
                } else if (x.getClass() == Person.class) {
                    contactMap.put(c, x);
                }
            }
            for (Map.Entry<Integer, Contact> ddd : contactMap.entrySet()) {
                contact = ddd.getValue();
                if (contact.getClass() == Organization.class){
                    System.out.println(ddd.getKey() + ". " + ddd.getValue().getName());
                }else if (contact.getClass() == Person.class){
                    System.out.println(ddd.getKey() + ". " + contact.getName() + " " + ((Person) contact).getSurname());
                }
            }
            }
        System.out.println();
        System.out.print("[list] Enter action ([number], back): ");
        String select = input();
        if (select.equals("back")){
            mainMenu();
            return;
        } else if (Integer.parseInt(select) > 0){
            System.out.println(contactMap.get(Integer.parseInt(select)).toString());
            contact.record(contact);
            return;
        }
    }
    static void adds(List<Contact> newList){
        for (Contact xxx : newList) {
            myContact.add(xxx);
        }
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        try {
            if (args.length >= 1){
                fileName = args[0];
                adds(SerializationUtils.deserialize(fileName));
            }
        } catch (FileNotFoundException e){
            try (OutputStream outputStream = new FileOutputStream("Phonebook.txt", true)) {
                fileName = "Phonebook.txt";
            } catch(IOException d) {
                System.out.println("Something went wrong");
            }
        }
        mainMenu();
        SerializationUtils.serialize(myContact, fileName);
    }
    }
