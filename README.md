# Dámajáték

A feladatban a dámajátékhoz szükséges alapokat fogjuk lefektetni egy
programkönyvtár elkészítésével.

A részfeladatok megoldása során ügyeljünk arra, hogy a megadottakon
kívül egyetlen osztály se tartalmazzon más publikus metódust vagy
adattagot! Bár nem kötelező, célszerű metódusokat *dokumentációs
megjegyzéssel* dokumentálni.

A megoldást egyetlen `zip` állományként kell feltölteni, amely
tartalmazza a csomagoknak megfelelő könyvtárszerkezetben az összes
forráskódot. A fordítás során létrejövő `class` állományokat viszont
nem szabad már mellékelni!

## Lépés irányának ábrázolása

A `position` csomagban hozzuk létre a nyilvános `Direction`
felsorolási típust a következő értékekkel: `UP_LEFT`, `UP_RIGHT`,
`DOWN_LEFT`, `DOWN_RIGHT`.

A `Direction` típuson belül adjunk meg egy osztályszintű
`fromDistance` metódust, mely `Direction`-t ad vissza, és paraméterül
egy (kételemű) egészekből álló tömböt vár. A tömbben rendre a két
játékmező közti vízszintes és függőleges távolság van megadva. Ha a
vízszintes távolság negatív, akkor az irány fölfele balra mutat, ha
pozitív, akkor jobbra. Ha a függőleges távolság negatív, akkor az
irány felfele mutat, ha pozitív, akkor lefele. Tehát az irány
`UP_RIGHT` lesz, ha a vízszintes távolság pozitív, a függőleges
negatív, és így tovább.

Feltesszük, hogy a tömbnek pontosan két eleme van, és a két távolság
közül egyik sem 0, ezeket tehát nem kell ellenőrizni.

## A bábuk pozícióinak ábrázolása

A `position` csomagban hozzuk létre a nyilvános `Position`
osztályt. Az osztály objektumai egy játéktábla pozícióit fogják
ábrázolni.

Az osztályban legyen két nyilvános, `int` típusú, nem módosítható
(`final`) adattag: a vízszintes (`h`) és a függőleges (`v`)
koordináta.

Valósítsuk meg az alábbi nyilvános metódusokat:

  - Egy konstruktort, amely paraméterül várja a vízszintes és
    függőleges koordinátákát, és inicializálja az adattagokat.

  - Az `Object`-ből örökölt `toString` metódust. Ennek az eredménye
    legyen `(h,v)` ahol `h` a vízszintes, `v` a függőleges koordináta.

  - Egy `next` metódust, mely paraméterül vár egy `Direction` értéket,
    és visszaadja az aktuális pozíció szomszédos pozícióját. Ha az
    irány `UP_LEFT`, akkor az új pozícióban eggyel kisebb lesz a
    függőleges és a vízszintes koordináta. Ha az irány `UP_RIGHT`,
    akkor az új pozícióban a függőleges koordináta eggyel kisebb, a
    vízszintes koordináta eggyel nagyobb lesz, és így tovább a maradék
    két esetre.

  - Az `Object`-ből örökölt `equals` metódust, mely csak akkor ad
    vissza logikai igaz értéket, ha a paraméterül kapott `Object`
    referencia `Position` objektumra mutat (`instanceof` segít
    eldönteni), és ennek a `Position` objektumnak a koordinátái
    megegyeznek annak a `Position` objektumnak a koordinátáival,
    amelyre meghívták az `equals`-t. Biztonság kedvéért használjuk az
    `Override` annotációt.

  - Egy `distance` metódust, mely paraméterül vár egy másik pozíciót,
    és visszaadja egy kételemű, egészekből álló tömbben a vízszintes
    és függőleges távolságot az aktuális és a paraméterül kapott
    pozíció között.

  - Egy osztályszintű `isDiagonal` metódust, amely paraméterül vár két
    pozíciót, és csak akkor ad vissza igaz értéket, ha a két pozíció
    egy átlón helyezkedik el. Ehhez számoljuk ki a pozíciók közti
    távolságot (`distance`). A két pozíció egy átlón helyezkedik el,
    ha a függőleges és a vízszintes távolság megegyezik, vagy a kettő
    összege 0.

  - Az `Object`-ből örökölt `hashCode`-t. A visszaadott egész szám
    értéke legyen $100 * v + h$, ahol $v$ a függőleges, $h$ a
    vízszintes koordináta. Biztonság kedvéért használjuk az `Override`
    annotációt.

