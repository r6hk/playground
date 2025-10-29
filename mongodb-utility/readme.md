使用 mongodb shell 工具，和 mongodump mongorestore 工具，完成 Mongodb 从生产数据库到测试数据库的迁移

mongodb shell , mongodump, mongorestore 均已经在机器上安装

编写一个 shell 脚本能够自动执行该流程

shell 脚本需要读取外部配置文件，该配置文件定义了三个变量：
生产数据库连接、测试数据库连接、开发数据库连接

脚本包含的命令行参数： -t dev,test -c collectionName

也就是说，可以指定将生产数据库的某个集合的内容移到测试数据库还是开发数据库，并且可以指定是哪个集合

迁移流程：
首先使用 mongoshell，检测生产数据库对应集合的大小（单位为 MB），回车运行命令后，屏幕上打印源集合大小信息、源数据库连接、目标数据库连接、提示用户目标数据库对应的集合上的所有数据会被清空，是否继续(y/n)

用户选择继续之后，使用 mongodump 和 mongorestore 自动执行迁移过程