# docker安装部署

## 设置yum源
```yum-config-manager --add-repo http://download.docker.com/linux/centos/docker-ce.repo（中央仓库）
yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo（阿里仓库）
```
## 跟新软件
```
yum update 
```

## 列出docker的版本
```
yum list docker-ce --showduplicates|sort -r
```

## 安装docker
```
yum -y install docker-ce
apt install docker.io
```

## 启动docker
```
systemctl start docker
```

## 设置服务跟随宿主机启动
```
systemctl enable docker.service  设置开机启动
systemctl is-enabled docker.service 查看某个软件是否开机自启动
systemctl status docker.sevice  查看服务状态
```

## docker设置镜像源
```
创建或修改 /etc/docker/daemon.json 文件：
{
    "registry-mirrors": ["https://registry.docker-cn.com"]
}

国内加速地址有：
Docker中国区官方镜像
https://registry.docker-cn.com
网易
http://hub-mirror.c.163.com
ustc 
https://docker.mirrors.ustc.edu.cn
中国科技大学
https://docker.mirrors.ustc.edu.cn
阿里云容器  服务
https://cr.console.aliyun.com/
首页点击“创建我的容器镜像”  得到一个专属的镜像加速地址，类似于“https://1234abcd.mirror.aliyuncs.com”
```