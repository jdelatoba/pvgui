/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.admon;


import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Informatica
 */
public class ControlAccesoBean implements Serializable {
    
    private int control_id;
    private Date fecha_bloqueo;
    private String ip;
    private String estatus;
    private String usuario;
    private int modificador_por;
    private Date fecha_modificacion;
    
}
