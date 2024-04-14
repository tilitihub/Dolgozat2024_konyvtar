CREATE TABLE Kolcsonzok (
    id INTEGER PRIMARY KEY,
    nev TEXT,
    szulIdo TEXT
);

CREATE TABLE Kolcsonzesek (
    id INTEGER PRIMARY KEY,
    kolcsonzokId INTEGER,
    iro TEXT,
    mufaj TEXT,
    cim TEXT,
    FOREIGN KEY(kolcsonzokId) REFERENCES Kolcsonzok(id)
);