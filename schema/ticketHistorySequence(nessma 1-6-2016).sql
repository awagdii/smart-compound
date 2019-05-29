create sequence ng_nts_ticket_histories_recid_seq;
alter table ng_nts_tickets_histories alter column recid set default nextval('ng_nts_ticket_histories_recid_seq');