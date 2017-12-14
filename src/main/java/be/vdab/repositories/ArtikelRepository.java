package be.vdab.repositories;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.persistence.NoResultException;

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
	
	public List<Artikel> findAll() {
		return getEntityManager().createNamedQuery("Artikel.findAll",Artikel.class)
			.getResultList();
	}
	
	public Optional<Artikel> findByNaam(String naam) {
		try {
			return Optional.of(getEntityManager()
				.createNamedQuery("Artikel.findByNaam", Artikel.class)
				.setParameter("naam", naam)
				.getSingleResult());
		}
		catch(NoResultException ex) {
			return Optional.empty();
		}
	}
	
//	public List<Artikel> findByArtikelgroep(Artikelgroep artikelgroep) {
//		return getEntityManager().createNamedQuery("Artikel.findByArtikelgroep",
//			Artikel.class)
//			.setParameter("artikelgroep", artikelgroep)
//			.getResultList();
//	}
	
	public List<Artikel> findAllMetArtikelgroep() {
		return getEntityManager().createNamedQuery("Artikel.findAll", Artikel.class)
			.setHint("javax.persistence.loadgraph",
				getEntityManager().createEntityGraph(Artikel.MET_ARTIKELGROEP))
			.getResultList();
	}
}
