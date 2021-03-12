# Toteutusdokumentti

Ohjelma vertailee eri reitinhakualgoritmien toimintaa.

## Rakenne
Ohjelman rakennetta vastaa seuraava puu:

```bash
├── algoritmit
│   ├── AStar.java
│   ├── Djikstra.java
│   ├── JPS.java
│   ├── Koordinaatti.java
│   └── Solmu.java
├── kartta
│   ├── Kartanpiirturi.java
│   ├── Kartta.java
│   └── Kartanlaturi.java
├── kayttoliittyma
│   ├── Algoritminvalitsija.java
│   ├── Kayttoliittyma.java
│   └── Tester.java
├── tietorakenteet
│   ├── Jono.java
│   ├── Keko.java
│   └── Lista.java
├── Main.java
└── resources

```
## Saavutetut aika- ja tilavaativuudet

| Luokka        | Aikavaativuus | Tilavaativuus  |
| :-----------: |:-------------:| :-------------:|
| Jono          |   O(n)        |   O(n)         |
| Keko          |   O(n log n)  |   O(n)         |     
| Lista         |   O(n)        |   O(n)         |
| Dijkstra      |   O(n^2)      |   O(n)         |
| A*            |   O(n^2)      |   O(n^b)       |
| JPS           |   O(n^2)      |   O(n^b)       |

Vaativuudet perustuvat "pahimpaan mahdolliseen tapaukseen".
Kartan lataamiseen kuluu keskimäärin ~0.1 sekuntia ja se tapahtuu myös ajassa O(n), jossa n on kartan koko.
A* ja JPS tilavaativuuksissa b on maksimi haarautuvuustekijä.

## Suorituskyky- ja O-analyysivertailu

Suorituskykytesteissä käytettiin karttaa "losttemple" ja lähtö- ja maalikoordinaatit arvottiin 1000 kertaa.
Aikaa ohjelman suorittamiseen kului yhteensä 4min 16sek (ohjelman suorituksen nopeuttamiseksi tiedostoon tallennus oli otettu pois käytöstä, tällä ei ole vaikutusta algoritmien suoritusaikaan).

|Algoritmi|Tutkitut solmut| Kulunut aika|
|:-------:|:-------------:|:-----------:|
|Dijkstra |   30566.557   | 0.005749    |
|A*       |   6926.895    | 0.002179    |
|JPS      |   37.189      | 0.000647    |

Kartta "riverrun" ja testeri 1000 kertaa.
Aikaa ohjelman suorittamiseen kului yhteensä 4min 23sek.

|Algoritmi|Tutkitut solmut| Kulunut aika|
|:-------:|:-------------:|:-----------:|
|Dijkstra |   49454.223   | 0.009628    |
|A*       |   19129.184   | 0.005581    |
|JPS      |   119.99      | 0.000991    |

Kartta "heart2heart" ja testeri 5000 kertaa.
Aikaa ohjelman suorittamiseen kului yhteensä 21min 8sek.

|Algoritmi|Tutkitut solmut| Kulunut aika|
|:-------:|:-------------:|:-----------:|
|Dijkstra |   55008.453   | 0.009830    |
|A*       |   33009.92    | 0.009109    |
|JPS      |   115.128     | 0.001597    |

Vaikka aika- ja tilavaativuudet ovat algoritmeillä samat, pahin mahdollinen tapaus tulee vastaan
A*- ja JPS-algoritmeillä erittäin harvoin. Mitä hankalammaksi kartta menee, A* hidastuu huomattavasti ja 
lähestyy jo Dijkstran mittaustuloksia. 

## Työn mahdolliset puutteet ja parannusehdotukset

JPS ei aina löydä lyhintä reittiä. Dijkstra ja A* palauttavat aina yhtä pitkän reitin, pieni pyöristysvirhe on mahdollinen.
Dijkstran aikavaativuutta on mahdollisuus parantaa.

Warcraft III pelissä on myös lentäviä hahmoja jotka pääsevät esteiden yli, sekä eri liikkumisnopeuden omaavia hahmoja. Nyt ohjelma selvittää vain lyhyimmän reitin ilman nopeuksia.

## Lähteet
https://www.movingai.com/benchmarks/wc3maps512/index.html
http://theory.stanford.edu/~amitp/GameProgramming/AStarComparison.html
https://en.wikipedia.org/wiki/Jump_point_search
https://www.researchgate.net/publication/287338108_Improving_jump_point_search
https://github.com/kevinsheehan/jps
https://github.com/ClintFMullins/JumpPointSearch-Java
https://www.youtube.com/watch?v=wNOoyZ45SmQ
https://en.wikipedia.org/wiki/A*_search_algorithm
https://github.com/TiraLabra/Testing-and-rmq