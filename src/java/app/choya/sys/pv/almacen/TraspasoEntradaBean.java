package app.choya.sys.pv.almacen;

/**
 *
 * @author Condese
 */
public class TraspasoEntradaBean {
    
    private int id;
    private int id_traspaso_salida;
    private int id_almacen_origen;
    private int id_solicita;
    private int id_almacen_destino;
    private String estatus;
    private String serie;
    private int creado_por;
    private String fecha_creacion;
    private int modificado_por;
    private String observacion;
    
    public TraspasoEntradaBean() {};

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_traspaso_salida() {
        return id_traspaso_salida;
    }

    public void setId_traspaso_salida(int id_traspaso_salida) {
        this.id_traspaso_salida = id_traspaso_salida;
    }

    public int getId_almacen_origen() {
        return id_almacen_origen;
    }

    public void setId_almacen_origen(int id_almacen_origen) {
        this.id_almacen_origen = id_almacen_origen;
    }

    public int getId_solicita() {
        return id_solicita;
    }

    public void setId_solicita(int id_solicita) {
        this.id_solicita = id_solicita;
    }

    public int getId_almacen_destino() {
        return id_almacen_destino;
    }

    public void setId_almacen_destino(int id_almacen_destino) {
        this.id_almacen_destino = id_almacen_destino;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public int getCreado_por() {
        return creado_por;
    }

    public void setCreado_por(int creado_por) {
        this.creado_por = creado_por;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    
       public int getModificado_por() {
        return modificado_por;
    }

    public void setModificado_por(int modificado_por) {
        this.modificado_por = modificado_por;
    }
    
}
