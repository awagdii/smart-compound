CREATE TABLE public.ng_nts_countries
(
  recid serial NOT NULL,
  name text,
  CONSTRAINT ng_nts_countries_pkey PRIMARY KEY (recid)
)
WITH (
  OIDS=FALSE
);


CREATE TABLE public.ng_nts_cities
(
   recid serial, 
   name text, 
   country_id integer, 
   PRIMARY KEY (recid) USING INDEX TABLESPACE pg_default, 
   FOREIGN KEY (country_id) REFERENCES ng_nts_countries (recid) ON UPDATE CASCADE ON DELETE CASCADE
) 
WITH (
  OIDS = FALSE
)
;

CREATE TABLE public.ng_nts_nationalities
(
  recid serial NOT NULL,
  name text,
  CONSTRAINT ng_nts_nationalities_pkey PRIMARY KEY (recid)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE ng_nts_nationalities
  OWNER TO postgres;
  
  
  INSERT INTO ng_nts_countries(
             name)
    VALUES ( 'Egypt');
  INSERT INTO ng_nts_countries(
             name)
    VALUES ( 'USA');
    INSERT INTO ng_nts_countries(
             name)
    VALUES ( 'France');
       INSERT INTO ng_nts_countries(
             name)
    VALUES ( 'Germany');
  INSERT INTO ng_nts_cities(
             name, country_id)
    VALUES ( 'Cairo', 1);
    INSERT INTO ng_nts_cities(
             name, country_id)
    VALUES ( 'Giza', 1);
    INSERT INTO ng_nts_cities(
             name, country_id)
    VALUES ( 'Alex', 1);
    INSERT INTO ng_nts_cities(
             name, country_id)
    VALUES ( 'New York', 2);
    INSERT INTO ng_nts_cities(
             name, country_id)
    VALUES ( 'Chicago', 2);
    INSERT INTO ng_nts_cities(
             name, country_id)
    VALUES ( 'Paris', 3);
      INSERT INTO ng_nts_cities(
             name, country_id)
    VALUES ( 'Frankfurt', 4);
    
  	INSERT INTO ng_nts_nationalities(name)
    VALUES ('Egyption' );
  	INSERT INTO ng_nts_nationalities(name)
    VALUES ('American' );
    INSERT INTO ng_nts_nationalities(name)
    VALUES ('French' );
        INSERT INTO ng_nts_nationalities(name)
    VALUES ('German' );
    
    INSERT INTO ng_nts_lookup_types(
            recid, type_name)
    VALUES (9, 'Best Connection Method');
	INSERT INTO ng_nts_lookup_types(
            recid, type_name)
    VALUES (7, 'Identification Type');
    INSERT INTO ng_nts_lookup(
             lookup_name, type_name, type_id)
    VALUES ( 'Phone', 'Best Connection Method', 9);
    INSERT INTO ng_nts_lookup(
             lookup_name, type_name, type_id)
    VALUES ( 'Mobile', 'Best Connection Method', 9);
	INSERT INTO ng_nts_lookup(
             lookup_name, type_name, type_id)
    VALUES ( 'Mail', 'Best Connection Method', 9);

  	INSERT INTO ng_nts_lookup(
             lookup_name, type_name, type_id)
    VALUES ( 'Passport', 'Identification Type', 7);
    INSERT INTO ng_nts_lookup(
             lookup_name, type_name, type_id)
    VALUES ( 'National ID', 'Identification Type', 7);

