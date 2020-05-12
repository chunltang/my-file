#  docker命令

## Docker 修改Docker0网桥默认网段
```
https://blog.csdn.net/zhuchunyan_aijia/article/details/87076108?depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-3&utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-3
```
## docker常用命令
```
https://www.yiibai.com/docker/plugin_rename.html
```

## 一键部署
```
https://blog.csdn.net/jccodecode/article/details/100129414
```
## docker教程
```
http://c.biancheng.net/view/3197.html
```
## 容器内安装命令
```
ubantu:
apt-get update
apt-get install inetutils-ping  安装ping

centos：
yum install -y iputils 安装ping
```
## docker相关
```
systemctl start docker		启动 docker
systemctl status docker		查看 docker 状态
systemctl stop docker		停止 docker
systemctl enable docker		开机自启
docker info 			查看 docker 概要信息
docker --help			查看 docker 帮助文档
显示指定容器IP地址：
docker inspect -f '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' 1f6ed3779bba

显示所有容器IP地址：
docker inspect --format='{{.Name}} - {{range.NetworkSettings.Networks}}{{.IPAddress}}{{end}}' $(docker ps -aq)

列出所有容器对应的名称，端口，及ip:
docker inspect -f='{{.Name}} {{.NetworkSettings.IPAddress}} {{.HostConfig.PortBindings}}' $(docker ps -aq)

查看docker name：
docker inspect -f='{{.Name}}' $(sudo docker ps -a -q)
```

## 运行镜像
```
一键生成镜像、容器：
docker build -t gms-1.0.1 . && docker images |grep "gms-1.0.1" |awk '{print $2}'|xargs -I {}  docker run -itd -p 8081:8081 --name=gms-1.0.1 --restart=always {}

使用Dockerfile 创建镜像：docker build -t gms-1.0 .     #最后的.表示使用当前目下的Dockerfile文件构建
FROM openjdk:8
RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo "Asia/Shanghai" > /etc/timezone
ADD gms-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]

docker run -it -p 8080:8080 --name=gms-1.0  --resart=always --privileged=true  976f5bb67fcb -d
docker logs -f  976f5bb67fcb  #查看实时运行日志
docker run hello-world
docker run ubuntu:15.10 /bin/echo "Hello world"
docker run +容器名称 +在容器中执行的命令
```
## java JVM参数
```
java -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=128m -Xms1024m -Xmx1024m -Xmn256m -Xss256k -XX:SurvivorRatio=8 -XX:+UseConcMarkSweepGC -jar /jar包路径 
-XX:MetaspaceSize=128m （元空间默认大小）
-XX:MaxMetaspaceSize=128m （元空间最大大小）
-Xms1024m （堆最大大小）
-Xmx1024m （堆默认大小）
-Xmn256m （新生代大小）
-Xss256k （棧最大深度大小）
-XX:SurvivorRatio=8 （新生代分区比例 8:2）
-XX:+UseConcMarkSweepGC （指定使用的垃圾收集器，这里使用CMS收集器）
```
## run参数详解
```
a.进入容器
我们通过docker的两个参数 -i -t，让docker运行的容器实现"对话"的能力
docker run -i -t ubuntu:15.10 /bin/bash
-t:在新容器内指定一个伪终端或终端。
-i:允许你对容器内的标准输入 (STDIN) 进行交互

  进入已有容器：
  docker attach ID
  或者使用exec进入一个已经在运行的容器：
  docker exec -it ID   /bin/bash
  docker exec -it mysql-test bash

-d参数让容器在后台运行

-P(大写) 将容器内部使用的网络端口映射到我们使用的主机上，可以通过docker ps看到端口信息

-p(些小)  -p 5000:5000 设置映射端口，前面为宿主机端口，后面为docker端口

-v  -v ~/nginx/www:/usr/share/nginx/html：将我们自己创建的 www 目录挂载到容器的 /usr/share/nginx/html 
      如果共享的是多级目录，可能会出现权限不足的情况 可以通过添加参数 --privileged=true 来解决，因为 CentOS7 中安全模块将 selinux 权限禁掉了，添加此参数，可以将问题解决

--name 给容器取名

-e  设置环境变量，代表添加环境变量 MYSQL_ROOT_PASSWORD 是 root 用户的登录密码

--restart always  开机启动容器

--rm ：这个参数是说容器退出后随之将其删除

--link可以用来链接2个容器，使得源容器（被链接的容器）和接收容器（主动去链接的容器）之间可以互相通信，并且接收容器可以获取源容器的一些数据，如源容器的环境变量

--net 指定网路形式 (https://blog.51cto.com/13362895/2130375)   可以通过docker inspect name  查看元数据，包括网路形式，ip，环境变量等信息

--privileged=true  给容器添加root用户的权限

--volumes-from  共享其他容器的挂载点     #docker run --name test2 -it --volumes-from test1  ubuntu  /bin/bash

-a stdin: 指定标准输入输出内容类型，可选 STDIN/STDOUT/STDERR 三项；
-d: 后台运行容器，并返回容器ID；
-i: 以交互模式运行容器，通常与 -t 同时使用；
-P: 随机端口映射，容器内部端口随机映射到主机的高端口
-p: 指定端口映射，格式为：主机(宿主)端口:容器端口
-t: 为容器重新分配一个伪输入终端，通常与 -i 同时使用；
--name="nginx-lb": 为容器指定一个名称；
--dns 8.8.8.8: 指定容器使用的DNS服务器，默认和宿主一致；
--dns-search example.com: 指定容器DNS搜索域名，默认和宿主一致；
-h "mars": 指定容器的hostname；
-e username="ritchie": 设置环境变量；
--env-file=[]: 从指定文件读入环境变量；
--cpuset="0-2" or --cpuset="0,1,2": 绑定容器到指定CPU运行；
-m :设置容器使用内存最大值；
--net="bridge": 指定容器的网络连接类型，支持 bridge/host/none/container: 四种类型；
--link=[]: 添加链接到另一个容器；
--expose=[]: 开放一个端口或一组端口；
--volume , -v: 绑定一个卷
```

