# StoragUnitSystem

Die Ist eine Dokumentation zu meinem StorageUnitSystem.
Ersteller: Remo Wettstein
Datum: 10.07.2025

---

## Projektidee

Das Storage Unit System is eine Webanwendung, bei welche Nutzer Lagerräume (Units) bequem online von zuhause mieten und verwalten können.
Adminis können neue Lagerplätze anlegen, oder auch anpassen, als auch diese zu entfernen.
Zusätzlich können Nutzer die Units nach Preis, Verfügbarkeit, Grösse oder auch nach dem Namen sortieren.
Nach dem anlegen seienes Nutzeraccount, kann der User diverse Units mieten.

Der Fokus liegt auf einem klar gegliederten, einfach zu bedienenden User-Interface.

Die Applickation basiert technig auf einem Backend mit SPRINGBOOT (REST-API), einer MySQL Datenbank und einem React-Frontend.

## Anforderungskatalg

### Funcktionale Anforderungen

### UI/UX Anforderungen

### Nicht-Funktionale Anforderungen

---

### User-Storises

:lemon: **User anlegen**
Als User möchte ich mich anlegen können um später dann auch mieten zu können

Akzeptanzkriterien:

- Das Passwort soll nicht leer sein.
- Der Benutzername muss vorhanden sein.
- Die Email Adresse muss gültig sein.

:watermelon: **Unit mieten**
Als Nutzer möchte ich Lagerräume mieten können, damit ich z.B meine nicht mehr verwendeten Möbel unterbringen kann.

Akzeptanzkriterien:

- Das Enddatum der neu Angelegten Miete darf nicht in der Vergangenheit liegen.
- Nach der Buchung eines Lagerraumes soll diese in der Datenbank abgespeichert werden.

:grapes: **Lagerflächen verwalten (Admin)**
Als Admin will ich neue Units anlegen können, damit Nutzer sie im Anschluss mieten können

Akzeptanzkriterien:

- Der Preis, Name und die Grösse müssen angegeben werden.
- Der Preis muss Positiv sein.
- Nach der Anlegung einer neuen Unit muss sie nun in der Datenbank erscheinen,

---

## Diagramme

### Klasendiagramm

:santa: **_Users_**
`id: Integer (PK)`
`username: String`
`email: String`
`password: String (vorerst Leer)`

:department*store: \*\*\_StorageUnits*\*\*
`id: Integer (PK)`
`name: String („Unit69“)`
`sizeInM2: Double (in m2)`
`pricePerMonth: Double`
`isAvailable: Boolean`

:dollar: **_Rented_** (Verknüpfungstabelle für Buchungen)
`id: Integer (PK)`
`user_id: Integer (FK zu User)`
`storage_unit_id: Integer (FK zu StorageUnit)`
`startDate: LocalDate (Wann die Miete beginnt)`
`endDate: LocalDate (Wann die Miete endet)`

**Entity-Relationship-Diagramm:**

<img style="border-radius:1rem"  src="./Pictures/ERD.png" alt="Rentingpage" width="500" />

### Komponentendiagramm

Dieses Diagramm zeigt, wie meine Applikation aufgebaut ist.

- Im Frontend **React**
- Im Backend **Java (Spring)**
- Als DB **MySQL**
  - Zwischen dem Front und Backend **Axios**
    <img styles="background-color:white" src="./Pictures/Components.png" alt="Componentdiagram" width="500"/>

---

## Storyboard

1. Nutzer gelant auf die Homepage
2. Fals schon angelegt kann er direkt eine Unit mieten.
   - Fals nicht angelegt, auf der Userseite kann er dies tun.
3. Storage Unit ansehen.
4. Storage Unit auswählen.
5. Mietzeitraum festlegen.
6. Buchung abschliessen.

Im Bild unten ist das Storyboard mehr im Detail erklärt.

<img src="./Pictures/StoryBoard.svg" alt="Storyboard" width="1000"/>

---

## Screen-Mockups

:gemini: **Homepage:**

<img style="border-radius:1rem" src="./Mockup/HomePage.png" alt="Homepage" width="1000" />

:virgo: **Adminpage:**

<img style="border-radius:1rem" src="./Mockup/Admin.png" alt="Adminpage" width="1000" />

