--Vistas 1
--Vista materializada de los jugadores con sus maestrias con cada campeón creada para visualizar los datos de los jugadores en la versión especificada en la columna "version"

CREATE MATERIALIZED VIEW info_jugadores_maestria
as
SELECT last_version() as version, j.apodo, d.campeon, d.maestria FROM jugadores j, dominar d WHERE j.apodo = d.jugador ORDER BY apodo

--Vistas 2
--Vista materializada con la información de los campeones (nombre, habilidades)

CREATE VIEW campeones_habilidades
as
SELECT c.nombre ,h.* FROM campeones c, separar_habilidades() h WHERE c.habilidades like concat(h.habilidad_q, ', %')

--Para probar estas vistas he realizado un par de inserts
INSERT INTO campeones
VALUES
('Zeri', 'Fuego Explosivo, Ultradescarga Láser, Subida de Tensión, Choque de Rayos', 'Físico')
INSERT INTO DOMINAR
VALUES
('Lwx', 'Zeri', 1531100)

------------------------------------------------------

--Función 1
--Función que calcula la versión actual del juego suponiendo que siempre actualizan el juego cada medio mes

CREATE OR REPLACE FUNCTION last_version()
RETURNS text AS $$
DECLARE
    anyo int := to_char(NOW(), 'YY')::int - 10;
    mes int :=to_char(NOW(), 'MM')::int * 2;
    dia int := to_char(NOW(), 'DD');
BEGIN
    IF(dia >= 15) THEN
        mes := mes + 1;
    END IF;
    RETURN CONCAT(anyo::text, '.', mes::text);
END;
$$ LANGUAGE PLPGSQL;

--Función 2
--Funcion que devuelve las habilidades de los campeones separada en varias columnas

CREATE OR REPLACE FUNCTION separar_habilidades()
RETURNS table(
    habilidad_q text,
    habilidad_w text,
    habilidad_e text,
    habilidad_r text
) AS $$
DECLARE
    num_hab int := 1;
    act_camp text;
    campeon cursor FOR SELECT nombre FROM campeones;
BEGIN
    OPEN campeon;
    LOOP
        FETCH campeon INTO act_camp;
        EXIT WHEN NOT FOUND;
            LOOP
                IF(num_hab = 1) THEN
                    SELECT SPLIT_PART((SELECT habilidades FROM campeones WHERE nombre = act_camp), ', ', num_hab) INTO STRICT habilidad_q;
                ELSIF(num_hab = 2) THEN
                    SELECT SPLIT_PART((SELECT habilidades FROM campeones WHERE nombre = act_camp), ', ', num_hab) INTO STRICT habilidad_w;
                ELSIF(num_hab = 3) THEN
                    SELECT SPLIT_PART((SELECT habilidades FROM campeones WHERE nombre = act_camp), ', ', num_hab) INTO STRICT habilidad_e;
                ELSIF(num_hab = 4) THEN
                    SELECT SPLIT_PART((SELECT habilidades FROM campeones WHERE nombre = act_camp), ', ', num_hab) INTO STRICT habilidad_r;
                END IF;
                num_hab := num_hab + 1;
                EXIT WHEN num_hab > 4;
             END LOOP;
             num_hab := 1;
            RETURN NEXT;
        END LOOP;
    CLOSE campeon;  
END;
$$ LANGUAGE PLPGSQL;

--Función 3
--Función para introducir datos en la tabla de dominar

CREATE OR REPLACE FUNCTION insertar_dominar(varchar(30), varchar(30), numeric(8))
RETURNS VOID AS $$
BEGIN
    INSERT INTO dominar
    VALUES
    ($1, $2, $3);
	RAISE INFO 'Datos introducidos correctamente';
    EXCEPTION
        WHEN unique_violation THEN
            RAISE EXCEPTION 'No se puede duplicar las claves primarias';
        WHEN foreign_key_violation THEN
            RAISE EXCEPTION 'No existe la información de las claves ajenas';
END;
$$ LANGUAGE PLPGSQL;

--Test correcto (si se ejecuta dos veces se obtiene insert incorrecto por clave primaria)
SELECT insertar_dominar('caPs', 'Ryze', 1000000);

--Test incorrecto por clave ajena
SELECT insertar_dominar('Faker', 'Guacamole', 4673291);

--Función 4
--Función que te permite introducir un objeto y una dependencia (en caso de tener)