## 容器命令
```
docker run -d ubuntu:15.10 /bin/sh -c "while true; do echo hello world; sleep 1; done"
4.在容器内使用docker logs命令，查看容器内的标准输出
5.停止容器
docker stop containerID
已经停止的容器，我们可以使用命令 docker start 来启动
docker start ID
docker restart ID
docker stop $(docker ps -a -q)   停止所有容器
```
## 查看docker客户端命令
```
docker  所有命令
docker command --help   命令详细使用
docker-current run --help   查看--参数的使用
```
## 查看特定容器的端口映射
```
docker port ID
```
## 可以查看容器内部的标准输出
```
docker logs [-f] ID 
-f: 让 docker logs 像使用 tail -f 一样来输出容器内部的标准输出
```

## 查看WEB应用程序容器的进程
```
docker top ID
```
## inspect
```
使用 docker inspect 来查看 Docker 的底层信息。它会返回一个 JSON 文件记录着 Docker 容器的配置和状态信息
docker inspect ID
```
## 移除容器
```
docker rm ID                 移除容器必须是停止状态，否则会报错
docker rm `docker ps -a -q`  删除所有容器
```
## 容器查看
```
docker ps -a    查看所有容器
docker ps       查看运行容器
docker ps -l    最近创建的容器
docker ps -a -q   -q只列出容器ID
docker ps -l                          查看最后一次运行的容器：
docker ps -f status=exited            查看停止的容器
docker inspect 容器名称(容器 ID )     查看容器 IP
docker inspect --format='{{NetworkSetting。IPAddress}}' 容器名称(容器 ID)    也可以直接输出 IP 地址
```
## 运行redis容器并使用
```
docker run -p 6379:6379 --name redis-test  -d redis:3.2 redis-server --appendonly yes
redis-server --appendonly yes : 在容器执行redis-server启动命令，并打开redis持久化配置
使用redis镜像执行redis-cli命令连接到刚启动的容器
docker exec -it redis-test(或者ID) redis-cli

--requirepasss设置密码：
docker run -d --name myredis -p 6379:6379 redis --requirepass "123456"

docker run -it -p 6379:6379 --name=redis-test --restart always --privileged=true  -v $PWD/redis.conf:/usr/redis/redis.conf -v $PWD:/data -d redis:3.2 redis-server /usr/redis/redis.conf --appendonly yes

>>>>>>>>>>Docker容器挂载数据卷出现Permission denied权限问题的解决办法

需要关闭selinux
chown: changing ownership of '.': Permission denied
临时关闭
setenforce 0 
永久关闭：
vim /etc/sysconfig/selinux  SELINUX=enforcing 改为 SELINUX=disabled

在运行容器的时候，给容器加特权，及加上 --privileged=true 参数。使用该参数，容器内的root拥有真正的root权限.否则，容器内的root只是外部的一个普通用户权限

redis使用配置文件启动：
redis-server /usr/redis/redis.conf
设置键空间通知,$表示字符串类型的键值
notify-keyspace-events "KE$"

redis监听：
__keyspace@0__:test*  0为指定的redis库，test*为模糊匹配，keyspace为匹配键
__keyevent@0__:set    指定类型的set监听，keyevent为键事件匹配

在容器中使用config命令可以获取或者设置配置项,如 config get notify-keyspace-events
config set notify-keyspace-events "E$"   #监听字符串的操作事件
在终端输入：PSUBSCRIBE __keyevent@0__:set   #发布事件

docker run -it --name redis -p 6379:6379 --restart always  --privileged=true -d redis:3.2  redis-cli config set notify-keyspace-events "E$"
```

