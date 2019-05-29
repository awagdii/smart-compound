ALTER TABLE public.ng_nts_orders
  ADD COLUMN meter_last_reading NUMERIC(12,2);
  
ALTER TABLE public.ng_nts_orders
  ADD COLUMN meter_last_reading_date DATE;