package exceptions;

import rocketDomain.RateDomainModel;

public class RateException extends Exception {

private RateDomainModel rateDomainModel = null;
	public RateException(RateDomainModel rateDomainModel) {
		this.rateDomainModel = rateDomainModel;
	}
	public RateException(String arg0, RateDomainModel rateDomainModel) {
		super(arg0);
		this.rateDomainModel = rateDomainModel;
	}


	public RateException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}


	public RateException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}


	public RateException(String arg0) {
		super(arg0);
	}


	public RateException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}


	public RateDomainModel getRateDomainModel() {
		return rateDomainModel;
	}	
	
}