# wc3_reitinhaku

[Viikkoraportit](https://github.com/zjuxicu/wc3_reitinhaku/tree/main/dokumentaatio/viikkoraportit)  
[Määrittelydokumentti](https://github.com/zjuxicu/wc3_reitinhaku/blob/main/dokumentaatio/maarittely.md)

## Käyttöohjeet
Käynnistä ohjelma komennolla
```
./gradlew run
```
Ykiskkötestit suoritetaan komennolla
```
./gradlew test
```
Jacoco Testikattavuusraportin saat suorittamalla komennon
```
./gradlew test jacocoTestReport
```
Suorituskykytestejä voi ajaa ohjelman ollessa käynnissä komennolla,
```
testeri
```
joka arpoo käytettävän kartan, minkä jälkeen ohjelma kysyy montako kertaa testit suoritetaan satunnaisilla koordinaateilla.

Jos haluat valita kartan ja koordinaatit käsin, niin ohjeita ja esimerkkisyötteitä saat kirjoittamalla
```
ohjeet
```
Kartannimi annetaan ohjelmalle ilman .map-päätettä. Ohjelma kertoo löytyikö karttaa, jonka jälkeen pyytää käyttäjää syöttämään vaaditut koordinaatit.
[Movin ai labsin](https://www.movingai.com/benchmarks/wc3maps512/index.html) karttoja vastaavat koordinaatit 0-511. Koordinaatit syötetään ohjelmalle yksi kerrallaan.

Listauksen saatavilla olevista kartoista saat kirjoittamalla
```
lista
```


Halutessasi satunnaisen kartan, kirjoita
```
satunnainen
```

![GitHub Actions](https://github.com/zjuxicu/wc3_reitinhaku/workflows/Java%20CI%20with%20Gradle/badge.svg)
[![codecov](https://codecov.io/gh/zjuxicu/wc3_reitinhaku/branch/main/graph/badge.svg?token=OYK8FZG9FI)](https://codecov.io/gh/zjuxicu/wc3_reitinhaku)
