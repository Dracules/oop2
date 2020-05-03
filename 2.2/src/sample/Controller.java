package sample;

import annotations.HierarchyAnnotation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import javax.swing.*;
import java.awt.*;
import java.lang.*;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class Controller {
    @FXML
    private Button CorrButton,AddBut,DeleteBut,OkBut,SaveBut,LoadBut;
    @FXML
    private ComboBox learnbox;
    @FXML
    private Label Label1,Label2,Label3,Label4,Label5,Label6,Label7,Label8,Label9,Label10,
            InfoLabel1,InfoLabel2,InfoLabel3,InfoLabel4,InfoLabel5,InfoLabel6,InfoLabel7,InfoLabel8,InfoLabel9,InfoLabel10,
            NameInfoLabel1,NameInfoLabel2,NameInfoLabel3,NameInfoLabel4,NameInfoLabel5,
            NameInfoLabel6,NameInfoLabel7,NameInfoLabel8,NameInfoLabel9,NameInfoLabel10;
    @FXML
    private TextField Area1,Area2,Area3,Area4,Area5,Area6,Area7,Area8,Area9,Area10;
    @FXML
    private ListView<String> learnerListView;

    @FXML
    private Label[] labels, Infolabels,NameInfolabels;
    @FXML
    private TextField[] fields;

    public ArrayList<learner> learners;
    leanerfactory[] factor;
    Serializ_Deserializ[] Serializ;
    enum ActionType {ADD,CORRECT};
    ActionType ActTp;
    learner SelectedLeaner;
    int SelectedIndex;

    ObservableList<String> typeslearner = FXCollections.observableArrayList("Ученик","Студент","Аспирант","Дипломник");
    Object[] SerializeTypes = {"Бинарная", "JSON", "Текстовая"};
    enum SerialType {BINARY,JSON,TEXT};
    SerialType SerTp;

    public Controller () {
        factor = new leanerfactory[4];
        factor[0] = new pupilfactory();
        factor[1] = new studentfactory();
        factor[2] = new graduatefactory();
        factor[3] = new diplomnikfactory();

        Serializ = new Serializ_Deserializ[3];
        Serializ[0] = new Binary_Serializ();
        Serializ[1] = new JSON_Serializ();
        Serializ[2] = new Text_Serializ();

        learners = new ArrayList<learner>();
    }

    public void initialize()
    {
        learnbox.setValue("Ученик");
        learnbox.setItems(typeslearner);

        labels= new Label[10];
        labels[0] = Label1;
        labels[1] = Label2;
        labels[2] = Label3;
        labels[3] = Label4;
        labels[4] = Label5;
        labels[5] = Label6;
        labels[6] = Label7;
        labels[7] = Label8;
        labels[8] = Label9;
        labels[9] = Label10;

        Infolabels= new Label[10];
        Infolabels[0] = InfoLabel1;
        Infolabels[1] = InfoLabel2;
        Infolabels[2] = InfoLabel3;
        Infolabels[3] = InfoLabel4;
        Infolabels[4] = InfoLabel5;
        Infolabels[5] = InfoLabel6;
        Infolabels[6] = InfoLabel7;
        Infolabels[7] = InfoLabel8;
        Infolabels[8] = InfoLabel9;
        Infolabels[9] = InfoLabel10;

        NameInfolabels= new Label[10];
        NameInfolabels[0] = NameInfoLabel1;
        NameInfolabels[1] = NameInfoLabel2;
        NameInfolabels[2] = NameInfoLabel3;
        NameInfolabels[3] = NameInfoLabel4;
        NameInfolabels[4] = NameInfoLabel5;
        NameInfolabels[5] = NameInfoLabel6;
        NameInfolabels[6] = NameInfoLabel7;
        NameInfolabels[7] = NameInfoLabel8;
        NameInfolabels[8] = NameInfoLabel9;
        NameInfolabels[9] = NameInfoLabel10;

        fields = new TextField[10];
        fields[0]= Area1;
        fields[1]= Area2;
        fields[2]= Area3;
        fields[3]= Area4;
        fields[4]= Area5;
        fields[5]= Area6;
        fields[6]= Area7;
        fields[7]= Area8;
        fields[8]= Area9;
        fields[9]= Area10;
    }

    private void ClearFields(){
        for (TextField field : fields)
        {
            field.setVisible(false);
            field.clear();
        }
    }

    private void ClearLabels(Label[] LabelArr){
        for (Label label : LabelArr)
        {
            label.setVisible(false);
        }
    }

    private void ShowTab() {
        learnerListView.getItems().clear();
        int N =learners.size();
        for (int i=0; i<N;i++)
        {
            learnerListView.getItems().add(i,learners.get(i).PrintInfo());
        }
    }

    private void Check(TextField TField) {
        String str =TField.getText();
        str = str.replaceAll("[^0-9]", "");
        TField.setText(str);
    }

    private String StringEmpty(String str) {
        if (str.equals(""))
        {
            return "-";
        }
        return str;
    }

    private String IntEmpty(String str) {
        if (str.equals(""))
        {
            return "0";
        }
        return str;
    }

    private void OKSetInfo() {
        int FieldIndex = 0;
        Class LeanerClass =SelectedLeaner.getClass();
        Class LeanerSuperClass = LeanerClass.getSuperclass();
        if (LeanerSuperClass != learner.class)
        {
            FieldIndex = SetFields(SelectedLeaner,LeanerSuperClass.getSuperclass(),FieldIndex);
        }
        FieldIndex=SetFields(SelectedLeaner,LeanerSuperClass,FieldIndex);
        SetFields(SelectedLeaner,LeanerClass,FieldIndex);
    }

    private int SetFields (Object ob,Class cl, int FieldIndex) {
        try {
            Field[] ObFields = cl.getDeclaredFields();
            for (Field field : ObFields) {
                if (field.getAnnotation(HierarchyAnnotation.class).Type() == scientific.class)
                {
                    scientific Scientif = new scientific();
                    Field[] ScintificFields = scientific.class.getDeclaredFields();
                    for (Field ScintField : ScintificFields)
                    {
                        fields[FieldIndex].setText(StringEmpty(fields[FieldIndex].getText()));
                        ScintField.set(Scientif, fields[FieldIndex].getText());
                        FieldIndex++;
                    }
                    field.set(ob, Scientif);
                }
                else {
                    if (field.getAnnotation(HierarchyAnnotation.class).Type() == int.class) {
                        Check(fields[FieldIndex]);
                        fields[FieldIndex].setText(IntEmpty(fields[FieldIndex].getText()));
                        field.set(ob, Integer.parseInt(fields[FieldIndex].getText()));
                    } else {
                        fields[FieldIndex].setText(StringEmpty(fields[FieldIndex].getText()));
                        field.set(ob, fields[FieldIndex].getText());
                    }
                    FieldIndex++;
                }
            }
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
        return FieldIndex;
    }

    private boolean Choise(boolean type) {
        String str;
        Frame frame = new Frame();
        if (type) {
            LoadBut.setDisable(true);
            str = (String) JOptionPane.showInputDialog(frame, "Тип сериализации:", "Выбор",
                    JOptionPane.INFORMATION_MESSAGE, null, SerializeTypes, "Бинарная");
        }
        else {
            SaveBut.setDisable(true);
            str = (String) JOptionPane.showInputDialog(frame, "Тип десериализации:", "Выбор",
                    JOptionPane.INFORMATION_MESSAGE, null, SerializeTypes, "Бинарная");
        }
        if (str != null) {
            if (str.equals("Бинарная")) {
                SerTp = SerialType.BINARY;
            } else if (str.equals("JSON")) {
                SerTp = SerialType.JSON;
            } else {
                SerTp = SerialType.TEXT;
            }
            return true;
        }
        else
        {
            return false;
        }
    }


    @FXML
    private void Add(ActionEvent event) {
        String s = learner.class.getName();
        int index = learnbox.getSelectionModel().getSelectedIndex();
        SelectedLeaner = factor[index].Create();
        SelectedIndex = learners.size();
        ActTp = ActionType.ADD;
        AddBut.setDisable(true);
        DeleteBut.setDisable(true);
        CorrButton.setDisable(true);
        OkBut.setDisable(false);
        SaveBut.setDisable(true);
        LoadBut.setDisable(true);

        int LabelIndex = 0;
        Class LeanerClass =SelectedLeaner.getClass();
        Class LeanerSuperClass = LeanerClass.getSuperclass();
        if (LeanerSuperClass != learner.class)
        {
            LabelIndex = GetFieldsName(LeanerSuperClass.getSuperclass(),LabelIndex);
        }
        LabelIndex=GetFieldsName(LeanerSuperClass,LabelIndex);
        GetFieldsName(LeanerClass,LabelIndex);
    }

    private int GetFieldsName (Class cl, int LabelIndex)
    {
        Field[] ObFields = cl.getDeclaredFields();
        for (Field field : ObFields)
        {
            if (field.getAnnotation(HierarchyAnnotation.class).Type() == scientific.class)
            {
                Field[] ScintificFields = scientific.class.getDeclaredFields();
                for (Field ScintField : ScintificFields)
                {
                    GetOneFieldName(LabelIndex,ScintField);
                    LabelIndex++;
                }
            }
            else {
                GetOneFieldName(LabelIndex,field);
                LabelIndex++;
            }
        }
        return LabelIndex;
    }

    private void GetOneFieldName(int LabelIndex, Field field){
        labels[LabelIndex].setText(field.getAnnotation(HierarchyAnnotation.class).Label());
        labels[LabelIndex].setVisible(true);
        fields[LabelIndex].setVisible(true);
    }

    @FXML
    private void Delete(ActionEvent event) {
        int index = learnerListView.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            learners.remove(index);
            ShowTab();
        }
    }

    @FXML
    private void Save(ActionEvent event) {
        if (Choise(true)) {
            Serializ[SerTp.ordinal()].Save(learners);
        }
        SaveBut.setDisable(false);
        LoadBut.setDisable(false);
    }

    @FXML
    private void Load(ActionEvent event) {
        if (Choise(false)) {
            learners.removeAll(learners);
            learners.addAll(Serializ[SerTp.ordinal()].Load());
            ShowTab();
        }
        SaveBut.setDisable(false);
        LoadBut.setDisable(false);
    }

    @FXML
    private void Correct(ActionEvent event) {

        int index = learnerListView.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            SelectedIndex = index;
            SelectedLeaner = learners.get(index);
            ActTp = ActionType.CORRECT;

            int i = 0;
            Class LeanerClass =SelectedLeaner.getClass();
            Class LeanerSuperClass = LeanerClass.getSuperclass();
            if (LeanerSuperClass != learner.class)
            {
                i = GetFieldsInfo(SelectedLeaner,LeanerSuperClass.getSuperclass() ,i);
            }
            i=GetFieldsInfo(SelectedLeaner,LeanerSuperClass,i);
            GetFieldsInfo(SelectedLeaner,LeanerClass,i);

            AddBut.setDisable(true);
            DeleteBut.setDisable(true);
            CorrButton.setDisable(true);
            OkBut.setDisable(false);
            SaveBut.setDisable(true);
            LoadBut.setDisable(true);
        }
    }

    private int GetFieldsInfo (Object ob, Class cl, int i)
    {
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
                    GetOneFieldInfo(i,ScintField,Scientif);
                    i++;
                }
            }
            else {
                GetOneFieldInfo(i,field,ob);
                i++;
            }
        }
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
        return i;
    }

    private void GetOneFieldInfo(int i, Field field, Object ob) throws IllegalAccessException {
        labels[i].setText(field.getAnnotation(HierarchyAnnotation.class).Label());
        labels[i].setVisible(true);
        fields[i].setText(field.get(ob).toString());
        fields[i].setVisible(true);
    }

    @FXML
    private void OKClick(ActionEvent event) {
        switch (ActTp)
        {
            case ADD:
            {
                OKSetInfo();
                learnerListView.getItems().add(learners.size(),SelectedLeaner.PrintInfo());
                learners.add(SelectedLeaner);
                break;
            }
            case CORRECT:
            {
                OKSetInfo();
                learners.set(SelectedIndex,SelectedLeaner);
                ShowTab();
                break;
            }
        }
        ClearFields();
        ClearLabels(labels);
        AddBut.setDisable(false);
        DeleteBut.setDisable(false);
        CorrButton.setDisable(false);
        OkBut.setDisable(true);
        SaveBut.setDisable(false);
        LoadBut.setDisable(false);
    }

    @FXML
    private void SelectedInfo(MouseEvent event) {
        ClearLabels(Infolabels);
        ClearLabels(NameInfolabels);
        int index = learnerListView.getSelectionModel().getSelectedIndex();
        if (index >=0) {
            learner Sellearner = learners.get(index);
            int LabelIndex=0;
            Class LeanerClass =Sellearner.getClass();
            Class LeanerSuperClass = LeanerClass.getSuperclass();
            if (LeanerSuperClass != learner.class)
            {
                LabelIndex = ShowLeanerInfo(Sellearner,LeanerSuperClass.getSuperclass() ,LabelIndex);
            }
            LabelIndex=ShowLeanerInfo(Sellearner,LeanerSuperClass,LabelIndex);
            ShowLeanerInfo(Sellearner,LeanerClass,LabelIndex);
        }
    }

        private int ShowLeanerInfo (Object ob, Class cl, int LabelIndex)
        {
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
                            FieldInfo(LabelIndex,ScintField,Scientif);
                            LabelIndex++;
                        }
                    }
                    else {
                        FieldInfo(LabelIndex,field,ob);
                        LabelIndex++;
                    }
                }
            }
            catch (IllegalAccessException e)
            {
                e.printStackTrace();
            }
            return LabelIndex;
        }


        private void FieldInfo(int LabelIndex, Field field, Object object) throws IllegalAccessException {
            NameInfolabels[LabelIndex].setText(field.getAnnotation(HierarchyAnnotation.class).Label());
            NameInfolabels[LabelIndex].setVisible(true);
            Infolabels[LabelIndex].setText(field.get(object).toString());
            Infolabels[LabelIndex].setVisible(true);
        }

}
