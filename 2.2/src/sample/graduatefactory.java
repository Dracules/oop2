package sample;

public class graduatefactory extends leanerfactory{
    public learner Create(){
        return new graduate();
    }
}
