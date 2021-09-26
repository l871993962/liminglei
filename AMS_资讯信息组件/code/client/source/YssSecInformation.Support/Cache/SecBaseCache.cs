using System;
using System.Collections.Generic;
using System.Text;
using System.Data;
using FAST.Core.Cache;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Communication.DataService;
using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using YssSecInformation.Support.Pojo.Sec;
using YssSecInformation.Support.Sv.Service;
using System.Threading;
using FAST.Core.Communication.Service;
using YssSecInformation.Support.Sv.Pojo;

/*
 * 开发人员 开发日期    开发描述
 * 张绍林   2015-05-23  数据更新依据，由之前的C_CODE改为按照数据ID来更新。
 * 张绍林   2015-06-15  本地数据服务公用化改造，多客户端使用同一个服务进程。
 */

namespace YssSecInformation.Support.Cache
{
    /// <summary>
    /// 证券代码类前台缓存对象。
    /// </summary>
    public class SecBaseCache : BaseCache
    {
        /// <summary>
        /// 记录更新站点，用于过滤重复的更新站点（相同站点不要重复更新，更新前校验是否存在站点，更新后移除站点）
        /// </summary>
        private static List<string> _ServiceUpdateSites = new List<string>();

        /// <summary>
        /// 每页数量
        /// </summary>
        private int pageSize = 2000;

        /// <summary>
        /// 构造
        /// </summary>
        /// <param name="pcServiceSite">服务器站点</param>
        /// <param name="pcStartupPath">程序所在目录</param>
        public SecBaseCache(string pcServiceSite, string pcStartupPath)
            : base(pcServiceSite, pcStartupPath)
        {
            ////注意：修改数据表结构(增加、删除、修改字段)之后，一定要更改此版本配置。
            this.DataFileVersion = "2021-07-13 20:32";
            this.CheckLocalDataFile();
        }

        /// <summary>
        /// 获取客户端数据缓存类型
        /// </summary>
        protected override CacheGroup CacheGroup
        {
            get { return CacheGroup.SECBASE; }
        }

        /// <summary>
        /// 获取待操作的表。
        /// </summary>
        protected override string TableName
        {
            get { return "SecBase"; }
        }

        /// <summary>
        /// 获取插入数据SQL模版。
        /// </summary>
        protected override string InsertSqlFormat
        {
            get
            {
                return "\r\n insert into SecBase (C_IDEN, C_SEC_CODE, C_SEC_NAME, C_SEC_NAME_PY, C_SEC_MKT_CODE, C_MKT_CODE,"
               + " C_SEC_VAR_CODE, C_DC_CODE, C_SEC_CODE_TRG, C_DV_VAR_DUR, N_PRICE_ISSUE,N_FV_ISSUE,D_OFF_LIST,C_DV_PI_MOD,C_DV_RTA,C_SEC_ISIN_CODE,C_ORG_CODE,N_CHECK_STATE,C_PORT_CODE)"
               + " values('{0}','{1}','{2}','{3}','{4}','{5}','{6}','{7}','{8}','{9}','{10}','{11}','{12}','{13}','{14}','{15}','{16}','{17}','{18}') \r\n";
            }
        }

        /// <summary>
        /// 获取Pojo的Code值。
        /// </summary>
        protected override string Code
        {
            get { return "C_SEC_CODE"; }
        }

        /// <summary>
        /// 获取Pojo的Key值。
        /// </summary>
        protected override string Key
        {
            get { return ""; }
        }

        /// <summary>
        /// 获取Pojo的Type值。
        /// </summary>
        protected override string Type
        {
            get { return "C_SEC_VAR_CODE"; }
        }

        /// <summary>
        /// 创建表格
        /// 注意：修改CreateTable方法后，一定要更改【_DataFileVertion】
        /// </summary>
        protected override void CreateTable()
        {
            string lcCreateTable = "create table " + this.TableName + " ("
                + " C_IDEN text primary key, "
                + " C_SEC_CODE text, "
                + " C_SEC_NAME text, "
                ////证券名称简拼，用于支持下拉框快速检索。STORY #37886 张绍林-20170207
                + " C_SEC_NAME_PY text, "
                + " C_SEC_MKT_CODE text, "
                + " C_MKT_CODE text, "
                + " C_SEC_VAR_CODE text, "
                + " C_DC_CODE text, "
                + " C_SEC_CODE_TRG text, "
                + " C_DV_VAR_DUR text, "
                + " N_PRICE_ISSUE text, "
                + " N_FV_ISSUE text, "
                + " D_OFF_LIST text, "
                + " C_DV_PI_MOD text, "
                + " C_DV_RTA text ,"
                ////STORY #58979 【易方达香港】证券基本信息界面增加ISIN码搜索且其他下拉框也要增加ISIN码列 
                + " C_SEC_ISIN_CODE text ,"
                //// STORY #34104 [紧急]上报保监会报表存款流水调整
                + " C_ORG_CODE text ,"
                + " N_CHECK_STATE text, "
				+ " C_PORT_CODE text );";


            this.SQLiteOperator.CreateTable(lcCreateTable);
        }

