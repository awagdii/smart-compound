alter table ng_nts_groups
add compound_id integer ,
 ADD FOREIGN KEY (compound_id) REFERENCES ng_nts_compounds(recid) ON DELETE CASCADE;