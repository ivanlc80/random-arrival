# random-arrival

## Description

The program computes the random arrival rule for the claims problem (E,d),
where E is the resource to be divided and d is the vector of claims.

This implementation follows the pseudo-code of "Algorithm 3" contained in the following paper:

*On the computation of the Shapley value and the random arrival rule.*

Authors: 
- Mirás Calvo, Miguel Ángel 
- Núñez Lugilde, Iago
- Quinteiro Sandomingo, Carmen
- Sánchez-Rodríguez, Estela.


Available at SSRN: 
https://papers.ssrn.com/sol3/papers.cfm?abstract_id=4293746

## How to build

```shell
mvn clean package
```

## How to run

* Single threaded implementation

```shell
java -cp target/randomarrival-1.0.0.jar com.ilecreurer.shapley.randomarrival.SingleThreadProg <E> <d as comma separated values>
```

eg: E=10 d=5,6,7
```shell
java -cp target/randomarrival-1.0.0.jar com.ilecreurer.shapley.randomarrival.SingleThreadProg 10 5,6,7
```

* Multi threaded implementation (16 threads)
```shell
java -cp target/randomarrival-1.0.0.jar com.ilecreurer.shapley.randomarrival.MultiThreadProg <E> <d as comma separated values>
```

For the following 27 player game the program takes roughly 2.1s to calculate: 
```shell
java -cp target/randomarrival-1.0.0.jar com.ilecreurer.shapley.randomarrival.MultiThreadProg 721214.5 15720.6600000000,25532.2000000000,32960.4400000000,13664.6100000000,8173.76000000000,3904.17000000000,14869.0400000000,289753.130000000,250962.130000000,126857.130000000,248338.500000000,227091.640000000,63069.7200000000,15915.9800000000,10059.7200000000,530070.440000000,121229.150000000,233163.450000000,248008.450000000,169534.830000000,240404.840000000,250845.440000000,70752.9600000000,140679.050000000,227684.440000000,234125.140000000,264726.580000000
```
