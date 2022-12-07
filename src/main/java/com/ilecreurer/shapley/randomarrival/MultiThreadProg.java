package com.ilecreurer.shapley.randomarrival;

import java.math.BigInteger;

/**
 * Multi thread implementation of the random arrival rule for claims problems.
 * @author ilecreurer
 */
public class MultiThreadProg {

	private static final int nThreads = 16;

	public static void main(String[] args) {
		try {
			System.out.println("Entering MultiThreadProg...");
			if (args.length != 2) {
				throw new Exception("Invalid params. Two parameters are required: E value1,...,valueN");
			}

			boolean bPrint = false;
			long t0 = System.currentTimeMillis();
			long t1 = t0;

			double E = Float.parseFloat(args[0]);
			System.out.println("E = " + E);

			String [] ar = args[1].split(",");
			double[] demands = new double[ar.length];

			System.out.print("Demands: ");
			for (int i = 0; i < ar.length; i++) {
				demands[i] = Float.parseFloat(ar[i]);
			}

			System.out.println(Utils.printDoubleArray(demands));

			int n = demands.length;
			int m = Math.round((float)n/2);
			double D = Utils.sum(demands);
			System.out.println("n=" + n + " m=" + m + " D=" + D);

			double [] auxSm = AuxSm.getAuxSm(n);
			System.out.println(Utils.printDoubleArray(auxSm));

			// Random arrival array.
			double [] ra = new double[n];
			BigInteger max = BigInteger.valueOf(2).pow(n-1);

			System.out.println("max: " + max.toString());

			System.out.println("Number of threads:" + nThreads);
			WorkerThread[] arWorkerThread = new WorkerThread[nThreads];
			for (int i = 0; i < nThreads; i++) {
				arWorkerThread[i] = new WorkerThread(i, nThreads,
						n,  max,
						E, D, demands,
						auxSm, bPrint);
			}

			for (int i = 0; i < nThreads; i++) {
				arWorkerThread[i].start();
			}

			for (int i = 0; i < nThreads; i++) {
				arWorkerThread[i].join();
			}

			// Update ra array by summing each thread's corresponding ii'th element.
			for (int ii = 0; ii < n; ii++) {
				ra[ii] = (E / n);

				for (int i = 0; i < nThreads; i++) {
					ra[ii] = ra[ii] + arWorkerThread[i].getRaLocal()[ii];
				}
			}

			t1 = System.currentTimeMillis();

			long deltaTime = t1-t0;

			System.out.println("\n");
			System.out.println("delta time:" + deltaTime);
			System.out.println("ra: [" + Utils.printDoubleArray(ra) + "]");
			System.out.println("sum:" + Utils.sum(ra));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
