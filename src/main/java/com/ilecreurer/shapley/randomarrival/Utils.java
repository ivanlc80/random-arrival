package com.ilecreurer.shapley.randomarrival;

/**
 * Utils class.
 * @author ilecreurer
 */
public class Utils {
	/**
	 * Method to write an array of doubles as a comma separated string
	 * @param ar the array of values.
	 * @return String.
	 * @throws IllegalArgumentException when the array is null.
	 */
	public static String printDoubleArray(final double [] ar) throws IllegalArgumentException {
		if (ar == null) throw new IllegalArgumentException("ar is null");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < ar.length; i++) {
			sb.append(ar[i]);
			if (i < ar.length - 1) sb.append(" ");
		}
		return sb.toString();
	}

	/**
	 * Method to calculate the sum of all the elements of a double array.
	 * @param ar the array of values.
	 * @return double representing the sum.
	 * @throws IllegalArgumentException when the input array is null.
	 */
	public static double sum(final double [] ar) throws IllegalArgumentException {
		if (ar == null) throw new IllegalArgumentException("ar is null");
		double s = 0;
		for (int i = 0; i < ar.length; i++) {
			s = s + ar[i];
		}
		return s;
	}

	/**
	 * Method to calculate the scalar product of two double arrays.
	 * @param ar1 the first array.
	 * @param ar2 the second array.
	 * @return double representing the scalar product.
	 * @throws IllegalArgumentException when one of the two input arrays is null or the lengths are not equal.
	 */
	public static double scalarProduct(final double [] ar1, final double [] ar2) throws IllegalArgumentException {
		if (ar1 == null) throw new IllegalArgumentException("ar1 is null");
		if (ar2 == null) throw new IllegalArgumentException("ar2 is null");
		if (ar1.length != ar2.length) throw new IllegalArgumentException("Array length mismatch. " + ar1.length + " != " + ar2.length);
		double s = 0;
		for (int i = 0; i < ar1.length; i++) {
			s = s + ar1[i] * ar2[i];
		}
		return s;
	}
}
