
    create table curnits (
        id bigint generated by default as identity (start with 1),
        OPTLOCK integer,
        sds_curnit_fk bigint not null,
        primary key (id),
        unique (sds_curnit_fk)
    )

    create table granted_authorities (
        id bigint generated by default as identity (start with 1),
        OPTLOCK integer,
        authority varchar(255) not null,
        primary key (id),
        unique (authority)
    )

    create table jnlps (
        id bigint generated by default as identity (start with 1),
        OPTLOCK integer,
        sds_jnlp_fk bigint not null,
        primary key (id),
        unique (sds_jnlp_fk)
    )

    create table offerings (
        id bigint generated by default as identity (start with 1),
        OPTLOCK integer,
        sds_offering_fk bigint not null,
        primary key (id),
        unique (sds_offering_fk)
    )

    create table sds_curnits (
        id bigint generated by default as identity (start with 1),
        OPTLOCK integer,
        curnit_id integer not null,
        name varchar(255) not null,
        url varchar(255) not null,
        primary key (id),
        unique (curnit_id)
    )

    create table sds_jnlps (
        id bigint generated by default as identity (start with 1),
        OPTLOCK integer,
        jnlp_id integer not null,
        name varchar(255) not null,
        url varchar(255) not null,
        primary key (id),
        unique (jnlp_id)
    )

    create table sds_offerings (
        id bigint generated by default as identity (start with 1),
        OPTLOCK integer,
        name varchar(255) not null,
        offering_id integer not null,
        sds_jnlp_fk bigint not null,
        sds_curnit_fk bigint not null,
        primary key (id),
        unique (offering_id)
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

    create table sds_workgroups (
        id bigint generated by default as identity (start with 1),
        OPTLOCK integer,
        workgroup_id integer not null,
        name varchar(255) not null,
        sds_offering_fk bigint not null,
        primary key (id),
        unique (workgroup_id)
    )

    create table sds_workgroups_related_to_sds_users (
        sds_workgroup_fk bigint not null,
        sds_user_fk bigint not null,
        primary key (sds_workgroup_fk, sds_user_fk)
    )

    create table student_user_details (
        id bigint not null,
        firstname varchar(255) not null,
        lastname varchar(255) not null,
        gender integer not null,
        birthday timestamp not null,
        primary key (id)
    )

    create table teacher_user_details (
        id bigint not null,
        firstname varchar(255) not null,
        lastname varchar(255) not null,
        city varchar(255) not null,
        state varchar(255) not null,
        country varchar(255) not null,
        schoolname varchar(255) not null,
        curriculumsubjects varbinary(255) not null,
        schoollevel varchar(255) not null,
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

    create table user_details_related_to_roles (
        user_details_fk bigint not null,
        granted_authorities_fk bigint not null,
        primary key (user_details_fk, granted_authorities_fk)
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

    create table workgroups (
        id bigint generated by default as identity (start with 1),
        OPTLOCK integer,
        offering_fk bigint not null,
        sds_workgroup_fk bigint not null,
        primary key (id),
        unique (sds_workgroup_fk)
    )

    create table workgroups_related_to_users (
        workgroup_fk bigint not null,
        user_fk bigint not null,
        primary key (workgroup_fk, user_fk)
    )

    alter table curnits 
        add constraint FK4329FBBA1B78E061 
        foreign key (sds_curnit_fk) 
        references sds_curnits

    alter table jnlps 
        add constraint FK6095FABA532A941 
        foreign key (sds_jnlp_fk) 
        references sds_jnlps

    alter table offerings 
        add constraint FK73F0F12DAB4F6201 
        foreign key (sds_offering_fk) 
        references sds_offerings

    alter table sds_offerings 
        add constraint FK242EBD70A532A941 
        foreign key (sds_jnlp_fk) 
        references sds_jnlps

    alter table sds_offerings 
        add constraint FK242EBD701B78E061 
        foreign key (sds_curnit_fk) 
        references sds_curnits

    alter table sds_workgroups 
        add constraint FK440A0C42AB4F6201 
        foreign key (sds_offering_fk) 
        references sds_offerings

    alter table sds_workgroups_related_to_sds_users 
        add constraint FKA31D36785AAC23E7 
        foreign key (sds_workgroup_fk) 
        references sds_workgroups

    alter table sds_workgroups_related_to_sds_users 
        add constraint FKA31D3678F342C661 
        foreign key (sds_user_fk) 
        references sds_users

    alter table student_user_details 
        add constraint FKC5AA2952D1D25907 
        foreign key (id) 
        references user_details

    alter table teacher_user_details 
        add constraint FKAC84070BD1D25907 
        foreign key (id) 
        references user_details

    alter table user_details_related_to_roles 
        add constraint FKE6A5FBDEE3B038C2 
        foreign key (user_details_fk) 
        references user_details

    alter table user_details_related_to_roles 
        add constraint FKE6A5FBDE44F8149A 
        foreign key (granted_authorities_fk) 
        references granted_authorities

    alter table users 
        add constraint FK6A68E08E3B038C2 
        foreign key (user_details_fk) 
        references user_details

    alter table users 
        add constraint FK6A68E08F342C661 
        foreign key (sds_user_fk) 
        references sds_users

    alter table workgroups 
        add constraint FKEC8E50255AAC23E7 
        foreign key (sds_workgroup_fk) 
        references sds_workgroups

    alter table workgroups 
        add constraint FKEC8E502553AE0756 
        foreign key (offering_fk) 
        references offerings

    alter table workgroups_related_to_users 
        add constraint FKD724CDB256CA53B6 
        foreign key (user_fk) 
        references users

    alter table workgroups_related_to_users 
        add constraint FKD724CDB2F54443B2 
        foreign key (workgroup_fk) 
        references workgroups
