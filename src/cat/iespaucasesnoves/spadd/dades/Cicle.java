package cat.iespaucasesnoves.spadd.dades;

import static cat.iespaucasesnoves.spadd.baseDades.BaseDades.isNull;

public class Cicle {

    private String codi;

    @Override
    public String toString() {
        return "Cicle{" +
                "codi='" + codi + '\'' +
                ", nom='" + nom + '\'' +
                '}';
    }

    private String nom;

    public Cicle(String codi, String nom) {
        if (!isNull(codi)) {
            this.codi = codi;
        }
        this.nom = nom;
    }
}
