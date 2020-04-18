package sample;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class JSON_Serializ extends Serializ_Deserializ {

    public void Save(ArrayList<learner> arr) {
        try {

            FileWriter nFile = new FileWriter("JSON.txt");

            Gson gson = new Gson();
            String json;
            int N = arr.size();

            for (int i = 0; i < N; i++) {
                nFile.write(arr.get(i).getClass().toString() + "\n");
                json = gson.toJson(arr.get(i));
                nFile.write(json + "\n");
            }
            nFile.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void Load(ArrayList<learner> arr) {
        arr.removeAll(arr);

        try {

            FileReader nFile = new FileReader("JSON.txt");
            Scanner scan = new Scanner(nFile);

            Gson gson = new Gson();

            while (scan.hasNextLine()) {
                String ClassName = scan.nextLine();
                String Info = scan.nextLine();
                switch (ClassName)
                {
                    case "class sample.pupil":
                    {
                        arr.add(gson.fromJson(Info,pupil.class));
                        break;
                    }
                    case "class sample.Student":
                    {
                        arr.add(gson.fromJson(Info,Student.class));
                        break;
                    }
                    case "class sample.graduate":
                    {
                        arr.add(gson.fromJson(Info,graduate.class));
                        break;
                    }
                    case "class sample.diplomnik":
                    {
                        arr.add(gson.fromJson(Info,diplomnik.class));
                        break;
                    }
                }
            }
            nFile.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
