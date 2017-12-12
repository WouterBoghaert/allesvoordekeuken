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
		int houdbaarheid) {
		super(naam, aankoopprijs, verkoopprijs);
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
}
