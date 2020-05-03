package sample;

import annotations.HierarchyAnnotation;

import java.io.Serializable;

public class diplomnik extends Student implements Serializable {

    @HierarchyAnnotation(
            Label = "Тема диплома",
            Type = String.class
    )
    String diplomtheme;
    @HierarchyAnnotation(
            Label = "Научный руководитель",
            Type = scientific.class
    )
    scientific scientif;

    public scientific GetScientif() {
        return scientif;
    }

    public void SetScientif(scientific scientif) {
        this.scientif = scientif;
    }

    public String GetDiplomtheme() {
        return diplomtheme;
    }

    public void SetDiplomtheme(String diplomtheme) {
        this.diplomtheme = diplomtheme;
    }

    diplomnik(){
        super();
        diplomtheme = "diplomtheme";
        scientif = new scientific();
    }

    @Override
    String PrintInfo() {
        return ("Дипломник: Имя: "+GetName()+"; Место учебы: "+GetStudyplase()+
                "; Возраст: "+Integer.toString(GetAge())+"; Тема диплома: "+diplomtheme);
    }

}
