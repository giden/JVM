# POJO to JSON converter

`mvn compile exec:java`


Obsługa: pola proste publiczne i prywatne, kolekcje

# Testy
Rozgrzewka: 10 wykonań całego benchmarku.
Jest 1000 testów, każdy test zawiera 10000 wywołań. Wyniki są sortowane, usuwane jest 100 skrajnych wartości testów.  
Średnie wyniki:  
* reflection 6731258 ns
* jackson mapper 3630443 ns
