package ClasesABC;

import BD.cBaseDatos;
import java.sql.ResultSet;
import java.sql.SQLException;

public class cABCUsuario {

    //BD
    private ResultSet _rs;
    private BD.cBaseDatos _Datos = new cBaseDatos();
    private String _Sentencia;

    //Variables Usuarios
    private String _Usrs;
    private String _Pasw;
    private int _idUsuario;
    private int _idEmpleado;
    private String _Repues;
    private int _idPregunta;
    int _err;

    //Contructor agregar Usuario
    public cABCUsuario(String Usr, String Pasw, int idEmpleado, String Respuesta, int Idpregunta) {
        this._idEmpleado = idEmpleado;
        this._Usrs = Usr;
        this._Pasw = Pasw;
        this._Repues = Respuesta;
        this._idPregunta = Idpregunta;
    }

    //Modificar Usuario
    public cABCUsuario(int idUsuario, String Usr, String Pasw, String Respuesta, int Idpregunta) {
        this._idUsuario = idUsuario;
        this._Pasw = Pasw;
        this._Usrs = Usr;
        this._Repues = Respuesta;
        this._idPregunta = Idpregunta;
    }

    //Constructor comprobar
    public cABCUsuario(String Usr) throws SQLException {
        this._Usrs = Usr;

    }

    //Cambiar Contraseña
    public cABCUsuario(String Usr, String Pasw) {
        this._Usrs = Usr;
        this._Pasw = Pasw;
    }

    public cABCUsuario() {

    }

    public int AgregarUsuario() throws SQLException {
        this._Datos.conectar();
        this._Sentencia = "call CONSULTA_USUARIOS()";
        this._rs = this._Datos.consulta(this._Sentencia);
        this._idUsuario = 1;
        while (this._rs.next()) {
            this._idUsuario++;
        }
        this._Sentencia = "call REGISTRO_USUARIO(" + this._idUsuario + "," + this._idEmpleado + ",'" + this._Usrs + "','" + this._Pasw + "'," + this._idPregunta + ", '" + this._Repues + "')";
        System.out.println(this._Sentencia);
        this._err = this._Datos.actualizar(this._Sentencia);
        this._Datos.cierraConexion();
        return this._err;
    }

    public String ConsultarUnUsuario() throws SQLException {
        this._Datos.conectar();
        String tabla = "";
        this._Sentencia = "Call CONSULTA_UN_USUARIO('" + this._Usrs + "');";
        this._rs = this._Datos.consulta(_Sentencia);
        while (this._rs.next()) {
            tabla = tabla + "<tr>"
                    + "<td>" + this._rs.getString("Usr") + "</td>"
                    + "<td>" + this._rs.getString("Pasw") + "</td>"
                    + "<td>" + this._rs.getString("N") + "</td>"
                    + "</tr>";
        }
        this._Datos.cierraConexion();
        return tabla;
    }

    public int ConsultarUnUsuarioNumero() throws SQLException {
        this._Datos.conectar();
        int tabla = 0;
        this._Sentencia = "Call CONSULTA_UN_USUARIO('" + this._Usrs + "');";
        this._rs = this._Datos.consulta(_Sentencia);
        while (this._rs.next()) {
            tabla++;            
        }
        this._Datos.cierraConexion();
        return tabla;
    }

    public String ConsultarUnUsuarioNivel() throws SQLException {
        this._Datos.conectar();
        String tabla = "";
        this._Sentencia = "Call CONSULTA_UN_USUARIO('" + this._Usrs + "');";
        this._rs = this._Datos.consulta(_Sentencia);
        while (this._rs.next()) {
            tabla = tabla + this._rs.getString("N");
        }
        this._Datos.cierraConexion();
        return tabla;
    }

    public String ConsultarUsuarios() throws SQLException {
        this._Datos.conectar();
        String tabla = "";
        this._Sentencia = "Call CONSULTA_USUARIOS();";
        this._rs = this._Datos.consulta(_Sentencia);
        while (this._rs.next()) {
            tabla = tabla + "<tr>"
                    + "<td>" + this._rs.getString("Usr") + "</td>"
                    + "<td>" + this._rs.getString("Pasw") + "</td>"
                    + "<td>" + this._rs.getString("N") + "</td>"
                    + "<td id='modify' name='modify'><form action='ModificarEmpleado.jsp' method='post'><button id='modify' name='modify' type='submit' value='" + this._rs.getString("Usr") + "' class='glyphicon glyphicon-pencil'></button></form></td>"
                    + "</tr>";
        }
        this._Datos.cierraConexion();
        return tabla;
    }

    public int identificar(String PswI, String usr) throws SQLException {
        this._Datos.conectar();
        this._Sentencia = "Call CONSULTA_UN_USUARIO('" + this._Usrs + "');";
        this._rs = this._Datos.consulta(_Sentencia);
        if (this._rs.next()) {
            if (usr.equals(this._rs.getString("Usr"))) {
                this._err = 0;
            } else {
                this._err = 2;
            }
            if (PswI.equals(this._rs.getString("Pasw"))) {
                this._err = 0;
            } else {
                this._err = 2;
            }
        } else {
            this._err = 1;
        }
        this._Datos.cierraConexion();
        return this._err;
    }

    public int ModificarUsuario() throws SQLException {
        this._Datos.conectar();
        this._Sentencia = "call MODIFICAR_USUARIO(" + this._idUsuario + ",'" + this._Usrs + "','" + this._Pasw + "'," + this._idPregunta + ",'" + this._Repues + "')";
        this._err = this._Datos.actualizar(this._Sentencia);
        this._Datos.cierraConexion();
        return this._err;
    }

    public String ConsultarUnUsuarioC() throws SQLException {
        this._Datos.conectar();
        this._Sentencia = "Call CONSULTA_UN_USUARIO('" + this._Usrs + "');";
        this._rs = this._Datos.consulta(_Sentencia);
        while (this._rs.next()) {
            this._Repues = this._rs.getString("Respuesta");
        }
        this._Datos.cierraConexion();
        return this._Repues;
    }

    public int comprobar(String Respuesta) throws SQLException {
        this._Repues = ConsultarUnUsuarioC();
        if (this._Repues == null) {
            this._err = 3;
        } else if (this._Repues.equals(Respuesta)) {
            this._err = 0;
        } else {
            this._err = 1;
        }
        return this._err;
    }

    public ResultSet ConsultarCantidadU() throws SQLException {
        this._Datos.conectar();
        this._Sentencia = "call CON_CANU;";
        this._rs = this._Datos.consulta(_Sentencia);
        return this._rs;
    }

    public int CambiarContraseña() throws SQLException {
        this._Datos.conectar();
        this._Sentencia = "call CABIAR_CONTRASEÑA('" + this._Usrs + "','" + this._Repues + "')";
        this._err = this._Datos.actualizar(this._Sentencia);
        return this._err;
    }
}
