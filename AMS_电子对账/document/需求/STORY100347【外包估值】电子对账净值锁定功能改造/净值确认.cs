        净值确认参数：节假日后锁定节假日期间存在估值表日期
		            ：锁定成立以来估值表
		一下为弹窗代码实现，有调用后台checkJjrAndAll实现，
		/// <summary>
        /// STORY #70054 【外包估值】估值表锁定个性化控制 
        /// 检查历史数据未锁定
        /// </summary>
        /// <param name="dataList">dataList</param>
        /// <param name="eConfirmSvc">eConfirmSvc</param>
        private void checkUnConfirm(List<BasePojo> dataList, IEConfirmService eConfirmSvc)
        {
            //根据参数“节假日后锁定节假日期间估值表”、“锁定成立以来估值表”查询未净值确认数据
            Dictionary<string, Dictionary<string, string>> checkRes = eConfirmSvc.checkJjrAndAll(dataList, "NAV_JCBTX");
            string info = "";
            int j = 0;
            int n = 0;
            ////节假日后锁定节假日期间存在估值表日期
            if (null != checkRes["NAV_SD_GZB_JJR"] && checkRes["NAV_SD_GZB_JJR"].Count > 0)
            {
                j = 3;
                n = 3;

                if (checkRes["NAV_SD_GZB_JJR"].Count < 3)
                {
                    j = checkRes["NAV_SD_GZB_JJR"].Count;
                }

                List<string> portCodes = new List<string>(checkRes["NAV_SD_GZB_JJR"].Keys);

                info = info + "以下组合节假日未锁定:";
                for (int i = 0; i < j; i++)
                {
                    info = info + "\n" + "组合:" + portCodes[i] + "日期:";
                    string dateStrs = checkRes["NAV_SD_GZB_JJR"][portCodes[i]];
                    string[] arrDates = dateStrs.Split(',');
                    if (arrDates.Length < 3)
                    {
                        n = arrDates.Length;
                    }
                    for (int m = 0; m < n; m++)
                    {
                        info = info + arrDates[m] + ",";

                        if ((m + 1) == n && arrDates.Length > 3)
                        {
                            info = info + "...";
                        }
                    }

                    if ((i + 1) == j && checkRes["NAV_SD_GZB_JJR"].Count > 3)
                    {
                        info = info + "\n......";
                    }
                }

                info = info + "\n是否锁定？";

                if (YssMessageBox.ShowQuestion(info, "节假日后锁定节假日期间估值表") == DialogResult.Yes)
                {
                    eConfirmSvc.updateJjrAndAll(checkRes["NAV_SD_GZB_JJR"]);
                }
            }

            ////锁定成立以来估值表
            info = "";
            if (null != checkRes["NAV_SD_GZB_ALL"] && checkRes["NAV_SD_GZB_ALL"].Count > 0)
            {
                j = 3;
                n = 3;
                if (checkRes["NAV_SD_GZB_ALL"].Count < 3)
                {
                    j = checkRes["NAV_SD_GZB_ALL"].Count;
                }

                List<string> portCodes = new List<string>(checkRes["NAV_SD_GZB_ALL"].Keys);

                info = info + "以下组合成立日后存在日期未锁定:";
                for (int i = 0; i < j; i++)
                {
                    info = info + "\n" + "组合:" + portCodes[i] + "日期:";
                    string dateStrs = checkRes["NAV_SD_GZB_ALL"][portCodes[i]];
                    string[] arrDates = dateStrs.Split(',');
                    if (arrDates.Length < 3)
                    {
                        n = arrDates.Length;
                    }
                    for (int m = 0; m < n; m++)
                    {
                        info = info + arrDates[m] + ",";

                        if ((m + 1) == n && arrDates.Length > 3)
                        {
                            info = info + "...";
                        }
                    }

                    if ((i + 1) == j && checkRes["NAV_SD_GZB_ALL"].Count > 3)
                    {
                        info = info + "\n.....";
                    }
                }

                info = info + "\n是否锁定？";

                if (YssMessageBox.ShowQuestion(info, "锁定成立以来估值表") == DialogResult.Yes)
                {
                    eConfirmSvc.updateJjrAndAll(checkRes["NAV_SD_GZB_ALL"]);
                }
            }
        }