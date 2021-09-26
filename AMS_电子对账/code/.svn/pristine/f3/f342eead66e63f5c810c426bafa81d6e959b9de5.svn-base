using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.IO;
using YssElecReco.Service.Er;
using YssElecReco.pojo.Er;
using Yss.KTable.Models;
using Yss.KMessage;
using FAST.Core.CRUD.Form;
using FAST.Common.Service.Pojo;
using FAST.Core.BaseControl.Pojo;
using FAST.Core.BaseControl.Fun;
using FAST.Resource;
using FAST.Core.Util;
using FAST.Core.Exceptions;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Communication.Service;
using FAST.Core.Context;
using FAST.Platform.Document.Fun;
using YssElecReco.Fun;
using YssElecReco.pojo.Deploy;

namespace YssElecReco.Form.Er
{
    /// <summary>
    /// 部署模板
    /// </summary>
    public partial class Frm_ELEC_TEMPLATE_L : FrmBaseListWithDetails
    {
        /// <summary>
        /// 部署
        /// </summary>
        private const string BTN_TEXT_DEPLOY = "部署";

        /// <summary>
        /// 卸载
        /// </summary>
        private const string BTN_TEXT_UNDEPLOY = "卸载";

        /// <summary>
        /// 启用
        /// </summary>
        private const string BTN_TEXT_USED = "启用";

        /// <summary>
        /// 未启用
        /// </summary>
        private const string BTN_TEXT_UNUSED = "停用";

        /// <summary>
        /// 上传
        /// </summary>
        private const string BTN_TEXT_UPLOAD = "上传";

        /// <summary>
        /// 下载
        /// </summary>
        private const string BTN_TEXT_DOWNLOAD = "下载";

        /// <summary>
        /// 模板状态，可用
        /// </summary>
        protected const string TEMP_MODE_USED = "TEMP_USABLE";

        /// <summary>
        /// 模板状态，不可用
        /// </summary>
        protected const string TEMP_MODE_UNUSED = "TEMP_UNUSABLE";

        /// <summary>
        /// 电子对账模板服务类
        /// </summary>
        private IDzTemplateService _templateService = null;

        /// <summary>
        /// 电子对账模板部署
        /// </summary>
        public Frm_ELEC_TEMPLATE_L()
        {
            InitializeComponent();
            this.bUseMVCService = true;
            isLoadFirst = true;
        }

        /// <summary>
        /// 电子对账模板服务类
        /// </summary>
        private IDzTemplateService TemplateService
        {
            get
            {
                if (_templateService == null)
                {
                    _templateService = ServiceFactory.createService<IDzTemplateService>();
                }

                return _templateService;
            }
        }