        /// <summary>
        /// 将从Access查询的Table转换为Pojo，默认采集第一条数据。
        /// </summary>
        /// <param name="poTable">待转换的Table数据源</param>
        /// <returns>返回Pojo</returns>
        protected override BasePojo DataTableToPojo(System.Data.DataTable poTable)
        {
            SecBase loBasePojo = null;
            if (poTable != null && poTable.Rows.Count > 0)
            {
                loBasePojo = new SecBase();

                DataRow loTempRow = poTable.Rows[0];
                loBasePojo.Id = loTempRow["C_IDEN"].ToString();
                loBasePojo.C_SEC_CODE = loTempRow["C_SEC_CODE"].ToString();
                loBasePojo.C_SEC_NAME = loTempRow["C_SEC_NAME"].ToString();
                ////证券名称简拼，用于支持下拉框快速检索。STORY #37886 张绍林-20170207
                loBasePojo.C_SEC_NAME_PY = loTempRow["C_SEC_NAME_PY"].ToString();
                loBasePojo.C_SEC_MKT_CODE = loTempRow["C_SEC_MKT_CODE"].ToString();
                loBasePojo.C_MKT_CODE = loTempRow["C_MKT_CODE"].ToString();
                loBasePojo.C_SEC_VAR_CODE = loTempRow["C_SEC_VAR_CODE"].ToString();
                loBasePojo.C_DC_CODE = loTempRow["C_DC_CODE"].ToString();
                loBasePojo.C_SEC_CODE_TRG = loTempRow["C_SEC_CODE_TRG"].ToString();
                loBasePojo.C_DV_VAR_DUR = loTempRow["C_DV_VAR_DUR"].ToString();
                loBasePojo.N_PRICE_ISSUE = loTempRow["N_PRICE_ISSUE"].ToString();
                loBasePojo.N_FV_ISSUE = loTempRow["N_FV_ISSUE"].ToString();
                loBasePojo.D_OFF_LIST = DateTime.Parse(loTempRow["D_OFF_LIST"].ToString());
                loBasePojo.C_DV_PI_MOD = loTempRow["C_DV_PI_MOD"].ToString();
                loBasePojo.C_DV_RTA = loTempRow["C_DV_RTA"].ToString();      ////增加证券利率类型字段;
                loBasePojo.C_SEC_ISIN_CODE = loTempRow["C_SEC_ISIN_CODE"].ToString();    ////增加ISIN码字段
                //// STORY #34104 [紧急]上报保监会报表存款流水调整
                //// 【资金存放业务】新增字段<机构名称> edit by yuanyafeng 2017-8-15
                loBasePojo.C_ORG_CODE = loTempRow["C_ORG_CODE"].ToString();
                loBasePojo.AuditState = Convert.ToInt32(loTempRow["N_CHECK_STATE"]);
				loBasePojo.C_PORT_CODE = loTempRow["C_PORT_CODE"].ToString();////增加组合代码字段;
            }

            return loBasePojo;
        }

        /// <summary>
        /// 获取所有的Pojo对象集。
        /// </summary>
        /// <returns>返回Pojo对象集</returns>
        public List<BasePojo> getAllDataList()
        {
            string lcSql = "select * from " + this.TableName;

            DataTable loTempTable = SQLiteOperator.GetData(lcSql);

            return this.DataTableToPojos(loTempTable);
        }

        /// <summary>
        /// 将从Access查询的Table转换为Pojo列表。
        /// </summary>
        /// <param name="poTable">待转换的Table数据源</param>
        /// <returns>返回Pojo集</returns>
        protected override List<BasePojo> DataTableToPojos(System.Data.DataTable poTable)
        {
            List<BasePojo> loBasePojos = new List<BasePojo>();
            ////改用Dictionary，检索更快。张绍林-20151027
            Dictionary<string, string> loCodeList = new Dictionary<string, string>();
            if (poTable != null && poTable.Rows.Count > 0)
            {
                string lcSecCode = "";
                foreach (DataRow loTempRow in poTable.Rows)
                {
                    lcSecCode = loTempRow["C_SEC_CODE"].ToString();
                    if (loCodeList.ContainsKey(lcSecCode))
                    {
                        ////由于在删除并重新录入数据时，客户端缓存数据无法做到同步（数据量大，严重影响性能），所以存在客户端数据重复。
                        ////所以，需要在这里剔除掉重复的数据。张绍林-20150929
                        continue;
                    }

                    SecBase loBasePojo = new SecBase();
                    loBasePojo.Id = loTempRow["C_IDEN"].ToString();
                    loBasePojo.C_SEC_CODE = lcSecCode;
                    loBasePojo.C_SEC_NAME = loTempRow["C_SEC_NAME"].ToString();
                    ////证券名称简拼，用于支持下拉框快速检索。STORY #37886 张绍林-20170207
                    loBasePojo.C_SEC_NAME_PY = loTempRow["C_SEC_NAME_PY"].ToString();
                    loBasePojo.C_SEC_MKT_CODE = loTempRow["C_SEC_MKT_CODE"].ToString();
                    loBasePojo.C_MKT_CODE = loTempRow["C_MKT_CODE"].ToString();
                    loBasePojo.C_SEC_VAR_CODE = loTempRow["C_SEC_VAR_CODE"].ToString();
                    loBasePojo.C_DC_CODE = loTempRow["C_DC_CODE"].ToString();
                    loBasePojo.C_SEC_CODE_TRG = loTempRow["C_SEC_CODE_TRG"].ToString();
                    loBasePojo.C_DV_VAR_DUR = loTempRow["C_DV_VAR_DUR"].ToString();
                    loBasePojo.N_PRICE_ISSUE = loTempRow["N_PRICE_ISSUE"].ToString();
                    loBasePojo.N_FV_ISSUE = loTempRow["N_FV_ISSUE"].ToString();
                    loBasePojo.D_OFF_LIST = DateTime.Parse(loTempRow["D_OFF_LIST"].ToString());
                    loBasePojo.C_DV_PI_MOD = loTempRow["C_DV_PI_MOD"].ToString();
                    loBasePojo.C_DV_RTA = loTempRow["C_DV_RTA"].ToString();                   ////增加证券利率类型字段
                    loBasePojo.C_SEC_ISIN_CODE = loTempRow["C_SEC_ISIN_CODE"].ToString();     ////增加ISIN码字段
                    //// STORY #34104 [紧急]上报保监会报表存款流水调整
                    //// 【资金存放业务】新增字段<机构名称> edit by yuanyafeng 2017-8-15
                    loBasePojo.C_ORG_CODE = loTempRow["C_ORG_CODE"].ToString();
                    loBasePojo.AuditState = Convert.ToInt32(loTempRow["N_CHECK_STATE"]);
					loBasePojo.C_PORT_CODE = loTempRow["C_PORT_CODE"].ToString();////增加组合代码字段;
                    loBasePojos.Add(loBasePojo);
                    loCodeList.Add(lcSecCode, null);
                }
            }

            return loBasePojos;
        }

