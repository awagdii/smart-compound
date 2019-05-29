CREATE TABLE "ng_nts_compounds" (
  "recid" SERIAL PRIMARY KEY,
  "compound_name" TEXT  ,
  "lattitude" DECIMAL(12, 2)  ,
  "longtude" DECIMAL(12, 2)  ,
  "country" TEXT  ,
  "city" TEXT  ,
  "owner_name" TEXT  
);

CREATE TABLE "ng_nts_groups" (
  "recid" SERIAL PRIMARY KEY,
  "group_name" TEXT  
);

CREATE TABLE "ng_nts_lookup_types" (
  "recid" SERIAL PRIMARY KEY,
  "type_name" TEXT  
);

CREATE TABLE "ng_nts_lookup" (
  "recid" SERIAL PRIMARY KEY,
  "lookup_name" TEXT  ,
  "type_name" TEXT  ,
  "type_id" INTEGER  
);

CREATE INDEX "idx_ng_nts_lookup__type_id" ON "ng_nts_lookup" ("type_id");

ALTER TABLE "ng_nts_lookup" ADD CONSTRAINT "fk_ng_nts_lookup__type_id" FOREIGN KEY ("type_id") REFERENCES "ng_nts_lookup_types" ("recid");

CREATE TABLE "ng_nts_employees" (
  "recid" SERIAL PRIMARY KEY,
  "salutation" TEXT  ,
  "username" TEXT  ,
  "password" TEXT  ,
  "first_name" TEXT  ,
  "middle_name" TEXT  ,
  "last_name" TEXT  ,
  "email" TEXT  ,
  "job" TEXT  ,
  "gender" TEXT  ,
  "identification_type" TEXT  ,
  "identification_number" TEXT  ,
  "address" TEXT  ,
  "phone_number1" TEXT  ,
  "phone_number2" TEXT  ,
  "mobile_number1" TEXT  ,
  "mobile_number2" TEXT  ,
  "status" TEXT  ,
  "hire_date" DATE  ,
  "group_name" TEXT  ,
  "compound_id" INTEGER  ,
  "salutation_lookup_id" INTEGER  ,
  "gender_lookup_id" INTEGER  ,
  "status_lookup_id" INTEGER  ,
  "group_id" INTEGER  
);

CREATE INDEX "idx_ng_nts_employees__compound_id" ON "ng_nts_employees" ("compound_id");

CREATE INDEX "idx_ng_nts_employees__gender_lookup_id" ON "ng_nts_employees" ("gender_lookup_id");

CREATE INDEX "idx_ng_nts_employees__group_id" ON "ng_nts_employees" ("group_id");

CREATE INDEX "idx_ng_nts_employees__salutation_lookup_id" ON "ng_nts_employees" ("salutation_lookup_id");

CREATE INDEX "idx_ng_nts_employees__status_lookup_id" ON "ng_nts_employees" ("status_lookup_id");

ALTER TABLE "ng_nts_employees" ADD CONSTRAINT "fk_ng_nts_employees__compound_id" FOREIGN KEY ("compound_id") REFERENCES "ng_nts_compounds" ("recid");

ALTER TABLE "ng_nts_employees" ADD CONSTRAINT "fk_ng_nts_employees__gender_lookup_id" FOREIGN KEY ("gender_lookup_id") REFERENCES "ng_nts_lookup" ("recid");

ALTER TABLE "ng_nts_employees" ADD CONSTRAINT "fk_ng_nts_employees__group_id" FOREIGN KEY ("group_id") REFERENCES "ng_nts_groups" ("recid");

ALTER TABLE "ng_nts_employees" ADD CONSTRAINT "fk_ng_nts_employees__salutation_lookup_id" FOREIGN KEY ("salutation_lookup_id") REFERENCES "ng_nts_lookup" ("recid");

ALTER TABLE "ng_nts_employees" ADD CONSTRAINT "fk_ng_nts_employees__status_lookup_id" FOREIGN KEY ("status_lookup_id") REFERENCES "ng_nts_lookup" ("recid");

