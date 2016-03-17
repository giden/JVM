# POJO to JSON converter

`mvn compile exec:java`


Obsługa: publiczne i prywatne pola proste, tablice, kolekcje

# Testy
Rozgrzewka: 10 wykonań całego benchmarku.
Jest 1000 testów, każdy test zawiera 10000 wywołań. Wyniki są sortowane, usuwane jest 100 skrajnych wartości testów.  
Średnie wyniki:
* reflection 12999068 ns
* jackson mapper 4467270 ns  
Biblioteka Jackson blisko działa blisko 3x szybciej.
