using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using System.IO;
using YssElecReco.Service.Er;
using FAST.Core.Util;
using FAST.Core.Context;
using FAST.Platform.DataIntegration.Pojo;
using YssElecReco.pojo.Deploy;

namespace YssElecReco.Fun
{
    /// <summary>
    /// 文件序列化类
    /// </summary>
    public class DeployUtil
    {
        /// <summary>
        /// 定义说明文档
        /// </summary>
        public const string DEPLOY_FILE = "deploy.xml";

        /// <summary>
        /// 读XML文件
        /// </summary>
        /// <param name="fileName">文档名称</param>
        /// <returns>序列化的类</returns>
        public static Deploy readDeploy(string fileName)
        {
            ClsXmlAdmin xmlAdmin = new ClsXmlAdmin();
            return xmlAdmin.Deserialize<Deploy>(fileName);
        }

        /// <summary>
        /// 读XML文件
        /// </summary>
        /// <param name="byteContent">文件内容</param>
        /// <returns>序列化的类</returns>
        public static Deploy readDeploy(byte[] byteContent)
        {
            ClsXmlAdmin xmlAdmin = new ClsXmlAdmin();

            ////资源模板管理-文档模板部署-上传文档错误-提示缺少temp文件夹,
            ////此问题可以通过手动在debug文件夹中创建temp文件夹解决，但是需要系统自动生成这个文件夹。
            if (!Directory.Exists(ClsConstant.PATH_TEMP))
            {
                Directory.CreateDirectory(ClsConstant.PATH_TEMP);
            }

            FileStream fs = new FileStream((ClsConstant.PATH_TEMP + DEPLOY_FILE), FileMode.Create, System.Security.AccessControl.FileSystemRights.WriteData, FileShare.ReadWrite, byteContent.Length, FileOptions.None);
            fs.Write(byteContent, 0, byteContent.Length);
            fs.Flush();
            fs.Close();
            Deploy deploy = xmlAdmin.Deserialize<Deploy>(ClsConstant.PATH_TEMP + DEPLOY_FILE);
            File.Delete(ClsConstant.PATH_TEMP + DEPLOY_FILE);
            return deploy;
        }


        /// <summary>
        /// 上传文件的方法
        /// </summary>
        /// <param name="fullFileName">文件名称</param>
        /// <param name="service">服务</param>
        /// <returns>上传成功返回真值</returns>
        public static bool UpLoad(string fullFileName, IDzTemplateService service)
        {
            bool bResult = false;
            FileStream fs = File.OpenRead(fullFileName);
            try
            {
                int length = 1024 * 1024;
                int block = 0, surplus = 0;
                surplus = (int)fs.Length;
                string fileName = "";
                if (fullFileName.StartsWith(ClsConstant.PATH_TEMP))
                {
                    fileName = fullFileName.Substring(ClsConstant.PATH_TEMP.Length);
                }
                else
                {
                    fileName = fullFileName.Substring(fullFileName.LastIndexOf("\\") + 1);
                }

                length = (surplus > length ? length : surplus);
                byte[] buf = new byte[length];
                int index = 0;
                while ((block = fs.Read(buf, 0, length)) > 0)
                {
                    index++;
                    FAST.Platform.DataIntegration.Pojo.FileStreamParam fileTrans = new FAST.Platform.DataIntegration.Pojo.FileStreamParam();
                    fileTrans.FileData = buf;
                    fileTrans.FileName = fileName;
                    fileTrans.PartIndex = index;
                    string sResult = service.upload(fileTrans);
                    bResult = bool.Parse(sResult);
                    if (!bResult)
                    {
                        return bResult;
                    }
                }
            }
            catch (Exception e)
            {
                throw e;
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
    }
}
