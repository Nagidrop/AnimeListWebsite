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
            .emp-details{
                padding: 3%;
                margin-top: 3%;
                margin-bottom: 3%;
                border-radius: 0.5rem;
                background: #fff;
            }
            .details-img{
                text-align: center;
            }
            .details-img img{
                width: 70%;
                height: 100%;
            }
            .details-img .file {
                position: relative;
                overflow: hidden;
                margin-top: -20%;
                width: 70%;
                border: none;
                border-radius: 0;
                font-size: 15px;
                background: #212529b8;
            }
            .details-img .file input {
                position: absolute;
                opacity: 0;
                right: 0;
                top: 0;
            }
            .details-head h5{
                color: #333;
            }
            .details-head h6{
                color: #0062cc;
            }
            .details-edit-btn{
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
            .details-head .nav-tabs{
                margin-bottom:5%;
            }
            .details-head .nav-tabs .nav-link{
                font-weight:600;
                border: none;
            }
            .details-head .nav-tabs .nav-link.active{
                border: none;
                border-bottom:2px solid #0062cc;
            }
            .details-work{
                padding: 14%;
                margin-top: -15%;
            }
            .details-work p{
                font-size: 12px;
                color: #818182;
                font-weight: 600;
                margin-top: 10%;
            }
            .details-work a{
                text-decoration: none;
                color: #495057;
                font-weight: 600;
                font-size: 14px;
            }
            .details-work ul{
                list-style: none;
            }
            .details-tab label{
                font-weight: 600;
            }
            .details-tab p{
                font-weight: 600;
                color: #0062cc;
            }

            .synopsis{
                color: #000000!important;
                font-weight:normal!important;
            }

            .btn-group{
                margin-top: 15%!important;
                width: 200px;
            }

            .addtolist-block {
                padding-bottom: 40px;
                padding-top: 5px;
            }

            .btn-primary {
                color: #007bff;
                background-color: #fff;
                border-color: #007bff;
                width: 100%;
            }

            .spaceit {
                margin: 3px 0;
                padding: 3px 0;
            }
        </style>
        <title><s:property value="anime.name"/> - WibuAnimeList</title>
    </head>
    <body style="background-image: url('images/bg-08.png')">
        <jsp:include page='header.jsp'/>
        <div class="container emp-details">
            <form method="post">
                <div class="row">
                    <div class="col-md-4">
                        <div class="details-img">
                            <img src="images/poster/<s:property value="anime.poster"/>" alt=""/>
                        </div>
                        <div class="details-work">
                            <%if (session.getAttribute("user") != null) {%>
                            <p>
                                <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
                                    Add To List
                                </button>
                            </p>
                            <div class="collapse" id="collapseExample">
                                <div class="card card-body" style="padding: 0.9rem!important;">
                                    <div id="addtolist" class="addtolist-block js-anime-addtolist-block" style="padding: 0!important;">
                                        <input type="hidden" id="myinfo_anime_id" value="39547">
                                        <input type="hidden" id="myinfo_curstatus" value="">

                                        <span class="notice_open_public pb4">* Your list is public by default.</span>
                                        <table border="0" cellpadding="0" cellspacing="0" width="100%" style="margin-top: 5%!important;">
                                            <tbody><tr>
                                                    <td class="spaceit">Status:</td>
                                                    <td class="spaceit">
                                                        <select name="myinfo_status" id="myinfo_status" class="inputtext js-anime-status-dropdown form-control-sm" style="border: 1px solid #ced4da!important; font-size: 0.95rem!important;"><option value="1">Watching</option><option value="2">Completed</option><option value="3">On-Hold</option><option value="4">Dropped</option><option value="6">Plan to Watch</option></select>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="spaceit">Eps Seen:</td>
                                                    <td class="spaceit">
                                                        <input type="text" id="myinfo_watchedeps" name="myinfo_watchedeps" size="3" class="inputtext form-control-sm" value="" style="border: 1px solid #ced4da!important;"> / <span id="curEps">                                
                                                            <s:if test="%{anime.episodes != 0}">
                                                                <s:property value="anime.episodes" />
                                                            </s:if>
                                                            <s:else>
                                                                ?
                                                            </s:else></span></td>
                                                </tr>
                                                <tr>
                                                    <td>&nbsp;</td>
                                                    <td>
                                                        <input type="button" name="myinfo_submit" value="Add" class="inputButton btn-middle flat js-anime-add-button" style="margin-top: 5%!important;">
                                                    </td>
                                                </tr>
                                            </tbody></table>
                                        <div id="myinfoDisplay" style="padding-left: 89px; margin-top: 3px;"></div>
                                    </div>
                                </div>
                            </div>

                            <% } %>
                        </div>
                    </div>
                    <div class="col-md-8">
                        <div class="details-head">
                            <h1>
                                <s:property value="anime.name" />
                            </h1>
                            <h6>
                                <s:property value="anime.season.name" />
                            </h6>
                            <p class="proile-rating">RANKINGS : <span>8/10</span></p>
                            <ul class="nav nav-tabs" id="myTab" role="tablist">
                                <li class="nav-item">
                                    <a class="nav-link active" id="synopsis-tab" data-toggle="tab" href="#synopsis" role="tab" aria-controls="synopsis" aria-selected="true">Synopsis</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" id="details-tab" data-toggle="tab" href="#details" role="tab" aria-controls="details" aria-selected="false">Details</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" id="trailer-tab" data-toggle="tab" href="#trailer" role="tab" aria-controls="trailer" aria-selected="false">Trailer</a>
                                </li>

                            </ul>
                        </div>
                        <div class="tab-content details-tab" id="myTabContent">
                            <div class="tab-pane fade show active" id="synopsis" role="tabpanel" aria-labelledby="synopsis-tab">
                                <p class="synopsis"> <s:property value="anime.description" /></p>
                            </div>
                            <div class="tab-pane fade" id="details" role="tabpanel" aria-labelledby="details-tab">
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
                                <s:if test="%{anime.trailer != null}">
                                    <div style="text-align: center;"><iframe width="500" height="300" src="<s:property value="anime.trailer"/>" frameborder="0" allow="encrypted-media" allowfullscreen></iframe></div>
                                        </s:if>
                                        <s:else>
                                    <p style="text-align: center; color: #000000;">There is no trailer available for this anime title</p>
                                </s:else>
                            </div>
                        </div>
                    </div>
                    <!--                    <div class="col-md-2">
                                            <input type="submit" class="details-edit-btn" name="btnAddMore" value="Edit Profile"/>
                                        </div>-->
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
