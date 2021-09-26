--Fastƽ̨
--Type
create or replace synonym v45axfund1.VARTABLETYPE for V45AXFUND_DEV.VARTABLETYPE;

--Sequence
create or replace synonym v45axfund1.SEQU_S_LIC_BASEINFO for V45AXFUND_DEV.SEQU_S_LIC_BASEINFO;
create or replace synonym v45axfund1.SEQU_S_DATA_META for V45AXFUND_DEV.SEQU_S_DATA_META;
create or replace synonym v45axfund1.SEQU_S_OPER_LOG for V45AXFUND_DEV.SEQU_S_OPER_LOG;
create or replace synonym v45axfund1.sequ_S_USER_CONFIG for V45AXFUND_DEV.sequ_S_USER_CONFIG;

create or replace synonym v45axfund1.SEQU_S_RESOURCE for V45AXFUND_DEV.SEQU_S_RESOURCE;
create or replace synonym v45axfund1.SEQU_S_RESOURCE_LOG for V45AXFUND_DEV.SEQU_S_RESOURCE_LOG;

create or replace synonym v45axfund1.SEQU_S_DB_UP_STATE for V45AXFUND_DEV.SEQU_S_DB_UP_STATE;
create or replace synonym v45axfund1.sequ_s_db_up_his for V45AXFUND_DEV.sequ_s_db_up_his;
create or replace synonym v45axfund1.sequ_s_fun for V45AXFUND_DEV.sequ_s_fun;
create or replace synonym v45axfund1.sequ_s_fun_rights for V45AXFUND_DEV.sequ_s_fun_rights;

--Sequence
create or replace synonym SEQU_S_SENDED_MAIL for V45AXFUND_DEV.SEQU_S_SENDED_MAIL;
create or replace synonym SEQU_S_DEPLOY_CONFIG for V45AXFUND_DEV.SEQU_S_DEPLOY_CONFIG;
create or replace synonym SEQU_S_RESOURCE_CACHE for V45AXFUND_DEV.SEQU_S_RESOURCE_CACHE;

--Table
create or replace synonym v45axfund1.t_s_user_post for V45AXFUND_DEV.t_s_user_post;
create or replace synonym v45axfund1.t_s_replace_post for V45AXFUND_DEV.t_s_replace_post;
create or replace synonym v45axfund1.t_s_user for V45AXFUND_DEV.t_s_user;
create or replace synonym v45axfund1.T_S_DATA_META for V45AXFUND_DEV.T_S_DATA_META;
create or replace synonym v45axfund1.T_S_POST for V45AXFUND_DEV.T_S_POST;
create or replace synonym v45axfund1.T_S_POST_RIGHTS for V45AXFUND_DEV.T_S_POST_RIGHTS;
create or replace synonym v45axfund1.t_s_fax_server_base for V45AXFUND_DEV.t_s_fax_server_base;
create or replace synonym v45axfund1.t_s_fax_server_ext for V45AXFUND_DEV.t_s_fax_server_ext;
create or replace synonym v45axfund1.T_S_SENDED_FAX for V45AXFUND_DEV.T_S_SENDED_FAX;
create or replace synonym v45axfund1.T_S_IE_ITEM for V45AXFUND_DEV.T_S_IE_ITEM;
create or replace synonym v45axfund1.T_S_NOTICE for V45AXFUND_DEV.T_S_NOTICE;
create or replace synonym v45axfund1.T_S_NOTICE_DETAIL for V45AXFUND_DEV.T_S_NOTICE_DETAIL;
create or replace synonym v45axfund1.t_s_pwd_hist for V45AXFUND_DEV.t_s_pwd_hist;
create or replace synonym v45axfund1.T_S_USER_POST_EDIT for V45AXFUND_DEV.T_S_USER_POST_EDIT;
create or replace synonym v45axfund1.T_S_REPLACE_POST_DETAIL for V45AXFUND_DEV.T_S_REPLACE_POST_DETAIL;
create or replace synonym v45axfund1.T_S_REPLACE_POST for V45AXFUND_DEV.T_S_REPLACE_POST;
create or replace synonym v45axfund1.t_s_oper_value for V45AXFUND_DEV.t_s_oper_value;
create or replace synonym v45axfund1.T_S_CORP_ORG for V45AXFUND_DEV.T_S_CORP_ORG;