        /// <summary>
        /// 将Pojo对象转换为Sql插入语句。注意转义符
        /// </summary>
        /// <param name="poBasePojo">待转换的Pojo</param>
        /// <returns>返回Sql语句</returns>
        protected override string PojoToInsertSql(BasePojo poBasePojo)
        {
            string lcSql = "";

            if (poBasePojo != null)
            {
                SecBase loTempPojo = poBasePojo as SecBase;

                lcSql = string.Format(this.InsertSqlFormat, loTempPojo.Id == null ? "" : loTempPojo.Id.Replace("'", "''"), loTempPojo.C_SEC_CODE == null ? "" : loTempPojo.C_SEC_CODE.Replace("'", "''"), loTempPojo.C_SEC_NAME == null ? "" : loTempPojo.C_SEC_NAME.Replace("'", "''").Replace("\0", ""), "", loTempPojo.C_SEC_MKT_CODE == null ? "" : loTempPojo.C_SEC_MKT_CODE.Replace("'", "''"), loTempPojo.C_MKT_CODE == null ? "" : loTempPojo.C_MKT_CODE.Replace("'", "''"), loTempPojo.C_SEC_VAR_CODE == null ? "" : loTempPojo.C_SEC_VAR_CODE.Replace("'", "''"), loTempPojo.C_DC_CODE == null ? "" : loTempPojo.C_DC_CODE.Replace("'", "''"), loTempPojo.C_SEC_CODE_TRG == null ? "" : loTempPojo.C_SEC_CODE_TRG.Replace("'", "''"), loTempPojo.C_DV_VAR_DUR == null ? "" : loTempPojo.C_DV_VAR_DUR.Replace("'", "''"), loTempPojo.N_PRICE_ISSUE == null ? "" : loTempPojo.N_PRICE_ISSUE.Replace("'", "''"), loTempPojo.N_FV_ISSUE == null ? "" : loTempPojo.N_FV_ISSUE.Replace("'", "''"), loTempPojo.D_OFF_LIST == null ? "" : loTempPojo.D_OFF_LIST.ToString("yyyy-MM-dd").Replace("'", "''"), loTempPojo.C_DV_PI_MOD == null ? "" : loTempPojo.C_DV_PI_MOD.Replace("'", "''"), loTempPojo.C_DV_RTA == null ? "" : loTempPojo.C_DV_RTA.Replace("'", "''"), loTempPojo.C_SEC_ISIN_CODE == null ? "" : loTempPojo.C_SEC_ISIN_CODE.Replace("'", "''"), loTempPojo.C_ORG_CODE == null ? "" : loTempPojo.C_ORG_CODE.Replace("'", "''"), loTempPojo.AuditState, loTempPojo.C_PORT_CODE == null ? "" : loTempPojo.C_PORT_CODE.Replace("'", "''"));
                //// STORY #34104 [紧急]上报保监会报表存款流水调整
                //// 【资金存放业务】新增字段<机构名称> edit by yuanyafeng 2017-8-15
            }

            return lcSql;
        }

        /// <summary>
        /// 将Pojo对象转换为Sql删除语句。
        /// BUG #184582 嘉实基金-流水，行情页面选择交易证券时卡顿（更新证券缓存慢）。张绍林-20171222
        /// </summary>
        /// <param name="poBasePojo">待转换的Pojo</param>
        /// <returns>返回Sql语句</returns>
        protected override string PojoToDeleteSql(BasePojo poBasePojo)
        {
            SecBase loPojo = poBasePojo as SecBase;
            ////string lcSql = " delete from " + this.TableName + " where C_IDEN = '" + loPojo.Id + "'"; by weijj 20160421 根据业务主键删除 防止删除重新新增后，前台缓存没有更新证券品种
            ////edit by shijian 2018-11-06 BUG #222669 嘉实基金-估值系统经常出现宕机问题，平均每周一到两次
            ////增加证券代码“'”单引号的处理
            string lcSql = " delete from " + this.TableName + " where C_IDEN = '" + loPojo.Id.Replace("'", "''") + "'" + " or C_SEC_CODE = '" + loPojo.C_SEC_CODE.Replace("'", "''") + "'";
            return lcSql;
        }

        /// <summary>
        /// 删除
        /// </summary>
        /// <param name="poBasePojos">poBasePojos</param>
        /// <returns>list</returns>
        protected override List<string> PojoToDeleteSql(List<BasePojo> poBasePojos)
        {
            List<string> loSqlList = new List<string>();
            ////edit by shijian 2018-11-06 BUG #222669 嘉实基金-估值系统经常出现宕机问题，平均每周一到两次
            string lcSqlMode = " delete from " + this.TableName + " where C_SEC_CODE in ('{0}') or C_IDEN in ('{1}')";
            List<string> loCodeList = new List<string>();
            List<string> loIdenList = new List<string>();
            int i = 0;

            foreach (SecBase loPojo in poBasePojos)
            {
                ////edit by shijian 2018-11-06 BUG #222669 嘉实基金-估值系统经常出现宕机问题，平均每周一到两次
                ////增加证券代码集，并对证券代码进行“'”单引号处理
                loCodeList.Add(loPojo.C_SEC_CODE.Replace("'", "''"));
                loIdenList.Add(loPojo.Id.Replace("'", "''"));
                i++;
                ////1000条一个批次，语句太长，SQLite执行效率低，易出错
                if (i == 1000)
                {
                    ////edit by shijian 2018-11-06 BUG #222669 嘉实基金-估值系统经常出现宕机问题，平均每周一到两次
                    string lcTempSql = string.Format(lcSqlMode, string.Join("','", loCodeList.ToArray()), string.Join("','", loIdenList.ToArray()));
                    loSqlList.Add(lcTempSql);
                    loCodeList.Clear();
                    loIdenList.Clear();
                }
            }

            ////edit by shijian 2018-11-06 BUG #222669 嘉实基金-估值系统经常出现宕机问题，平均每周一到两次
            if (loCodeList.Count > 0 || loIdenList.Count > 0)
            {
                ////最后尾部数据，补插一次
                string lcTempSql = string.Format(lcSqlMode, string.Join("','", loCodeList.ToArray()), string.Join("','", loIdenList.ToArray()));
                loSqlList.Add(lcTempSql);
            }

            return loSqlList;
        }

        #region 客户端缓存数据同步。STORY #58109 客户端缓存逻辑优化，代码结构梳理，完善开发文档。张绍林-20180907

