

<!--Za upute o sintaksi koju možete koristiti u ovom dokumentu i kod pisanje vaše projektne dokumentacije pogledajte [ovaj link](https://guides.github.com/features/mastering-markdown/).
Sav programski kod potrebno je verzionirati u glavnoj **master** grani i **obvezno** smjestiti u mapu Software. Sve artefakte (npr. slike) koje ćete koristiti u vašoj dokumentaciju obvezno verzionirati u posebnoj grani koja je već kreirana i koja se naziva **master-docs** i smjestiti u mapu Documentation.

Nakon vaše prijave bit će vam dodijeljen mentor s kojim ćete tijekom semestra raditi na ovom projektu. Mentor će vam slati povratne informacije kroz sekciju Discussions također dostupnu na GitHubu vašeg projekta. A sada, vrijeme je da prijavite vaš projekt. Za prijavu vašeg projektnog prijedloga molimo vas koristite **predložak** koji je naveden u nastavku, a započnite tako da kliknete na *olovku* u desnom gornjem kutu ovoga dokumenta :) -->

# Buketomat


## Projektni tim
(svi članovi tima moraju biti iz iste seminarske grupe)

Ime i prezime | E-mail adresa (FOI) | JMBAG | Github korisničko ime | Seminarska grupa
------------  | ------------------- | ----- | --------------------- | ----------------
Martin Friščić | mfriscic20@student.foi.hr | 0016147114 | mfriscic20 | G02
Ana Škarica | askarica20@student.foi.hr | 0016147364 | askarica20 | G02
Filip Milohanović | fmilohano20@student.foi.hr | 0016148270 | fmilohano20 | G02

## Opis domene
<!--Umjesto ovih uputa opišite domenu ili problem koji pokrivate vašim projektom. Domena može biti proizvoljna, ali obratite pozornost da sukladno ishodima učenja, domena omogući primjenu zahtijevanih koncepata kako je to navedeno u sljedećem poglavlju. Priložite odgovarajuće skice gdje je to prikladno.-->

Aplikacija Buketomat će omogućiti izradu personaliziranog buketa temeljem ponuđenih vrsta cvijeća, naručivanje buketa i odabir gotovih buketa za različite prigode.
Osim toga biti će omogućeno nasumično odabiranje nekog od gotovih buketa. Radi boljeg korisničkog iskustva bit će omogućeno kontaktiranje korisničke podrške putem mail adrese te odabir kada se naručeni buket želi preuzeti.

## Specifikacija projekta
<!--Umjesto ovih uputa opišite zahtjeve za funkcionalnošću mobilne aplikacije ili aplikacije za pametne uređaje. Pobrojite osnovne funkcionalnosti i za svaku naznačite ime odgovornog člana tima. Opišite osnovnu buduću arhitekturu programskog proizvoda. Obratite pozornost da mobilne aplikacije često zahtijevaju pozadinske servise. Također uzmite u obzir da bi svaki član tima trebao biti odgovoran za otprilike 3 funkcionalnosti, te da bi opterećenje članova tima trebalo biti ujednačeno. Priložite odgovarajuće dijagrame i skice gdje je to prikladno. Funkcionalnosti sustava bobrojite u tablici ispod koristeći predložak koji slijedi:-->

Oznaka | Naziv | Kratki opis | Odgovorni član tima
------ | ----- | ----------- | -------------------
F01 | Login | Sustav će omogućiti prijavu postojećih korisnika u sustav i korištenje funkcionalnosti aplikacije | Ana Škarica
F02 | Registracija| Sustav će omogućiti registraciju novih korisnika | Martin Friščić
F03 | Prikaz vlastitih narudžbi | Sustav će omogućiti prikaz vlastitih narudžbi (buketa). Buketi se mogu naručiti već gotovi ili se mogu napraviti svoje prilagođene verzije. | Filip Milohanović
F04 | Prikaz gotovih buketa | Sustav će omogućiti prikaz buketa koje aplikacija nudi koji su već složeni i spremni za prodaju. Ti buketi neće se moći dodatno prilagoditi.| Martin Friščić
F05 | Izrada vlastitog buketa | Sustav će omogućiti izradu vlastitog buketa po narudžbi. Biti će moguće odabrati vrste cvijeća, način dekoracije (paket ili omot...) i količinu | Ana Škarica
F06 | Kontaktiranje trgovine mailom | Sustav će omogućiti slanje maila iz aplikacije (mail može biti u svrhu žalbe ili upita)  | Martin Friščić
F07 | Slučajno generiranje buketa | Sustav će omogućiti slučajan odabir gotovog buketa | Ana Škarica
F08 | Naručivanje buketa | Sustav će omogućiti naručivanje buketa koji se odabere ili kreira unutar aplikacije | Filip Milohanović
F09 | Odabir termina dostave | Sustav će omogućiti odabir termina dostave buketa | Filip Milohanović


## Tehnologije i oprema
<!--Umjesto ovih uputa jasno popišite sve tehnologije, alate i opremu koju ćete koristiti pri implementaciji vašeg rješenja. Vaše rješenje može biti implementirano u bilo kojoj tehnologiji za razvoj mobilnih aplikacija ili aplikacija za pametne uređaje osim u hibridnim web tehnologijama kao što su React Native ili HTML+CSS+JS. Tehnologije koje ćete koristiti bi trebale biti javno dostupne, a ako ih ne budemo obrađivali na vježbama u vašoj dokumentaciji ćete morati navesti način preuzimanja, instaliranja i korištenja onih tehnologija koje su neopbodne kako bi se vaš programski proizvod preveo i pokrenuo. Pazite da svi alati koje ćete koristiti moraju imati odgovarajuću licencu. Što se tiče zahtjeva nastavnika, obvezno je koristiti git i GitHub za verzioniranje programskog koda, GitHub Wiki za pisanje jednostavne dokumentacije sukladno uputama mentora, a projektne zadatke je potrebno planirati i pratiti u alatu GitHub projects.-->

<h3>Programski alati</h3>
<ul>
<li>Kotlin</li>
<li>Android Studio</li>
<li>Github</li>
<li>MySQL Workbench</li>
</ul>

<h3>Literatura</h3>
<ul>
<li>Literatura s kolegija RAMPU</li>
<li>Internet stranice sa službenom dokumentacijom</li>
<li>Službeni kod izrađen na laboratorijskim vježbama</li>

</ul>

<h3>Oprema</h3>
<ul>
<li>Osobno računalo</li>
<li>Pametni telefon sa sustavom Android</li>
<li>USB kabel za povezivanje uređaja</li>
</ul>

## Baza podataka i web server
Trebamo bazu podataka i pristup serveru za PHP skripte. 

## .gitignore
<!--Uzmite u obzir da je u mapi Software .gitignore konfiguriran za nekoliko tehnologija, ali samo ako će projekti biti smješteni direktno u mapu Software ali ne i u neku pod mapu. Nakon odabira konačne tehnologije i projekta obavezno dopunite/premjestite gitignore kako bi vaš projekt zadovoljavao kriterije koji su opisani u ReadMe.md dokumentu dostupnom u mapi Software.-->

