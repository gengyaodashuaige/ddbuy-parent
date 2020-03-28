<%@page pageEncoding="utf-8" contentType="text/html; utf-8"  language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>添加广告</title>
    <link rel="stylesheet" type="text/css" href="easyUI/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="easyUI/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="easyUI/css/demo.css">
    <script src="js/jquery-1.8.3.js" language="JavaScript" type="text/javascript"></script>
    <!--jquery.easyui.min.js包含了easyUI中的所有插件-->
    <script src="js/jquery.easyui.min.js" language="JavaScript" type="text/javascript"></script>
    <script language="JavaScript">
        $(function () {  //jquery加载事件
            //使用easyui给下拉列表绑定数据
            $("#select").combobox({
                url:'getAllContentCatagory',  //指定服务器返回json数据
                valueField:'id',  //指定项值
                textField:'name'  //指定项文本
            });

            //给添加按钮加事件
            $("#button").click(function () {
                //借助easyui提交表单，异步提交
                $("#form1").form('submit',{
                    url:"addTbContent",  //服务器地址
                    success:function (data) {  //data表示字符串
                        //将返回的json字符串转化为json对象
                        data=$.parseJSON(data);
                        if (data.result==1){
                            $.messager.alert('>>提示','添加成功！','info'); //提示框
                        }else {
                            $.messager.alert('>>提示','添加失败！','error');
                        }
                    }
                })
            });
        });
    </script>
</head>
<body>
    <p>添加广告:</p>
    <form action="addContent" method="post" enctype="multipart/form-data" name="form1" id="form1">
        <p>
            类别
            <select name="categoryId" id="select" style="width: 150px;"></select>
        </p>
        <p>
            标题:
            <input type="text" name="title" id="textfield" />
        </p>
        <p>
            url:
            <input type="text" name="url" id="textfield2" />
        </p>
        <p>图片:
            <input type="file" name="pfile" id="fileField" />
        </p>
        <p>
            状态:
            <input type="radio"  name="status" id="radio" value="1" />
            启用
            <input type="radio" checked name="status" id="radio2" value="0" />
            停用
        </p>
        <p>
            顺序:
            <input type="text" name="sortOrder" id="textfield3" />
        </p>
        <p>
            <input type="button" name="button" id="button" value="添加" />
        </p>
        <p>&nbsp;</p>
    </form>
</body>
</html>