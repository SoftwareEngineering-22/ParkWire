CREATE TABLE public.users (
	username varchar(50) NOT NULL,
	password varchar(50) NOT NULL,
	email varchar(255) NOT NULL,
	isdriver bool NOT NULL,
	isvalet bool NOT NULL,
	created_on timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT users_email_key UNIQUE (email),
	CONSTRAINT users_pkey PRIMARY KEY (username)
);

CREATE TABLE public.drivers (
	username varchar(50) NOT NULL,
	points int8 NULL,
	CONSTRAINT drivers_pkey PRIMARY KEY (username),
	CONSTRAINT drivers_username_fkey FOREIGN KEY (username) REFERENCES public.users(username)
);

CREATE TABLE public.parked_driver (
	username varchar(50) NOT NULL,
	parkingloclatitude float8 NULL,
	parkingloclongitude float8 NULL,
	parkedtimestamp timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	timeestimate timestamp NULL,
	CONSTRAINT parked_driver_pkey PRIMARY KEY (username),
	CONSTRAINT parked_driver_parkingloclatitude_fkey FOREIGN KEY (parkingloclatitude) REFERENCES public.parking(parkingloclatitude) ON DELETE SET NULL,
	CONSTRAINT parked_driver_parkingloclongitude_fkey FOREIGN KEY (parkingloclongitude) REFERENCES public.parking(parkingloclongitude) ON DELETE SET NULL,
	CONSTRAINT parked_driver_username_fkey FOREIGN KEY (username) REFERENCES public.drivers(username)
);

CREATE TABLE public.valet (
	username varchar(50) NOT NULL,
	businessname varchar(255) NOT NULL,
	info text NULL,
	CONSTRAINT valet_pkey PRIMARY KEY (username),
	CONSTRAINT valet_username_fkey FOREIGN KEY (username) REFERENCES public.users(username)
);

CREATE TABLE public.parking (
	id serial4 NOT NULL,
	parkingloclatitude float8 NOT NULL,
	parkingloclongitude float8 NOT NULL,
	isfree bool NOT NULL,
	ismeter bool NOT NULL,
	CONSTRAINT parking_parkingloclatitude_key UNIQUE (parkingloclatitude),
	CONSTRAINT parking_parkingloclongitude_key UNIQUE (parkingloclongitude),
	CONSTRAINT parking_pkey PRIMARY KEY (id)
);

CREATE TABLE public.paid_parking (
	id serial4 NOT NULL,
	username varchar(50) NOT NULL,
	parkingloclatitude float8 NOT NULL,
	parkingloclongitude float8 NOT NULL,
	capacity int4 NOT NULL,
	"cost" float8 NOT NULL,
	contact_information varchar NOT NULL,
	work_hours text NOT NULL,
	address varchar NOT NULL,
	CONSTRAINT paid_parking_parkingloclatitude_key UNIQUE (parkingloclatitude),
	CONSTRAINT paid_parking_parkingloclongitude_key UNIQUE (parkingloclongitude),
	CONSTRAINT "paid_parking_pkΕΥ" PRIMARY KEY (id),
	CONSTRAINT paid_parking_username_fkey FOREIGN KEY (username) REFERENCES public.valet(username)
);

CREATE TABLE public.driver_history (
	username varchar(50) NOT NULL,
	parking_id int4 NOT NULL,
	payment float8 NOT NULL,
	parked timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	left_parking timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT history_pkey PRIMARY KEY (username),
	CONSTRAINT driver_history_fk FOREIGN KEY (parking_id) REFERENCES public.paid_parking(id),
	CONSTRAINT history_username_fkey FOREIGN KEY (username) REFERENCES public.drivers(username)
);

