插件化

掌握这个技术能够做什么？
1. 瘦身App
2. 按需加载业务模块

知识点：
1. 熟悉ClassLoader 机制、加载Apk
2. 插件中Android 四大组件生命周期处理
3. 插件中的资源如何加载
4. 打包流水线如何管理插件，插件上传、下发、版本号等管理

功能实现：
1. 创建一个工程创建Actiivty编写代码
2. 打包插件工程产出Apk
3. 宿主工程使用classLoader加载插件Apk
4. 在宿主项目中配置占位Activity用于加载插件Activity
5. 处理插件Activity生命周期保证插件能够正常使用
6. 处理插件资源调用问题（AsertManager）
