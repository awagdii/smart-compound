ALTER TABLE public.ng_nts_facilities
  RENAME COLUMN facility_lookup_id TO facility_type_lookup_id;
  
  
ALTER TABLE public.ng_nts_orders
  ADD COLUMN status_lookup_id INTEGER;
  
  
ALTER TABLE public.ng_nts_orders
  ADD CONSTRAINT ng_nts_orders_fk FOREIGN KEY (status_lookup_id)
    REFERENCES public.ng_nts_lookup(recid)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    NOT DEFERRABLE;
    
    
    
ALTER TABLE public.ng_nts_facilities
  ADD COLUMN status_lookup_id INTEGER;
  
ALTER TABLE public.ng_nts_facilities
  ADD CONSTRAINT ng_nts_facilities_fk FOREIGN KEY (status_lookup_id)
    REFERENCES public.ng_nts_lookup(recid)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    NOT DEFERRABLE;
  