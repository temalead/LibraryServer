create table address
(
    id              bigserial primary key,
    city            varchar(30)  not null,
    number_of_house bigint       not null,
    street          varchar(255) not null
);
create table library
(
    id         bigserial primary key,
    address_id bigserial references address (id)
);
create table book
(
    id               bigserial primary key,
    name             varchar(25) not null,
    date_of_creation timestamp,
    description      varchar(255),
    is_bestseller    boolean,
    library_id       bigserial   not null references library (id)
);
create table authors(
    id bigserial references book(id),
    authors varchar(40)
);
create table book_genres(
    id bigserial references book(id),
    genres varchar(40)
);
create table publishers(
    id bigserial references book(id),
    publishers varchar(40)
);