package sample;

import annotations.HierarchyAnnotation;

import java.io.Serializable;

public class Student extends learner implements Serializable {

    @HierarchyAnnotation(
            Label = "Факультет",
            Type = String.class
    )
    String faculty;

    @HierarchyAnnotation(
            Label = "Специальность",
            Type = String.class
    )
    String speciality;

    @HierarchyAnnotation(
            Label = "Номер группы",
            Type = int.class
    )
    int groopnumber;


    public String GetFaculty() {
        return faculty;
    }

    public void SetFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String GetSpeciality() {
        return speciality;
    }

    public void SetSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public int GetGroopnumber() {
        return groopnumber;
    }

    public void SetGroopnumber(int groopnumber) {
        this.groopnumber = groopnumber;
    }

    Student (){
        super();
        faculty = "faculty";
        speciality = "speciality";
        groopnumber = 0;
    };

    Student (String Name, String StudPlace, int age, String facult, String specialit, int groop){
        super(Name, StudPlace, age);
        faculty = facult;
        speciality = specialit;
        groopnumber = groop;
    };

    @Override
    String PrintInfo() {
        return ("Студент: Имя: "+GetName()+"; Место учебы: "+GetStudyplase()+
                "; Возраст: "+Integer.toString(GetAge())+"; Факультет: "+GetFaculty()+
                "; Номер группы: "+Integer.toString(groopnumber));
    }

}


