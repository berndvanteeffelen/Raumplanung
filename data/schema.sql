CREATE TABLE NUTZER(
  Personalnummer INTEGER NOT NULL CHECK(Personalnummer>0 AND Personalnummer<1000000000) PRIMARY KEY,--Identifikationsnummer des Nutzers
  Passwort       VARCHAR NOT NULL CHECK(length(Passwort)>7 AND length(Passwort)<21 AND Passwort NOT GLOB '*[^ -~]*' AND Passwort GLOB '*[A-Z]*' AND Passwort GLOB '*[a-z]*' AND Passwort GLOB '*[0-9]*')--Passwort muss 8-20 Zeichen enthalten, davon je mindestens ein Klein- und ein Großbuchstabe und eine Zahl
);
CREATE TABLE ADMIN(
  Personalnummer VARCHAR REFERENCES NUTZER(Personalnummer) PRIMARY KEY
);
CREATE TABLE GERAET(
  Typ        VARCHAR NOT NULL PRIMARY KEY CHECK(length(Typ)>1 AND length(Typ)<50 AND Typ NOT GLOB '[^ -~]')--Typ des Geraets, bspw. 'Laptop' oder 'Tablet'
);
CREATE TABLE RAUM(
  Nummer     INTEGER NOT NULL PRIMARY KEY CHECK(Nummer>0 AND Nummer<100000)--Bereits vorhandene Raumnummer
);
CREATE TABLE PLATZ(
  Position   INTEGER NOT NULL CHECK(Position>0 AND Position<5),--Durchnummerierung von Arbeitsplätzen innerhalb desselben Raums
  PID        INTEGER UNIQUE PRIMARY KEY AUTOINCREMENT,
  Nummer     INTEGER REFERENCES RAUM(Nummer)
);
CREATE TABLE NUTZT(
  Personalnummer VARCHAR REFERENCES NUTZER (Personalnummer),
  Typ            VARCHAR REFERENCES GERAET (Typ),
  PRIMARY KEY (
    Personalnummer,
    Typ
  )
);
CREATE TABLE VERFUEGBAR(
  PID INTEGER REFERENCES PLATZ (PID),
  Typ VARCHAR REFERENCES GERAET (Typ),
  PRIMARY KEY (
    PID,
    Typ
  )
);
CREATE TABLE BESETZT(
  Personalnummer VARCHAR REFERENCES NUTZER (Personalnummer),
  PID            INTEGER REFERENCES PLATZ (PID),
  Datum          DATE NOT NULL CHECK(Datum==strftime('%YYYY-%MM-%DD',Datum) AND DATE() <= Datum AND Datum <= DATE(DATE(),'28 days')),--Planung für die nächsten zwei Wochen ab heute möglich
  Nachmittag     BOOLEAN NOT NULL,--Ist der Wert 0, ist es Vormittag. Ist der Wert 1, ist es Nachmittag.
  PRIMARY KEY (
    Personalnummer,
    PID,
    Datum,
    Nachmittag
  )
);
