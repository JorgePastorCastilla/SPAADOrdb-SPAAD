package cat.iespaucasesnoves.spadd.baseDades;

import cat.iespaucasesnoves.spadd.auxiliars.OrdbException;
import cat.iespaucasesnoves.spadd.dades.Alumne;
import cat.iespaucasesnoves.spadd.dades.Assignatura;
import cat.iespaucasesnoves.spadd.dades.Cicle;
import cat.iespaucasesnoves.spadd.dades.Matricula;

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
        }catch ( SQLException e){
            throw new OrdbException("fallo sql");
        }

    }

    public Assignatura findAssignatura(String codi) throws OrdbException  {

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
        }catch ( SQLException e){
            throw new OrdbException("fallo sql");
        }

    }

public Alumne findAlumneNoContacte(String nif) throws OrdbException{
    try(Connection con = DriverManager.getConnection( connectionURL, properties ) ){
        Alumne alumne = null;
        String sql = "SELECT * FROM \"centre educatiu\".\"Alumnes\" where nif = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,nif);
        System.out.println( ps );
        ResultSet result = ps.executeQuery();
        while( result.next() ){
            String nif_alumne = result.getString("nif");
            String nom = result.getString("nom");
            String llinatges = result.getString("llinatges");
            alumne = new Alumne( nif_alumne, nom, llinatges, null ) ;
            return alumne;
        }
        return alumne;
    }catch ( SQLException e){
        throw new OrdbException("fallo sql");
    }
}

public Matricula findMatricula(String nif, String assignatura) throws OrdbException{
    try(Connection con = DriverManager.getConnection( connectionURL, properties ) ){
        Matricula matricula = null;
        String sql = "SELECT * FROM \"centre educatiu\".\"Matricula\" where nif = ? and asignatura = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,nif);
        ps.setString(2,assignatura);
        System.out.println( ps );
        ResultSet result = ps.executeQuery();
        while( result.next() ){
            String nif_alumne = result.getString("nif");
            String nom_assignatura = result.getString("asignatura");
            Integer[] notes = (Integer[]) result.getArray("notes").getArray();
            matricula = new Matricula( findAlumneNoContacte(nif_alumne), findAssignatura(nom_assignatura), notes ) ;
            return matricula;
        }
        return matricula;
    }catch (SQLException e){
        throw new OrdbException("fallo sql");
    }
}

public ArrayList<Alumne> getAlumnes() throws OrdbException{
    try(Connection con = DriverManager.getConnection( connectionURL, properties ) ){
        ArrayList<Alumne> alumnes = new ArrayList<Alumne>();
        String sql = "SELECT * FROM \"centre educatiu\".\"Alumnes\"";
        PreparedStatement ps = con.prepareStatement(sql);
        System.out.println( ps );
        ResultSet result = ps.executeQuery();
        while( result.next() ){
            String nif_alumne = result.getString("nif");
            String nom = result.getString("nom");
            String llinatges = result.getString("llinatges");
            alumnes.add( new Alumne( nif_alumne, nom, llinatges, null ) );
        }
        return alumnes;
    }catch ( SQLException e){
        throw new OrdbException("fallo sql");
    }
}

public ArrayList<Matricula> findMatriculas(Alumne alumne) throws OrdbException{
    try(Connection con = DriverManager.getConnection( connectionURL, properties ) ){
        ArrayList<Matricula> matriculas = new ArrayList<Matricula>();
        String sql = "SELECT * FROM \"centre educatiu\".\"Matricula\" where nif = ? ";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,alumne.getNif());
        System.out.println( ps );
        ResultSet result = ps.executeQuery();
        while( result.next() ){
            String nif_alumne = result.getString("nif");
            String nom_assignatura = result.getString("asignatura");
            Integer[] notes = (Integer[]) result.getArray("notes").getArray();
            matriculas.add( new Matricula( findAlumneNoContacte(nif_alumne), findAssignatura(nom_assignatura), notes ) );
        }
        return matriculas;
    }catch (SQLException e){
        throw new OrdbException("fallo sql");
    }
}

public ArrayList<Matricula> findMatriculas(Assignatura assignatura) throws OrdbException{
    try(Connection con = DriverManager.getConnection( connectionURL, properties ) ){
        ArrayList<Matricula> matriculas = new ArrayList<Matricula>();
        String sql = "SELECT * FROM \"centre educatiu\".\"Matricula\" where asignatura = ? ";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,assignatura.getCodi());
        System.out.println( ps );
        ResultSet result = ps.executeQuery();
        while( result.next() ){
            String nif_alumne = result.getString("nif");
            String nom_assignatura = result.getString("asignatura");
            Integer[] notes = (Integer[]) result.getArray("notes").getArray();
            matriculas.add( new Matricula( findAlumneNoContacte(nif_alumne), findAssignatura(nom_assignatura), notes ) );
        }
        return matriculas;
    }catch (SQLException e){
        throw new OrdbException("fallo sql");
    }
}
//TODO:Modificar una de les notes d'una assignatura d'un alumne.

//TODO:Recuperar un alumne per l'identificador (inclòs contacte).

//TODO:Afegir un alumne nou a la base de dades.

//TODO:Matricular un alumne a una assignatura.

//TODO:Afegir les notes d'aquest alumne nou per a una assignatura.

}
