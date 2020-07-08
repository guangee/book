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
        html{
            padding:20px 32px;
        }
        .center{
            text-align:center;
        }
        .right{
            text-align:right;
        }
        table{
            /* margin-top:20px; */
            margin:30px auto 0;
        }
        table,tr,td{
            border:1px solid #333;
            border-collapse:collapse;

        }
        td{
            padding:8px 16px;
            height:20px;
        }
        .specail{
            color:lightcoral;
        }
        .gap{
            margin:10px 0 0;
        }
        .sign{
            margin-top:30px;
        }
    </style>
</head>
<#-- 这样配置不中文不会显示 -->
<#--<body style="font-family: 宋体">-->
<body style="font-family: 'SimSun'">
<#--<h3>维修调试记录 <span>${fixNo!}</span></h3>-->
<#--<table width="80%" style="margin-left: 10%;border-color: black;border-style: solid" border="1">-->

<#--    <#list list as item>-->

<#--        <#if item.inputType==1>-->

<#--            <tr>-->
<#--                <td style="text-align: right">${item.label!}:</td>-->
<#--                <td style="text-decoration: #0c0c0c"><u>${item.value!}</u></td>-->
<#--            </tr>-->
<#--        </#if>-->
<#--        <#if item.inputType==2>-->

<#--            <tr>-->
<#--                <td style="text-align: right">${item.label!}:</td>-->
<#--                <td style="text-decoration: #0c0c0c"><u>${item.value!}</u></td>-->
<#--            </tr>-->
<#--        </#if>-->

<#--    </#list>-->

<#--</table>-->
<h1 class="center" style="padding-top: 20px">维修调试记录</h1>
<div class="right"><span>记录编号：${fixNo!}</span></div>
<div><span>设备名称：${shebeimingcheng!}</span>
<#--    <label><input name="安全阀" type="checkbox" value="安全阀" checked="checked"/>安全阀</label>-->
<#--    <label><input name="安全阀" type="checkbox" value="安全阀"/>减压阀</label>-->
<#--    其他______________-->
</div>
<table>
    <tbody>
    <tr>
        <td>位 号</td>
        <td>公称通径Dn(mm)</td>
        <td>公称压力PN(MPa)</td>
        <td>型 号</td>
        <td>数量（台）</td>
        <td>修复次数__次__年</td>
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
        <td rowspan="2">工作介质</td>
        <td rowspan="2">
<#--            <div><label><input name="蒸汽" type="checkbox" checked="checked"/>蒸汽</label></div>-->
<#--            <div><label><input name="水" type="checkbox"/>水</label></div>-->
<#--            <div><label><input name="油" type="checkbox"/>油</label></div>-->
<#--            <div>其他：_____</div>-->
            <div>${gongzuojiezhi!}</div>
        </td>
        <td>工作压力</td>
        <td>${gongzuoyali!}</td>
        <td>整定压力Mpa</td>
        <td>${zhengdingyali!}</td>
    </tr>
    <tr>
        <td>螺纹（规格）</td>
        <td>${luowen!}</td>
        <td>调压范围Mpa</td>
        <td>${tiaoyafanwei!}</td>
    </tr>
    <tr>
        <td>产品编号</td>
        <td>${chanpinbianhao!}</td>
        <td>连接形式</td>
        <td>
<#--            <div><label><input name="法兰" type="checkbox"/>法兰</label></div>-->
<#--            <div>其他：_____</div>-->
            <div>${lianjiexingshi!}</div>
        </td>
        <td>密封面中径mm</td>
        <td>${gongzuoyali!}</td>
    </tr>
    <tr>
        <td>工作温度℃</td>
        <td>${gongzuowendu!}</td>
        <td>安装位置</td>
        <td>${anzhuangweizhi!}</td>
        <td>阀杆外露长度</td>
        <td>${faganwailouchangdu!}</td>
    </tr>
    <tr>
        <td>制造单位</td>
        <td>${zhizaodanwei!}</td>
        <td>产地</td>
        <td>
