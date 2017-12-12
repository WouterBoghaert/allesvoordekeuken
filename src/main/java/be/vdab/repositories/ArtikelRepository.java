package be.vdab.repositories;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import be.vdab.entities.Artikel;

public class ArtikelRepository extends AbstractRepository {
	public Optional<Artikel> read(long id) {
		return Optional.ofNullable(getEntityManager().find(Artikel.class, id));
	}
	
	public void create(Artikel artikel) {
		getEntityManager().persist(artikel);
	}
	
	public List<Artikel> findByLikeNaam(String naam) {
		return getEntityManager().createNamedQuery("Artikel.findByLikeNaam", Artikel.class)
			.setParameter("naam", "%" + naam + "%")
			.getResultList();
	}
	
	public void algemenePrijsVerhoging(BigDecimal factor) {
		getEntityManager().createNamedQuery("Artikel.algemenePrijsVerhoging")
			.setParameter("factor", factor)
			.executeUpdate();
	}
}
