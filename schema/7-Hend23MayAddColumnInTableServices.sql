ALTER TABLE public.ng_nts_services
  ADD COLUMN flat_or_usage_lookup INTEGER;
  
  
  ALTER TABLE public.ng_nts_services
  ADD CONSTRAINT ng_nts_usage_lookup_fk FOREIGN KEY (flat_or_usage_lookup)
    REFERENCES public.ng_nts_lookup(recid)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    NOT DEFERRABLE;