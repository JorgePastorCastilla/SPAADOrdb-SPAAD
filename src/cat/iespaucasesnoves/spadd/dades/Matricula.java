package cat.iespaucasesnoves.spadd.dades;

import java.util.Arrays;

import static java.util.Objects.isNull;

public class Matricula {



    @Override
    public String toString() {
        return "Matricula{" +
                "alumne=" + alumne +
                ", assignatura=" + assignatura +
                ", notes=" + Arrays.toString(notes) +
                '}';
    }
    private Alumne alumne;
    private Assignatura assignatura;
    private Integer[] notes;

    public Matricula(Alumne alumne, Assignatura assignatura, Integer[] notes) {
        if(!isNull(alumne)){
            this.alumne = alumne;
        }
        if(!isNull(assignatura)){
            this.assignatura = assignatura;
        }
        this.notes = notes;
    }
}
