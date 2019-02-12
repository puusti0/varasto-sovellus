# Palvelin

* __IP__: 10.114.34.75
* __Jenkins__: https://inno-jenkinsnew.educloud.metropolia.fi/ 

Komento 		   | Selitys
------------------ | -------------
cd / 			   | root directory


# MariaDB

Huom. Omilla tunnuksilla kirjaudutaan koneelle, mutta ensimmaisella kerralla tietokannan kayttajana root (demouser)

**/usr/bin/mysql -u demouser -p**		MariaDB komentorivi / SQL kysely ikkuna / MariaDB [(none)] > (u = user, p = password)

Huom. Salasana kysytaan komennon suoritamisen jalkeen

### Luo ensimmaisella kerralla itselle kayttaja
1. __CREATE USER 'demouser'@'localhost' IDENTIFIED BY 'demopassword';__ 		Lisaa uusi kayttaja
2. __GRANT ALL PRIVILEGES ON &ast;.&ast; TO demouser@localhost;__  Lisaa kaikki kayttajaoikeudet

Huom. __&ast;.&ast;__ antaa oikeudet kaikille tietokannoille MariaDB ja __varasto.*__ vain varasto tietokantaa

3. __FLUSH PRIVILEGES;__ 		Aseta kayttooikeudet voimaan 
4. __SHOW GRANTS FOR 'demouser'@'localhost';__	Listaa oikeudet
5. __exit__ Poistu MariaDB:sta ja kirjaudu uudelleen luomallasi kayttajalla 

## Tietokannan kaytto (SQL komennot + ...)

**SELECT User, Host, Password FROM mysql.user;**	Listaa kayttajatietoja (etenkin CREATE USER jalkeen)

**CREATE DATABASE varasto;**	Tietokannan luominen

**USE varasto;**  Siirry kayttamaan varasto tietokantaa, jonne luodaan taulut  

## HeidiSQL etäyhteys (koulun verkosta, vaatii putkituksen muualta)
1. Tee itsellesi tunnus tietokantaan seuraavilla käskyillä:  
__CREATE USER 'user'@'localhost' IDENTIFIED BY 'some_pass';__  
__GRANT ALL PRIVILEGES ON *.* TO 'user'@'localhost' WITH GRANT OPTION;__  
__CREATE USER 'user'@'%' IDENTIFIED BY 'some_pass';__  
__GRANT ALL PRIVILEGES ON *.* TO 'user'@'%' WITH GRANT OPTION;__  
__FLUSH PRIVILEGES;__

2. Lataa HeidiSQL osoitteesta: https://www.heidisql.com/
3. Ota yhteys osoitteeseen 10.114.34.75 (Portti: 3306)

## Asetukset (TEHTY)

**/usr/bin/mysql_secure_installation**		Salasana ja oikeudet

**sudo systemctl start mariadb.service**	Kaynnista MariaDB

**sudo systemctl stop mariadb.service**		Sammuta MariaDB

**sudo systemctl enable maria.service**		Uudelleen kaynnistamisen yhteydessa kaynnistaa automaattisesti MariaDB


# Lahteet

MariaDB - https://support.rackspace.com/how-to/installing-mariadb-server-on-centos/

MARKDOWN - https://guides.github.com/features/mastering-markdown/
