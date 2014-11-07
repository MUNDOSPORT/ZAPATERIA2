package project1;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Ciudad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int CP;
    private String Pais;
    private String provincia;
    private String nombre;

    public Ciudad() {
    }

    public Ciudad(String nombre, String provincia, String pais, int CP) throws Exception {
        this.id = 0;
        this.CP = CP;
        this.provincia = provincia;
        this.Pais = pais;
        this.nombre = nombre;
        Empresa.persistencia.insert(this);
    }


    public void setPais(String Pais) {
        this.Pais = Pais;
    }

    public String getPais() {
        return Pais;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getProvincia() {
        return provincia;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCP(int CP) {
        this.CP = CP;
    }

    public int getCP() {
        return CP;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