## A játéktábla ábrázolása

A következőkben a játéktábla interfészét és két megvalósítását fogjuk
megadni. Annak érdekében, hogy minél általánosabb legyen a kód, a
következő osztályok nem csak játéktáblákhoz használhatók, hanem
bármilyen olyan esetben, amikor téglalap alakú rács adatszerkezetre
van szükségünk. Az egyszerűség kedvéért azonban továbbiakban
játéktáblaként hivatkozunk az osztályokra.

### A játéktábla interfésze

A `grid` csomagban hozzuk létre a nyilvános `Grid` interfészt, mely
egy típusparamétert vár. Az interfészen belül deklaráljuk a következő
metódusokat:

  - Egy `isValid` metódust, mely egy `Position`-t vár paraméterül, és
    logikai értéket ad vissza.

  - Egy `get` metódust, mely egy `Position`-t vár paraméterül, és a
    típusparaméternek megfelelő objektumot ad vissza.

  - Egy `set` metódust, mely paraméterül vár egy `Position`-t és egy,
    a típusparaméternek megfelelő objektumot, és nincs visszatérési
    értéke.

### Absztrakt játéktábla

Annak érdekében, hogy könnyebb legyen játéktáblák különböző
megvalósításait megadni, hozzuk létre a `grid` csomagban a nyilvános,
absztrakt `AbstractGrid` osztályt. Az osztály típussal paraméterezett,
és megvalósítja az előbbi `Grid` interfészt ugyanezzel a paraméterrel.

Vegyünk fel két nyilvános, nem módosítható `int` típusú adattagot:
`rows` és `cols`. Ezek jelölik a játéktábla sorainak és oszlopainak
számát.

Valósítsunk meg két nyilvános műveletet:

  - Egy konstruktort, amely paraméterül várja a játéktábla sorainak és
    oszlopainak számát, és inicializálja a két adattagot. A bal fölső
    sarok a nulladik sor (függőleges koordináta), nulladik oszlopában
    (vízszintes koordináta) van. A jobb alsó sarok sorát és oszlopát
    megkapjuk, ha a tábla sorainak és oszlopainak számából levonunk
    egyet-egyet.

  - Az `isValid` metódust, mely igaz értéket ad vissza, ha a
    paraméterül várt pozíció a játéktáblán van. Ez akkor teljesül, ha
    a bal alsó sarokhoz a `compareTo`-val viszonyítva a paramétert
    nempozitív számot kapunk, a jobb fölsőhöz viszonyítva
    nemnegatívat.

### Sűrűn telepakolt játéktábla

A `grid` csomagon belül hozzuk létre a nyilvános `DenseGrid` osztályt,
mely típussal paraméterezett, és kiterjeszti az előbbi `AbstractGrid`
osztályt ugyanazzal a paraméterrel.

Vegyünk fel egy rejtett adattagot, melynek típusa `ArrayList`, mely
`ArrayList`-eket tárol, mely típusparaméternek megfelelő objektumokat
tartalmaz. Tehát az adattag a kétdimenziós tömb `ArrayList`
megfelelője. Legyen az adattag neve `grid`.

