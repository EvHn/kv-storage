create table kvs.entries (
      id integer not null primary key
    , key varchar(100) not null
    , value varchar(4000) not null
    , changed date not null
);