$("#").click(function(){
    $.get("demo_test.php",function(data,status){
      alert("数据: " + data + "\n状态: " + status);
    });
  });