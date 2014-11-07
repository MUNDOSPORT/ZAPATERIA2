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
    @ManyToOne
    private Provincia provinciaPadre;
    private String nombre;

    public Ciudad() {
    }

    public Ciudad(String nombre, Provincia padre, int CP) throws Exception {
        this.id = 0;
        this.CP = CP;
        this.provinciaPadre = padre;
        this.nombre = nombre;
        Empresa.persistencia.insert(this);
    }

    public Provincia getProvinciaPadre() {
        return provinciaPadre;
    }

    public void setProvinciaPadre(Provincia provinciaPadre) {
        this.provinciaPadre = provinciaPadre;
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