CREATE TABLE "ng_nts_services" (
  "recid" SERIAL PRIMARY KEY,
  "service_name" TEXT  ,
  "description" TEXT  ,
  "is_active" INTEGER  ,
  "severity" INTEGER  ,
  "flat_or_usage" TEXT  ,
  "estimated_delivery_days" INTEGER  ,
  "nrc" INTEGER  ,
  "mrc" INTEGER  ,
  "installation_price" INTEGER  ,
  "is_prepaid" INTEGER  ,
  "creation_date" TIMESTAMP  ,
  "measuring_unit" TEXT  ,
  "compound_id" INTEGER  ,
  "measuring_unit_lookup_id" INTEGER  
);

CREATE INDEX "idx_ng_nts_services__compound_id" ON "ng_nts_services" ("compound_id");

CREATE INDEX "idx_ng_nts_services__measuring_unit_lookup_id" ON "ng_nts_services" ("measuring_unit_lookup_id");

ALTER TABLE "ng_nts_services" ADD CONSTRAINT "fk_ng_nts_services__compound_id" FOREIGN KEY ("compound_id") REFERENCES "ng_nts_compounds" ("recid");

ALTER TABLE "ng_nts_services" ADD CONSTRAINT "fk_ng_nts_services__measuring_unit_lookup_id" FOREIGN KEY ("measuring_unit_lookup_id") REFERENCES "ng_nts_lookup" ("recid");

CREATE TABLE "ng_nts_service_usage_rates" (
  "recid" SERIAL PRIMARY KEY,
  "usage_amount_from" DECIMAL(12, 2)  ,
  "usage_amount_to" DECIMAL(12, 2)  ,
  "unit_price" DECIMAL(12, 2)  ,
  "service_name" TEXT  ,
  "compound_id" INTEGER  ,
  "service_id" INTEGER  
);

CREATE INDEX "idx_ng_nts_service_usage_rates__compound_id" ON "ng_nts_service_usage_rates" ("compound_id");

CREATE INDEX "idx_ng_nts_service_usage_rates__service_id" ON "ng_nts_service_usage_rates" ("service_id");

ALTER TABLE "ng_nts_service_usage_rates" ADD CONSTRAINT "fk_ng_nts_service_usage_rates__compound_id" FOREIGN KEY ("compound_id") REFERENCES "ng_nts_compounds" ("recid");

ALTER TABLE "ng_nts_service_usage_rates" ADD CONSTRAINT "fk_ng_nts_service_usage_rates__service_id" FOREIGN KEY ("service_id") REFERENCES "ng_nts_services" ("recid");

CREATE TABLE "ng_nts_tenants" (
  "recid" SERIAL PRIMARY KEY,
  "salutation" TEXT  ,
  "username" TEXT  ,
  "password" TEXT  ,
  "first_name" TEXT  ,
  "middle_name" TEXT  ,
  "last_name" TEXT  ,
  "email" TEXT  ,
  "job" TEXT  ,
  "gender" TEXT  ,
  "nationality" TEXT  ,
  "date_of_birth" DATE  ,
  "identification_type" TEXT  ,
  "identification_number" TEXT  ,
  "best_connection_method" TEXT  ,
  "address" TEXT  ,
  "country" TEXT  ,
  "city" TEXT  ,
  "postal_code" TEXT  ,
  "phone_number1" TEXT  ,
  "phone_number2" TEXT  ,
  "mobile_number1" TEXT  ,
  "mobile_number2" TEXT  ,
  "facebook" TEXT  ,
  "whatsapp" TEXT  ,
  "compound_id" INTEGER  ,
  "salutation_lookup_id" INTEGER  ,
  "gender_lookup_id" INTEGER  
);

CREATE INDEX "idx_ng_nts_tenants__compound_id" ON "ng_nts_tenants" ("compound_id");

