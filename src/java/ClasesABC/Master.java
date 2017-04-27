/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesABC;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.ResultSet;
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
}
