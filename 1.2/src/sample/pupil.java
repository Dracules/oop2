package sample;

import annotations.HierarchyAnnotation;

import java.io.Serializable;

public class pupil extends learner implements Serializable {

    @HierarchyAnnotation(
            Label = "Класс",
            Type = int.class
    )
    int Classnumber;

    @HierarchyAnnotation(
            Label = "Профиль",
            Type = String.class
    )
    String profile;

    public int GetClassnumber() {
        return Classnumber;
    }

    public void SetClassnumber(int classnumber) {
        Classnumber = classnumber;
    }

    public String GetProfile() {
        return profile;
    }

    public void SetProfile(String profile) {
        this.profile = profile;
    }

    pupil () {
        super();
        Classnumber = 0;
        profile = "profile";
    }

    pupil (String Name, String studyplace, int age, int classnumber, String prof) {
        super(Name, studyplace, age);
        Classnumber = classnumber;
        profile = prof;
    }

    @Override
    String PrintInfo() {
        return ("Ученик: Имя: "+GetName()+"; Место учебы: "+GetStudyplase()+
                "; Возраст: "+Integer.toString(GetAge())+"; Класс: "+Integer.toString(Classnumber));
    }

}


