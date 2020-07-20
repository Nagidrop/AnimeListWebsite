<%-- 
    Document   : editseason
    Created on : Jul 20, 2020, 10:04:16 AM
    Author     : PC
--%>

<%@page import="java.util.List"%>
<%@page import="animelist.model.SeasonDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Display Genre</title>
    </head>
    <body>
        <s:form action="genreView">
            <h1>Display Genre List</h1>
            <table border="1" width="600" align="center">
                <tr bgcolor="00FF7F">
                    <th><b>SeasonID</b></th>
                    <th><b>name</b></th>
                </tr>
                <s:iterator value="seasons" status="dto">
                    <tr>
                        <td><input type='text' value='<s:property value="id"/>'</td>
                        <td><input type='text' value='<s:property value="name"/>' </td>
                        <td><a href="CRUD?action=delete&seasonid='id'" onclick="return confirm('Are you sure to delete?')">Delete</a> </td>
                    <input type="hidden" name="action" value="update">
                    <td><button value="submit" onclick="return confirm('Are you sure to update')">Update</button></td>
                </tr>
            </s:iterator>
        </table>
    </s:form>
</body>
</html>