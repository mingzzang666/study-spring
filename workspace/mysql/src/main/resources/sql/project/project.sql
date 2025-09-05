# 병원 테이블
create table tbl_hospital(
     id bigint unsigned auto_increment primary key,
     hospital_name varchar(255) not null,
     hospital_status enum('active', 'inactive') default 'active',
     created_date datetime default current_timestamp,
     updated_date datetime default current_timestamp
);

select * from tbl_hospital;

# 의사 테이블
create table tbl_doctor(
    id bigint unsigned auto_increment primary key,
    doctor_name varchar(255) not null,
    doctor_status enum('active', 'inactive') default 'active',
    doctor_license_number int default 0,
    doctor_specialty varchar(255) not null,
    hospital_id bigint unsigned not null,
    created_date datetime default current_timestamp,
    updated_date datetime default current_timestamp,
    constraint fk_doctor_hospital foreign key (hospital_id)
        references tbl_hospital(id)
);

select * from tbl_doctor;

# 주소 테이블

create table tbl_address(
   id bigint unsigned auto_increment primary key,
   road_address varchar(255) not null,
   detail_address varchar(255) not null,
   latitude decimal(10,7),
   longitude decimal(10,7),
   created_date datetime default current_timestamp,
   updated_date datetime default current_timestamp
);

# 병원-주소 연결 테이블

create table tbl_hospital_address(
    address_id bigint unsigned primary key,
    hospital_id bigint unsigned not null,
    constraint fk_hospital_address_address foreign key (address_id)
        references tbl_address (id)
);