插件化学习

掌握这个技术能够做什么？
1. 瘦身App
2. 按需加载业务模块

知识点：
1. 熟悉ClassLoader 机制、加载Apk
2. 插件中Android 四大组件生命周期处理
3. 插件中的资源如何加载
4. 打包流水线如何管理插件，插件上传、下发、版本号等管理

运行gradle ‘packageApkTransform’ 任务将最新的apk拷贝到宿主asset目录下模拟下载插件，点击运行将文件拷贝到app内部存储目录

功能实现：
1. 创建一个工程创建Actiivty编写代码
2. 打包插件工程产出Apk
3. 宿主工程使用classLoader加载插件Apk
4. 在宿主项目中配置占位Activity用于加载插件Activity
5. 处理插件Activity生命周期保证插件能够正常使用
6. 处理插件资源调用问题（AsertManager）

项目结构
app：宿主
plugin：插件
plugin_scheme：定义插件项目协议，暴露给宿主，支持宿主根据定义的接口回调给插件生命周期

已实现的功能
✅ 加载Activity
✅ 支持Lifecycle 等Jetpack工具包功能
✅ 使用静态代理功能将生命周期回调到插件
❎ 反射系统欺骗AMS，个人认为这种的健壮性并不高，目前还没增加，TODO
❎ 经过测试约束布局使用有崩溃问题，还没解决 TODO
 
