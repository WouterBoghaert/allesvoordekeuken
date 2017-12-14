package be.vdab.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.persistence.PersistenceException;

import be.vdab.entities.Artikel;
import be.vdab.repositories.ArtikelRepository;
import vdab.be.exceptions.ArtikelBestaatAlException;

public class ArtikelService extends AbstractService {
	private final ArtikelRepository artikelRepository = new ArtikelRepository();
	
	public Optional<Artikel> read(long id) {
		return artikelRepository.read(id);
	}
	
	public void create(Artikel artikel) {
		if(artikelRepository.findByNaam(artikel.getNaam()).isPresent()) {
			throw new ArtikelBestaatAlException();
		}
		beginTransaction();
		try {
			artikelRepository.create(artikel);
			commit();
		}
		catch (PersistenceException ex) {
			rollback();
			throw ex;
		}
	}
	
	public List<Artikel> findByLikeName (String naam) {
		return artikelRepository.findByLikeNaam(naam);
	}
	
	public void algemenePrijsVerhoging(BigDecimal percentage) {
		BigDecimal factor = BigDecimal.ONE.add(
			percentage.divide(BigDecimal.valueOf(100)));
		beginTransaction();
		try {
			
			artikelRepository.algemenePrijsVerhoging(factor);
			commit();
		}
		catch(PersistenceException ex) {
			rollback();
			throw ex;
		}
	}
	
	public List<Artikel> findAll() {
		return artikelRepository.findAll();
	}
	
//	public List<Artikel> findByArtikelgroep(Artikelgroep artikelgroep) {
//		return artikelRepository.findByArtikelgroep(artikelgroep);
//	}
	
	public List<Artikel> findAllMetArtikelgroep() {
		return artikelRepository.findAllMetArtikelgroep();
	}
}
