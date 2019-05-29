CREATE TABLE public.ng_nts_employee_groups (
  recid SERIAL,
  employee_id INTEGER,
  group_id INTEGER
) ;

ALTER TABLE public.ng_nts_employee_groups
  ADD PRIMARY KEY (recid);

ALTER TABLE public.ng_nts_employee_groups
  ADD CONSTRAINT ng_nts_employee_groups_fk FOREIGN KEY (employee_id)
    REFERENCES public.ng_nts_employees(recid)
    ON DELETE CASCADE
    ON UPDATE CASCADE
    NOT DEFERRABLE;

ALTER TABLE public.ng_nts_employee_groups
  ADD CONSTRAINT ng_nts_employee_groups_fk1 FOREIGN KEY (group_id)
    REFERENCES public.ng_nts_groups(recid)
    ON DELETE CASCADE
    ON UPDATE CASCADE
    NOT DEFERRABLE;
    