create or replace synonym v45axfund1.T_S_FUN for V45AXFUND_DEV.T_S_FUN;--Add,Update,Delete
create or replace synonym v45axfund1.T_S_FUN_RIGHTS for V45AXFUND_DEV.T_S_FUN_RIGHTS;--Add,Update,Delete
create or replace synonym v45axfund1.T_S_LIC_BASEINFO for V45AXFUND_DEV.T_S_LIC_BASEINFO;--Add,Update,Delete
create or replace synonym v45axfund1.T_S_USER_CONFIG for V45AXFUND_DEV.T_S_USER_CONFIG;--Add,Update,Delete
create or replace synonym v45axfund1.T_S_OPER_LOG for V45AXFUND_DEV.T_S_OPER_LOG;--Add,Update,Delete
create or replace synonym v45axfund1.T_S_RESOURCE for V45AXFUND_DEV.T_S_RESOURCE;--Add,Update,Delete
create or replace synonym v45axfund1.T_S_DV_VOC for V45AXFUND_DEV.T_S_DV_VOC;--Add,Update,Delete

create or replace synonym v45axfund1.T_S_RESOURCE_LOG for V45AXFUND_DEV.T_S_RESOURCE_LOG;--Add,Update,Delete

create or replace synonym v45axfund1.T_S_DB_UP_STATE for V45AXFUND_DEV.T_S_DB_UP_STATE;
create or replace synonym v45axfund1.T_S_DB_UP_HIS for V45AXFUND_DEV.T_S_DB_UP_HIS;

--Table
create or replace synonym T_S_DICT for V45AXFUND_DEV.T_S_DICT;
create or replace synonym T_S_DEPLOY_CONFIG for V45AXFUND_DEV.T_S_DEPLOY_CONFIG;
create or replace synonym T_S_RESOURCE_CACHE for V45AXFUND_DEV.T_S_RESOURCE_CACHE;
create or replace synonym t_s_replace_post_data for V45AXFUND_DEV.t_s_replace_post_data;
create or replace synonym t_v_imp_group_rule_rela for V45AXFUND_DEV.t_v_imp_group_rule_rela;
create or replace synonym t_v_imp_group_rule for V45AXFUND_DEV.t_v_imp_group_rule;
create or replace synonym T_R_TREP_DEF_BASIC for V45AXFUND_DEV.T_R_TREP_DEF_BASIC;
create or replace synonym T_P_BI_FUND_ACC for V45AXFUND_DEV.T_P_BI_FUND_ACC;
create or replace synonym t_s_email_account for V45AXFUND_DEV.t_s_email_account;
create or replace synonym T_S_MAIL_SERVER for V45AXFUND_DEV.T_S_MAIL_SERVER;
create or replace synonym T_S_SENDED_MAIL for V45AXFUND_DEV.T_S_SENDED_MAIL;

---View
create or replace synonym v45axfund1.v_s_fun for V45AXFUND_DEV.v_s_fun;
create or replace synonym v45axfund1.v_s_dv_voc for V45AXFUND_DEV.v_s_dv_voc;
create or replace synonym v45axfund1.V_S_DA_SEC_VAR for V45AXFUND_DEV.V_S_DA_SEC_VAR;
create or replace synonym v45axfund1.V_S_DAT_ASS_TYPE for V45AXFUND_DEV.V_S_DAT_ASS_TYPE;
create or replace synonym v45axfund1.V_S_DT_TD_MODE for V45AXFUND_DEV.V_S_DT_TD_MODE;
create or replace synonym v45axfund1.V_S_DVA_ITEM for V45AXFUND_DEV.V_S_DVA_ITEM;


create or replace synonym v45axfund1.T_S_POST_RIGHTS for V45AXFUND_DEV.T_S_POST_RIGHTS;
create or replace synonym v45axfund1.T_S_FUN_META for V45AXFUND_DEV.T_S_FUN_META;
create or replace synonym v45axfund1.SEQU_S_POST_RIGHTS for V45AXFUND_DEV.SEQU_S_POST_RIGHTS;
create or replace synonym v45axfund1.SEQU_S_FUN_META for V45AXFUND_DEV.SEQU_S_FUN_META;
create or replace synonym v45axfund1.F_CONCAT_ARRAY for V45AXFUND_DEV.F_CONCAT_ARRAY;