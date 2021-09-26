using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json;

namespace YssElecReco.pojo.Deploy
{
    /// <summary>
    /// 文件流传输的相关参数
    /// </summary>
    public class FileStreamParam
    {
        /// <summary>
        /// 文件名称
        /// </summary>
        private string _fileName = "";

        /// <summary>
        /// 文件路径s
        /// </summary>
        private string _filePath = "";

        /// <summary>
        /// 文件流
        /// </summary>
        private byte[] _fileData = null;

        /// <summary>
        /// 文件流所属文件内容的索引
        /// </summary>
        private int partIndex = -1;

        /// <summary>
        /// 属性：文件流所属文件内容的索引
        /// </summary>
        [JsonProperty(PropertyName = "partIndex")]
        public int PartIndex
        {
            get { return partIndex; }
            set { partIndex = value; }
        }

        /// <summary>
        /// 文件流
        /// </summary>
        [JsonProperty(PropertyName = "fileData")]
        public byte[] FileData
        {
            get
            {
                return _fileData;
            }

            set
            {
                _fileData = value;
            }
        }

        /// <summary>
        /// 文件名称
        /// </summary>
        [JsonProperty(PropertyName = "fileName")]
        public string FileName
        {
            get
            {
                return _fileName;
            }

            set
            {
                _fileName = value;
            }
        }

        /// <summary>
        /// 文件路径
        /// </summary>
        [JsonProperty(PropertyName = "filePath")]
        public string FilePath
        {
            get
            {
                return _filePath;
            }

            set
            {
                _filePath = value;
            }
        }
    }
}
