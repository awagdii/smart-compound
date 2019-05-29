ALTER TABLE public.ng_nts_compounds
  ADD COLUMN first_email TEXT;


ALTER TABLE public.ng_nts_compounds
  ADD COLUMN second_email TEXT;


ALTER TABLE public.ng_nts_compounds
  ADD COLUMN third_email TEXT;

ALTER TABLE public.ng_nts_compounds
  ADD COLUMN first_phone_number TEXT;

ALTER TABLE public.ng_nts_compounds
  ADD COLUMN second_phone_number TEXT;

ALTER TABLE public.ng_nts_compounds
  ADD COLUMN third_phone_number TEXT;

ALTER TABLE public.ng_nts_tenants
  ADD COLUMN tenant_image TEXT;