## 运行mysql并执行客服端
```
docker run -p 3306:3306 --name mysql-test -e MYSQL_ROOT_PASSWORD=123456 -d mysql:8.0.13
docker run -it -p 3306:3306 --name=mysql.8  --restart always --privileged=true  -e MYSQL_ROOT_PASSWORD=123456  mysql:8 -d
-e MYSQL_ROOT_PASSWORD=123456：初始化 root 用户的密码
docker exec -it mysql-test mysql -uroot -p123456  进入mysql客服端
docker exec -it mysql-test mysql bash  进入mysql所在容器

Navicat连接MySQL，出现2059 - authentication plugin 'caching_sha2_password'的解决方案：
use mysql;
ALTER USER 'root'@'%' IDENTIFIED WITH mysql_native_password BY '123456';

mysql导出数据：mysqldump
（https://blog.csdn.net/muriyue6/article/details/82848358）
（http://www.yinxi.net/zt/show.php?ztID=11215）
docker exec -it 8c459c071087  mysqldump -uroot -p123456 gms_base > demo.sql            导出gms_base数据库
docker exec -it 8c459c071087  mysqldump --opt -t -uroot -p123456 gms_base > demo2.sql  使用mysqldump只导出数据不导出表结构
docker exec -it mysql01 mysqldump --opt -d -uroot -proot demo > /home/bak/demo1.sql    使用mysqldump只导出表结构不导出数据
docker exec -it 8c459c071087 mysqldump -uroot -p123456 --default-character-set=utf8  gms_base sys_user_log > userlog.sql  指定表导出
docker exec -it 8c459c071087  mysqldump -uroot -p123456 -d  -B mysql gms_base  > demo.sql  
docker exec -it 8c459c071087 mysqldump -uroot -p123456 --default-character-set=utf8  gms_base --tables sys_menu sys_role_menu sys_user_role sys_role t_mineral > gms_base_sometable_0413.sql  指定多个表导出
--opt:默认只导出数据
-d:只导出结构
-t:只导出数据
-B d1 d2 指定导出多个数据库
-A 导出所有数据库
--default-character-set=utf8  指定导出编码类型
--tables  指定导出表名
--database 指定数据库名
--ignore-table  指定忽略表，可以使用多次

数据库中执行set names utf8; 设置数据库编码
在数据库中使用source命令导入sql文件

导入数据时报错：
ERROR 1231 (42000): Variable 'character_set_client' can't be set to the value of 'NULL'
1.mysql设置导入文件大小的限制：
(https://blog.csdn.net/yabingshi_tech/article/details/52813855)
set global max_allowed_packet = 2*1024*1024*10;
2.打开sql文件，删除这几行：
(https://stackoverflow.com/questions/29112716/mysql-error-1231-42000variable-character-set-client-cant-be-set-to-the-val)
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

```

## rabbitmq运行并创建用户、密码
```
docker run -d --hostname rabbit-host --name rabbitmq -e RABBITMQ_DEFAULT_USER=user -e RABBITMQ_DEFAULT_PASS=password -p 15672:15672 -p 5672:5672 rabbitmq:3-management
docker run -d --name=rabbitmq-test -p 15672:15672 -p 5672:5672 rabbitmq:management  需要映射5672端口，不然访问被拒绝

进入docker的mq容器执行，安装web管理页面插件
--rabbitmq-plugins enable rabbitmq_management  

rabbirmq查看版本：
rabbitmqctl status|grep version

```