CREATE INDEX "idx_ng_nts_tenants__gender_lookup_id" ON "ng_nts_tenants" ("gender_lookup_id");

CREATE INDEX "idx_ng_nts_tenants__salutation_lookup_id" ON "ng_nts_tenants" ("salutation_lookup_id");

ALTER TABLE "ng_nts_tenants" ADD CONSTRAINT "fk_ng_nts_tenants__compound_id" FOREIGN KEY ("compound_id") REFERENCES "ng_nts_compounds" ("recid");

ALTER TABLE "ng_nts_tenants" ADD CONSTRAINT "fk_ng_nts_tenants__gender_lookup_id" FOREIGN KEY ("gender_lookup_id") REFERENCES "ng_nts_lookup" ("recid");

ALTER TABLE "ng_nts_tenants" ADD CONSTRAINT "fk_ng_nts_tenants__salutation_lookup_id" FOREIGN KEY ("salutation_lookup_id") REFERENCES "ng_nts_lookup" ("recid");

CREATE TABLE "ng_nts_facilities" (
  "recid" SERIAL PRIMARY KEY,
  "facility_type" TEXT  ,
  "block" TEXT,
  "street" TEXT,
  "building_number" INTEGER  ,
  "floor_number" INTEGER  ,
  "facility_number" INTEGER  ,
  "lattitude" DECIMAL(12, 2)  ,
  "longtude" DECIMAL(12, 2)  ,
  "status" TEXT,
  "is_for_rent" INTEGER  ,
  "area" DECIMAL(12, 2)  ,
  "description" TEXT,
  "picture_url" TEXT,
  "compound_id" INTEGER  ,
  "tenant_id" INTEGER  ,
  "facility_lookup_id" INTEGER  
);

CREATE INDEX "idx_ng_nts_facilities__compound_id" ON "ng_nts_facilities" ("compound_id");

CREATE INDEX "idx_ng_nts_facilities__facility_lookup_id" ON "ng_nts_facilities" ("facility_lookup_id");

CREATE INDEX "idx_ng_nts_facilities__tenant_id" ON "ng_nts_facilities" ("tenant_id");

ALTER TABLE "ng_nts_facilities" ADD CONSTRAINT "fk_ng_nts_facilities__compound_id" FOREIGN KEY ("compound_id") REFERENCES "ng_nts_compounds" ("recid");

ALTER TABLE "ng_nts_facilities" ADD CONSTRAINT "fk_ng_nts_facilities__facility_lookup_id" FOREIGN KEY ("facility_lookup_id") REFERENCES "ng_nts_lookup" ("recid");

ALTER TABLE "ng_nts_facilities" ADD CONSTRAINT "fk_ng_nts_facilities__tenant_id" FOREIGN KEY ("tenant_id") REFERENCES "ng_nts_tenants" ("recid");

CREATE TABLE "ng_nts_tenant_facilities" (
  "recid" SERIAL PRIMARY KEY,
  "is_leased" INTEGER  ,
  "leasing_or_ownership_start_date" DATE  ,
  "leasing_or_ownership_end_date" DATE  ,
  "compound_id" INTEGER  ,
  "tenant_id" INTEGER  ,
  "facility_id" INTEGER  
);

CREATE INDEX "idx_ng_nts_tenant_facilities__compound_id" ON "ng_nts_tenant_facilities" ("compound_id");

CREATE INDEX "idx_ng_nts_tenant_facilities__facility_id" ON "ng_nts_tenant_facilities" ("facility_id");

CREATE INDEX "idx_ng_nts_tenant_facilities__tenant_id" ON "ng_nts_tenant_facilities" ("tenant_id");

ALTER TABLE "ng_nts_tenant_facilities" ADD CONSTRAINT "fk_ng_nts_tenant_facilities__compound_id" FOREIGN KEY ("compound_id") REFERENCES "ng_nts_compounds" ("recid");

