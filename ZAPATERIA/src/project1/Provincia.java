package project1;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Provincia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Pais unPais;
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ciudad> ciudades;

    public Provincia(String nombre, Pais unPais) throws Exception {
        this.id = 0;
        this.nombre = nombre;
        this.unPais = unPais;
        this.ciudades = new LinkedList<Ciudad>();
        Empresa.persistencia.insert(this);
    }

    public Provincia() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUnPais(Pais unPais) {
        this.unPais = unPais;
    }

    public Pais getUnPais() {
        return unPais;
    }

    public void setDomicilios(List<Ciudad> ciudades) {
        this.ciudades = ciudades;
    }

    public List<Ciudad> getciudades() {
        return ciudades;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Ciudad altaCiudad(String nombreCiudad) throws Exception {
        if (obtenerCiudad(nombreCiudad) != null) {
            throw new Exception("La Localidad: " + nombreCiudad + " ya existe");
        } else {
            Ciudad unDomicilio = new Ciudad(nombreCiudad, this);
            this.ciudades.add(unDomicilio);
            Empresa.persistencia.update(this);
            return unDomicilio;
        }


    }

    public void bajaCiudad(Ciudad unaCiudad) throws Exception {
        if (existeCiudad(unaCiudad)) {
            this.ciudades.remove(unaCiudad);
            Empresa.persistencia.update(this);
        } else {
            throw new Exception("La Localidad a ser eliminada no existe en el Sistema");
        }
    }

    public Ciudad modificarCiudad(Ciudad unaCiudad, String nombreunaCiudad) throws Exception {
        nombreunaCiudad = nombreunaCiudad.trim();
        if (obtenerCiudad(nombreunaCiudad) != null) {
            throw new Exception("La modificacion ingresada corresponde a otra localidad exitente en el sistema");
        } else {
            if (existeCiudad(unaCiudad) && (unaCiudad.getNombre().equals(unaCiudad) != true)) {
                unaCiudad.setNombre(nombreunaCiudad);
                Empresa.persistencia.update(this);
            }
            return unaCiudad;
        }
    }

    public Ciudad obtenerCiudad(String nombreCiudad) {
        Ciudad devolver = null;
        for (Ciudad unaCiudad : this.getciudades()) {
            if (unaCiudad.getNombre().equals(nombreCiudad)) {
                devolver = unaCiudad;
            }
        }
        return devolver;

    }

    public boolean existeCiudad(Ciudad localidad) {
        boolean existe = false;
        List<Ciudad> p = this.getciudades();
        for (Ciudad unaLocalidad : p) {
            if (unaLocalidad.equals(localidad)) {
                existe = true;
            }
        }
        return existe;
    }
}