<#--            <div><label><input name="中国" type="checkbox"/>中国</label></div>-->
<#--            <div>进口：_____</div>-->
            <div>${chandi!}</div>
        </td>
        <td>现场调试</td>
        <td>
            <div><label><input name="是" type="checkbox"  <#if shifouxianchangtiaoshi==1>checked="checked"</#if>/>是</label></div>
            <div><label><input name="否" type="checkbox"  <#if shifouxianchangtiaoshi==0>checked="checked"</#if>/>否</label></div>
        </td>
    </tr>
    <tr>
        <td>修复主要内容</td>
        <td colspan="5">

            <label><input name="拆卸" type="checkbox" <#if shifouchaixie==1>checked="checked"</#if>/>拆卸</label>
            <label><input name="安装" type="checkbox" <#if shifouanzhuang==1>checked="checked"</#if>/>安装</label>
            <label><input name="离线调试" type="checkbox" <#if shifoulixiantiaoshi==1>checked="checked"</#if>/>离线调试</label>
            <label><input name="强度试验" type="checkbox" <#if shifouqiangdushiyan==1>checked="checked"</#if>/>强度试验</label>
            <label><input name="密封试验" type="checkbox" <#if shifoumifengshiyan==1>checked="checked"</#if>/>密封试验</label>



            <label><input name="解体" type="checkbox" <#if shifoujieti==1>checked="checked"</#if>/>解体</label>
            <label><input name="清洗" type="checkbox" <#if shifouqingxi==1>checked="checked"</#if>/>清洗</label>
            <label><input name="膜片清理" type="checkbox" <#if shifoumopianchuli==1>checked="checked"</#if>/>膜片清理</label>
<#--            <label><input name="弹簧处理" type="checkbox" <#if tanhuangchuli==1>checked="checked"</#if>/>弹簧处理:${tanhuangchuli!}</label>-->
            <div class="gap"><label>弹簧处理：${tanhuangchuli!}</label></div>

            <div class="gap"><label><input name="密封件更换" type="checkbox" <#if shifoumifengjiangenghuan==1>checked="checked"</#if>/>密封件更换:${mifengjiangenghuan!}</label></div>
            <div class="gap"><label><input name="密封系统维修" type="checkbox" <#if shifoumifengxitongweixiu==1>checked="checked"</#if>/>密封系统维修:${mifengxitongweixiu!}</label></div>
            <div class="gap"><label><input name="机械加工处理" type="checkbox" <#if shifoujiexiejiagongchuli==1>checked="checked"</#if>/>机械加工处理:${jiexiejiagongchuli!}</label></div>
            <div class="gap"><label><input name="紧固件更换" type="checkbox" <#if shifoujingujiangenghuan==1>checked="checked"</#if>/>紧固件更换:${jingujiangenghuan!}</label></div>
            <div class="gap"><label><input name="部件校正处理" type="checkbox" <#if shifoubujianjiaozhengchuli==1>checked="checked"</#if>/>部件校正处理:${bujianjiaozhengchuli!}</label></div>
            <div class="gap">特殊处理：
                <#--                <label><input name="脱脂" type="checkbox" <#if shifoujingujiangenghuan==1>checked="checked"</#if>/>脱脂(高纯酒精/四氯化碳)</label>-->
                <#--                <label><input name="其他" type="checkbox"/>其他：___________</label>-->
                <label>${teshuchuli!}</label>
            </div>
            <div class="gap"><label><input name="是否处理后检测" type="checkbox" <#if shifouchulihoujiance==1>checked="checked"</#if>/>处理后检测:${chulihoujiance!}</label></div>
            <div class="gap"><label>其他部件处理：${qitabujianchuli!}</label></div>

        </td>
    </tr>
    <tr>
        <td colspan="3">
            <div>承修单位：</div>
            <div class="center">${chengxiudanwei!}</div>
            <div class="center">工程部</div>
            <div class="sign">维修人员签字：________ 日期：_________</div>
            <div class="sign">校验人员签字：________ 日期：_________</div>
        </td>
        <td colspan="3">
            <div class="gap">使用单位:${shiyongdanwei!}</div>
            <div class="gap">现场人员签字：______日期:__________</div>
            <div class="gap">负责人签字：_______ 日期:__________</div>
            <div class="gap">主管签字：________ 日期:__________</div>
        </td>
    </tr>
    </tbody>
</table>
</body>
</html>