        /// <summary>
        /// 从服务器更新数据-按日期时间戳更新，没有日期传递空串。
        /// </summary>
        /// <param name="plUpdateAll">是否强制刷新所有数据</param>
        /// <param name="pcDateTime">日期时间戳</param>
        protected override void UpdateFromServer(bool plUpdateAll, string pcDateTime)
        {
            ////为防止反复更新，这里在刷新数据时检测下是否有正在执行的更新行为。
            string lcKey = (this.ServiceSite + this.StartupPath).ToLower();
            if (_ServiceUpdateSites.Contains(lcKey))
            {
                ////已经有站点在更新，就不要再继续更新了。
                return;
            }

            ////插入更新键值标识
            _ServiceUpdateSites.Add(lcKey);
            try
            {
                ISecBaseInfoDataService loService = DataServiceFactory.createService(typeof(ISecBaseInfoDataService), this.ServiceSite) as ISecBaseInfoDataService;

                ////使用分页查询数据，查完全部后一次性插入mdb文件
                string countStr = loService.getUpdateByTimestampCount(pcDateTime);
                int count = Convert.ToInt32(countStr);
                if (count > 0)
                {
                    ////BUG #183925 【广发基金】【保险资产】【缓存】缓存在筛选证券时经常报错。张绍林-20171219
                    ////改用线程池，分批获取每页的数据，频率1秒，4并发。
                    ////ThreadPoolConcurrent threadPool = new ThreadPoolConcurrent();
                    ////threadPool.ThreadStoped += new EventHandler(this.threadPool_ThreadStoped);
                    ////threadPool.ThreadTick = 1000;
                    ////threadPool.MaxRunningCount = 1;

                    int pageCount = (count / pageSize) + 1;
                    for (int i = 1; i <= pageCount; i++)
                    {
                        PageInation page = new PageInation();
                        page.PageCount = pageCount;
                        page.PageSize = pageSize;
                        page.TotalNum = count;
                        page.CurrPage = i;
                        this.InsertToDataBase(loService, pcDateTime, page, plUpdateAll);
                        ////ThreadStart threadStart = delegate() { this.InsertToDataBase(loService, pcDateTime, page, plUpdateAll); };
                        ////threadPool.AddThread(threadStart);
                    }

                }
                else
                {
                    ////移除更新键值标识
                    ////_ServiceUpdateSites.Remove(lcKey);
                    this.time = DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss");
                }
            }
            finally
            {
                _ServiceUpdateSites.Remove(lcKey);
            }
        }

        /// <summary>
        /// 缓存更新线程池完成事件。
        /// </summary>
        /// <param name="sender">线程池</param>
        /// <param name="e">事件参数</param>
        private void threadPool_ThreadStoped(object sender, EventArgs e)
        {
            ////移除更新键值标识
            string lcKey = (this.ServiceSite + this.StartupPath).ToLower();
            _ServiceUpdateSites.Remove(lcKey);
        }

        /// <summary>
        /// 将数据集插入到本地缓存数据库。
        /// BUG #183925 【广发基金】【保险资产】【缓存】缓存在筛选证券时经常报错：The database file is locked
        /// </summary>
        /// <param name="poService">服务</param>
        /// <param name="dateTime">日期时间戳</param>
        /// <param name="page">数据分页页码</param>
        /// <param name="plUpdateAll">是否强制刷新所有数据</param>
        private void InsertToDataBase(ISecBaseInfoDataService poService, string dateTime, PageInation page, bool plUpdateAll)
        {
            try
            {
                List<BasePojo> pojoList = getFromServerByPage(poService, dateTime, page);

                List<string> sqlList = new List<string>();

                ////不是更新全部时，插入前要先执行一次删除操作
                sqlList.AddRange(this.PojoToDeleteSql(pojoList));

                sqlList.AddRange(this.PojoToInsertSql(pojoList));

                int excuteState = updateDataSqlite(sqlList);
                if (excuteState > -1 && page.PageCount == page.CurrPage)
                {
                    ////最后一页的时候，才能写入配置文件
                    this.WriteConfig();
                }

                if (excuteState != sqlList.Count)
                {
                    ClsLogger.Info("证券缓存刷新失败,页码：" + page.CurrPage);
                }
            }
            catch (Exception ex)
            {
                ClsLogger.Error(ex);
            }
        }

        /// <summary>
        /// 执行sqllite修改
        /// </summary>
        /// <param name="sqlList">sqlList</param>
        /// <returns>int</returns>
        private int updateDataSqlite(List<string> sqlList)
        {
            ////foreach (string sql in sqlList)
            ////{
            ////    ClsLogger.Info("执行证券缓存sql:" + sql);
            ////}
            return this.SQLiteOperator.UpdateData(sqlList);

        }


        /// <summary>
        /// 根据分页查询数据
        /// </summary>
        /// <param name="poService">服务</param>
        /// <param name="dateTime">日期时间戳</param>
        /// <param name="page">数据分页页码</param>
        /// <returns>返回更新到的数据集</returns>
        private List<BasePojo> getFromServerByPage(ISecBaseInfoDataService poService, string dateTime, PageInation page)
        {
            List<BasePojo> loTempPojos = new List<BasePojo>();
            CacheData loCacheData = poService.updateByTimestampPage(dateTime, page);
            this.time = loCacheData.Timestamp;
            if (loCacheData != null && loCacheData.DataList != "")
            {
                List<SecBase> loSecBasePojos = JsonUtil.toObject<List<SecBase>>(loCacheData.DataList);
                loTempPojos.AddRange(loSecBasePojos.ToArray());
            }

            return loTempPojos;
        }

        /// <summary>
        /// 根据ID获取数据
        /// </summary>
        /// <param name="ids">ids</param>
        /// <returns>a</returns>
        protected override List<BasePojo> UpdateFromServerByIds(List<string> ids)
        {
            ////BUG #167994 嘉实基金—证券信息加载时间过长，影响客户做账效率 雷建华 20170803
            List<BasePojo> loTempPojos = null;
            if (ids != null && ids.Count > 0)
            {
                ISecBaseInfoDataService loService = DataServiceFactory.createService(typeof(ISecBaseInfoDataService), this.ServiceSite) as ISecBaseInfoDataService;
                CacheData loCacheData = loService.updateByIds(string.Join(",", ids.ToArray()));
                if (loCacheData != null && loCacheData.DataList != "")
                {
                    List<SecBase> loSecBasePojos = JsonUtil.toObject<List<SecBase>>(loCacheData.DataList);

                    ////这里不能直接将【List<SecBase>】强转为【List<BasePojo>】，所以折中一下。
                    loTempPojos = new List<BasePojo>();
                    loTempPojos.AddRange(loSecBasePojos.ToArray());
                    return loTempPojos;
                }
            }

            return loTempPojos;
        }

