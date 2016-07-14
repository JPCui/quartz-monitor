# quartz-monitor

1 使用quartz自带数据库管理

2 简易的查看Quartz管理界面：

	/quartz-monitor/quartz/findAll
	
3 一个比较有用的util

	cn.cjp.util.LoadPackageClasses
这里用于手动扫描制定包下，被@Job注解的Job实体，用于创建Job

	
# Questions

任务恢复之后运行多次的原因：[quartz重复执行的问题](http://bbs.csdn.net/topics/391903644)

# Version Update

> [v0.0.1](https://github.com/JPCui/quartz-monitor/tree/v0.0.1) 简单的例子

> [v1.0.0](https://github.com/JPCui/quartz-monitor/tree/v1.0.0) 简单的Job、Trigger管理界面

> [v1.0.1](https://github.com/JPCui/quartz-monitor/tree/v1.0.1) Job交由Spring管理，可以将Service注入到Job
	
	