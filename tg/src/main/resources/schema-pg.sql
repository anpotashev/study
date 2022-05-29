create table if not exists auth_data
(
    code             text   not null primary key ,
    single_time_code text   not null
);

create table if not exists telegram_auth_data
(
    chat_id      bigint not null primary key,
    auth_code text not null
        constraint auth_data_fk1
            references auth_data
            on update restrict
            on delete cascade
);

