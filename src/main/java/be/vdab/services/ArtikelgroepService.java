package be.vdab.services;

import java.util.List;
import java.util.Optional;

import be.vdab.entities.Artikelgroep;
import be.vdab.repositories.ArtikelgroepRepository;

public class ArtikelgroepService extends AbstractService {
	private final transient ArtikelgroepRepository artikelgroepRepository
		= new ArtikelgroepRepository();
	
	public Optional<Artikelgroep> read(long id) {
		return artikelgroepRepository.read(id);
	}
	
	public List<Artikelgroep> findAll() {
		return artikelgroepRepository.findAll();
	}
}
