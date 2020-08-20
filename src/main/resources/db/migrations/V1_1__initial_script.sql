create table m_positions
(
    id   bigserial    not null
        constraint m_position_pk
            primary key,
    name varchar(255) not null
);

alter table m_positions
    owner to postgres;

create unique index m_position_id_uindex
    on m_positions (id);

create unique index m_position_name_uindex
    on m_positions (name);

create table m_rooms
(
    id   bigserial    not null
        constraint m_rooms_pk
            primary key,
    type varchar(255) not null,
    name varchar(255) not null
);

alter table m_rooms
    owner to postgres;

create unique index m_rooms_id_uindex
    on m_rooms (id);

create unique index m_rooms_name_uindex
    on m_rooms (name);

create table m_users
(
    id          bigserial    not null
        constraint m_users_pk
            primary key,
    login       varchar(255) not null,
    password    varchar(255) not null,
    position_id bigint       not null
        constraint m_users_m_positions_id_fk
            references m_positions
            on update cascade on delete cascade
);

alter table m_users
    owner to postgres;

create table m_reservations
(
    id          bigserial    not null
        constraint m_reservation_pk
            primary key,
    room_id     bigint       not null
        constraint m_reservations_m_rooms_id_fk
            references m_rooms
            on update cascade on delete cascade,
    user_id     bigint       not null
        constraint m_reservations_m_users_id_fk
            references m_users
            on update cascade on delete cascade,
    name        varchar(255) not null,
    description varchar(255) not null,
    start_time  time         not null,
    end_time    time         not null
);

alter table m_reservations
    owner to postgres;

create unique index m_reservation_id_uindex
    on m_reservations (id);

create table m_roles
(
    id      bigserial    not null
        constraint m_roles_pk
            primary key,
    user_id bigint       not null
        constraint m_roles_m_users_id_fk
            references m_users
            on update cascade on delete cascade,
    role    varchar(255) not null
);

alter table m_roles
    owner to postgres;

create unique index m_roles_id_uindex
    on m_roles (id);

create unique index m_users_id_uindex
    on m_users (id);

create unique index m_users_login_uindex
    on m_users (login);
