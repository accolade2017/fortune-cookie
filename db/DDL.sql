create table users_fortunes (
 user_id BIGINT not null
 , fortune_ymd VARCHAR(8) not null
 , fortune_id BIGINT not null
 , constraint users_fortunes_PKC primary key (user_id,fortune_ymd)
) ;

create table fortunes (
 fortune_id BIGINT not null
 , message TEXT
 , path VARCHAR(500) not null
 , score INT not null
 , enabled smallint default 1 not null
 , constraint fortunes_PKC primary key (fortune_id)
) ;

create table users (
 user_id serial not null
 , login_id VARCHAR(50) not null
 , password VARCHAR(255) not null
 , user_type CHAR(1) default '1' not null
 , created_at timestamp not null
 , constraint users_PKC primary key (user_id)
) ;

alter table users add unique users_login_id_uniq (login_id) ;
alter table users add column slack_user_name varchar(255);