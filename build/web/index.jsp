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
        <style>
            .img-thumbnail {
                padding: 0!important;
                background-color: #fff;
                border: 1px solid #dee2e6;
                border-radius: .25rem;
                max-width: 100%;
                height: auto;
            }
        </style>
        <title>Wibu Anime List</title>
    </head>
    <body class="container">
        <jsp:include page='header.jsp'/>
        <div class=""><p></div>

        <div class="row mb-3 ">
            <s:iterator value="listAnimeDTOs" status="dto">
                <div class="col-md-3">
                    <div class="card " style="margin: 2%;" title="<s:property value="name"/>">
                        <a href="viewAnime?animeID=<s:property  value="id"/>">
                            <div class="card-body">
                                <img class="img-thumbnail"  style="width: 200px;height: 280px"  src="images/poster/<s:property  value="poster"/>" style="max-height: 100%; max-width: 100%">
                            </div>

                            <div class="card-footer" style="text-align: center;" title="<s:property value="name"/>" >
                                <s:if test="%{name.length() > 20}">
                                    <s:property value="name.substring(0,20)"/>...

                                </s:if>
                                <s:else>
                                    <s:property value="name"/>
                                </s:else>
                            </div>
                        </a>
                    </div>
                </div>
            </s:iterator>

        </div>

    </body>
</html>
