ALTER TABLE ng_nts_groups
   ADD COLUMN created_by integer;
   ALTER TABLE ng_nts_groups
  ADD CONSTRAINT created_by_fk FOREIGN KEY (created_by) REFERENCES ng_nts_employees (recid)
   ON UPDATE NO ACTION ON DELETE NO ACTION;
CREATE INDEX fki_created_by_fk
  ON ng_nts_groups(created_by);