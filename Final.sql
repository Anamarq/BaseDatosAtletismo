DROP TABLE ATLETAS CASCADE CONSTRAINTS;
DROP TABLE FEDERACIONes CASCADE CONSTRAINTS;
DROP TABLE COMPETICION CASCADE CONSTRAINTS;
DROP TABLE FotoAtleta CASCADE CONSTRAINTS;
DROP TABLE PARTICIPA CASCADE CONSTRAINTS;
DROP TABLE PRUEBAS CASCADE CONSTRAINTS;
DROP TABLE COMPITE CASCADE CONSTRAINTS;
DROP TABLE TIENE CASCADE CONSTRAINTS;

CREATE TABLE atletas (
    licencia                 VARCHAR2(32 CHAR) NOT NULL,
    nombre                   VARCHAR2(32 CHAR),
    apellidos                VARCHAR2(32 CHAR),
    sexo                     VARCHAR2(32 CHAR),
    fechanacimiento          DATE,
    federaciones_federacion  VARCHAR2(32 CHAR),
    ciudad                   VARCHAR2(32 CHAR)
);

ALTER TABLE atletas
    ADD CHECK ( sexo IN ( 'Femenino', 'Masculino' ) );

ALTER TABLE atletas ADD CONSTRAINT atletas_pk PRIMARY KEY ( licencia );

CREATE TABLE competicion (
    descripcion  VARCHAR2(64 CHAR) NOT NULL,
    sede         VARCHAR2(32 CHAR) NOT NULL,
    fecha        DATE NOT NULL
);

ALTER TABLE competicion ADD CONSTRAINT competicion_pk PRIMARY KEY ( descripcion );

CREATE TABLE compite (
    competicion_descripcion  VARCHAR2(64 CHAR) NOT NULL,
    atletas_licencia         VARCHAR2(32 CHAR) NOT NULL
);

ALTER TABLE compite ADD CONSTRAINT compite_pk PRIMARY KEY ( competicion_descripcion,
                                                            atletas_licencia );

CREATE TABLE federaciones (
    federacion    VARCHAR2(32 CHAR) NOT NULL,
    telefono      INTEGER,
    web           VARCHAR2(32 CHAR),
    localización  VARCHAR2(32 CHAR)
);

ALTER TABLE federaciones ADD CONSTRAINT federaciones_pk PRIMARY KEY ( federacion );

CREATE TABLE fotoatleta (
    atletas_licencia  VARCHAR2(32 CHAR) NOT NULL,
    foto              BLOB,
    nombre            VARCHAR2(32 CHAR)
    
);

CREATE UNIQUE INDEX fotoatleta__idx ON
    fotoatleta (
        atletas_licencia
    ASC );

ALTER TABLE fotoatleta ADD CONSTRAINT fotoatleta_pk PRIMARY KEY ( atletas_licencia );

CREATE TABLE participa (
    atletas_licencia  VARCHAR2(32 CHAR) NOT NULL,
    pruebas_prueba    VARCHAR2(32 CHAR) NOT NULL,
    resultado         NUMBER(6, 2)
);

ALTER TABLE participa ADD CONSTRAINT participa_pk PRIMARY KEY ( atletas_licencia,
                                                                pruebas_prueba );

CREATE TABLE pruebas (
    prueba  VARCHAR2(32 CHAR) NOT NULL,
    tipo    VARCHAR2(32 CHAR) NOT NULL
);

ALTER TABLE pruebas ADD CONSTRAINT pruebas_pk PRIMARY KEY ( prueba );

CREATE TABLE tiene (
    competicion_descripcion  VARCHAR2(64 CHAR) NOT NULL,
    pruebas_prueba           VARCHAR2(32 CHAR) NOT NULL,
    hora                     DATE
);

ALTER TABLE tiene ADD CONSTRAINT tiene_pk PRIMARY KEY ( competicion_descripcion,
                                                        pruebas_prueba );

ALTER TABLE atletas
    ADD CONSTRAINT atletas_federaciones_fk FOREIGN KEY ( federaciones_federacion )
        REFERENCES federaciones ( federacion );

ALTER TABLE compite
    ADD CONSTRAINT compite_atletas_fk FOREIGN KEY ( atletas_licencia )
        REFERENCES atletas ( licencia );

ALTER TABLE compite
    ADD CONSTRAINT compite_competicion_fk FOREIGN KEY ( competicion_descripcion )
        REFERENCES competicion ( descripcion );

