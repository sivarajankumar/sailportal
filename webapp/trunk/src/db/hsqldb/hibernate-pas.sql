
    create table acl_class (
        id bigint generated by default as identity (start with 1),
        OPTLOCK integer,
        class varchar(255) not null,
        primary key (id),
        unique (class)
    );

    create table acl_entry (
        id bigint generated by default as identity (start with 1),
        ace_order integer not null,
        mask integer not null,
        granting bit not null,
        audit_success bit not null,
        audit_failure bit not null,
        OPTLOCK integer,
        acl_object_identity bigint not null,
        sid bigint not null,
        primary key (id),
        unique (acl_object_identity, ace_order)
    );

    create table acl_object_identity (
        id bigint generated by default as identity (start with 1),
        object_id_identity bigint not null,
        object_id_identity_num integer,
        entries_inheriting bit not null,
        OPTLOCK integer,
        object_id_class bigint not null,
        owner_sid bigint,
        parent_object bigint,
        primary key (id),
        unique (object_id_class, object_id_identity)
    );

    create table acl_sid (
        id bigint generated by default as identity (start with 1),
        OPTLOCK integer,
        principal bit not null,
        sid varchar(255) not null,
        primary key (id),
        unique (sid, principal)
    );

    create table annotationBundles (
        id bigint generated by default as identity (start with 1),
        OPTLOCK integer,
        bundle longvarchar not null,
        workgroup_fk bigint not null,
        primary key (id)
    );

    create table curnits (
        id bigint generated by default as identity (start with 1),
        OPTLOCK integer,
        sds_curnit_fk bigint not null,
        primary key (id),
        unique (sds_curnit_fk)
    );

    create table granted_authorities (
        id bigint generated by default as identity (start with 1),
        OPTLOCK integer,
        authority varchar(255) not null,
        primary key (id),
        unique (authority)
    );

    create table groups (
        id bigint generated by default as identity (start with 1),
        OPTLOCK integer,
        name varchar(255) not null,
        parent_fk bigint,
        primary key (id)
    );

    create table groups_related_to_users (
        group_fk bigint not null,
        user_fk bigint not null,
        primary key (group_fk, user_fk)
    );

    create table jnlps (
        id bigint generated by default as identity (start with 1),
        OPTLOCK integer,
        sds_jnlp_fk bigint not null,
        primary key (id),
        unique (sds_jnlp_fk)
    );

    create table offerings (
        id bigint generated by default as identity (start with 1),
        OPTLOCK integer,
        sds_offering_fk bigint not null,
        primary key (id),
        unique (sds_offering_fk)
    );

    create table sds_curnits (
        id bigint generated by default as identity (start with 1),
        OPTLOCK integer,
        curnit_id bigint not null,
        name varchar(255) not null,
        url varchar(255) not null,
        primary key (id),
        unique (curnit_id)
    );

    create table sds_jnlps (
        id bigint generated by default as identity (start with 1),
        OPTLOCK integer,
        jnlp_id bigint not null,
        name varchar(255) not null,
        url varchar(255) not null,
        primary key (id),
        unique (jnlp_id)
    );

    create table sds_offerings (
        id bigint generated by default as identity (start with 1),
        OPTLOCK integer,
        name varchar(255) not null,
        offering_id bigint not null,
        sds_curnitmap longvarchar,
        sds_curnit_fk bigint not null,
        sds_jnlp_fk bigint not null,
        primary key (id),
        unique (offering_id)
    );

    create table sds_users (
        id bigint generated by default as identity (start with 1),
        OPTLOCK integer,
        user_id bigint not null,
        first_name varchar(255) not null,
        last_name varchar(255) not null,
        primary key (id),
        unique (user_id)
    );

    create table sds_workgroups (
        id bigint generated by default as identity (start with 1),
        OPTLOCK integer,
        workgroup_id bigint not null,
        name varchar(255) not null,
        sds_sessionbundle longvarchar,
        sds_offering_fk bigint not null,
        primary key (id),
        unique (workgroup_id)
    );

    create table sds_workgroups_related_to_sds_users (
        sds_workgroup_fk bigint not null,
        sds_user_fk bigint not null,
        primary key (sds_workgroup_fk, sds_user_fk)
    );

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
    );

    create table user_details_related_to_roles (
        user_details_fk bigint not null,
        granted_authorities_fk bigint not null,
        primary key (user_details_fk, granted_authorities_fk)
    );

    create table users (
        id bigint generated by default as identity (start with 1),
        OPTLOCK integer,
        user_details_fk bigint not null,
        sds_user_fk bigint not null,
        primary key (id),
        unique (sds_user_fk),
        unique (user_details_fk)
    );

    create table workgroups (
        id bigint generated by default as identity (start with 1),
        OPTLOCK integer,
        group_fk bigint not null,
        sds_workgroup_fk bigint not null,
        offering_fk bigint not null,
        primary key (id),
        unique (sds_workgroup_fk)
    );

    alter table acl_entry 
        add constraint FK5302D47D9A4DE79D 
        foreign key (sid) 
        references acl_sid;

    alter table acl_entry 
        add constraint FK5302D47DC9975936 
        foreign key (acl_object_identity) 
        references acl_object_identity;

    alter table acl_object_identity 
        add constraint FK2A2BB009BDC00DA1 
        foreign key (parent_object) 
        references acl_object_identity;

    alter table acl_object_identity 
        add constraint FK2A2BB0092458F1A3 
        foreign key (object_id_class) 
        references acl_class;

    alter table acl_object_identity 
        add constraint FK2A2BB0099B5E7811 
        foreign key (owner_sid) 
        references acl_sid;

    alter table annotationBundles 
        add constraint FKD986A02F54443B2 
        foreign key (workgroup_fk) 
        references workgroups;

    alter table curnits 
        add constraint FK4329FBBA1B78E061 
        foreign key (sds_curnit_fk) 
        references sds_curnits;

    alter table groups 
        add constraint FKB63DD9D4E696E7FF 
        foreign key (parent_fk) 
        references groups;

    alter table groups_related_to_users 
        add constraint FK3311F7E3895EAE0A 
        foreign key (group_fk) 
        references groups;

    alter table groups_related_to_users 
        add constraint FK3311F7E356CA53B6 
        foreign key (user_fk) 
        references users;

    alter table jnlps 
        add constraint FK6095FABA532A941 
        foreign key (sds_jnlp_fk) 
        references sds_jnlps;

    alter table offerings 
        add constraint FK73F0F12DAB4F6201 
        foreign key (sds_offering_fk) 
        references sds_offerings;

    alter table sds_offerings 
        add constraint FK242EBD70A532A941 
        foreign key (sds_jnlp_fk) 
        references sds_jnlps;

    alter table sds_offerings 
        add constraint FK242EBD701B78E061 
        foreign key (sds_curnit_fk) 
        references sds_curnits;

    alter table sds_workgroups 
        add constraint FK440A0C42AB4F6201 
        foreign key (sds_offering_fk) 
        references sds_offerings;

    alter table sds_workgroups_related_to_sds_users 
        add constraint FKA31D36785AAC23E7 
        foreign key (sds_workgroup_fk) 
        references sds_workgroups;

    alter table sds_workgroups_related_to_sds_users 
        add constraint FKA31D3678F342C661 
        foreign key (sds_user_fk) 
        references sds_users;

    alter table user_details_related_to_roles 
        add constraint FKE6A5FBDEE3B038C2 
        foreign key (user_details_fk) 
        references user_details;

    alter table user_details_related_to_roles 
        add constraint FKE6A5FBDE44F8149A 
        foreign key (granted_authorities_fk) 
        references granted_authorities;

    alter table users 
        add constraint FK6A68E08E3B038C2 
        foreign key (user_details_fk) 
        references user_details;

    alter table users 
        add constraint FK6A68E08F342C661 
        foreign key (sds_user_fk) 
        references sds_users;

    alter table workgroups 
        add constraint FKEC8E5025895EAE0A 
        foreign key (group_fk) 
        references groups;

    alter table workgroups 
        add constraint FKEC8E50255AAC23E7 
        foreign key (sds_workgroup_fk) 
        references sds_workgroups;

    alter table workgroups 
        add constraint FKEC8E502553AE0756 
        foreign key (offering_fk) 
        references offerings;
