# Testausdokumentti

Yksikkötestaus on toteutettu jUnitilla, ja testit voi ajaa komennolla 
```
./gradlew test
```
[Codecovista](https://codecov.io/gh/zjuxicu/wc3_reitinhaku) tarkemmat tiedot testauksen rivikattavuuksista.

Käsin tapahtuvaa testausta voi suorittaa valitsemalla yhden kartan kerrallaan, tai käyttää valmista testeriä, joka suorittaa useamman haun samalla kartalla ja tarjoaa vertailudataa eri algoritmien toiminnasta ja tehokkuudesta. Tulokset ohjelma kirjaa png-tiedostoon nimellä "lähtö"-"algoritmi"-"maali". Kartan ja reitin graafisessa esityksessä värit ovat melko yksiselitteisiä, sininen vettä, puut vihreää, maa ruskeaa ja ei kuljettavissa olevat alueet mustaa. "Testeri" luokan käyttö ohjelman käyttämiseen on suotavaa, tämä onnistuu ohjelman kysyessä kartannimeä syöttämällä ohjelmalle merkkijonon "testeri".

Reittien oikeellisuutta testaa "VertailuTest", joka vertaa A*:n ja JPS:n löytämiä polkuja Dijkstran löytämään polkuun. Olettaen että Dijkstra tarjoaa varmat tulokset, voidaan testien tuloksista havaita, että A* toimii myös aina oikein (pieniä pyöristysvirheitä saattaa olla). JPS pidemmillä matkoilla tekee ylimääräisiä mutkia, eikä aina palauta lyhintä reittiä. Mittaustuloksista kuitenkin voidaan havaita JPS:n olevan huomattavasti tehokkaampi algoritmi kuin muut vertailun jäsenet. Tehokkuudella tässä tarkoitetaan algoritmin suoritukseen kulunutta aikaa, sekä käsiteltyjen solmujen määrää.

Ohessa vielä kuvankaappaukset Jacocon rivikattavuusraportista:

![](https://github.com/zjuxicu/wc3_reitinhaku/blob/main/dokumentaatio/jacoco.png)

![](https://github.com/zjuxicu/wc3_reitinhaku/blob/main/dokumentaatio/jacoco1.png)

![](https://github.com/zjuxicu/wc3_reitinhaku/blob/main/dokumentaatio/jacoco2.png)

![](https://github.com/zjuxicu/wc3_reitinhaku/blob/main/dokumentaatio/jacoco3.png)

Suorituskykytesteistä lisää [toteutusdokumentin](https://github.com/zjuxicu/wc3_reitinhaku/blob/main/dokumentaatio/Toteutusdokumentti.md) puolella.



