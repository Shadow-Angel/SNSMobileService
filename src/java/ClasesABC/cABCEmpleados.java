package ClasesABC;

import java.sql.ResultSet;
import java.sql.SQLException;

public class cABCEmpleados {

    //BD
    private BD.cBaseDatos _Datos = new BD.cBaseDatos();
    private ResultSet _rs;
    private String _Sentencia;

    //Datos Empleado
    private int _IdEmpleado;
    private String _Nombre;
    private int _NADmi;
    private int _IdLicencia;
    private String _Contacto[] = new String[3];

    //Errores
    int _err;

    //Contructor para Agregar Empleado
    public cABCEmpleados(String nombre, int NaDmi, String email, String tel, String cel, int idD) {
        this._Nombre = nombre;
        this._NADmi = NaDmi;
        this._IdLicencia = idD;
        this._Contacto[0] = email;
        this._Contacto[1] = tel;
        this._Contacto[2] = cel;
    }

    public cABCEmpleados(int idEmpl) {
        this._IdEmpleado = idEmpl;
    }

    //Constructor para Modificar Usuario
    public cABCEmpleados(int IdUsrM, String nombre, int NaDmi, String email, String tel, String cel, int idD) {
        this._IdEmpleado = IdUsrM;
        this._Nombre = nombre;
        this._NADmi = NaDmi;
        this._IdLicencia = idD;
        this._Contacto[0] = email;
        this._Contacto[1] = tel;
        this._Contacto[2] = cel;
    }

    //Constructor para Consultar un Usuario
    public cABCEmpleados(String nombre) {
        this._Nombre = nombre;
    }

    //Contructor consultar usuarios
    public cABCEmpleados() {

    }

    //metodos ABC
    public int AgregarEmpleado() throws SQLException {
        this._Datos.conectar();
        this._Sentencia = "Call CONSULTA_EMPLEADOS();";
        this._rs = this._Datos.consulta(_Sentencia);
        this._IdEmpleado = 1;
        while (this._rs.next()) {
            this._IdEmpleado++;
        }
        this._Sentencia = "call REGISTRO_EMPLEADOS(" + this._IdEmpleado + ",'" + this._Nombre + "','" + this._Contacto[0] + "'," + this._Contacto[1] + "," + this._Contacto[2] + "," + this._NADmi + "," + this._IdLicencia + ");";
        this._err = _Datos.actualizar(this._Sentencia);
        this._Datos.cierraConexion();
        return this._IdEmpleado;
    }

    public int getIdEmpleado() {
        return this._IdEmpleado;
    }

    public int ConsultarUnEmpleadoNumero() throws SQLException {
        this._Datos.conectar();
        int tabla = 0;
        this._Sentencia = "call CONSULTA_UN_EMPLEADO('" + this._Nombre + "');";
        this._rs = this._Datos.consulta(this._Sentencia);
        while (this._rs.next()) {
            tabla++;
        }
        this._Datos.cierraConexion();
        return tabla;
    }

    public String ConsultarUnEmpleado() throws SQLException {
        this._Datos.conectar();
        String tabla = "";
        this._Sentencia = "call CONSULTA_UN_EMPLEADO('" + this._Nombre + "');";
        this._rs = this._Datos.consulta(this._Sentencia);
        while (this._rs.next()) {
            tabla = tabla + "<tr>"
                    + "<td>" + this._rs.getString("Nombre") + "</td>"
                    + "<td>" + this._rs.getString("NAD") + "</td>"
                    + "<td>" + this._rs.getString("Licencia") + "</td>"
                    + "<td>" + this._rs.getString("tel") + "</td>"
                    + "<td>" + this._rs.getString("cel") + "</td>"
                    + "<td>" + this._rs.getString("email") + "</td>"
                    + "<td id='modify' name='modify'><form action='ModificarEmpleado.jsp' method='post'><button id='modify' name='modify' type='submit' value='" + this._rs.getString("id") + "' class='glyphicon glyphicon-pencil'></button></form></td>"
                    + "<td id='supr'><form action='../InterP/EliminaEmpl.jsp' method='post'><button type='submit' name='borrar' value='" + this._rs.getString("id") + "' class='glyphicon glyphicon-trash'></button></form></td>"
                    + "<td></td>"
                    + "<td></td>"
                    + "</tr>";
        }
        this._Datos.cierraConexion();
        return tabla;
    }

