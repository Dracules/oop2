package sample;

import annotations.HierarchyAnnotation;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Text_Serializ extends Serializ_Deserializ {


    @Override
    public void Save(ArrayList<learner> arr) {
        try {
            FileWriter nFile = new FileWriter("TEST.txt");
            for (learner learn : arr) {
                Class LeanerClass = learn.getClass();
                Class LeanerSuperClass = LeanerClass.getSuperclass();

                nFile.write("{" + LeanerClass.getName() + "}");

                if (LeanerSuperClass != learner.class){
                    SaveFields(learn, LeanerSuperClass.getSuperclass(), nFile);
                }
                SaveFields(learn, LeanerSuperClass, nFile);
                SaveFields(learn, LeanerClass, nFile);
                nFile.write("\n");
            }
            nFile.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void SaveFields (Object ob,Class cl,FileWriter File) {
        try {
            Field[] ObFields = cl.getDeclaredFields();
            for (Field field : ObFields)
            {
                if (field.getAnnotation(HierarchyAnnotation.class).Type() == scientific.class)
                {
                    scientific Scientif = (scientific) field.get(ob);
                    Field[] ScintificFields = scientific.class.getDeclaredFields();
                    for (Field ScintField : ScintificFields)
                    {
                        File.write("{" + ScintField.get(Scientif).toString() + "}");
                    }
                }
                else {
                    File.write("{" + field.get(ob).toString() + "}");
                }
            }
        }
        catch (IllegalAccessException | IOException e)
        {
            e.printStackTrace();
        }
    }


    @Override
    public ArrayList<learner> Load(){
        ArrayList<learner> arr = new ArrayList<learner>();
        try {
            FileReader NFile = new FileReader("TEST.txt");
            Scanner scan = new Scanner(NFile);

            while (scan.hasNextLine())
            {
                String str = scan.nextLine();
                StringTokenizer tokenizer = new StringTokenizer(str, "{}");

                Class LeanerClass = Class.forName(tokenizer.nextToken());
                Object learner = LeanerClass.newInstance();

                Class LeanerSuperClass = LeanerClass.getSuperclass();
                if (LeanerSuperClass != learner.class)
                {
                    LoadFields(learner,LeanerSuperClass.getSuperclass() , tokenizer);
                }
                LoadFields(learner,LeanerSuperClass, tokenizer);
                LoadFields(learner,LeanerClass, tokenizer);

                arr.add((learner) learner);
            }
            NFile.close();
        }
        catch (InstantiationException | IllegalAccessException | ClassNotFoundException |IOException e)
        {
            e.printStackTrace();
        }
        return arr;
    }


    private void LoadFields (Object ob,Class cl, StringTokenizer tokenizer) {
        try {
            Field[] ObFields = cl.getDeclaredFields();
            for (Field field : ObFields) {
                if (field.getAnnotation(HierarchyAnnotation.class).Type() == scientific.class)
                {
                    scientific Scientif = new scientific();
                    Field[] ScintificFields = scientific.class.getDeclaredFields();
                    for (Field ScintField : ScintificFields)
                    {
                        ScintField.set(Scientif, tokenizer.nextToken());
                    }
                    field.set(ob, Scientif);
                }
                else {
                    if (field.getAnnotation(HierarchyAnnotation.class).Type() == int.class) {
                        field.set(ob, Integer.parseInt(tokenizer.nextToken()));
                    } else {
                        field.set(ob, tokenizer.nextToken());
                    }
                }
            }
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
    }


}
