package project1;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Pais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Provincia> provincias;

    public Pais() {
    }

    public Pais(String nombre) throws Exception {
        this.id = 0;
        this.nombre = nombre;
        this.provincias = new LinkedList<Provincia>();
        Empresa.persistencia.insert(this);
    }

    // <editor-fold defaultstate="collapsed" desc=" Seter y Geter ">
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public List<Provincia> getProvincias() {
        return provincias;
    }

    public void setProvincias(List<Provincia> provincias) {
        this.provincias = provincias;
    }

    // </editor-fold>
    public Provincia altaProvincia(String nombreProvincia) throws Exception {
        nombreProvincia = nombreProvincia.trim();
        if ((obtenerProvincia(nombreProvincia)) != null) {
            throw new Exception("La Provincia: " + nombreProvincia + " ya existe");
        } else {
            Provincia unaProvincia = new Provincia(nombreProvincia, this);
            this.provincias.add(unaProvincia);
            Empresa.persistencia.update(this);
            return unaProvincia;
        }
    }

    public void bajaProvincia(Provincia unaProvincia) throws Exception {
        try {
            this.provincias.remove(unaProvincia);
            Empresa.persistencia.update(this);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void modificarProvincia(Provincia unaProvincia, String nombreProvincia) throws Exception {
        if ((obtenerProvincia(nombreProvincia) == null) && (unaProvincia.getNombre().equals(nombreProvincia) != true)) {
            try {
                unaProvincia.setNombre(nombreProvincia);
                Empresa.persistencia.update(this);
                Empresa.persistencia.update(unaProvincia);
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }

        } else {
            throw new Exception("Ya xiste una provincia con el nombre " + nombreProvincia);
        }
    }

    public Ciudad altaLocalidad(Provincia unaProvincia, String nombreLocalidad, int CP) throws Exception {
        Ciudad unaCiudad = unaProvincia.altaCiudad(nombreLocalidad, CP);
        return unaCiudad;
    }

    public Ciudad modificarLocalidad(Provincia unaProvincia, Ciudad unaCiudad, String nombreCiudad) throws Exception {
        return unaProvincia.modificarCiudad(unaCiudad, nombreCiudad);
    }

    public void bajaCiudad(Provincia unaProvincia, Ciudad unaCiudad) throws Exception {
        unaProvincia.bajaCiudad(unaCiudad);
    }

    public boolean existeProvincia(Provincia unaProvincia) {
        boolean existe = false;
        List<Provincia> p = this.getProvincias();
        for (Provincia unaProv : p) {
            if (unaProv.equals(unaProvincia)) {
                existe = true;
            }
        }
        return existe;
    }

    public Provincia obtenerProvincia(String nombreProvincia) {
        Provincia devolver = null;
        for (Provincia unaProvincia : this.getProvincias()) {
            if (unaProvincia.getNombre().toUpperCase().equals(nombreProvincia.toUpperCase())) {
                devolver = unaProvincia;
            }
        }
        return devolver;

    }
}
