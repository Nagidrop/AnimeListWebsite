<%@taglib uri = "/struts-tags" prefix = "s" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="animelist.model.ListDTO"%>
<%@page import="animelist.model.GenreDTO"%>
<%@page import="animelist.model.StudioDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="animelist.model.AnimeDTO"%>
<%@page import="animelist.model.AnimeListDAO"%>

<!DOCTYPE html>
<html lang="en">

    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Anime Manager</title>
        <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-beta.1/dist/css/select2.min.css" rel="stylesheet" />
        <!-- Custom fonts for this template -->
        <link href="admin/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <!-- Custom styles for this template -->
        <link href="admin/css/sb-admin-2.min.css" rel="stylesheet">

        <!-- Custom styles for this page -->
        <link href="admin/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">

    </head>

    <body id="page-top">

        <!-- Page Wrapper -->
        <div id="wrapper">

            <!-- Sidebar -->
            <jsp:include page='sidebar.jsp'/>
            <!-- End of Sidebar -->

            <!-- Content Wrapper -->
            <div id="content-wrapper" class="d-flex flex-column">

                <!-- Main Content -->
                <div id="content">

                    <!-- Topbar -->
                    <jsp:include page='navbar.jsp'/>
                    <!-- End of Topbar -->

                    <!-- Begin Page Content -->
                    <div class="container-fluid">

                        <!-- Page Heading -->
                        <h1 class="h3 mb-2 text-gray-800">Anime Manager</h1>
                        <!--          <p class="mb-4">DataTables is a third party plugin that is used to generate the demo table below. For more information about DataTables, please visit the <a target="_blank" href="https://datatables.net">official DataTables documentation</a>.</p>-->

                        <!-- DataTales Example -->
                        <div class="card shadow mb-4">
                            <div class="card shadow mb-4">
                                <div class="col-md-6" style="padding: 0.8%;">
                                    <h6 class="m-0 font-weight-bold text-primary">List Anime</h6>
                                </div>
                                <div class="col-md-6">
                                    <s:form action="createAnimeAction" method="post" enctype="multipart/form-data" theme="simple">
                                        <div class="form-group">
                                            <label>Name:</label>
                                            <input id="name" name="name" required class="form-control">
                                        </div>
                                        <div class="form-group">
                                            <label>Type:</label>
                                            <input id="type" name="type" required class="form-control">
                                        </div>
                                        <div class="form-group">
                                            <label>Release Date</label>
                                            <input type="date" id="releaseDate" name="releaseDate"  class="form-control">
                                        </div>
                                        <div class="form-group">
                                            <label>Rating</label>

                                            <input id="rating" name="rating"  class="form-control">
                                        </div>
                                        <div class="form-group">
                                            <label>Episodes</label>

                                            <input id="episodes" name="episodes"  type="number" class="form-control">
                                        </div>
                                        <div class="form-group">
                                            <label>Status</label>

                                            <input id="status" name="status"  type="text" class="form-control">
                                        </div>
                                        <div class="form-group">
                                            <label>Duration</label>

                                            <input id="duration" name="duration"  type="text" class="form-control">
                                        </div>
                                        <div class="form-group">
                                            <label>Description</label>

                                            <textarea id="description" name="description"  type="text" class="form-control" rows="4" cols="4">
                                            
                                            </textarea>   
                                        </div>
                                        <div class="form-group">
                                            <label>Poster</label>

                                            <s:file id="poster" name="poster" cssClass="form-control" theme="simple"/>
                                        </div>
                                        <div class="form-group">
                                            <label>Trailer URL</label>

                                            <input id="trailer" name="trailer"  type="text" class="form-control">
                                        </div>
                                        <div class="form-group">
                                            <label>Season</label>
                                            <select id="season" name="season" class="form-control">
                                                <s:iterator value="seasonArrayList">
                                                    <option value=" <s:property value="id"></s:property>">
                                                        <s:property value="name"></s:property>
                                                        </option>
                                                </s:iterator>
                                            </select>    
                                        </div>
                                        <div class="form-group">
                                            <label>Studios</label>
                                            <select id="studio" name="studio" class="form-control" multiple>
                                                <s:iterator value="studiosArrayList">
                                                    <option value=" <s:property value="id"></s:property>">
                                                        <s:property value="name"></s:property>
                                                        </option>
                                                </s:iterator>
                                            </select>    
                                        </div>
                                        <div class="form-group">
                                            <label>    Genres</label>

                                            <select id="genre" name="genre" class="form-control" multiple>
                                                <s:iterator value="genreArrayList">
                                                    <option value="<s:property value="id"></s:property>">
                                                        <s:property value="name"></s:property>
                                                        </option>
                                                </s:iterator>
                                            </select>
                                        </div>


                                        <p>
                                            <input type="submit" value="Create" class="btn btn-success form-control">
                                    </s:form>

                                </div>

                            </div>


                        </div>

                    </div>
                    <!-- /.container-fluid -->        
                    <footer class="sticky-footer bg-white">
                        <div class="container my-auto">
                            <div class="copyright text-center my-auto">
                                <span>Copyright &copy; WibuTeam Website 2020</span>
                            </div>
                        </div>
                    </footer>
                    <!-- End of Footer -->

                </div>
                <!-- End of Content Wrapper -->

            </div>
            <!-- End of Page Wrapper -->

            <!-- Scroll to Top Button-->
            <a class="scroll-to-top rounded" href="#page-top">
                <i class="fas fa-angle-up"></i>
            </a>

            <script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-beta.1/dist/js/select2.min.js"></script>
            G
            <script>
                $("#studio").select2();
                $("#genre").select2();
                $("#season").select2();


                function fillInfo(id, username, fullname, email, gender) {
                    console.log(fullname)
                    $("#id").val(id);
                    $("#username").val(username);
                    $("#fullname").val(fullname);
                    $("#email").val(email);
                    $("#gender").html("");
                    var i;
                    var label = ["Female", "Male", "Other"];
                    for (i = 0; i < label.length; i++) {
                        if (gender == i) {
                            $("#gender").append('<option value="' + i + '" selected>' + label[i] + '</option>');

                        } else {
                            $("#gender").append('<option value="' + i + '" >' + label[i] + '</option>');

                        }

                    }
                    $("#exampleModal").modal();

                }
                $("#btn-submit").click(function () {
                    $("#info-form").submit();
                });
            </script>

            <!-- Bootstrap core JavaScript-->
            <script src="admin/vendor/jquery/jquery.min.js"></script>
            <script src="admin/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

            <!-- Core plugin JavaScript-->
            <script src="admin/vendor/jquery-easing/jquery.easing.min.js"></script>

            <!-- Custom scripts for all pages-->
            <script src="admin/js/sb-admin-2.min.js"></script>

            <!-- Page level plugins -->
            <script src="admin/vendor/datatables/jquery.dataTables.min.js"></script>
            <script src="admin/vendor/datatables/dataTables.bootstrap4.min.js"></script>

            <!-- Page level custom scripts -->
            <script src="admin/js/demo/datatables-demo.js"></script>

    </body>

</html>
