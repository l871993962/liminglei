using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using FAST.Core.BaseForm;
using YssInformation.Support.Sys.automaticSet.Service;
using Yss.KRichEx.AutoFilter.Events;
using FAST.Core.Communication.Service;
using FAST.Common.Service.Dict.Pojo;
using Yss.KRichEx.AutoFilter.Model;
using Yss.KTable.Models;
using Yss.KMessage;
using FAST.Core.BaseControl.Fun;
using Yss.KRichEx;
using YssProductInfo.Support.Ab.Port.Service;
using FAST.Common.Service.Pojo;
using FAST.Common.Service.Pojo.Base;
using YssInformation.Support.Sys.automaticSet.Pojo;
using FAST.Platform.DataIntegration.Imp.Service;
using FAST.Core.BaseControl;
using FAST.Platform.DataIntegration.Imp.Pojos;
using Yss.KTable.Collections;
using FAST.Core.Context;
using FAST.Core.Util;
using Yss.Forms;
using Yss.KTable.Events;


namespace YssInformation.Sys.automaticSet.Form
{
    public partial class Frm_AUTOMATIC_SET_INSERT_S : FrmTabItemSet
    {
        /// <summary>
        /// 业务类型服务接口
        /// </summary>
        private IAutomaticSetPathService automaticSetPathService;

        private IAutomaticSetService automaticSetService;

        private IPortService portService;
        /// <summary>
        /// 接口路径下拉框
        /// </summary>
        private YssSelCombox selInterPath;

        /// <summary>
        /// 接口名称
        /// </summary>
        private YssTextBox txtInterfaceCode;

        /// <summary>
        /// 接口路径
        /// </summary>
        private YssTextBox txtInterfacePath;

        /// <summary>
        /// 参照组合
        /// </summary>
        private string rePortCode = null;

        /// <summary>
        /// 单元格事件
        /// </summary>
        private Yss.KTable.Events.CellEventArgs tempCellEvent;

        /// <summary>
        /// 修改或则复制的时候下拉框不同步的标记
        /// </summary>
        private bool tagEditOrCope = true;


        public Frm_AUTOMATIC_SET_INSERT_S()
        {
            InitializeComponent();
            this.rtbMain.Table.AllowResizeColumn = true;
            this.rtbMain.Table.Border.Top = false;
            this.rtbMain.Table.Border.Bottom = false;
            this.rtbMain.Table.Border.Left = false;
            this.rtbMain.Table.DefaultToolStripItems = Yss.KTable.Enums.SysToolStripItems.None;
            this.rtbMain.Table.SelectionMode = SelectionMode.One;
            this.rtbMain.Table.CellMouseClick += this.rtbMain_ClieCellChanged;
            this.bUseMVCService = true;
            InitializeColumns();
            initServiceMVC();
            initTextBox();
        }


        /// <summary>
        /// 列表下拉框
        /// </summary>
        private YssSelCombox ProcessListDropDown
        {
            get
            {
                this.selInterPath = new YssSelCombox();
                this.selInterPath.AutoTooltip = true;
                this.selInterPath.BeforeDropDownClick += new Yss.KRichEx.AutoFilter.Events.DropDownEventHandler(selInterPath_BeforeDropDownClick);
                this.selInterPath.SelectedValueChanged += new EventHandler(processListDropdown_SelectedValueChanged);
                this.selInterPath.ShowCheckBox = true;
                this.selInterPath.DefaultValue = "C_Path";
                this.selInterPath.DisplayName = "C_Path";
                this.selInterPath.DisplayValue = "C_Path";
                this.selInterPath.MethodInfo = null;
                this.selInterPath.Name = "selInterPath";
                this.selInterPath.Parameter = "C_Path~C_Path_Type";
                this.selInterPath.NodeID = "C_Path";
                this.selInterPath.SortColumn = "C_Path";
                this.selInterPath.PrefixBackColor = System.Drawing.Color.White;
                this.selInterPath.TabIndex = 26;
                this.selInterPath.Visible = true;
                this.selInterPath.YssAssociaType = ((FAST.Core.Context.Associa)(FAST.Core.Context.AssociaFAST.NULL));
                this.selInterPath.TextAlign = System.Windows.Forms.HorizontalAlignment.Left;

                return this.selInterPath;
            }
        }

