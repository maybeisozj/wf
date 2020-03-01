# 部署

## 关于wf

首先呢，这个是我学习boot时候的一个想法，就是给自己做一个任务管理器，想来想去就做了这个天气预报的。
里面用到了:

> - spring boot (必须的)
> - quartz (动态任务的实现，实现了持久化)
> - fastjson (返回对象自动解析为json数据或者接收json数据为javabean)
> - druid alibaba (数据库连接池)
> - javamail (邮件发送)
> - webservice (中华万年历的天气接口，获取天气数据)
> - Mybatis (主要是管理我自己写的两个表的)
> - mysql (mysql数据库以及相应的依赖)

## 关于数据库

在sql目录下找到相应的sql文件，新建数据库运行部署即可，另，本项目新建了两个表用于管理
用户以及用户的任务，名为user，task。修改application.properties以及quartz.properties
文件里对应的数据库配置。

> 例子：我的是mysql的数据库,所以在sql目录下找到tables_mysql.sql,user.sql,task.sql
> 运行部署即可。或者你可以直接运行quartz.sql。

## 关于邮箱配置

在application.properties文件里有着发送邮件的配置，将其改为你自己的即可。
QQ邮箱步骤如下：

> 打开你的邮箱首页，进入设置，找到POP3/IMAP/SMTP/Exchange/CardDAV/CalDAV服务
> 这里我们用到的是SMTP服务，所以我们点击开启该服务，按步骤往下走，你会得到一个授权码
> ，这个授权码就是你的密码了（spring.mail.password）。这个码是可以再次生成的，忘了也不要紧。

## 关于天气接口

这个讲道理是随便一个都可以的，只有你能把数据解析出来都可以。这里我们用的是中华万年历的天气接口

> - 通过城市名字获得天气数据 ：http://wthrcdn.etouch.cn/weather_mini?city=长沙
> - 通过城市id获得天气数据：http://wthrcdn.etouch.cn/weather_mini?citykey=101280601
> - 城市的id值 https://www.cnblogs.com/emo-Studio/p/6840534.html

## 关于如何运行

可能你以及注意到了，这里我们并没有前端，也就是说我们是没有界面的。那我为了这个小项目写一个webservice
好像有点小困难，所以我在这里用的是postman来进行操作的。
postman collection url: https://www.getpostman.com/collections/58c74ed33381051f1c2e
打开你的postman导入链接，接下来：

> - 利用注册接口进行注册，这里我们会发送一个url到你的邮箱，用来检验你的邮箱是否可用，将该url用postman访问即可
> - 邮箱检验通过会返回一个token，把它存到你的环境变量里去，这个token就是我们用来辨认身份的
> - 修改密码，按要求发送相应数据即可
> - 添加任务，在task目录下找到添加任务请求，填写相应数据，成功会返回相应数据
> - 暂停，删除，恢复任务都是由一个请求(管理任务)控制的，输入不同type的值即可，如pause，remove，resume
> - 修改任务的cron，请输入正确的cron  -_-
> - 如果你忘记了你任务的相关信息，可以通过查看所有任务的请求得到你所有的任务
> - 通过城市id或者城市名称可以直接得到天气信息