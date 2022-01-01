CREATE TABLE meetup.payers
(
    ID UUID UNIQUE NOT NULL,
    LOGIN VARCHAR(100) NOT NULL,
    EMAIL VARCHAR (255) NOT NULL,
    FIRST_NAME VARCHAR(50) NOT NULL,
    LAST_NAME VARCHAR(50) NOT NULL,
    CONSTRAINT PK_PAYERS PRIMARY KEY (ID)
);