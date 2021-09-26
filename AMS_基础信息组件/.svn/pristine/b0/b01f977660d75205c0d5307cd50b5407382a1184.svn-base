using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Core.Cache;
using FAST.Common.Service.Pojo.Base;
using FAST.Common.Service.Pojo;
using System.Data;
using FAST.Core.Util;
using FAST.Core.Communication.DataService;
using System.Threading;
using YssInformation.Support.Bi.AssociationOrgan.Pojo;
using YssInformation.Support.Bi.AssociationOrgan.Service;
using FAST.Core.Communication.Service;

namespace YssInformation.Support.Bi.AssociationOrgan.Cache
{
    /// <summary>
    /// 机构信息类前台缓存对象。
    /// </summary>
    public class OrgCache : BaseCache
    {
        /// <summary>
        /// 分页大小
        /// </summary>
        private int pageSize = 1000;

        /// <summary>
        /// 机构服务接口
        /// </summary>
        private IOrgDataService orgDataService = null;

        /// <summary>
        /// 构造 BUG197890【太平基金】关联机构设置以及回购交易中的对手方信息查询过慢
        /// BUG250890【国海证券】新增回购交易业务，对手方添加时，都会卡一段时间 
        ///     添加getAllDataListByPage方法，需要自己设置机构控件的QueryDataByPage=true，YssAssociation=base_organ属性才会有翻页效果
        /// </summary>
        /// <param name="pcServiceSite">服务器站点</param>
        /// <param name="pcStartupPath">程序所在目录</param>
        public OrgCache(string pcServiceSite, string pcStartupPath)
            : base(pcServiceSite, pcStartupPath)
        {
            this.DataFileVersion = "2020-01-14 10:43";
            this.orgDataService = DataServiceFactory.createService(typeof(IOrgDataService), this.ServiceSite) as IOrgDataService;
            this.CheckLocalDataFile();
        }

        /// <summary>
        /// 获取客户端数据缓存类型
        /// </summary>
        protected override CacheGroup CacheGroup
        {
            get { return CacheGroup.ORG; }
        }

        /// <summary>
        /// 获取待操作的表。
        /// </summary>
        protected override string TableName
        {
            get { return "Org"; }
        }

        /// <summary>
        /// 获取插入数据SQL模版。
        /// </summary>
        protected override string InsertSqlFormat
        {
            get
            {
                return "\r\n insert into Org (C_IDEN, C_ORG_CODE, C_ORG_NAME, C_DV_ORG_TYPE, C_ORG_CODE_P, C_DV_TRUSTEE,C_DV_SALES_CHANNELS,C_DV_TRUSTEE_SEC,C_DV_TRUSTEE_MA,N_CHECK_STATE)"
               + " values('{0}','{1}','{2}','{3}','{4}','{5}','{6}','{7}','{8}','{9}') \r\n";
            }
        }

        /// <summary>
        /// 获取Pojo的Code值。
        /// </summary>
        protected override string Code
        {
            get { return "C_ORG_CODE"; }
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
            get { return "C_DV_ORG_TYPE"; }
        }

        /// <summary>
        /// 创建表格
        /// 注意：修改CreateTable方法后，一定要更改【_DataFileVertion】
        /// </summary>
        protected override void CreateTable()
        {
            string lcCreateTable = "create table " + this.TableName + " ("
                + " C_IDEN text primary key, "
                + " C_ORG_CODE text, "
                + " C_ORG_NAME text, "
                + " C_DV_ORG_TYPE text, "
                + " C_ORG_CODE_P text, "
                + " C_DV_TRUSTEE text,"
                + " C_DV_SALES_CHANNELS text,"
                + " C_DV_TRUSTEE_SEC text,"
                + " C_DV_TRUSTEE_MA text,"
                + " N_CHECK_STATE text);";


            this.SQLiteOperator.CreateTable(lcCreateTable);
        }

