# MyVolley
模仿Volley写的网络请求框架  

各个类的功能介绍
Volley:是将外界调用层和框架层分离的类,作用是将我们生产的任务加入到任务队列中.
ThreadPoolManager:线程池和任务队列的管理类是单利的,在这里FutureTask任务将被从队列中取出执行.
HttpTask:是对我们生成任务的封装,但是真正执行的是FutureTask他是对HttpTask的进一步封装.
IHttpService:真正去请求网络的接口,也就是真正执行任务的类.
JSonHttpService:IHttpService的实现类,是去联网请求json文件的类.
IHttpListener:处理返回数据的接口.
JSonDealListenter:是IHttpListener的实现类,在这里将请求回来的数据解析,并使用Handler和IJsonListener回调到主线程中.
IJsonListener:从子线程切换到主线程的接口

