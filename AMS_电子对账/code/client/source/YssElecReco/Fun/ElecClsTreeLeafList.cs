using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Core.Util;
using FAST.Common.Service.Pojo.Base;
using Yss.KTable.Models;

namespace YssElecReco.Fun
{
    /// <summary>
    /// STORY #103621 【公共需求】电子对账企业年金1711和1811新报表名称调整 
    /// </summary>
    public class ElecClsTreeLeafList : ClsTreeLeafList
    {

        /// <summary>
        /// 生成一个叶子节点是list的树
        /// </summary>
        /// <param name="tbMain">Table</param>
        /// <param name="list">list</param>
        /// <param name="cols">cols</param>
        public void drawCheckIconCell_New(Table tbMain, List<BasePojo> list, int cols)
        {
            if (list == null || list.Count == 0)
            {
                return;
            }

            string[] codes = this.getPropName(list[0]);
            string nodePropName = codes[1];
            string parentPropName = codes[0];
            string showTxtPropName = codes[1].Replace("_CODE", "_NAME");
            string[] propNames = new[] { parentPropName, nodePropName, showTxtPropName };
            this.drawCheckIconCell_New(tbMain, list, cols, propNames);
        }

        /// <summary>
        /// 生成一个叶子节点是list的树
        /// </summary>
        /// <param name="tbMain">Table</param>
        /// <param name="list">list</param>
        /// <param name="cols">cols</param>
        /// <param name="propNames">数组顺序为：父节点代码，节点代码，节点名称</param>
        public void drawCheckIconCell_New(Table tbMain, List<BasePojo> list, int cols, string[] propNames)
        {
            string nodePropName = propNames[1];
            string parentPropName = propNames[0];
            string showTxtPropName = propNames[2];
            Dictionary<string, List<BasePojo>> dict = this.treeAndList(list, nodePropName, parentPropName);
            ////画B区
            // 加载列头
            Column col = null;
            for (int i = 0; i < cols; i++)
            {
                col = new Yss.KTable.Models.Column();
                col.Text = string.Empty;
                if (i == 0)
                {
                    col.Width = 200;
                }
                else
                {
                    col.Width = 220;
                }

                tbMain.Columns.Add(col);
            }

            CheckBoxCell cell = null;
            Row row = null;
            List<BasePojo> treeList = dict["tree"];
            Dictionary<string, CheckBoxCell> tbMainTags = new Dictionary<string, CheckBoxCell>();
            Dictionary<string, Row> treeRowDict = this.getRowDict(treeList, nodePropName);

            foreach (KeyValuePair<string, Row> ider in treeRowDict)
            {
                BasePojo basePojo = ider.Value.Tag as BasePojo;
                cell = new Yss.KTable.Models.CheckBoxCell();
                cell.Checked = true; ////修改单元格的选中状态

                cell.Text = ReflectBase.getAttrValue(showTxtPropName, basePojo) as string;
                ////cell.Font = new Font(tbMain.Font, FontStyle.Bold);
                cell.Tag = basePojo;

                row = ider.Value;
                row.Cells.Add(cell);
                string code_p = ReflectBase.getAttrValue(parentPropName, basePojo) as string;
                if (treeRowDict.ContainsKey(code_p))
                {
                    treeRowDict[code_p].SubRows.Add(row);
                }
                else
                {
                    tbMain.Rows.Add(row);
                }

                tbMainTags.Add(ider.Key, cell);

                if (dict.ContainsKey(ider.Key))
                {
                    ////子节点为叶子节点创建list行
                    List<BasePojo> leafList = dict[ider.Key];
                    ////int cellCount = leafList.Count;////单元格的总数
                    int rowCount = (leafList.Count / cols) + 1;

                    ////创建的行数
                    for (int i = 0; i < rowCount; i++)
                    {
                        Row childRow = new Yss.KTable.Models.Row(); ////创建行
                        int cellNum = leafList.Count - (i * cols); ////剩余的列数
                        int cellnum = Math.Min(cellNum, cols); ////当前行要画的单元格
                        for (int j = 0; j < cellnum; j++)
                        {
                            basePojo = leafList[(i * cols) + j];
                            cell = new Yss.KTable.Models.CheckBoxCell();
                            cell.Checked = true; ////修改单元格的选中状态
                            cell.Text = ReflectBase.getAttrValue(showTxtPropName, basePojo) as string;
                            ////cell.Font = new Font(tbMain.Font, FontStyle.Bold);
                            cell.Tag = basePojo;
                            childRow.Cells.Add(cell);
                            string nodeCode = ReflectBase.getAttrValue(nodePropName, basePojo) as string;
                            ////BUG #216140 【招商基金版本合并】产品参数复制界面查询报错 eidt by chenchangyou 20180908
                            if (tbMainTags != null && !tbMainTags.ContainsKey(nodeCode))
                            {
                                tbMainTags.Add(nodeCode, cell);
                            }
                        }

                        row.SubRows.Add(childRow);
                    }
                }
            }

            tbMain.Tag = tbMainTags;
        }
    }
}
