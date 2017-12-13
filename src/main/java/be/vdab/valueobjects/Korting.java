package be.vdab.valueobjects;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Embeddable;

@Embeddable
public class Korting implements Serializable {
	private static final long serialVersionUID = 1L;
	private int vanafAantal;
	private BigDecimal kortingsPercentage;
	
	public Korting (int vanafAantal, BigDecimal kortingsPercentage) {
		this.vanafAantal = vanafAantal;
		this.kortingsPercentage = kortingsPercentage;
	}
	
	protected Korting() {}

	public int getVanafAantal() {
		return vanafAantal;
	}

	public BigDecimal getKortingsPercentage() {
		return kortingsPercentage;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((kortingsPercentage == null) ? 0 : kortingsPercentage.hashCode());
		result = prime * result + vanafAantal;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Korting other = (Korting) obj;
		if (kortingsPercentage == null) {
			if (other.kortingsPercentage != null)
				return false;
		} else if (!kortingsPercentage.equals(other.kortingsPercentage))
			return false;
		if (vanafAantal != other.vanafAantal)
			return false;
		return true;
	}
	
	
}
