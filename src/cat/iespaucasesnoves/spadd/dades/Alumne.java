package cat.iespaucasesnoves.spadd.dades;

import static java.util.Objects.isNull;

public class Alumne {

private String nif;
private String nom;

    @Override
    public String toString() {
        return "Alumne{" +
                "nif='" + nif + '\'' +
                ", nom='" + nom + '\'' +
                ", llinatges='" + llinatges + '\'' +
                ", contacte=" + contacte +
                '}';
    }

    private String llinatges;
private T_contacte contacte;

    public Alumne(String nif, String nom, String llinatges, T_contacte contacte) {
        if(!isNull(nif)){
            this.nif = nif;
        }
        this.nom = nom;
        this.llinatges = llinatges;
        this.contacte = contacte;
    }
}
