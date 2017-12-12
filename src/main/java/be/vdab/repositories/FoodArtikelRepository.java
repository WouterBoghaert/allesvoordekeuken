package be.vdab.repositories;

import be.vdab.entities.FoodArtikel;

public class FoodArtikelRepository extends AbstractRepository {
	public void create(FoodArtikel foodArtikel) {
		getEntityManager().persist(foodArtikel);
	}
}
