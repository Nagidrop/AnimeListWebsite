<%-- 
    Document   : hearder
    Created on : Jul 15, 2020, 7:25:43 PM
    Author     : HAPPY
--%>

<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
    <a class="navbar-brand" href="#">WibuAnimeList</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample03" aria-controls="navbarsExample03" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarsExample03">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="dropdown03" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">Anime</a>
                <div class="dropdown-menu" aria-labelledby="dropdown03">
                    <a class="dropdown-item" href="AnimeSearch.jsp">Anime Search</a>
                    <a class="dropdown-item" href="#">Top Anime</a>
                    <a class="dropdown-item" href="#">Seasonal Anime</a>
                </div>
            </li>


        </ul>


    </div>

    <form class="form-inline my-2 my-md-0" id="search-form">
        <input class="form-control" type="text" placeholder="Search Anime...">
        <input class="btn btn-search form-control" type="submit" value="Search">
    </form>
    <div class="col-sm-1">
        <a type="button" href="login.action" class="btn btn-primary ">Login</a>
    </div>
     <div class="col-sm-1">
        <a type="button" href="" class="btn btn-success ">Sign Up</a>
    </div>

</nav>