ALTER TABLE "ng_nts_tenant_facilities" ADD CONSTRAINT "fk_ng_nts_tenant_facilities__facility_id" FOREIGN KEY ("facility_id") REFERENCES "ng_nts_facilities" ("recid");

ALTER TABLE "ng_nts_tenant_facilities" ADD CONSTRAINT "fk_ng_nts_tenant_facilities__tenant_id" FOREIGN KEY ("tenant_id") REFERENCES "ng_nts_tenants" ("recid");

CREATE TABLE "ng_nts_orders" (
  "recid" SERIAL PRIMARY KEY,
  "service_name" TEXT  ,
  "request_date" DATE  ,
  "acceptence_date" DATE  ,
  "status" TEXT  ,
  "channel" TEXT  ,
  "notes" TEXT  ,
  "service_location_lattitude" DECIMAL(12, 2)  ,
  "service_location_longtude" DECIMAL(12, 2)  ,
  "service_location_desc" TEXT  ,
  "compound_id" INTEGER  ,
  "tenant_id" INTEGER  ,
  "service_id" INTEGER  ,
  "facility_id" INTEGER  ,
  "created_by" INTEGER  ,
  "accepted_by" INTEGER,
  "channel_lookup_id" INTEGER  
);

CREATE INDEX "idx_ng_nts_orders__accepted_by" ON "ng_nts_orders" ("accepted_by");

CREATE INDEX "idx_ng_nts_orders__channel_lookup_id" ON "ng_nts_orders" ("channel_lookup_id");

CREATE INDEX "idx_ng_nts_orders__compound_id" ON "ng_nts_orders" ("compound_id");

CREATE INDEX "idx_ng_nts_orders__created_by" ON "ng_nts_orders" ("created_by");

CREATE INDEX "idx_ng_nts_orders__facility_id" ON "ng_nts_orders" ("facility_id");

CREATE INDEX "idx_ng_nts_orders__service_id" ON "ng_nts_orders" ("service_id");

CREATE INDEX "idx_ng_nts_orders__tenant_id" ON "ng_nts_orders" ("tenant_id");

ALTER TABLE "ng_nts_orders" ADD CONSTRAINT "fk_ng_nts_orders__accepted_by" FOREIGN KEY ("accepted_by") REFERENCES "ng_nts_employees" ("recid");

ALTER TABLE "ng_nts_orders" ADD CONSTRAINT "fk_ng_nts_orders__channel_lookup_id" FOREIGN KEY ("channel_lookup_id") REFERENCES "ng_nts_lookup" ("recid");

ALTER TABLE "ng_nts_orders" ADD CONSTRAINT "fk_ng_nts_orders__compound_id" FOREIGN KEY ("compound_id") REFERENCES "ng_nts_compounds" ("recid");

ALTER TABLE "ng_nts_orders" ADD CONSTRAINT "fk_ng_nts_orders__created_by" FOREIGN KEY ("created_by") REFERENCES "ng_nts_employees" ("recid");

ALTER TABLE "ng_nts_orders" ADD CONSTRAINT "fk_ng_nts_orders__facility_id" FOREIGN KEY ("facility_id") REFERENCES "ng_nts_facilities" ("recid");

ALTER TABLE "ng_nts_orders" ADD CONSTRAINT "fk_ng_nts_orders__service_id" FOREIGN KEY ("service_id") REFERENCES "ng_nts_services" ("recid");

ALTER TABLE "ng_nts_orders" ADD CONSTRAINT "fk_ng_nts_orders__tenant_id" FOREIGN KEY ("tenant_id") REFERENCES "ng_nts_tenants" ("recid");

