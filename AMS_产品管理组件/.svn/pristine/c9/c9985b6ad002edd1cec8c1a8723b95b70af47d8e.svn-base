create table T_T_RELATION
(
  c_relation_id VARCHAR2(20) not null,
  c_task_id     VARCHAR2(20) not null,
  c_re_task_id  VARCHAR2(20) not null,
  n_type        NUMBER(22) default 0 not null
);
alter table T_T_RELATION add constraint PK_T_T_RELATION primary key (C_RELATION_ID);

create table T_T_TASK
(
  c_task_id    VARCHAR2(20) default ' ' not null,
  c_code       VARCHAR2(50) default ' ' not null,
  c_name       VARCHAR2(50) default ' ' not null,
  n_type       NUMBER(1) default 0 not null,
  c_item_id    VARCHAR2(20),
  n_enable     NUMBER(1) default '0' not null,
  c_user_id    VARCHAR2(20) default ' ' not null,
  c_sys_id     VARCHAR2(100) default '' not null,
  c_desc       VARCHAR2(500),
  c_creator    VARCHAR2(20),
  c_start_time TIMESTAMP(6),
  c_state      VARCHAR2(20),
  c_end_time   TIMESTAMP(6)
);
alter table T_T_TASK  add constraint PK_T_TASK primary key (C_TASK_ID);


create table T_T_TRIGGER
(
  c_trigger_id   VARCHAR2(20) default ' ' not null,
  c_task_id      VARCHAR2(20) default ' ' not null,
  n_type         NUMBER(1) default 0 not null,
  n_repeat       NUMBER(2) default 0 not null,
  c_repeat       VARCHAR2(1000) default ' ' not null,
  d_start        TIMESTAMP(6),
  n_endtype      NUMBER(1) default 0 not null,
  c_end          VARCHAR2(100),
  c_execute_rule VARCHAR2(2000)
);
alter table T_T_TRIGGER  add constraint PK_T_TRIGGER primary key (C_TRIGGER_ID);

create table T_T_TASK_EXT
(
  c_task_id       VARCHAR2(20) default ' ' not null,
  d_create_time   TIMESTAMP(6),
  n_directional   NUMBER(22) default 0 not null,
  c_fun_code      VARCHAR2(100) default ' ',
  c_belong_module VARCHAR2(100) default ' ' not null,
  n_execu_type    NUMBER(22) default 0 not null,
  c_task_urls     VARCHAR2(1000),
  c_execute_user  VARCHAR2(100),
  n_cycle_flag    NUMBER(22) default 0 not null,
  c_remark        VARCHAR2(1000),
  c_dv_oper_type  VARCHAR2(20) default ' '
);
alter table T_T_TASK_EXT  add constraint PK_T_TASK_EXT primary key (C_TASK_ID);

create table T_T_PARAMS
(
  c_param_id VARCHAR2(20) default ' ' not null,
  c_task_id  VARCHAR2(20) default ' ' not null,
  c_item_id  VARCHAR2(20) default ' ' not null,
  c_code     VARCHAR2(100) default ' ' not null,
  c_value    VARCHAR2(1000) default ' ' not null
);
alter table T_T_PARAMS  add constraint PK_T_PARAMS primary key (C_PARAM_ID);

create table T_S_WF_DISPATCH
(
  c_iden         VARCHAR2(30) not null,
  c_plan_code    VARCHAR2(20) default ' ' not null,
  c_plan_name    VARCHAR2(50),
  c_plan_code_p  VARCHAR2(20),
  c_fun_code     VARCHAR2(50),
  c_item_code    VARCHAR2(100),
  c_item_name    VARCHAR2(200),
  c_item_code_p  VARCHAR2(100),
  n_enable       NUMBER(3) default 0,
  n_order        NUMBER(6) default 0 not null,
  n_plan_order   NUMBER(6) default 0 not null,
  c_auto_execute VARCHAR2(2000),
  c_data_idf     VARCHAR2(10) default 'Z',
  c_desc         VARCHAR2(200),
  n_check_state  NUMBER(3),
  c_update_by    VARCHAR2(20),
  c_update_time  VARCHAR2(20),
  c_check_by     VARCHAR2(20),
  c_check_time   VARCHAR2(20),
  n_shared       NUMBER(3) default 0 not null,
  c_hday_code    VARCHAR2(20),
  n_days_offset  NUMBER(3) default 0,
  c_apply_port   VARCHAR2(10) default 'select'
);
create unique index IDX_S_WF_DISPATCH0 on T_S_WF_DISPATCH (C_PLAN_CODE, C_FUN_CODE, C_ITEM_CODE);
alter table T_S_WF_DISPATCH  add constraint PK_S_WF_DISPATCH primary key (C_IDEN);

create table T_S_WF_NAVIGATION
(
  c_iden         VARCHAR2(30) not null,
  c_user_code    VARCHAR2(20) default ' ' not null,
  c_nav_text     VARCHAR2(200) default ' ' not null,
  c_plan_code    VARCHAR2(20) not null,
  c_process_code VARCHAR2(30),
  c_nav_data     CLOB
);
alter table T_S_WF_NAVIGATION  add constraint PK_S_WF_NAVIGATION primary key (C_PLAN_CODE);

create table T_S_WF_CUST_LISTEN
(
  c_name               VARCHAR2(100),
  c_listener_class     VARCHAR2(200),
  c_listener_interface VARCHAR2(200),
  c_bundle_id          VARCHAR2(20),
  c_iden               VARCHAR2(20) not null
);
alter table T_S_WF_CUST_LISTEN  add constraint PK_S_WF_CUST_LISTEN primary key (C_IDEN);

