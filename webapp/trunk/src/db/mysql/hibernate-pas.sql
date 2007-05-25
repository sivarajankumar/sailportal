
    create table curnits (
        id bigint not null auto_increment,
        OPTLOCK integer,
        sds_curnit_fk bigint not null unique,
        primary key (id)
    ) type=InnoDB;

    create table granted_authorities (
        id bigint not null auto_increment,
        OPTLOCK integer,
        authority varchar(255) not null unique,
        primary key (id)
    ) type=InnoDB;

    create table jnlps (
        id bigint not null auto_increment,
        OPTLOCK integer,
        sds_jnlp_fk bigint not null unique,
        primary key (id)
    ) type=InnoDB;

    create table offerings (
        id bigint not null auto_increment,
        OPTLOCK integer,
        sds_offering_fk bigint not null unique,
        primary key (id)
    ) type=InnoDB;

    create table sds_curnits (
        id bigint not null auto_increment,
        OPTLOCK integer,
        curnit_id integer not null unique,
        name varchar(255) not null,
        url varchar(255) not null,
        primary key (id)
    ) type=InnoDB;

    create table sds_jnlps (
        id bigint not null auto_increment,
        OPTLOCK integer,
        jnlp_id integer not null unique,
        name varchar(255) not null,
        url varchar(255) not null,
        primary key (id)
    ) type=InnoDB;

    create table sds_offerings (
        id bigint not null auto_increment,
        OPTLOCK integer,
        name varchar(255) not null,
        offering_id integer not null unique,
        sds_curnit_fk bigint not null,
        sds_jnlp_fk bigint not null,
        primary key (id)
    ) type=InnoDB;

    create table sds_users (
        id bigint not null auto_increment,
        OPTLOCK integer,
        user_id integer not null unique,
        first_name varchar(255) not null,
        last_name varchar(255) not null,
        primary key (id)
    ) type=InnoDB;

    create table sds_workgroups (
        id bigint not null auto_increment,
        OPTLOCK integer,
        workgroup_id integer not null unique,
        name varchar(255) not null,
        sds_offering_fk bigint not null,
        primary key (id)
    ) type=InnoDB;

    create table sds_workgroups_related_to_sds_users (
        sds_workgroup_fk bigint not null,
        sds_user_fk bigint not null,
        primary key (sds_workgroup_fk, sds_user_fk)
    ) type=InnoDB;

    create table user_details (
        id bigint not null auto_increment,
        OPTLOCK integer,
        password varchar(255) not null,
        username varchar(255) not null unique,
        email_address varchar(255),
        account_not_expired bit not null,
        account_not_locked bit not null,
        credentials_not_expired bit not null,
        enabled bit not null,
        primary key (id)
    ) type=InnoDB;

    create table user_details_related_to_roles (
        user_details_fk bigint not null,
        granted_authorities_fk bigint not null,
        primary key (user_details_fk, granted_authorities_fk)
    ) type=InnoDB;

    create table users (
        id bigint not null auto_increment,
        OPTLOCK integer,
        sds_user_fk bigint not null unique,
        user_details_fk bigint not null unique,
        primary key (id)
    ) type=InnoDB;

    create table workgroups (
        id bigint not null auto_increment,
        OPTLOCK integer,
        offering_fk bigint not null,
        sds_workgroup_fk bigint not null unique,
        primary key (id)
    ) type=InnoDB;

    create table workgroups_related_to_users (
        workgroup_fk bigint not null,
        user_fk bigint not null,
        primary key (workgroup_fk, user_fk)
    ) type=InnoDB;

    alter table curnits 
        add index FK4329FBBA1B78E061 (sds_curnit_fk), 
        add constraint FK4329FBBA1B78E061 
        foreign key (sds_curnit_fk) 
        references sds_curnits (id);

    alter table jnlps 
        add index FK6095FABA532A941 (sds_jnlp_fk), 
        add constraint FK6095FABA532A941 
        foreign key (sds_jnlp_fk) 
        references sds_jnlps (id);

    alter table offerings 
        add index FK73F0F12DAB4F6201 (sds_offering_fk), 
        add constraint FK73F0F12DAB4F6201 
        foreign key (sds_offering_fk) 
        references sds_offerings (id);

    alter table sds_offerings 
        add index FK242EBD70A532A941 (sds_jnlp_fk), 
        add constraint FK242EBD70A532A941 
        foreign key (sds_jnlp_fk) 
        references sds_jnlps (id);

    alter table sds_offerings 
        add index FK242EBD701B78E061 (sds_curnit_fk), 
        add constraint FK242EBD701B78E061 
        foreign key (sds_curnit_fk) 
        references sds_curnits (id);

    alter table sds_workgroups 
        add index FK440A0C42AB4F6201 (sds_offering_fk), 
        add constraint FK440A0C42AB4F6201 
        foreign key (sds_offering_fk) 
        references sds_offerings (id);

    alter table sds_workgroups_related_to_sds_users 
        add index FKA31D36785AAC23E7 (sds_workgroup_fk), 
        add constraint FKA31D36785AAC23E7 
        foreign key (sds_workgroup_fk) 
        references sds_workgroups (id);

    alter table sds_workgroups_related_to_sds_users 
        add index FKA31D3678F342C661 (sds_user_fk), 
        add constraint FKA31D3678F342C661 
        foreign key (sds_user_fk) 
        references sds_users (id);

    alter table user_details_related_to_roles 
        add index FKE6A5FBDEE3B038C2 (user_details_fk), 
        add constraint FKE6A5FBDEE3B038C2 
        foreign key (user_details_fk) 
        references user_details (id);

    alter table user_details_related_to_roles 
        add index FKE6A5FBDE44F8149A (granted_authorities_fk), 
        add constraint FKE6A5FBDE44F8149A 
        foreign key (granted_authorities_fk) 
        references granted_authorities (id);

    alter table users 
        add index FK6A68E08E3B038C2 (user_details_fk), 
        add constraint FK6A68E08E3B038C2 
        foreign key (user_details_fk) 
        references user_details (id);

    alter table users 
        add index FK6A68E08F342C661 (sds_user_fk), 
        add constraint FK6A68E08F342C661 
        foreign key (sds_user_fk) 
        references sds_users (id);

    alter table workgroups 
        add index FKEC8E50255AAC23E7 (sds_workgroup_fk), 
        add constraint FKEC8E50255AAC23E7 
        foreign key (sds_workgroup_fk) 
        references sds_workgroups (id);

    alter table workgroups 
        add index FKEC8E502553AE0756 (offering_fk), 
        add constraint FKEC8E502553AE0756 
        foreign key (offering_fk) 
        references offerings (id);

    alter table workgroups_related_to_users 
        add index FKD724CDB256CA53B6 (user_fk), 
        add constraint FKD724CDB256CA53B6 
        foreign key (user_fk) 
        references users (id);

    alter table workgroups_related_to_users 
        add index FKD724CDB2F54443B2 (workgroup_fk), 
        add constraint FKD724CDB2F54443B2 
        foreign key (workgroup_fk) 
        references workgroups (id);
