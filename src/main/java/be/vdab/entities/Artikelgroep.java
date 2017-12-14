package be.vdab.entities;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name="artikelgroepen")
public class Artikelgroep implements Serializable {
	private static final long serialVersionUID = 1L;
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long id;
	private String naam;
	@OneToMany(mappedBy="artikelgroep")
	@OrderBy("naam")
	private Set<Artikel> artikels = new LinkedHashSet<>();
	
	public long getId() {
		return id;
	}
	
	public String getNaam() {
		return naam;
	}
	
	public Set<Artikel> getArtikels() {
		return Collections.unmodifiableSet(artikels);
	}
	
	public void addArtikel(Artikel artikel) {
		artikels.add(artikel);
		if(artikel.getArtikelgroep() != this) {
			artikel.setArtikelgroep(this);
		}
	}
	
	public void removeArtikel(Artikel artikel) {
		artikels.remove(artikel);
		if(artikel.getArtikelgroep() == this) {
			artikel.setArtikelgroep(null);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((naam == null) ? 0 : naam.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Artikelgroep))
			return false;
		Artikelgroep other = (Artikelgroep) obj;
		if (naam == null) {
			if (other.naam != null)
				return false;
		} else if (!naam.equals(other.naam))
			return false;
		return true;
	}
	
	
}
