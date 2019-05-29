ALTER TABLE ng_nts_bill_details
   ADD COLUMN usage_value numeric(12,2);

ALTER TABLE ng_nts_bill_details
  ADD COLUMN facility_id integer;
ALTER TABLE ng_nts_bill_details
  ADD FOREIGN KEY (facility_id) REFERENCES ng_nts_facilities (recid) 
  ON UPDATE CASCADE ON DELETE CASCADE;
  
 ALTER TABLE ng_nts_bill_calculation
  ADD COLUMN facility_id integer;
ALTER TABLE ng_nts_bill_calculation
  ADD FOREIGN KEY (facility_id) REFERENCES ng_nts_facilities (recid)  ON UPDATE CASCADE ON DELETE CASCADE;

  
INSERT INTO ng_nts_lookup VALUES (28, 'Done and billed', 'Order Status', 8);

   
