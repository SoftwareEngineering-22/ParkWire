CREATE TABLE users (
	user_id serial PRIMARY KEY,
	username VARCHAR ( 50 ) UNIQUE NOT NULL,
	password VARCHAR ( 50 ) NOT NULL,
	email VARCHAR ( 255 ) UNIQUE NOT NULL,
	isDriver BOOLEAN NOT NULL,
	isValet BOOLEAN NOT NULL,
	created_on TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE drivers (
    user_id INT PRIMARY KEY NOT NULL,
    points BIGINT,
    FOREIGN KEY (user_id)
      REFERENCES users (user_id)
);

CREATE TABLE parked_driver (
    user_id INT PRIMARY KEY NOT NULL,
    parkingLocLatitude FLOAT DEFAULT NULL,
    parkingLocLongitude FLOAT DEFAULT NULL,
    parkedTimestamp TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    timeEstimate TIMESTAMP WITH TIME ZONE,
    FOREIGN KEY (user_id)
      REFERENCES users (user_id),
      FOREIGN KEY (parkingLocLatitude)
      REFERENCES parking (parkingLocLatitude)
      ON DELETE SET NULL,
      FOREIGN KEY (parkingLocLongitude)
      REFERENCES parking (parkingLocLongitude)
      ON DELETE SET NULL
);

CREATE TABLE valet (
    user_id INT PRIMARY KEY NOT NULL,
    businessName VARCHAR ( 255 ) NOT NULL,
    info TEXT,
    FOREIGN KEY (user_id)
      REFERENCES users (user_id)
);

CREATE TABLE parking(
    id serial PRIMARY KEY,
    parkingLocLatitude FLOAT UNIQUE NOT NULL,
    parkingLocLongitude FLOAT UNIQUE NOT NULL,
    isFree BOOLEAN NOT NULL,
    isMeter BOOLEAN NOT NULL
);

CREATE TABLE paid_parking(
    id serial PRIMARY KEY,
    parkingLocLatitude FLOAT UNIQUE NOT NULL,
    parkingLocLongitude FLOAT UNIQUE NOT NULL,
    capacity INT,
    cost FLOAT,
    user_id INT NOT NULL,
    FOREIGN KEY (user_id)
      REFERENCES valet (user_id)
);

CREATE TABLE history(
    id serial PRIMARY KEY,
    user_id INT NOT NULL,
    parking_id INT NOT NULL,
    payment FLOAT NOT NULL,
    parked TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    left_parking TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
     FOREIGN KEY (user_id)
      REFERENCES drivers (user_id),
    FOREIGN KEY (parking_id)
      REFERENCES paid_parking (id)
);

