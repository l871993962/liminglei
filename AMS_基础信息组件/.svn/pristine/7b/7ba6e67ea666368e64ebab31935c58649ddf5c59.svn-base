namespace YssInformation.Bi.Account.Form
{
    using FAST.Core.BaseForm;
    using System.Xml;
    using FAST.Core.Context.Events;
    using System.IO;
    using System;
    using FAST.Core.Util;
    using FAST.Core.Context;
    using System.Collections.Generic;
    using FAST.Common.Service.Pojo.Base;
    using Yss.KTable.Models;
    using System.Drawing;
    using System.Windows.Forms;
    using Yss.KRichEx;
    using FAST.Core.BaseControl;
    using FAST.Common.Service.Pojo;
    using FAST.Core.Communication.DataService;
    using FAST.Common.Service.Services;
    using FAST.Core.Communication.Service;
    using Yss.KTable.Events;
    using System.Diagnostics;
    using Yss.KMessage;
    using FAST.Core.Enclosure.Service;
    using FAST.Platform.DataIntegration.Imp.Service;
    using System.Threading;
    using YssInformation.Support.Bi.Account.Service;
    using YssInformation.Support.Bi.Account.Pojo;

    /// <summary>
    /// 附件上传盒子
    /// </summary>
    public partial class Frm_FILEMSG_BOX_S : FrmBase
    {

        ///// <summary>
        ///// 要展示的附件LIST
        ///// </summary>
        //public List<DataEnclosure> showFileList = new List<DataEnclosure>();

        /// <summary>
        /// 用于展示的单条数据ID
        /// </summary>
        private string showDataId = "";

        /// <summary>
        /// 是否有数据改变
        /// </summary>
        public bool isSave = true;
        
        /// <summary>
        /// 序列
        /// </summary>
        private int seq = 0;

        /// <summary>
        /// 要插入的附件LIST
        /// </summary>
        private List<DataEnclosure> addFileList = new List<DataEnclosure>();

        /// <summary>
        /// 要删除的附件LIST
        /// </summary>
        private List<DataEnclosure> delFileList = new List<DataEnclosure>();

        /// <summary>
        /// 需要上传附件的数据FundAccList
        /// </summary>
        private List<FundAcc> choiceAccList = new List<FundAcc>();

        /// <summary>
        /// FunCode
        /// </summary>
        private string funCode = "";

        /// <summary>
        /// 是否Show
        /// </summary>
        public bool whetherShowBox = true;

        /// <summary>
        /// 框架附件管理service
        /// </summary>
        private IDataEnclosureService dataEnclosureService = null;

        ///// <summary>
        ///// 附件管理Service
        ///// </summary>
        //IFaxFilesMgrService faxFilesMgrService = null;

        /// <summary>
        /// 附件管理Service
        /// </summary>
        IFundAccService fundAccService = null;

        /// <summary>
        /// 接口设置界面服务
        /// </summary>
        private IImpCfgService cfgService = null;

        /// <summary>
        /// 构造方法1
        /// </summary>
        /// <param name="basePojo">basePojo</param>
        /// <param name="funCode">funCode</param>
        public Frm_FILEMSG_BOX_S(BasePojo basePojo, String funCode)
            : this(new List<BasePojo>(new BasePojo[1]{basePojo}), funCode)
        {
        }
      
        /// <summary>
        /// 构造方法2
        /// </summary>
        /// <param name="pojoList"></param>
        /// <param name="funCode"></param>
        public Frm_FILEMSG_BOX_S(List<BasePojo> basePojoList, String funCode)
        {
            this.funCode = funCode;
            dataEnclosureService = ServiceFactory.createService<IDataEnclosureService>();
            fundAccService = ServiceFactory.createService<IFundAccService>();
            cfgService = ServiceFactory.createService<IImpCfgService>();
            ////初始化
            InitializeComponent();
            ////处理数据
            this.processingData(basePojoList);
            ////绘制列头
            drawColumnHead();
        }

        /// <summary>
        /// 区分传入的各种类型数据，最终转换为DataEnclosure
        /// 对传入的数据进行业务逻辑上的处理
        /// </summary>
        /// <param name="basePojoList">basePojoList</param>
        /// <returns>bool</returns>
        private void processingData(List<BasePojo> basePojoList)
        {
            ////进行业务逻辑后返回的弹框信息(可扩展)
            string resultMsg = "";
            ////账户数据业务逻辑处理
            if ("FundAcc".Equals(funCode))
            {
                resultMsg = this.processingFundAccData(basePojoList);
            }

            if (!string.IsNullOrEmpty(resultMsg))
            {
                DialogResult dr = new MessageDialog().Show(resultMsg.Split('#')[1], "提示", MessageBoxButtons.YesNo, MessageBoxIcon.Information, resultMsg.Split('#')[0]);
                if (dr == DialogResult.No)
                {
                    whetherShowBox = false;
                }
            }
        }

        /// <summary>
        /// 传入数据为账户数据处理逻辑
        /// </summary>
        /// <param name="basePojoList"></param>
        /// <returns></returns>
        private string processingFundAccData(List<BasePojo> basePojoList)
        {
            ////弹框提醒
            string resultMsg = "";
            List<FundAcc> accList = basePojoList.ConvertAll(s => (FundAcc)s);
            ////如果只有一条数据则不提醒、要去后台查展示的附件数据
            if (accList.Count == 1)
            {
                showDataId = accList[0].N_FILE_COUNT > 0 ? accList[0].Id : "";
                choiceAccList = accList;
            }
            else
            {
                ////多条
                foreach (FundAcc acc in accList)
                {
                    ////没有上传附件的账户信息(新增)
                    if (acc.N_FILE_COUNT == 0)
                    {
                        choiceAccList.Add(acc);
                    }
                    else
                    {
                        resultMsg += acc.Id + ",";
                    }
                }
            }

            if(!string.IsNullOrEmpty(resultMsg))
            {
                resultMsg += "#所选数据中下列数据已有附件！是否跳过这些数据继续上传！：\r\n\r\n是-继续\r\n\r\n否-退出";
            }
            return resultMsg;
        } 

        /// <summary>
        /// 关闭事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void btnClose_Click(object sender, EventArgs e)
        {
            this.Dispose();
        }

        /// <summary>
        /// 保存事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void btnSave_Click(object sender, EventArgs e)
        {
            ////处理业务数据
            this.processingSaveData(sender, e);
            if (delFileList.Count > 0 || addFileList.Count > 0)
            {
                foreach (DataEnclosure d in addFileList)
                {
                    ////上传附件到服务器
                    bool check = this.UpLoad(d.C_Desc, d.C_FileName_L, "transfer/attachment");
                    if (check == false)
                    {
                        isSave = false;
                    }
                }

                if (isSave == true)
                {
                    fundAccService.updateFileMsg(delFileList, addFileList);
                }
            }

            this.Close();
            this.Dispose();
        }

        /// <summary>
        /// 保存前处理需Add的数据
        /// 可扩展
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void processingSaveData(object sender, EventArgs e)
        {
            foreach (Row row in this.richTable.Table.Rows)
            {
                string filePath = row.Cells[4].Text;
                if (!string.IsNullOrEmpty(filePath) && !filePath.StartsWith("transfer/attachment/"))
                {
                    if ("FundAcc".Equals(funCode))
                    {
                        this.processingSaveAccData(row);
                    }
                }
            }
        }

        /// <summary>
        /// 保存前处理账户ADD数据
        /// </summary>
        /// <param name="row">row</param>
        private void processingSaveAccData(Row row)
        { 
            foreach(FundAcc acc in choiceAccList){
                DataEnclosure d = row.Tag as DataEnclosure;
                d.C_Data_Id = acc.Id;
                d.C_Fun_Code = funCode;
                d.C_Fax_Status = "ZL_WCZ";
                d.C_File_Type = "OTHER_TYPE";
       
                this.addFileList.Add(d);
            }
        }

        /// <summary>
        /// 上传文件的方法
        /// </summary>
        /// <param name="serverFileName">服务端文件名</param>
        /// <param name="fullFileName">文件名称</param>
        /// <returns>上传成功返回真值</returns>
        private bool UpLoad(string serverFileName, string fullFileName, string serverPath)
        {
            ITemplateService templateService = ServiceFactory.createService<ITemplateService>();
            bool bResult = false;
            FileStream fs = File.OpenRead(fullFileName);
            try
            {
                int length = 1024 * 1024;
                int block = 0, surplus = 0;
                surplus = (int)fs.Length;
                length = (surplus > length ? length : surplus);
                byte[] buf = new byte[length];
                int index = 0;
                while ((block = fs.Read(buf, 0, length)) > 0)
                {
                    FileTrans fileTrans = new FileTrans();
                    fileTrans.B_FileBytes = buf;
                    fileTrans.C_FileName = serverFileName;
                    fileTrans.N_Index = index++;
                    string sResult = templateService.upload(serverPath, fileTrans);
                }

                bResult = true;
            }
            catch (Exception e)
            {
                bResult = false;
            }
            finally
            {
                if (fs != null)
                {
                    fs.Close();
                    fs.Dispose();
                }
            }

            return bResult;
        }

        /// <summary>
        /// 下载 事件 处理
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void btnDownLoad_Click(object sender, EventArgs e)
        {
            ////文件在服务器的路径
            List<string> fileServicePathList = new List<string>();
            ButtonCell btnCell = (ButtonCell)sender;
            DataEnclosure d = btnCell.OwnRow.Tag as DataEnclosure;
            if (btnCell.OwnRow.Cells[4].Text.StartsWith("transfer/attachment/") && d != null && !string.IsNullOrEmpty(d.C_FileName_S))
            {
                fileServicePathList.Add(d.C_FileName_S);
                this.downLoad(fileServicePathList);
            }
          
        }

        /// <summary>
        /// 批量下载 事件 处理
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void btnBatchDown_Click(object sender, EventArgs e)
        {
            ////文件在服务器的路径
            List<string> fileServicePathList = new List<string>();
            foreach (Row row in this.richTable.Table.Rows)
            {
                ////CheckBoxCell c = row.Cells[0] as CheckBoxCell;
                if (row.Checked == true)
                {
                    DataEnclosure d = row.Tag as DataEnclosure;
                    if (row.Cells[4].Text.StartsWith("transfer/attachment/") && d != null && !string.IsNullOrEmpty(d.C_FileName_S))
                    {
                        fileServicePathList.Add(d.C_FileName_S);
                    }
                }
            }
            if (fileServicePathList.Count > 0)
            {
                this.downLoad(fileServicePathList);
            }

        }

        /// <summary>
        /// 下载动作
        /// </summary>
        /// <param name="fileServicePathList">fileServicePathList</param>
        private void downLoad(List<string> fileServicePathList)
        {
            bool rs = true;
            List<string> serverFilePathList = new List<string>();
            ////下载文件到该路径
            if (clsInterface == null)
            {
                clsInterface = new ClsInterface();
            }
            string filePath = clsInterface.folderDialog();
            if (string.IsNullOrEmpty(filePath)) {
                return;
            }
            Dictionary<string, string> fileDic = fundAccService.filesDownLoad(fileServicePathList);

            ////下载
            try
            {
                foreach (string fileServicePath in fileServicePathList)
                {
                    serverFilePathList.Add(fileServicePath);
                    string fileName = fileServicePath.Substring(fileServicePath.LastIndexOf('/') + 1);
                    string downPath = fileDic[fileServicePath];
                    bool downLoadStatus = ClsFileTool.DownLoad(downPath, fileName, filePath);
                    if (downLoadStatus == false)
                    {
                        rs = false;
                    }
                }
                if (rs == true)
                {
                    Yss.CommonLib.ShowMessage("恭喜您。下载成功！");
                }
                else
                {
                    Yss.CommonLib.ShowMessage("对不起。文件下载失败或部分下载失败！");
                }
            }
            catch (Exception e)
            {
                Yss.CommonLib.ShowMessage("对不起。文件下载失败或部分下载失败！");
            }
            finally
            {
                ////删除后台临时文件
                new Thread(delegate()
                {
                    try
                    {
                        fundAccService.delFile(serverFilePathList);
                    }
                    catch (System.Exception ex)
                    {
                        YssMessageBox.ShowCommonInfo(ex.Message);
                    }
                }).Start();
            }
        }

        /// <summary>
        /// 绘制列头
        /// </summary>
        private void drawColumnHead()
        {
            ////CheckBoxCell check = new CheckBoxCell("勾选");
            ////Column checkCol = new Column("");
            CheckBoxColumn checkCol = new CheckBoxColumn();
            ////checkCol.Tag = check;
            checkCol.DataPropertyName = "勾选";
            checkCol.Width = 30;

            ////Column noCol = new Column("");
            MarkColumn noCol = new MarkColumn();
            noCol.DataPropertyName = "序列";
            noCol.Width = 30;

            Column borwCol = new Column("浏览");
            borwCol.DataPropertyName = "浏览";
            borwCol.Width = 50;

            Column downCol = new Column("下载");
            downCol.DataPropertyName = "下载";
            downCol.Width = 50;

            Column fileNameCol = new Column("文件名");
            fileNameCol.Width = 240;
            fileNameCol.DataPropertyName = "文件名";
            Column operatorCol = new Column("操作人");
            operatorCol.Width = 70;
            operatorCol.DataPropertyName = "操作人";
            Column dateCol = new Column("上传日期");
            dateCol.Width = 180;
            dateCol.DataPropertyName = "上传日期";
            this.richTable.Table.Columns.AddRange(new Column[] {checkCol,noCol,borwCol, downCol, fileNameCol, operatorCol, dateCol });

            ////IFaxFilesMgrService faxFilesMgrService = ServiceFactory.createService<IFaxFilesMgrService>();
            if (!string.IsNullOrEmpty(showDataId))
            {
                List<DataEnclosure> showfileList = dataEnclosureService.getDataEnclosures(funCode, new List<string>(showDataId.Split(',')));
                foreach (DataEnclosure d in showfileList)
                {
                    if (!string.IsNullOrEmpty(d.C_FileName_S))
                    {
                        Row row = new Row();
                        row.Tag = d;
                        Cell cell = null;

                        cell = new Cell();
                        row.Cells.Add(cell);

                        cell = new Cell();
                        row.Cells.Add(cell);

                        ButtonCell bottonBrow = new ButtonCell();
                        bottonBrow.Text = "浏览";
                        bottonBrow.TextAlign = ContentAlignment.MiddleCenter;
                        bottonBrow.ForeColor = Color.Black;
                        bottonBrow.BackColor = Color.LimeGreen;
                        bottonBrow.BackColorFocus = Color.DarkGreen;
                        bottonBrow.BackColorFocusAuxiliary = Color.LightSeaGreen;
                        bottonBrow.BackColorHot = Color.DarkSeaGreen;
                        bottonBrow.BackColorHotAuxiliary = Color.LightSeaGreen;
                        bottonBrow.Font = new Font("宋体", 10, FontStyle.Regular);
                        bottonBrow.Click += new CellEventHandler(btnFile_Click);
                        row.Cells.Add(bottonBrow);
                        ////row.Cells.Insert(2, bottonBrow);

                        ButtonCell bottonDown = new ButtonCell();
                        bottonDown.Text = "下载";
                        bottonDown.TextAlign = ContentAlignment.MiddleCenter;
                        bottonDown.ForeColor = Color.Black;
                        bottonDown.BackColor = Color.LimeGreen;
                        bottonDown.BackColorFocus = Color.DarkGreen;
                        bottonDown.BackColorFocusAuxiliary = Color.LightSeaGreen;
                        bottonDown.BackColorHot = Color.DarkSeaGreen;
                        bottonDown.BackColorHotAuxiliary = Color.LightSeaGreen;
                        bottonDown.Font = new Font("宋体", 10, FontStyle.Regular);
                        bottonDown.Click += new CellEventHandler(btnDownLoad_Click); 
                        row.Cells.Add(bottonDown);

                        cell = new Cell();
                        TailTextBox tailTxtBox = new TailTextBox();
                        tailTxtBox.YssCaption = "路径";
                        tailTxtBox.YssLength = 180;
                        tailTxtBox.TailImage = FAST.Resource.Resource.OpenFolder_TailText;
                        tailTxtBox.TailImagePosition = new Point(3, 1);
                        tailTxtBox.TailImageSize = new Size(16, 16);
                        ////tailTxtBox.TailClick += new EventHandler(txtBox_TailClick);
                        tailTxtBox.Text = d.C_FileName_S;
                        tailTxtBox.Tag = cell;
                        cell.InnerControl = tailTxtBox;
                        tailTxtBox.YssReadOnly = true;
                        tailTxtBox.Enabled = false;
                        row.Cells.Add(cell);

                        cell = new Cell();
                        cell.Text = string.IsNullOrEmpty(d.C_OperUser) ? ClsContext.currentUser.C_USER_CODE : d.C_OperUser;
                        row.Cells.Add(cell);

                        cell = new Cell();
                        cell.Text = string.IsNullOrEmpty(d.C_OperDate) ? DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss") : d.C_OperDate;
                        row.Cells.Add(cell);
                        
                        richTable.Table.Rows.Add(row);
                    }
                }
                richTable.Table.Refresh();
            }
        }

        /// <summary>
        /// richTable_AfterRowAdded
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void richTable_AfterRowAdded(object sender, Yss.KTable.Events.RowEventArgs e)
        {
            Column checkCol = this.richTable.Table.Columns["勾选"];
            Column noCol = this.richTable.Table.Columns["序列"];
            Column borwCol = this.richTable.Table.Columns["浏览"];
            Column downCol = this.richTable.Table.Columns["下载"];
            Column fileNameCol = this.richTable.Table.Columns["文件名"];
            Column operatorCol = this.richTable.Table.Columns["操作人"];
            Column dateCol = this.richTable.Table.Columns["上传日期"];

            Cell cell0 = e.Row.Cells[checkCol.Index];
            Cell cell1 = e.Row.Cells[noCol.Index];
            Cell cell2 = e.Row.Cells[borwCol.Index];
            Cell cell3 = e.Row.Cells[downCol.Index];
            Cell cell4 = e.Row.Cells[fileNameCol.Index];
            Cell cell5 = e.Row.Cells[operatorCol.Index];
            Cell cell6 = e.Row.Cells[dateCol.Index];

            ////e.Row.Cells.Remove(e.Row.Cells[0], true);
            ////CheckBoxCell checkCell = new CheckBoxCell();
            ////checkCell.Tag = cell0;
            ////e.Row.Cells.Insert(0, checkCell);

            ////e.Row.Cells.Remove(e.Row.Cells[1], true);
            ////Cell noCell = new Cell();
            ////noCell.Text = (this.richTable.Table.Rows.Count).ToString();
            ////e.Row.Cells.Insert(1, noCell);

            e.Row.Cells.Remove(e.Row.Cells[2], true);
            ButtonCell bottonBrow = new ButtonCell();
            bottonBrow.Text = "浏览";
            bottonBrow.TextAlign = ContentAlignment.MiddleCenter;
            bottonBrow.ForeColor = Color.Black;
            bottonBrow.BackColor = Color.LimeGreen;
            bottonBrow.BackColorFocus = Color.DarkGreen;
            bottonBrow.BackColorFocusAuxiliary = Color.LightSeaGreen;
            bottonBrow.BackColorHot = Color.DarkSeaGreen;
            bottonBrow.BackColorHotAuxiliary = Color.LightSeaGreen;
            bottonBrow.Font = new Font("宋体", 10, FontStyle.Regular);
            bottonBrow.Click += new CellEventHandler(btnFile_Click);
            e.Row.Cells.Insert(2, bottonBrow);

            e.Row.Cells.Remove(e.Row.Cells[3], true);
            ButtonCell bottonDown = new ButtonCell();
            bottonDown.Text = "下载";
            bottonDown.TextAlign = ContentAlignment.MiddleCenter;
            bottonDown.ForeColor = Color.Black;
            bottonDown.BackColor = Color.LimeGreen;
            bottonDown.BackColorFocus = Color.DarkGreen;
            bottonDown.BackColorFocusAuxiliary = Color.LightSeaGreen;
            bottonDown.BackColorHot = Color.DarkSeaGreen;
            bottonDown.BackColorHotAuxiliary = Color.LightSeaGreen;
            bottonDown.Font = new Font("宋体", 10, FontStyle.Regular);
            bottonDown.Click += new CellEventHandler(btnDownLoad_Click);
            bottonDown.Tag = cell3;
            e.Row.Cells.Insert(3, bottonDown);

            TailTextBox txtBox = new TailTextBox();
            txtBox.YssCaption = "路径";
            txtBox.YssLength = 180;
            txtBox.TailImage = FAST.Resource.Resource.OpenFolder_TailText;
            txtBox.TailImagePosition = new Point(3, 1);
            txtBox.TailImageSize = new Size(16, 16);
            txtBox.TailClick += new EventHandler(txtBox_TailClick);
            cell4.InnerControl = txtBox;
            txtBox.Tag = cell4;

            e.Row.Tag = new DataEnclosure();
            richTable.Refresh();
        }

        /// <summary>
        /// 删除行数据点击事件 
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void richTable_BeforeRowRemoved(object sender, Yss.KTable.Events.RowEventArgs e)
        {
            string filePath = e.Row.Cells[4].Text;
            if (!string.IsNullOrEmpty(filePath) && filePath.StartsWith("transfer/attachment/"))
            {
                this.delFileList.Add(e.Row.Tag as DataEnclosure);
            }
            ////int index = 1;
            ////for (int i = 0; i < this.richTable.Table.Rows.Count; i++)
            ////{
            ////    Row row = this.richTable.Table.Rows[i];
            ////    if(row == e.Row){
            ////        continue;
            ////    }
            ////    row.Cells[1].Text = index.ToString();
            ////    index++;
            ////}

            richTable.Refresh();
        }

        ////private void processingDelAccData(Row row)
        ////{
        ////    foreach (FundAcc acc in choiceAccList)
        ////    {
        ////        DataEnclosure d = row.Tag as DataEnclosure;
        ////        d.C_Data_Id = acc.Id;
        ////        this.addFileList.Add(d);
        ////    }
        ////}

        /// <summary>
        /// 结果文件浏览
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void btnFile_Click(object sender, CellEventArgs e)
        {
            Column fileNameCol = this.richTable.Table.Columns["文件名"];
            string filePath = e.Row.Cells[fileNameCol.Index].InnerControl.Text;

            if (filePath.StartsWith("transfer/attachment/"))
            {
                ////创建临时文件夹
                string filePathTemp = ClsConstant.PATH_TEMP + "tempToOpenByLocal\\";
                if (!Directory.Exists(filePathTemp))
                {
                    Directory.CreateDirectory(filePathTemp);
                }
                string tempFileName = filePath;
                string fileName = tempFileName.Substring(tempFileName.LastIndexOf("/") + 1);
                ////IZhzlMgrService zhzlMgrService = ServiceFactory.createService<IZhzlMgrService>();
                string path = fundAccService.fileDown(tempFileName);
                bool bOper = ClsFileTool.DownLoad(path, fileName, filePathTemp);
                if (bOper)
                {
                    ////System.Diagnostics.Process pro = new System.Diagnostics.Process();
                    ////pro.EnableRaisingEvents = false;
                    ////pro.StartInfo.Arguments = filePathTemp + fileName;
                    ////pro.StartInfo.UseShellExecute = true;
                    ////pro.
                    ////pro.Start(filePathTemp + fileName);
                    System.Diagnostics.Process.Start(filePathTemp + fileName);
                    ////pro.WaitForExit();
                    ////pro.Close();
                    ////pro.Dispose();
                }
            }

            if (!string.IsNullOrEmpty(filePath))
            {
                if (File.Exists(filePath))
                {
                    Process.Start(filePath);
                }
            }
        }

        /// <summary>
        /// txtBox_TailClick
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void txtBox_TailClick(object sender, EventArgs e)
        {
            TailTextBox txtBox = (TailTextBox)sender;

            YssSelCombox combox = txtBox.Tag as YssSelCombox;
            if (clsInterface == null)
            {
                clsInterface = new ClsInterface();
            }

            string arrFile = clsInterface.fileDialog("附件", "", "");
 
            if (arrFile != null && !arrFile.Equals(""))
            {
                FileInfo fileInfo = new FileInfo(arrFile);
                string userCode = ClsContext.currentUser.C_USER_CODE;
                string upDate = DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss");
                ((Yss.KRichEx.TailTextBox)sender).Text = arrFile;
                Cell c = txtBox.Tag as Cell;
                (c.OwnRow.Tag as DataEnclosure).C_FileName_L = arrFile;
                (c.OwnRow.Tag as DataEnclosure).C_FileName_S = "transfer/attachment/" + fileInfo.Name;
                (c.OwnRow.Tag as DataEnclosure).C_OperUser = userCode;
                (c.OwnRow.Tag as DataEnclosure).C_OperDate = upDate;
                (c.OwnRow.Tag as DataEnclosure).C_Desc = fileInfo.Name;
                c.OwnRow.Cells[5].Text = userCode;
                c.OwnRow.Cells[6].Text = upDate;
            }
        }

    }
}
