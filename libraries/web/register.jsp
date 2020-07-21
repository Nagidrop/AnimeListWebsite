<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Register - WibuAnimeList</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <script src="https://use.fontawesome.com/7a37b2739f.js"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!--===============================================================================================-->	
        <link rel="icon" type="image/png" href="images/icons/favicon.ico"/>
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="fonts/iconic/css/material-design-iconic-font.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
        <!--===============================================================================================-->	
        <link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/animsition/css/animsition.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
        <!--===============================================================================================-->	
        <link rel="stylesheet" type="text/css" href="vendor/daterangepicker/daterangepicker.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="css/util.css">
        <link rel="stylesheet" type="text/css" href="css/main.css">
        <!--===============================================================================================-->
        <script src="vendor/jquery/jquery-3.2.1.min.js"></script>
        <!--===============================================================================================-->
        <script src="vendor/animsition/js/animsition.min.js"></script>
        <!--===============================================================================================-->
        <script src="vendor/bootstrap/js/popper.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.min.js"></script>
        <!--===============================================================================================-->
        <script src="vendor/select2/select2.min.js"></script>
        <!--===============================================================================================-->
        <script src="vendor/daterangepicker/moment.min.js"></script>
        <script src="vendor/daterangepicker/daterangepicker.js"></script>
        <!--===============================================================================================-->
        <script src="vendor/countdowntime/countdowntime.js"></script>
        <!--===============================================================================================-->
        <script src="js/main.js"></script>
        <!--===============================================================================================-->
    </head>
    <body>
        <jsp:include page='header.jsp'/>

        <div class="limiter">
            <div class="container-login100" style="background-image: url('images/bg-02.jpg');">
                <div class="wrap-register100">
                    <s:form class="login100-form validate-form" action="register">
                        <span class="login100-form-logo">
                            <img src="images/lilo.png" width="100" height="100" >
                        </span>

                        <span class="login100-form-title p-b-34 p-t-27">
                            Sign Up
                        </span>

                        <div class="wrap-input100 validate-input" data-validate = "Enter username">
                            <input class="input100" type="text" name="registerUsername" placeholder="Username" required>
                            <span class="focus-input100" data-placeholder="&#xf207;"></span>
                        </div>
                        <div class="wrap-input100 validate-input"  data-validate = "Enter Full name">
                            <input class="input100" type="text" name="registerFullname" placeholder="Full name" required>
                            <span class="focus-input100" data-placeholder="&#xf207;"></span>
                        </div>
                        <div class="wrap-input100 validate-input" onchange="ValidateEmail()" data-validate = "Enter Email">
                            <input class="input100" id="email" type="text" name="registerEmail" placeholder="Email">
                            <span class="focus-input100" data-placeholder="&#xf207;"></span>
                        </div>

                        <div class="wrap-input100 validate-input" data-validate="Enter password">
                            <input class="input100" id="registerPassword"  type="password" name="registerPassword" placeholder="Password" required>
                            <span class="focus-input100" data-placeholder="&#xf191;"></span>
                        </div>
                        <div class="wrap-input100 validate-input" data-validate="Enter confirm password">
                            <input class="input100" type="password" id="repass" onchange="checkPass()" name="repass" placeholder="Confirm Password" required>
                            <span class="focus-input100" data-placeholder="&#xf191;"></span>
                        </div>

                        <div class="container-login100-form-btn">
                            <button id="button-sign" class="login100-form-btn " disabled>
                                Sign Up
                            </button>
                        </div>

                    </s:form>
                </div>
            </div>
        </div>


        <div id="dropDownSelect1"></div>


        <script>
            $('#email').blur(function () {
                var email = $(this).val();
                var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/igm;
                if (re.test(email)) {

                } else {
                    alert("Not a valid email address")
                }

            });
            function checkPass() {
                alert($("#registerPassword").val(), $("#repass").val())
                if ($("#repass").val() == $("#registerPassword").val()) {
                    $("#button-sign").removeAttr("disabled");
                } else {
                    alert("Please check confirm password")
                    $("#button-sign").prop("disabled", true);
                }
            }
            ;
        </script>
    </body>
</html>