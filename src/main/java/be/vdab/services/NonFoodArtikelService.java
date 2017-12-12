package be.vdab.services;

import javax.persistence.PersistenceException;

import be.vdab.entities.NonFoodArtikel;
import be.vdab.repositories.NonFoodArtikelRepository;

public class NonFoodArtikelService extends AbstractService {
	private final NonFoodArtikelRepository nonFoodArtikelRepository = new NonFoodArtikelRepository();
	
	public void create(NonFoodArtikel nonFoodArtikel) {
		beginTransaction();
		try {
			nonFoodArtikelRepository.create(nonFoodArtikel);
			commit();
		}
		catch(PersistenceException ex) {
			rollback();
			throw ex;
		}
	}
}
