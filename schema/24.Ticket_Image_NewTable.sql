INSERT INTO ng_nts_lookup_types VALUES (11, 'Facility Status');


INSERT INTO ng_nts_lookup VALUES (29, 'Owned Apartment', 'Facility Status', 11);

INSERT INTO ng_nts_lookup VALUES (30, 'cancelled', 'Order Status', 8);



CREATE TABLE "ng_nts_ticket_image" (
  "recid" SERIAL PRIMARY KEY,
  "image_url" TEXT  ,
  "ticket_id" INTEGER  
);


ALTER TABLE public.ng_nts_ticket_image
  ADD CONSTRAINT ng_nts_ticket_image_fk FOREIGN KEY (ticket_id)
    REFERENCES public.ng_nts_tickets(recid)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    NOT DEFERRABLE;


CREATE INDEX idx_ng_nts_ticket_image__compound_id ON public.ng_nts_ticket_image
  USING btree (ticket_id);
  
  
--already existed (who was created this sequance?)
--tell me if anyone used it .... in any plase
--if not existed .... run the following sql 
--CREATE SEQUENCE public.ng_nts_ticket_image_recid_seq
--  INCREMENT 1 MINVALUE 1
--  MAXVALUE 9223372036854775807 START 1
--  CACHE 1;
--  
