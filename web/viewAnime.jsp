<%-- 
    Document   : viewAnime
    Created on : Jul 19, 2020, 5:04:19 PM
    Author     : Quan Duc Loc CE140037 (SE1401)
--%>

<%@page import="animelist.model.GenreDTO"%>
<%@page import="animelist.model.StudioDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="animelist.model.AnimeDTO"%>
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
            body{
                background: -webkit-linear-gradient(left, #3931af, #00c6ff);
            }
            .emp-profile{
                padding: 3%;
                margin-top: 3%;
                margin-bottom: 3%;
                border-radius: 0.5rem;
                background: #fff;
            }
            .profile-img{
                text-align: center;
            }
            .profile-img img{
                width: 70%;
                height: 100%;
            }
            .profile-img .file {
                position: relative;
                overflow: hidden;
                margin-top: -20%;
                width: 70%;
                border: none;
                border-radius: 0;
                font-size: 15px;
                background: #212529b8;
            }
            .profile-img .file input {
                position: absolute;
                opacity: 0;
                right: 0;
                top: 0;
            }
            .profile-head h5{
                color: #333;
            }
            .profile-head h6{
                color: #0062cc;
            }
            .profile-edit-btn{
                border: none;
                border-radius: 1.5rem;
                width: 70%;
                padding: 2%;
                font-weight: 600;
                color: #6c757d;
                cursor: pointer;
            }
            .proile-rating{
                font-size: 12px;
                color: #818182;
                margin-top: 5%;
            }
            .proile-rating span{
                color: #495057;
                font-size: 15px;
                font-weight: 600;
            }
            .profile-head .nav-tabs{
                margin-bottom:5%;
            }
            .profile-head .nav-tabs .nav-link{
                font-weight:600;
                border: none;
            }
            .profile-head .nav-tabs .nav-link.active{
                border: none;
                border-bottom:2px solid #0062cc;
            }
            .profile-work{
                padding: 14%;
                margin-top: -15%;
            }
            .profile-work p{
                font-size: 12px;
                color: #818182;
                font-weight: 600;
                margin-top: 10%;
            }
            .profile-work a{
                text-decoration: none;
                color: #495057;
                font-weight: 600;
                font-size: 14px;
            }
            .profile-work ul{
                list-style: none;
            }
            .profile-tab label{
                font-weight: 600;
            }
            .profile-tab p{
                font-weight: 600;
                color: #0062cc;
            }

            .synopsis{
                color: #000000!important;
                font-weight:normal!important;
            }
        </style>
        <title><s:property value="anime.name"/> - AnimeListWebsite</title>
    </head>
    <body style="background-image: url('images/bg-08.png')">
        <jsp:include page='header.jsp'/>
        <div class="container emp-profile">
            <form method="post">
                <div class="row">
                    <div class="col-md-4">
                        <div class="profile-img">
                            <img src="images/poster/<s:property value="anime.poster"/>" alt=""/>
                        </div>
                        <div class="profile-work">
                            <%if (session.getAttribute("user") != null) {%>
                            <h1>HELLO</h1>
                            <% } %>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="profile-head">
                            <h1>
                                <s:property value="anime.name" />
                            </h1>
                            <h6>
                                <s:property value="anime.season" />
                            </h6>
                            <p class="proile-rating">RANKINGS : <span>8/10</span></p>
                            <ul class="nav nav-tabs" id="myTab" role="tablist">
                                <li class="nav-item">
                                    <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">Synopsis</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false">Details</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" id="trailer-tab" data-toggle="tab" href="#trailer" role="tab" aria-controls="trailer" aria-selected="false">Trailer</a>
                                </li>

                            </ul>
                        </div>
                        <div class="tab-content profile-tab" id="myTabContent">
                            <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                                <p class="synopsis"> <s:property value="anime.description" /></p>
                            </div>    
                            <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                                <div class="row">
                                    <div class="col-md-6">
                                        <label>Type</label>
                                    </div>
                                    <div class="col-md-6">
                                        <p><s:property value="anime.type" /></p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <label>Episodes</label>
                                    </div>
                                    <div class="col-md-6">
                                        <p><s:property value="anime.episodes" /></p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <label>Status</label>
                                    </div>
                                    <div class="col-md-6">
                                        <p><s:property value="anime.status" /></p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <label>Release Date</label>
                                    </div>
                                    <div class="col-md-6">
                                        <p><s:property value="anime.releaseDate" /></p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <label>Studio(s)</label>
                                    </div>
                                    <div class="col-md-6">
                                        <% ArrayList<StudioDTO> studioList = (ArrayList<StudioDTO>) request.getAttribute("StudioList");
                                            int studioIndex = 0;
                                            for (StudioDTO studio : studioList) {
                                                if (studioIndex == 0) {
                                        %>    
                                        <p><%= studio.getName()%>
                                            <% } else {%>
                                            <%= ", " + studio.getName()%> 
                                            <% }
                                                    studioIndex++;
                                                }%>
                                        </p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <label>Genre(s)</label>
                                    </div>
                                    <div class="col-md-6">
                                        <% ArrayList<GenreDTO> genreList = (ArrayList<GenreDTO>) request.getAttribute("GenreList");
                                            int genreIndex = 0;
                                            for (GenreDTO genre : genreList) {
                                                if (genreIndex == 0) {
                                        %>    
                                        <p> <%= genre.getName()%>
                                            <% } else {%>
                                            <%= ", " + genre.getName()%> 
                                            <% }
                                                    genreIndex++;
                                                }%>
                                        </p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <label>Duration</label>
                                    </div>
                                    <div class="col-md-6">
                                        <p><s:property value="anime.duration" /></p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <label>Rating</label>
                                    </div>
                                    <div class="col-md-6">
                                        <p><s:property value="anime.rating" /></p>
                                    </div>
                                </div>
                            </div>
                            <div class="tab-pane fade" id="trailer" role="tabpanel" aria-labelledby="trailer-tab">
                                <iframe width="500" height="300" src="<s:property value="anime.trailer"/>  " frameborder="0" allow="encrypted-media" allowfullscreen></iframe>
                            </div>  
                        </div>
                    </div>
                    <div class="col-md-2">
                        <input type="submit" class="profile-edit-btn" name="btnAddMore" value="Edit Profile"/>
                    </div>
                    <div class="row">
                        <div class="col-md-4">

                        </div>
                        <div class="col-md-8">

                        </div>
                    </div>
                </div>
            </form>           
        </div>
    </body>
</html>
