package com.ilecreurer.shapley.randomarrival;

import java.math.BigInteger;

/**
 * BitGet class.
 * @author ilecreurer
 */
public class BitGet {
	/**
	 * Method to get the binary representation as an array of double.
	 * @param bi
	 * @param n
	 * @return double[]
	 * @throws IllegalArgumentException
	 */
	public static double[] getDoubleArray(BigInteger bi, int n) throws IllegalArgumentException {
		if (bi == null) throw new IllegalArgumentException("Param bi is null");
		if (n <= 0) throw new IllegalArgumentException("Param n is non-positive");
		double [] ar = new double[n];

		for (int i = 0; i < n; i++) {
			if (bi.testBit(i)) {
				ar[i] = 1;
			}
		}

		return ar;
	}


	public static void main(String[] args) {

		/*for(long n = 1; n < 63; n++) {
			BigInteger bi = BigInteger.valueOf(n);
			double [] ar = BitGet.getFloatArray(bi, 8);
			System.out.println(printFloatArray(ar));
		}*/

		int n = 10;
		BigInteger bi = BigInteger.valueOf(2);
		System.out.println(Utils.printDoubleArray(getDoubleArray(bi, n)));
	}

}