Valósítsuk meg a következő nyilvános metódusokat:

  - Egy konstruktort, mely paraméterül várja a sorok és oszlopok
    számát, ezekkel meghívja a szülőosztály
    konstruktorát. Inicializáljuk az egyetlen adattagot annyi
    `null`-lal feltöltött `ArrayList`-tel, ahány sor van.

    Legyen a típusparaméter neve `T`. A feltöltéshez vegyünk fel egy
    `T` típusú változót, melynek adjunk `null`-t értékül.

  - A `get` metódust, mely `Position`-t vár, és típusparaméternek
    megfelelő objektumot ad vissza. Két eset lehetséges:

      - Ha a pozíció a táblán van (`isValid`), akkor adjuk vissza a
        `Position` által jelölt soron belül (függőleges koordináta)
        található oszlopban (vízszintes koordináta) tárolt elemet. Az
        elem elérésekor használt az első index a sorszám, a második az
        oszlopszám.

      - Ha a pozíció nincs a táblán, úgy dobjunk
        `IndexOutOfBoundsException`-t a következő paraméterrel:
        `"Invalid position: "` melyet a paraméter követ.

  - A `set` metódust, mely egy `Position`-t és egy, a
    típusparaméternek megfelelő objektumot vár paraméterül, és nincs
    visszatérési értéke. Szintén két eset lehetséges:

      - Ha a pozíció a táblán van (`isValid`), akkor tároljuk el a
        `Position` által jelölt sorban és oszlopban a második
        paramétert.

      - Ha a pozíció nincs a táblán, akkor dobjunk
        `IndexOutOfBoundsException`-t a következő paraméterrel:
        `"Invalid position: "` melyet a pozíció követ.

### Ritkán pakolt játéktábla

Készítsünk egy másik, memóriatakarékos ábrázolást játéktáblára! Hozzuk
létre a `grid` csomagban a nyilvános `SparseGrid` osztályt, mely
típussal paraméterezett, és szintén kiterjeszti az `AbstractGrid`
osztályt ugyanazzal a típusparaméterrel.

Vegyünk fel egy rejtett, leképezést tartalmazó adattagot. Az adattag
típusa legyen `java.util.Map`, amely két típusparamétert vár (a kulcs
és a hozzárendelt érték típusa). Az adattag `Position`-ről a
típusparaméterre képez le (ezeket kell tehát a `Map`-nak
típusparaméterként megadni).

Valósítsuk meg a következő nyilvános metódusokat:

  - Egy konstruktort, mely paraméterül várja a sorok és oszlopok
    számát, ezekkel meghívja a szülőosztály konstruktorát. Az egyetlen
    adattagot inicializáljuk egy üres leképezéssel
    (`java.util.HashMap`).

  - A `get` metódust, mely paraméterül vár egy `Position`-t, és
    visszaadja az ott tárolt elemet. Két eset lehetséges:

      - Ha a pozíció a táblán van (`isValid`), akkor adjuk vissza azt
        az elemet, amely a leképezésben a pozícióhoz van rendelve.

      - Ha a pozíció nincs a táblán, akkor dobjunk
        `IndexOutOfBoundsException`-t a következő paraméterrel:
        `"Invalid position: "` melyet a pozíció követ.

  - A `set` metódust, mely paraméterül vár egy `Position`-t és egy, a
    típusparaméternek megfelelő objektumot, és nincs visszatérési
    értéke. Szintén két eset lehetséges:

      - Ha a pozíció a táblán van (`isValid`), akkor a leképezésben
        egyszerűen rendeljük a pozícióhoz a második paramétert.

      - Ha a pozíció nincs a táblán, akkor dobjunk
        `IndexOutOfBoundsException`-t a következő paraméterrel:
        `"Invalid position: "` melyet a pozíció követ.

## Bábuk ábrázolása

A dámajátékban kétféle bábu van: egyszerű és dáma. Az alábbiakban a
két fajtához egy-egy osztályt készítünk (`Men` és `King`), kiemelve a
közös vonásokat egy szülőosztályba (`Piece`).

A bábukkal léphetünk a játéktáblán. Lépés előtt megvizsgáljuk, hogy
szabályos-e a lépés. Ha igen, áthelyezzük a bábut, ellenkező esetben
kivételt váltunk ki.

### Szabálytalan lépés kivétel

A `draughts` csomagban hozzuk létre az `InvalidStepException`
ellenőrzött kivételt, mely a `java.lang.Exception` osztályból
származik!

Az osztály egyetlen metódusa a konstruktor, amely paraméterül vár két
`Position` objektumot: honnan hova léptünk volna. A metódus meghívja a
szülőosztály konstruktorát a következő üzenettel: `"Invalid step from:
<kiindulási hely> to: <cél>"`, ahol `<kiindulási hely>` és `<cél>`
helyére a két paraméter szöveges ábrázolása kerül.

