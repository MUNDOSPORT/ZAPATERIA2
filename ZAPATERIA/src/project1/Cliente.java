package project1;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cliente extends Persona {
    public Cliente(String nroDocCli, String nombreCli, String domCli, String telCli, String apellidoCli) {
        super(nroDocCli, nombreCli, domCli, telCli, apellidoCli);
    }
}
