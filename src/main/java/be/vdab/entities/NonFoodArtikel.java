package be.vdab.entities;

import java.math.BigDecimal;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("NF")
public class NonFoodArtikel extends Artikel {
	private static final long serialVersionUID = 1L;
	private int garantie;
	
	protected NonFoodArtikel() {}
	
	public NonFoodArtikel(String naam, BigDecimal aankoopprijs, BigDecimal verkoopprijs,
		int garantie, Artikelgroep artikelgroep) {
		super(naam, aankoopprijs, verkoopprijs, artikelgroep);
		setGarantie(garantie);
	}
	
	public static boolean isGarantieValid(int garantie) {
		return garantie >= 0;
	}
	
	public void setGarantie(int garantie) {
		if(!isGarantieValid(garantie)) {
			throw new IllegalArgumentException();
		}
		this.garantie = garantie;
	}
	
	public int getGarantie() {
		return garantie;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + garantie;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof NonFoodArtikel))
			return false;
		NonFoodArtikel other = (NonFoodArtikel) obj;
		if (garantie != other.garantie)
			return false;
		return true;
	}
}
