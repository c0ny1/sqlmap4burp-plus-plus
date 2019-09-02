# sqlmap4burp++ | burp联动sqlmap插件
## 0x01 插件简介
sqlmap4burp++对[sqlmap4burp](https://github.com/difcareer/sqlmap4burp)进行了重构,可在多个平台下联动burpsuite于与sqlmap。

在sqlmap4burp基础上进行了如下改动：

* 在支持Windows基础上，拓展对Linux，Mac OSX的支持
* 移除对commons-io-<version>.jar,commons-langs-<version>.jar的依赖
* 移除Burpsuite tab面板，采用弹窗式配置，让界面更加简洁易用。
* 移除了多余的代码

## 0x02 插件演示
已经在如下系统测试成功：

* Windows：7,10
* Mac OSX：Mojave 10.14.5
* Linux：Kali2019.2,Ubuntu,Centos

[![视频演示](https://img.youtube.com/vi/Rcyfm8bd63o/0.jpg)](https://www.youtube.com/watch?v=Rcyfm8bd63o)

## 0x03 FQA
#### 1.在Mac OSX下无法弹出Terminal？
* 原因一：没有允许运行外部`Burp suite`运行`osascript`。
* 原因二：没有启动终端（Terminal），请将其启动。若已经是运行状态，那么请重启它！

#### 2.在linux下弹窗了，为何没有执行命令呢？
插件已经将命令复制到剪贴板，将其粘贴到弹出的命令窗口即可！目前插件在Linux下暂时无法实现启动Terminal的同时使其运行sqlmap命令，所以暂时采用这种临时的方法。

## 0x04 参考项目
* https://github.com/blueroutecn/Burpsuite4Extender
* https://github.com/difcareer/sqlmap4burp
