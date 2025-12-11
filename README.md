# Assecor Assessment Test Implementation
This is the implementation of the Assecor Assessment Test

- [Assecor Assessment Test Implementation](#assecor-assessment-test-implementation)
  - [Deutsch](#deutsch)
    - [Erklärung](#erklärung)
    - [Warnungen](#warnungen)
    - [Mögliche Verbesserungen](#mögliche-verbesserungen)
  - [English](#english)
    - [Explanations](#explanations)
    - [Warnings](#warnings)
    - [Further improvement](#further-improvement)

## Deutsch
### Erklärung
Als Framework wurde Spring Boot mit gradle gewählt. Als Datenbank wurde die in-memory Datenbank h2 gewählt, da es keine Anforderung gab die Daten dauerhaft zwischen Neustarts zu speichern.

Die csv Datei wurde, aus Sicherheitsgründen, in `sample-input_original.csv` kopiert. Bei jedem Start der Anwendung wird diese Datei kopiert, wenn csv als Datenquelle benutzt wird. Der Kopiermechanismus kann mit `assessment.copyOriginalFile`ausgeschaltet werden. Die Datenbank wird ebenfalls beim Starten der Anwendung befüllt. Hibernate erstellt die Datenbankstrukturen.

Es wird ein enum für die Farben benutzt, da nicht beschrieben war, ob die Farben anpassbar / erweiterbar sein sollen. Deswegen muss das enum geändert werden, wenn die Farben geändert werden.

### Warnungen
* Die Datenbank ist eine in-memory und damit benutzt Spring Boot automatisch `spring.jpa.hibernate.ddl-auto` mit `create-drop`. Sollte eine andere Datenbank benutzt werden, welche nicht eine in-memory ist, muss / sollte diese Eigenschaft angegeben / gefüllt werden, da Spring Boot hier sonst `none` als Standard setzt. Oder man erstellt das Datenbankschema anders, dann muss aber mit der ID Generation aufgepasst werden.

* Wenn die csv Datei beim Starten der Anwendung nicht überschrieben werden soll durch eine Kopie dann muss in der `application.yml` die Eigenschaft `assessment.copyOriginalFile` auf `false` gesetzt werden. Es wird kopiert, da in der Aufgabenstellung steht, dass die Originaldatei eingelesen werden soll und der POST Endpunkt überschreibt die benutzte csv Datei. Dabei wird die Datei auch formatiert. Die Originaldatei hat eingebaute Fehler.

* Im Moment werden nur deutsche Farbnamen unterstützt. Es wurde nicht erwähnt, dass es mehrere Sprachen unterstützen soll und auch die englische Beschreibund benutzt deutsche Farbnamen.

* Wenn die `application-xxx.yml` für die Tests angepasst werden müssen: Diese befinden sich unter `main/resources`.

* Wenn eine weitere Datenquelle implementiert wird, sollte die `Startup` Klasse angepasst werden oder wenigstens geprüft werden.

* In der Aufgabenstellung ist das JSON mit Postleitzahl und Stadt angegeben. Allerdings passen diese nicht so ganz in den Daten, da hier nicht weiter beschrieben wird, ob der Inhalt auf Sinnhaftigkeit geprüft werden soll, wird dieser nicht geprüft. Auch weiß ich nicht, ob es Stadtnamen mit Leerzeichen gibt oder nicht. Daher werden die so übernommen.

### Mögliche Verbesserungen
* Sollten die Farben anpassbar sein muss eine andere Art der Repräsentation / Speicherung gewählt werden. Am Besten eine eigene Tabelle. Aber das ist schwierig mit csv. Auch eine Internationalisierung der Farbnamen wäre sinnvoll.

* Die Tests benutzen die normale Spring Boot Anwendung. Das heißt es werden keine Ressourcen aus `test/resources` benutzt sondern aus `main/resources`. Für dieses Projekt ist das kein Problem. Aber mit größeren Projekten mag dies nicht der beste Weg sein.

## English
### Explanations
As framework spring boot with gradle was chosen. The database was chosen as in-memory h2 as it does not need to persist the data between application starts, at least nothing like that was stated in the test description.

The csv file was copied to `sample-input_original.csv` for safety reason. It is copied on the application start if the csv repository is used and for the csv tests. The copy can be turned off with `assessment.copyOriginalFile`.
The database is filled with the data on startup too. Hibernate creates the table(s).

An enum is used for the colors as it was not stated if or how it can be changed by the user afterwards. If the colors change the enum must be changed as well.

### Warnings
* The database is an in-memory, therefore spring boot automatically uses `spring.jpa.hibernate.ddl-auto` with `create-drop`. If there is a need to use a different database which is not in-memory, then this must be specified as then spring boot will use `none`. Or the schema must be made differently, but then the ID generation must be considered as well.

* If the csv file should not be overwritten on startup then in the `applicaton.yml` the `assessment.copyOriginalFile` needs to be set to `false`. It is copied as the test stated clearly that the original csv file must be read in and the POST endpoint overwrites the csv file which reformats the the file a bit. The original file has intentional some errors in it.

* At the moment it is only possible to use german color names. It was not stated that different languages should be considered for it and in the english portion of the test description / goals the gernam names were used too.

* If the `application-xxx.yml` for the tests need to be changed: There are at the moment under `main/resources`

* If another datasource type is added the `Startup` class needs to be changed too or at least should be looked at.

* In the exercise was the JSON stated with zip code and city, but in the data the the cities where not always cities. It was not stated if the input should be checked for plausibility therefore the input is not checked and just used. I do not know if there are cities in the world who have space in the name or not.

### Further improvement
* If the colors must be changeable another way of representig/storage is needed. They should have an own table. But that would be difficult now with the csv. An internationalizing of the color names would be useful too.

* The tests use the normal spring boot application. Meaning they do not use `test/resources` as resource folder but `main/resources`. For this projects that is no problem. But with larger projects this may not the best way.