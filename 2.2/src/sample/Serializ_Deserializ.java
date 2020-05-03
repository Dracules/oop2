package sample;

import java.util.ArrayList;

abstract class Serializ_Deserializ {
    abstract public void Save(ArrayList<learner> arr);
    abstract public ArrayList<learner> Load();
}
