/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.administracion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.ListIterator;
import app.choya.sys.pv.dao.EApps;
import app.choya.sys.pv.dao.EDriver;
import app.choya.sys.pv.dao.GenericDAO;

/**
 *
 * @author Rembao
 */
public class RolAgenda extends GenericDAO {

    public ListIterator<RolAgendaBean> getDueno(int ejectivo_id, int dependencia_id, int subsecretaria_id) throws SQLException {

        LinkedList<RolAgendaBean> lista = new LinkedList<>();

        getConexion(EDriver.MYSQL, EApps.PV);

        String sql = "select id\n"
                + ",CONCAT(nombre,' ',apellido_paterno,' ',apellido_materno) as nombre\n"
                + "FROM cat_funcionario\n"
                + "WHERE ejecutivo_id = ?\n"
                + "and dependencia_id =?\n"
                + "and subsecretaria_id = ?\n"
                + "and nivel = 2";
        try{
        PreparedStatement ps = getPrepareStatement(sql);
        ps.setInt(1, ejectivo_id);
        ps.setInt(2, dependencia_id);
        ps.setInt(3, subsecretaria_id);
            System.out.println("ps "+ps);
        
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            RolAgendaBean rb = new RolAgendaBean();
            rb.setDueno_id(rs.getInt("id"));
            rb.setNombre_dueno(rs.getString("nombre"));
            lista.add(rb);
        }
        }catch(SQLException ex){
            System.out.println("el metodo getDueno lanzo el siguiente error "+ex.getMessage());
            throw new SQLException(ex);
        }finally{
            closeResultSet();
            closePStmt();
            closeConexion();
        }
        
        
        return lista.listIterator();
    }

}
