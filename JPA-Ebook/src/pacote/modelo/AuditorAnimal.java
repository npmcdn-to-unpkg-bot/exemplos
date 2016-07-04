package pacote.modelo;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class AuditorAnimal {

    @PreUpdate
    @PrePersist
    public void alterarDataUltimaAtualizacao(Animal animal) {
	animal.setDataUltimaAtualização(new Date());
    }
}
