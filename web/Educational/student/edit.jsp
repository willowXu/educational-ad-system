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
    

</head>
<body>

		<div class="div_head">
            <span>
                <span style="float:left">当前位置是：教务中心-》学生管理-》更新</span>
                <span style="float:right;margin-right: 8px;font-weight: bold"></span>
            </span>
        </div>
</div>
<div class="cztable">
    <form action="/Educational/student/studentServlet?method=update" method="post">
    <input type="hidden" name="stuid" value="${student.stuId}"/>
	<table border="1" width="100%" class="table_a">
                <tr  width="120px;">
                    <td width="10%">学号：<span style="color:red">*</span>：</td>
                    <td>
						<input type="text"  name="stuno" value="${student.stuNo}" />
					</td>
                </tr>

                <tr  width="120px;">
                    <td>姓名<span style="color:red">*</span>：</td>
                    <td>
						<input type="text"  name="stuname" value="${student.stuName}" />
					</td>
                </tr>
                 
                <tr>
                    <td>班级<span style="color:red">*</span>：</td>
                    <td>
                        <select name="gid">
                            <c:forEach items="${gradeList}" var="grade">
                        	<option value="${grade.gradeId}" ${student.gid==grade.gradeId?'selected':''}>${grade.gradeName}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>性别<span style="color:red">*</span>：</td>
                    <td>
                        <input type="radio" name="sex"  value="1"  ${student.sex==1?'checked':''}/>男
                        <input type="radio" name="sex"  value="0"  ${student.sex==0?'checked':''}/>女
                    </td>
                </tr>


				<tr>
                    <td>EMAIL：</td>
                    <td>
                        <input type="text" name="email" value="${student.email}" />
                    </td>                
                </tr>

				<tr>
                    <td>联系电话：</td>
                    <td>
                        <input type="text" name="phone" value="${student.phone}" />
                    </td>                
                </tr>

				<tr>
                    <td>户口所在地：</td>
                    <td>
                        <input type="text" name="registered" value="${student.registered}"  />
                    </td>                
                </tr>

				<tr>
                    <td>住址：</td>
                    <td>
                        <input type="text" name="address" value="${student.address}" />
                    </td>                
                </tr>
				<tr>
                    <td>政治面貌：</td>
                    <td>
                        <input type="text" name="politics" value="${student.politics}" />
                    </td>                
                </tr>
				<tr>
                    <td>身份证号：</td>
                    <td>
                        <input type="text" name="idnumber" value="${student.idNumber}" />
                    </td>                
                </tr>
				
				<tr>
                    <td>专业：</td>
                    <td>
                        <input type="text" name="profession" value="${student.profession}" />
                    </td>                
                </tr>
					
				
				<tr>
                    <td>简介<span style="color:red">*</span>：</td>
                    <td>
                        <textarea name=introduction">一个新开辟领域的探讨，探讨摸索</textarea>
                    </td>
                </tr>
				<tr>
					<td colspan=2 align="center">
						<input type="submit" value="保存"> 
					</td> 
				</tr>
			</table>
	</form>
</div>

            </div>
        </div>
    </div>
</body>
</html>
