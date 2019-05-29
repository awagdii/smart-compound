delete from ng_nts_lookup;
delete from ng_nts_lookup_types;
INSERT INTO ng_nts_lookup_types VALUES (9, 'Best Connection Method');
INSERT INTO ng_nts_lookup_types VALUES (7, 'Identification Type');
INSERT INTO ng_nts_lookup_types VALUES (4, 'Salutation');
INSERT INTO ng_nts_lookup_types VALUES (5, 'Gender');
INSERT INTO ng_nts_lookup_types VALUES (1, 'Measurement Units');
INSERT INTO ng_nts_lookup_types VALUES (3, 'General Status');
INSERT INTO ng_nts_lookup_types VALUES (8, 'Order Status');
INSERT INTO ng_nts_lookup_types VALUES (6, 'Factility Type');
INSERT INTO ng_nts_lookup_types VALUES (2, 'Service Type');
INSERT INTO ng_nts_lookup_types VALUES (10, 'Employee Status');

INSERT INTO ng_nts_lookup VALUES (3, 'Phone', 'Best Connection Method', 9);
INSERT INTO ng_nts_lookup VALUES (4, 'Mobile', 'Best Connection Method', 9);
INSERT INTO ng_nts_lookup VALUES (5, 'Mail', 'Best Connection Method', 9);
INSERT INTO ng_nts_lookup VALUES (6, 'Passport', 'Identification Type', 7);
INSERT INTO ng_nts_lookup VALUES (7, 'National ID', 'Identification Type', 7);
INSERT INTO ng_nts_lookup VALUES (8, 'Male', 'Gender', 5);
INSERT INTO ng_nts_lookup VALUES (9, 'Female', 'Gender', 5);
INSERT INTO ng_nts_lookup VALUES (11, 'ENG', 'Salutation', 4);
INSERT INTO ng_nts_lookup VALUES (10, 'DR', 'Salutation', 4);
INSERT INTO ng_nts_lookup VALUES (1, 'active', 'General Status', 3);
INSERT INTO ng_nts_lookup VALUES (2, 'not active', 'General Status', 3);
INSERT INTO ng_nts_lookup VALUES (15, 'House', 'Facility Type', 6);
INSERT INTO ng_nts_lookup VALUES (16, 'MRS', 'Salutation', 4);
INSERT INTO ng_nts_lookup VALUES (17, 'MR', 'Salutation', 4);
INSERT INTO ng_nts_lookup VALUES (12, 'Flat', 'Service Type', 2);
INSERT INTO ng_nts_lookup VALUES (13, 'Usage', 'Service Type', 2);
INSERT INTO ng_nts_lookup VALUES (14, 'OneTime', 'Service Type', 2);
INSERT INTO ng_nts_lookup VALUES (18, 'New', 'Order Status', 8);
INSERT INTO ng_nts_lookup VALUES (19, 'In Progress', 'Order Status', 8);
INSERT INTO ng_nts_lookup VALUES (20, 'Done', 'Order Status', 8);
INSERT INTO ng_nts_lookup VALUES (21, 'Working', 'Employee Status', 10);
INSERT INTO ng_nts_lookup VALUES (22, 'Fired', 'Employee Status', 10);
INSERT INTO ng_nts_lookup VALUES (23, 'Resigned', 'Employee Status', 10);