        /// <summary>
        /// load event
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_ELEC_TEMPLATE_L_Load(object sender, EventArgs e)
        {
            //// 部署
            ClsButtonInfo buttonDeploy = new ClsButtonInfo();
            buttonDeploy.Image = FAST.Resource.Resource.btnUpload_L;
            buttonDeploy.Name = ClsButtonName.BtnNew;
            buttonDeploy.Text = BTN_TEXT_DEPLOY;
            buttonDeploy.Tooltip = BTN_TEXT_DEPLOY;
            buttonDeploy.ClickEvent = btnDeploy_Click;

            ////下载
            ClsButtonInfo buttonDownload = new ClsButtonInfo();
            buttonDownload.Image = FAST.Resource.Resource.btn_Download_L;
            buttonDownload.Name = ClsButtonName.BtnEdit;
            buttonDownload.Text = BTN_TEXT_DOWNLOAD;
            buttonDownload.Tooltip = BTN_TEXT_DOWNLOAD;
            buttonDownload.ClickEvent = btnDownLoad_Click;

            //// 卸载
            ClsButtonInfo buttonUnDeploy = new ClsButtonInfo();
            buttonUnDeploy.Image = FAST.Resource.Resource.btnDel_L;
            buttonUnDeploy.Name = ClsButtonName.BtnDelete;
            buttonUnDeploy.Text = BTN_TEXT_UNDEPLOY;
            buttonUnDeploy.Tooltip = BTN_TEXT_UNDEPLOY;
            buttonUnDeploy.ClickEvent = btnUnDeploy_Click;

            //// 启用
            ClsButtonInfo buttonUsed = new ClsButtonInfo();
            buttonUsed.Name = "USE";
            buttonUsed.Text = BTN_TEXT_USED;
            buttonUsed.Tooltip = BTN_TEXT_USED;
            buttonUsed.ClickEvent = btnUsed_Click;

            //// 停用
            ClsButtonInfo buttonUnUsed = new ClsButtonInfo();
            buttonUnUsed.Name = "UNUSE";
            buttonUnUsed.Text = BTN_TEXT_UNUSED;
            buttonUnUsed.Tooltip = BTN_TEXT_UNUSED;
            buttonUnUsed.ClickEvent = btnUnUsed_Click;

            ClsButtonInfo btnNew = btnBar.getButton(ClsButtonName.BtnNew);
            btnBar.removeButton(btnNew);
            ClsButtonInfo btnEdit = btnBar.getButton(ClsButtonName.BtnEdit);
            btnBar.removeButton(btnEdit);
            ClsButtonInfo btnDel = btnBar.getButton(ClsButtonName.BtnDelete);
            btnBar.removeButton(btnDel);
            ClsButtonInfo btnCopy = btnBar.getButton(ClsButtonName.BtnCopy);
            btnBar.removeButton(btnCopy);
            btnBar.addButton(buttonDeploy, 0);
            btnBar.addButton(buttonDownload, 1);
            btnBar.addButton(buttonUnDeploy, 2);
            btnBar.addButton(buttonUsed, 3);
            btnBar.addButton(buttonUnUsed, 4);
        }

        /// <summary>
        /// 封装前台查询条件
        /// </summary>
        /// <param name="paraDict">paraDict</param>
        /// <returns>paraDict</returns>
        protected override Dictionary<string, string> OnGetParaAssemble(Dictionary<string, string> paraDict)
        {
            try
            {
                paraDict.Add("dataClass", "DzTemplate");
                if (!string.IsNullOrEmpty(this.cboDzType.Value))
                {
                    paraDict.Add("ARRAY_C_TMPL_TYPE", this.cboDzType.Value.Replace("|", ","));
                }
              
            }
            catch (Exception ex)
            {
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getCommonError("ERR-500037", _formFun, status));
                ClsBaseException.DiscardException(ex);
            }

            return paraDict;
        }

