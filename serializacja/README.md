# Serializacja benchmark

Porównywane metody:

* Serializable
* JSON – Jackson2
* GSON
* JAX-B

Ilość serializowanych obiektów

* Jeden obiekt
* Lista zawierająca 10 obiektów
* Lista zawierająca 10 tys obiektów


Wynik

`mvn compile exec:java`


## jeden obiekt: 

![one](resources/c.png)


## Lista 10 obiektów: 

![one](resources/b.png)

## Lista 10 tys. obiektów: 

![one](resources/a.png)