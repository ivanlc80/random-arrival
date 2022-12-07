# random-arrival

## Description

The program computes the random arrival rule for the claims problem (E,d),
where E is the resource to be divided and d is the vector of claims.

This implementation follows the pseudo-code of "Algorithm 3" contained in the following paper:

*On the computation of the Shapley value and the random arrival rule.*

Authors: 
Mirás Calvo, Miguel Ángel and Nuñez Lugilde, Iago and Quinteiro
Sandomingo, Carmen and Sanchez-Rodriguez, Estela, 

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