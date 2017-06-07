/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesABC;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author Laptop
 */
@WebService
@Stateless
public class Master {

    private BD.cBaseDatos _Datos = new BD.cBaseDatos();
    private ResultSet _rs;
    private String _Sentencia;
    private String[] Empl;
    private ArrayList<String[]> Tabla;

    /**
     * Web service operation
     */
    @WebMethod(operationName = "ConsultaEmpleados")
    public ArrayList<String[]> ConsultaEmpleados(@WebParam(name = "NombreBuscado") String NombreBuscado) {
        try {
            this._Datos.conectar();
            if (NombreBuscado.equals("")) {
                this._Sentencia = "call CONSULTA_EMPLEADOS;";
            } else {
                this._Sentencia = "call CONSULTA_UN_EMPLEADO('" + NombreBuscado + "');";
            }
            this._rs = this._Datos.consulta(this._Sentencia);
            Empl = new String[6];
            while (this._rs.next()) {
                Empl[0] = this._rs.getString("Nombre");
                Empl[1] = this._rs.getString("NAD");
                Empl[2] = this._rs.getString("Licencia");
                Empl[3] = this._rs.getString("tel");
                Empl[4] = this._rs.getString("cel");
                Empl[5] = this._rs.getString("email");
                Tabla.add(Empl);
            }
            this._Datos.cierraConexion();
        } catch (Exception ex) {
            Empl = new String[2];
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            Empl[0] = sw.toString();
            Empl[1] = "Error";
            Tabla.add(Empl);
        }
        return Tabla;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "Usuarios")
    public ArrayList<String[]> Usuarios(@WebParam(name = "UsuarioBuscado") String UsuarioBuscado) {
        try {
            this._Datos.conectar();
            if (UsuarioBuscado.equals("")) {
                this._Sentencia = "call CONSULTA_USUARIOS()";
            } else {
                this._Sentencia = "Call CONSULTA_UN_USUARIO('" + UsuarioBuscado + "');";
            }
            this._rs = this._Datos.consulta(this._Sentencia);
            Empl = new String[6];
            while (this._rs.next()) {
                Empl[0] = this._rs.getString("Usr");
                Empl[1] = this._rs.getString("Pasw");
                Empl[2] = this._rs.getString("N");                
                Tabla.add(Empl);
            }
            this._Datos.cierraConexion();
        } catch (Exception ex) {
            Empl = new String[2];
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            Empl[0] = sw.toString();
            Empl[1] = "Error";
            Tabla.add(Empl);
        }
        return Tabla;
    }   
    
    @WebMethod(operationName = "Unidades")
    public ArrayList<String[]> Unidades(@WebParam(name = "UnidadBuscada") String UnidadBuscada) {
        try {
            this._Datos.conectar();
            if (UnidadBuscada.equals("")) {
                this._Sentencia = "call CONSULTAR_UNIDADES();";
            } else {
                this._Sentencia = "call CONSULTAR_UNA_UNIDAD('"+ UnidadBuscada + "');";
            }
            this._rs = this._Datos.consulta(this._Sentencia);
            Empl = new String[6];
            while (this._rs.next()) {
                Empl[0] = this._rs.getString("exits");
                Empl[1] = this._rs.getString("modelar");
                Empl[2] = this._rs.getString("tipo");
                Empl[3] = "" + this._rs.getInt("tiempo");
                Empl[4] = this._rs.getString("tono");
                Empl[5] = this._rs.getString("carga");
                Tabla.add(Empl);
            }
            this._Datos.cierraConexion();
        } catch (Exception ex) {
            Empl = new String[2];
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            Empl[0] = sw.toString();
            Empl[1] = "Error";
            Tabla.add(Empl);
        }
        return Tabla;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "Login")
    public String Login(@WebParam(name = "UsuarioLog") String UsuarioLog, @WebParam(name = "PaswordLog") String PaswordLog) {
        String err = "Bien";
        try {
        this._Datos.conectar();
        this._Sentencia = "Call CONSULTA_UN_USUARIO('" + UsuarioLog + "');";
        this._rs = this._Datos.consulta(_Sentencia);
        if (this._rs.next()) {
            if (UsuarioLog.equals(this._rs.getString("Usr"))) {
                err = "Bien";
            } else {
                err = "Usuario Incorrecto";
            }
            if (PaswordLog.equals(this._rs.getString("Pasw"))) {
                err = "Acediendo....";
            } else {
                err = "Pasword Mal";
            }
        } else {
            err = "No existe Usuario";
        }
        this._Datos.cierraConexion();
          } catch (Exception ex) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            err = sw.toString();       
        }
        return err;
    }            
}
