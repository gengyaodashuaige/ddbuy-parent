<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>我的网页</title>
</head>
<body>
    主题：${title}
    <hr>
    正文：${content}
    <hr>
    高薪大佬：
    <ul>
        <#list stulist as item >
            <li>${item}</li>
        </#list>
    </ul>
</body>
</html>