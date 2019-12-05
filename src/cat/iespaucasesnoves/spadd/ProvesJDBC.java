package cat.iespaucasesnoves.spadd;

import cat.iespaucasesnoves.spadd.auxiliars.OrdbException;
import cat.iespaucasesnoves.spadd.baseDades.BaseDades;
import cat.iespaucasesnoves.spadd.dades.Alumne;
import cat.iespaucasesnoves.spadd.dades.Assignatura;
import cat.iespaucasesnoves.spadd.dades.Cicle;

import java.sql.SQLException;
import java.util.Properties;

public class ProvesJDBC {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("user", "usuari");
        properties.put("password", "seCret_19");
        BaseDades basedades = new BaseDades( "jdbc:postgresql://192.168.56.95:5432/basedades", properties );
        try {
            Cicle cicle = basedades.findCicle("DAM");
            System.out.println("cicle = " + cicle);
            Alumne assignatura = basedades.findAlumneNoContacte("SPAAD");
            System.out.println("assignatura = " + assignatura);

        } catch (OrdbException e) {
            //System.out.println("Error = " + e.getMessage());
            e.printStackTrace();
        }


    }

}
