delete from ng_nts_ticket_status_sequences;

delete from ng_nts_ticket_statuses;

insert into ng_nts_ticket_statuses values(1,'New');
insert into ng_nts_ticket_statuses values(2,'In Progress');
insert into ng_nts_ticket_statuses values(3,'Solved');
insert into ng_nts_ticket_statuses values(4,'Cancelled');
insert into ng_nts_ticket_statuses values(5,'Reopened');


insert into ng_nts_ticket_status_sequences values (	1	,	1	,	1		);
insert into ng_nts_ticket_status_sequences values (	2	,	2	,	1		);
insert into ng_nts_ticket_status_sequences values (	3	,	4	,	1		);
insert into ng_nts_ticket_status_sequences values (	4	,	2	,	2		);
insert into ng_nts_ticket_status_sequences values (	5	,	3	,	2		);
insert into ng_nts_ticket_status_sequences values (	6	,	4	,	2		);
insert into ng_nts_ticket_status_sequences values (	7	,	3	,	3		);
insert into ng_nts_ticket_status_sequences values (	8	,	5	,	3		);
insert into ng_nts_ticket_status_sequences values (	9	,	5	,	4		);
insert into ng_nts_ticket_status_sequences values (	10	,	1	,	5		);
insert into ng_nts_ticket_status_sequences values (	11	,	2	,	5		);
insert into ng_nts_ticket_status_sequences values (	12	,	4	,	5		);
insert into ng_nts_ticket_status_sequences values (	13	,	5	,	5		);
