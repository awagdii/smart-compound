delete from ng_nts_menu_item_group;
delete from ng_nts_menu;
delete from ng_nts_menu_item;


insert into ng_nts_menu values (1,'System Administration',1);
insert into ng_nts_menu values (2,'CRM',1);
insert into ng_nts_menu values (3,'Service Catalog ',1);
insert into ng_nts_menu values (4,'Order Management ',1);
insert into ng_nts_menu values (5,'Ticket Management',1);
insert into ng_nts_menu values (6,'Billing',1);

insert into ng_nts_menu values (7,'Access And Security',1);
insert into ng_nts_menu values (8,'Profile Settings',1);


insert into ng_nts_menu_item values (1,	'Compound Management',1,'/admin/compound/index.xhtml',1,1);
insert into ng_nts_menu_item values (2,	'Employees Management',1,'/admin/employee/index.xhtml',2);
insert into ng_nts_menu_item values (3,	'Groups',1,'/admin/group/index.xhtml',2);
insert into ng_nts_menu_item values (4,	'Employee Groups',1,'/admin/groupEmployees/index.xhtml',2);
insert into ng_nts_menu_item values (5,	'Groups Access',1,'/admin/access/index.xhtml',2);

insert into ng_nts_menu_item values (6,	'Tenants Management',1,'/admin/tenant/index.xhtml',2);
insert into ng_nts_menu_item values (7,	'Facilities Management',1,'/admin/facility/index.xhtml',2);
insert into ng_nts_menu_item values (8,	'Services Management',1,'/admin/service/index.xhtml',3);
insert into ng_nts_menu_item values (9,	'Orders',1,'/admin/order/index.xhtml',4);
insert into ng_nts_menu_item values (10,	'Tickets',1,'/admin/ticket/index.xhtml',5);
insert into ng_nts_menu_item values (11,	'Modules',1,'/admin/module/index.xhtml',7,1);
insert into ng_nts_menu_item values (12,	'SubModules',1,'/admin/submodule/index.xhtml',7,1);
insert into ng_nts_menu_item values (13,	'Change Password',1,'/admin/resetPassword.xhtml',8,1);
insert into ng_nts_menu_item values (16,	'Bills',1,'/admin/bill/index.xhtml',6);


