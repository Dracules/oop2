package sample;

public class diplomnikfactory extends studentfactory {
    public learner Create(){
        return new diplomnik();
    }
}
