
    create table acl_class (
        id bigint not null auto_increment,
        OPTLOCK integer,
        class varchar(255) not null unique,
        primary key (id)
    ) type=MyISAM;

    create table acl_entry (
        id bigint not null auto_increment,
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
    ) type=MyISAM;

    create table acl_object_identity (
        id bigint not null auto_increment,
        object_id_identity bigint not null,
        object_id_identity_num integer,
        entries_inheriting bit not null,
        OPTLOCK integer,
        parent_object bigint,
        object_id_class bigint not null,
        owner_sid bigint,
        primary key (id),
        unique (object_id_class, object_id_identity)
    ) type=MyISAM;

    create table acl_sid (
        id bigint not null auto_increment,
        OPTLOCK integer,
        principal bit not null,
        sid varchar(255) not null,
        primary key (id),
        unique (sid, principal)
    ) type=MyISAM;

    create table annotationBundles (
        id bigint not null auto_increment,
        OPTLOCK integer,
        bundle longtext not null,
        workgroup_fk bigint not null,
        primary key (id)
    ) type=MyISAM;

    create table curnits (
        id bigint not null auto_increment,
        OPTLOCK integer,
        sds_curnit_fk bigint not null unique,
        primary key (id)
    ) type=MyISAM;

    create table granted_authorities (
        id bigint not null auto_increment,
        OPTLOCK integer,
        authority varchar(255) not null unique,
        primary key (id)
    ) type=MyISAM;

    create table groups (
        id bigint not null auto_increment,
        OPTLOCK integer,
        name varchar(255) not null,
        parent_fk bigint,
        primary key (id)
    ) type=MyISAM;

    create table groups_related_to_users (
        group_fk bigint not null,
        user_fk bigint not null,
        primary key (group_fk, user_fk)
    ) type=MyISAM;

    create table jnlps (
        id bigint not null auto_increment,
        OPTLOCK integer,
        sds_jnlp_fk bigint not null unique,
        primary key (id)
    ) type=MyISAM;

    create table offerings (
        id bigint not null auto_increment,
        OPTLOCK integer,
        sds_offering_fk bigint not null unique,
        primary key (id)
    ) type=MyISAM;

    create table runs (
        id bigint not null,
        start_time datetime not null,
        end_time datetime,
        run_code varchar(255) not null unique,
        primary key (id)
    ) type=MyISAM;

    create table runs_related_to_groups (
        runs_fk bigint not null,
        groups_fk bigint not null,
        primary key (runs_fk, groups_fk),
        unique (groups_fk)
    ) type=MyISAM;

    create table runs_related_to_owners (
        runs_fk bigint not null,
        owners_fk bigint not null,
        primary key (runs_fk, owners_fk)
    ) type=MyISAM;

    create table sds_curnits (
        id bigint not null auto_increment,
        OPTLOCK integer,
        curnit_id bigint not null unique,
        name varchar(255) not null,
        url varchar(255) not null,
        primary key (id)
    ) type=MyISAM;

    create table sds_jnlps (
        id bigint not null auto_increment,
        OPTLOCK integer,
        jnlp_id bigint not null unique,
        name varchar(255) not null,
        url varchar(255) not null,
        primary key (id)
    ) type=MyISAM;

    create table sds_offerings (
        id bigint not null auto_increment,
        OPTLOCK integer,
        name varchar(255) not null,
        offering_id bigint not null unique,
        sds_curnitmap longtext,
        sds_curnit_fk bigint not null,
        sds_jnlp_fk bigint not null,
        primary key (id)
    ) type=MyISAM;

    create table sds_users (
        id bigint not null auto_increment,
        OPTLOCK integer,
        user_id bigint not null unique,
        first_name varchar(255) not null,
        last_name varchar(255) not null,
        primary key (id)
    ) type=MyISAM;

    create table sds_workgroups (
        id bigint not null auto_increment,
        OPTLOCK integer,
        workgroup_id bigint not null unique,
        name varchar(255) not null,
        sds_sessionbundle longtext,
        sds_offering_fk bigint not null,
        primary key (id)
    ) type=MyISAM;

    create table sds_workgroups_related_to_sds_users (
        sds_workgroup_fk bigint not null,
        sds_user_fk bigint not null,
        primary key (sds_workgroup_fk, sds_user_fk)
    ) type=MyISAM;

    create table student_user_details (
        id bigint not null,
        firstname varchar(255) not null,
        lastname varchar(255) not null,
        signupdate datetime not null,
        gender integer not null,
        birthday datetime not null,
        numberoflogins integer not null,
        lastlogintime datetime,
        accountquestion varchar(255) not null,
        accountanswer varchar(255) not null,
        primary key (id)
    ) type=MyISAM;

    create table teacher_user_details (
        id bigint not null,
        firstname varchar(255) not null,
        lastname varchar(255) not null,
        signupdate datetime not null,
        city varchar(255),
        state varchar(255),
        country varchar(255) not null,
        schoolname varchar(255) not null,
        curriculumsubjects tinyblob not null,
        schoollevel integer not null,
        numberoflogins integer not null,
        lastlogintime datetime,
        primary key (id)
    ) type=MyISAM;

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
    ) type=MyISAM;

    create table user_details_related_to_roles (
        user_details_fk bigint not null,
        granted_authorities_fk bigint not null,
        primary key (user_details_fk, granted_authorities_fk)
    ) type=MyISAM;

    create table users (
        id bigint not null auto_increment,
        OPTLOCK integer,
        sds_user_fk bigint not null unique,
        user_details_fk bigint not null unique,
        primary key (id)
    ) type=MyISAM;

    create table wiseworkgroups (
        id bigint not null,
        period bigint,
        primary key (id)
    ) type=MyISAM;

    create table workgroups (
        id bigint not null auto_increment,
        OPTLOCK integer,
        group_fk bigint not null,
        offering_fk bigint not null,
        sds_workgroup_fk bigint not null unique,
        primary key (id)
    ) type=MyISAM;

    alter table acl_entry 
        add index FK5302D47DC9975936 (acl_object_identity), 
        add constraint FK5302D47DC9975936 
        foreign key (acl_object_identity) 
        references acl_object_identity (id);

    alter table acl_entry 
        add index FK5302D47D9A4DE79D (sid), 
        add constraint FK5302D47D9A4DE79D 
        foreign key (sid) 
        references acl_sid (id);

    alter table acl_object_identity 
        add index FK2A2BB0099B5E7811 (owner_sid), 
        add constraint FK2A2BB0099B5E7811 
        foreign key (owner_sid) 
        references acl_sid (id);

    alter table acl_object_identity 
        add index FK2A2BB009BDC00DA1 (parent_object), 
        add constraint FK2A2BB009BDC00DA1 
        foreign key (parent_object) 
        references acl_object_identity (id);

    alter table acl_object_identity 
        add index FK2A2BB0092458F1A3 (object_id_class), 
        add constraint FK2A2BB0092458F1A3 
        foreign key (object_id_class) 
        references acl_class (id);

    alter table annotationBundles 
        add index FKD986A02F54443B2 (workgroup_fk), 
        add constraint FKD986A02F54443B2 
        foreign key (workgroup_fk) 
        references workgroups (id);

    alter table curnits 
        add index FK4329FBBA1B78E061 (sds_curnit_fk), 
        add constraint FK4329FBBA1B78E061 
        foreign key (sds_curnit_fk) 
        references sds_curnits (id);

    alter table groups 
        add index FKB63DD9D4E696E7FF (parent_fk), 
        add constraint FKB63DD9D4E696E7FF 
        foreign key (parent_fk) 
        references groups (id);

    alter table groups_related_to_users 
        add index FK3311F7E356CA53B6 (user_fk), 
        add constraint FK3311F7E356CA53B6 
        foreign key (user_fk) 
        references users (id);

    alter table groups_related_to_users 
        add index FK3311F7E3895EAE0A (group_fk), 
        add constraint FK3311F7E3895EAE0A 
        foreign key (group_fk) 
        references groups (id);

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

    alter table runs 
        add index FK3597481834F8D3 (id), 
        add constraint FK3597481834F8D3 
        foreign key (id) 
        references offerings (id);

    alter table runs_related_to_groups 
        add index FK6CD673CD50B193C8 (runs_fk), 
        add constraint FK6CD673CD50B193C8 
        foreign key (runs_fk) 
        references runs (id);

    alter table runs_related_to_groups 
        add index FK6CD673CD12D98E95 (groups_fk), 
        add constraint FK6CD673CD12D98E95 
        foreign key (groups_fk) 
        references groups (id);

    alter table runs_related_to_owners 
        add index FK7AC2FE1960AA7F41 (owners_fk), 
        add constraint FK7AC2FE1960AA7F41 
        foreign key (owners_fk) 
        references users (id);

    alter table runs_related_to_owners 
        add index FK7AC2FE1950B193C8 (runs_fk), 
        add constraint FK7AC2FE1950B193C8 
        foreign key (runs_fk) 
        references runs (id);

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

    alter table student_user_details 
        add index FKC5AA2952D1D25907 (id), 
        add constraint FKC5AA2952D1D25907 
        foreign key (id) 
        references user_details (id);

    alter table teacher_user_details 
        add index FKAC84070BD1D25907 (id), 
        add constraint FKAC84070BD1D25907 
        foreign key (id) 
        references user_details (id);

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

    alter table wiseworkgroups 
        add index FKF16C83C93013AD46 (period), 
        add constraint FKF16C83C93013AD46 
        foreign key (period) 
        references groups (id);

    alter table wiseworkgroups 
        add index FKF16C83C9F309B437 (id), 
        add constraint FKF16C83C9F309B437 
        foreign key (id) 
        references workgroups (id);

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

    alter table workgroups 
        add index FKEC8E5025895EAE0A (group_fk), 
        add constraint FKEC8E5025895EAE0A 
        foreign key (group_fk) 
        references groups (id);
