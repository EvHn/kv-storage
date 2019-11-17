create table kvs.entries (
      key varchar(100) not null primary key
    , value varchar(4000) not null
    , changed date not null
);