        /// <summary>
        /// 下载事件处理
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void btnDownLoad_Click(object sender, EventArgs e)
        {
            try
            {
                if (tbMain.CheckedRows.Count > 0)
                {
                    if (clsInterface == null)
                    {
                        clsInterface = new ClsInterface();
                    }

                    string filePath = clsInterface.folderDialog();

                    if (filePath != null && filePath.Trim().Length > 0)
                    {
                        foreach (Yss.KTable.Models.Row row in tbMain.CheckedRows)
                        {
                            BasePojo basePojo = row.Tag as BasePojo;
                            string fileUrl = this.TemplateService.downLoad(basePojo);
                            if (fileUrl != null)
                            {
                                string fileName = fileUrl.Substring(fileUrl.LastIndexOf("/") + 1);
                                bool bOper = ClsFileTool.DownLoad(fileUrl, fileName, filePath);
                            }
                        }

                        YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtInfo("008", _formFun, status));
                    }
                }
                else
                {
                    YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtInfo("007", _formFun, status));
                }
            }
            catch (Exception ex)
            {
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("009", _formFun, status));
            }

        }

        /// <summary>
        /// 部署 事件 处理
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void btnDeploy_Click(object sender, EventArgs e)
        {
            try
            {
                if (clsInterface == null)
                {
                    clsInterface = new ClsInterface();
                }

                StringBuilder successFileNames = new StringBuilder();
                StringBuilder failFileNames = new StringBuilder();

                string[] arrFile = clsInterface.filesDialog("文件模板", "ZIP类型|*.zip", "");
                if (arrFile != null && arrFile.Length > 0)
                {
                    List<string> expCodeList = this.TemplateService.getDeployTemplate();
                    Frm_DeployMess_S frmDeployMess = new Frm_DeployMess_S();
                    frmDeployMess.table1.Columns.Add(new CheckBoxColumn());
                    frmDeployMess.table1.Columns.Add(new MarkColumn());
                    frmDeployMess.table1.Columns.Add(new Column("模板代码", "模板代码"));
                    frmDeployMess.table1.Columns.Add(new Column("模板名称", "模板名称"));
                    frmDeployMess.table1.Columns.Add(new Column("模板类型", "模板类型"));
                    frmDeployMess.table1.Columns.Add(new Column("版本号", "版本号"));
                    frmDeployMess.table1.Columns.Add(new Column("模板描述", "模板描述"));
                    frmDeployMess.table1.Columns.Add(new Column("备注", "备注"));
                    frmDeployMess.table1.Columns.Add(new Column("部署结果", "部署结果"));
                    Column hideCol = new Column("检查结果", "检查结果");
                    hideCol.Visible = false;
                    frmDeployMess.table1.Columns.Add(hideCol);
                    foreach (string sZipFile in arrFile)
                    {
                        Row subRow = new Row();
                        for (int i = 0; i < frmDeployMess.table1.Columns.Count; i++)
                        {
                            Cell cell = new Cell();
                            subRow.Cells.Add(cell);
                        }

                        ClsZipAdmin.UnZip(sZipFile, "", ClsConstant.PATH_TEMP + Path.GetFileNameWithoutExtension(sZipFile) + "\\");
                        FileInfo deployFile = new FileInfo(ClsConstant.PATH_TEMP + Path.GetFileNameWithoutExtension(sZipFile) + "\\" + DeployUtil.DEPLOY_FILE);

                        FileInfo reademeFile = new FileInfo(ClsConstant.PATH_TEMP + Path.GetFileNameWithoutExtension(sZipFile) + "\\" + ClsReadMe.READ_ME_FILE);
                        if (!deployFile.Exists && !reademeFile.Exists)
                        {
                            subRow.Cells[frmDeployMess.table1.Columns["模板代码"].Index].Text = Path.GetFileName(sZipFile);
                            subRow.Cells[frmDeployMess.table1.Columns["备注"].Index].Text = "请检查所选择配置文件是否正确！";
                            subRow.Cells[frmDeployMess.table1.Columns["检查结果"].Index].Text = "文件错误";
                            frmDeployMess.table1.Rows.Add(subRow);
                            Directory.Delete(ClsConstant.PATH_TEMP + Path.GetFileNameWithoutExtension(sZipFile) + "\\", true);
                            continue;
                        }

                        if (deployFile.Exists)
                        {
                            Deploy deploy = null;
                            try
                            {
                                deploy = DeployUtil.readDeploy(ClsConstant.PATH_TEMP + Path.GetFileNameWithoutExtension(sZipFile) + "\\" + DeployUtil.DEPLOY_FILE);
                                Directory.Delete(ClsConstant.PATH_TEMP + Path.GetFileNameWithoutExtension(sZipFile) + "\\", true);
                            }
                            catch (Exception ex)
                            {
                                subRow.Cells[frmDeployMess.table1.Columns["模板代码"].Index].Text = Path.GetFileName(sZipFile);
                                subRow.Cells[frmDeployMess.table1.Columns["备注"].Index].Text = "请检查所选择配置文件是否正确！";
                                subRow.Cells[frmDeployMess.table1.Columns["检查结果"].Index].Text = "文件错误";
                                frmDeployMess.table1.Rows.Add(subRow);
                                Directory.Delete(ClsConstant.PATH_TEMP + Path.GetFileNameWithoutExtension(sZipFile) + "\\", true);
                                continue;
                            }

                            Info info = deploy.Info;
                            subRow.Tag = sZipFile;
                            subRow.Cells[frmDeployMess.table1.Columns["模板代码"].Index].Text = info.Code;
                            subRow.Cells[frmDeployMess.table1.Columns["模板名称"].Index].Text = info.Name;
                            subRow.Cells[frmDeployMess.table1.Columns["模板类型"].Index].Text = info.Type;
                            subRow.Cells[frmDeployMess.table1.Columns["版本号"].Index].Text = info.Version;
                            subRow.Cells[frmDeployMess.table1.Columns["模板描述"].Index].Text = info.Desc;

                            if (expCodeList.Contains(info.Code))
                            {
                                subRow.ForeColor = ClsConstant.ColorUnAudit;
                                subRow.Cells[frmDeployMess.table1.Columns["备注"].Index].Text = "该模板已部署且已启用 不能重新部署！";
                                subRow.Cells[frmDeployMess.table1.Columns["检查结果"].Index].Text = "已部署";
                                frmDeployMess.table1.Rows.Insert(0, subRow);
                            }
                            else
                            {
                                subRow.Checked = true;
                                subRow.Cells[frmDeployMess.table1.Columns["检查结果"].Index].Text = "未部署";
                                frmDeployMess.table1.Rows.Add(subRow);
                            }
                        }
                    }

                    frmDeployMess.table1.AutoWidth();
                    frmDeployMess.table1.Refresh();
                    frmDeployMess.ShowDialog();
                    if (frmDeployMess.Result != System.Windows.Forms.DialogResult.Yes)
                    {
                        return;
                    }

                    for (int i = 0; i < frmDeployMess.table1.CheckedRows.Count; i++)
                    {
                        Row row = frmDeployMess.table1.CheckedRows[i];
                        if (row.Cells[frmDeployMess.table1.Columns["检查结果"].Index].Text.Equals("未部署") && row.Tag != null)
                        {
                            string cResult = "部署成功";
                            string sZipFile = row.Tag as string;
                            try
                            {
                                //// 文件上传到服务器
                                if (DeployUtil.UpLoad(sZipFile, this.TemplateService))
                                {
                                    this.TemplateService.deploy(Path.GetFileName(sZipFile));
                                }
                            }
                            catch (Exception ex)
                            {
                                cResult = "部署失败";
                            }

                            frmDeployMess.table1.CheckedRows[i].Cells[frmDeployMess.table1.Columns["部署结果"].Index].Text = cResult;
                        }
                    }

                    frmDeployMess.btnOK.Text = "确定";
                    frmDeployMess.btnCancel.Text = "退出";
                    frmDeployMess.ShowDialog();
                    getMainListDataMVC(new BasePojo(), true);
                }
            }
            catch (Exception ex)
            {
                YssMessageBox.ShowCommonInfo(ex.Message);
            }
        }

        /// <summary>
        /// 卸载 事件 处理
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void btnUnDeploy_Click(object sender, EventArgs e)
        {
            try
            {
                if (tbMain.CheckedRows.Count > 0)
                {
                    ClsRetInfo retInfo = new ClsRetInfo();
                    retInfo.infoContent = "确认要卸载？";
                    retInfo.infoType = ClsConstant.INFO_TP_HINT;
                    retInfo.infoGroup = ClsConstant.INFO_GRP_SYS;
                    retInfo.buttonGroup = MessageBoxButtons.YesNo;
                    retInfo.infoTitle = "提示";
                    retInfo.icon = MessageBoxIcon.Question;
                    retInfo.terminalType = ClsConstant.TERMINAL_CLIENT;
                    MessageDialog msgDialog = new MessageDialog();
                    if (msgDialog.Show(retInfo.infoContent, retInfo.infoTitle, retInfo.buttonGroup, retInfo.icon, retInfo.detailInfo) == DialogResult.No)
                    {
                        return;
                    }

                    List<BasePojo> templateList = new List<BasePojo>();
                    foreach (Yss.KTable.Models.Row row in tbMain.CheckedRows)
                    {
                        DzTemplate template = row.Tag as DzTemplate;
                        if (!template.C_DV_TMPL_STATUS.Equals(TEMP_MODE_USED))
                        {
                            templateList.Add(template);
                        }
                    }

                    if (templateList.Count > 0)
                    {
                        string cResult = this.TemplateService.unDeploy(templateList);

                        if (bool.Parse(cResult))
                        {
                            YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtInfo("012", _formFun, status));
                            getMainListDataMVC(new BasePojo(), true);
                        }
                        else
                        {
                            YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtInfo("013", _formFun, status));
                        }
                    }
                    else
                    {
                        YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("004", _formFun, status));
                    }
                }
                else
                {
                    YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtInfo("007", _formFun, status));
                }
            }
            catch (Exception ex)
            {
                YssMessageBox.ShowCommonInfo(ex.Message);
            }
        }

        /// <summary>
        /// 启用 事件 处理
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void btnUsed_Click(object sender, EventArgs e)
        {
            try
            {
                if (tbMain.CheckedRows.Count > 0)
                {
                    List<BasePojo> templateList = new List<BasePojo>();

                    foreach (Yss.KTable.Models.Row row in tbMain.CheckedRows)
                    {
                        DzTemplate template = row.Tag as DzTemplate;

                        /* 选中的模板中是否有 没有被启用的模板 */
                        if (template.C_DV_TMPL_STATUS != TEMP_MODE_USED)
                        {
                            template.C_DV_TMPL_STATUS = TEMP_MODE_USED;
                            templateList.Add(template);
                        }
                    }

                    /* count若为0表明没有需要启用的模板 */
                    if (templateList.Count > 0)
                    {
                        this.TemplateService.updateStatus(templateList);

                        getMainListDataMVC(new BasePojo(), true);
                    }
                    else
                    {
                        YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtInfo("005", _formFun, status));
                    }

                }
                else
                {
                    YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtInfo("007", _formFun, status));
                }
            }
            catch (Exception ex)
            {
                YssMessageBox.ShowCommonInfo(ex.Message);
            }
        }

        /// <summary>
        /// 停用 事件 处理
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void btnUnUsed_Click(object sender, EventArgs e)
        {
            try
            {
                if (tbMain.CheckedRows.Count > 0)
                {
                    List<BasePojo> templateList = new List<BasePojo>();

                    foreach (Yss.KTable.Models.Row row in tbMain.CheckedRows)
                    {
                        DzTemplate template = row.Tag as DzTemplate;

                        /* 选中的模板中是否有 没有被启用的模板 */
                        if (template.C_DV_TMPL_STATUS != TEMP_MODE_UNUSED)
                        {
                            template.C_DV_TMPL_STATUS = TEMP_MODE_UNUSED;
                            templateList.Add(template);
                        }
                    }

                    if (templateList.Count > 0)
                    {
                        this.TemplateService.updateStatus(templateList);

                        getMainListDataMVC(new BasePojo(), true);
                    }
                    else
                    {
                        YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtInfo("006", _formFun, status));
                    }

                }
                else
                {
                    YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtInfo("007", _formFun, status));
                }
            }
            catch (Exception ex)
            {
                YssMessageBox.ShowCommonInfo(ex.Message);
            }
        }

        /// <summary>
        /// 装载明细窗体功能代码列表
        /// </summary>
        /// <returns>返回明细窗体功能代码列表</returns>
        protected override List<SysFun> LoadDetailFormFuns()
        {
            List<SysFun> sysFuns = new List<SysFun>();

            //// 电子对账模板关联
            SysFun newFun = new SysFun();
            newFun.C_FUN_CODE = "dzTmplRela";
            sysFuns.Add(newFun);

            return sysFuns;
        }

        /// <summary>
        /// 屏蔽双击事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void tbMain_RowDoubleClicked(object sender, Yss.KTable.Events.RowEventArgs e)
        {
        }
    }
}
