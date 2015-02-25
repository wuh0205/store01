-- Create table
create table USERS
(
  u_id     VARCHAR2(32) not null,
  name     VARCHAR2(100),
  age      NUMBER(3),
  address  VARCHAR2(2000),
  password VARCHAR2(20)
)
tablespace WH_DATA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns 
comment on column USERS.name
  is '名字';
comment on column USERS.age
  is '年龄';
comment on column USERS.address
  is '地址';
comment on column USERS.password
  is '密码';
-- Create/Recreate primary, unique and foreign key constraints 
alter table USERS
  add constraint PK_USERS_U_ID primary key (U_ID)
  using index 
  tablespace WH_DATA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

insert into USERS (U_ID, NAME, AGE, ADDRESS, PASSWORD)
values ('1', '张三丰', 100, '武当山', '123123');

insert into USERS (U_ID, NAME, AGE, ADDRESS, PASSWORD)
values ('2', '张翠山', 50, '武当山', '123123');

insert into USERS (U_ID, NAME, AGE, ADDRESS, PASSWORD)
values ('3', '张无忌', 20, '明教', '123123');

