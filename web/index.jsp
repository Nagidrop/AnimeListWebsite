<%-- 
    Document   : index
    Created on : Jul 15, 2020, 6:50:18 PM
    Author     : Quan Duc Loc CE140037 (SE1401)
--%>

<%@page import="animelist.model.AccountDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="animelist.model.AnimeDTO"%>
<%@page import="animelist.model.AnimeDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri = "/struts-tags" prefix = "s" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <script src="https://use.fontawesome.com/7a37b2739f.js"></script>
        <title>Wibu Anime List</title>
    </head>
    <body class="container">
        <jsp:include page='header.jsp'/>
        <div class=""><p></div>

        <div class="row mb-3 ">
            <s:iterator value="listAnimeDTOs" status="dto">
                <div class="col-md-3">
                    <div class="card ">
                        <a href="viewAnime?animeID=<s:property  value="id"/>">
                            <div class="card-body">
                                <img  src="images/poster/<s:property  value="poster"/>">
                            </div>
                            <div class="card-footer">
                                <s:property value="name"/>
                            </div>
                        </a>
                    </div>
                </div>
            </s:iterator>

        </div>

    </body>
</html>
