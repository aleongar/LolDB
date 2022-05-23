INSERT INTO equipos
values
('G2', 'G2', 'Español'),
('SKT1', 'SK Telecom T1', 'Coreano'),
('MAD', 'MAD Lions', 'Español'),
('EDG', 'EDward Gaming', 'Chino'),
('FPX', 'FunPlus Phoenix', 'Chino'),
('DWG', 'Damwong Gaming', 'Chino');

INSERT INTO entrenadores
values
('Dylan', 'Falco', 8, 34586408, 'G2'),
('Choi', 'Seong-hun', 3, 35987491, 'SK Telecom T1'),
('James', 'MacCormack', 5, 15689354, 'MAD Lions'),
('Yang', 'Ji-Song', 5, 31486321, 'EDward Gaming'),
('Chou', 'Lu-Hsi', 10, 31687546, 'FunPlus Phoenix');

INSERT INTO jugadores
values
('caPs', 'Rasmus', 'Borregaard Winther', 'Danés', 'G2'),
('Faker', 'Lee', 'SaElng-hyeok', 'Coreano', 'SK Telecom T1'),
('Elyoya', 'Javier', 'Prades Batalla', 'Español', 'MAD Lions'),
('Flandre', 'Li', 'Xuan-Jun', 'Chino', 'EDward Gaming'),
('Lwx', 'Lin', 'Wei-Xiang', 'Chino', 'FunPlus Phoenix');

INSERT INTO campeones
values
('Akali', 'Pleno de 5 puntas, Velo, Voltereta Shuriken, Ejecución Perfecta', 'Mágico'),
('Ryze', 'Descarga Electrica, Prisión Rúnica, Tormenta Eléctrica, Distorsión de Reinos', 'Mágico'),
('Lee Sin', 'Onda Sónica, Salvaguarda, Tempestad, Ira del Dragón', 'Fisico'),
('Irelia', 'Embate de Espadas, Danza Desafiante, Dúo Impecable, Filo de la Vanguardia', 'Físico'),
('Ezreal', 'Disparo Místico, Flujo de Esencia, Alteración Arcana, Andanada Certera', 'Físico');

INSERT INTO dominar
values
('caPs', 'Akali', 3549090),
('Faker', 'Ryze', 4500321),
('Elyoya', 'Lee Sin', 2493109),
('Flandre', 'Irelia', 3000000),
('caPs', 'Irelia', 1508595),
('Faker', 'Akali', 1000252),
('Lwx', 'Ezreal', 1934459);

INSERT INTO partida
values
(1, 33),
(2, 27),
(3, 25),
(4, 40),
(5, 38);

INSERT INTO objetivo
values
(1, 'Torreta Inhibidora Superior', 'Torreta que se encuentra en la línea superior'),
(2, 'Torreta Inhibidora Central', 'Torreta que se encuentra en la línea central'),
(3, 'Torreta Inhibidora Inferior', 'Torreta que se encuentra en la línea inferior'),
(4, 'Torreta Principal Superior', 'Torreta en el centro de la línea superior'),
(5, 'Torreta Principal Inferior', 'Torreta en el centro de la línea inferior'),
(12, 'Dragón del Infierno', 'Dragón de fuego'),
(13, 'Dragón de Océano', 'Dragón de agua'),
(14, 'Dragón de las Montañas', 'Dragón de tierra'),
(15, 'Dragón de las Nubes', 'Dragon de aire'),
(16, 'Dragón Ancestral', 'Dragón que sale cuando mueren 4'),
(21, 'Dragona Tecnoquímica', 'Dragona basada en el veneno'),
(22, 'Dragona Hextech', 'Dragona basada en la tecnología');

INSERT INTO torretas
values
(1, 3),
(2, 3),
(3, 3),
(4, 1),
(5, 1);

INSERT INTO dragones
values
(12, 'Daño en area', 'Fuego'),
(13, 'Recupera vida fuera de combate', 'Agua'),
(14, 'Aumenta la armadura y la resistencia', 'Tierra'),
(15, 'Aumenta la velocidad de movimiento', 'Aire'),
(21, 'Da una segunda vida', 'Veneno'),
(22, 'Electrocuta a los objetivos cercanos al golpear', 'Electrico');

INSERT INTO conseguir
values
(3, 12, 6),
(3, 5, 7),
(4, 13, 20),
(4, 3, 30),
(1, 5, 9);

INSERT INTO objeto
values
(1, 'Tomo amplificador', 'Aumenta el daño mágico'),
(2, 'Espada larga', 'Aumenta el daño físico'),
(3, 'Cristal de maná', 'Aumenta el maná máximo'),
(4, 'Cristal de rubi', 'Aumenta la vida máxima'),
(5, 'Vara Explosiva', 'Aumenta el daño mágico'),
(6, 'Capítulo perdido', 'Restaura maná al subir de nivel'),
(7, 'Eco de Luden', 'Genera daño explosivo al lanzar una habilidad'),
(8, 'Bacteriófago', 'Regenera vida cuando haces daño a enemigos'),
(9, 'Pico', 'Aumenta el daño físico'),
(10, 'Portal del Zzrot', 'Invoca un portal'),
(11, 'Calibrador de Sterak', 'Da un escudo cuando luchas contra enemigos');

INSERT INTO dependen
values
(6,1,2,'Base'),
(6,3,1,'Base'),
(7,5,1,'Base fuerte'),
(7,6,1,'Núcleo Mítico'),
(8,2,1,'Base'),
(8,4,1,'Base'),
(11,9,1,'Base fuerte'),
(11,8,1,'Intermedio'),
(11,4,1,'Base');

INSERT INTO hacerse
values
(2,3,'caPs',5),
(8,3,'caPs',8),
(4,4,'Faker',1),
(6,4,'Faker',4),
(7,4,'Faker',10),
(9,3,'Elyoya',8);

INSERT INTO antitrampas
values
(1, true),
(2, true),
(3, false),
(4, true),
(5, false);

INSERT INTO juega
values
('caPs', 3, 1),
('Faker', 4, 2),
('Elyoya', 1, 3),
('Elyoya', 3, 4),
('Flandre', 2, 5);
