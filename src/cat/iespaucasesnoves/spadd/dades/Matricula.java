package cat.iespaucasesnoves.spadd.dades;

import java.util.Arrays;

import static java.util.Objects.isNull;

public class Matricula {

    private Alumne alumne;

    @Override
    public String toString() {
        return "Matricula{" +
                "alumne=" + alumne +
                ", assignatura=" + assignatura +
                ", notes=" + Arrays.toString(notes) +
                '}';
    }

    private Assignatura assignatura;
    private int[] notes;

    public Matricula(Alumne alumne, Assignatura assignatura, int[] notes) {
        if(!isNull(alumne)){
            this.alumne = alumne;
        }
        if(!isNull(assignatura)){
            this.assignatura = assignatura;
        }
        this.notes = notes;
    }
}