## 安装ssh
```
（https://blog.csdn.net/gesanghuakaisunshine/article/details/79395400）
docker pull dockerbase/openssh-server
查看ssh状态：
/etc/init.d/ssh status
打开ssh服务：
/etc/init.d/ssh start 

The authenticity of host 'localhost (127.0.0.1)' can't be established的处理方法：
修改/etc/ssh/ssh_config：
追加：StrictHostKeyChecking no
         UserKnownHostsFile /dev/null

```
## docker安装druid
```
docker pull druidio/example-cluster
docker run --rm -i -p 3000:8082 -p 3001:8081 druidio/example-cluster
报Not enough direct memory.  Please adjust -XX:MaxDirectMemorySize, druid.processing.numThreads, druid.processing.numThreads, or druid.processing.numMergeBuffers: maxDirectMemory[1,011,351,552], memoryNeeded[2,617,245,696] = druid.processing.buffer.sizeBytes[67,108,864] * (druid.processing.numMergeBuffers[7] + druid.processing.numThreads[31] + 1)
处理：修改容器/etc/supervisor/conf.d/supervisord.conf的配置，调小druid.processing.numThreads或者druid.processing.buffer.sizeBytes，容器中的文件可以拷贝到容器外修改
```

## 文件拷贝
```
将文件拷贝到容器内
docker cp 需要拷贝的文件或目录  容器名称：容器目录

将文件从容器内拷贝出来
docker cp 容器名称：容器目录	需要拷贝的文件或目录

```

## 迁移与备份
```
容器保存为镜像：
docker commit 容器名称 镜像名称
docker commit mynginx mynginx_i

将镜像保存为 tar 文件，例：
docker save -o mynginx.tar mynginx_i

镜像恢复与迁移：-i 输入的文件，例：
docker load -i mynginx.tar
```

## Dockerfile
```
创建镜像：
docker build -f /gms-files/map-service/Dockerfile -t gms-map:1.0.0 .
-f 指定Dockerfile文件路径
-t 指定镜像名称和tag
.  表示build上下文件的根目录，在使用ADD/COPY命令时，如果不用WORKDIR命令切换工作目录，则默认使用根目录

FROM image_name：tag  定义了使用哪儿个基础镜像启动构建流程
MAINTAINER user_name	声明镜像的创建者
ENV key value		设置环境变量(可以写多条)
RUN command 		是 Dockerfile 的核心部分(可以写多条)
ADD source_dir/file dest_dir/file  将宿主机的文件复制到容器内，如果是一个压缩文件，将会在复制后自动解压，多个相关命令使用&&连接
COPY source_dir/file dest_dir/file   和 ADD 相似，但是如果有压缩文件并不能解压
WORDIR path_dir 设置工作目录
EXPOSE 8080 暴露端口

ENTRYPOINT ["java","-jar","/app/app.jar"]  CMD和ENTRYPOINT指令都是用来指定容器启动时运行的命令，ENTRYPOINT总是会被执行，CMD命令可以通过启动命令覆盖，容器启动必须指定其中一个命令，不然报错
CMD ["executable","param1","param2"]    // 这是 exec 模式的写法，注意需要使用双引号。
CMD command param1 param2               // 这是 shell 模式的写法。两种形式CMD，都可以通过容器启动时设置的命令覆盖CMD
同时使用 CMD 和 ENTRYPOINT 的情况
对于 CMD 和 ENTRYPOINT 的设计而言，多数情况下它们应该是单独使用的。当然，有一个例外是 CMD 为 ENTRYPOINT 提供默认的可选参数。
我们大概可以总结出下面几条规律：
    • 如果 ENTRYPOINT 使用了 shell 模式，CMD 指令会被忽略。
    • 如果 ENTRYPOINT 使用了 exec 模式，CMD 指定的内容被追加为 ENTRYPOINT 指定命令的参数。
    • 如果 ENTRYPOINT 使用了 exec 模式，CMD 也应该使用 exec 模式。

```

