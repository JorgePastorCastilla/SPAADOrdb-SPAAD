package cat.iespaucasesnoves.spadd.dades;

import static cat.iespaucasesnoves.spadd.baseDades.BaseDades.isNull;

public class Assignatura {

    private String codi;
    private String nom;
    private Cicle cicle;

    @Override
    public String toString() {
        return "Assignatura{" +
                "codi='" + codi + '\'' +
                ", nom='" + nom + '\'' +
                ", cicle=" + cicle +
                '}';
    }

    public Assignatura(String codi, String nom, Cicle cicle) {
        if (!isNull(codi)) {
            this.codi = codi;
        }
        this.nom = nom;
        this.cicle = cicle;
    }
}
