-- Table: ng_nts_bill

-- DROP TABLE ng_nts_bill;

CREATE TABLE ng_nts_bill
(
  recid serial NOT NULL,
  bill_number integer,
  issue_date date,
  bill_from date,
  bill_to date,
  tenant_id integer,
  total_amount numeric(12,2),
  collected integer,
  collection_date date,
  payment_method text,
  collected_by text,
  compound_id integer,
  CONSTRAINT ng_nts_bill_pkey PRIMARY KEY (recid),
  CONSTRAINT ng_nts_bill_compound_id_fkey FOREIGN KEY (compound_id)
      REFERENCES ng_nts_compounds (recid) MATCH SIMPLE
      ON DELETE CASCADE
  ON UPDATE CASCADE,
  CONSTRAINT ng_nts_bill_tenant_id_fkey FOREIGN KEY (tenant_id)
      REFERENCES ng_nts_tenants (recid) MATCH SIMPLE
      ON DELETE CASCADE
  ON UPDATE CASCADE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE ng_nts_bill
  OWNER TO postgres;

 -- Table: ng_nts_bill_details

-- DROP TABLE ng_nts_bill_details;

CREATE TABLE ng_nts_bill_details
(
  recid serial NOT NULL,
  service_id integer,
  nrc_value numeric(12,2),
  mrc_value numeric(12,2),
  installation numeric(12,2),
  total_amount numeric(12,2),
  bill_id integer,
  compound_id integer,
  CONSTRAINT ng_nts_bill_details_pkey PRIMARY KEY (recid),
  CONSTRAINT ng_nts_bill_details_bill_id_fkey FOREIGN KEY (bill_id)
      REFERENCES ng_nts_bill (recid) MATCH SIMPLE
      ON DELETE CASCADE
  ON UPDATE CASCADE,
  CONSTRAINT ng_nts_bill_details_compound_id_fkey FOREIGN KEY (compound_id)
      REFERENCES ng_nts_compounds (recid) MATCH SIMPLE
      ON DELETE CASCADE
  ON UPDATE CASCADE,
  CONSTRAINT ng_nts_bill_details_service_id_fkey FOREIGN KEY (service_id)
      REFERENCES ng_nts_services (recid) MATCH SIMPLE
      ON DELETE CASCADE
  ON UPDATE CASCADE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE ng_nts_bill_details
  OWNER TO postgres;