        /// <summary>
        /// 接口代码下拉框取值
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void selInterPath_BeforeDropDownClick(Object sender, DropDownEventArgs e)
        {
            e.Cancel = true;
            if (this.selInterPath.Items.Count > 0)
            {
                return;
            }
            //获取存在cell中的分组代码和接口代码
            string[] keys = tempCellEvent.Row.Cells[tempCellEvent.Cell.ColumnIndex].Key.Split('#');
            string parentId = keys[0];
            string interfaceCode = keys[1];

            IImpCfgGroupDataService importDataService = ServiceFactory.createService<IImpCfgGroupDataService>();
            ImpCfgGroup impCfgGroup = importDataService.getCfgGroupInfo(parentId, interfaceCode);

            if (impCfgGroup.LstPath != null)
            {
                foreach (ImpCfgGroupPath pathData in impCfgGroup.LstPath)
                {
                    if ("PM_SERVER".Equals(pathData.C_Path_Type))
                    {
                        KTableEntity entity = new KTableEntity(pathData);
                        this.selInterPath.Items.Add(entity);
                    }
                }
            }
        }




        /// <summary>
        /// 点击接口路径初始化下拉框事件
        /// </summary>
        /// <param name="sender">事件源</param>
        /// <param name="e">事件参数</param>  
        private void rtbMain_ClieCellChanged(object sender, Yss.KTable.Events.CellEventArgs e)
        {
            tempCellEvent = e;
            if (e.Cell.RelColumn.DataPropertyName != "txtParentCode" && e.Cell.RelColumn.DataPropertyName != "txtInterfaceCode" && e.Cell.RelColumn.DataPropertyName != "txtInterfacePath")
            {
                return;
            }
            if (e.Cell.RelColumn.DataPropertyName == "txtInterfacePath" && !"CZZH_ZD".Equals(this.selRePortCode.Tag as string))
            {
                if (e.Cell.Selected)
                {
                    //先记录下单元格文本，避免给InnerControl赋值时，获取不到真实的文本值。
                    string lcCellText = e.Cell.Text;
                    ////选中，把控件放进去
                    e.Cell.InnerControl = this.ProcessListDropDown;

                    //先赋值，方便后面抛异常
                    if (this.selInterPath.Items.Count > 0)
                    {
                        return;
                    }
                    //获取存在cell中的分组代码和接口代码
                    string[] keys = tempCellEvent.Row.Cells[tempCellEvent.Cell.ColumnIndex].Key.Split('#');
                    string parentId = keys[0];
                    string interfaceCode = keys[1];

                    IImpCfgGroupDataService importDataService = ServiceFactory.createService<IImpCfgGroupDataService>();
                    ImpCfgGroup impCfgGroup = importDataService.getCfgGroupInfo(parentId, interfaceCode);

                    if (impCfgGroup.LstPath != null)
                    {
                        foreach (ImpCfgGroupPath pathData in impCfgGroup.LstPath)
                        {
                            if ("PM_SERVER".Equals(pathData.C_Path_Type))
                            {
                                KTableEntity entity = new KTableEntity(pathData);
                                this.selInterPath.Items.Add(entity);
                            }
                        }
                    }

                    if (e.Cell.InnerControl != null)
                    {
                        ////初始化控件文本
                        (e.Cell.InnerControl as YssSelCombox).Text = lcCellText;
                        (e.Cell.InnerControl as YssSelCombox).Value = lcCellText;
                    }
                }
                else if ("1".Equals(sender as string) || "2".Equals(sender as string))
                {
                    ////同步的接口行本身没有对应的路径，则报异常
                    e.Cell.InnerControl = this.ProcessListDropDown;
                    if (e.Cell.InnerControl != null)
                    {
                        ////初始化控件报错文本
                        string lcCellText = "InputError!";
                        this.selInterPath.Tag = "synchronous";//同步接口路径的标识，防止同步路径的时候多次弹窗提示
                        (e.Cell.InnerControl as YssSelCombox).Text = "";
                        (e.Cell.InnerControl as YssSelCombox).Value = "";

                        //先赋值，方便后面抛异常
                        if (this.selInterPath.Items.Count > 0)
                        {
                            return;
                        }
                        //获取存在cell中的分组代码和接口代码
                        string[] keys = tempCellEvent.Row.Cells[tempCellEvent.Cell.ColumnIndex].Key.Split('#');
                        string parentId = keys[0];
                        string interfaceCode = keys[1];

                        IImpCfgGroupDataService importDataService = ServiceFactory.createService<IImpCfgGroupDataService>();
                        ImpCfgGroup impCfgGroup = importDataService.getCfgGroupInfo(parentId, interfaceCode);

                        if (impCfgGroup.LstPath != null)
                        {
                            foreach (ImpCfgGroupPath pathData in impCfgGroup.LstPath)
                            {
                                if ("PM_SERVER".Equals(pathData.C_Path_Type))
                                {
                                    KTableEntity entity = new KTableEntity(pathData);
                                    this.selInterPath.Items.Add(entity);
                                }
                            }
                        }

                         //抛异常
                        if ("1".Equals(sender as string))
                        {
                            (e.Cell.InnerControl as YssSelCombox).Text = lcCellText;
                            (e.Cell.InnerControl as YssSelCombox).InputError = true;
                        }
                   }
                }
            }
        }

