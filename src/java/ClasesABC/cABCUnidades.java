package ClasesABC;

import java.sql.ResultSet;
import java.sql.SQLException;

public class cABCUnidades {

    //BD
    private BD.cBaseDatos _Datos = new BD.cBaseDatos();
    private String _Sentencia;
    private ResultSet _rs;

    //Datos Unidades
    private String _IdMatricula;
    private String _Marca;
    private String _Modelo;
    private int _Año;
    private String _Color;
    private int _TipoCarga;

    //Errores
    int _err;

    //Contructor para Agregar Unidad
    public cABCUnidades(String idMatricula, String modelo, String marca, int año, String color, int tipocarga) {
        this._IdMatricula = idMatricula;
        this._Marca = marca;
        this._Modelo = modelo;
        this._Año = año;
        this._TipoCarga = tipocarga;
        this._Color = color;
    }

    public cABCUnidades() {

    }

    //Constructor para Modificar Unidad
    public cABCUnidades(String idMatricula, String color) {
        this._IdMatricula = idMatricula;
        this._Color = color;
    }

    //Constructor para Consultar una Unidad
    public cABCUnidades(String IdUsrM) {
        this._IdMatricula = IdUsrM;
    }

    //metodos ABC
    public int AgregarUnidades() throws SQLException {
        this._Datos.conectar();
        this._Sentencia = "call REGISTRO_UNIDAD('" + this._IdMatricula + "','" + this._Modelo + "','" + this._Marca + "'," + this._Año + ",'" + this._Color + "'," + this._TipoCarga + ");";
        this._err = _Datos.actualizar(this._Sentencia);
        this._Datos.cierraConexion();
        return this._err;
    }

    public void EliminarUnidad() throws SQLException {
        this._Datos.conectar();
        this._Sentencia = "call ELIMINAR_UNIDAD('" + this._IdMatricula + "')";
        this._Datos.actualizar(this._Sentencia);
        this._Datos.cierraConexion();
    }

    //existe Unidad
    public String ExisteUni() throws SQLException {
        this._Datos.conectar();
        String Tabla = "";
        this._Sentencia = "call EXISTE_UNI('" + this._IdMatricula + "')";
        this._rs = this._Datos.consulta(this._Sentencia);
        if (this._rs.next()) {
            Tabla = "Si Existe";
        } else {
            Tabla = "No Existe";
        }
        this._Datos.cierraConexion();
        return Tabla;
    }

    public String ConsultarUnidades() throws SQLException {
        this._Datos.conectar();
        String tabla = "";
        this._Sentencia = "call CONSULTAR_UNIDADES();";
        this._rs = this._Datos.consulta(this._Sentencia);
        while (this._rs.next()) {
            tabla = tabla
                    + "<tr>"
                    + "<td>" + this._rs.getString("exits") + "</td>"
                    + "<td>" + this._rs.getString("modelar") + "</td>"
                    + "<td>" + this._rs.getString("tipo") + "</td>"
                    + "<td>" + this._rs.getInt("tiempo") + "</td>"
                    + "<td>" + this._rs.getString("tono") + "</td>"
                    + "<td>" + this._rs.getString("carga") + "</td>"
                    + "<td id='modify' name='modify'><form action='ModificarUnidad.jsp' method='post'><button id='modify' name='modify' type='submit' value='" + this._rs.getString("exits") + "' class='glyphicon glyphicon-pencil'><input value='" + this._rs.getString("tono") + "' name='tono' id='tono' type='hidden'/></button></form></td>"
                    + "<td id='supr'><form action='../InterP/EliminaUnidad.jsp' method='post'><button type='submit' name='borrar' value='" + this._rs.getString("exits") + "' class='glyphicon glyphicon-trash'></button></form></td>"
                    + "</tr>";
        }
        this._Datos.cierraConexion();
        return tabla;
    }

    public String ConsultarUnaUnidad() throws SQLException {
        this._Datos.conectar();
        String tabla = "";
        this._Sentencia = "call CONSULTAR_UNA_UNIDAD('" + this._IdMatricula + "');";
        this._rs = this._Datos.consulta(this._Sentencia);
        while (this._rs.next()) {
            tabla = tabla
                    + "<tr>"
                    + "<td>" + this._rs.getString("exits") + "</td>"
                    + "<td>" + this._rs.getString("modelar") + "</td>"
                    + "<td>" + this._rs.getString("tipo") + "</td>"
                    + "<td>" + this._rs.getInt("tiempo") + "</td>"
                    + "<td>" + this._rs.getString("tono") + "</td>"
                    + "<td>" + this._rs.getString("carga") + "</td>"
                    + "<td id='modify' name='modify'><form action='ModificarUnidad.jsp' method='post'><button id='modify' name='modify' type='submit' value='" + this._rs.getString("exits") + "' class='glyphicon glyphicon-pencil'><input value='" + this._rs.getString("tono") + "' name='tono' id='tono' type='hidden'/></button></form></td>"
                    + "<td id='supr'><form action='../InterP/EliminaUnidad.jsp' method='post'><button type='submit' name='borrar' value='" + this._rs.getString("exits") + "' class='glyphicon glyphicon-trash'></button></form></td>"
                    + "</tr>";
        }
        this._Datos.cierraConexion();
        return tabla;
    }

    public ResultSet ConsultaMatrícula() throws SQLException {
        this._Datos.conectar();
        this._Sentencia = "call CONSULTAR_MATRI();";
        this._rs = this._Datos.consulta(this._Sentencia);
        return this._rs;
    }

    public void Modify() throws SQLException {
        this._Datos.conectar();
        this._Sentencia = " call MODIFY_UNIDAD('" + this._IdMatricula + "','" + this._Color + "');";
        System.out.println(this._Sentencia);
        this._Datos.actualizar(this._Sentencia);
        this._Datos.cierraConexion();
    }

    public void ModicarUnidad() throws SQLException {
        this._Datos.conectar();
        this._Sentencia = "call MODIFICAR_UNIDAD(" + this._IdMatricula + ",'" + this._Color + "');";
        this._Datos.actualizar(this._Sentencia);
        this._Datos.cierraConexion();
    }
}
