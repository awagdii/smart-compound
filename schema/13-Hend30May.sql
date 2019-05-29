ALTER TABLE ng_nts_services
   ADD COLUMN activity_status_lookup integer;
   
   ALTER TABLE ng_nts_services
  ADD CONSTRAINT ng_nts_activity_lookup_fk FOREIGN KEY (activity_status_lookup) REFERENCES ng_nts_lookup (recid)
   ON UPDATE NO ACTION ON DELETE NO ACTION;
CREATE INDEX fki_ng_nts_activity_lookup_fk
  ON ng_nts_services(activity_status_lookup);