CREATE OR REPLACE FUNCTION insertar_objeto(varchar(30), varchar(50), objeto_requerido numeric(3) DEFAULT NULL, cantidad numeric(2) DEFAULT NULL, nivel varchar(30) DEFAULT NULL)
RETURNS VOID AS $$
DECLARE
    idNueva int := (SELECT MAX(id) + 1 FROM objeto);
BEGIN
    INSERT INTO OBJETO
    VALUES
    (idNueva, $1, $2);
    RAISE INFO 'Se ha insertado el objeto correctamente';
    IF(objeto_requerido is not null AND cantidad is not null AND nivel is not null) THEN
        INSERT INTO DEPENDEN
        VALUES
        (idNueva, objeto_requerido, cantidad, nivel);
        RAISE INFO 'Se ha insertado la dependencia del objeto correctamente';
   END IF;
   EXCEPTION
   WHEN unique_violation THEN
        RAISE EXCEPTION 'No se puede duplicar claves primarias';
   WHEN foreign_key_violation THEN
        RAISE EXCEPTION'No existe la información de las claves ajenas';
END;
$$ LANGUAGE PLPGSQL;

--Test correcto (sin dependencia)
SELECT insertar_objeto('Espadón', 'Aumenta el daño físico');

--Test correcto (con dependencia)
SELECT insertar_objeto('Filo infinito', 'Aumenta el daño físico y probabilidad de critico', (SELECT id FROM objeto WHERE nombre = 'Espadón'), 1, 'Base fuerte');

--Test incorrecto por clave ajena
SELECT insertar_objeto('Filo del averno', 'Averno era el nombre antiguo de un cráter', 123, 8, 'Si');

--Función 5
--Función que cuenta las habilidades del campeón (creada para un trigger)

CREATE OR REPLACE FUNCTION contar_habilidades(varchar(120))
RETURNS INT AS $$
DECLARE
    cantidad int := 0;
    habilidad text;
    num_hab int := 1;
BEGIN
    LOOP
        SELECT SPLIT_PART($1, ', ', num_hab) INTO habilidad;
        EXIT WHEN habilidad = '';
        num_hab := num_hab + 1;
        cantidad := cantidad + 1;
    END LOOP;
    RETURN cantidad;
END;
$$ LANGUAGE PLPGSQL;

--Test
select contar_habilidades('Pleno de 5 puntas, Velo, Voltereta Shuriken, Ejecución Perfecta') --devuelve 4

--Funcion 6
--Funcion que devuelve el mejor campeón de un jugador con su puntuación maestría, en caso de no existir devuelve 0
CREATE OR REPLACE FUNCTION obtener_mejor_campeon(varchar(30))
RETURNS table(
    maestria_R numeric(8),
    campeon_R varchar(30)
) as $$
BEGIN
    SELECT a.max, d.campeon INTO STRICT maestria_R ,campeon_R FROM (SELECT max(maestria) FROM dominar WHERE jugador = $1) a,
                                  dominar d WHERE a.max = maestria and d.jugador = $1;
    RETURN NEXT;
    EXCEPTION
        WHEN no_data_found THEN
             SELECT 0, ' ' into maestria_R, campeon_R;
             RETURN NEXT;
END;
$$ language plpgsql

--Ejemplo
SELECT * FROM obtener_mejor_campeon('Faker')

-----------------------------------

--Trigger 1
--Trigger que comprueba si cuando se vaya a introducir una maestria nueva, esta sea mayor de un millón

CREATE OR REPLACE FUNCTION insertar_maestria()
RETURNS TRIGGER AS $$
BEGIN
    IF(NEW.maestria < 1000000) THEN
        RAISE EXCEPTION 'No puedes insertar una maestría menor de un millón';
    END IF;
    RAISE INFO 'Insert realizado correctamente';
    RETURN NEW;
END;
$$ LANGUAGE PLPGSQL;

CREATE TRIGGER insertar_maestria_trigger
BEFORE INSERT OR UPDATE of maestria
ON dominar
FOR EACH ROW
EXECUTE PROCEDURE insertar_maestria()

--Test incorrecto insert
INSERT INTO dominar
VALUES
('Faker', 'Lee Sin', '25')

--Test correcto
INSERT INTO dominar
VALUES
('Faker', 'Lee Sin', '1000000')

