package cat.iespaucasesnoves.spadd;

import cat.iespaucasesnoves.spadd.auxiliars.OrdbException;
import cat.iespaucasesnoves.spadd.baseDades.BaseDades;
import cat.iespaucasesnoves.spadd.dades.Alumne;
import cat.iespaucasesnoves.spadd.dades.Assignatura;
import cat.iespaucasesnoves.spadd.dades.Cicle;
import cat.iespaucasesnoves.spadd.dades.Matricula;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ProvesJDBC {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("user", "usuari");
        properties.put("password", "seCret_19");
        BaseDades basedades = new BaseDades( "jdbc:postgresql://192.168.56.95:5432/basedades", properties );
        try {
            Cicle cicle = basedades.findCicle("DAM");
//            System.out.println("cicle = " + cicle);
            Assignatura assignatura = basedades.findAssignatura("SPAAD");
//            System.out.println("assignatura = " + assignatura);
            Alumne alumne = basedades.findAlumneNoContacte("78787878P");
            System.out.println("alumne = " + alumne);
            Matricula matricula = basedades.findMatricula(alumne.getNif(), assignatura.getCodi());
//            System.out.println("matricula = " + matricula);
            ArrayList<Alumne> alumnes = basedades.getAlumnes();
            for (Alumne single_alumne: alumnes) {
//                System.out.println(single_alumne);
            }
            ArrayList<Matricula> matriculas = basedades.findMatriculas(assignatura);
            for (Matricula single_matricula: matriculas) {
                System.out.println(single_matricula);
            }

        } catch (OrdbException e) {
            //System.out.println("Error = " + e.getMessage());
            e.printStackTrace();
        }


    }

}
