package BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class cBaseDatos {
    
    private String _usuarioBD;
    private String _passwordBD;
    private String _urlBD;
    private String _driverClassName;
    private Connection _conn = null;
    private Statement _estancia;
 
    public cBaseDatos(String usrBD, String paswBD, String url, String Driver) {
        this._usuarioBD = usrBD;
        this._passwordBD = paswBD;
        this._urlBD = url;
        this._driverClassName = Driver;
    }
    public cBaseDatos() {
        //poner los datos apropiados
        this._usuarioBD = "root";
        this._passwordBD = "n0m3l0";
        this._urlBD = "jdbc:mysql://localhost:3306/sns";
        this._driverClassName = "com.mysql.jdbc.Driver";
    }    
    
    //metodos para establecer los valores de conexion a la BD
    public void AsignarUsuarioBD(String usuario) throws SQLException {
        this._usuarioBD = usuario;
    }
    public void AsignarPassBD(String pass) {
        this._passwordBD = pass;
    } 
    public void AsignarUrlBD(String url) {
        this._urlBD = url;
    }
    public void AsignarConn(Connection conn) {
        this._conn = conn;
    }
    public void AsignarDriverClassName(String driverClassName) {
        this._driverClassName = driverClassName;
    }
    
    //Conexion a la BD
    public void conectar() throws SQLException {
        try {
            Class.forName(this._driverClassName).newInstance();
            this._conn = DriverManager.getConnection(this._urlBD, this._usuarioBD, this._passwordBD);
 
        } catch (Exception err) {
            System.out.println("Error " + err.getMessage());
        }
    }
    
    //Cerrar la conexion de BD
    public void cierraConexion() throws SQLException {
        this._conn.close();
    }
    
    //Metodos para ejecutar sentencias SQL
    public ResultSet consulta(String consultar) throws SQLException {
        this._estancia = (Statement) _conn.createStatement();
        return this._estancia.executeQuery(consultar);
    } 
    public int actualizar(String actualizar) throws SQLException {
        this._estancia = (Statement) _conn.createStatement();
        return _estancia.executeUpdate(actualizar);
        
    } 
}
