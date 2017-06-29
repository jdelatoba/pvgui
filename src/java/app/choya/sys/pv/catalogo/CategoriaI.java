/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.catalogo;

import app.choya.sys.pv.utils.SqlAppsException;
import java.util.ListIterator;
import org.json.simple.JSONObject;

/**
 *
 * @author Rembao
 */
public interface CategoriaI {
    
    public int setCategoria(CategoriaBean cb) throws SqlAppsException;
    
    public int updateCategoria(CategoriaBean cb) throws SqlAppsException;
    
    public int deleteCategoria(CategoriaBean cb) throws SqlAppsException;
    
    public ListIterator<CategoriaBean> getCategoria() throws SqlAppsException;
    
    public CategoriaBean getCategoria(int id) throws SqlAppsException;
    
    public JSONObject getCategoriaListaJSON(int draw) throws SqlAppsException;
    
    public CategoriaBean getCategoriaResume(int id) throws SqlAppsException;
    
}
