SELECT * FROM PLATZ--Abfrage nach freien Plätzen mit Laptop am Vormittag des 14.09.22
WHERE PID IN(
    SELECT PID FROM VERFUEGBAR 
    WHERE Typ='Laptop' AND PID NOT IN (
        SELECT PID FROM BESETZT 
        WHERE Datum='2022-09-14' AND Nachmittag=0
    )
);