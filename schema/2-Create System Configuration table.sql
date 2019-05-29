CREATE TABLE public.ng_nts_system_configuration (
  id SERIAL NOT NULL,
  key TEXT,
  value TEXT,
  PRIMARY KEY(id)
) ;

ALTER TABLE public.ng_nts_system_configuration
  ALTER COLUMN key SET STATISTICS 0;

ALTER TABLE public.ng_nts_system_configuration
  ALTER COLUMN value SET STATISTICS 0;