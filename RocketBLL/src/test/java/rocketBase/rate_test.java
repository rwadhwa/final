package rocketBase;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import exceptions.RateException;
import rocketDomain.RateDomainModel;

public class rate_test {

	// TODO - RocketBLL rate_test
	// Check to see if a known credit score returns a known interest rate
	@Test
	public void test() throws Exception {
		ArrayList<RateDomainModel> rates = RateDAL.getAllRates();
		int creditScore = 700;
		double rate = 0.0;
		double payment = 0.0;
		try {
			// get rate per year and convert to rate per month
			rate = RateBLL.getRate(creditScore) / (100 * 12);
			System.out.println(rate);
		} catch (RateException e) {
			throw e;
		}

		// Calculate PITI calculation for given set of data

		payment = rocketBase.RateBLL.getPayment(rate, 360.0, 300000.0, 0.0, false);
		System.out.println(Math.round(payment * 100.00) / 100.00);

		assertTrue(Math.round(payment * 100.00) / 100.00 == 1432.25);
	}

	// TODO - RocketBLL rate_test
	// Check to see if a RateException is thrown if there are no rates for a
	// given credit score
	@Test(expected = RateException.class)
	public void testException() throws Exception {
		ArrayList<RateDomainModel> rates = RateDAL.getAllRates();
		int creditScore = 550;
		try {
			double rate = RateBLL.getRate(creditScore);
			System.out.println(rate);
		} catch (RateException e) {
			throw e;
		}
	}

}