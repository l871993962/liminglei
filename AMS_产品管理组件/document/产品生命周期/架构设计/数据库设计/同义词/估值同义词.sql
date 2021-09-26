--Table
create or replace synonym v45axfund1.T_P_BI_AREA for V45AXFUND_DEV.T_P_BI_AREA;
create or replace synonym v45axfund1.T_P_BI_CURY_PAIR for V45AXFUND_DEV.T_P_BI_CURY_PAIR;
create or replace synonym v45axfund1.T_P_BI_HDAY for V45AXFUND_DEV.T_P_BI_HDAY;
create or replace synonym v45axfund1.T_P_BI_HDAY_SUB for V45AXFUND_DEV.T_P_BI_HDAY_SUB;
create or replace synonym v45axfund1.T_P_BI_MKT for V45AXFUND_DEV.T_P_BI_MKT;
create or replace synonym v45axfund1.T_S_DC_ADDRESS for V45AXFUND_DEV.T_S_DC_ADDRESS;
create or replace synonym v45axfund1.T_S_DC_CURY for V45AXFUND_DEV.T_S_DC_CURY;
create or replace synonym v45axfund1.T_S_MKT_VAR for V45AXFUND_DEV.T_S_MKT_VAR;
create or replace synonym v45axfund1.T_S_DAC_TYPE for V45AXFUND_DEV.T_S_DAC_TYPE;
create or replace synonym v45axfund1.T_S_IE_ITEM for V45AXFUND_DEV.T_S_IE_ITEM;
create or replace synonym v45axfund1.T_P_BI_IE for V45AXFUND_DEV.T_P_BI_IE;
create or replace synonym v45axfund1.T_S_DAI_ITEM for V45AXFUND_DEV.T_S_DAI_ITEM;
create or replace synonym v45axfund1.T_P_BI_IE_RELA for V45AXFUND_DEV.T_P_BI_IE_RELA;

--Function
create or replace synonym v45axfund1.ISCONTAIN for V45AXFUND_DEV.ISCONTAIN;



--Table
create or replace synonym T_S_AUTH_ORG for v45axfund.T_S_AUTH_ORG; 
create or replace synonym T_S_USER_POST_DATA for v45axfund.T_S_USER_POST_DATA; 
create or replace synonym T_S_DEPLOY_GROUP for v45axfund.T_S_DEPLOY_GROUP;   
create or replace synonym t_v_imp_group_file for v45axfund.t_v_imp_group_file;
create or replace synonym T_V_IMP_GROUP for v45axfund.T_V_IMP_GROUP; 
create or replace synonym T_V_IMP_DEPLOY for v45axfund.T_V_IMP_DEPLOY;
create or replace synonym T_V_IMP_GROUP_PORT for v45axfund.T_V_IMP_GROUP_PORT;
create or replace synonym T_V_IMP_GROUP_PATH for v45axfund.T_V_IMP_GROUP_PATH;
create or replace synonym T_S_INF_VAR for v45axfund.T_S_INF_VAR;

--Sequence
create or replace synonym SEQU_S_DEPLOY_GROUP for v45axfund.SEQU_S_DEPLOY_GROUP; 
create or replace synonym SEQU_V_IMP_GROUP for v45axfund.SEQU_V_IMP_GROUP;
create or replace synonym SEQU_V_IMP_DEPLOY for v45axfund.SEQU_V_IMP_DEPLOY;
create or replace synonym SEQU_S_DEPLOY_ITEM for v45axfund.SEQU_S_DEPLOY_ITEM;
create or replace synonym T_S_DEPLOY_ITEM for v45axfund.T_S_DEPLOY_ITEM;
create or replace synonym SEQU_S_USER_POST_DATA for v45axfund.SEQU_S_USER_POST_DATA; 

---View
create or replace synonym V_V_IMP_DEPLOY for v45axfund.V_V_IMP_DEPLOY; 