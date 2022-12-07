package com.ilecreurer.shapley.randomarrival;

import java.math.BigInteger;

/**
 * WorkerThread class.
 * @author ilecreurer
 */
public class WorkerThread extends Thread {

	private static BigInteger TWO = BigInteger.valueOf(2);

	private int indexThread;
	private int nThreads;
	private int n;
	private double [] raLocal;
	private BigInteger max;
	private double E;
	private double D;
	private double[] demands;
	private double [] auxSm;
	private boolean bPrint;

	/**
	 * Constructor.
	 * @param indexThread the thread index.
	 * @param nThreads the total number of threads used.
	 * @param n the number of demands.
	 * @param max the maximum value of the main loop.
	 * @param E the endowment value.
	 * @param D the sum of all the demands.
	 * @param demands the array of demands.
	 * @param auxSm the Shapley auxiliary matrix.
	 * @param bPrint boolean indicating to print or not values to console.
	 */
	public WorkerThread(int indexThread, int nThreads, int n, BigInteger max,
			double E, double D, double[] demands,
			double [] auxSm, boolean bPrint) {
		this.indexThread = indexThread;
		this.nThreads = nThreads;
		this.n = n;
		raLocal = new double[n];
		this.max = max;

		this.E = E;
		this.D = D;
		this.demands = demands;
		this.auxSm = auxSm;
		this.bPrint = bPrint;
	}

	/**
	 * Method to run the thread.
	 */
	@Override
	public void run() {
		System.out.println("Starting thread " + indexThread);

		BigInteger biStart = BigInteger.valueOf(indexThread + 1);
		BigInteger biThreads = BigInteger.valueOf(nThreads);

		long count = 0;
		for (BigInteger s = biStart; s.compareTo(max) == -1 ; s = s.add(biThreads)) {

			double [] coal = BitGet.getDoubleArray(s, n);

			double vs = E - D + Utils.scalarProduct(demands, coal);
			double vcs = 2 * E - D - vs;

			if (vs < 0) {
				vs = 0;
			}

			if (vcs < 0) {
				vcs = 0;
			}

			/*
			if (bPrint)
				System.out.println("[" + this.getName() + "] vs=" + vs + " vcs=" + vcs);
*/
			int bitCount = s.bitCount();

			for (int ii = 0; ii < n; ii++) {
				if (coal[ii] == 1.0) {
					coal[ii] = auxSm[bitCount - 1];
				} else {
					coal[ii] = - auxSm[bitCount];
				}
			}

			/*
			if (bPrint)
				System.out.println("[" + this.getName() + "] s=" + s.toString() + " " + Utils.printDoubleArray(coal));
*/
			double deltaVs = vs - vcs;

			for (int ii = 0; ii < n; ii++) {
				raLocal[ii] = raLocal[ii] + deltaVs * coal[ii];
			}

			/*
			if (count % 1_000_000_000 == 0) {
				System.out.println("Thread [" + indexThread + "] s = " + s.toString());
			}
			count++;
			*/
		}
	}

	public double [] getRaLocal() {
		return this.raLocal;
	}

}