        /// <summary>
        /// 接口路径下拉框选择改变事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void processListDropdown_SelectedValueChanged(object sender, EventArgs e)
        {
            //同步接口路径
            if (!("synchronous").Equals(this.selInterPath.Tag) && !tagEditOrCope)
            {
                //非复制或则修改才弹窗
                DialogResult res = YssMessageBox.ShowQuestion("接口路径，是否同步到其他接口？如果其他接口中无对应的路径，则可能会报异常");
                if (DialogResult.Yes == res)
                {
                    if (this.rtbMain.Table.Rows.Count > 0)
                    {
                        for (int i = 0; i < this.rtbMain.Table.Rows.Count; i++)
                        {
                            Dictionary<string, string> paraDict = new Dictionary<string, string>();

                            Row row = this.rtbMain.Table.Rows[i];
                            cell3 = row.Cells[3];

                            CellEventArgs cellEvent = new CellEventArgs(row, cell3);
                            if (cell3 != null && cell3.Key != null)
                            {
                                //同步前，先检查同步的接口行有没有对应的路径，没有则在下拉框中报异常，显示为："InputError!",有则进行同步
                                string[] keys = cell3.Key.Split('#');
                                string parentId = keys[0];
                                string interfaceCode = keys[1];

                                IImpCfgGroupDataService importDataService = ServiceFactory.createService<IImpCfgGroupDataService>();
                                ImpCfgGroup impCfgGroup = importDataService.getCfgGroupInfo(parentId, interfaceCode);

                                //下拉框中的路径
                                string paths = (sender as YssSelCombox).Text;
                                //接口路径可能是多条，根据"|"切割接口路径
                                string[] arrPath = paths.Split('|');

                                //查询是否包含路径
                                if (impCfgGroup.LstPath != null && !string.IsNullOrEmpty(paths))
                                {
                                    //将所有接口代码和分组代码对应的接口路径放入一个集合中
                                    List<string> pathList = new List<string>();

                                    foreach (ImpCfgGroupPath groupPath in impCfgGroup.LstPath)
                                    {
                                        if (!string.IsNullOrEmpty(groupPath.C_Path))
                                        {
                                            if ("PM_SERVER".Equals(groupPath.C_Path_Type))
                                            {
                                                pathList.Add(groupPath.C_Path);
                                            }
                                        }
                                    }

                                    //定义标识,是否跳出外层循环
                                    bool tag = false;
                                    //判断接口本身对应的路径是否包含同步路径
                                    foreach (string path in arrPath)
                                    {
                                        if (!pathList.Contains(path))
                                        {
                                            this.rtbMain_ClieCellChanged("1", cellEvent);
                                            tag = true;
                                            break;
                                        }
                                    }

                                    //路径没完全匹配上，跳出本次循环
                                    if (tag)
                                    {
                                        continue;
                                    }
                                }
                                else if (impCfgGroup.LstPath == null)
                                {
                                    this.rtbMain_ClieCellChanged("1", cellEvent);
                                    continue;
                                }
                                //同步接口路径，对其他cell赋值
                                if (cell3.InnerControl == null)
                                {
                                    this.rtbMain_ClieCellChanged("2", cellEvent);
                                    cell3.Text = paths;
                                    cell3.Tag = (tempCellEvent.Cell.InnerControl as YssSelCombox).CheckedItems;
                                    (row.Cells[3].InnerControl as YssSelCombox).Text = paths;
                                    (row.Cells[3].InnerControl as YssSelCombox).Value = paths;
                                }
                                else
                                {
                                    //原本身有的路径
                                    cell3.Text = paths;
                                    cell3.Tag = (tempCellEvent.Cell.InnerControl as YssSelCombox).CheckedItems;
                                    this.selInterPath.Tag = "synchronous";
                                    (row.Cells[3].InnerControl as YssSelCombox).Text = paths;
                                    (row.Cells[3].InnerControl as YssSelCombox).Value = paths;
                                }
                            }
                        }
                        //遍历完成之后的标记
                        this.selInterPath.Tag = "end";
                    }
                }
            }
        }

