CREATE TABLE public.ng_nts_bill_calculation (
  recid SERIAL,
  calculation_date DATE,
  previous_meter_reading NUMERIC(12,2),
  current_meter_reading NUMERIC(12,2),
  installation_value NUMERIC(12,2),
  nrc_value NUMERIC(12,2),
  mrc_value NUMERIC(12,2),
  usage_amount NUMERIC(12,2),
  usage_value NUMERIC(12,2),
  calculation_base TEXT,
  description TEXT,
  calculated_by TEXT,
  compound_id INTEGER,
  service_id INTEGER,
  order_id INTEGER,
  PRIMARY KEY(recid)
) ;

ALTER TABLE public.ng_nts_bill_calculation
  ADD CONSTRAINT ng_nts_bill_calculation_fk FOREIGN KEY (compound_id)
    REFERENCES public.ng_nts_compounds(recid)
    ON DELETE CASCADE
    ON UPDATE CASCADE
    NOT DEFERRABLE;

ALTER TABLE public.ng_nts_bill_calculation
  ADD CONSTRAINT ng_nts_bill_calculation_fk1 FOREIGN KEY (service_id)
    REFERENCES public.ng_nts_services(recid)
    ON DELETE CASCADE
    ON UPDATE CASCADE
    NOT DEFERRABLE;

ALTER TABLE public.ng_nts_bill_calculation
  ADD CONSTRAINT ng_nts_bill_calculation_fk2 FOREIGN KEY (order_id)
    REFERENCES public.ng_nts_orders(recid)
    ON DELETE CASCADE
    ON UPDATE CASCADE
    NOT DEFERRABLE;