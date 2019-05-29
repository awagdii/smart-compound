CREATE TABLE public.ng_nts_ticket_statuses (
  recid SERIAL,
  name VARCHAR(40),
  PRIMARY KEY(recid)
) ;
CREATE TABLE public.ng_nts_ticket_status_sequences (
  recid SERIAL NOT NULL,
  to_ticket_status SERIAL,
  from_ticket_status SERIAL,
  PRIMARY KEY(recid)
) ;
ALTER TABLE public.ng_nts_ticket_status_sequences
  ADD CONSTRAINT ng_nts_ticket_status_sequences_fk FOREIGN KEY (to_ticket_status)
    REFERENCES public.ng_nts_ticket_statuses(recid)
    ON DELETE CASCADE
    ON UPDATE CASCADE
    NOT DEFERRABLE;
ALTER TABLE public.ng_nts_ticket_status_sequences
  ADD CONSTRAINT ng_nts_ticket_status_sequences_fk1 FOREIGN KEY (from_ticket_status)
    REFERENCES public.ng_nts_ticket_statuses(recid)
    ON DELETE CASCADE
    ON UPDATE CASCADE
    NOT DEFERRABLE;