CREATE TABLE "ng_nts_tickets" (
  "recid" SERIAL PRIMARY KEY,
  "ticket_subject" TEXT,
  "description" TEXT,
  "problem_date" TIMESTAMP  ,
  "open_date" TIMESTAMP  ,
  "channel" TEXT,
  "last_status" TEXT,
  "severity" INTEGER  ,
  "feedback" TEXT  ,
  "rate" INTEGER  ,
  "service_name" TEXT  ,
  "compound_id" INTEGER  ,
  "facility_id" INTEGER  ,
  "service_id" INTEGER  ,
  "related_tenant_id" INTEGER  ,
  "assigned_group_id" INTEGER  ,
  "opened_by" INTEGER  ,
  "channel_lookup_id" INTEGER  ,
  "group_name" TEXT
);

CREATE INDEX "idx_ng_nts_tickets__assigned_group_id" ON "ng_nts_tickets" ("assigned_group_id");

CREATE INDEX "idx_ng_nts_tickets__channel_lookup_id" ON "ng_nts_tickets" ("channel_lookup_id");

CREATE INDEX "idx_ng_nts_tickets__compound_id" ON "ng_nts_tickets" ("compound_id");

CREATE INDEX "idx_ng_nts_tickets__facility_id" ON "ng_nts_tickets" ("facility_id");

CREATE INDEX "idx_ng_nts_tickets__opened_by" ON "ng_nts_tickets" ("opened_by");

CREATE INDEX "idx_ng_nts_tickets__related_tenant_id" ON "ng_nts_tickets" ("related_tenant_id");

CREATE INDEX "idx_ng_nts_tickets__service_id" ON "ng_nts_tickets" ("service_id");

ALTER TABLE "ng_nts_tickets" ADD CONSTRAINT "fk_ng_nts_tickets__assigned_group_id" FOREIGN KEY ("assigned_group_id") REFERENCES "ng_nts_groups" ("recid");

ALTER TABLE "ng_nts_tickets" ADD CONSTRAINT "fk_ng_nts_tickets__channel_lookup_id" FOREIGN KEY ("channel_lookup_id") REFERENCES "ng_nts_lookup" ("recid");

ALTER TABLE "ng_nts_tickets" ADD CONSTRAINT "fk_ng_nts_tickets__compound_id" FOREIGN KEY ("compound_id") REFERENCES "ng_nts_compounds" ("recid");

ALTER TABLE "ng_nts_tickets" ADD CONSTRAINT "fk_ng_nts_tickets__facility_id" FOREIGN KEY ("facility_id") REFERENCES "ng_nts_facilities" ("recid");

ALTER TABLE "ng_nts_tickets" ADD CONSTRAINT "fk_ng_nts_tickets__opened_by" FOREIGN KEY ("opened_by") REFERENCES "ng_nts_employees" ("recid");

ALTER TABLE "ng_nts_tickets" ADD CONSTRAINT "fk_ng_nts_tickets__related_tenant_id" FOREIGN KEY ("related_tenant_id") REFERENCES "ng_nts_tenants" ("recid");

ALTER TABLE "ng_nts_tickets" ADD CONSTRAINT "fk_ng_nts_tickets__service_id" FOREIGN KEY ("service_id") REFERENCES "ng_nts_services" ("recid");

CREATE TABLE "ng_nts_tickets_histories" (
  "recid" SERIAL PRIMARY KEY,
  "action_date" TIMESTAMP  ,
  "is_completed" INTEGER  ,
  "comment" TEXT  ,
  "previous_status" TEXT  ,
  "currenet_status" TEXT  ,
  "compound_id" INTEGER  ,
  "ticket_id" INTEGER  ,
  "action_by" INTEGER  ,
  "opened_by" INTEGER  
);


--
-- Structure for table menu (OID = 41280) : 
--
SET search_path = public, pg_catalog;
CREATE TABLE public.menu (
    recid serial NOT NULL,
    name text,
    status_id integer
)
WITH (oids = false);
--
-- Structure for table menu_item (OID = 41296) : 
--
CREATE TABLE public.menu_item (
    recid serial NOT NULL,
    name text,
    status_id integer,
    url text,
    menu_id integer
)
WITH (oids = false);
--
-- Structure for table menu_item_group (OID = 41315) : 
--
CREATE TABLE public.menu_item_group (
    group_id integer NOT NULL,
    menu_item_id integer NOT NULL
)
WITH (oids = false);
--
-- Definition for index menu_pkey (OID = 41287) : 
--
ALTER TABLE ONLY menu
    ADD CONSTRAINT menu_pkey
    PRIMARY KEY (recid);
