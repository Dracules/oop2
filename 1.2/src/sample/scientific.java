package sample;

import annotations.HierarchyAnnotation;

import java.io.Serializable;

public class scientific implements Serializable {

    @HierarchyAnnotation(
            Label = "Имя науч. рук.",
            Type = String.class
    )
    String nameScient;

    @HierarchyAnnotation(
            Label = "Кафедра науч. рук.",
            Type = String.class
    )
    String department;

    @HierarchyAnnotation(
            Label = "Научная степень рук.",
            Type = String.class
    )
    String degree;

    public String GetNameScient() {
        return nameScient;
    }

    public void SetNameScient(String name) {
        this.nameScient = name;
    }

    public String GetDepartment() {
        return department;
    }

    public void SetDepartment(String department) {
        this.department = department;
    }

    public String GetDegree() {
        return degree;
    }

    public void SetDegree(String degree) {
        this.degree = degree;
    }

    scientific () {
        nameScient = "nameScient";
        department = "department";
        degree = "degree";
    }

    scientific (String NameScient, String Department, String Degree) {
        nameScient = NameScient;
        department = Department;
        degree = Degree;
    }
}
