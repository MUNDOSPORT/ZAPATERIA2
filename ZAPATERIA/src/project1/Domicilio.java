package project1;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Domicilio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    private Ciudad unaCiudad;
    private String domicilio;

    public Domicilio(Ciudad localidad, String domicilio) throws Exception {
        this.id = 0;
        this.unaCiudad = localidad;
        this.domicilio = domicilio;
        Empresa.persistencia.insert(this);
    }

    public Domicilio() {
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public void setUnaCiudad(Ciudad unaCiudad) {
        this.unaCiudad = unaCiudad;
    }

    public Ciudad getUnaCiudad() {
        return unaCiudad;
    }

    @Override
    public String toString() {
        return this.getDomicilio();
    }
}