:sagittarius: **Rentingpage:**
<img style="border-radius:1rem" src="./Mockup/RentingPage.png" alt="Rentingpage" width="1000" />

:pisces: **Userpage:**

<img style="border-radius:1rem" src="./Mockup/UserPage.png" alt="Userpage" width="1000" />

---

## REST-Schnittstellen

### USERS:

![GET](https://img.shields.io/badge/GET-purple) `/api/users`  
![GET](https://img.shields.io/badge/GET-purple) `/api/users/{id}`  
![POST](https://img.shields.io/badge/POST-green) `/api/users`  
![PUT](https://img.shields.io/badge/PUT-orange) `/api/users/{id}`  
![DELETE](https://img.shields.io/badge/DELETE-red) `/api/users/{id}`

**Datenmodell:**

```json
{
  "id": 1,
  "userName": "Alice",
  "email": "alice@example.com",
  "password": "password123"
}
```

### STORAGEUNITS:

![GET](https://img.shields.io/badge/GET-purple) `/api/storageunits`  
![GET](https://img.shields.io/badge/GET-purple) `/api/storageunits/{id}`  
![GET](https://img.shields.io/badge/GET-purple) `/api/storageunits?sortBy={sortby}` (price, name, size, availability)  
![POST](https://img.shields.io/badge/POST-green) `/api/storageunits`  
![PUT](https://img.shields.io/badge/PUT-orange) `/api/storageunits/{id}`  
![DELETE](https://img.shields.io/badge/DELETE-red) `/api/storageunits/{id}`

**Datenmodell:**

```json
{
  "id": 4,
  "name": "Unit D",
  "sizeInM2": 25.0,
  "pricePerMonth": 180.0,
  "available": true
}
```

### RENTINGS:

![GET](https://img.shields.io/badge/GET-purple) `/api/rented`  
![GET](https://img.shields.io/badge/GET-purple) `/api/rented/{id}`  
![POST](https://img.shields.io/badge/POST-green) `/api/rented/`  
![PUT](https://img.shields.io/badge/PUT-orange) `/api/rented//{id}`  
![DELETE](https://img.shields.io/badge/DELETE-red) `/api/rented/{id}`

**Datenmodell:**

```json
{
  "id": 4,
  "startDate": "2025-02-01",
  "endDate": "2026-02-01",
  "user": {
    "id": 2,
    "userName": "Bob",
    "email": "bob@example.com",
    "password": "secret456"
  },
  "storageUnit": {
    "id": 4,
    "name": "Unit D",
    "sizeInM2": 25.0,
    "pricePerMonth": 180.0,
    "available": true
  }
}
```

## Testplan

| Testfall | Beschreibung                     | Erwartetes Ergebnis                 |
| -------- | -------------------------------- | ----------------------------------- |
| 1        | Anmeldung ohne Passwort          | Validierungsfehler                  |
| 2        | Ungültige E-Mail-Adresse         | Validierungsfehler                  |
| 3        | Speichern einer Unit ohne Preis  | Validierungsfehler                  |
| 4        | Mietprozess mit gültigen Daten   | Mietobjekt wird korrekt gespeichert |
| 5        | Aufruf von GET /api/storageunits | Rückgabe einer Liste aller Units    |

## Installationsanleitung

Die Installationsanleitung finden sie in den einzelnen Repositorys im `INSTALLATION.md`.

- https://github.com/wettsteinremodev/StorageUnitSystem-Client.git
- https://github.com/wettsteinremodev/storageUnitSytemService.git

## Hillfestellungen

- **ChatGPT (OpenAI)** ➔ Hilfestellung zu im Frontend
- **Stack Overflow** ➔ Fehler bei @DataJpaTest und SQL
- **Spring Boot Docs** ➔ Entity Mapping, Validation
- **DBeaver** ➔ Klassendiagramm
- [Firma](https://Figma.com) ➔ Mockups
- [Excalidraw](https://excalidraw.com) ➔ Storyboard
- Noah Wieschmann und Silaz Enzler ➔ Feedback zu REST-API Design
- [Bezkoder](https://Bezkoder.com) ➔ Frontendhilfe
- [Notistack](https://notistack.com/getting-started) ➔ Anleiung wie man Notistack implementiert