        /// <summary>
        /// 外部渠道类型下拉框取值    
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void selChanelType_BeforeDropDownClick(object sender, DropDownEventArgs e)
        {
            e.Cancel = true;
            if (e.Items.Count > 0)
            {
                return;
            }
            IAutomaticSetService automaticSetService = ServiceFactory.createService<IAutomaticSetService>();
            List<Vocabulary> businessTypeVoc = automaticSetService.getDataListByType("WBQD_TYPE");
            foreach (Vocabulary voc in businessTypeVoc)
            {
                KTableEntity entity = new KTableEntity(voc);
                this.selChanelType.Items.Add(entity);
            }
        }

        /// <summary>
        /// 组合代码下拉框取值    
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void selPortCode_BeforeDropDownClick(object sender, DropDownEventArgs e)
        {
            e.Cancel = true;
            if (e.Items.Count > 0)
            {
                return;
            }
            Dictionary<string, string> paraDict = new Dictionary<string, string>();
            IPortDataService portService = ServiceFactory.createService<IPortDataService>();
            List<BasePojo> list = portService.getPortListByDatClass("");
            if (list != null && list.Count > 0)
            {
                foreach (BasePojo item in list)
                {
                    Port port = new Port();
                    port = (Port)item.Clone();
                    KTableEntity entity = new KTableEntity(port);
                    this.selPortCode.Items.Add(entity);
                }
            }
        }

        /// <summary>
        /// 参照组合下拉框取值    
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void selRePortCode_BeforeDropDownClick(object sender, DropDownEventArgs e)
        {
            e.Cancel = true;
            if (e.Items.Count > 0)
            {
                return;
            }
            List<AutomaticSetPojo> list = automaticSetPathService.getRePortCodeList();
            foreach (AutomaticSetPojo pojo in list)
            {
                KTableEntity entity = new KTableEntity(pojo);
                this.selRePortCode.Items.Add(entity);
            }         
        }

        /// <summary>
        /// 参照组合下拉框改变值
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void selRePortCode_SelectedValueChanged(object sender, EventArgs e)
        {
            this.rePortCode = this.selRePortCode.Value;
            string portCode = " ";
            if (rePortCode != null)
            {
                portCode = this.rePortCode;
            }
            //获取参照组合对应数据库的所有数据
            List<BasePojo> dataList = automaticSetPathService.queryByCodeAndName(portCode, new List<string>());
            this.selChanelType.Tag = "CZZH"; //此标识为辨别数据加载的标识，有值则是参照组合同步数据，没值就是普通新增
            this.showInfoMVC(dataList);
        }

        /// <summary>
        /// 产品业务分类下拉框取值    
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void selBuessType_BeforeDropDownClick(object sender, DropDownEventArgs e)
        {
            e.Cancel = true;
            if (e.Items.Count > 0)
            {
                return;
            }

            List<string> nameSet = new List<string>();
            IAutomaticSetPathService automaticSetPathService = ServiceFactory.createService<IAutomaticSetPathService>();
            List<AutomaticSetPojo> productTypeVoc = automaticSetPathService.getProductType();
            foreach (AutomaticSetPojo voc in productTypeVoc)
            {
                string name = voc.C_PRODUCT_NAME;
                if (!nameSet.Contains(name))
                {
                    KTableEntity entity = new KTableEntity(voc);
                    this.selBuessType.Items.Add(entity);
                    nameSet.Add(name);
                }
            }
            nameSet.Clear();
        }


