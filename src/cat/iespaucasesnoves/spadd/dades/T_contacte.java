package cat.iespaucasesnoves.spadd.dades;

import org.postgresql.util.PGobject;

import java.sql.SQLException;

import static cat.iespaucasesnoves.spadd.auxiliars.EinesCadenes.parseValue;
import static cat.iespaucasesnoves.spadd.auxiliars.EinesCadenes.tanca;

public class T_contacte extends PGobject {

    private String telefon;
    private String email;
    private String twitter;

    public T_contacte() {
    }

    public T_contacte(String telefon, String email, String twitter) {
        this.telefon = telefon;
        this.email = email;
        this.twitter = twitter;
    }


    @Override
    public void setValue(String value) throws SQLException {
        super.setValue(value);
        String[] atributs= parseValue(value);
        //Assignam valors als atributs.
        telefon = atributs[0];
        email = atributs[1];
        twitter = atributs[2];
    }

    @Override
    public String toString() {
        return "T_contacte{" +
                "telefon='" + telefon + '\'' +
                ", email='" + email + '\'' +
                ", twitter='" + twitter + '\'' +
                '}';
    }

    @Override
    public String getValue() {
        String resultat = "("+ tanca(telefon) + ",";
        resultat += tanca(email) + ",";
        resultat += tanca(twitter) + ')';
        return resultat;

    }
}
