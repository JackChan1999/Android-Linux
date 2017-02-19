#Android目录结构
* data
	* data：安装的应用的内部存储所在位置
	* system：packages.list,packages.xml,相当于注册表
	* app:用户级别的应用安装后就保存在这里
	* anr:记录手机的anr异常的详细信息

* dev：全程devices，设备
	* Linux中所有硬件在系统中都会用一个文件来表示

* mnt:mount挂载
	* sdcard

* proc:硬件的状态信息
	* cpuinfo、meminfo

* sbin：system bin
	* adbd：eclipse和Android设备建立连接的Android端的进程

* system：
	* app：系统级别的应用安装在此
	* bin：二进制可执行文件，就是命令行中执行的指令
	* etc：host：IP地址和域名的映射
		* permissions：platfrom.xml：Android定义的权限
	* fonts:字库文件
	* framework:框架层，保存应用框架层的部分jar包
	* lib：c语言类库
	* media：
		* audio：系统中使用到的音效
	* tts：发声引擎
	* usr：
		* keylayout：qwerty.kl:物理按键和按键码的映射
	* xbin:为程序员提供的二进制指令

---
# 常用Linux指令
* su：切换到超级用户
	* #：代表当前用户是root用户
	* $：代表当前用户是普通用户

* rm：删除文件
	* rm 文件名.后缀名
* ls
	* ls -l：查看目录下所有文件的详细信息
	* ls -a：查看隐藏文件
* cd：切换目录，用法跟windows一样
* cat：以文本形式输出文件内容，不要cat非文本文件
* mv：把文件移动到指定位置的文件里
	* mv 文件名 路径/文件名
* mkdir：创建文件夹
	* mkdir 文件夹名
* rmdir：删除文件夹，如果文件夹不为空，删不了
* touch：创建一个新的文件，没有内容
	* touch 文件名
* chmod：change mode 修改文件访问权限
	* chmod 777 文件名
* echo:重定向内容
	* echo 文本内容 > 文件名：把指定内容重定向到指定文件中
	* cat 文件名 > 文件名：把指定文件的内容重定向到指定文件中
* sleep:睡眠，参数是秒
* df:罗列出几个目录的空间信息
	* df sdcrad：罗列出指定目录的空间信息
* id:列出当前用户的用户id和所在群组id
	* uid = 0，root用户
	* uid = 1000，system用户
	* uid = 2000，shell用户（命令行用户）
	* uid > 10000，普通用户（所有上层应用）
* ps:罗列出系统运行的所有进程：包括java进程和c进程
* kill：杀死指定进程
	* kill pid
* chown：change owner
	* chown 0.0 文件名：把指定文件的拥有者改成root用户
* mount:挂载指定的路径
	* mount -o remount rw /:重新挂载根目录为可读可写
	* mount -o remount rw /system:重新挂载system目录为可读可写

#Android特有的指令
* am
	* am start -n com.itheima.helloworld/com.itheima.helloworld.MainActivity:开启指定的activity
	* am kill com.itheima.helloworld:杀死指定的进程，但是不会杀死影响用户体验的进程
	* am force-stop com.itheima.helloworld:强制杀死指定进程

* pm:
	* pm disable 包名：冻结指定应用
	* pm enable 包名：解冻指定应用

* monkey:自动测试指令
	* monkey -p com.itheima.helloworld 1000：测试指定应用1000次