        /// <summary>
        /// 产品业务分类下拉框改变值加载接口窗口
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void selBuessType_SelectedValueChanged(object sender, EventArgs e)
        {
            //如果是由于选择参照组合，加载默认事件期间触发的改变下拉框值事件，不做处理
            if ("CZZH".Equals(this.selChanelType.Tag))
            {
                return;
            }

            //如果参照组合下拉框没值，就还是之前的产品业务分类加载接口窗口的原逻辑
            if (string.IsNullOrEmpty(this.selRePortCode.Value))
            {
                this.selRePortCode.Tag = "";   //不再设置接口路径为只读
                this.selBuessType.NodeID = "";//不再直接加载路径标识
            }

            //获取所有产品业务分类
            List<string> nameSet = new List<string>();
            string productName = this.selBuessType.Text;
            string[] name = productName.Split('|');
            ///遍历产品分类
            foreach (string voc in name)
            {
                nameSet.Add(voc);
            }

            //参照组合的情况下的下拉框改变值加载数据
            if ("CZZH_ZD".Equals(this.selRePortCode.Tag as string))
            {
                //通过组合代码和产品业务分类获取数据
                List<BasePojo> dataList = automaticSetPathService.queryByCodeAndName(this.selRePortCode.Value, nameSet);

                this.selChanelType.Tag = "CZZH"; //此标识为辨别数据加载的标识，有值则是参照组合同步数据，没值就是普通新增
                this.showInfoMVC(dataList);
            }
            else
            {
                List<FAST.Platform.DataIntegration.Imp.Pojos.ImpCfgGroup> productTypeVoc = automaticSetPathService.getInterfaceData(nameSet);
                List<BasePojo> list = new List<BasePojo>();
                foreach (ImpCfgGroup impCfgGroup in productTypeVoc)
                {
                    BasePojo basePojo = (BasePojo)impCfgGroup;
                    list.Add(basePojo);
                }
                this.showInfoMVC(list);
            }   
        }


        /// <summary>
        /// 初始化列
        /// </summary>
        private void InitializeColumns()
        {
            Column newColumn = new MarkColumn();
            this.rtbMain.Table.Columns.Add(newColumn);
            newColumn = new Column();
            newColumn.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Center;
            newColumn.CellTextAlign = ContentAlignment.MiddleLeft;
            newColumn.DataPropertyName = "txtParentCode";
            newColumn.Text = "接口父级名称";
            newColumn.Width = 150;

            this.rtbMain.Table.Columns.Add(newColumn);
            newColumn = new Column();
            newColumn.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Center;
            newColumn.CellTextAlign = ContentAlignment.MiddleLeft;
            newColumn.DataPropertyName = "txtInterfaceCode";
            newColumn.Text = "接口名称";
            newColumn.Width = 200;
            this.rtbMain.Table.Columns.Add(newColumn);

            newColumn = new Column();
            newColumn.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Center;
            newColumn.CellTextAlign = ContentAlignment.MiddleLeft;
            newColumn.DataPropertyName = "txtInterfacePath";
            newColumn.Text = "接口路径";
            newColumn.Width = 250;
            this.rtbMain.Table.Columns.Add(newColumn);
        }

        /// <summary>
        /// 初始化服务
        /// </summary>
        protected override void initServiceMVC()
        {
            if (automaticSetService == null)
            {
                automaticSetService = ServiceFactory.createService<IAutomaticSetService>();
                automaticSetPathService = ServiceFactory.createService<IAutomaticSetPathService>();
            }
        }

        /// <summary>
        /// 控件初始化
        /// </summary>
        private void initTextBox()
        {
            ////TextBox控件
            this.txtInterfaceCode = new YssTextBox();
            this.txtInterfacePath = new YssTextBox();
        }

        /// <summary>
        /// 加载保存按钮
        /// </summary>
        /// <param name="e">参数</param>
        protected override void OnLoad(EventArgs e)
        {
            this.btnBar.setAllButtonVisibled(false);
            this.btnBar.setButtonVisibled(ClsButtonName.BtnSave);
            this.btnBar.setButtonEnabled(ClsButtonName.BtnSave);
        }

