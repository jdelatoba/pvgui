/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.configuracion;

import app.choya.sys.pv.utils.SqlAppsException;
import org.json.simple.JSONObject;

/**
 *
 * @author Rembao
 */
public interface RolI {
    
    
    public JSONObject getRolJSON(int draw) throws SqlAppsException; 
}
