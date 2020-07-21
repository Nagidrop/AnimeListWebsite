<%-- 
    Document   : header
    Created on : Jul 15, 2020, 7:25:43 PM
    Author     : HAPPY
--%>
<%@taglib uri="/struts-tags" prefix="s"%>

<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
    <a class="navbar-brand" href="<s:url action="index"/>">WibuAnimeList</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample03" aria-controls="navbarsExample03" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarsExample03">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="dropdown03" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">Anime</a>
                <div class="dropdown-menu" aria-labelledby="dropdown03">
                    <a class="dropdown-item" href="<s:url action="initSearchPage"/>">Anime Search</a>
                    <a class="dropdown-item" href="comingSoon.jsp">Top Anime</a>
                    <a class="dropdown-item" href="comingSoon.jsp">Seasonal Anime</a>
                </div>
            </li>


        </ul>


    </div>

    <s:form cssClass="form-inline my-2 my-md-0" id="search-form" action="search">
        <input class="form-control" type="text" name="searchvalue" placeholder="Search Anime..." style="margin-right: 10px!important;">
        <input class="btn btn-search form-control" type="submit" value="Search" style="margin-right: 20px!important;">
    </s:form>
    <%if(session.getAttribute("user")==null){%>
    <div class="col-sm-1">
        <a type="button" href="prelogin.action" class="btn btn-primary ">Login</a>
    </div>
    <div class="col-sm-1">
        <a type="button" href="register.jsp" class="btn btn-success ">Sign Up</a>
    </div>
    <%}else{%>

    <ul class="nav navbar-nav">
        <li class="dropdown">
            <a href="#" class="nav-link dropdown-toggle" id="navbarDropdown" data-toggle="dropdown" aria-expanded="false"> 

                Welcome, <s:property value="%{#session.fullname}"/> <b class="caret"></b>
            </a>
            <div class="dropdown-menu dropdown-menu-right">
                <a class="dropdown-item" href="viewAnimeList?accountID=<s:property value="%{#session.id}"/>&listStatus=0">View Own List</a>
                <a class="dropdown-item" href="<s:url action="ViewUserTotalAnimes"/>">Personal Info</a>
                <a class="dropdown-item" href="changepassword.jsp">Change Password</a>
                <a class="dropdown-item" href="editinfo.jsp">Change Info</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="logout">Log Out</a>
            </div>
        </li>
    </ul>


    <%}%>


</nav>
