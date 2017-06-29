
package app.choya.sys.pv.admon;

import java.io.Serializable;

/**
 *
 * @author developer
 * bean para la tabla adm_usuario
 */
public class AdmUsuariosBean implements Serializable {
    
    private int usuario_id; //USUARIO_ID
    private String usuario; //USUARIO
    private String nombre;
    private String apellido_paterno;
    private String apellido_materno;
    private int dependencia_id;
    private int direccion_id;
    private int departamento_id;
    private String correo;
    private String descripcion; // DESCRIPCION
    private String password; //PASSWORD
    private String fecha_inicio; //FECHA_INICIO
    private String fecha_termino; //FECHA_TERMINO
    private String fecha_ultimo_acceso; //FECHA_ULTIMO_ACCESO
    private String fecha_password; //FECHA_PASSWORD
    private String fecha_creacion; //FECHA_CREACION
    private int creado_por; //CREADO_POR
    private String fecha_modificacion; //FECHA_MODIFICACION
    private int modificado_por; //MODIFICADO_POR
    private String expirado; //EXPIRADO
    private String dirip; //DIR_IP
    private String oficina; //OFICINA
    private String adscripcion; //ADSCRIPCION
    private String telefono;
    private String extension;

    public AdmUsuariosBean() {
    }

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public String getUsuario() {
        if (usuario != null) {
            return usuario;
        } else {
            return "";
        }
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        if (nombre != null) {
            return nombre;
        } else {
            return "";
        }
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido_paterno() {
        if (apellido_paterno != null) {
            return apellido_paterno;
        } else {
            return "";
        }
    }

    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }

    public String getApellido_materno() {
        if (apellido_materno != null) {
            return apellido_materno;
        } else {
            return "";
        }
    }

    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }

    public int getDependencia_id() {
        return dependencia_id;
    }

    public void setDependencia_id(int dependencia_id) {
        this.dependencia_id = dependencia_id;
    }

    public int getDireccion_id() {
        return direccion_id;
    }

    public void setDireccion_id(int direccion_id) {
        this.direccion_id = direccion_id;
    }

    public int getDepartamento_id() {
        return departamento_id;
    }

    public void setDepartamento_id(int departamento_id) {
        this.departamento_id = departamento_id;
    }

    public String getCorreo() {
        if (correo != null) {
            return correo;
        } else {
            return "";
        }
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDescripcion() {
        if (descripcion != null) {
            return descripcion;
        } else {
            return "";
        }
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPassword() {
        if (password != null) {
            return password;
        } else {
            return "";
        }
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFecha_inicio() {
        if (fecha_inicio != null) {
            return fecha_inicio;
        } else {
            return "";
        }
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_termino() {
        if (fecha_termino != null) {
            return fecha_termino;
        } else {
            return "";
        }
    }

    public void setFecha_termino(String fecha_termino) {
        this.fecha_termino = fecha_termino;
    }

    public String getFecha_ultimo_acceso() {
        if (fecha_ultimo_acceso != null) {
            return fecha_ultimo_acceso;
        } else {
            return "";
        }
    }

    public void setFecha_ultimo_acceso(String fecha_ultimo_acceso) {
        this.fecha_ultimo_acceso = fecha_ultimo_acceso;
    }

    public String getFecha_password() {
        if (fecha_password != null) {
            return fecha_password;
        } else {
            return "";
        }
    }

    public void setFecha_password(String fecha_password) {
        this.fecha_password = fecha_password;
    }

    public String getFecha_creacion() {
        if (fecha_creacion != null) {
            return fecha_creacion;
        } else {
            return "";
        }
    }

    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public int getCreado_por() {
        return creado_por;
    }

    public void setCreado_por(int creado_por) {
        this.creado_por = creado_por;
    }

    public String getFecha_modificacion() {
        if (fecha_modificacion != null) {
            return fecha_modificacion;
        } else {
            return "";
        }
    }

    public void setFecha_modificacion(String fecha_modificacion) {
        this.fecha_modificacion = fecha_modificacion;
    }

    public int getModificado_por() {
        return modificado_por;
    }

    public void setModificado_por(int modificado_por) {
        this.modificado_por = modificado_por;
    }

    public String getExpirado() {
        return expirado;
    }

    public void setExpirado(String expirado) {
        this.expirado = expirado;
    }

    public String getDirip() {
        if (dirip != null) {
            return dirip;
        } else {
            return "";
        }
    }

    public void setDirip(String dirip) {
        this.dirip = dirip;
    }

    public String getOficina() {
        if (oficina != null) {
            return oficina;
        } else {
            return oficina;
        }
    }

    public void setOficina(String oficina) {
        this.oficina = oficina;
    }

    public String getAdscripcion() {
        if (adscripcion != null) {
            return adscripcion;
        } else {
            return "";
        }
    }

    public void setAdscripcion(String adscripcion) {
        this.adscripcion = adscripcion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
    
    

}
