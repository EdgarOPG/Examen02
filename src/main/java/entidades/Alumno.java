/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author edgar
 */

public class Alumno implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellifo")
    private String apellifo;
    @Column(name = "apellido2")
    private String apellido2;
    @Column(name = "edad")
    private Integer edad;
    @Id
    @Basic(optional = false)
    @Column(name = "curp")
    private String curp;
    @Column(name = "rfc")
    private String rfc;

    public Alumno() {
    }

    public Alumno(String curp) {
        this.curp = curp;
    }

    public Alumno(String nombre, String apellifo, String apellido2, Integer edad, String curp, String rfc) {
        this.nombre = nombre;
        this.apellifo = apellifo;
        this.apellido2 = apellido2;
        this.edad = edad;
        this.curp = curp;
        this.rfc = rfc;
    }
    
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellifo() {
        return apellifo;
    }

    public void setApellifo(String apellifo) {
        this.apellifo = apellifo;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (curp != null ? curp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Alumno)) {
            return false;
        }
        Alumno other = (Alumno) object;
        if ((this.curp == null && other.curp != null) || (this.curp != null && !this.curp.equals(other.curp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Alumno[ curp=" + curp + " ]";
    }
    
}
