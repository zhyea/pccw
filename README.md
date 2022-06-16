# PCCW

用户管理功能

接口文档：[用户管理相关接口](https://github.com/zhyea/pccw/wiki)

## 如何部署

### 1. 获取源码

```shell
git clone git@github.com:zhyea/pccw.git
```
或者

```shell
wget https://github.com/zhyea/pccw/archive/refs/heads/main.zip
unzip main.zip
mv pccw-main pccw
```

### 2. 编译打包

```shell
cd pccw
mvn clean package -DskipTests
```

### 3. 启动服务

```shell
sh start.sh
```
