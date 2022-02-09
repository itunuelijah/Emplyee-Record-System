create database EmployeeRecord_db;

create user  'EmployeeRecord_user'@'localhost' identified by 'pass_123';

grant all privileges on EmployeeRecord_db.* to 'EmployeeRecord_user'@'localhost';
flush privileges ;