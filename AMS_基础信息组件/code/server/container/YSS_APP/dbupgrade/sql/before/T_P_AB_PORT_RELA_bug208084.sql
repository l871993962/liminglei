delete from T_P_AB_PORT_RELA
    where rowid in (select rowid
                      from (select rowid as rid,
                                   row_number() over(partition by C_PORT_CODE,C_RELA_TYPE,C_RELA_CODE,C_DV_TYPE_CODE,C_MKT_CODE order by C_IDEN) as seq
                              from T_P_AB_PORT_RELA)
                     where seq > 1);