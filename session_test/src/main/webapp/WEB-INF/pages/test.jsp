<!doctype html>
<%@page contentType="text/html; charset=utf-8" %>
<%@page pageEncoding="utf-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Member Test</title>

    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

<body>

<div class="container">
    <div class="row">
        <div class="span8 offset2">
            <h1>Members(사용자) - 조회</h1>
            <form:form method="get" action="testList" commandName="member" class="form-horizontal" >
            <div class="control-group">
                <form:label cssClass="control-label" path="name">이름:</form:label>
                <div class="controls">
                    <form:input path="name"/>
                </div>
            </div>
            <div class="control-group">
                <form:label cssClass="control-label" path="email">이메일:</form:label>
                <div class="controls">
                    <form:input path="email"/>
                </div>
            </div>
            <div class="control-group">
                <form:label cssClass="control-label" path="phone">전화번호</form:label>
                <div class="controls">
                    <form:input path="phone"/>
                </div>
            </div>

            <div class="control-group">
                <div class="controls">
                    <input type="submit" value="Search" class="btn"/>
                    </form:form>
                </div>
            </div>

            <c:if test="${!empty members}">
                <h3>Members</h3>
                <table class="table table-bordered table-striped">
                    <thead>
                    <tr>
                        <th>이름</th>
                        <th>이메일</th>
                        <th>전화번호</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${members}" var="member">
                        <tr>
                            <td>${member.name}</td>
                            <td>${member.email}</td>
                            <td>${member.phone}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </div>
    </div>
</div>

</body>
</html>
