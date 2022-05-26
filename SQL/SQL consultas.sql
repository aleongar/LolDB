a1 Se desea recuperar la información de los campeones que hay

SELECT * FROM campeones

a2 Se desea conocer la información del entrenador de G2

SELECT * FROM entrenadores WHERE upper(equipo) = 'G2'

a3 Se desea ver que equipos pertenecen a la selección de China

SELECT * FROM equipos WHERE upper(nacion) = 'CHINO'

a4 Se requieren ver los jugadores que tengan más de 3 millones de puntos de maestría con algún campeón ordenado por el nombre de campeón

SELECT * FROM dominar WHERE maestria > 3000000 ORDER BY campeon

a5 Se quiere ver el nombre y apellidos de Faker

SELECT nombre, apellido FROM jugadores WHERE apodo = 'Faker'

----

b1 En los parches recientes, se cambió el nombre del 'Eco de Luden' a 'Tempestad de Luden'

UPDATE objeto
SET nombre = 'Tempestad de Luden'
WHERE nombre = 'Eco de Luden'

b2 La normativa de los torneos actual, ha obligado a cambiar el id de 'SKT1' a 'T1'

UPDATE equipos
SET id = 'T1'
WHERE id = 'SKT1'

b3 En los ultimos parches, (Riot la empresa a la que le pertenece el juego) ha eliminado de la lista de objetos el 'Portal del Zzrot'

DELETE FROM objeto WHERE nombre = 'Portal del Zzrot'

b4 El equipo Damwong Gaming se retira del torneo

DELETE FROM equipos WHERE id = 'DWG'

----

c1 Se solicita ver el nombre de los dragones con el elemento que representan (excepto el dragón anciano que no representa ningún elemento)

SELECT o.nombre, d.elemento FROM objetivo o, dragones d WHERE o.id = d.id

c2 Se desean ver que jugadores de los que iban a jugar una partida tenían un test antitrampas negativo (false) para esa partida

SELECT j.jugador, a.estado FROM juega j, antitrampas a WHERE j.antitrampa = a.codigo AND a.estado = false

c3 Se solicita ver los nombres de los objetos que se hicieron los jugadores y en que partida se lo hicieron

SELECT o.nombre, h.partida, h.jugador FROM objeto o, hacerse h WHERE o.id = h.objeto

----

d1 Se solicita ver cuantos dragones hay

SELECT count(*) FROM dragones

d2 Se solicita ver cuantos objetos se hizo Faker en la partida 4

SELECT count(*) FROM hacerse WHERE jugador = 'Faker'

d3 Se solicita ver cuantos minutos ha jugado caPs

SELECT sum(p.duracion) FROM juega j, partida p WHERE j.partida = p.id AND j.jugador = 'caPs'

----

e1 Se solicita ver cuantos puntos de maestría tienen cada jugador en total

SELECT jugador, sum(maestria) FROM dominar GROUP BY jugador

e2 Se solicita ver la cantidad de objetos que se han hecho los jugadores en diferentes partidas

SELECT count(o.nombre) cantidad_objetos, h.partida, h.jugador FROM objeto o, hacerse h WHERE o.id = h.objeto GROUP BY h.jugador, h.partida

----

f1 Se solicita el nombre del jugador que tenga el campeon con menos puntuación de maestría

SELECT jugador, campeon from dominar WHERE maestria =(SELECT min(maestria) from dominar)

f2 Ver los puntos de maestria de cada jugador con el campeón con el nombre más largo

SELECT maestria, campeon, jugador FROM dominar WHERE length(campeon) = (SELECT max(length(nombre)) FROM campeones)

----

g1 Seleccionar el/los jugador/es que más test antitrampas ha/han pasado

SELECT jugador FROM juega GROUP BY jugador HAVING count(antitrampa) = (SELECT max(e.a) FROM (SELECT count(antitrampa) a FROM juega GROUP BY jugador) e)

g2 Seleccionar el objeto, con su nombre que necesite más de 2 objetos distintos para crearse

SELECT d.objeto1, o.nombre, count(d.objeto2) FROM dependen d, objeto o WHERE d.objeto1 = o.id GROUP BY d.objeto1, o.nombre HAVING count(d.objeto2) > 2

----

h1 En los parches recientes, Riot ha decidido eliminar de la lista de dragones a la Dragona Tecnoquímica

DELETE FROM dragones WHERE id = (SELECT id FROM objetivo WHERE nombre = 'Dragona Tecnoquímica')

h2 Riot, ha aumentado el nivel de las torretas inhibidoras en 1

UPDATE torretas
SET nivel = nivel + 1
WHERE id in (SELECT id FROM objetivo WHERE nombre like 'Torreta Inhibidora%')

h3 Un error en la toma de datos ha hecho que se tomasé mal el minuto donde se consiguió un Dragón de Oceano en la partida 4, el minuto donde se consiguió fue 24

UPDATE conseguir
SET minuto = 24
WHERE partida = 4 
AND objetivo = (SELECT id FROM objetivo WHERE nombre = 'Dragón de Océano')


--Consultas adicionales (utilizadas para las funciones de java)

SELECT ultima_version, a.*, i.campeon, j.equipo from (SELECT apodo, max(maestria) from info_jugadores_maestria group by apodo) a, info_jugadores_maestria i, jugadores j WHERE a.max = i.maestria AND j.apodo = a.apodo
