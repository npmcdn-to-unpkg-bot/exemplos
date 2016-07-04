package pacote.modelo2;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class Comentario implements Serializable {
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    public Usuario usuario;

    public String texto;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dataDoComentario;

    public Calendar getDataDoComentario() {
	return dataDoComentario;
    }

    public void setDataDoComentario(Calendar dataDoComentario) {
	this.dataDoComentario = dataDoComentario;
    }

    public Usuario getUsuario() {
	return usuario;
    }

    public void setUsuario(Usuario usuario) {
	this.usuario = usuario;
    }

    public String getTexto() {
	return texto;
    }

    public void setTexto(String texto) {
	this.texto = texto;
    }
}
