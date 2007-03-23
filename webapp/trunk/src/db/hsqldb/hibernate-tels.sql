
    create table roles (
        id bigint generated by default as identity (start with 1),
        OPTLOCK integer,
        role varchar(255) not null,
        primary key (id),
        unique (role)
    )

    create table sds_users (
        id bigint generated by default as identity (start with 1),
        OPTLOCK integer,
        user_id integer not null,
        first_name varchar(255) not null,
        last_name varchar(255) not null,
        primary key (id),
        unique (user_id)
    )

    create table student_user_details (
        id bigint not null,
        firstname varchar(255),
        lastname varchar(255),
        gender integer,
        birthday timestamp,
        primary key (id)
    )

    create table teacher_user_details (
        id bigint not null,
        firstname varchar(255),
        lastname varchar(255),
        accounttype integer,
        city varchar(255),
        state varchar(255),
        country varchar(255),
        schoolname varchar(255),
        curriculumsubjects varbinary(255),
        schoollevel varchar(255),
        primary key (id)
    )

    create table user_details (
        id bigint generated by default as identity (start with 1),
        OPTLOCK integer,
        password varchar(255) not null,
        username varchar(255) not null,
        email_address varchar(255),
        account_not_expired bit not null,
        account_not_locked bit not null,
        credentials_not_expired bit not null,
        enabled bit not null,
        primary key (id),
        unique (username)
    )

    create table users (
        id bigint generated by default as identity (start with 1),
        OPTLOCK integer,
        user_details_fk bigint not null,
        sds_user_fk bigint not null,
        primary key (id),
        unique (sds_user_fk),
        unique (user_details_fk)
    )

    create table users_roles (
        user_fk bigint not null,
        role_fk bigint not null,
        primary key (user_fk, role_fk)
    )

    alter table student_user_details 
        add constraint FKC5AA2952D1D25907 
        foreign key (id) 
        references user_details

    alter table teacher_user_details 
        add constraint FKAC84070BD1D25907 
        foreign key (id) 
        references user_details

    alter table users 
        add constraint FK6A68E08E3B038C2 
        foreign key (user_details_fk) 
        references user_details

    alter table users 
        add constraint FK6A68E08F342C661 
        foreign key (sds_user_fk) 
        references sds_users

    alter table users_roles 
        add constraint FKF6CCD9C6C90D39A5 
        foreign key (user_fk) 
        references user_details

    alter table users_roles 
        add constraint FKF6CCD9C68DCB6121 
        foreign key (role_fk) 
        references roles
