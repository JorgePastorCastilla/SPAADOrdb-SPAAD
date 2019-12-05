package cat.iespaucasesnoves.spadd.baseDades;

import cat.iespaucasesnoves.spadd.auxiliars.OrdbException;
import cat.iespaucasesnoves.spadd.dades.Alumne;
import cat.iespaucasesnoves.spadd.dades.Assignatura;
import cat.iespaucasesnoves.spadd.dades.Cicle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class BaseDades {

    String connectionURL;
    Properties properties;

    public BaseDades(String connectionURL, Properties properties) {
        this.connectionURL = connectionURL;
        this.properties = properties;
    }

    public String getConnectionURL() {
        return connectionURL;
    }

    public void setConnectionURL(String connectionURL) {
        this.connectionURL = connectionURL;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public static boolean isNull(String string) {
        return ( string.equals("") || string.equals(null) );
    }

    public Cicle findCicle(String codi) throws OrdbException {

        try(Connection con = DriverManager.getConnection( connectionURL, properties ) ){
            Cicle cicle = null;
            String sql = "SELECT * FROM \"centre educatiu\".\"Cicles\" where codi = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,codi);
            System.out.println( ps );
            ResultSet result = ps.executeQuery();
            while( result.next() ){
                String nom = result.getString("nom");
                String codi_cicle = result.getString("codi");
                cicle = new Cicle( codi_cicle,nom ) ;
                //System.out.println("codi_cicle = " + codi_cicle);
                return cicle;
            }
            return cicle;
        }catch (SQLException e){
            throw new OrdbException("fallo sql");
        }

    }

    public Assignatura findAssignatura(String codi) throws OrdbException {

        try(Connection con = DriverManager.getConnection( connectionURL, properties ) ){
            Assignatura assignatura = null;
            String sql = "SELECT * FROM \"centre educatiu\".\"Assignatures\" where codi = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,codi);
            System.out.println( ps );
            ResultSet result = ps.executeQuery();
            while( result.next() ){
                String codi_assignatura = result.getString("codi");
                String nom = result.getString("nom");
                String cicle = result.getString("cicle");
                assignatura = new Assignatura( codi_assignatura,nom, findCicle(cicle) ) ;
                //System.out.println("codi_cicle = " + codi_cicle);
                return assignatura;
            }
            return assignatura;
        }catch (SQLException e){
            throw new OrdbException("fallo sql");
        }

    }

public Alumne findAlumneNoContacte(String nif) throws OrdbException{
    try(Connection con = DriverManager.getConnection( connectionURL, properties ) ){
        Alumne assignatura = null;
        String sql = "SELECT * FROM \"centre educatiu\".\"Assignatures\" where codi = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,nif);
        System.out.println( ps );
        ResultSet result = ps.executeQuery();
        while( result.next() ){
            String nif_alumne = result.getString("nif");
            String nom = result.getString("nom");
            String llinatges = result.getString("llinatges");

            assignatura = new Assignatura( codi_assignatura,nom, findCicle(cicle) ) ;
            //System.out.println("codi_cicle = " + codi_cicle);
            return assignatura;
        }
        return assignatura;
    }catch (SQLException e){
        throw new OrdbException("fallo sql");
    }
}

}
