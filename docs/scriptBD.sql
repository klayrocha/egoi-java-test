create database egoi;

create table category (
  id integer not null auto_increment,
  created datetime,
  modified datetime,
  name varchar(255) not null,
  category_id integer,
  primary key (id)
); 
    
alter table category 
  add constraint fk_category_category 
  foreign key (category_id) 
  references category (id);