        #endregion

        /// <summary>
        /// 前台通过缓存获取数据，根据证券品种，市场获取证券列表
        /// </summary>
        /// <param name="types">条件1：证券品种 条件2：市场代码</param>
        /// <returns>证券列表</returns>
        public List<BasePojo> getDataListByTypesAndMkt(string[] types)
        {
            ////BUG #135428 【紧急】华宝信托手工录入凭证开放式货币基金科目匹配问题  Modified by dingxukun 2016-08-02
            ////string lcSql = " select  * from " + this.TableName + " where c_sec_var_code like '" + types[0] + "%' and c_mkt_code ='" + types[1] + "'";
            ////STORY #53287 【招商证券】【紧急】持有到期产品资产数据投资分类清算优化 (#2 #1 )
            string lcSql = "";
            if (!string.IsNullOrEmpty(types[0]) && string.IsNullOrEmpty(types[1]))
            {
                lcSql = " select  * from " + this.TableName + " where c_sec_var_code like '" + types[0] + "%' ";
            }
            else if (string.IsNullOrEmpty(types[0]) && !string.IsNullOrEmpty(types[1]))
            {
                lcSql = " select  * from " + this.TableName + " where c_mkt_code ='" + types[1] + "'";
            }
            else if (string.IsNullOrEmpty(types[0]) && string.IsNullOrEmpty(types[1]))
            {
                lcSql = " select  * from " + this.TableName;
            }
            else
            {
                lcSql = " select  * from " + this.TableName + " where c_sec_var_code like '" + types[0] + "%' and c_mkt_code ='" + types[1] + "'";
            }

            //STORY #43752 下拉框大数据处理机制。添加过滤参数-张绍林-20171116
            lcSql += this.AddFilterParams(lcSql);

            DataTable loTempTable = SQLiteOperator.GetData(lcSql);
            return this.DataTableToPojos(loTempTable);
        }

        /// <summary>
        /// 前台通过缓存获取数据，根据证券品种，市场获取证券列表
        /// add by zhaijiajia 20161126 合并需求：STORY #35438 【南方基金】【紧急】社保资产企业债公司债中期票据匹配到同一个企业公司债债的科目需求
        /// </summary>
        /// <param name="types">条件1：证券品种 条件2：市场代码</param>
        /// <returns>证券列表</returns>
        public List<BasePojo> getDataListByTypesAndMktNew(string[] types)
        {
            string lcWhere = "";
            foreach (string lcType in types)
            {
                if (string.Equals(lcType, types[types.Length - 1]))
                {
                    break;
                }

                lcWhere += " c_sec_var_code like '" + lcType + "%' \r\n or";
            }

            lcWhere = lcWhere.Substring(0, lcWhere.Length - 2);

            string lcSql = " select  * from " + this.TableName + " where ( " + lcWhere + " ) and c_mkt_code ='" + types[types.Length - 1] + "'";

            ////STORY #43752 下拉框大数据处理机制。添加过滤参数-张绍林-20171116
            lcSql += this.AddFilterParams(lcSql);

            DataTable loTempTable = SQLiteOperator.GetData(lcSql);
            return this.DataTableToPojos(loTempTable);
        }

        /// <summary>
        /// 前台通过缓存获取数据，根据证券品种，市场获取证券列表。可控制是否获取全量数据
        /// BUG #228437 指数成分股信息set界面特别卡顿 suixin 20190321
        /// </summary>
        /// <param name="types">条件1：证券品种 条件2：市场代码</param>
        /// <param name="isAll">是否全量</param>
        /// <returns>证券列表</returns>
        public List<BasePojo> getDataListByTypesAndMktIsAll(string[] types, bool isAll)
        {
            ////BUG #135428 【紧急】华宝信托手工录入凭证开放式货币基金科目匹配问题  Modified by dingxukun 2016-08-02
            ////string lcSql = " select  * from " + this.TableName + " where c_sec_var_code like '" + types[0] + "%' and c_mkt_code ='" + types[1] + "'";
            ////STORY #53287 【招商证券】【紧急】持有到期产品资产数据投资分类清算优化 (#2 #1 )
            string lcSql = "";
            if (!string.IsNullOrEmpty(types[0]) && string.IsNullOrEmpty(types[1]))
            {
                lcSql = " select  * from " + this.TableName + " where c_sec_var_code like '" + types[0] + "%' ";
            }
            else if (string.IsNullOrEmpty(types[0]) && !string.IsNullOrEmpty(types[1]))
            {
                lcSql = " select  * from " + this.TableName + " where c_mkt_code ='" + types[1] + "'";
            }
            else if (string.IsNullOrEmpty(types[0]) && string.IsNullOrEmpty(types[1]))
            {
                lcSql = " select  * from " + this.TableName;
            }
            else
            {
                lcSql = " select  * from " + this.TableName + " where c_sec_var_code like '" + types[0] + "%' and c_mkt_code ='" + types[1] + "'";
            }

            if (!isAll)
            {
                ////STORY #43752 下拉框大数据处理机制。添加过滤参数-张绍林-20171116
                lcSql += this.AddFilterParams(lcSql);
            }

            DataTable loTempTable = SQLiteOperator.GetData(lcSql);
            return this.DataTableToPojos(loTempTable);
        }