    public String[] ConsultarUnEmpleadoModi() throws SQLException {
        this._Datos.conectar();
        String tabla[] = new String[6];
        this._Sentencia = "call CONSULTA_UN_EMPLEADO('" + this._Nombre + "');";
        this._rs = this._Datos.consulta(this._Sentencia);
        while (this._rs.next()) {
            tabla[0] = this._rs.getString("Nombre");
            tabla[1] = this._rs.getString("email");
            tabla[2] = this._rs.getString("tel");
            tabla[3] = this._rs.getString("cel");
        }
        this._Datos.cierraConexion();
        return tabla;
    }

    public String ConsultarEmpleados() throws SQLException {
        this._Datos.conectar();
        String tabla = "";
        this._Sentencia = "call CONSULTA_EMPLEADOS;";
        this._rs = this._Datos.consulta(this._Sentencia);
        while (this._rs.next()) {
            tabla = tabla + "<tr>"
                    + "<td>" + this._rs.getString("Nombre") + "</td>"
                    + "<td>" + this._rs.getString("NAD") + "</td>"
                    + "<td>" + this._rs.getString("Licencia") + "</td>"
                    + "<td>" + this._rs.getString("tel") + "</td>"
                    + "<td>" + this._rs.getString("cel") + "</td>"
                    + "<td>" + this._rs.getString("email") + "</td>"
                    + "<td id='modify' name='modify'><form action='ModificarEmpleado.jsp' method='post'><button id='modify' name='modify' type='submit' value='" + this._rs.getString("Nombre") + "' class='glyphicon glyphicon-pencil'></button></form></td>"
                    + "<td id='supr'><form action='../InterP/EliminaEmpl.jsp' method='post'><button type='submit' name='borrar' value='" + this._rs.getString("id") + "' class='glyphicon glyphicon-trash'></button></form></td>"
                    + "<td></td>"
                    + "</tr>";
        }
        this._Datos.cierraConexion();
        return tabla;
    }
    
        public String ConsultarNivelEmpleado() throws SQLException {
        this._Datos.conectar();
        String tabla = "";
        this._Sentencia = "call CONSULTA_UN_EMPLEADO('" + this._Nombre + "');";
        this._rs = this._Datos.consulta(this._Sentencia);
        while (this._rs.next()) {
            tabla = tabla + "<option value='1'>" + this._rs.getString("NAD") + "</option>";
        }
        this._Datos.cierraConexion();
        return tabla;
    }

    public ResultSet ConsultarCantidadE() throws SQLException {
        this._Datos.conectar();
        this._Sentencia = "call CON_CANE;";
        this._rs = this._Datos.consulta(_Sentencia);
        return this._rs;
    }

    public ResultSet ConsutarEmp1234() throws SQLException {
        this._Datos.conectar();
        this._Sentencia = "call CON_EMPL_N5;";
        this._rs = this._Datos.consulta(_Sentencia);
        return this._rs;
    }

    public ResultSet ConsutarEmp5() throws SQLException {
        this._Datos.conectar();
        this._Sentencia = "call CON_EMPL_5;";
        this._rs = this._Datos.consulta(_Sentencia);
        return this._rs;
    }

    public int ModicarEmpleado() throws SQLException {
        this._Datos.conectar();
        this._Sentencia = "call MODIFICAR_USUARIO(" + this._IdEmpleado + ",'" + this._Nombre + "'," + this._NADmi + ",'" + this._Contacto[0] + "','" + this._Contacto[1] + "','" + this._Contacto[2] + "');";
        this._err = this._Datos.actualizar(_Sentencia);
        this._Datos.cierraConexion();
        return this._err;
    }

    public int EliminarEmpleado() throws SQLException {
        this._Datos.conectar();
        this._Sentencia = "call ELIMINAR_EMPLEADO(" + this._IdEmpleado + ");";
        this._err = this._Datos.actualizar(_Sentencia);
        this._Datos.cierraConexion();
        return this._err;
    }

}