--Test incorrecto update
UPDATE dominar
SET maestria = 20
WHERE jugador = 'Elyoya';

--Test correcto update
UPDATE dominar
SET maestria = 1000000
WHERE jugador = 'Elyoya';

--Trigger 2
--Trigger que comprueba que las habilidades introducidas sean 4 y que no se pueda eliminar a un campeón

CREATE OR REPLACE FUNCTION modificar_tabla_campeon()
RETURNS TRIGGER AS $$
BEGIN
    IF(TG_OP = 'INSERT' OR TG_OP = 'UPDATE') THEN
        IF(contar_habilidades(NEW.habilidades) != 4) THEN
            RAISE EXCEPTION 'Los campeones deben de tener 4 habilidades';
        END IF;
	RETURN NEW;
    END IF;
    IF(TG_OP = 'DELETE') THEN
        RAISE EXCEPTION 'No puedes eliminar un campeón';
    END IF;
    RETURN null;
END;
$$ LANGUAGE PLPGSQL;

CREATE TRIGGER modificar_tabla_campeon_trigger
BEFORE INSERT OR UPDATE of habilidades OR DELETE
ON campeones
FOR EACH ROW
EXECUTE PROCEDURE modificar_tabla_campeon();

--Ejemplo incorrecto insert
INSERT INTO campeones VALUES ('Ahri', 'Orbe del Engoño, Fuego Zorruno, Hechizar, Impulso Espiritual, invento', 'Mágico')

--Ejemplo correcto insert
INSERT INTO campeones VALUES ('Ahri', 'Orbe del Engoño, Fuego Zorruno, Hechizar, Impulso Espiritual', 'Mágico')

--Ejemplo incorrecto update
UPDATE campeones
SET habilidades = 'Orbe del Engaño, Fuego Zorruno, Hechizar, Impulso Espiritual, haba'
WHERE nombre = 'Ahri'

--Ejemplo correcto update
UPDATE campeones
SET habilidades = 'Orbe del Engaño, Fuego Zorruno, Hechizar, Impulso Espiritual'
WHERE nombre = 'Ahri'

--Trigger 3
--Trigger que comprueba si el ID de dos equipos es parecido

CREATE OR REPLACE FUNCTION comprobar_nombre()
RETURNS TRIGGER AS $$
DECLARE
    nom_cur cursor FOR SELECT id FROM equipos;
    equipo text;
BEGIN
    OPEN nom_cur;
    LOOP
        FETCH nom_cur INTO equipo;
        EXIT WHEN NOT FOUND;
        IF(NEW.id like CONCAT('%',equipo,'%')) THEN
            RAISE EXCEPTION 'No pueden haber dos equipos con un id parecido';
            CLOSE nom_cur;
        END IF;
    END LOOP;
    CLOSE nom_cur;
    RETURN NEW;
END;
$$ LANGUAGE PLPGSQL;

CREATE TRIGGER comprobar_nombre_trigger
BEFORE INSERT OR UPDATE of id
ON equipos
FOR EACH ROW
EXECUTE PROCEDURE comprobar_nombre();

--Test update incorrecto
UPDATE EQUIPOS
SET id = 'MADD'
WHERE equipo = 'G2'

--Test update correcto
UPDATE EQUIPOS
SET id = 'G2'
WHERE equipo = 'G2'

--Test insert incorrecto
INSERT INTO equipos
values
('EDG', 'DWG KIA', 'Koreano')

--Test insert correcto
INSERT INTO equipos
values
('DWK', 'DWG KIA', 'Koreano')

--Trigger 4
--Trigger que inserta en la tabla objetivos la id de un objetivo metido desde las tablas dragon, torreta, heraldo y inhibidor
CREATE OR REPLACE FUNCTION actualizar_objetivos()
RETURNS TRIGGER AS $$
BEGIN
    INSERT INTO objetivo
    values
    (NEW.id, 'Dragon de ' || NEW.elemento, NEW.alma);
    RAISE INFO 'Id reservado en la tabla objetivos';
    RETURN NEW;
END;
$$ LANGUAGE PLPGSQL;

CREATE OR REPLACE TRIGGER actualizar_objetivos_trigger
BEFORE INSERT
ON dragones
FOR EACH ROW
EXECUTE PROCEDURE actualizar_objetivos();

--Ejemplo
INSERT into DRAGONES
values
(99, 'Revive temporalmente', 'Veneno')
