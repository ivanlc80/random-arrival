package com.ilecreurer.shapley.randomarrival;

import java.math.BigInteger;

/**
 * Single thread implementation of the random arrival rule for claims problems.
 * @author ilecreurer
 */
public class SingleThreadProg {

	public static void main(String[] args) {
		try {
			System.out.println("Entering SingleThreadProg...");
			if (args.length != 2) {
				throw new Exception("Invalid params");
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

			double [] ra = new double[n];
			BigInteger max = BigInteger.valueOf(2).pow(n-1);
			BigInteger oneMillion = BigInteger.valueOf(1000000);

			System.out.println("max: " + max.toString());

			Object monitor = new Object();
			for (BigInteger s = BigInteger.ONE; s.compareTo(max) == -1 ; s = s.add(BigInteger.ONE)) {

				double [] coal = BitGet.getDoubleArray(s, n);

				double vs = E - D + Utils.scalarProduct(demands, coal);
				double vcs = 2 * E - D - vs;

				if (vs < 0) {
					vs = 0;
				}

				if (vcs < 0) {
					vcs = 0;
				}

				if (bPrint)
					System.out.println("vs=" + vs + " vcs=" + vcs);

				int bitCount = s.bitCount();

				for (int ii = 0; ii < n; ii++) {
					if (coal[ii] == 1.0) {
						coal[ii] = auxSm[bitCount - 1];
					} else {
						coal[ii] = - auxSm[bitCount];
					}
				}

				if (bPrint)
					System.out.println("s=" + s.toString() + " " + Utils.printDoubleArray(coal));

				double deltaVs = vs - vcs;

				for (int ii = 0; ii < n; ii++) {
					synchronized(monitor) {
						ra[ii] = ra[ii] + deltaVs * coal[ii];
					}
				}

			}

			for (int ii = 0; ii < n; ii++) {
				ra[ii] = ra[ii] + (E / n);
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
