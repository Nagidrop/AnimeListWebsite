<%-- 
    Document   : editseason
    Created on : Jul 20, 2020, 10:04:16 AM
    Author     : PC
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="animelist.model.GenreDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
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
                <th><b>GenreID</b></th>
                <th><b>name</b></th>
                <th><b>Delete</b></th>
                <th><b>Update</b></th>
            </tr>
         <%--        <%
                int i = 0;
                ArrayList<GenreDTO> std = (ArrayList<GenreDTO>) request.getAttribute("listGenre");
                for(GenreDTO g : std){
                    System.out.println("e.toString()");
                } 
                for(GenreDTO g: std) {%>
                <tr>
                    <form action="CRUD" method="get" >
                    <td><input type="text" name="id" value="<%=g.getId()%>"></td>
                    <td><input type="text" name="name" value="<%=g.getName()%>"></td>
                    <td><a href="CRUD?action=delete&genreid=<%=g.getId()%>" onlick="return confirm('Are you sure to delete?)')">Delete</a></td>
                    <input type="hidden" name="action" value="update">
                    <td><button value="submit" onclick="return confirm('Are you sure to update')">Update</button></td>
                </form>
                </tr>
                <%}%>--%>
             <s:iterator value="genres">
                <tr>
                    <td><input type='text' value='<s:property value="id"/>' </td>
                    <td><input type='text' value='<s:property value="name"/>' </td>
                    <td><a href="CRUD?action=delete&genreid='id'" onclick="return confirm('Are you sure to delete?')">Delete</a> </td>
                    <input type="hidden" name="action" value="update">
                    <td><button value="submit" onclick="return confirm('Are you sure to update')">Update</button></td>
                </tr>
            </s:iterator>
        </table>
            </s:form>
    </body>
</html>