ALTER TABLE fotoatleta
    ADD CONSTRAINT fotoatleta_atletas_fk FOREIGN KEY ( atletas_licencia )
        REFERENCES atletas ( licencia );

ALTER TABLE participa
    ADD CONSTRAINT participa_atletas_fk FOREIGN KEY ( atletas_licencia )
        REFERENCES atletas ( licencia );

ALTER TABLE participa
    ADD CONSTRAINT participa_pruebas_fk FOREIGN KEY ( pruebas_prueba )
        REFERENCES pruebas ( prueba );

ALTER TABLE tiene
    ADD CONSTRAINT tiene_competicion_fk FOREIGN KEY ( competicion_descripcion )
        REFERENCES competicion ( descripcion );

ALTER TABLE tiene
    ADD CONSTRAINT tiene_pruebas_fk FOREIGN KEY ( pruebas_prueba )
        REFERENCES pruebas ( prueba );



---insertar ( he insertado todos los datos directamente desde la interfaz gráfica de java, aqui solo uno de prueba)
---No es obligatorio, puede funcionar sin insertar nada
Insert into FEDERACIONES(FEDERACION,TELEFONO,WEB,LOCALIZACIÓN) values('Holanda','31306087300','www.siggs.eu/partner/nocnsf','IJSSELSTEIN ');
Insert into COMPETICION(SEDE,FECHA,DESCRIPCION) values('Doha', to_date('27/10/19','DD/MM/RR'), 'Mundial 2019');
Insert into ATLETAS(LICENCIA,NOMBRE,APELLIDOS,SEXO,FECHANACIMIENTO,FEDERACIONES_FEDERACION,CIUDAD) values('HO1908','Sifan','Hassan','Femenino',to_date('01/01/93','DD/MM/RR'),'Holanda','Adama');
Insert into COMPITE(COMPETICION_DESCRIPCION, ATLETAS_LICENCIA) values('Mundial 2019','HO1908');
Insert into PRUEBAS(PRUEBA,TIPO) values('1500ml','velocidad'); 
Insert into TIENE(COMPETICION_DESCRIPCION,PRUEBAS_PRUEBA, HORA) values('Mundial 2019','1500ml',to_date('05/10/19 18:55','DD/MM/RR HH24:MI'));
Insert into PARTICIPA(ATLETAS_LICENCIA,PRUEBAS_PRUEBA,RESULTADO) values('HO1908','1500ml','231,95');


