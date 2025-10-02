INSERT INTO USERS (id, rm, password, name)
VALUES (NEWID(), '558471', 'ricardo123', 'Lucas Rainha');
INSERT INTO USERS (id, rm, password, name)
VALUES (NEWID(), '554944', 'futebol', 'Nicolas Barutti');
INSERT INTO USERS (id, rm, password, name)
VALUES (NEWID(), '557887', 'paodequeijo', 'Kleber da Silva');
INSERT INTO USERS (id, rm, password, name)
VALUES (NEWID(), '558472', 'senha123', 'Ana Silva');
INSERT INTO USERS (id, rm, password, name)
VALUES (NEWID(), '554945', 'outrasenha', 'Carlos Oliveira');



INSERT INTO SECTOR (id, name)
VALUES (NEWID(), 'ENTRANCE');
INSERT INTO SECTOR (id, name)
VALUES (NEWID(), 'MAINTENANCE');
INSERT INTO SECTOR (id, name)
VALUES (NEWID(), 'WASHING');
INSERT INTO SECTOR (id, name)
VALUES (NEWID(), 'STORAGE1');
INSERT INTO SECTOR (id, name)
VALUES (NEWID(), 'STORAGE2');



INSERT INTO SPOT (id, code, occupied, sector_id)
SELECT NEWID(), 'A1', 0, (SELECT TOP 1 id FROM SECTOR WHERE name = 'ENTRANCE');
INSERT INTO SPOT (id, code, occupied, sector_id)
SELECT NEWID(), 'A2', 1, (SELECT TOP 1 id FROM SECTOR WHERE name = 'ENTRANCE');

INSERT INTO SPOT (id, code, occupied, sector_id)
SELECT NEWID(), 'A1', 0, (SELECT TOP 1 id FROM SECTOR WHERE name = 'MAINTENANCE');
INSERT INTO SPOT (id, code, occupied, sector_id)
SELECT NEWID(), 'A2', 0, (SELECT TOP 1 id FROM SECTOR WHERE name = 'MAINTENANCE');

INSERT INTO SPOT (id, code, occupied, sector_id)
SELECT NEWID(), 'A1', 1, (SELECT TOP 1 id FROM SECTOR WHERE name = 'WASHING');
INSERT INTO SPOT (id, code, occupied, sector_id)
SELECT NEWID(), 'A2', 1, (SELECT TOP 1 id FROM SECTOR WHERE name = 'WASHING');

INSERT INTO SPOT (id, code, occupied, sector_id)
SELECT NEWID(), 'A1', 1, (SELECT TOP 1 id FROM SECTOR WHERE name = 'STORAGE1');
INSERT INTO SPOT (id, code, occupied, sector_id)
SELECT NEWID(), 'A2', 0, (SELECT TOP 1 id FROM SECTOR WHERE name = 'STORAGE2');




INSERT INTO MOTORCYCLE (id, sector_id, plate, plate_readable, type)
SELECT NEWID(), (SELECT TOP 1 id FROM SECTOR WHERE name = 'ENTRANCE'), 'ABC1D23', 1, 'ELECTRIC';
INSERT INTO MOTORCYCLE (id, sector_id, plate, plate_readable, type)
SELECT NEWID(), (SELECT TOP 1 id FROM SECTOR WHERE name = 'MAINTENANCE'), 'XYZ9K87', 1, 'COMBUSTION';
INSERT INTO MOTORCYCLE (id, sector_id, plate, plate_readable, type)
SELECT NEWID(), (SELECT TOP 1 id FROM SECTOR WHERE name = 'WASHING'), 'JHK4T56', 1, 'ELECTRIC';
INSERT INTO MOTORCYCLE (id, sector_id, plate, plate_readable, type)
SELECT NEWID(), (SELECT TOP 1 id FROM SECTOR WHERE name = 'STORAGE1'), 'LMN7R88', 1, 'COMBUSTION';
INSERT INTO MOTORCYCLE (id, sector_id, plate, plate_readable, type)
SELECT NEWID(), (SELECT TOP 1 id FROM SECTOR WHERE name = 'STORAGE2'), 'QWE2Z45', 1, 'ELECTRIC';



-- Update 1
INSERT INTO USER_UPDATE (id, user_id, motorcycle_id, update_date, update_message)
SELECT
    NEWID(),
    (SELECT TOP 1 id FROM USERS WHERE rm = '558471'),
    (SELECT TOP 1 id FROM MOTORCYCLE WHERE plate = 'ABC1D23'),
    GETDATE() - 1,
    'Revisão completa do sistema elétrico';

-- Update 2
INSERT INTO USER_UPDATE (id, user_id, motorcycle_id, update_date, update_message)
SELECT
    NEWID(),
    (SELECT TOP 1 id FROM USERS WHERE rm = '554944'),
    (SELECT TOP 1 id FROM MOTORCYCLE WHERE plate = 'XYZ9K87'),
    GETDATE() - 2,
    'Troca de óleo e filtros realizada';

-- Update 3
INSERT INTO USER_UPDATE (id, user_id, motorcycle_id, update_date, update_message)
SELECT
    NEWID(),
    (SELECT TOP 1 id FROM USERS WHERE rm = '557887'),
    (SELECT TOP 1 id FROM MOTORCYCLE WHERE plate = 'JHK4T56'),
    GETDATE() - 3,
    'Lavagem completa e polimento';

-- Update 4
INSERT INTO USER_UPDATE (id, user_id, motorcycle_id, update_date, update_message)
SELECT
    NEWID(),
    (SELECT TOP 1 id FROM USERS WHERE rm = '558471'),
    (SELECT TOP 1 id FROM MOTORCYCLE WHERE plate = 'LMN7R88'),
    GETDATE() - 4,
    'Transferência para área de armazenamento';

-- Update 5
INSERT INTO USER_UPDATE (id, user_id, motorcycle_id, update_date, update_message)
SELECT
    NEWID(),
    (SELECT TOP 1 id FROM USERS WHERE rm = '554944'),
    (SELECT TOP 1 id FROM MOTORCYCLE WHERE plate = 'QWE2Z45'),
    GETDATE(),
    'Inspeção de rotina - sem problemas encontrados';