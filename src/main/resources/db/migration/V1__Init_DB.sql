create table department
(
    id          bigint not null,
    description text,
    name        varchar(255),
    primary key (id)
);

create table developers
(
    id            bigint not null,
    email         varchar(255) not null unique,
    first_name    varchar(128),
    last_name     varchar(128),
    department_id bigint,
    primary key (id)
);

create table developers_projects
(
    project_id   bigint not null,
    developer_id bigint not null,
    primary key (project_id, developer_id)
);

create table hibernate_sequence
(
    next_val bigint
);
insert into hibernate_sequence values ( 1 );

create table projects
(
    id           bigint not null,
    name         varchar(255) unique ,
    description  text,
    project_link varchar(255),
    subject      varchar(255) default 'general',
    primary key (id)
);

create table users
(
    id         bigint not null auto_increment,
    email      varchar(255) unique,
    first_name varchar(255),
    last_name  varchar(255),
    password   varchar(255),
    role       varchar(255) default 'USER',
    status     varchar(255) default 'ACTIVE',
    primary key (id)
);

alter table developers
    add constraint developers_ibfk_1 foreign key (department_id) references department (id);
alter table developers_projects
    add constraint developers_projects_ibfk_1 foreign key (developer_id) references developers (id);
alter table developers_projects
    add constraint developers_projects_ibfk_2 foreign key (project_id) references projects (id);