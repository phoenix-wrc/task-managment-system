create sequence app_user_seq start with 1 increment by 50;
create sequence comment_seq start with 1 increment by 50;
create sequence task_seq start with 1 increment by 50;
create table app_user (
                          id bigint not null,
                          last_name varchar(255),
                          name varchar(255),
                          password varchar(255),
                          second_name varchar(255),
                          username varchar(255),
                          primary key (id)
);
create table comment (
                         author_id bigint,
                         date_create timestamp(6),
                         id bigint not null,
                         task_id bigint,
                         description varchar(255),
                         title varchar(255),
                         primary key (id)
);
create table task (
                      create_date timestamp(6),
                      expiration_date timestamp(6),
                      id bigint not null,
                      description varchar(255),
                      status varchar(255) check (status in ('AWAITING','IN_PROGRESS','DONE')),
                      title varchar(255),
                      primary key (id)
);
create table task_comments (
                               comments_id bigint not null unique,
                               task_id bigint not null
);
create table task_priority (
                               task_id bigint not null,
                               priority varchar(255) check (priority in ('HIGH','MEDIUM','LOW')),
                               primary key (task_id)
);
create table user_role (
                           user_id bigint not null,
                           role varchar(255) check (role in ('ROLE_USER','ROLE_ADMIN'))
);
create table user_task_executor (
                                    tasks_executor_id bigint not null unique,
                                    user_id bigint not null
);
create table user_task_owner (
                                 tasks_own_id bigint not null unique,
                                 user_id bigint not null
);
alter table if exists comment
    add constraint FKemqjawvkiyejurssvwm5c21y7
        foreign key (author_id)
            references app_user;
alter table if exists comment
    add constraint FKfknte4fhjhet3l1802m1yqa50
        foreign key (task_id)
            references task;
alter table if exists task_comments
    add constraint FK7sybm6byg0d319yp5b0xkvn9b
        foreign key (comments_id)
            references comment;
alter table if exists task_comments
    add constraint FK57giy29i5nak139pefvyvhj9h
        foreign key (task_id)
            references task;
alter table if exists task_priority
    add constraint FKavhn0klmu1a9hb44phxhwhjvd
        foreign key (task_id)
            references task;
alter table if exists user_role
    add constraint FKg7fr1r7o0fkk41nfhnjdyqn7b
        foreign key (user_id)
            references app_user;
alter table if exists user_task_executor
    add constraint FK8eubxh53eyxs8saxpe7nc7kiu
        foreign key (tasks_executor_id)
            references task;
alter table if exists user_task_executor
    add constraint FKoq4t459am9e694osms9y6fph7
        foreign key (user_id)
            references app_user;
alter table if exists user_task_owner
    add constraint FKakwe5lt38ecy4xpqmumcnpjio
        foreign key (tasks_own_id)
            references task;
alter table if exists user_task_owner
    add constraint FKayefjqktpu6064qabjdu4rmdb
        foreign key (user_id)
            references app_user