        /// <summary>
        /// 保存按钮点击事件
        /// </summary>
        /// <param name="sender">事件源</param>
        /// <param name="e">事件参数</param>
        protected override void btnSave_Click(object sender, EventArgs e)
        {
            this.btnBar.setButtonDisabled(ClsButtonName.BtnSave);
            MessageDialog dlg = new MessageDialog();
            try
            {
                List<Dictionary<string, string>> paraList = new List<Dictionary<string, string>>();
                List<string> proList = new List<string>();
                //组合代码
                if (string.IsNullOrEmpty(this.selPortCode.Value))
                {
                    throw new Exception("组合代码不能为空");
                }
                //产品业务分类
                if (string.IsNullOrEmpty(this.selBuessType.Text))
                {
                    throw new Exception("产品业务分类不能为空");
                }
                if (this.rtbMain.Table.Rows.Count > 0)
                {
                    for (int i = 0; i < this.rtbMain.Table.Rows.Count; i++)
                    {
                        Dictionary<string, string> paraDict = new Dictionary<string, string>();
                        Row row = this.rtbMain.Table.Rows[i];
                        Cell cell = row.Cells[1];
                        if (cell != null)
                        {
                            if (cell.Tag != null)
                            {
                                string groupName = cell.Tag as string; ///接口分组
                                paraDict.Add("C_INTERFACE_GROUP", groupName);
                            }
                            if (cell.Key != null)
                            {
                                paraDict.Add("C_PRODUCT_NAME", cell.Key); //产品业务分类
                                proList.Add(cell.Key); //产品业务分类作为另外一个参数传到后端，方便新增前删除数据
                            }
                        }

                        cell = row.Cells[2];
                        if (cell != null)
                        {
                            if (cell.Text != null)
                            {
                                paraDict.Add("C_INTERFACE_CODE", cell.Tag as string);
                                paraDict.Add("C_INTERFACE_NAME", cell.Key as string);
                            }
                        }

                        paraDict.Add("C_CHANEL_CODE", this.selChanelType.Value);
                        paraDict.Add("C_CHANEL_TYPE", this.selChanelType.Text);

                        cell3 = row.Cells[3];
                        if (cell3 != null)
                        {
                            //保存接口路径前，先判断是否有InputError!的报错
                            if ("InputError!".Equals(cell3.Text))
                            {
                                throw new Exception("有接口路径选择异常！请先处理再保存");
                            }

                            if (cell3.Text != null)
                            {
                                string[] allPath = cell3.Text.Split('|');

                                foreach (string path in allPath)
                                {
                                    if (paraDict.ContainsKey("C_INTERFACE_PATH"))
                                    {
                                        paraDict["C_INTERFACE_PATH"] = path; //接口路径
                                    }
                                    else
                                    {
                                        paraDict.Add("C_INTERFACE_PATH", path); //接口路径
                                    }
                                    foreach (string portCode in this.selPortCode.Value.Split('|'))
                                    {
                                        if (paraDict.ContainsKey("C_PORT_CODE"))
                                        {
                                            paraDict["C_PORT_CODE"] = portCode;
                                        }
                                        else
                                        {
                                            paraDict.Add("C_PORT_CODE", portCode);
                                        }
                                        paraList.Add(new Dictionary<string, string>(paraDict));
                                    }
                                }
                            }
                            else
                            {
                                foreach (string portCode in this.selPortCode.Value.Split('|'))
                                {
                                    if (paraDict.ContainsKey("C_PORT_CODE"))
                                    {
                                        paraDict["C_PORT_CODE"] = portCode;
                                    }
                                    else
                                    {
                                        paraDict.Add("C_PORT_CODE", portCode);
                                    }
                                    paraList.Add(new Dictionary<string, string>(paraDict));
                                }
                            }
                        }
                    }
                }

                if (paraList.Count == 0)
                {
                    return;
                }

                //保存前，先删除原数据 (true则是修改，先删除原数据，false则是新增)
                if (!string.IsNullOrEmpty(this.selChanelType.NodeID))
                {
                    List<AutomaticSetPojo> list = new List<AutomaticSetPojo>();
                    AutomaticSetPojo automaticSetPojo = new AutomaticSetPojo();
                    automaticSetPojo.Id = this.selChanelType.NodeID;
                    list.Add(automaticSetPojo);
                    automaticSetPathService.deleteById(list);
                    this.selChanelType.NodeID = "";
                }

                //复制
                if (ClsEnums.StatusSetting.YssCopy == this.status)
                {
                    bool res = automaticSetPathService.saveDataList(proList, paraList);
                    if (res)
                    {
                        dlg.Show("复制成功", "提示", MessageBoxButtons.OK, MessageBoxIcon.Information);
                    }
                    else
                    {
                        dlg.Show("复制失败", "提示", MessageBoxButtons.OK, MessageBoxIcon.Information);
                    }
                }
                else
                {
                    bool ret = automaticSetPathService.saveDataList(proList, paraList);

                    if (ret)
                    {
                        dlg.Show("保存成功", "提示", MessageBoxButtons.OK, MessageBoxIcon.Information);
                    }
                    else
                    {
                        dlg.Show("保存失败", "提示", MessageBoxButtons.OK, MessageBoxIcon.Information);
                    }
                }
            }
            catch (Exception ex)
            {
                dlg.Show(ex.Message, "错误", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
            finally
            {
                this.btnBar.setButtonEnabled(ClsButtonName.BtnSave);
            }
        }

        /// <summary>   
        /// <summary>
        /// 展示对象属性到窗体
        /// </summary>
        /// <param name="pojo">Pojo</param>
        private void showInfoMVC(List<BasePojo> businessTypeVoc)
        {
            foreach (Row row in this.rtbMain.Table.Rows)
            {
                foreach (Cell cell in row.Cells)
                {
                    cell.InnerControl = null;
                }
            }
            this.rtbMain.Table.Rows.Clear();

            if ("CZZH".Equals(this.selChanelType.Tag))
            {
                this.selBuessType.Value = "";
                //选择参照组合时加载数据
                foreach (BasePojo basePojo in businessTypeVoc)
                {
                    AutomaticSetPojo automaticSetPojo = (AutomaticSetPojo)basePojo;

                    //下拉框赋值
                    this.selBuessType.NodeID = automaticSetPojo.C_INTERFACE_PATH;    //作为接口路径的标记,现在NodeID有值就是加载显示接口路径
                    this.selRePortCode.Tag = "CZZH_ZD";  //作为参照组合加载数据时候的标识，此时第三列的接口路径为只读模式，鼠标点击不会加载下拉框
                   
                    //下拉框不包含的时候才添加
                    if (!this.selBuessType.Text.Contains(automaticSetPojo.C_PRODUCT_NAME))
                    {
                        this.selBuessType.Text += automaticSetPojo.C_PRODUCT_NAME + "|";
                        this.selBuessType.Value += automaticSetPojo.C_PRODUCT_NAME + "|";
                    }
                    Row row = new Row();
                    row.Cells.Add(new Cell());
                    Cell codeCell = new Cell();
                    codeCell.Text = automaticSetPojo.C_INTERFACE_P_ID + "   " + automaticSetPojo.C_INTERFACE_GROUP;
                    codeCell.Tag = "\\" + automaticSetPojo.C_INTERFACE_P_ID + "\\";
                    codeCell.Key = automaticSetPojo.C_PRODUCT_NAME; //此处是每个接口数据对应的产品业务分类
                    row.Cells.Add(codeCell);
                    Cell nameCell = new Cell();
                    nameCell.Text = automaticSetPojo.C_INTERFACE_CODE + "   " + automaticSetPojo.C_INTERFACE_NAME;
                    nameCell.Tag = automaticSetPojo.C_INTERFACE_CODE;  ///接口代码
                    nameCell.Key = automaticSetPojo.C_INTERFACE_NAME;  ///接口名称
                    row.Cells.Add(nameCell);
                    Cell pathCell = new Cell();
                    pathCell.Text = automaticSetPojo.C_INTERFACE_PATH;
                    pathCell.Key = automaticSetPojo.C_INTERFACE_P_ID + "#" + automaticSetPojo.C_INTERFACE_CODE;  ///分组代码#接口代码
                    List<ControlEntity> list = new List<ControlEntity>();
                    ImpCfgGroupPath importOperItem = new ImpCfgGroupPath();
                    importOperItem.C_Path = automaticSetPojo.C_INTERFACE_PATH;
                    KTableEntity entity = new KTableEntity(importOperItem);
                    list.Add(entity);
                    pathCell.Tag = list;
                    row.Cells.Add(pathCell);
                    tagEditOrCope = true; //复制或则修改下拉框不弹窗
                    this.rtbMain.Table.Rows.Add(row);
                }
                this.rtbMain.Refresh();
                //赋值完，去除标识
                if ("CZZH".Equals(this.selChanelType.Tag))
                {
                    this.selChanelType.Tag = "";
                }               
            }
            else
            {
                //普通新增加载数据
                foreach (BasePojo basePojo in businessTypeVoc)
                {
                    ImpCfgGroup voc = (ImpCfgGroup)basePojo;
                    Row row = new Row();
                    row.Cells.Add(new Cell());
                    Cell codeCell = new Cell();
                    codeCell.Text = voc.C_Group_Code_P + "   " + voc.C_Group_Name;
                    codeCell.Tag = "\\" + voc.C_Group_Code_P + "\\";
                    codeCell.Key = voc.C_HDay_Code; //此处是借用fast的pojo节假日字段获取每个接口数据对应的产品业务分类
                    row.Cells.Add(codeCell);
                    Cell nameCell = new Cell();
                    nameCell.Text = voc.C_Cfg_Code + "   " + voc.C_Cfg_Name;
                    nameCell.Tag = voc.C_Cfg_Code;  ///接口代码
                    nameCell.Key = voc.C_Cfg_Name;  ///接口名称
                    row.Cells.Add(nameCell);
                    Cell pathCell = new Cell();

                    //有值则是修改的时候加载，否则就是新增的时候加载 
                    if (!string.IsNullOrEmpty(this.selBuessType.NodeID as string))
                    {
                        pathCell.Text = this.selBuessType.NodeID;
                        this.selBuessType.NodeID = "";
                    }
                    else
                    {
                        pathCell.Text = "";
                    }
                    pathCell.Key = voc.C_Group_Code_P + "#" + voc.C_Cfg_Code;  ///分组代码#接口代码
                    row.Cells.Add(pathCell);
                    tagEditOrCope = false;
                    this.rtbMain.Table.Rows.Add(row);                 
                }
                this.rtbMain.Refresh();
            }
        }



        /// <summary>
        /// 展示对象属性到窗体 （修改的时候加载默认数据）
        /// </summary>
        /// <param name="pojo">Pojo</param>
        public override void showInfoMVC(FAST.Common.Service.Pojo.Base.BasePojo pojo)
        {
            AutomaticSetPojo automaticSetPojo = (AutomaticSetPojo)pojo;

            //下拉框赋值
            this.selChanelType.Text = automaticSetPojo.C_CHANEL_TYPE;
            this.selChanelType.Value = automaticSetPojo.C_CHANEL_CODE;
            this.selChanelType.NodeID = automaticSetPojo.Id; //将原数据的唯一标识，存到下拉框中，之后保存的时候借此id，先删除再保存
            this.selPortCode.Value = automaticSetPojo.C_PORT_CODE;
            this.selBuessType.NodeID = automaticSetPojo.C_INTERFACE_PATH;    //作为接口路径的标记
            this.selBuessType.Value = automaticSetPojo.C_PRODUCT_NAME;
            this.selBuessType.Text = automaticSetPojo.C_PRODUCT_NAME;

            IImpCfgGroupDataService importDataService = ServiceFactory.createService<IImpCfgGroupDataService>();
            ImpCfgGroup impCfgGroup = importDataService.getCfgGroupInfo(automaticSetPojo.C_INTERFACE_GROUP.TrimStart('\\').TrimEnd('\\'), automaticSetPojo.C_INTERFACE_CODE);

            this.rtbMain.Table.Rows.Clear();
            Row row = new Row();
            row.Cells.Add(new Cell());
            Cell codeCell = new Cell();
            codeCell.Text = impCfgGroup.C_Group_Code_P + "   " + impCfgGroup.C_Group_Name;
            codeCell.Tag = "\\" + impCfgGroup.C_Group_Code_P + "\\";
            codeCell.Key = automaticSetPojo.C_PRODUCT_NAME; //此处是每个接口数据对应的产品业务分类
            row.Cells.Add(codeCell);
            Cell nameCell = new Cell();
            nameCell.Text = impCfgGroup.C_Cfg_Code + "   " + impCfgGroup.C_Cfg_Name;
            nameCell.Tag = impCfgGroup.C_Cfg_Code;  ///接口代码
            nameCell.Key = impCfgGroup.C_Cfg_Name;  ///接口名称
            row.Cells.Add(nameCell);
            Cell pathCell = new Cell();
            pathCell.Text = automaticSetPojo.C_INTERFACE_PATH;
            pathCell.Key = impCfgGroup.C_Group_Code_P + "#" + impCfgGroup.C_Cfg_Code;  ///分组代码#接口代码
            List<ControlEntity> list = new List<ControlEntity>();
            ImpCfgGroupPath importOperItem = new ImpCfgGroupPath();
            importOperItem.C_Path = automaticSetPojo.C_INTERFACE_PATH;
            KTableEntity entity = new KTableEntity(importOperItem);
            list.Add(entity);
            pathCell.Tag = list;
            row.Cells.Add(pathCell);
            tagEditOrCope = true; //复制或则修改下拉框不弹窗
            this.rtbMain.Table.Rows.Add(row);
            this.rtbMain.Refresh();
        }
    }
}
