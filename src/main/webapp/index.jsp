<%--
  Created by IntelliJ IDEA.
  User: djw
  Date: 2020/12/13
  Time: 18:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h3>服务器推送测试</h3>
<div id="msg_from_server"></div>
<%--<script type="text/javascript" src="<c:url value="/js/jquery.js" />"></script>--%>
<script src="https://cdn.staticfile.org/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" src="../js/cookie.js"></script>
<script type="text/javascript">
    //只有新式的浏览器才有，EventSource是SSE的客户端
    if (!!window.EventSource) {
        addCookie("id",1);
        var source = new EventSource('order/serverPush');
        s = '';
        source.addEventListener('open', function(e) {
            console.log("连接打开");
        }, false);
        //添加SSE监听，获得服务器推送的消息
        source.addEventListener('message', function(e) {
            console.log(e);
            /*s += e.data + "<br/>";
            $('#msgFromPush').html(s);*/
        }, false);
        // 上次回调事件
        source.addEventListener("connecttime", function(event) {
            // console.log("Connection was last established at: " + event.data);
        }, false);
        // 发生错误时
        source.addEventListener('error', function(e) {
            if (e.readyState == EventSource.CLOSED) {
                console.log('连接关闭');
            } else {
                // 关闭sse
                source.close();
                console.log(e.target.readyState);
            }
        },false);
    } else {
        console.log('你的浏览器不支持SSE');
    }
    /**
     * 上面的方法也可以写成 ： source.onMessage = function(e){console.log(e)}
     * 上面的几个方法都可以以类似的方法写
     */
</script>

</body>
</html>


