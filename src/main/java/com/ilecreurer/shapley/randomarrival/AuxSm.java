package com.ilecreurer.shapley.randomarrival;

/**
 * AuxSm class.
 * @author ilecreurer
 */
public class AuxSm {

	/**
	 * Method to obtain the Auxiliary Shapley array.
	 * @param n the number of elements.
	 * @return double[] representing the auxiliary Shapley matrix.
	 * @throws IllegalArgumentException when n has too few elements.
	 */
	public static double [] getAuxSm(int n) throws IllegalArgumentException {
		if (n < 2) throw new IllegalArgumentException("n=" + n + " is too small");
		int m = Math.round((float)n/2);

		double[] arValues = new double[m];
		arValues[0] = (double)1/(double)n;

		int k = 1;
		for (int i = 1; i < arValues.length; i++) {
			arValues[i] = arValues[i - 1] * (double)i / (n - k);
			k++;
		}

		double [] arValuesAug = new double[n];
		if (n % 2 == 0) { // even
			for (int i = 0; i < arValues.length; i++) {
				arValuesAug[i] = arValues[i];
				arValuesAug[arValuesAug.length - 1 - i] = arValues[i];
			}
		} else {
			for (int i = 0; i < arValues.length; i++) {
				arValuesAug[i] = arValues[i];
				if (i < arValues.length - 1) {
					arValuesAug[arValuesAug.length - 1 - i] = arValues[i];
				}
			}
		}

		return arValuesAug;
	}
}
