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

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLlinatges() {
        return llinatges;
    }

    public void setLlinatges(String llinatges) {
        this.llinatges = llinatges;
    }

    public T_contacte getContacte() {
        return contacte;
    }

    public void setContacte(T_contacte contacte) {
        this.contacte = contacte;
    }

    public Alumne(String nif, String nom, String llinatges, T_contacte contacte) {
        if(!isNull(nif)){
            this.nif = nif;
        }
        this.nom = nom;
        this.llinatges = llinatges;
        this.contacte = contacte;
    }
}
