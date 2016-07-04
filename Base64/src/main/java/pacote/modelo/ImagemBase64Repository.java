package pacote.modelo;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import pacote.cdi.Transacao;

public class ImagemBase64Repository implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	@Transacao
	public void inserir(ImagemBase64 base64) {
		manager.persist(base64);
	}

	public ImagemBase64 porId(int id) {
		return manager.find(ImagemBase64.class, id);
	}

	public List<ImagemBase64> listar() {
		TypedQuery<ImagemBase64> query = manager.createQuery("from ImagemBase64", ImagemBase64.class);
		return query.getResultList();
	}
}
