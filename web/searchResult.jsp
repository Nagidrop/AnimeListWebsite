<%-- 
    Document   : index
    Created on : Jul 15, 2020, 6:50:18 PM
    Author     : Quan Duc Loc CE140037 (SE1401)
--%>

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
        <title>Search Results - WibuAnimeList</title>
    </head>
    <body class="container">
        <jsp:include page='header.jsp'/>
        <%ArrayList<AnimeDTO> list =(ArrayList<AnimeDTO>) request.getAttribute("showlist");
            session.setAttribute("sortlist", list);
        %>
        <div class=""><p></div>
        <div class="row">
            <div class="col-md-7" ><h3>Search Result :</h3></div>
            <div class="col-md-5" >
                <div class="d-flex flex-row-reverse">

                    <div class="dropdown p-2">
                        <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Sort
                        </button>
                        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                            <a class="dropdown-item" href="<s:url action="sortAnimeByName"/>">Name</a>
                            <a class="dropdown-item" href="<s:url action="sortAnimeBySeason"/>">Season</a>
                            <a class="dropdown-item" href="<s:url action="sortAnimeByEpisode"/>">Episode</a>
                        </div>
                    </div>

                </div>
            </div>
        </div>

        <%if (list != null) {%>
        <div class="row mb-3 ">
            <%for (AnimeDTO item : list) {%>
            <div class="col-md-3">
                <div class="card " style="margin: 2%;" title=" <%=item.getName()%>">

                    <a href="viewAnime?animeID=<%= item.getId()%>">
                        <div class="card-body">
                            <img class="img-thumbnail" style="width: 200px;height: 280px"   src="images/poster/<%=item.getPoster()%>">
                        </div>
                        <div class="card-footer" style="text-align: center" title=" <%=item.getName()%>">
                            <%if (item.getName().length() > 20) {
                                    out.print(item.getName().substring(0, 20) + "...");
                                } else {
                                    out.print(item.getName());
                                }%>
                        </div>
                    </a>
                </div>

            </div>
            <%}
                } else {%>
            <div class="row">
                <h5>There is no anime meet the criteria</h5>
            </div>
            <%}%>
    </body>
</html>

