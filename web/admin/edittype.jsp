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
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Display Studio</title>
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
        <s:form action="seasonView">
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
                            <h1 class="h3 mb-2 text-gray-800">Display Type List</h1>
                            <!--          <p class="mb-4">DataTables is a third party plugin that is used to generate the demo table below. For more information about DataTables, please visit the <a target="_blank" href="https://datatables.net">official DataTables documentation</a>.</p>-->

                            <!-- DataTales Example -->
                            <div class="card shadow mb-4">
                                <div class="card-header py-3">
                                    <h6 class="m-0 font-weight-bold text-primary">List Type</h6>
                                </div>
                                <div class="card-body">
                                    <div class="table-responsive">
                                        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                            <thead>
                                                <tr>
                                                    <th><b>Type</b></th>
                                                </tr>
                                            </thead>
                                            <tfoot>
                                                <tr>
                                                    <th><b>Type</b></th>
                                                </tr>
                                            </tfoot>
                                            <tbody>
                                                <s:iterator value="types" status="dto">
                                                    <tr>
                                                        <td><s:property/></td>
                                                        <td style="text-align: center">
                                                            <a class="btn-sm btn-success btn-circle"  onclick="fillInfo(<s:property value="id"/>, '<s:property value="name"/>')"> <i class="fa fa-edit" style="color: white"></i></a>
                                                        </td>
                                                        <td style="text-align: center">
                                                            <a class="btn-sm btn-danger btn-circle "><i class="fa fa-trash" style="color: white"></i></a>
                                                        </td>
                                                    </tr>
                                                </s:iterator>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- /.container-fluid -->

                        <!-- Modal -->
                        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLabel">Edit Studio Info</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <form action="updateStudio" method="post" id="info-form" accept-charset="utf-8">
                                            <div >
                                                <%-- <input  hidden id="id" name="id" value=> --%>

                                                <div class="form-group">
                                                    <label for="name">id:</label>
                                                    <input class="form-control" id="id" name="id" readonly>
                                                    <label for="fullname">name:</label>

                                                    <div class="form-group">
                                                        <input class="form-control" id="name" name="name" required>

                                                    </div>
                                                </div>
                                            </div>
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                            <input type="submit" id="btn-submit" class="btn btn-primary" value="Save">
                                        </form>

                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- End of Main Content -->

                        <!-- Footer -->
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
            </s:form>
            <script>
                function fillInfo(id, name) {
                    $("#id").val(id);
                    $("#name").val(name);
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