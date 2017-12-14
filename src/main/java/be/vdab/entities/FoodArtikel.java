package be.vdab.entities;

import java.math.BigDecimal;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("F")
public class FoodArtikel extends Artikel {
	private static final long serialVersionUID = 1L;
	private int houdbaarheid;
	
	protected FoodArtikel () {}
	
	public FoodArtikel(String naam, BigDecimal aankoopprijs, BigDecimal verkoopprijs,
		int houdbaarheid, Artikelgroep artikelgroep) {
		super(naam, aankoopprijs, verkoopprijs, artikelgroep);
		setHoudbaarheid(houdbaarheid);
	}
	
	public static boolean isHoudbaarheidValid(int houdbaarheid) {
		return houdbaarheid > 0;
	}
	
	public void setHoudbaarheid(int houdbaarheid) {
		if(!isHoudbaarheidValid(houdbaarheid)) {
			throw new IllegalArgumentException();
		}
		this.houdbaarheid = houdbaarheid;
	}
	
	public int getHoudbaarheid() {
		return houdbaarheid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + houdbaarheid;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof FoodArtikel))
			return false;
		FoodArtikel other = (FoodArtikel) obj;
		if (houdbaarheid != other.houdbaarheid)
			return false;
		return true;
	}
}