        /// <summary>
        /// 根据核算元素查数据
        /// </summary>
        /// <param name="parameter">参数</param>
        /// <returns>数据列表</returns>
        public List<BasePojo> getDataListByDaes(string parameter)
        {
            ////注销无用代码，张绍林-20150616
            ////ISecBaseInfoDataService loService = DataServiceFactory.createService(typeof(ISecBaseInfoDataService), this.ServiceSite) as ISecBaseInfoDataService;
            StringBuilder buf = new StringBuilder();
            buf.Append(" select * from ");
            buf.Append(this.TableName);
            buf.Append(" a ");
            if (parameter.Trim().Length > 0)
            {
                string whereSql = "WHERE";
                string[] arrSplit = parameter.Split(';');
                foreach (string sSplit in arrSplit)
                {
                    if (sSplit != null && sSplit.Trim().Length > 0)
                    {
                        string[] arrPara = sSplit.Trim().Split('=');
                        if (arrPara.Length == 2)
                        {
                            if (arrPara[1].Contains("%"))
                            {
                                whereSql += " a.C_" + arrPara[0].Trim() + "_CODE LIKE '" + arrPara[1] + "' AND";
                            }
                            else
                            {
                                whereSql += " a.C_" + arrPara[0].Trim() + "_CODE = '" + arrPara[1] + "' AND";
                            }
                        }
                    }
                }

                whereSql = whereSql.Remove(whereSql.Length - 4);

                ////STORY #43752 下拉框大数据处理机制。添加过滤参数-张绍林-20171116
                whereSql += this.AddFilterParams(whereSql);

                buf.Append(whereSql);
            }

            DataTable loTempTable = SQLiteOperator.GetData(buf.ToString());
            return this.DataTableToPojos(loTempTable);
        }

        /// <summary>
        /// 1
        /// </summary>
        /// <param name="cSecCode">1</param>
        /// <returns>1</returns>
        public BasePojo getSecBaseInfoDataBySecCode(string cSecCode)
        {
            string lcSql = " select  * from " + this.TableName + " where " + this.Code + " = '" + cSecCode + "'";
            DataTable loTempTable = SQLiteOperator.GetData(lcSql);
            return this.DataTableToPojo(loTempTable);
        }

        /// <summary>
        /// 因为要排序 分页没有在前台实现。
        /// </summary>
        /// <param name="types">1</param>
        /// <param name="like">1</param>
        /// <param name="page">1</param>
        /// <returns>1</returns>
        public ShortDataListPackage<SecShortPojo> getShortDataList(string[] types, string like, PageInation page)
        {
            ISecBaseInfoDataService loService = DataServiceFactory.createService(typeof(ISecBaseInfoDataService), this.ServiceSite) as ISecBaseInfoDataService;
            return loService.getShortDataList(types, like, page);
        }

        /// <summary>
        /// 根据证券品种和到期日期查询证券信息
        /// </summary>
        /// <param name="types">证券品种</param>
        /// <param name="dateStr">到期日期</param>
        /// <returns>证券信息列表</returns>
        public List<BasePojo> getDataListByTypesAndDate(string[] types, string dateStr)
        {
            List<BasePojo> list = this.getDataListByTypes(types);
            DateTime dt = DateTime.Parse(dateStr);
            /////// 过滤到期的证券
            for (int i = list.Count - 1; i >= 0; i--)
            {
                SecBase sec = (SecBase)list[i];
                if (sec.D_OFF_LIST.CompareTo(dt) <= 0)
                {
                    list.Remove(list[i]);
                }
            }

            return list;
        }

        ////private string whereTypes(String[] types)
        ////{
        ////    StringBuilder buf = new StringBuilder();
        ////    foreach (string type in types)
        ////    {
        ////        if (type != null && type.trim().length() > 0)
        ////        {
        ////            buf.append(" a.C_SEC_VAR_CODE LIKE ? OR ");
        ////        }
        ////    }

        ////    if (buf.length() > 3)
        ////    {
        ////        buf.setLength(buf.length() - 3);
        ////    }

        ////    return buf.toString();
        ////}

        /////// <summary>
        /////// 排序
        /////// </summary>
        /////// <returns></returns>
        ////private string getCommonOrder()
        ////{
        ////    return " order by a.N_CHECK_STATE asc ";
        ////}

        /// <summary>
        /// 把所有的指数信息封装成证券基本信息，供下拉框选择
        /// </summary>
        /// <returns>list</returns>
        public List<BasePojo> getAllIndexDataList()
        {
            ISecBaseInfoDataService loService = DataServiceFactory.createService(typeof(ISecBaseInfoDataService), this.ServiceSite) as ISecBaseInfoDataService;
            return loService.getAllIndexDataList();
        }

        /// <summary>
        /// STORY34120【南方基金】【紧急】现金存放业务，流水加入标识，4级科目不明确到品种，而是按照标识来区分
        /// </summary>
        /// <param name="types">types</param>
        /// <returns>list</returns>
        public List<BasePojo> getDataListBySjsszq(string[] types)
        {
            ISecBaseInfoDataService loService = DataServiceFactory.createService(typeof(ISecBaseInfoDataService), this.ServiceSite) as ISecBaseInfoDataService;
            return loService.getDataListBySjsszq(types);
        }

        /// <summary>
        /// 前台通过缓存获取数据，根据证券品种，市场获取证券列表
        /// 如果证券品种为空 则加载市场之下的全部股票
        /// 如果品种不为空 则加载品种之下的股票
        /// 如果两个都为空 则加载GP，JJ，ZQ
        ///  STORY36353【YSS】【优化】投资分类设置增加银行间债券的设置
        ///  yulinfeng
        /// </summary>
        /// <param name="types">条件1：证券品种 条件2：市场代码</param>
        /// <returns>证券列表</returns>
        public List<BasePojo> getDataListByTypesAndMktEX(string[] types)
        {
            string lcSql = null;
            if (types[0] == " " && types[1] != " ")
            {
                lcSql = " select  * from " + this.TableName + " where c_mkt_code ='" + types[1] + "'";
            }

            if (types[1] == " " && types[0] != " ")
            {
                lcSql = " select  * from " + this.TableName + " where c_sec_var_code like '" + types[0] + "%'";
            }

            if (types[1] == " " && types[0] == " ")
            {
                lcSql = " select  * from " + this.TableName + " where (c_sec_var_code like 'GP%'or c_sec_var_code like 'ZQ%' or c_sec_var_code like 'JJ%') ";
            }

            if (types[1] != " " && types[0] != " ")
            {
                lcSql = " select  * from " + this.TableName + " where c_sec_var_code like '" + types[0] + "%' and c_mkt_code ='" + types[1] + "'";
            }

            ////STORY #43752 下拉框大数据处理机制。添加过滤参数-张绍林-20171116
            lcSql += this.AddFilterParams(lcSql);

            DataTable loTempTable = SQLiteOperator.GetData(lcSql);
            return this.DataTableToPojos(loTempTable);
        }

