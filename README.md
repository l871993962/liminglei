1.单个文件超过100M时处理流程
2.先手工删除本地文件
3.git add .
4.git commit -m'删除文件'
5.git filter-branch  #将文件从本地的git repository里面删除（重要）
