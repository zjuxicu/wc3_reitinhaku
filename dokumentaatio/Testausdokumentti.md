# Testausdokumentti

Yksikkötestaus on toteutettu jUnitilla, ja testit voi ajaa komennolla 
```
./gradlew test
```
[Codecovista](https://codecov.io/gh/zjuxicu/wc3_reitinhaku) tarkemmat tiedot testauksen rivikattavuuksista.

Käsin tapahtuvaa testausta voi suorittaa valitsemalla yhden kartan kerrallaan, tai käyttää valmista testeriä, joka suorittaa useamman haun samalla kartalla ja tarjoaa vertailudataa eri algoritmien toiminnasta ja tehokkuudesta. Tulokset ohjelma kirjaa png-tiedostoon nimellä "lähtö"-"algoritmi"-"maali". Kartan ja reitin graafisessa esityksessä värit ovat melko yksiselitteisiä, sininen vettä, puut vihreää, maa ruskeaa ja ei kuljettavissa olevat alueet mustaa.

Suorituskykytesteistä lisää [toteutusdokumentin](https://github.com/zjuxicu/wc3_reitinhaku/blob/main/dokumentaatio/Toteutusdokumentti.md) puolella.

