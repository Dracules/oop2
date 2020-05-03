package sample;

public class studentfactory extends leanerfactory  {
    public learner Create(){
        return new Student();
    }
}
