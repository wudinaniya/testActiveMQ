### JMS
JMS java message service Java消息服务,它是一个中间件.
是javaEE 1.4 出来的
提供者(生产者),消费者, 是一个规范,是接口

### ActiveMQ
ActiveMQ 是Apache出品，最流行的，能力强劲的开源消息总线。ActiveMQ 是一个完全支持JMS1.1和J2EE 1.4规范的 JMS Provider实现

### 特点

 1
支持多语言  Java, c, c++ , c#, ruby,perl,python,php,

支持的协议: rest, xmpp(聊天,即时通讯),msn, 可以相互通信

2 支持spring, 可以很容易和spring整合

3 支持常见的javaEE容器, javaee容器即指服务器,tomcat, jboss,weblogic

4 支持javaEE规范,支持ajax,可以使用jdbc持久化消息

### 消息模式

#### 点对点通信模式
消费者不一定需要在线,只需要发出去即可,等待消费者接收就行,同理,消费者在线的时候,不一定会有生产者发消息(已验证：不能一对多)

一对一,一个生产者对应一个消费者, 类似于单发短信.  但一个消费者可以对应多个生产者，我给你发短信，别人也可以给你发短信。

在此传送模型中，目标是一个队列。消息首先被传送至队列目标，从该队列将消息传送至向此队列进行注册的某一个消费者，一次只传送一条消息。可以向队列目标发送消息的生产者的数量没有限制，但每条消息只能发送至、并由一个消费者成功使用。如果没有已经向队列目标注册的消费者，队列将保留它收到的消息，并在某个消费者向该队列进行注册时将消息传送给该消费者。

#### 发布/订阅模式
一对多,多对多,类似于收音机的广播. 一个电台可以被很多收音机接收。

在此传送模型中，目标是一个主题。消息首先被传送至主题目标，然后传送至所有已订阅此主题的活动消费者。(已验证：可以一对多)


### 其他
spring配置文件中：
若构造方法中只有一个参数时，可以不写name，直接写<value>标签，传一个参数过来就行了。
![](http://img.blog.csdn.net/20170909144609071?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvd3VkaW5hbml5YQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center)

![](http://img.blog.csdn.net/20170909144644849?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvd3VkaW5hbml5YQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center)

ActiveMQQueue的构造方法中只有一个参数，因此在spring中配置时可有两种写法



对应博客链接地址  <http://blog.csdn.net/wudinaniya/article/details/77914993>
