package rocketBase;

import java.util.ArrayList;

import org.apache.poi.ss.formula.functions.*;

import exceptions.RateException;
import rocketDomain.RateDomainModel;

public class RateBLL {

	private static RateDAL _RateDAL = new RateDAL();

	public static double getRate(int GivenCreditScore) throws RateException {
    //get all the rates from database
	ArrayList<RateDomainModel> rates = _RateDAL.getAllRates();
	RateDomainModel rdmRate = null;
	double dRate = -1;
	//iterate through all the rates fetched from db
	if(rates != null && rates.size() > 0) {
		for (RateDomainModel rate : rates) {
			rdmRate = rate;
		    // the rates that are fetched are ordered by the credit score
			if (rate.getiMinCreditScore() == GivenCreditScore) {
				//the first match is the rate that would be applied for this score
				dRate = rate.getdInterestRate();
				//set the interest rate and exit loop as further loop is not required.
				break;
			}
		}
 	    if (dRate > 0) {
 	    	//return determined rate
			return dRate;
			
		} else {
			//log and throw exception if there is no matching rate for the given credit score
			System.out.println("No matching data found for this creditscore");
			throw new RateException("No matching data found for this creditscore",rdmRate);
		}
	}
	else
	{
		//log and throw exception when there are no rates records in db
		System.out.println("No data returned");
		throw new RateException("No data found for rate");
	}
	}

	public static double getPayment(double r, double n, double p, double f, boolean t) {
		return FinanceLib.pmt(r, n, p, f, t);
	}
}