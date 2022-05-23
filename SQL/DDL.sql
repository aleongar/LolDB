CREATE TABLE equipos(
	id varchar(5),
	nombre varchar(25),
	nacion varchar(20),
	CONSTRAINT PK_equipos PRIMARY KEY (nombre)
);


CREATE TABLE entrenadores(
	nombre varchar(30),
	apellidos varchar(30),
	años_servicio numeric(2),
	telefono numeric(8),
	equipo varchar(25),
	CONSTRAINT PK_entrenador PRIMARY KEY (nombre),
	CONSTRAINT UK_entrenador UNIQUE (equipo),
	CONSTRAINT FK_entrenador_equipo FOREIGN KEY (equipo) REFERENCES equipos(nombre) ON UPDATE CASCADE ON DELETE NO ACTION
);

CREATE TABLE jugadores(
	apodo varchar(30),
	nombre varchar(30),
	apellido varchar(30),
	nacionalidad varchar(20),
	equipo varchar(25),
	CONSTRAINT PK_jugadores PRIMARY KEY (apodo),
	CONSTRAINT FK_jugadores_equipos FOREIGN KEY (equipo) REFERENCES equipos(nombre) ON UPDATE CASCADE ON DELETE NO ACTION
);

CREATE TABLE campeones(
	nombre varchar(30),
	habilidades varchar(120),
	daño varchar(6),
	CONSTRAINT PK_campeones PRIMARY KEY (nombre)
);

CREATE TABLE dominar(
	jugador varchar(30),
	campeon varchar(30),
	maestria numeric(8),
	CONSTRAINT PK_dominar PRIMARY KEY (jugador, campeon),
	CONSTRAINT FK_dominar_jugadores FOREIGN KEY (jugador) REFERENCES jugadores(apodo) ON UPDATE CASCADE ON DELETE NO ACTION,
	CONSTRAINT FK_dominar_campeones FOREIGN KEY (campeon) REFERENCES campeones(nombre) ON UPDATE CASCADE ON DELETE NO ACTION
);

CREATE TABLE partida(
	id numeric(2),
	duracion numeric(3),
	CONSTRAINT PK_partida PRIMARY KEY (id)
);

CREATE TABLE objetivo(
	id numeric(2),
	nombre varchar(30),
	descripcion varchar(50),
	CONSTRAINT PK_objetivo PRIMARY KEY (id)
);

CREATE TABLE dragones(
	id numeric(2),
	alma varchar(50),
	elemento varchar(30),
	CONSTRAINT PK_dragones PRIMARY KEY (id),
	CONSTRAINT FK_dragones_objetivos FOREIGN KEY (id) REFERENCES objetivo (id) ON UPDATE CASCADE ON DELETE NO ACTION
);

CREATE TABLE torretas(
	id numeric(2),
	nivel numeric(1),
	CONSTRAINT PK_torretas PRIMARY KEY (id),
	CONSTRAINT FK_torretas_objetivos FOREIGN KEY (id) REFERENCES objetivo(id) ON UPDATE CASCADE ON DELETE NO ACTION
);

CREATE TABLE heraldo(
	id numeric(2),
	numero numeric(1),
	CONSTRAINT PK_heraldo PRIMARY KEY (id),
	CONSTRAINT FK_heraldo_objetivo FOREIGN KEY (id) REFERENCES objetivo(id) ON UPDATE CASCADE ON DELETE NO ACTION
);

CREATE TABLE inhibidores(
	id numeric(2),
	linea varchar(3),
	CONSTRAINT PK_inhibidores PRIMARY KEY (id),
	CONSTRAINT FK_inhibidores_objetivo FOREIGN KEY (id) REFERENCES objetivo(id) ON UPDATE CASCADE ON DELETE NO ACTION

);

CREATE TABLE conseguir(
	partida numeric(2),
	objetivo numeric(2),
	minuto numeric(3),
	CONSTRAINT PK_conseguir PRIMARY KEY (partida, objetivo),
	CONSTRAINT FK_conseguir_partida FOREIGN KEY (partida) REFERENCES partida(id),
	CONSTRAINT FK_conseguir_objetivos FOREIGN KEY (objetivo) REFERENCES objetivo(id)
);

CREATE TABLE objeto(
	id numeric(3),
	nombre varchar(30),
	descripcion varchar(50),
	CONSTRAINT PK_objeto PRIMARY KEY (id)
);

CREATE TABLE dependen(
	objeto_resultado numeric(3),
	objeto_requerido numeric(3),
	cantidad numeric(2),
	nivel varchar(30),
	CONSTRAINT PK_dependen PRIMARY KEY (objeto1, objeto2),
	CONSTRAINT FK_dependen_objeto1 FOREIGN KEY (objeto1) REFERENCES objeto(id) ON UPDATE CASCADE ON DELETE NO ACTION,
	CONSTRAINT FK_dependen_objeto2 FOREIGN KEY (objeto2) REFERENCES objeto(id) ON UPDATE CASCADE ON DELETE NO ACTION
);

CREATE TABLE hacerse(
	objeto numeric(3),
	partida numeric(2),
	jugador varchar(30),
	minuto numeric(3),
	CONSTRAINT PK_hacerse PRIMARY KEY (objeto, partida, jugador),
	CONSTRAINT FK_hacerse_objeto FOREIGN KEY (objeto) REFERENCES objeto(id) ON UPDATE CASCADE ON DELETE NO ACTION,
	CONSTRAINT FK_hacerse_partida FOREIGN KEY (partida) REFERENCES partida(id) ON UPDATE CASCADE ON DELETE NO ACTION,
	CONSTRAINT FK_hacerse_jugador FOREIGN KEY (jugador) REFERENCES jugadores(apodo) ON UPDATE CASCADE ON DELETE NO ACTION
);

CREATE TABLE antitrampas(
	codigo numeric(3),
	estado boolean,
	CONSTRAINT PK_antitrampas PRIMARY KEY (codigo)
);

CREATE TABLE juega(
	jugador varchar(30),
	partida numeric(2),
	antitrampa numeric(3),
	CONSTRAINT PK_juega PRIMARY KEY (jugador, partida),
	CONSTRAINT FK_juega_jugador FOREIGN KEY (jugador) REFERENCES jugadores(apodo) ON UPDATE CASCADE ON DELETE NO ACTION,
	CONSTRAINT FK_juega_partida FOREIGN KEY (partida) REFERENCES partida(id) ON UPDATE CASCADE ON DELETE NO ACTION,
	CONSTRAINT FK_juega_antitrampa FOREIGN KEY (antitrampa) REFERENCES antitrampas(codigo) ON UPDATE CASCADE ON DELETE NO ACTION
)
