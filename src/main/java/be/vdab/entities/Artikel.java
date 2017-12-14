package be.vdab.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import be.vdab.valueobjects.Korting;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "artikels")
@DiscriminatorColumn(name="soort")
@NamedEntityGraph(name= Artikel.MET_ARTIKELGROEP,
	attributeNodes = @NamedAttributeNode("artikelgroep"))
public abstract class Artikel implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String MET_ARTIKELGROEP = "Artikel.metArtikelgroep";
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private long id;
	private String naam;
	private BigDecimal aankoopprijs;
	private BigDecimal verkoopprijs;
	private static final BigDecimal MINIMUM_AANKOOPPRIJS = BigDecimal.valueOf(0.01);
	@ElementCollection
	@CollectionTable(name="kortingen",
		joinColumns = @JoinColumn(name="artikelid"))
	@OrderBy("vanafAantal")
	private Set<Korting> kortingen;
	@ManyToOne(fetch= FetchType.LAZY, optional = false)
	@JoinColumn(name="artikelgroepid")
	private Artikelgroep artikelgroep;
	
	protected Artikel() {}
	
	public Artikel(String naam, BigDecimal aankoopprijs, BigDecimal verkoopprijs, Artikelgroep artikelgroep) {
		setNaam(naam);
		setAankoopprijs(aankoopprijs);
		setVerkoopprijs(verkoopprijs);
		kortingen = new LinkedHashSet<>();
		setArtikelgroep(artikelgroep);
	}
	
	public static boolean isNaamValid(String naam) {
		return naam != null && !naam.trim().isEmpty();
	}
	
	public static boolean isAankoopprijsValid(BigDecimal aankoopprijs) {
		return aankoopprijs != null && aankoopprijs.compareTo(MINIMUM_AANKOOPPRIJS) >= 0;
	}
	
	public static boolean isVerkoopprijsValid(BigDecimal verkoopprijs, BigDecimal aankoopprijs) {
		return verkoopprijs != null && aankoopprijs != null && verkoopprijs.compareTo(aankoopprijs) >= 0;
	}
	
	public void setNaam(String naam) {
		if(!isNaamValid(naam)) {
			throw new IllegalArgumentException();
		}
		this.naam = naam;
	}
	
	public void setAankoopprijs(BigDecimal aankoopprijs) {
		if(!isAankoopprijsValid(aankoopprijs)) {
			throw new IllegalArgumentException();
		}
		this.aankoopprijs = aankoopprijs;
	}
	
	public void setVerkoopprijs(BigDecimal verkoopprijs) {
		if(!isVerkoopprijsValid(verkoopprijs,aankoopprijs)) {
			throw new IllegalArgumentException();
		}
		this.verkoopprijs = verkoopprijs;
	}
	
	public long getId() {
		return id;
	}
	public String getNaam() {
		return naam;
	}
	public BigDecimal getAankoopprijs() {
		return aankoopprijs;
	}
	public BigDecimal getVerkoopprijs() {
		return verkoopprijs;
	}	
	
	public BigDecimal getWinstPercentage() {
		return verkoopprijs.subtract(aankoopprijs)
			.divide(aankoopprijs, 2, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));
	}
	
	public Set<Korting> getKortingen() {
		return Collections.unmodifiableSet(kortingen);
	}
	
	public void add(Korting korting) {
		kortingen.add(korting);
	}
	
	public void remove(Korting korting) {
		kortingen.remove(korting);
	}
	
	public void setArtikelgroep(Artikelgroep artikelgroep) {
		if(this.artikelgroep != null && this.artikelgroep.getArtikels().contains(this)) {
			this.artikelgroep.removeArtikel(this);
		}		
		this.artikelgroep = artikelgroep;
		if(artikelgroep != null && ! artikelgroep.getArtikels().contains(this)) {
			artikelgroep.addArtikel(this);
		}
	}
	
	public Artikelgroep getArtikelgroep() {
		return artikelgroep;
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
		if (!(obj instanceof Artikel))
			return false;
		Artikel other = (Artikel) obj;
		if (naam == null) {
			if (other.naam != null)
				return false;
		} else if (!naam.equals(other.naam))
			return false;
		return true;
	}
}