/*
--SELECT * FROM ATLETAS;
SELECT * FROM PARTICIPA;
SELECT* FROM COMPETICION;
Insert into COMPETICION(SEDE,FECHA) values('Sevilla', to_date('02/12/97','DD/MM/RR'));
Select* from atletas;
select* from pruebas;
select* from fotoatleta;
---Insertar comp
Insert into COMPETICION(SEDE,FECHA,DESCRIPCION) values('Malaga', to_date('02/01/97','DD/MM/RR'), 'Desc');
---

---Insertar Fed
Insert into FEDERACIONES(FEDERACION,TELEFONO,WEB,LOCALIZACIÓN) values('JET','7827391','www.ajskd2','Direcc');
----Buscar fed
select* from federaciones where federacion='JET';
---Borrar fed
delete federaciones where federacion='JET';
---Atl en fed
select * from atletas where federaciones_federacion='Estados Unidos';
--Lista fed
select* from federaciones;

--Lista atl
select atletas.* from atletas join compite on atletas_licencia=licencia where competicion_descripcion ='Descripcion';

--Buscar atl
select atletas.* from atletas join compite on atletas_licencia=licencia where competicion_descripcion ='Desc' and atletas_licencia='442314';

--Añadir atleta

Insert into ATLETAS(LICENCIA,NOMBRE,APELLIDOS,SEXO,FECHANACIMIENTO,FEDERACIONES_FEDERACION,CIUDAD) values('445','Alvaro','Rodriguez','Masculino',to_date('02/01/97','DD/MM/RR'),'JET','Sevilla');
Insert into COMPITE(COMPETICION_DESCRIPCION, ATLETAS_LICENCIA) values('Descripcion','445');

--Borrar atleta
delete compite where competicion_descripcion='Desc' and atletas_licencia='442314';

--resultados totales

select competicion_descripcion, atletas.licencia, atletas.Nombre, atletas.apellidos,tiene.pruebas_prueba AS prueba,participa.resultado  from (participa join tiene on participa.pruebas_prueba=tiene.pruebas_prueba)join atletas on licencia=atletas_licencia
where competicion_descripcion ='Olimpiadas 2016' and sexo= 'Masculino';

select* from tiene;

--resultados por prueba
select atletas.licencia, atletas.Nombre, atletas.apellidos,participa.resultado  from ((participa join tiene on participa.pruebas_prueba=tiene.pruebas_prueba) join atletas on licencia=atletas_licencia)
where (competicion_descripcion ='Mundial 2019' and participa.pruebas_prueba='Longitud') and sexo='Masculino'
order by
case when participa.pruebas_prueba='100ml' or participa.pruebas_prueba='400ml' 
or participa.pruebas_prueba='1500ml' or participa.pruebas_prueba='100/110m vallas' then resultado end ,
case when participa.pruebas_prueba='Altura' or participa.pruebas_prueba='Longitud' or participa.pruebas_prueba='Pertiga' or participa.pruebas_prueba='Triple' 
or participa.pruebas_prueba='Jabalina' or participa.pruebas_prueba='Peso' or participa.pruebas_prueba='Martillo' or participa.pruebas_prueba='Disco'
then resultado end desc;

--resultados por atleta
select atletas.licencia, atletas.Nombre, atletas.apellidos,participa.pruebas_prueba AS prueba,participa.resultado  from ((participa join tiene on participa.pruebas_prueba=tiene.pruebas_prueba) join atletas on licencia=atletas_licencia)
where (competicion_descripcion ='Descripcion' and licencia='445') and sexo='Masculino';

--Añadir resultado
Insert into PARTICIPA(ATLETAS_LICENCIA,PRUEBAS_PRUEBA,RESULTADO) values('445','60ml','7,50');
delete participa where atletas_licencia='100ml'and pruebas_prueba='220ml'; 

--Competicion mejores marcas
--NO ordena bien
--select atletas.licencia, atletas.Nombre, atletas.apellidos,participa.resultado  from (participa join atletas on licencia=atletas_licencia)join pruebas on participa.pruebas_prueba=prueba
--where  pruebas_prueba='Longitud' and sexo='Masculino'
--order by
-- case when Tipo='velocidad' then resultado end,
 --case when Tipo='peso' or Tipo='Saltos' then resultado end desc;


select atletas.licencia, atletas.Nombre, atletas.apellidos,participa.resultado from participa join atletas on licencia=atletas_licencia 
where  pruebas_prueba='Longitud' and sexo='Masculino'
order by
case when participa.pruebas_prueba='100ml' or participa.pruebas_prueba='400ml' 
or participa.pruebas_prueba='1500ml' or participa.pruebas_prueba='100/110m vallas' then resultado end ,
case when participa.pruebas_prueba='Altura' or participa.pruebas_prueba='Longitud' or participa.pruebas_prueba='Pertiga' or participa.pruebas_prueba='Triple' 
or participa.pruebas_prueba='Jabalina' or participa.pruebas_prueba='Peso' or participa.pruebas_prueba='Martillo' or participa.pruebas_prueba='Disco'
then resultado end desc;


 --Añadir prueba por primera vez
Insert into PRUEBAS(PRUEBA,TIPO) values('60ml','velocidad') --,to_date('13:45','HH24:MI'));
--Añadir prueba a competicion
Insert into TIENE(COMPETICION_DESCRIPCION,PRUEBAS_PRUEBA, HORA) values('Descripcion','60ml');
--Horario
select tiene.hora, pruebas.* from pruebas join Tiene on prueba=pruebas_prueba where competicion_descripcion='Mundial 2019' order by hora;

------------------------------------------------------
select* from tiene;
Insert into COMPETICION(SEDE,FECHA,DESCRIPCION) values('Cadiz', to_date('07/10/98','DD/MM/RR'), 'descda');
select* from Federaciones where(competicion_sede='Doha' and competicion_fecha= '27/09/19');
select foto from FotoAtleta where atletas_licencia= 'EU0303' for update
select*from participa
Insert into FEDERACIONES(FEDERACION,TELEFONO,WEB,LOCALIZACIÓN,Competicion_descripcion) values('JET','7827391','www.ajskd2','Direcc','descda');
*/