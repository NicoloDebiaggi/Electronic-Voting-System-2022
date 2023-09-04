BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "partiti" (
	"nomeId"	TEXT,
	"displayName"	TEXT,
	PRIMARY KEY("nomeId")
);
CREATE TABLE IF NOT EXISTS "scheda" (
	"id"	TEXT,
	"data"	TEXT,
	"tipo"	TEXT,
	"win"	TEXT,
	PRIMARY KEY("id")
);
CREATE TABLE IF NOT EXISTS "modUsers" (
	"username"	TEXT,
	"password"	TEXT,
	"name"	TEXT,
	"surname"	TEXT,
	PRIMARY KEY("username")
);
CREATE TABLE IF NOT EXISTS "elections" (
	"data"	TEXT,
	"tipoVotazione"	TEXT,
	"tipoVittoria"	TEXT,
	"status"	INTEGER,
	"twoPrefs"	INTEGER,
	"referendumQuest"	TEXT
);
CREATE TABLE IF NOT EXISTS "candidatiPartito" (
	"partito"	TEXT,
	"candidato"	TEXT,
	PRIMARY KEY("partito","candidato"),
	FOREIGN KEY("candidato") REFERENCES "candidati"("cf") ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY("partito") REFERENCES "partiti"("nomeId") ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS "baseUsers" (
	"username"	TEXT,
	"password"	TEXT,
	"name"	TEXT,
	"surname"	TEXT,
	"voted"	INTEGER,
	PRIMARY KEY("username")
);
CREATE TABLE IF NOT EXISTS "candidati" (
	"nome"	TEXT,
	"cognome"	TEXT,
	"cf"	TEXT,
	"sesso"	TEXT,
	PRIMARY KEY("cf")
);
