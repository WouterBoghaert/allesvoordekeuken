package be.vdab.services;

import javax.persistence.PersistenceException;

import be.vdab.entities.FoodArtikel;
import be.vdab.repositories.FoodArtikelRepository;

public class FoodArtikelService extends AbstractService {
	private final FoodArtikelRepository foodArtikelRepository = new FoodArtikelRepository();
	
	public void create(FoodArtikel foodArtikel) {
		beginTransaction();
		try  {
			foodArtikelRepository.create(foodArtikel);
			commit();
		}
		catch(PersistenceException ex) {
			rollback();
			throw ex;
		}
	}
}
