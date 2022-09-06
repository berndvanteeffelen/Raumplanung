INSERT INTO NUTZER
VALUES ('0123456789','Ac5B6abc');--(Personalnummer,Passwort)

INSERT INTO ADMIN
VALUES ('0123456789');--(Nutzer.Personalnummer)

INSERT INTO GERAET
VALUES ('Laptop');--(Typ)

INSERT INTO RAUM
VALUES (1234);--(Nummer)

INSERT INTO PLATZ
VALUES (1,1,1234);--(Position,PID,Raum.Nummer)

INSERT INTO PLATZ
VALUES (2,2,1234);--s.o.

INSERT INTO NUTZT
VALUES ('0123456789','Laptop');--(Personalnummer,Geraet.Typ)

INSERT INTO VERFUEGBAR
VALUES (1,'Laptop');--(Platz.PID,Geraet.Typ)

INSERT INTO BESETZT
VALUES ('0123456789',1,'2022-09-15',0);--(Nutzer.Personalnummer,Platz.PID,Datum,Nachmittag)