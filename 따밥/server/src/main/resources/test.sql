create table member(
                       id bigint not null primary key auto_increment,
                       name varchar(20) not null ,
                       nickname varchar(20) not null unique ,
                       gender enum('MALE', 'FEMALE', 'OTHER') not null default 'OTHER',
                       phone_number varchar(11) not null unique ,
                       photo TEXT,
                       authority enum('ROLE_USER', 'ROLE_OWNER') not null default 'ROLE_USER',
                       point int4 not null default 0,
                       login_type enum('INAPP', 'KAKAO', 'NAVER', 'APPLE', 'GOOGLE') not null default 'INAPP',
                       status varchar(20) not null default 'ACTIVE',
                       inactive_date datetime,
                       created_at datetime(6) not null DEFAULT CURRENT_TIMESTAMP(6),
                       updated_at datetime(6) not null DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6)
);

create table social_login(
                             id bigint not null primary key auto_increment,
                             member_id bigint not null,
                             provider enum('KAKAO', 'NAVER', 'APPLE', 'GOOGLE') not null,
                             provider_id varchar(255) not null,
                             created_at datetime(6) not null default current_timestamp(6),

                             unique key uq_provider_id (provider, provider_id),

                             foreign key (member_id) references member(id) on delete cascade
);

create table customer_inquiry(
                                 id bigint not null primary key auto_increment,
                                 type enum('SERVICE', 'ACCOUNT', 'PAYMENT', 'EXCHANGE', 'REFUND', 'OTHER') not null,
                                 photo TEXT,
                                 description TEXT not null,
                                 title varchar(20) not null,
                                 created_at datetime(6) not null default current_timestamp(6),

                                 member_id bigint not null,
                                 foreign key (member_id) references member(id) on delete cascade
);

create table customer_inquiry_response(
                                          inquiry_id bigint not null primary key ,
                                          body TEXT not null ,
                                          created_at datetime(6) not null default current_timestamp(6),
                                          foreign key (inquiry_id) references customer_inquiry(id) on delete cascade
);

create table food_type(
                          id bigint not null primary key auto_increment,
                          name varchar(20) not null
);

create table member_food_type(
                                 id bigint not null primary key auto_increment,
                                 member_id bigint not null,
                                 food_type_id bigint not null ,

                                 foreign key (member_id) references member(id) on delete cascade ,
                                 foreign key (food_type_id) references food_type(id) on delete restrict
);

create table restaurant(
                           id bigint not null primary key auto_increment,
                           name varchar(20) not null,
                           score decimal not null default 0.0,
                           opening_time time not null,
                           closing_time time not null,
                           break_start_time time null,
                           break_end_time time null,
                           food_type_id bigint not null,

                           foreign key (food_type_id) references food_type(id) on delete restrict
);

create table restaurant_addr(
                                restaurant_id bigint not null primary key,
                                addr1 varchar(20) not null ,
                                addr2 varchar(20) not null,
                                addr3 varchar(20) not null ,
                                addr4 varchar(20) not null ,
                                latitude decimal not null ,
                                longitude decimal not null,

                                foreign key (restaurant_id) references restaurant(id) on delete cascade
);

create table mission(
                        id bigint not null primary key auto_increment,
                        description TEXT not null ,
                        point int4 not null default 0,
                        created_at datetime(6) not null default current_timestamp(6),
                        deadline datetime not null ,
                        restaurant_id bigint not null,

                        foreign key (restaurant_id) references restaurant(id) on delete restrict
);

create table member_mission(
                               id bigint not null primary key auto_increment,
                               is_completed boolean not null default FALSE,
                               is_provided boolean not null  default FALSE,
                               is_reviewed boolean not null default FALSE,
                               member_id bigint not null ,
                               mission_id bigint not null ,

                               foreign key (member_id) references member(id) on delete cascade ,
                               foreign key (mission_id) references mission(id) on delete cascade
);

create table review(
                       id bigint not null primary key auto_increment,
                       description TEXT not null ,
                       score int4 not null default 0,
                       created_at datetime(6) not null default current_timestamp(6),
                       photo TEXT,
                       member_mission_id bigint not null ,

                       foreign key (member_mission_id) references member_mission(id) on delete cascade
);

create table re_review(
                          id bigint not null primary key auto_increment,
                          body TEXT not null ,
                          created_at datetime(6) not null default current_timestamp(6),

                          member_id bigint not null ,
                          review_id bigint not null ,

                          foreign key (member_id) references member(id) on delete cascade ,
                          foreign key (review_id) references review(id) on delete cascade
);

create table address(
                        member_id bigint not null primary key ,
                        foreign key (member_id) references member(id) on delete cascade ,

                        addr1 varchar(20) not null ,
                        addr2 varchar(20) not null ,
                        addr3 varchar(20) not null ,
                        addr4 varchar(20) not null
);

create table term(
                     id bigint not null primary key auto_increment,
                     name varchar(20) not null ,
                     is_required boolean not null default FALSE
);

create table member_term(
                            member_id bigint not null ,
                            term_id bigint not null,

                            primary key (member_id, term_id),

                            is_agreed boolean not null default FALSE,
                            agreed_at datetime(6) not null default current_timestamp(6),

                            foreign key (member_id) references member(id) on delete restrict ,
                            foreign key (term_id) references term(id) on delete restrict
);

create table alarm(
                      id bigint not null primary key auto_increment,
                      member_id bigint not null ,
                      foreign key (member_id) references member(id) on delete cascade ,

                      type varchar(20) not null ,
                      body TEXT not null ,

                      created_at datetime(6) not null default current_timestamp(6)
);