        /// <summary>
        /// STORY55618【南方基金】债券提前兑付需要系统实现自动生成流水  add by liuyanni 20180613
        /// </summary>
        /// <param name="condition">condition</param>
        /// <returns>证券列表</returns>
        public List<BasePojo> getDataListByCondition(string condition)
        {
            string lcSql = " select  * from " + this.TableName + " a";

            if (condition.Trim().Length > 0)
            {
                string whereSql = " WHERE";
                string[] arrSplit = condition.Split(';');
                foreach (string sSplit in arrSplit)
                {
                    if (sSplit != null && sSplit.Trim().Length > 0)
                    {
                        if (sSplit.Contains("!="))
                        {
                            string[] arrPara = sSplit.Trim().Split(new string[] { "!=" }, StringSplitOptions.RemoveEmptyEntries);
                            if (arrPara.Length == 2)
                            {
                                if (arrPara[1].Contains("/"))
                                {
                                    whereSql += " a.C_" + arrPara[0].Trim() + "_CODE NOT IN ('" + string.Join("','", arrPara[1].Split('/')) + "') AND";
                                }
                                else
                                {
                                    whereSql += " a.C_" + arrPara[0].Trim() + "_CODE != '" + arrPara[1] + "' AND";
                                }
                            }
                        }
                        else if (sSplit.Contains("="))
                        {
                            string[] arrPara = sSplit.Trim().Split('=');
                            if (arrPara.Length == 2)
                            {
                                if (arrPara[1].Contains("%"))
                                {
                                    whereSql += " a.C_" + arrPara[0].Trim() + "_CODE LIKE '" + arrPara[1] + "' AND";
                                }
                                else
                                {
                                    whereSql += " a.C_" + arrPara[0].Trim() + "_CODE = '" + arrPara[1] + "' AND";
                                }
                            }
                        }
                    }
                }

                whereSql = whereSql.Remove(whereSql.Length - 4);
                whereSql += this.AddFilterParams(whereSql);

                lcSql += whereSql;
            }

            DataTable loTempTable = SQLiteOperator.GetData(lcSql);
            return this.DataTableToPojos(loTempTable);
        }
		
		/// <summary>
        /// STORY #93872 【深国投信托】【4.6】投资标的资产虚拟净值编码需关联到对应的投资组合
        /// </summary>
        /// <param name="condition">condition</param>
        /// <returns>证券列表</returns>
        public List<BasePojo> getDataListByPortCondition(string condition)
        {
            string lcSql = " select  * from " + this.TableName + " a";

            if (condition.Trim().Length > 0)
            {
                string whereSql = " WHERE";
                string[] arrSplit = condition.Split(';');
                foreach (string sSplit in arrSplit)
                {
                    if (sSplit != null && sSplit.Trim().Length > 0)
                    {
                        if (sSplit.Contains("!="))
                        {
                            string[] arrPara = sSplit.Trim().Split(new string[] { "!=" }, StringSplitOptions.RemoveEmptyEntries);
                            if (arrPara.Length == 2)
                            {
                                if (arrPara[1].Contains("/"))
                                {
                                    whereSql += " a.C_" + arrPara[0].Trim() + "_CODE NOT IN ('" + string.Join("','", arrPara[1].Split('/')) + "') AND";
                                }
                                else
                                {
                                    whereSql += " a.C_" + arrPara[0].Trim() + "_CODE != '" + arrPara[1] + "' AND";
                                }
                            }
                        }
                        else if (sSplit.Contains("="))
                        {
                            string[] arrPara = sSplit.Trim().Split('=');
                            if (arrPara.Length == 2)
                            {
                                string[] vars = arrPara[1].Trim().Split('|');
                                if (vars.Length > 0)
                                {
                                    string orSql = "";
                                    foreach (string var in vars)
                                    {
                                        if (var.Contains("%"))
                                        {
                                            orSql += " a.C_" + arrPara[0].Trim() + "_CODE LIKE '" + var + "'  OR";
                                        }
                                        else
                                        {
                                            if ("PUB".Equals(var))
                                            {
                                                orSql += " trim(a.C_" + arrPara[0].Trim() + "_CODE) = ''  OR";
                                            }
                                            else 
                                            {
                                                if ("PORT".Equals(arrPara[0]))
                                                {
                                                    orSql += " a.C_" + arrPara[0].Trim() + "_CODE like '" + "%" + var + "%" + "'  OR";
                                                }
                                                else 
                                                {
                                                    orSql += " a.C_" + arrPara[0].Trim() + "_CODE = '" + var + "'  OR";
                                                }
                                            }
                                            
                                        }
                                    }

                                    orSql = orSql.Remove(orSql.Length - 4);
                                    whereSql += " (" + orSql + ") AND";
                                }
                                else
                                {
                                    if (arrPara[1].Contains("%"))
                                    {
                                        whereSql += " a.C_" + arrPara[0].Trim() + "_CODE LIKE '" + arrPara[1] + "' AND";
                                    }
                                    else
                                    {
                                        if ("PUB".Equals(arrPara[1]))
                                        {
                                            whereSql += " trim(a.C_" + arrPara[0].Trim() + "_CODE) = '' AND";
                                        }
                                        else
                                        {
                                            if ("PORT".Equals(arrPara[0]))
                                            {
                                                whereSql += " a.C_" + arrPara[0].Trim() + "_CODE like '" + "%" + arrPara[1] + "%" + "' AND";
                                            }
                                            else
                                            {
                                                whereSql += " a.C_" + arrPara[0].Trim() + "_CODE = '" + arrPara[1] + "' AND";
                                            }
                                        }
                                    }
                                
                                }
                            }
                        }
                    }
                }

                whereSql = whereSql.Remove(whereSql.Length - 4);
				whereSql += this.AddFilterParams(whereSql);

                lcSql += whereSql;
            }

            DataTable loTempTable = SQLiteOperator.GetData(lcSql);
            return this.DataTableToPojos(loTempTable);
        }