        /// <summary>
        /// 将从Access查询的Table转换为Pojo，默认采集第一条数据。
        /// </summary>
        /// <param name="poTable">待转换的Table数据源</param>
        /// <returns>返回Pojo</returns>
        protected override BasePojo DataTableToPojo(System.Data.DataTable poTable)
        {
            Org loBasePojo = null;
            if (poTable != null && poTable.Rows.Count > 0)
            {
                loBasePojo = new Org();

                DataRow loTempRow = poTable.Rows[0];
                loBasePojo.Id = loTempRow["C_IDEN"].ToString();
                loBasePojo.C_ORG_CODE = loTempRow["C_ORG_CODE"].ToString();
                loBasePojo.C_ORG_NAME = loTempRow["C_ORG_NAME"].ToString();
                loBasePojo.C_DV_ORG_TYPE = loTempRow["C_DV_ORG_TYPE"].ToString();
                loBasePojo.C_ORG_CODE_P = loTempRow["C_ORG_CODE_P"].ToString();
                loBasePojo.C_DV_TRUSTEE = loTempRow["C_DV_TRUSTEE"].ToString();
                loBasePojo.C_DV_SALES_CHANNELS = loTempRow["C_DV_SALES_CHANNELS"].ToString();
                loBasePojo.C_DV_TRUSTEE_SEC = loTempRow["C_DV_TRUSTEE_SEC"].ToString();
                loBasePojo.C_DV_TRUSTEE_MA = loTempRow["C_DV_TRUSTEE_MA"].ToString();
                loBasePojo.AuditState = int.Parse(loTempRow["N_CHECK_STATE"].ToString());
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
        /// 添加方法 获取所有的Pojo对象集。
        /// BUG250890【国海证券】新增回购交易业务，对手方添加时，都会卡一段时间
        /// </summary>
        /// <returns>返回Pojo对象集</returns>
        public List<BasePojo> getAllDataListByPage()
        {
            string lcSql = "select * from " + this.TableName;
            //// 这里只能用base父类的方法，类里面的这个方法已经不做什么操作了。
            lcSql += base.AddFilterParams(lcSql);
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
                string lcOrgCode = "";
                foreach (DataRow loTempRow in poTable.Rows)
                {
                    lcOrgCode = loTempRow["C_ORG_CODE"].ToString();
                    if (loCodeList.ContainsKey(lcOrgCode))
                    {
                        ////由于在删除并重新录入数据时，客户端缓存数据无法做到同步（数据量大，严重影响性能），所以存在客户端数据重复。
                        ////所以，需要在这里剔除掉重复的数据。张绍林-20150929
                        continue;
                    }

                    Org loBasePojo = new Org();
                    loBasePojo.Id = loTempRow["C_IDEN"].ToString();
                    loBasePojo.C_ORG_CODE = lcOrgCode;
                    loBasePojo.C_ORG_NAME = loTempRow["C_ORG_NAME"].ToString();
                    loBasePojo.C_DV_ORG_TYPE = loTempRow["C_DV_ORG_TYPE"].ToString();
                    loBasePojo.C_ORG_CODE_P = loTempRow["C_ORG_CODE_P"].ToString();
                    loBasePojo.C_DV_TRUSTEE = loTempRow["C_DV_TRUSTEE"].ToString();
                    loBasePojo.C_DV_SALES_CHANNELS = loTempRow["C_DV_SALES_CHANNELS"].ToString();
                    loBasePojo.C_DV_TRUSTEE_SEC = loTempRow["C_DV_TRUSTEE_SEC"].ToString();
                    loBasePojo.C_DV_TRUSTEE_MA = loTempRow["C_DV_TRUSTEE_MA"].ToString();
                    loBasePojo.AuditState = int.Parse(loTempRow["N_CHECK_STATE"].ToString());
                    loBasePojos.Add(loBasePojo);
                    loCodeList.Add(lcOrgCode, null);
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
                Org loTempPojo = poBasePojo as Org;
                lcSql = string.Format(this.InsertSqlFormat, loTempPojo.Id.Replace("'", "''"), loTempPojo.C_ORG_CODE.Replace("'", "''"), loTempPojo.C_ORG_NAME.Replace("'", "''").Replace("\0", ""), loTempPojo.C_DV_ORG_TYPE.Replace("'", "''"), loTempPojo.C_ORG_CODE_P.Replace("'", "''"), loTempPojo.C_DV_TRUSTEE.Replace("'", "''"), loTempPojo.C_DV_SALES_CHANNELS.Replace("'", "''"), loTempPojo.C_DV_TRUSTEE_SEC.Replace("'", "''"), loTempPojo.C_DV_TRUSTEE_MA.Replace("'", "''"), loTempPojo.AuditState.ToString().Replace("'", "''"));
            }

            return lcSql;
        }

        /// <summary>
        /// 将Pojo对象转换为Sql删除语句。
        /// </summary>
        /// <param name="poBasePojo">待转换的Pojo</param>
        /// <returns>返回Sql语句</returns>
        protected override string PojoToDeleteSql(BasePojo poBasePojo)
        {
            Org loPojo = poBasePojo as Org;
            ////string lcSql = " delete from " + this.TableName + " where C_IDEN = '" + loPojo.Id + "'"; by weijj 20160421 根据业务主键删除 防止删除重新新增后，前台缓存没有更新证券品种
            ////增加证券代码“'”单引号的处理
            string lcSql = " delete from " + this.TableName + " where C_ORG_CODE = '" + loPojo.C_ORG_CODE.Replace("'", "''") + "'" + " or C_IDEN = '" + loPojo.Id.Replace("'", "''") + "'";
            return lcSql;
        }

        /// <summary>
        /// 将Pojo对象转换为Sql更新语句集。
        /// BUG #184582 嘉实基金-流水，行情页面选择交易证券时卡顿（更新证券缓存慢）。张绍林-20171222
        /// </summary>
        /// <param name="poBasePojos">待转换的Pojo集</param>
        /// <returns>返回Sql语句集</returns>
        protected override List<string> PojoToDeleteSql(List<BasePojo> poBasePojos)
        {
            List<string> loSqlList = new List<string>();

            string lcSqlMode = " delete from " + this.TableName + " where C_ORG_CODE in ('{0}') or C_IDEN in ('{1}')";
            List<string> loCodeList = new List<string>();
            List<string> loIdenList = new List<string>();
            foreach (Org loPojo in poBasePojos)
            {
                ////增加证券代码集，并对证券代码进行“'”单引号处理
                loCodeList.Add(loPojo.C_ORG_CODE.Replace("'", "''"));
                loIdenList.Add(loPojo.Id.Replace("'", "''"));

                ////1000条一个批次，语句太长，SQLite执行效率低，易出错
                if (loCodeList.Count == 1000)
                {
                    string lcTempSql = string.Format(lcSqlMode, string.Join("','", loCodeList.ToArray()), string.Join("','", loIdenList.ToArray()));
                    loSqlList.Add(lcTempSql);
                    loCodeList.Clear();
                    loIdenList.Clear();
                }
            }

            if (loCodeList.Count > 0)
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
            IOrgDataService loService = DataServiceFactory.createService(typeof(IOrgDataService), this.ServiceSite) as IOrgDataService;

            ////使用分页查询数据，查完全部后一次性插入mdb文件
            string countStr = loService.getUpdateByTimestampCount(pcDateTime);
            int count = Convert.ToInt32(countStr);
            if (count > 0)
            {
                ////BUG #183925 【广发基金】【保险资产】【缓存】缓存在筛选证券时经常报错。张绍林-20171219
                ////改用线程池，分批获取每页的数据，频率1秒，4并发。
                Yss.ThreadPoolConcurrent threadPool = new Yss.ThreadPoolConcurrent();
                threadPool.ThreadTick = 1000;
                threadPool.MaxRunningCount = 4;
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
                this.time = DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss");
            }
        }

        /// <summary>
        /// 将数据集插入到本地缓存数据库。
        /// BUG #183925 【广发基金】【保险资产】【缓存】缓存在筛选证券时经常报错：The database file is locked
        /// </summary>
        /// <param name="poService">服务</param>
        /// <param name="dateTime">日期时间戳</param>
        /// <param name="page">数据分页页码</param>
        /// <param name="plUpdateAll">是否强制刷新所有数据</param>
        private void InsertToDataBase(IOrgDataService poService, string dateTime, PageInation page, bool plUpdateAll)
        {
            try
            {
                List<BasePojo> pojoList = getFromServerByPage(poService, dateTime, page);

                List<string> sqlList = new List<string>();
                if (plUpdateAll == false)
                {
                    //不是更新全部时，插入前要先执行一次删除操作
                    sqlList.AddRange(this.PojoToDeleteSql(pojoList));
                }

                sqlList.AddRange(this.PojoToInsertSql(pojoList));

                int excuteState = this.SQLiteOperator.UpdateData(sqlList);
                if (excuteState > -1 && page.PageCount == page.CurrPage)
                {
                    //最后一页的时候，才能写入配置文件
                    this.WriteConfig();
                }

                if (excuteState != sqlList.Count)
                {
                    ClsLogger.Info("机构缓存刷新失败,页码：" + page.CurrPage);
                }
            }
            catch (Exception ex)
            {
                ClsLogger.Error(ex);
            }
        }

        /// <summary>
        /// 根据分页查询数据
        /// </summary>
        /// <param name="poService">服务</param>
        /// <param name="dateTime">日期时间戳</param>
        /// <param name="page">数据分页页码</param>
        /// <returns>返回更新到的数据集</returns>
        private List<BasePojo> getFromServerByPage(IOrgDataService poService, string dateTime, PageInation page)
        {
            List<BasePojo> loTempPojos = new List<BasePojo>();
            CacheData loCacheData = poService.updateByTimestampPage(dateTime, page);
            this.time = loCacheData.Timestamp;
            if (loCacheData != null && loCacheData.DataList != "")
            {
                List<Org> loOrgPojos = JsonUtil.toObject<List<Org>>(loCacheData.DataList);
                loTempPojos.AddRange(loOrgPojos.ToArray());
            }

            return loTempPojos;
        }

        /// <summary>
        /// 根据ID获取数据
        /// STORY #40717 缓存刷新增加删除和审核数据时增量刷新 雷建华 20170329
        /// </summary>
        /// <param name="ids">ids</param>
        /// <returns>list</returns>
        protected override List<BasePojo> UpdateFromServerByIds(List<string> ids)
        {
            ////BUG #167994 嘉实基金—证券信息加载时间过长，影响客户做账效率 雷建华 20170803
            List<BasePojo> loTempPojos = null;
            if (ids != null && ids.Count > 0)
            {
                CacheData loCacheData = orgDataService.updateByIds(string.Join(",", ids.ToArray()));
                if (loCacheData != null && loCacheData.DataList != "")
                {
                    List<Org> loOrgPojos = JsonUtil.toObject<List<Org>>(loCacheData.DataList);

                    loTempPojos = new List<BasePojo>();
                    loTempPojos.AddRange(loOrgPojos.ToArray());
                    return loTempPojos;
                }
            }

            return loTempPojos;
        }

        #endregion

        /// <summary>
        /// 前台通过缓存获取数据，根据证券品种，市场获取证券列表
        /// </summary>
        /// <param name="types">机构类型</param>
        /// <returns>机构列表</returns>
        public List<BasePojo> getDataListByTypes(string[] types)
        {
            string lcWhere = "";
            lcWhere += " c_dv_org_type in ( ";
            foreach (string lcType in types)
            {
                if (string.Equals(lcType, types[types.Length - 1]))
                {
                    lcWhere = lcWhere.Substring(0, lcWhere.LastIndexOf(','));
                    lcWhere += " )  ";
                    break;
                }

                lcWhere += " '" + lcType + "' ,";
            }

            string lcSql = " select  * from " + this.TableName + " where ( " + lcWhere + " ) ";

            DataTable loTempTable = SQLiteOperator.GetData(lcSql);
            return this.DataTableToPojos(loTempTable);
        }

        /// <summary>
        /// 前台通过缓存获取数据，根据证券品种，市场获取证券列表
        /// </summary>
        /// <param name="types">机构类型</param>
        /// <returns>机构列表</returns>
        public List<BasePojo> getDataListByTypesForPage(string[] types)
        {
            string lcWhere = "";
            lcWhere += " c_dv_org_type in ( ";
            foreach (string lcType in types)
            {
                if (string.Equals(lcType, types[types.Length - 1]))
                {
                    lcWhere = lcWhere.Substring(0, lcWhere.LastIndexOf(','));
                    lcWhere += " )  ";
                    break;
                }

                lcWhere += " '" + lcType + "' ,";
            }

            string lcSql = " select  * from " + this.TableName + " where ( " + lcWhere + " ) ";
            lcSql += " limit 100 offset " + (this.CurrentPageNumber * 100).ToString();
            DataTable loTempTable = SQLiteOperator.GetData(lcSql);
            return this.DataTableToPojos(loTempTable);
        }

        /// <summary>
        /// 通过机构代码获取机构信息
        /// </summary>
        /// <param name="cOrgCode">1</param>
        /// <returns>1</returns>
        public BasePojo getOrgInfoDataByOrgCode(string cOrgCode)
        {
            string lcSql = " select  * from " + this.TableName + " where " + this.Code + " = '" + cOrgCode + "'";
            DataTable loTempTable = SQLiteOperator.GetData(lcSql);
            return this.DataTableToPojo(loTempTable);
        }

        /// <summary>
        /// 重写，树形结构的数据不分页
        /// </summary>
        /// <param name="pcSqlSource">现有的SQL语句，要根据现有的SQL语句检测待追加的查询条件关键字</param>
        /// <returns>返回过滤条件</returns>
        protected override string AddFilterParams(string pcSqlSource)
        {
            return "";
        }

        /// <summary>
        /// 定义下拉框的树形排序
        /// Created By yaohongwei 2016-4-26
        /// </summary>
        /// <param name="param">param</param>
        /// <returns>list</returns>
        public List<BasePojo> getDataListTree(string[] param)
        {
            return orgDataService.getDataListTree(param);
        }

        /// <summary>
        /// 查询所有银行总行
        /// </summary>
        /// <returns>符合条件的数据</returns>
        public List<BasePojo> getAllBankHead()
        {
            return orgDataService.getAllBankHead();
        }

        /// <summary>
        /// 查询所有银行总行
        /// </summary>
        /// <returns>符合条件的数据</returns>
        public override List<BasePojo> getDataList()
        {
            return orgDataService.getDataList();
        }

        /// <summary>
        /// 根据总行查银行支行
        /// </summary>
        /// <param name="param">符合条件的数据</param>
        /// <returns>list</returns>
        public List<BasePojo> getBankBranchByHead(string[] param)
        {
            return orgDataService.getBankBranchByHead(param);
        }

        /// <summary>
        /// add by shijian 2016-10-12 STORY #35056 嘉实基金--成交清算日报表--增加名义管理人等字段
        /// 按查询条件查询数据
        /// </summary>
        /// <param name="condition">查询条件</param>
        /// <returns>符合条件的数据</returns>
        public List<BasePojo> getDataListByCondition(string condition)
        {
            return orgDataService.getDataListByCondition(condition);
        }

        /// <summary>
        /// 按机构资质查询机构数据
        /// </summary>
        /// <param name="types">types</param>
        /// <returns>list</returns>
        public List<BasePojo> getDataListByAptitude(string[] types)
        {
            return orgDataService.getDataListByAptitude(types);
        }

        /// <summary>
        /// 按机构类型查询顶级机构数据
        /// </summary>
        /// <param name="types">types</param>
        /// <returns>list</returns>
        public List<BasePojo> getParentListByTypes(string[] types)
        {
            return orgDataService.getParentListByTypes(types);
        }
    }
}
