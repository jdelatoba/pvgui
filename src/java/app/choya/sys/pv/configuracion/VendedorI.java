/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.configuracion;

import app.choya.sys.pv.utils.SqlAppsException;
import java.util.ListIterator;
import org.json.simple.JSONObject;

/**
 *
 * @author Rembao
 */
public interface VendedorI {
    
    public int setVendedor(VendedorBean vb) throws SqlAppsException;
    
    public int updateVendedor(VendedorBean vb) throws SqlAppsException;
    
    public int deleteVendedor(VendedorBean vb) throws SqlAppsException;
    
    public JSONObject getListaVendedor(int draw) throws SqlAppsException;
    
    public ListIterator<VendedorBean> getListaVendedor() throws SqlAppsException;
    
    public ListIterator<VendedorBean> getListaVendedorById(int id) throws SqlAppsException;
    
    public VendedorBean getVendedor(int id) throws SqlAppsException;
    
}
