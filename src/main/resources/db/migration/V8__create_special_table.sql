create table if not exists public.special_table
(
    id     bigint generated by default as identity primary key,
    age    bigint,
    name   varchar,
    email  varchar,
    gender varchar
);