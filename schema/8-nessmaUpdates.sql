ALTER TABLE ng_nts_compounds ADD COLUMN domain text;
ALTER TABLE ng_nts_tickets
   ADD COLUMN ticket_last_status_id integer;

ALTER TABLE ng_nts_tickets
  ADD CONSTRAINT ticket_ticket_status_id_fk FOREIGN KEY (ticket_last_status_id) REFERENCES ng_nts_ticket_statuses (recid)
   ON UPDATE NO ACTION ON DELETE NO ACTION;
CREATE INDEX fki_ticket_ticket_status_id_fk
  ON ng_nts_tickets(ticket_last_status_id);

