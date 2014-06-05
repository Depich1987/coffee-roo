--J_FIRMS(ID,DEALERCODE,NAME,PRICECOCOAANALYSIS,PRICECOFFEEANALYSIS,PRODUCTWEIGHTBAG,VERSION)
insert into J_FIRM values (1,'CQ05','KMS',855,850,70,1);

--J_ROLE(ID,DESCRIPTION,NAME,VERSION)
insert into J_ROLE values (1,'Administrateur','ROLE_ADMIN',1),
						  (2,'Chef Usine','ROLE_FACTORY_MANAGER',1),
						  (3,'Agent','ROLE_FACTORY_AGENT',1),
						  (4,'Superviseur','ROLE_SUPERVISOR',1);
						  
--J_USER
INSERT INTO J_USER VALUES (250,'agahfranckjanel@yahoo.fr','Franck Janel','AGAH','7297aa334c4d70e3c71ae72a5dae5ddf180c456d8e3da50d5854e2162389347f','08 74 39 52','ADMINISTRATEUR','admin',4);						  
							
--J_ROLE_J_USER(JROLE_ID,USERS_ID)							
insert into  J_ROLE_J_USER values (1,250);

--J_USER_J_ROLE(JUSER_ID,ROLES_ID)							
insert into  J_USER_J_ROLE values (250,1);

--J_USER_J_FIRMS(JUSER_ID,COMPANIES_ID)
insert into  J_USER_J_FIRM values (250,1);

--J_FIRMS_J_USER
insert into  J_FIRM_J_USER values (1,250);