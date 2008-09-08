
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
        sid bigint not null,
        acl_object_identity bigint not null,
        primary key (id),
        unique (acl_object_identity, ace_order)
    );

    create table acl_object_identity (
        id bigint generated by default as identity (start with 1),
        object_id_identity bigint not null,
        object_id_identity_num integer,
        entries_inheriting bit not null,
        OPTLOCK integer,
        owner_sid bigint,
        object_id_class bigint not null,
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

    create table brainstormanswers (
        id bigint generated by default as identity (start with 1),
        OPTLOCK integer,
        workgroups_fk bigint not null,
        primary key (id)
    );

    create table brainstormanswers_related_to_brainstormcomments (
        brainstormanswers_fk bigint not null,
        brainstormcomments_fk bigint not null,
        primary key (brainstormanswers_fk, brainstormcomments_fk),
        unique (brainstormcomments_fk)
    );

    create table brainstormcomments (
        id bigint generated by default as identity (start with 1),
        OPTLOCK integer,
        primary key (id)
    );

    create table brainstormquestions (
        id bigint generated by default as identity (start with 1),
        OPTLOCK integer,
        body longvarchar,
        primary key (id)
    );

    create table brainstorms (
        id bigint generated by default as identity (start with 1),
        OPTLOCK integer,
        brainstormquestions_fk bigint,
        runs_fk bigint not null,
        primary key (id)
    );

    create table brainstorms_related_to_brainstormanswers (
        brainstorms_fk bigint not null,
        brainstormanswers_fk bigint not null,
        primary key (brainstorms_fk, brainstormanswers_fk),
        unique (brainstormanswers_fk)
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

    create table modules (
        id bigint not null,
        description varchar(255),
        total_time bigint,
        computer_time bigint,
        tech_reqs varchar(255),
        primary key (id)
    );

    create table modules_related_to_owners (
        module_fk bigint not null,
        owners_fk bigint not null,
        primary key (module_fk, owners_fk)
    );

    create table newsitem (
        id bigint generated by default as identity (start with 1),
        news longvarchar not null,
        date timestamp not null,
        title varchar(255) not null,
        owner bigint not null,
        primary key (id)
    );

    create table offerings (
        id bigint generated by default as identity (start with 1),
        OPTLOCK integer,
        sds_offering_fk bigint not null,
        primary key (id),
        unique (sds_offering_fk)
    );

    create table premadecommentlists (
        id bigint generated by default as identity (start with 1),
        label varchar(255) not null,
        owner bigint,
        run bigint,
        primary key (id)
    );

    create table premadecomments (
        id bigint generated by default as identity (start with 1),
        comment varchar(255) not null,
        label varchar(255) not null,
        owner bigint,
        run bigint,
        primary key (id)
    );

    create table premadecomments_related_to_premadecommentlists (
        premadecommentslist_fk bigint not null,
        premadecomments_fk bigint not null,
        primary key (premadecommentslist_fk, premadecomments_fk)
    );

    create table projects (
        id bigint generated by default as identity (start with 1),
        familytag integer,
        iscurrent bit,
        OPTLOCK integer,
        run_fk bigint,
        jnlp_fk bigint not null,
        curnit_fk bigint not null,
        primary key (id),
        unique (run_fk)
    );

    create table roolootmlmodules (
        id bigint not null,
        roolomoduleuri varchar(255),
        roolorepositoryurl varchar(255),
        primary key (id)
    );

    create table runs (
        id bigint not null,
        start_time timestamp not null,
        end_time timestamp,
        run_code varchar(255) not null,
        project_fk bigint not null,
        primary key (id),
        unique (run_code)
    );

    create table runs_related_to_groups (
        runs_fk bigint not null,
        groups_fk bigint not null,
        primary key (runs_fk, groups_fk),
        unique (groups_fk)
    );

    create table runs_related_to_owners (
        runs_fk bigint not null,
        owners_fk bigint not null,
        primary key (runs_fk, owners_fk)
    );

    create table runs_related_to_shared_owners (
        runs_fk bigint not null,
        shared_owners_fk bigint not null,
        primary key (runs_fk, shared_owners_fk)
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
        sds_jnlp_fk bigint not null,
        sds_curnit_fk bigint not null,
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

    create table student_user_details (
        id bigint not null,
        firstname varchar(255) not null,
        lastname varchar(255) not null,
        signupdate timestamp not null,
        gender integer not null,
        birthday timestamp not null,
        numberoflogins integer not null,
        lastlogintime timestamp,
        accountquestion varchar(255) not null,
        accountanswer varchar(255) not null,
        primary key (id)
    );

    create table teacher_user_details (
        id bigint not null,
        firstname varchar(255) not null,
        lastname varchar(255) not null,
        signupdate timestamp not null,
        city varchar(255),
        state varchar(255),
        country varchar(255) not null,
        schoolname varchar(255) not null,
        curriculumsubjects varbinary(255) not null,
        schoollevel integer not null,
        numberoflogins integer not null,
        lastlogintime timestamp,
        displayname varchar(255),
        primary key (id)
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

    create table wiseworkgroups (
        id bigint not null,
        period bigint,
        primary key (id)
    );

    create table workgroups (
        id bigint generated by default as identity (start with 1),
        OPTLOCK integer,
        offering_fk bigint not null,
        sds_workgroup_fk bigint not null,
        group_fk bigint not null,
        primary key (id),
        unique (sds_workgroup_fk)
    );

    alter table acl_entry 
        add constraint FK5302D47DC9975936 
        foreign key (acl_object_identity) 
        references acl_object_identity;

    alter table acl_entry 
        add constraint FK5302D47D9A4DE79D 
        foreign key (sid) 
        references acl_sid;

    alter table acl_object_identity 
        add constraint FK2A2BB0099B5E7811 
        foreign key (owner_sid) 
        references acl_sid;

    alter table acl_object_identity 
        add constraint FK2A2BB009BDC00DA1 
        foreign key (parent_object) 
        references acl_object_identity;

    alter table acl_object_identity 
        add constraint FK2A2BB0092458F1A3 
        foreign key (object_id_class) 
        references acl_class;

    alter table annotationBundles 
        add constraint FKD986A02F54443B2 
        foreign key (workgroup_fk) 
        references workgroups;

    alter table brainstormanswers 
        add constraint FK678121622B7BFD8A 
        foreign key (workgroups_fk) 
        references wiseworkgroups;

    alter table brainstormanswers_related_to_brainstormcomments 
        add constraint FKCF105FBA2605B8EA 
        foreign key (brainstormanswers_fk) 
        references brainstormanswers;

    alter table brainstormanswers_related_to_brainstormcomments 
        add constraint FKCF105FBAA73BCAE9 
        foreign key (brainstormcomments_fk) 
        references brainstormcomments;

    alter table brainstorms 
        add constraint FK174BDF2050B193C8 
        foreign key (runs_fk) 
        references runs;

    alter table brainstorms 
        add constraint FK174BDF205E4D3D22 
        foreign key (brainstormquestions_fk) 
        references brainstormquestions;

    alter table brainstorms_related_to_brainstormanswers 
        add constraint FK477CA8F12605B8EA 
        foreign key (brainstormanswers_fk) 
        references brainstormanswers;

    alter table brainstorms_related_to_brainstormanswers 
        add constraint FK477CA8F179D46939 
        foreign key (brainstorms_fk) 
        references brainstorms;

    alter table curnits 
        add constraint FK4329FBBA1B78E061 
        foreign key (sds_curnit_fk) 
        references sds_curnits;

    alter table groups 
        add constraint FKB63DD9D4E696E7FF 
        foreign key (parent_fk) 
        references groups;

    alter table groups_related_to_users 
        add constraint FK3311F7E356CA53B6 
        foreign key (user_fk) 
        references users;

    alter table groups_related_to_users 
        add constraint FK3311F7E3895EAE0A 
        foreign key (group_fk) 
        references groups;

    alter table jnlps 
        add constraint FK6095FABA532A941 
        foreign key (sds_jnlp_fk) 
        references sds_jnlps;

    alter table modules 
        add constraint FK492927875E6F3BA6 
        foreign key (id) 
        references curnits;

    alter table modules_related_to_owners 
        add constraint FKE09C9860AA7F41 
        foreign key (owners_fk) 
        references users;

    alter table modules_related_to_owners 
        add constraint FKE09C9839A4B723 
        foreign key (module_fk) 
        references modules;

    alter table newsitem 
        add constraint FK532D646665E358B0 
        foreign key (owner) 
        references users;

    alter table offerings 
        add constraint FK73F0F12DAB4F6201 
        foreign key (sds_offering_fk) 
        references sds_offerings;

    alter table premadecommentlists 
        add constraint FKF237B2CEF4421937 
        foreign key (run) 
        references runs;

    alter table premadecommentlists 
        add constraint FKF237B2CE65E358B0 
        foreign key (owner) 
        references users;

    alter table premadecomments 
        add constraint FK7786D42CF4421937 
        foreign key (run) 
        references runs;

    alter table premadecomments 
        add constraint FK7786D42C65E358B0 
        foreign key (owner) 
        references users;

    alter table premadecomments_related_to_premadecommentlists 
        add constraint FK6958FC11C8153CF5 
        foreign key (premadecomments_fk) 
        references premadecomments;

    alter table premadecomments_related_to_premadecommentlists 
        add constraint FK6958FC112FC6E4D5 
        foreign key (premadecommentslist_fk) 
        references premadecommentlists;

    alter table projects 
        add constraint FKC479187A7F08E576 
        foreign key (curnit_fk) 
        references curnits;

    alter table projects 
        add constraint FKC479187ABD6D05A5 
        foreign key (run_fk) 
        references runs;

    alter table projects 
        add constraint FKC479187A9568F016 
        foreign key (jnlp_fk) 
        references jnlps;

    alter table roolootmlmodules 
        add constraint FKCB4BDACE5E6F3BA6 
        foreign key (id) 
        references curnits;

    alter table runs 
        add constraint FK3597486F1ED29A 
        foreign key (project_fk) 
        references projects;

    alter table runs 
        add constraint FK3597481834F8D3 
        foreign key (id) 
        references offerings;

    alter table runs_related_to_groups 
        add constraint FK6CD673CD50B193C8 
        foreign key (runs_fk) 
        references runs;

    alter table runs_related_to_groups 
        add constraint FK6CD673CD12D98E95 
        foreign key (groups_fk) 
        references groups;

    alter table runs_related_to_owners 
        add constraint FK7AC2FE1960AA7F41 
        foreign key (owners_fk) 
        references users;

    alter table runs_related_to_owners 
        add constraint FK7AC2FE1950B193C8 
        foreign key (runs_fk) 
        references runs;

    alter table runs_related_to_shared_owners 
        add constraint FKBD30D52150B193C8 
        foreign key (runs_fk) 
        references runs;

    alter table runs_related_to_shared_owners 
        add constraint FKBD30D521DB63ABE7 
        foreign key (shared_owners_fk) 
        references users;

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

    alter table student_user_details 
        add constraint FKC5AA2952D1D25907 
        foreign key (id) 
        references user_details;

    alter table teacher_user_details 
        add constraint FKAC84070BD1D25907 
        foreign key (id) 
        references user_details;

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

    alter table wiseworkgroups 
        add constraint FKF16C83C93013AD46 
        foreign key (period) 
        references groups;

    alter table wiseworkgroups 
        add constraint FKF16C83C9F309B437 
        foreign key (id) 
        references workgroups;

    alter table workgroups 
        add constraint FKEC8E50255AAC23E7 
        foreign key (sds_workgroup_fk) 
        references sds_workgroups;

    alter table workgroups 
        add constraint FKEC8E502553AE0756 
        foreign key (offering_fk) 
        references offerings;

    alter table workgroups 
        add constraint FKEC8E5025895EAE0A 
        foreign key (group_fk) 
        references groups;
