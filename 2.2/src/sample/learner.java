package sample;

import annotations.HierarchyAnnotation;

import java.io.Serializable;

abstract class learner implements Serializable {

    @HierarchyAnnotation(
        Label = "ФИО",
        Type = String.class
    )
    String Name;

    @HierarchyAnnotation(
            Label = "Место учебы",
            Type = String.class
    )
    String Studyplase;

    @HierarchyAnnotation(
            Label = "Возраст",
            Type = int.class
    )
    int Age;

    abstract String PrintInfo();

    learner () {
        Name = "Name";
        Studyplase = "Studyplase";
        Age = 0;
    };

    learner (String name, String studyplase, int age) {
        Name = name;
        Studyplase = studyplase;
        Age = age;
    };

    public String GetStudyplase() {
        return Studyplase;
    }

    public void SetStudyplase(String studyplase) {
        Studyplase = studyplase;
    }

    public int GetAge() {
        return Age;
    }

    public void SetAge(int age) {
        Age = age;
    }

    public String GetName() {
        return Name;
    }

    public void SetName(String name) {
        Name = name;
    }

}
