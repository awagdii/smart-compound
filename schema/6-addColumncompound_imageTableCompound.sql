

ALTER TABLE public.ng_nts_compounds
ADD COLUMN compound_image TEXT;
ALTER TABLE public.ng_nts_compounds
ALTER COLUMN compound_image SET DEFAULT NULL;