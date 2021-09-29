<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head><meta http-equiv="Content-Type" content="text/html; charset=utf-8" /><title>
	学生信息管理平台
</title>
	<link href="../../Style/StudentStyle.css" rel="stylesheet" type="text/css" />
	<link href="../../Script/jBox/Skins/Blue/jbox.css" rel="stylesheet" type="text/css" />
	<link href="../../Style/ks.css" rel="stylesheet" type="text/css" />
	<link href="../../css/mine.css" type="text/css" rel="stylesheet">
    <script src="../../Script/jBox/jquery-1.4.2.min.js" type="text/javascript"></script>
    <script src="../../Script/jBox/jquery.jBox-2.3.min.js" type="text/javascript"></script>
    <script src="../../Script/jBox/i18n/jquery.jBox-zh-CN.js" type="text/javascript"></script>
    <script src="../../Script/Common.js" type="text/javascript"></script>
    <script src="../../Script/Data.js" type="text/javascript"></script>
    <script>
	function del(roleId){
		var sel = confirm("确认删除？");
		if(sel){
		    location.href = "/power/role/roles?method=delete&roleId="+roleId;
        }

	}

</script>
</head>
<body>

   

<div class="div_head" style="width: 100%;text-align:center;">
		<span> <span style="float:left">当前位置是：权限管理-》角色管理</span> <span
			style="float:right;margin-right: 8px;font-weight: bold">
			<a style="text-decoration: none;" href="/power/role/roles?method=selectMenus">【新增角色】</a>&nbsp;&nbsp;&nbsp;&nbsp;
		</span>
		</span>
	</div>

<div class="morebt">
 
</div>





 <div class="cztable">
        
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tbody>
                <tr style="height: 25px" align="center">
                	<th><input type="checkbox"/></th>
                    <th scope="col">
                        序号
                    </th>
                    
                    <th scope="col">
                        角色名称
                    </th>
                    <th scope="col">
                        状态
                    </th>
                    <th scope="col" width="300px">
                        操作
                    </th>
                </tr>
                
               
                <c:forEach items="${p1.dataList}" var="r" varStatus="sta">
                <tr align="center">
                    <th><input type="checkbox"/></th>
                    <td>
                        ${r.roleId}
                    </td>
                    <td>
                        ${r.roleName}
                    </td>                    
                    <td>&nbsp;
                        ${r.roleState==1?'启用':'未启用'}
                    </td>
                    <td>&nbsp;
                    	<a href="javascript:alert('操作成功！');">启用</a>
                        <a href="/power/role/getMenuList?method=show&roleId=${r.roleId}">详情</a>
                        <a href="/power/role/getMenuList?method=edit&roleId=${r.roleId}">修改</a>
						<a href="javascript:void(0)" onclick="del(${r.roleId});return false" class="tablelink"> 删除</a>
                    </td>
                </tr>
                </c:forEach>
               
                
            </tbody>
        </table>
        
        <div class='MainStyle'>
            <div class=''>
                <a href='/power/role/roles?method=select' target='_self'>首页</a>
            </div>

            <div class=''>
                <a href='/power/role/roles?method=select&pageIndex=${p1.pageIndex-1<=0?1:p1.pageIndex-1}' target='_self'>上一页</a>
            </div>

            <c:forEach begin="1" end="${p1.totalPages}" var="indexa">
            <div class='${p1.pageIndex==indexa?'NowItemStyle':''}'>
                <a href='/power/role/roles?method=select&pageIndex=${indexa}' target='_self'>${indexa}</a>
            </div>
            </c:forEach>


            <div class=''>
                <a href='/power/role/roles?method=select&pageIndex=${p1.pageIndex+1>=p1.totalPages?p1.totalPages:p1.pageIndex+1}' target='_self'>下一页</a>
            </div>

            <div class=''>
                <a href='/power/role/roles?method=select&pageIndex=${p1.totalPages}' target='_self'>尾页</a>
            </div>

            <div class=''>总共<b>${p1.total}</b>条数据</div>
            <div class=''>每页<b>${p1.pageSize}</b>条数据</div>
            <div class=''><b>${p1.pageIndex}</b>/${p1.totalPages}</div>
            <div class='SearchStyle'>
                <input type='text' id='john_Page_Search' onkeydown="if(event.keyCode == 13){page_searchIndex(${p1.totalPages});}"/>
            </div>
            <div class=''>
              <input type='button' value='Go' onclick="page_searchIndex()"/>
            </div>

        </div>

        <script>
             function page_searchIndex(totalPages){
                 var searchText = document.getElementById('john_Page_Search');
                 var searchIndex = searchText != null && searchText.value != '' ? parseInt(searchText.value) : 0;
                 if(searchIndex > 0 && searchIndex <= totalPages) {
                     window.location='/power/role/roles?method=select&pageIndex=' + searchIndex;
                 }else {
                     alert('需要跳转的页码不能超出范围！');
                 }
             }
        </script>

        </div>
    </div>

    </div>
</body>
</html>