        /// <summary>
        /// 前台手动刷新缓存时，证券缓存从后台比较出前台缺失的缓存，而后从后台证券缓存中取回缺失的缓存进行增量更新
        /// BY ouyangliang
        /// </summary>
        public override void UpdateDifferent()
        {
            if (null == CacheFactory.CreateCache(CacheGroup.SECBASE))
            {
                return;
            }
            ////从客户端缓存取出证券缓存
            List<BasePojo> clientSecBaseCache = CacheFactory.CreateCache(CacheGroup.SECBASE).getAllDataList();
            if (null == clientSecBaseCache)
            {
                return;
            }

            StringBuilder sb = new StringBuilder();
            foreach (BasePojo item in clientSecBaseCache)
            {
                sb.Append((item as SecBase).C_SEC_CODE + ",");
            }

            string codes = sb.ToString();
            ISecBaseInfoDataService iSecBaseInfoDataService = ServiceFactory.createService<ISecBaseInfoDataService>();
            ////调用服务查询后台得出缓存差异数据,结果集索引末尾项为缓存时间戳
            List<string> secBaseList = iSecBaseInfoDataService.UpdateDifferent(codes);
            string cacheTimeStamp = "";
            ////根据差异数据进行增量更新
            if (null != secBaseList && secBaseList.Count > 0)
            {
                cacheTimeStamp = secBaseList[(secBaseList.Count - 1)];
                secBaseList.RemoveAt(secBaseList.Count - 1);
                List<string> pageList = new List<string>();
                ////分页更新，一次最大10000条
                while (secBaseList.Count >= 10000 && (secBaseList.Count % 10000) >= 0)
                {
                    pageList.Clear();
                    pageList.AddRange(secBaseList.GetRange(0, 10000));
                    if (secBaseList.Count > 10000)
                    {
                        secBaseList.RemoveRange(0, 10000);
                    }
                    else
                    {
                        secBaseList.Clear();
                    }

                    List<BasePojo> resDataList = this.UpdateFromServerByCodes(pageList);
                    if (null != resDataList && resDataList.Count > 0)
                    {
                        clientSecBaseCache.AddRange(resDataList);
                        this.saveDataToSqlLite(resDataList);
                    }

                }
                ////不足10000条的数据更新
                if (secBaseList.Count > 0)
                {
                    List<BasePojo> resDataList = this.UpdateFromServerByCodes(secBaseList);
                    if (null != resDataList && resDataList.Count > 0)
                    {
                        clientSecBaseCache.AddRange(resDataList);
                        this.saveDataToSqlLite(resDataList);
                    }

                }
                ////将后台缓存时间戳入前台库更新
                if (string.IsNullOrEmpty(cacheTimeStamp))
                {
                    this.time = DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss");
                }
                else
                {
                    this.time = cacheTimeStamp;
                }

                this.WriteConfig();
            }

        }

        /// <summary>
        /// 根据code获取数据 
        /// </summary>
        /// <param name="codes">codes</param>
        /// <returns>list</returns>
        private List<BasePojo> UpdateFromServerByCodes(List<string> codes)
        {
            List<BasePojo> loTempPojos = null;
            if (codes != null && codes.Count > 0)
            {
                ISecBaseInfoDataService loService = DataServiceFactory.createService(typeof(ISecBaseInfoDataService), this.ServiceSite) as ISecBaseInfoDataService;
                CacheData loCacheData = loService.updateByCodes(string.Join(",", codes.ToArray()));
                if (loCacheData != null && loCacheData.DataList != "")
                {
                    List<SecBase> loSecBasePojos = JsonUtil.toObject<List<SecBase>>(loCacheData.DataList);

                    ////这里不能直接将【List<SecBase>】强转为【List<BasePojo>】，所以折中一下。
                    loTempPojos = new List<BasePojo>();
                    loTempPojos.AddRange(loSecBasePojos.ToArray());
                    return loTempPojos;
                }
            }

            return loTempPojos;
        }


        /// <summary>
        /// 重写父类的的从后台获取缓存的方法
        /// 取到之后先保存在前台的数据库中
        /// BUG #268715 【融通基金】-各个界面债券选择控件，债券信息加载不出来，右下角刷新缓存也不行，需要再次反审核债券再审核债券品种信息
        /// </summary>
        /// <param name="code">code</param>
        /// <returns>basePojo</returns>
        public override BasePojo getPojoByCodeFromServer(string code)
        {
            ClsLogger.Info("前台没有获取到券   " + code + "  改为后台查询");
            ISecBaseInfoDataService iSecBaseInfoDataService = ServiceFactory.createService<ISecBaseInfoDataService>();
            BasePojo basePojo = iSecBaseInfoDataService.getPojoByCode(code);
            List<BasePojo> pojoList = new List<BasePojo>();

            ////BUG #307031 股票流水中证券基本信息不存在时双击打开流水报错
            if (basePojo != null)
            {
                pojoList.Add(basePojo);
            }

            this.saveDataToSqlLite(pojoList);
            return basePojo;

        }

        /// <summary>
        /// BUG #268715 【融通基金】-各个界面债券选择控件，债券信息加载不出来，右下角刷新缓存也不行，需要再次反审核债券再审核债券品种信息
        /// </summary>
        /// <param name="types">types</param>
        /// <param name="paraValue">paraValue</param>
        /// <returns>list</returns>
        public override List<BasePojo> getDataListByTypesFromServer(string[] types, string paraValue)
        {
            if (paraValue != null && paraValue.Contains("_"))
            {
                paraValue = paraValue.Substring(0, paraValue.IndexOf("_"));
            }

            ClsLogger.Info("前台没有获取到券类型和券：   " + paraValue + "  改为后台查询");
            ISecBaseInfoDataService iSecBaseInfoDataService = ServiceFactory.createService<ISecBaseInfoDataService>();
            List<BasePojo> baseList = iSecBaseInfoDataService.getDataListByTypes(types, paraValue);

            ////BUG #307031 股票流水中证券基本信息不存在时双击打开流水报错
            if (baseList != null && baseList.Count > 0)
            {
                this.saveDataToSqlLite(baseList);
            }

            return baseList;
        }
    }
}