ALTER TABLE ng_nts_employees
   ADD COLUMN created_by integer;
ALTER TABLE ng_nts_employees
  ADD CONSTRAINT fk_ng_nts_employees__createdby FOREIGN KEY (created_by)
      REFERENCES ng_nts_employees (recid) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE;
      
      
   ALTER TABLE ng_nts_menu
   ADD COLUMN description text;

   ALTER TABLE ng_nts_menu_item
   ADD COLUMN description text;
