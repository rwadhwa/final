package exceptions;

import rocketDomain.RateDomainModel;

public class RateException extends Exception {

private RateDomainModel rateDomainModel = null;
	public RateException(RateDomainModel rateDomainModel) {
		this.rateDomainModel = rateDomainModel;
	}
	public RateDomainModel getRateDomainModel() {
		return rateDomainModel;
	}	
}

