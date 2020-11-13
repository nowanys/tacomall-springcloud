<!--
 * @Author: 码上talk|RC
 * @Date: 2020-06-24 11:41:49
 * @LastEditTime: 2020-06-24 11:51:04
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-springboot/_doc/jd/README.md
 * @Just do what I think it is right
--> 
# 京东爬虫程序

初始化了数据库后，为了有数据展示，我们提供了一个简易的京东爬虫程序用于初始化数据库数据

## 步骤

### 安装环境

1. 装python环境

```
python >= 3.5
```

2. 安装python依赖

切换到 /tacomall-springboot/_doc/jd/

```
pip install -r requirements.txt
```

3. 配置chromedriver

```
https://npm.taobao.org/mirrors/chromedriver/
```

### 配置数据库

编辑index.py文件配置数据库

### 启动

切换目录至 /tacomall-springboot/_doc/jd/

powershell >>>
```
python index.py
```
