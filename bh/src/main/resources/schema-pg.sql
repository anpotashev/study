create extension if not exists citext;

create sequence if not exists word_collection_seq;

create table if not exists word_collection (
    id bigint not null default nextval('word_collection_seq') primary key,
    user_id bigint not null,
    name text not null
);

create sequence if not exists word_seq;

create table if not exists word (
    id bigint not null default nextval('word_seq') primary key ,
    original citext not null,
    translated text not null,
    sound_url text,
    word_collection_id bigint not null
        constraint word_word_collection_fk1
            references word_collection
            on update restrict on delete cascade,
    success_answers_count int not null default 0,
    error_answers_count int not null default 0,
    constraint original_collection_id_uniq unique (word_collection_id, original)
);

create sequence if not exists word_example_seq;

create table if not exists word_example(
    id bigint not null default nextval('word_example_seq') primary key ,
    text text not null,
    word_id bigint not null
        constraint word_example_word_fk1
            references word
            on update restrict on delete cascade
);

create or replace function quest_word(
    p_err_code out text,
    p_word_id out bigint,
    p_collection_id in bigint
)
    language plpgsql
    security definer
as '
declare
    l_min_success int;
    l_max_success int;
    l_random int;
begin
    if (not exists(select from word where word_collection_id = p_collection_id)) then
        p_err_code := ''collection is empty'';
        return ;
    end if;
    select min(w.success_answers_count - w.error_answers_count),
           max(w.success_answers_count - w.error_answers_count)
      into l_min_success, l_max_success
      from word w
     where word_collection_id = p_collection_id;
    l_random := floor(random()* (l_max_success-l_min_success + 1) + l_min_success);

    select r.id into p_word_id
      from (select id, random() as _random
              from word
             where success_answers_count - word.error_answers_count <= l_random) r
     order by _random
     limit 1;
    return;
exception
    when others then
        p_err_code := ''QUEST_WORD_ERROR'';
end;
'