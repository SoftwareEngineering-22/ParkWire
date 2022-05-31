CREATE TABLE users (
	username VARCHAR ( 50 ) PRIMARY KEY,
	password VARCHAR ( 50 ) NOT NULL,
	email VARCHAR ( 255 ) UNIQUE NOT NULL,
	isDriver BOOLEAN NOT NULL,
	isValet BOOLEAN NOT NULL,
	created_on TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE drivers (
    username VARCHAR ( 50 ) PRIMARY KEY,
    points BIGINT,
    FOREIGN KEY (username)
      REFERENCES users (username)
);

CREATE TABLE parked_driver (
    username VARCHAR ( 50 ) PRIMARY KEY,
    parkingLocLatitude FLOAT DEFAULT NULL,
    parkingLocLongitude FLOAT DEFAULT NULL,
    parkedTimestamp TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    timeEstimate TIMESTAMP WITH TIME ZONE,
    FOREIGN KEY (username)
      REFERENCES drivers (username),
      FOREIGN KEY (parkingLocLatitude)
      REFERENCES parking (parkingLocLatitude)
      ON DELETE SET NULL,
      FOREIGN KEY (parkingLocLongitude)
      REFERENCES parking (parkingLocLongitude)
      ON DELETE SET NULL
);

CREATE TABLE valet (
    username VARCHAR ( 50 ) PRIMARY KEY,
    businessName VARCHAR ( 255 ) NOT NULL,
    info TEXT,
    FOREIGN KEY (username)
      REFERENCES users (username)
);

CREATE TABLE parking(
    id serial PRIMARY KEY,
    parkingLocLatitude FLOAT UNIQUE NOT NULL,
    parkingLocLongitude FLOAT UNIQUE NOT NULL,
    isFree BOOLEAN NOT NULL,
    isMeter BOOLEAN NOT NULL
);

CREATE TABLE paid_parking(
    id serial UNIQUE,
    username VARCHAR ( 50 ) PRIMARY KEY,
    parkingLocLatitude FLOAT UNIQUE NOT NULL,
    parkingLocLongitude FLOAT UNIQUE NOT NULL,
    capacity INT,
    cost FLOAT,
    FOREIGN KEY (username)
      REFERENCES valet (username)
);

CREATE TABLE history(
    username VARCHAR ( 50 ) PRIMARY KEY,
    parking_id INT NOT NULL,
    payment FLOAT NOT NULL,
    parked TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    left_parking TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
     FOREIGN KEY (username)
      REFERENCES drivers (username),
    FOREIGN KEY (parking_id)
      REFERENCES paid_parking (id)
);

