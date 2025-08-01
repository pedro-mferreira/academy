CREATE TYPE rack_status_enum AS ENUM ('ACTIVE', 'RETURNED', 'REPAIR', 'OUTDATED', 'BRICKED');

CREATE SEQUENCE IF NOT EXISTS SEQ_TEAM_ID;

CREATE TABLE T_TEAM
(
    id               bigint PRIMARY KEY DEFAULT nextval('SEQ_TEAM_ID'),
    name             text,
    product          text,
    created_at       TIMESTAMP,
    modified_at      TIMESTAMP,
    default_location varchar(10)
);


CREATE SEQUENCE IF NOT EXISTS SEQ_RACK_ID;

CREATE TABLE T_RACK
(
    id               bigint PRIMARY KEY DEFAULT nextval('SEQ_RACK_ID'),
    serial_number    text,
    status           rack_status_enum ,
    team_id          bigint,
    default_location varchar(10),
    assembled_at     TIMESTAMP,
    created_at       TIMESTAMP,
    modified_at      TIMESTAMP,
    FOREIGN KEY (team_id) REFERENCES T_TEAM (id)
);

CREATE SEQUENCE IF NOT EXISTS SEQ_RACK_ASSET_ID;

CREATE TABLE T_RACK_ASSET
(
    id        bigint PRIMARY KEY DEFAULT nextval('SEQ_RACK_ASSET_ID'),
    asset_tag varchar(10),
    rack_id   bigint,
    FOREIGN KEY (rack_id) REFERENCES T_RACK (id)
);
CREATE SEQUENCE IF NOT EXISTS SEQ_TEAM_MEMBER_ID;

CREATE TABLE T_TEAM_MEMBER
(
    id          bigint PRIMARY KEY DEFAULT nextval('SEQ_TEAM_MEMBER_ID'),
    team_id     bigint,
    ctw_id      text,
    name        text,
    created_at  TIMESTAMP,
    modified_at TIMESTAMP,
    FOREIGN KEY (team_id) REFERENCES T_TEAM (id)
);

CREATE SEQUENCE IF NOT EXISTS SEQ_BOOKING_ID;

CREATE TABLE T_BOOKING
(
    id            bigint PRIMARY KEY DEFAULT nextval('SEQ_BOOKING_ID'),
    rack_id       bigint,
    requester_id  bigint,
    serial_number text,
    book_from     TIMESTAMP,
    book_to       TIMESTAMP,
    created_at    TIMESTAMP,
    modified_at   TIMESTAMP,
    FOREIGN KEY (requester_id) REFERENCES T_TEAM_MEMBER (id)
);

INSERT INTO T_TEAM(name, product, default_location)
VALUES('The Gardeners', 'Plant', 'Lisbon');
INSERT INTO T_TEAM(name, product, default_location)
VALUES('Stars', 'Car', 'Porto');
INSERT INTO T_TEAM(name, product, default_location)
VALUES('Speedsters','Moto', 'Braga');
INSERT INTO T_TEAM(name, product, default_location)
VALUES('The Analytics', 'Dashboards', 'Lisbon');
INSERT INTO T_TEAM(name, product, default_location, created_at, modified_at)
VALUES('Wheels', 'Car', 'Porto', now(), now() );
INSERT INTO T_TEAM(name, product, default_location)
VALUES('Sonic Team', 'Car', 'Porto');


INSERT INTO T_RACK(id, serial_number, team_id, created_at, default_location, status)
VALUES (DEFAULT, '1000-12021-01', 1, '2024-07-09 17:49:22.471747', 'PORTO', 'ACTIVE');

INSERT INTO T_RACK(id, serial_number, team_id, created_at, default_location, status)
VALUES (DEFAULT, '1000-12021-02', 1, '2024-02-01', 'PORTO', 'ACTIVE');

INSERT INTO T_RACK(id, serial_number, team_id, created_at, default_location, status)
VALUES (DEFAULT, '2222-10000-01', 1, '2023-08-01', 'LISBON', 'ACTIVE');

INSERT INTO T_RACK(id, serial_number, team_id, created_at, default_location, status)
VALUES (DEFAULT, '1000-12021-03', 1, '2024-07-09 21:15:21.350827', 'BRAGA', 'ACTIVE');

INSERT INTO T_RACK(id, serial_number, team_id, created_at, default_location, status)
VALUES (DEFAULT, '3100-11031-01', 1, '2024-07-09 21:54:38.432536', 'PORTO', 'RETURNED');

INSERT INTO T_RACK_ASSET(asset_tag, rack_id)
VALUES('tag1', 2);

INSERT INTO T_RACK_ASSET(asset_tag, rack_id)
VALUES('tag2', 1);

INSERT INTO T_RACK_ASSET(asset_tag, rack_id)
VALUES('tag3', 3);

INSERT INTO T_RACK_ASSET(asset_tag, rack_id)
VALUES('tag4', 1);

INSERT INTO T_TEAM_MEMBER(team_id, ctw_id, name)
VALUES(1,'CTW0001','João Pires');

INSERT INTO T_TEAM_MEMBER(team_id, ctw_id, name)
VALUES(2,'CTW0002','Amália Rodrigues');

INSERT INTO T_TEAM_MEMBER(team_id, ctw_id, name)
VALUES(3,'CTW0003','Alberto Meireles');

INSERT INTO T_TEAM_MEMBER(team_id, ctw_id, name)
VALUES(3,'CTW0004','Ana Luísa');

INSERT INTO T_TEAM_MEMBER(team_id, ctw_id, name)
VALUES(4,'CTW0005','António Costa');

INSERT INTO T_TEAM_MEMBER(team_id, ctw_id, name)
VALUES(4,'CTW0006','Catarina Silva');

INSERT INTO T_BOOKING(rack_id, requester_id, book_from, book_to)
VALUES(2, 1, '01-01-2024', '01-31-2024');

INSERT INTO T_BOOKING(rack_id, requester_id, book_from, book_to)
VALUES(4, 3, '07-01-2024', '07-31-2025');

INSERT INTO T_BOOKING(rack_id, requester_id, book_from, book_to)
VALUES(4, 4, '03-01-2024', '05-15-2024');

INSERT INTO T_BOOKING(rack_id, requester_id, book_from, book_to)
VALUES(3, 4, '01-01-2024', '03-01-2024');

INSERT INTO T_BOOKING(rack_id, requester_id, book_from, book_to)
VALUES(2, 2, '04-01-2024', '04-30-2024');
