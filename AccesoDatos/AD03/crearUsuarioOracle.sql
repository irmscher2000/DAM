alter session set "_ORACLE_SCRIPT"=true;

 create user accesodatos identified by "A1b2c3d4."
		default tablespace system
		quota 100M on system;
  
 GRANT create session to accesodatos;
 
 GRANT create table, create view to accesodatos;
 
 