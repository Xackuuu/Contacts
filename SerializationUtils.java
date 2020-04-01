package contacts;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

interface SerializationUtils {
    /**
     * Serialize the given object to the file
     */
    static void serialize(List<Contact> myContact, String fileName) throws IOException, IOException {
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            for (Contact xxx : myContact) {
                oos.writeObject(xxx);
            }
            oos.close();
        } catch (NullPointerException e){

        }
    }

    /**
     * Deserialize to an object from the file
     */
    static List<Contact> deserialize(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(fileName);
        BufferedInputStream bis = new BufferedInputStream(fis);
        ObjectInputStream ois = new ObjectInputStream(bis);
        List<Contact> newList = new ArrayList<>();
        int a = 0;
        while (true) {
            try {
                newList.add((Contact) ois.readObject());
            } catch (EOFException e) {
                break;
            }
        }

        fis.close();
        return newList;
    }
}
