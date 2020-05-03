package sample;

import java.io.*;
import java.util.ArrayList;

public class Binary_Serializ extends Serializ_Deserializ {
    public void Save(ArrayList<learner> arr) {
        try {
            FileOutputStream fos = new FileOutputStream("binary.out");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeInt(arr.size());
            for (int i = 0; i < arr.size(); i++) {
                oos.writeObject(arr.get(i));
            }
            oos.flush();
            oos.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public ArrayList<learner> Load(){
        ArrayList<learner> arr = new ArrayList<learner>();
        try {
            FileInputStream fis = new FileInputStream("binary.out");
            ObjectInputStream ois = new ObjectInputStream(fis);
            int N =ois.readInt();
            for (int i=0;i<N;i++) {
                arr.add(i, (learner) ois.readObject());
            }
            ois.close();
        }
        catch (ClassNotFoundException | IOException e)
        {
            e.printStackTrace();
        }
        return arr;
    }
}
