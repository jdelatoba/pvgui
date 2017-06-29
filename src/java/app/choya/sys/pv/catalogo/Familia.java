/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.catalogo;

import app.choya.sys.pv.dao.EApps;
import app.choya.sys.pv.dao.EDriver;
import app.choya.sys.pv.dao.GenericDAO;
import app.choya.sys.pv.utils.SqlAppsException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rembao
 */
public class Familia extends GenericDAO implements FamiliaI{

    public Familia() {
    }
    

    @Override
    public int setFamilia(FamiliaBean fb) throws SqlAppsException {
        
        int resultado = 0;
        try {
            getConexion(EDriver.MYSQL, EApps.PV);
            
            String sql = "INSERT INTO cat_familia(id"
                    + ",descripcion"
                    + ",estatus"
                    + ",url_img"
                    + ",creado_por"
                    + ",fecha_creacion"
                    + ",modificado_por) VALUES "
                    + "(null"
                    + ",?"
                    + ",?"
                    + ",?"
                    + ",?"
                    + ",current_timestamp(),?)";
            
            PreparedStatement ps = getPrepareStatement(sql);
            
            ps.setString(1, fb.getDescripcion());
            //ps.set
            
            System.out.println("");
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (SQLException ex) {
            Logger.getLogger(Familia.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return resultado;
    }

    @Override
    public int updateFamilia(FamiliaBean fb) throws SqlAppsException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int updateFamilia(FamiliaBean fb1, FamiliaBean fb2) throws SqlAppsException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ListIterator<FamiliaBean> getFamilia() throws SqlAppsException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public FamiliaBean getFamilia(int id) throws SqlAppsException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deleteFamiliaBean(int id) throws SqlAppsException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
