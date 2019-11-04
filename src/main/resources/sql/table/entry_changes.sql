create table kvs.entry_changes
(
      id int not null primary key
    , value varchar(4000) not null
    , created date not null
);