### Absztrakt bábu

A `draughts` csomagban hozzuk létre az absztrakt `Piece` osztályt!

Az osztályon belül definiáljuk a nyilvános, osztályszintű `Color`
felsorolási típust `WHITE` és `BLACK` értékekkel!

Vegyük fel a következő adattagokat. Mindegyik hozzáférhető a
leszármazott számára (de nem nyilvános), és nem módosítható (`final`).

  - `Position` objektum, mely a bábu pozíciója a játéktáblán.

  - `Color` objektum, mely a bábu színe.

  - `Grid` objektum `Piece` típussal paraméterezve, mely a játéktábla.

Legyenek az osztálynak a következő nyilvános metódusai:

  - Egy konstruktor, mely paraméterül várja a bábu pozícióját, színét
    és a játéktáblát, és ezekkel inicializálja az adattagokat. A
    paramétereket nem kell klónozni.

  - `canStepTo`, mely paraméterül vár egy pozíciót, és csak akkor ad
    vissza logikai igaz értéket, ha a bábu léphet a megadott
    pozícióra. Ezt nem tudjuk itt megadni, ezért legyen absztrakt.

  - `stepTo`, mely paraméterül vár egy pozíciót, és nincs visszatérési
    értéke. Ha nem sikerült a megadott pozícióra lépni, egy
    `InvalidStepException` kivételt vált ki. Ehhez vizsgáljuk meg,
    hogy tudunk-e a bábuval az adott helyre lépni (`canStepTo`). Ha
    nem, dobjunk `InvalidStepException` kivételt, átadva az eredeti és
    a paraméter pozíciót. Ellenkező esetben végezzük az alábbi
    lépéseket:

      1. Számítsuk ki a bábu eredeti helye és a paraméter közötti
         távolságot (`distance`)!

      2. A távolság alapján kérdezzük le, melyik irányba kell lépni a
         bábuval (`fromDistance`)!

      3. Az irány alapján hozzuk létre a bábu aktuális pozíciójával
         szomszédos pozíciót (`next`)!

      4. Ha a szomszédos pozíció még nem az, ahova kell lépni
         (`equals`), akkor ez azt jelenti, hogy van egy bábu, melyet
         át kell ugornunk, azaz le kell ütnünk. A leütés azt jelenti,
         hogy az adott helyen a játéktáblán `null` lesz (`set`).

      5. Helyezzük át a bábut eredeti helyéről az újra. Ehhez legyen
         az eredeti helyen `null`, és az új helyre állítsuk be az
         aktuális bábut.

  - Tegyük absztrakttá a `toString` metódust! Ezzel azt érjük el, hogy
    a leszármazottakban kötelező legyen ezt is megadni.

### Egyszerű bábu

A `draughts` csomagon belül hozzuk létre a nyilvános `Man` osztályt,
mely kiterjeszti az előbbi `Piece` osztályt. Új adattag bevezetésére
nincs szükség, az absztrakt metódusok konkréttá tételére szorítkozunk.

