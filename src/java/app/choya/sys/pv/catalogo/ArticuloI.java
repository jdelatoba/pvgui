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
public interface ArticuloI {
    
    public int setArticulo(ArticuloBean ab) throws SqlAppsException;
    
    public int updateArticulo(ArticuloBean ab) throws SqlAppsException;
    
    public int deleteArticulo(ArticuloBean ab) throws SqlAppsException;
    
    public ListIterator<ArticuloBean> getArticulo() throws SqlAppsException;
    
    public ListIterator<ArticuloBean> getArticulo(String campo, String buscar) throws SqlAppsException;
    
    public ListIterator<ArticuloBean> getArticulo(int id) throws SqlAppsException;
    
    public ArticuloBean getArticuloBean(int id) throws SqlAppsException;
    
    public ListIterator<ArticuloBean> buscarArticulo(String buscar, int todos_dep, int todos_cat, int con_existencia, int sin_existencia) throws SqlAppsException;
    
    public JSONObject getListaArticulo(String buscar, String tipo_busqueda, int categorias
            , int departamentos, int categoria_id, int departamento_id
            , int con_existencia, int sin_existencia) throws SqlAppsException;
    
}