--
-- Definition for index menu_fk (OID = 41289) : 
--
ALTER TABLE ONLY menu
    ADD CONSTRAINT menu_fk
    FOREIGN KEY (status_id) REFERENCES ng_nts_lookup(recid) ON UPDATE CASCADE ON DELETE CASCADE;
--
-- Definition for index menu_item_pkey (OID = 41303) : 
--
ALTER TABLE ONLY menu_item
    ADD CONSTRAINT menu_item_pkey
    PRIMARY KEY (recid);
--
-- Definition for index menu_item_fk (OID = 41305) : 
--
ALTER TABLE ONLY menu_item
    ADD CONSTRAINT menu_item_fk
    FOREIGN KEY (status_id) REFERENCES ng_nts_lookup(recid) ON UPDATE CASCADE ON DELETE CASCADE;
--
-- Definition for index menu_item_fk1 (OID = 41310) : 
--
ALTER TABLE ONLY menu_item
    ADD CONSTRAINT menu_item_fk1
    FOREIGN KEY (menu_id) REFERENCES menu(recid) ON UPDATE CASCADE ON DELETE CASCADE;
--
-- Definition for index pkc_name (OID = 41340) : 
--
ALTER TABLE ONLY menu_item_group
    ADD CONSTRAINT pkc_name
    PRIMARY KEY (group_id, menu_item_id);
--
-- Definition for index menu_item_group_fk (OID = 41342) : 
--
ALTER TABLE ONLY menu_item_group
    ADD CONSTRAINT menu_item_group_fk
    FOREIGN KEY (group_id) REFERENCES ng_nts_groups(recid) ON UPDATE CASCADE ON DELETE CASCADE;
--
-- Definition for index menu_item_group_fk1 (OID = 41347) : 
--
ALTER TABLE ONLY menu_item_group
    ADD CONSTRAINT menu_item_group_fk1
    FOREIGN KEY (menu_item_id) REFERENCES menu_item(recid) ON UPDATE CASCADE ON DELETE CASCADE;
--
-- Comments
--
COMMENT ON SCHEMA public IS 'standard public schema';




CREATE INDEX "idx_ng_nts_tickets_histories__action_by" ON "ng_nts_tickets_histories" ("action_by");

CREATE INDEX "idx_ng_nts_tickets_histories__compound_id" ON "ng_nts_tickets_histories" ("compound_id");

CREATE INDEX "idx_ng_nts_tickets_histories__opened_by" ON "ng_nts_tickets_histories" ("opened_by");

CREATE INDEX "idx_ng_nts_tickets_histories__ticket_id" ON "ng_nts_tickets_histories" ("ticket_id");

ALTER TABLE "ng_nts_tickets_histories" ADD CONSTRAINT "fk_ng_nts_tickets_histories__action_by" FOREIGN KEY ("action_by") REFERENCES "ng_nts_employees" ("recid");

ALTER TABLE "ng_nts_tickets_histories" ADD CONSTRAINT "fk_ng_nts_tickets_histories__compound_id" FOREIGN KEY ("compound_id") REFERENCES "ng_nts_compounds" ("recid");

ALTER TABLE "ng_nts_tickets_histories" ADD CONSTRAINT "fk_ng_nts_tickets_histories__opened_by" FOREIGN KEY ("opened_by") REFERENCES "ng_nts_employees" ("recid");

ALTER TABLE "ng_nts_tickets_histories" ADD CONSTRAINT "fk_ng_nts_tickets_histories__ticket_id" FOREIGN KEY ("ticket_id") REFERENCES "ng_nts_tickets" ("recid")