## docker中安装vi命令
```
依次运行：apt-get update |  apt-get install vi
apt-get update，这个命令的作用是：同步 /etc/apt/sources.list 和 /etc/apt/sources.list.d 中列出的源的索引，这样才能获取到最新的软件包

安装netstat命令：
apt-get update | apt-get install net-tools


deb http://mirrors.163.com/debian/ jessie main non-free contrib
deb http://mirrors.163.com/debian/ jessie-proposed-updates main non-free 
deb-src http://mirrors.163.com/debian/ jessie main non-free contrib
deb-src http://mirrors.163.com/debian/ jessie-proposed-updates main non-free contrib
```
## zookeeper
```
docker run --name zk-test  -p 2181:2181 -p 2888:2888 -p 3888:3888  -p 2182:2182 -p 2889:2889 -p 3889:3889 -p 2183:2183 -p 2890:2890 -p 3890:3890 --restart always -d zookeeper

docker run -it --rm --link zk-test:zookeeper zookeeper zkCli.sh -server zookeeper 运行zk客户端
```

## docker的vim不能右键复制
```
右键不能粘贴，反而进入了visual模式，
修改方法：
vim /usr/share/vim/vim81/defaults.vim
第70行
在mouse=a的=前面加个-
如下：
if has('mouse')
set mouse-=a
endif
```

## 查看docker相关统计(镜像，容器，卷占用)
```
docker system df
docker inspect : 获取容器/镜像的元数据
查看挂载目录：
docker inspect 6836b2acfb6a |grep Volumes -A 10
docker inspect jenkins_docker | grep Mounts -A 20
```
## top命令
```
docker top cid
查看所有运行容器的进程信息：
for i in  `docker ps |grep Up|awk '{print $1}'`;do echo \ &&docker top $i; done
```

## 修改容器启动配置参数
```
1.命令修改
docker container update --restart=always 容器名字

2.直接改配置文件 （https://www.cnblogs.com/Zfc-Cjk/p/10851847.html）
```

## 查看所有容器CPU使用状态：
```
docker stats
```

## docker容器、镜像导入导出
```
容器导出:
docker export  cid -o  fileName.tar
容器导入:
docker import  fileName.tar cName:v1.0.0

镜像导出:
docker save -o  jdk.tar imageId
镜像导入：
docker load -i jdk.tar

将容器保存为镜像：
docker commit cid imageName

使用导出的容器文件运行容器时，需要带上commands参数，不然报错：
docker run -it --rm --name mysql-gms -p 3306:3306 -e MYSQL_ROOT_PASSWORD=123456 -d  mysql:8 docker-entrypoint.sh mysqld
使用docker ps --no-trunc查看commands参数
```

## docker容器网路
```
显示所有容器网路类型：
查看网路和网路id
docker network ls
查看网路详细信息
docker network inspect id

容器通信：
http://www.iamlintao.com/6700.html
创建网路：
docker network create --subnet=172.18.0.0/16 gms-network

启动 Docker的时候，用 --network 参数，可以指定网络类型和ip，如：
docker run -itd --name test1 --network gms-network --ip 172.17.0.10 centos:latest/bin/bash
```

## 镜像重命名
```
docker tag f8146facf376 openjdk:8
```

## 查看容器的运行命令(commands参数)
```
docker ps --no-trunc
```

## Dockerfile文件详解
```
(https://www.cnblogs.com/panwenbin-logs/p/8007348.html)
FROM           使用镜像构建容器     #FROM openjdk:8
ENV              添加环境变量   #eENV a 10  \ ENV b 20  可以写多个,在容器中使用env命令查看，用$a 使用环境变量
EXPOSE        暴露端口   #EXPOSE 8080
ADD             复制文件到容器    #ADD a.jar  app.jar
```

## docker容器时间与主机时间不一致
```
（https://www.cnblogs.com/okong/p/docker-time-diff.html）
1.docker cp /etc/localtime 500b725eacea:/etc/localtime
2.docker run --name <name> -v /etc/localtime:/etc/localtime

解决JVM时间与主机时间不一致(https://blog.csdn.net/jiangjun0130/article/details/86505156)
在Dockerfile中添加命令:
RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo "Asia/Shanghai" > /etc/timezone
```
## docker启动WARNING
```
IPv4 forwarding is disabled. Networking will not work. 报错解决办法 
centos 7 docker 启动了一个web服务 但是启动时 报
WARNING: IPv4 forwarding is disabled. Networking will not work.
#需要做如下配置
解决办法：
vi /etc/sysctl.conf
net.ipv4.ip_forward=1  #添加这段代码
#重启network服务
systemctl restart network && systemctl restart docker
#查看是否修改成功 （备注：返回1，就是成功）
[root@docker-node2 ~]# sysctl net.ipv4.ip_forward
net.ipv4.ip_forward = 1
```
