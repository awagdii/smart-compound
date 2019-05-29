-- Table: ng_nts_bill_cycle_history

-- DROP TABLE ng_nts_bill_cycle_history;

CREATE TABLE ng_nts_bill_cycle_history
(
  recid serial NOT NULL,
  compound_id integer,
  month integer,
  year integer,
  has_ended integer,
  CONSTRAINT ng_nts_bill_cycle_history_pkey PRIMARY KEY (recid),
  CONSTRAINT ng_nts_bill_cycle_history_compund_id_fkey FOREIGN KEY (compound_id)
      REFERENCES ng_nts_compounds (recid) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE ng_nts_bill_cycle_history
  OWNER TO postgres;
