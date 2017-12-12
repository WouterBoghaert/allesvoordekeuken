package be.vdab.repositories;

import be.vdab.entities.NonFoodArtikel;

public class NonFoodArtikelRepository extends AbstractRepository {
	public void create(NonFoodArtikel nonFoodArtikel) {
		getEntityManager().persist(nonFoodArtikel);
	}
}
