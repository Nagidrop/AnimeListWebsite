<%-- 
    Document   : AnimeSearch
    Created on : Jul 17, 2020, 4:54:00 PM
    Author     : dorew
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
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
        <link rel="stylesheet" type="text/css" href="css/AdvanceSearch.css">
        <!--===============================================================================================-->
        <script src="/vendor/jquery/jquery-3.2.1.min.js"></script>
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
    <body >
        <jsp:include page='header.jsp'/>
        
    
        <div class="s010" style="background-image: url('images/bg-10.jpg'); background-repeat: no-repeat; background-size: cover; background-position: center; background-attachment: fixed;">
            <s:form action="search">
                <div class="inner-form">
                    <div class="basic-search">
                        <div class="input-field">
                            <input name="searchvalue" id="search" type="text" placeholder="Search Anime..." />
                            <div class="icon-wrap">
                                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24">
                                <path d="M15.5 14h-.79l-.28-.27C15.41 12.59 16 11.11 16 9.5 16 5.91 13.09 3 9.5 3S3 5.91 3 9.5 5.91 16 9.5 16c1.61 0 3.09-.59 4.23-1.57l.27.28v.79l5 4.99L20.49 19l-4.99-5zm-6 0C7.01 14 5 11.99 5 9.5S7.01 5 9.5 5 14 7.01 14 9.5 11.99 14 9.5 14z"></path>
                                </svg>
                            </div>
                        </div>
                    </div>
                    <div class="advance-search">
                        <span class="desc">ADVANCED SEARCH</span>
                        <div class="row">
                            <div class="input-field">
                                <div class="input-select">
                                    <select data-trigger="" name="type">
                                        <option placeholder="" value="">Type</option>
                                        <s:iterator value="types" status="dto">
                                            <option value="<s:property/>"><s:property/></option>
                                        </s:iterator>
                                    </select>
                                </div>
                            </div>
                            <div class="input-field">
                                <div class="input-select">
                                    <select data-trigger="" name="genre">
                                        <option placeholder="" value="">Genre</option>
                                        <s:iterator value="genres" status="dto">
                                            <option value="<s:property value="id"/>"><s:property value="name"/></option>
                                        </s:iterator>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="row second">
                            <div class="input-field">
                                <div class="input-select">
                                    <select data-trigger="" name="studio">
                                        <option placeholder="" value="">Studio</option>
                                         <s:iterator value="studios" status="dto">
                                            <option value="<s:property value="id"/>"><s:property value="name"/></option>
                                        </s:iterator>
                                    </select>
                                </div>
                            </div>
                            <div class="input-field">
                                <div class="input-select">
                                    <select data-trigger="" name="season">
                                        <option placeholder="" value="">Season</option>
                                         <s:iterator value="seasons" status="dto">
                                            <option value="<s:property value="id"/>"><s:property value="name"/></option>
                                        </s:iterator>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="row third">
                            <div class="input-field">
                                <div class="result-count">
                                    <span> </span></div>
                                <div class="group-btn">
                                    <button type="reset" class="btn-delete" id="delete">RESET</button>
                                    <button type="submit" class="btn-search" value="SEARCH">SEARCH</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </s:form>
        </div>
        <script src="js/extention/choices.js" type="62c9915bca822e18f27fd30b-text/javascript"></script>
        <script type="62c9915bca822e18f27fd30b-text/javascript">
            const customSelects = document.querySelectorAll("select");
            const deleteBtn = document.getElementById('delete')
            const choices = new Choices('select',
            {
            searchEnabled: false,
            itemSelectText: '',
            removeItemButton: true,
            });
            for (let i = 0; i < customSelects.length; i++)
            {
            customSelects[i].addEventListener('addItem', function(event)
            {
            if (event.detail.value)
            {
            let parent = this.parentNode.parentNode
            parent.classList.add('valid')
            parent.classList.remove('invalid')
            }
            else
            {
            let parent = this.parentNode.parentNode
            parent.classList.add('invalid')
            parent.classList.remove('valid')
            }
            }, false);
            }
            deleteBtn.addEventListener("click", function(e)
            {
            e.preventDefault()
            const deleteAll = document.querySelectorAll('.choices__button')
            for (let i = 0; i < deleteAll.length; i++)
            {
            deleteAll[i].click();
            }
            });

        </script>

        <script async src="https://www.googletagmanager.com/gtag/js?id=UA-23581568-13" type="62c9915bca822e18f27fd30b-text/javascript"></script>
        <script type="62c9915bca822e18f27fd30b-text/javascript">
            window.dataLayer = window.dataLayer || [];
            function gtag(){dataLayer.push(arguments);}
            gtag('js', new Date());

            gtag('config', 'UA-23581568-13');
        </script>
        <script src="https://ajax.cloudflare.com/cdn-cgi/scripts/7089c43e/cloudflare-static/rocket-loader.min.js" data-cf-settings="62c9915bca822e18f27fd30b-|49" defer=""></script></body>
</body>
</html>
