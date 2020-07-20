<%-- 
    Document   : editseason
    Created on : Jul 20, 2020, 10:04:16 AM
    Author     : PC
--%>

<%@page import="animelist.model.StudioDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Display Studio</title>
    </head>
    <body>
        <h1>Display Studio List</h1>
        <table border="1" width="600" align="center">
            <tr bgcolor="00FF7F">
                <th><b>StudioID</b></th>
                <th><b>name</b></th>
                <th><b>created_at</b></th>
                <th><b>deleted_at</b></th>
            </tr>
            <%
                int i = 0;
                ArrayList<StudioDTO> std = (ArrayList<StudioDTO>) request.getAttribute("studios");
                for(StudioDTO st : std){
                    System.out.println("e.toString()");
                } 
                for(StudioDTO st: std) {%>
                <tr>
                    <form action="CRUD" method="get" >
                    <td><input type="text" nam="id" value="<%=st.getId()%>"></td>
                    <td><input type="text" nam="name" value="<%=st.getName()%>"></td>
                    <td><a href="CRUD?action=delete&studioid=<%=st.getId()%>" onlick="return confirm('Are you sure to delete?)')">Delete</a></td>
                    <input type="hidden" name="action" value="update">
                    <td><button value="submit" onclick="return confirm('Are you sure to update')">Update</button></td>
                </form>
                </tr>
                <%}%>
        </table>
    </body>
</html>
