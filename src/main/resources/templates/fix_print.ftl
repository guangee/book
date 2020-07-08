<!DOCTYPE html>
<html>
<head lang="en">
    <title>维修记录</title>
    <link href="/css/index.css" rel="stylesheet" type="text/css"/>
    <style>
        @page {
            /*设置纸张大小:A4(210mm 297mm)、A3(297mm 420mm) 横向则反过来*/
            size: 210mm 297mm;
            /*设置纸张大小:A4(210mm 297mm)、A3(297mm 420mm) 横向则反过来*/
            /*size: 100mm 140mm;*/
            margin: 0 auto;
            /*margin: 0.25in;*/
            padding: 3px;
            @bottom-center {
                content: "";
                font-family: SimSun;
                font-size: 12px;
                color: red;
            };
            @top-center {
                content: element(header)
            };
            @bottom-right {
                content: "";
                font-family: SimSun;
                font-size: 12px;
                color: #000;
            };
        }

        img {
            display: block;
            height: 62mm;
            width: 90mm;
            margin: 0 auto;
            align-content: center;
        }
    </style>
</head>
<#-- 这样配置不中文不会显示 -->
<#--<body style="font-family: 宋体">-->
<body style="font-family: 'SimSun'">
<h1>维修调试记录 <span>${fixNo!}</span></h1>
<table width="80%" style="margin-left: 10%;border-color: black;border-style: solid" border="1">
    <tr>
        <td width="15%">位号</td>
        <td width="15%">公称通径</td>
        <td width="15%">公称压力</td>
        <td width="15%">型号</td>
        <td width="15%">数量</td>
        <td width="15%">修复次数</td>
    </tr>
    <tr>
        <td>${name!}</td>
        <td>${gongchengtongjin!}</td>
        <td>${gongchengyali!}</td>
        <td>${xinghao!}</td>
        <td>${shuliang!}</td>
        <td>${xiufucishu!}</td>
    </tr>

    <tr>
        <td rowspan="3">工作介质</td>
        <td rowspan="3">${gongzuojiezhi!}</td>
        <td>工作压力</td>
        <td>${gongzuoyali!}</td>
        <td>整定压力</td>
        <td>${zhengdingyali!}</td>
    </tr>

    <tr>
        <td>螺纹</td>
        <td>${luowen!}</td>
        <td>调压范围</td>
        <td>${tiaoyafanwei!}</td>
    </tr>

    <tr>
        <td>连接形式</td>
        <td>${lianjiexingshi!}</td>
        <td>密封面中径</td>
        <td>${mifengmianzhongjing!}</td>
    </tr>

    <tr>
        <td>工作温度</td>
        <td>${gongzuowendu!}</td>
        <td>安装位置</td>
        <td colspan="3">${anzhuangweizhi!}</td>
    </tr>


</table>

</body>
</html>