Legyenek az osztálynak az alábbi nyilvános metódusai:

  - Egy konstruktor, mely paraméterül várja a pozíciót (`Position`),
    színt (`Color`), és a játéktáblát (`Piece`-szel paraméterezett
    `Grid`), és ezekkel meghívja a szülőosztály konstruktorát.

  - A `canStepTo`, mely paraméterül várja egy pozíciót, és csak akkor
    ad vissza igaz értéket, ha lehet oda lépni. Egy egyszerű bábu
    egyet léphet átlósan le (ha a színe fekete) vagy fel (ha a színe
    fehér). Előfordulhat hogy kettőt lépünk, ha át tudjunk ugrani az
    ellenfél egy bábuját.

      Vizsgáljuk meg, hogy a megadott pozíció a táblán van-e
      (`isValid`), egy átlón helyezkedik-e el bábu eredeti pozíciója
      és a paraméter pozíció (`isDiagonal`), és a paraméterül kapott
      pozíció különbözik- a bábu jelenlegi pozíciójától (`equals`)! Ha
      nem, adjunk vissza hamis értéket. Ellenkező esetben a kérdés
      eldöntésére egy lehetséges megoldás az alábbi lépéssorozat:

      1. Számítsuk ki a távolságot a bábu eredeti helye és a paraméter
         között (`distance`)!

      2. Váltsuk át a távolságot irányra (`fromDistance`)!

      3. Egy logikai változóban tároljuk el, hogy csak egy lépést
         tettünk-e meg! Ez akkor igaz, ha a függőleges és vízszintes
         távolság abszolút értékének összege pontosan 2.

      3. Egy logikai változóban tároljuk el, hogy két lépést tettünk-e
         meg! Ez akkor igaz, ha a függőleges és vízszintes távolság
         abszolút értékének összege pontosan 4.

      4. Egy változóban tároljuk el, hogy a szomszédos mezőn (`next`)
         milyen bábu található (`get`)!

      5. Egy logikai változóban tároljuk el, hogy ellenséges-e a
         szomszédos mezőn lévő bábu. Ez akkor igaz, ha a szomszédos
         bábu nem `null` és a színe eltér az aktuális bábu színétől.

      6. Egy logikai változóban tároljuk el, hogy a paraméterül kapott
         hely üres-e (a `get` `null`-t ad vissza).

      7. Egy logikai változóban tároljuk el, hogy jó irányba
         lépnénk-e! Ha a bábu színe fekete, akkor az iránynak
         `DOWN_LEFT` vagy `DOWN_RIGHT`-nak kell lennie. Ha a szín
         fehér, akkor `UP_LEFT` vagy `UP_RIGHT`-nak kell lennie.

      8. Mindezek után a végeredmény a következő logikai kifejezés: jó
         irányba léptünk, és a paraméterül kapott hely üres, és vagy
         csak egyet léptünk vagy kettőt, az utóbbi esetben egy
         ellenséges bábu áll a közbülső mezőn.

  - `toString`, amely visszaadja a bábu színének első betűjét
    (`charAt`), mellette egy ,,M'' betűt. Például egy egyszerű fekete
    bábu esetén ez `BM` lesz.

### Dáma bábu

A dámák abban különlegesek az egyszerű bábukhoz képest, hogy léphetnek
előre vagy hátra is egyet.

A `draughts` csomagban hozzuk létre a nyilvános `King` osztályt, mely
kiterjeszti az absztrakt `Piece` osztályt, és amelyben nem adunk meg
új adattagot.

Legyenek az osztálynak az alábbi nyilvános metódusai:

  - Egy konstruktor, mely paraméterül várja a pozíciót (`Position`),
    színt (`Color`), és a játéktáblát (`Piece`-szel paraméterezett
    Grid), és ezekkel meghívja a szülőosztály konstruktorát.

  - `canStepTo`, mely paraméterül vár egy pozíciót, és csak akkor ad
    vissza igaz értéket, ha lehet oda lépni a bábuval. Egy dáma egyet
    léphet átlósan tetszőleges irányban. Előfordulhat hogy kettőt
    lépünk, ha át tudjunk ugrani az ellenfél egy bábuját. Feltesszük,
    hogy a paraméterül kapott pozíció nem egyezik meg a bábu jelenlegi
    pozíciójával.

    Vizsgáljuk meg, hogy a megadott pozíció a táblán van-e
    (`isValid`), és egy átlón helyezkedik-e el bábu eredeti pozíciója
    és a paraméter pozíció (`isDiagonal`)! Ha nem, adjunk vissza hamis
    értéket. Ellenkező esetben a kérdés eldöntésére egy lehetséges
    megoldás hasonló, de megengedőbb a `Man` osztályban megírthoz
    képest. Az 1-7. lépés megegyezik, azonban a 8. lépés kimarad, ahol
    azt ellenőriztük, hogy jó irányba lépnénk-e.

    A végeredmény tehát a következő logikai kifejezés: a paraméterül kapott
    hely üres és vagy csak egyet léptünk vagy kettőt, az utóbbi
    esetben egy ellenséges bábu áll a közbülső mezőn.

  - `toString`, amely visszaadja a bábu színének első betűjét
    (`charAt`), mellette egy ,,K'' betűt. Például egy fehér dáma bábu
    esetén ez `WK` lesz.
