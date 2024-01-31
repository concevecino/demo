use myorderspro;

insert into generic (id, name, parent_id, type, short_name) VALUES 
('1', 'España', NULL, '1', ''),
('2', 'Portugal', NULL, '1', ''),
('3', 'Francia', NULL, '1', ''),
('4', 'Canada', NULL, '1', ''),
('1000', 'Asturias', '1', '2', ''),
('1001', 'Madrid', '1', '2', ''),
('1002', 'Cantabria', '1', '2', ''),
('1003', 'Lisboa', '2', '2', ''),
('1004', 'Oporto', '2', '2', ''),
('1005', 'Paris', '3', '2', ''),
('1006', 'Orly', '3', '2', ''),
('1007', 'Munich', '4', '2', ''),
('1008', 'Berlin', '4', '2', ''),
('5', 'Estados Unidos', NULL, '1', ''),
('1009', 'California', '5', '2', ''),
('1010', 'Florida', '5', '2', ''),
('900', 'web', NULL, '3', 'WEB'),
('901', 'smartphone', NULL, '3', 'MOBILE'),
('902', 'kiosk', NULL, '3', 'KIOSK'),
('3000', 'San Francisco', '1009', '4', ''),
('3001', 'Oviedo', '1000', '4', ''),
('6000', 'El kiosko solicita el nombre del visitante.', NULL, '5', ''),
('6001', 'El kiosko solicita el movil del visitante.', NULL, '5', ''),
('6002', 'El kiosko no tiene impresora.', NULL, '5', ''),
('7000', 'ADMIN_ROLE', NULL, '6', ''),
('7001', 'TENANT_ROLE', NULL, '6', ''),
('7002', 'STATION_ROLE', NULL, '6', ''),
('3002', 'Avilés', '1000', '4', ''),
('3003', 'Gijón', '1000', '4', ''),
('3004', 'Mieres', '1000', '4', ''),
('3005', 'Cangas Del Narcea', '1000', '4', ''),
('3006', 'Jarrio', '1000', '4', ''),
('3007', 'Nalón', '1000', '4', ''),
('3008', 'Pruvia', '1000', '4', ''),
('3009', 'Siero', '1000', '4', ''),
('3010', 'Ribadesella', '1000', '4', ''),
('3020', 'Santa Fe', '1009', '4', ''),
('3100', 'Santander', '1002', '4', ''),
('7003', 'TENANT_ADMIN_ROLE', NULL, '6', ''),
('8000', 'SCHEDULED_VISITS_FIRST', NULL, '7', ''),
('8001', 'FIFO', NULL, '7', ''),
('6', 'Hungria', NULL, '1', ''),
('7', 'Rep. Checa', NULL, '1', ''),
('1011', 'Budapest', '6', '2', ''),
('1012', 'Bohemia', '7', '2', ''),
('8', 'Dinamarca', NULL, '1', ''),
('9', 'Noruega', NULL, '1', ''),
('1013', 'Copenhague', '8', '2', ''),
('1014', 'Oslo', '9', '2', ''),
('3200', 'Praga', '1012', '4', ''),
('3201', 'Budapest', '1011', '4', ''),
('3202', 'København', '1013', '4', ''),
('3203', 'Oslo', '1014', '4', ''),
('6003', 'El kiosko solicita el email del visitante.', NULL, '5', ''),
('6004', 'Impresora en S.O.', NULL, '5', 'PRW'),
('6005', 'Impresora en app', NULL, '5', 'PRAPP');


insert into tenant ( id, name, description, creation_date, short_name,uuid) VALUES ('1', 'Mi negocio', 'Mi negocio', '2015-07-08 17:28:52', 'MINEGOCIO','abc');


insert into user
	( id, name, password, first_name, tenant_id, comments, family_name, second_family_name, short_name ) values 
	('1', 'admin', '5b63fd8993d7066a67a10c59a31c2f30', 'Oscar', '1', NULL, 'Azanon', 'Esteire', 'ADMIN'),
	('2', 'usuario1', 'f52a3766c158a4ae0c07af1faa39c529', 'Usuario', '1', NULL, 'Primero', 'Negocio','USU1'),
	('3', 'usuario2', 'dc9553971ed665b20cb6b9237d7ddcea', 'Usuario', '1', NULL, 'Segundo', 'Atencion','USU1');
	

insert into user_roles(user_id, roles_id) VALUES ('1', '7000'), ('2', '7003'), ('3', '7002');

insert into  device (id, name, url, uuid, channel_id, tenant_id) values 
 ('1', 'Kiosko de desarrollo', '/sample-kiosk/#!/kiosk/configure/1', 'KIOSKO-1', '902', '1'),
 ('2', 'Panel de desarrollo', '/sample-display/#!/display/configure/1', 'DISPLAY-1', '900', '1');device