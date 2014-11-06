package project1;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cliente extends Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int numeroCliente;

    public Cliente(String nroDocCli, String nombreCli, String domCli, String telCli, String apellidoCli) {
        super(nroDocCli, nombreCli, domCli, telCli, apellidoCli);
        this.numeroCliente = 0;
    }

    public void setNumeroCliente(int numeroCliente) {
        this.numeroCliente = numeroCliente;
    }

    public int getNumeroCliente() {
        return numeroCliente;
    }

}