create table T_S_WF_CUST_DELEGATE
(
  c_name           VARCHAR2(100),
  c_delegate_class VARCHAR2(200),
  c_bundle_id      VARCHAR2(20),
  c_iden           VARCHAR2(20) not null
);
alter table T_S_WF_CUST_DELEGATE  add constraint PK_S_WF_CUST_DELEGATE primary key (C_IDEN);

create table T_T_TASK_LOCKS
(
  c_plan_code VARCHAR2(200) not null,
  c_lock_type VARCHAR2(100) not null,
  d_task_date DATE not null,
  n_version   NUMBER default 0 not null,
  c_node_id   VARCHAR2(100) not null
);
alter table T_T_TASK_LOCKS  add constraint PK_T_TASK_LOCKS primary key (C_PLAN_CODE, C_LOCK_TYPE, D_TASK_DATE);

create table T_T_ITEM
(
  c_item_id           VARCHAR2(20) default ' ' not null,
  c_sys_id            VARCHAR2(100) default ' ' not null,
  c_item_code         VARCHAR2(20) default ' ' not null,
  c_item_name         VARCHAR2(100) default ' ' not null,
  c_desc              VARCHAR2(500) default ' ' not null,
  c_class             VARCHAR2(200) default ' ' not null,
  c_params            VARCHAR2(2000),
  c_fun_code          VARCHAR2(20) default ' ' not null,
  c_product_dimension VARCHAR2(100),
  c_data_dimension    VARCHAR2(100) default 1,
  c_is_osgi           VARCHAR2(20),
  c_is_workflow       VARCHAR2(10),
  c_service_id        VARCHAR2(200) default ' '
);
alter table T_T_ITEM  add constraint PK_T_ITEM primary key (C_ITEM_ID);

create table T_T_PARAM_META
(
  c_param_id VARCHAR2(20) default ' ' not null,
  c_item_id  VARCHAR2(20) default ' ' not null,
  c_key      VARCHAR2(100) default ' ' not null,
  c_code     VARCHAR2(100) default ' ' not null,
  c_type     VARCHAR2(100) default ' ' not null,
  c_name     VARCHAR2(100) default ' ' not null
);
alter table T_T_PARAM_META  add constraint PK_T_PARAM_META primary key (C_PARAM_ID);

create table T_S_WF_SYSFUN
(
  c_fun_code       VARCHAR2(50),
  c_operation_name VARCHAR2(100),
  c_operation_id   VARCHAR2(50),
  c_bundle_id      VARCHAR2(50),
  c_iden           NUMBER not null
);
alter table T_S_WF_SYSFUN  add constraint PK_S_WF_SYSFUN primary key (C_IDEN);

create table T_S_WF_SYSFUN_BTN
(
  c_button_id VARCHAR2(50),
  c_name      VARCHAR2(100),
  c_status    VARCHAR2(5),
  c_operation VARCHAR2(50),
  c_fun_code  VARCHAR2(50),
  c_iden      NUMBER not null
);
alter table T_S_WF_SYSFUN_BTN  add constraint PK_S_WF_SYSFUN_BTN primary key (C_IDEN);

create table T_S_WF_SYSFUN_VAR
(
  c_name     VARCHAR2(50),
  c_type     VARCHAR2(50),
  c_value    VARCHAR2(100),
  c_fun_code VARCHAR2(50),
  c_mapping  VARCHAR2(200),
  c_iden     NUMBER not null
);
alter table T_S_WF_SYSFUN_VAR  add constraint PK_S_WF_SYSFUN_VAR primary key (C_IDEN);


create table T_S_WF_PROCESS_INFO
(
  c_process_code       VARCHAR2(30),
  c_process_name       VARCHAR2(30),
  c_process_file       CLOB,
  c_process_group_code VARCHAR2(30),
  c_start_type         VARCHAR2(20),
  c_holiday_group      VARCHAR2(20),
  c_start_param        VARCHAR2(20),
  c_desc               VARCHAR2(200),
  n_deploy_state       NUMBER(3),
  n_check_state        NUMBER(3),
  c_update_by          VARCHAR2(20),
  c_update_time        VARCHAR2(20),
  c_check_by           VARCHAR2(20),
  c_check_time         VARCHAR2(20),
  c_iden               VARCHAR2(20) not null,
  c_bus_oper           VARCHAR2(200),
  c_proc_inst_type     VARCHAR2(20) default 'normal',
  c_releate_cycle_busi VARCHAR2(100),
  c_process_type       VARCHAR2(50) default 'normalProcess'
);
create unique index IDX_T_S_WF_PROCESS_INFO on T_S_WF_PROCESS_INFO (C_PROCESS_CODE);
alter table T_S_WF_PROCESS_INFO  add constraint PK_T_S_WF_PROCESS_INFO primary key (C_IDEN);


create sequence SEQU_S_WF_CUST_LISTEN
minvalue 1
maxvalue 9999999999999999999999999999
start with 2235
increment by 1
cache 10;

create sequence SEQU_S_WF_NAVIGATION
minvalue 1
maxvalue 9999999999999999999999999999
start with 271
increment by 1
cache 10;

create sequence SEQU_T_PARAM_META
minvalue 1
maxvalue 999999999999999999999999
start with 175697
increment by 1
cache 20;

create sequence SEQU_S_WF_SYSFUN_BTN
minvalue 1
maxvalue 9999999999999999999999999999
start with 1859
increment by 1
cache 10;

create sequence SEQU_S_WF_SYSFUN
minvalue 1
maxvalue 9999999999999999999999999999
start with 1704
increment by 1
cache 10;