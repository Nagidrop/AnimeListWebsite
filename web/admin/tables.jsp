<%@taglib uri = "/struts-tags" prefix = "s" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>User Manager</title>

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
                        <h1 class="h3 mb-2 text-gray-800">User Manager</h1>
                        <!--          <p class="mb-4">DataTables is a third party plugin that is used to generate the demo table below. For more information about DataTables, please visit the <a target="_blank" href="https://datatables.net">official DataTables documentation</a>.</p>-->

                        <!-- DataTales Example -->
                        <div class="card shadow mb-4">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">List User</h6>
                            </div>
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                        <thead>
                                            <tr>
                                                <th>ID</th>
                                                <th>Username</th>
                                                <th>Full name</th>
                                                <th>Email</th>
                                                <th>Gender</th>
                                                <th></th><th></th>
                                            </tr>
                                        </thead>
                                        <tfoot>
                                            <tr>
                                                <th>ID</th>
                                                <th>Username</th>
                                                <th>Full name</th>
                                                <th>Email</th>
                                                <th>Gender</th>
                                                <th></th>
                                                <th></th>
                                            </tr>
                                        </tfoot>
                                        <tbody>
                                            <s:iterator value="listUser" status="dto">
                                                <tr>
                                                    <td><s:property value="id"/></td>
                                                    <td><s:property value="username"/></td>
                                                    <td><s:property value="fullName"/></td>
                                                    <td><s:property value="email"/></td>
                                                    <td>
                                                        <s:if test="%{gender==1}">
                                                            Male
                                                        </s:if>
                                                        <s:elseif test="%{gender==0}">
                                                            Female       
                                                        </s:elseif>  
                                                        <s:else>  
                                                            Other
                                                        </s:else>


                                                    </td>      
                                                    <td style="text-align: center">
                                                        <a class="btn-sm btn-success btn-circle"  onclick="fillInfo(<s:property value="id"/>, '<s:property value="username"/>', '<s:property value="fullName"/>', '<s:property value="email"/>',<s:property value="gender"/>)"> <i class="fa fa-edit" style="color: white"></i></a>
                                                    </td>
                                                    <td style="text-align: center">
                                                        <a class="btn-sm btn-danger btn-circle " href="deleteUser?id=<s:property value="id"/>"><i class="fa fa-trash" style="color: white"></i></a>                                  
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
                                    <h5 class="modal-title" id="exampleModalLabel">Edit User Info</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <form action="updateUser" method="post" id="info-form" accept-charset="utf-8">
                                        <div >
                                            <input  hidden id="id" name="id" value="">

                                            <div class="form-group">
                                                <label for="username">User name:</label>
                                                <input class="form-control" id="username" name="username" readonly>

                                            </div>
                                            <label for="fullname">Full name:</label>

                                            <div class="form-group">
                                                <input class="form-control" id="fullname" name="fullname" required>

                                            </div>

                                            <div class="form-group">
                                                <label for="email">Email:</label>
                                                <input class="form-control" id="email" name="email" >

                                            </div>
                                            <div class="form-group">
                                                <label for="password">Password(Fill if you want to change):</label>

                                                <input class="form-control" type="password" id="password" name="password">

                                            </div>
                                            <div class="form-group">
                                                <label for="gender">Gender</label>
                                                <select id="gender" name="gender" class="form-control" required></select>
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
            <a class="scroll-to-top rounded" href="#page-top">
                <i class="fas fa-angle-up"></i>
            </a>


            <script>
                function fillInfo(id, username, fullname, email, gender) {
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
