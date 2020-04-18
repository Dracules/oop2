package sample;

import annotations.HierarchyAnnotation;

import java.io.Serializable;

public class graduate extends learner implements Serializable {

    @HierarchyAnnotation(
            Label = "Тема диссертации",
            Type = String.class
    )
    String diserttheme;

    @HierarchyAnnotation(
            Label = "Научный руководитель",
            Type = scientific.class
    )
    scientific scientif;

    public String GetDiserttheme() {
        return diserttheme;
    }

    public void SetScientif(scientific scientif) {
        this.scientif = scientif;
    }

    public void SetDiserttheme(String diserttheme) {
        this.diserttheme = diserttheme;
    }

    public scientific GetScientif() {
        return scientif;
    }


    graduate () {
        super();
        diserttheme = "diserttheme";
        scientif = new scientific();
    }

    graduate (String Name, String Studyplace, int Age, String Diserttheme, String ScintName, String Departm, String Degree) {
        super(Name, Studyplace, Age);
        diserttheme = Diserttheme;
        scientif = new scientific(ScintName, Departm, Degree);
    }

    @Override
    String PrintInfo() {
        return ("Аспирант: Имя: "+GetName()+"; Место учебы: "+GetStudyplase()+
                "; Возраст: "+Integer.toString(GetAge())+"; Тема диссертации: "+diserttheme);
    }

}
