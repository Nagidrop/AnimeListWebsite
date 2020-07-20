<%-- 
    Document    viewList
    Created on  Jul 20, 2020, 10:45:33 AM
    Author      Quan Duc Loc CE140037 (SE1401)
--%>

<%@page import="animelist.model.AnimeDTO"%>
<%@page import="animelist.model.ListDTO"%>
<%@page import="animelist.model.GenreDTO"%>
<%@page import="java.util.ArrayList"%>
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
        <style  type="text/css">
            body.ownlist {
                background-image: url("https://cdn.myanimelist.net/s/common/uploaded_files/1455540188-934a8b8942494df1086f9402bbb5330b.png");
                background-attachment: fixed;
                background-position: center top;
                background-repeat: repeat\2D x;
            }

            #advanced-options {
                position: absolute;
                top: 24px;
                left: 0;
                right: 0;
                background-color: #F6F6F6;
                display: none;
                width: 860px;
                margin: 0 auto;
                padding: 25px 0px 30px;
                border: #EBEBEB 1px solid;
                -moz-box-shadow: 0px 2px 8px 0px rgba(0,0,0,0.3);
                -webkit-box-shadow: 0px 2px 8px 0px rgba(0,0,0,0.3);
                -o-box-shadow: 0px 2px 8px 0px rgba(0,0,0,0.3);
                -ms-box-shadow: 0px 2px 8px 0px rgba(0,0,0,0.3);
                box-shadow: 0px 2px 8px 0px rgba(0,0,0,0.3);
                color: #323232;
                text-align: left;
                font-family: 'Helvetica neue', Helvetica, "lucida grande", tahoma, verdana, arial, sans-serif;
                z-index: 1101;
            }
            #advanced-options .advanced-options-header {
                width: 750px;
                margin: 0px auto;
                padding-bottom: 4px;
                border-bottom: 1px solid #BEBEBE;
                font-size: 16px;
            }

            #advanced-options .advanced-options-header .description {
                font-size: 12px;
                font-weight: normal;
                margin-left: 8px;
            }

            #advanced-options select {
                -ms-appearance: none;
                -webkit-appearance: none;
                -moz-appearance: none;
                appearance: none;
                background-image: url(/img/pc/ownlist/icon_pulldown_triangle.png);
                background-repeat: no-repeat;
                background-position: center right;
                background-size: 18px 8px;
                background-color: #FFFFFF;
                display: inline-block;
                height: 25px;
                padding: 4px 20px 4px 4px;
                padding-right: 4px\0;
                border: #BEBEBE 1px solid;
                border-radius: 0px;
                font-size: 12px;
            }
            #advanced-options select::-ms-expand {
                display: none;
            }

            #advanced-options select:disabled {
                background-image: url(/img/pc/ownlist/icon_pulldown_triangle_disable.png);
                color: #9B9B9B;
            }

            #advanced-options input[type=text] {
                padding: 4px;
                border: #BEBEBE 1px solid;
            }

            #advanced-options input:focus {
                outline: none;
            }

            /* sort */
            #advanced-options .sort-widget {
                margin: 0px auto;
                padding: 12px 0px 0px;
                width: 750px;
            }
            #advanced-options .sort-widget:last-of-type {
                padding-bottom: 45px;
            }

            #advanced-options .sort-widget select {
                width: 172px;
                margin-right: 8px;
            }

            #advanced-options .sort-widget input[type=radio] {
                display: none;
            }
            #advanced-options .sort-widget input[type=radio] + label {
                background-color: #FFFFFF;
                display: inline-block;
                width: 56px;
                padding: 5px 0px;
                border: #BEBEBE 1px solid;
                border-radius: 4px;
                color: #323232;
                font-size: 12px;
                text-align: center;
                cursor: pointer;
            }
            #advanced-options .sort-widget input[type=radio]:checked + label {
                background-color: #4065BA;
                border: #4065BA 1px solid;
                color: #FFFFFF;
            }
            #advanced-options .sort-widget input[type=radio]:disabled + label {
                border: #BEBEBE 1px solid;
                color: #9B9B9B;
            }
            #advanced-options .sort-widget input[type=radio]:checked:disabled + label {
                background-color: #FFFFFF;
                border: #BEBEBE 1px solid;
                color: #9B9B9B;
            }

            /* filter */
            #advanced-options .filter-widget {
                margin: 0px auto;
                padding: 12px 0px 0;
                width: 750px;
            }

            #advanced-options .filter-widget select {
                margin-right: 8px;
                font-size: 12px;
            }

            #advanced-options .filter-widget.title input[type=text] {
                width: 360px;
            }

            #advanced-options .filter-widget.airing-status select,
            #advanced-options .filter-widget.publishing-status select {
                width: 152px;
            }

            #advanced-options .filter-widget.producer select,
            #advanced-options .filter-widget.magazine select {
                width: 360px;
            }

            #advanced-options .filter-widget.aired-date select.year,
            #advanced-options .filter-widget.published-date select.year {
                width: 80px;
            }

            #advanced-options .filter-widget.aired-date select.month,
            #advanced-options .filter-widget.published-date select.month,
            #advanced-options .filter-widget.aired-date select.day,
            #advanced-options .filter-widget.published-date select.day {
                width: 60px;
            }

            #advanced-options .filter-widget.aired-date .text,
            #advanced-options .filter-widget.published-date .text {
                display: inline-block;
                margin-right: 4px;
                font-size: 12px;
            }

            #advanced-options .filter-widget.aired-season select.year {
                width: 80px;
            }

            #advanced-options .filter-widget.aired-season select.season {
                width: 110px;
            }

            #advanced-options .sort-widget .widget-header,
            #advanced-options .filter-widget .widget-header {
                display: inline-block;
                width: 110px;
                font-size: 12px;
                font-family: 'Helvetica neue', Helvetica, "lucida grande", tahoma, verdana, arial, sans-serif;
            }

            #advanced-options .sort-widget:last-of-type,
            #advanced-options .filter-widget:last-of-type {
                padding-bottom: 40px;
            }

            #advanced-options .advanced-options-button {
                width: 750px;
                margin: 0px auto;
                padding: 12px 0px 0px;
                border-top: 1px solid #BEBEBE;
                text-align: center;
            }

            #advanced-options .btn-apply,
            #advanced-options .btn-clear {
                background-color: rgba(64, 101, 186, 1);
                display: inline-block;
                width: 135px;
                margin: 0 4px;
                padding: 6px 0px;
                border-radius: 4px;
                font-size: 12px;
                color: #FFFFFF;
                text-align: center;
                -moz-transition-property: all;
                -o-transition-property: all;
                -webkit-transition-property: all;
                transition-property: all;
                -moz-transition-duration: 0.3s;
                -o-transition-duration: 0.3s;
                -webkit-transition-duration: 0.3s;
                transition-duration: 0.3s;
                -moz-transition-timing-function: ease-in-out;
                -o-transition-timing-function: ease-in-out;
                -webkit-transition-timing-function: ease-in-out;
                transition-timing-function: ease-in-out
            }

            #advanced-options .btn-apply:hover,
            #advanced-options .btn-clear:hover {
                background-color: rgba(64, 101, 186, 0.8);
            }


            /**
             * General Styles
             */

            body {
                margin: 0 0 0 0;
                text-align: center;
                font-family: Verdana, Arial;
                font-size: 11px;
            }

            td {
                line-height: 1.5em;
            }

            a {
                color: #1d439b;
                text-decoration: none;
            }

            #footer-block {
                padding: 15px 0;
                background-color: #2E51A2;
                color: #FFFFFF;
                font-family: Avenir, "lucida grande", tahoma, verdana, arial, sans-serif;
            }

            #copyright {
                font-size: 12px;
                color: #9caed9;
                padding-top: 3px;
                text-align: center;
            }


            /*
             * Header
             */
            .header a {
                font-weight: bold;
                color: #fff;
            }
            .header a:hover {
                text-decoration: underline;
            }

            .header {
                position: relative;
                color: #fff;
                display: block;
                width: 1060px;
                height: 70px;
                margin: 0 auto
            }

            .header .header-title {
                position: absolute;
                top: 21px;
                background-image: url("/img/pc/ownlist/logo_mal.png");
                background-position: left top;
                background-repeat: no-repeat;
                background-size: auto 36px;
                display: block;
                width: 240px;
                height: 36px;
                text-indent: -9999px;
                overflow: hidden
            }

            .header .header-menu {
                position: absolute;
                top: 25px;
                right: 4px
            }
            .header .header-menu.other {
                top: 18px;
            }

            .header .header-menu .btn-menu {
                display: block;
                text-align: right;
                font-size: 16px;
            }

            .header .header-menu .header-info {
                font-size: 1.0em;
                margin-top: 6px;
                margin-right: 15px;
                text-align: right;
            }

            .header .username {
                font-weight: bold;
            }

            .header .header-menu .list-menu {
                position: absolute;
                top: 25px;
                right: -4px;
                background-color: #ffffff;
                display: none;
                border: #2E51A2 1px solid;
                -moz-box-shadow: rgba(0, 0, 0, 0.4) 0 0 10px;
                -webkit-box-shadow: rgba(0, 0, 0, 0.4) 0 0 10px;
                box-shadow: rgba(0, 0, 0, 0.4) 0 0 10px;
                z-index: 1;
            }

            .header .header-menu .list-menu .icon-menu {
                display: block;
                width: 150px;
                height: 30px;
                color: #2E51A2;
                font-size: 14px;
                font-weight: bold;
                text-decoration: none;
                -moz-transition-property: all;
                -o-transition-property: all;
                -webkit-transition-property: all;
                transition-property: all;
                -moz-transition-duration: 0.3s;
                -o-transition-duration: 0.3s;
                -webkit-transition-duration: 0.3s;
                transition-duration: 0.3s;
                -moz-transition-timing-function: ease-in-out;
                -o-transition-timing-function: ease-in-out;
                -webkit-transition-timing-function: ease-in-out;
                transition-timing-function: ease-in-out
            }

            .header .header-menu .list-menu .icon-menu:hover {
                background-color: #e1e7f5
            }

            .header .header-menu .list-menu .icon-menu svg.icon {
                position: absolute;
                fill: #2E51A2;
                left: 12px;
                top: 4px;
            }

            .header .header-menu .list-menu .icon-menu .text {
                position: absolute;
                left: 52px;
                top: 6px;
            }

            /**
             * Floating Menu
             */
            .list-menu-float {
                position: fixed;
                display: block;
                top: 20%;
                width: 50px;
                border: #EBEBEB 1px solid;
                z-index: 2
            }

            .list-menu-float .icon-menu {
                position: relative;
                background-color: #f6f6f6;
                display: block;
                width: 50px;
                height: 50px;
                opacity: 1;
                -moz-transition-property: all;
                -o-transition-property: all;
                -webkit-transition-property: all;
                transition-property: all;
                -moz-transition-duration: 0.3s;
                -o-transition-duration: 0.3s;
                -webkit-transition-duration: 0.3s;
                transition-duration: 0.3s;
                -moz-transition-timing-function: ease-in-out;
                -o-transition-timing-function: ease-in-out;
                -webkit-transition-timing-function: ease-in-out;
                transition-timing-function: ease-in-out;
            }

            /* SVG */
            .list-menu-float .icon-menu svg.icon {
                position: absolute;
                fill: #323232;
            }
            .list-menu-float .icon-menu:hover svg.icon {
                position: absolute;
                fill: #ffffff;
            }

            .list-menu-float .icon-menu.profile {
                background-size: cover;
            }

            .list-menu-float .icon-menu.quick-add {}
            .list-menu-float .icon-menu.quick-add svg.icon-quick-add {
                left: 13px;
                top: 12px;
            }

            .list-menu-float .icon-menu.anime-list {}
            .list-menu-float .icon-menu.anime-list svg.icon-anime-list {
                left: 13px;
                top: 12px;
            }

            .list-menu-float .icon-menu.manga-list {}
            .list-menu-float .icon-menu.manga-list svg.icon-manga-list {
                left: 13px;
                top: 12px;
            }

            .list-menu-float .icon-menu.history {}
            .list-menu-float .icon-menu.history svg.icon-history {
                left: 13px;
                top: 12px;
            }

            .list-menu-float .icon-menu.export {}
            .list-menu-float .icon-menu.export svg.icon-export {
                left: 13px;
                top: 12px;
            }

            .list-menu-float .icon-menu.login {}
            .list-menu-float .icon-menu.login svg.icon-login {
                left: 13px;
                top: 12px;
            }

            .list-menu-float .icon-menu.logout {}
            .list-menu-float .icon-menu.logout svg.icon-logout {
                left: 13px;
                top: 14px;
            }

            .list-menu-float .icon-menu.setting {}
            .list-menu-float .icon-menu.setting svg.icon-setting {
                left: 13px;
                top: 13px;
            }

            .list-menu-float .icon-menu .text {
                position: absolute;
                top: 14px;
                left: 50px;
                width: 0;
                display: inline-block;
                color: #ffffff;
                font-size: 1.4em;
                font-family: 'Helvetica neue', Helvetica, "lucida grande", tahoma, verdana, arial, sans-serif;
                text-align: center;
                vertical-align: middle;
                overflow: hidden;
                white-space: nowrap;
                opacity: 0;
                -moz-transition-property: all;
                -o-transition-property: all;
                -webkit-transition-property: all;
                transition-property: all;
                -moz-transition-duration: 0.3s;
                -o-transition-duration: 0.3s;
                -webkit-transition-duration: 0.3s;
                transition-duration: 0.3s;
                -moz-transition-timing-function: ease-in-out;
                -o-transition-timing-function: ease-in-out;
                -webkit-transition-timing-function: ease-in-out;
                transition-timing-function: ease-in-out
            }

            .list-menu-float .icon-menu:not(.profile):hover {
                background-color: #4065BA;
                width: 145px;
            }

            .list-menu-float .icon-menu:hover .text {
                width: 94px;
                opacity: 1
            }

            /* setting */
            .list-menu-float .icon-menu.setting .text {
                top: 0px;
                width: 0px;
                height: 100px;
                opacity: 0;
                -moz-transition-property: all;
                -o-transition-property: all;
                -webkit-transition-property: all;
                transition-property: all;
                -moz-transition-duration: 0.3s;
                -o-transition-duration: 0.3s;
                -webkit-transition-duration: 0.3s;
                transition-duration: 0.3s;
                -moz-transition-timing-function: ease-in-out;
                -o-transition-timing-function: ease-in-out;
                -webkit-transition-timing-function: ease-in-out;
                transition-timing-function: ease-in-out
            }
            .list-menu-float .icon-menu.setting:hover .text {
                top: 0px;
                width: 114px;
                height: 100px;
                opacity: 1
            }

            .list-menu-float .icon-menu.setting .text .link-list-setting,
            .list-menu-float .icon-menu.setting .text .link-style-setting {
                display: block;
                opacity: 0;
            }

            .list-menu-float .icon-menu.setting:hover .text .link-list-setting,
            .list-menu-float .icon-menu.setting:hover .text .link-style-setting {
                position: absolute;
                background-color: #4065BA;
                display: block;
                width: 110px;
                height: 50px;
                line-height: 50px;
                text-decoration: none;
                color: #ffffff;
                opacity: 1;
            }
            .list-menu-float .icon-menu.setting:hover .text .link-list-setting {
                top: 0px;
            }

            .list-menu-float .icon-menu.setting:hover .text .link-style-setting {
                border-top: #EBEBEB 1px solid;
                top: 50px;
            }


            /**
             * List Container
             */

            .list-container {
                position: relative;
                background-color: #ffffff;
                border: #EBEBEB 1px solid;
                width: 1058px;
                margin-bottom: 0;
                margin-right: auto;
                margin-left: auto;
                text-align: left;
            }

            /**
             * List Container - Cover Block
             */
            .cover-block {
                display: block;
                width: 1024px;
                margin: 0 auto;
                text-align: center;
                vertical-align: middle;
            }

            .cover-block .image-container {
                display: table-cell;
                padding-top: 16px;
                width: 1024px;
            }

            .cover-block .image-container.hide {
                display: none;
            }

            .cover-block .image-container img {
                max-width: 1024px;
            }

            /* setting icon */
            .cover-block .image-container .btn-list-setting {
                display: none;
            }

            .cover-block .image-container:hover .btn-list-setting {
                display: block;
                position: absolute;
                top: 20px;
                right: 28px;
                padding: 8px;
                border: rgba(255,255,255,0) 1px solid;
                font-size: 14px;
                color: #ffffff;
                font-family: 'Helvetica neue', Helvetica, "lucida grande", tahoma, verdana, arial, sans-serif;
            }

            .cover-block .image-container .btn-list-setting:hover {
                position: absolute;
                top: 20px;
                right: 28px;
                background-color: rgba(0,0,0,0.6);
                padding: 8px;
                border: #ffffff 1px solid;
                -moz-box-shadow: 0 0 4px #000000;
                -webkit-box-shadow: 0 0 4px #000000;
                -o-box-shadow: 0 0 4px #000000;
                -ms-box-shadow: 0 0 4px #000000;
                box-shadow: 0 0 4px #000000;
                font-size: 14px;
                color: #ffffff;
                text-shadow: #323232 1px 1px 1px;
            }

            .cover-block .image-container .btn-list-setting .fa-camera {
                font-size: 16px;
                -moz-text-shadow: #000000 1px 1px 3px;
                -webkit-text-shadow: #000000 1px 1px 3px;
                -o-text-shadow: #000000 1px 1px 3px;
                -ms-text-shadow: #000000 1px 1px 3px;
                text-shadow: #000000 1px 1px 3px;
                -ms-filter:"progid:DXImageTransform.Microsoft.DropShadow(color=#000000,offx=1,offy=1)";
            }

            .cover-block .image-container .btn-list-setting .icon-plus-circle {
                position: absolute;
                top: 4px;
                left: 18px;
                background-color: rgba(0,0,0,0.6);
                border-radius: 50%;
                -moz-box-shadow: -1px 1px 0px 0px #323232;
                -webkit-box-shadow: -1px 1px 0px 0px #323232;
                -o-box-shadow: -1px 1px 0px 0px #323232;
                -ms-box-shadow: -1px 1px 0px 0px #323232;
                box-shadow: -1px 1px 0px 0px #323232;
                font-size: 10px;
            }

            .cover-block .image-container .btn-list-setting .text {
                display: none;
            }

            .cover-block .image-container .btn-list-setting:hover .text {
                display: inline-block;;
                margin-left: 4px;
            }

            /**
             * List Container - Status Menu
             */
            .status-menu-container {
                width: 1058px;
                height: 46px;
                background-color: #FFF;
                border-bottom: #EBEBEB 1px solid;
                z-index: 1
            }

            .status-menu-container.fixed {
                position: fixed;
                display: block;
                top: 0;
            }

            .status-menu-container.fixed + div.list-block {
                margin-top: 62px;
            }

            .status-menu-container .status-menu {
                display: table;
                margin: 0 auto;
                border-collapse: separate;
                border-spacing: 40px 0;
            }

            .status-menu-container .status-menu .status-button {
                position: relative;
                display: table-cell;
                padding: 12px 0;
                margin: 0;
                font-family: 'Helvetica neue', Helvetica, "lucida grande", tahoma, verdana, arial, sans-serif;
                text-align: center;
                vertical-align: middle;
                font-size: 1.6em;
                color: #9B9B9B;
            }

            .status-menu-container .status-menu .status-button.on {
                color: #787878;
                font-weight: bold;
            }

            .status-menu-container .status-menu .status-button:after {
                position: absolute;
                bottom: 0px;
                left: 0px;
                background-color: #343a40;
                display: block;
                width: 100%;
                height: 4px;
                content: '';
                opacity: 0;
                -moz-transition-property: all;
                -o-transition-property: all;
                -webkit-transition-property: all;
                transition-property: all;
                -moz-transition-duration: 0.3s;
                -o-transition-duration: 0.3s;
                -webkit-transition-duration: 0.3s;
                transition-duration: 0.3s;
                -moz-transition-timing-function: ease-in-out;
                -o-transition-timing-function: ease-in-out;
                -webkit-transition-timing-function: ease-in-out;
                transition-timing-function: ease-in-out;
            }

            .status-menu-container .status-menu .status-button.on:after, .status-menu .status-button:hover:after {
                opacity: 1
            }

            /**
             * List Container - Status Menu - Search
             */
            .status-menu-container .search-container {
                position: absolute;
                right: 18px;
            }
            .status-menu-container .search-container #search-button {
                display: inline-block;
                height: 22px;
                margin-top: 15px;
                color: #787878;
                font-size: 1.6em;
                vertical-align: middle;
            }

            .status-menu-container .search-container #search-box {
                display: inline-block;
                width: 0;
                height: 22px;
                overflow: hidden;
                margin-top: 13px;
                -webkit-transition: width 0.3s;
                -moz-transition: width 0.3s;
                transition: width 0.3s;
                -webkit-backface-visibility: hidden;
                vertical-align: middle;
            }

            .status-menu-container .search-container #search-box.open {
                display: inline-block;
                width: 100px;
            }

            .status-menu-container .search-container #search-box input {
                width: 100%;
                height: 100%;
                box-sizing: border-box;
            }

            /**
             * List Container - List Block
             */
            .list-block {
                margin-top: 16px;
                min-height: 600px;
                padding: 0
            }

            .list-unit {
                display: block;
                margin: 0 auto;
                width: 1024px
            }

            /**
             * List Container - List Status Title (with Stats)
             */
            .list-unit .list-status-title {
                position: relative;
                display: table;
                background-color: #4065BA;
                width: 1024px;
                height: 38px
            }

            .list-unit .list-status-title .text {
                display: table-cell;
                width: 1024px;
                height: 38px;
                font-size: 2.0em;
                color: #FFF;
                font-weight: bold;
                font-family: 'Helvetica neue', Helvetica, "lucida grande", tahoma, verdana, arial, sans-serif;
                text-align: center;
                vertical-align: middle
            }

            .list-unit .list-status-title .stats {
                position: absolute;
                height: 38px;
                line-height: 38px;
                right: 4px;
            }

            .list-unit .list-status-title .stats a {
                margin-right: 8px;
                color: #FFF;
            }

            .list-unit .list-status-title .stats a.filterd {
                text-shadow: 0px 0px 2px #FFF;
            }

            .list-unit .list-stats {
                display: none;
                background-color: #4065BA;
                width: 1024px;
                height: 30px;
                line-height: 30px;
                color: #FFF;
                text-align: center;
            }


            /**
             * List Container - List Table
             */

            .list-table {
                width: 100%;
                margin: 0 auto;
                border-collapse: collapse;
                border: #EBEBEB 1px solid;
            }

            .list-table > tbody:nth-of-type(2n+1) {
                background-color: #fcfcfc;
            }


            /**
             * List Container - List Table - Header
             */

            .list-table .list-table-header .header-title {
                background: #F6F6F6 url("/img/pc/ownlist/bar-table-header.png") no-repeat right 7px / 1px 22px;
                display: table-cell;
                border-bottom: #EBEBEB 1px solid;
                height: 36px;
                text-align: center;
                vertical-align: middle;
            }

            .list-table .list-table-header .header-title:last-child {
                background-image: none;
            }

            .list-table .list-table-header .header-title.status {
                background-image: none;
                width: 4px
            }

            .list-table .list-table-header .header-title.number {
                width: 30px
            }

            .list-table .list-table-header .header-title.image {
                width: 66px
            }

            .list-table .list-table-header .header-title.title {
                padding-left: 8px;
                text-align: left
            }

            .list-table .list-table-header .header-title.score {
                width: 65px
            }

            .list-table .list-table-header .header-title.type {
                width: 60px
            }

            .list-table .list-table-header .header-title.progress {
                width: 90px
            }

            .list-table .list-table-header .header-title.chapters {
                width: 90px
            }

            .list-table .list-table-header .header-title.volumes {
                width: 90px
            }

            .list-table .list-table-header .header-title.tags {
                width: 120px
            }

            .list-table .list-table-header .header-title.rated {
                width: 70px
            }

            .list-table .list-table-header .header-title.started {
                width: 110px
            }

            .list-table .list-table-header .header-title.finished {
                width: 110px
            }

            .list-table .list-table-header .header-title.days {
                width: 45px
            }

            .list-table .list-table-header .header-title.storage {
                width: 75px
            }

            .list-table .list-table-header .header-title.retail {
                width: 65px;
            }

            .list-table .list-table-header .header-title.priority {
                width: 70px
            }

            .list-table .list-table-header .header-title .link {
                font-size: 11px;
                color: #323232;
                text-decoration: none
            }

            .list-table .list-table-header .header-title .link.sort {
                position: relative;
                display: inline-block;
                color: #323232;
                white-space: nowrap;
                -moz-transition-property: all;
                -o-transition-property: all;
                -webkit-transition-property: all;
                transition-property: all;
                -moz-transition-duration: 0.3s;
                -o-transition-duration: 0.3s;
                -webkit-transition-duration: 0.3s;
                transition-duration: 0.3s;
                -moz-transition-timing-function: ease-in-out;
                -o-transition-timing-function: ease-in-out;
                -webkit-transition-timing-function: ease-in-out;
                transition-timing-function: ease-in-out
            }

            .list-table .list-table-header .header-title .link.sort:hover {
                color: #2556c6
            }

            .list-table .list-table-header .header-title .sort-icon {
                color: #1d439b;
            }


            /**
             * List Container - List Table - Items
             */

            .list-table .list-table-data .data {
                display: table-cell;
                padding: 4px 0;
                text-align: center;
                vertical-align: middle;
                border-bottom: #EBEBEB 1px solid
            }

            .list-table .list-table-data a:not(.edit-disabled):hover {
                text-decoration: underline;
            }

            .list-table .list-table-data a.edit-disabled {
                cursor: text;
                color: black;
            }

            .list-table .list-table-data .tags .edit {
                display: block;
                width: 100%;
                text-align: center;
                color: #2db039;
                font-size: 10px;
                font-family: 'Helvetica neue', Helvetica, "lucida grande", tahoma, verdana, arial, sans-serif;
            }

            .list-table .list-table-data .tags .edit:hover {
                color: green !important;
            }

            .list-table .list-table-data .tags textarea {
                box-sizing: border-box;
                width: 100%;
                height: 60px;
            }

            .list-table .list-table-data .data.image a {
                display: inline-block;
            }
            .list-table .list-table-data .data.image .image {
                width: 48px;
                height: 68px;
                border: #EBEBEB 1px solid
            }

            .list-table .list-table-data .data.title {
                padding-left: 8px;
                text-align: left;
                word-wrap: break-word
            }

            .list-table .list-table-data .data.title .link {
                font-size: 1.1em;
                font-weight: bold
            }

            .list-table .list-table-data .data.title .rewatching, .list-table .list-table-data .data.title .rereading, .list-table .list-table-data .data.title .content-status {
                color: #BABABA;
                font-size: 0.9em
            }

            .list-table .list-table-data .data.score .link {
                font-size: 1.1em;
                font-weight: bold
            }

            .list-table .list-table-data .data.title .add-edit-more {
                float: right;
                margin-right: 10px;
                font-size: 0.9em;
            }

            .list-table .list-table-data .data.title .more {
                position: relative;
            }

            .list-table .more-info {
                display: none;
                border-bottom: #EBEBEB 1px solid;
            }

            .list-table .more-info .more-content {
                padding: 10px;
            }

            .list-table .list-table-data .data.tags {
                word-wrap: break-word
            }

            .list-table .list-table-data .data.status {
                width: 4px
            }

            .list-table .list-table-data .data.status .text {
                display: none
            }

            .list-table .list-table-data .data.status.reading, .list-table .list-table-data .data.status.watching {
                background-color: #2db039
            }

            .list-table .list-table-data .data.status.plantoread, .list-table .list-table-data .data.status.plantowatch {
                background-color: #c3c3c3
            }

            .list-table .list-table-data .data.status.completed {
                background-color: #26448f
            }

            .list-table .list-table-data .data.status.onhold {
                background-color: #f1c83e
            }

            .list-table .list-table-data .data.status.dropped {
                background-color: #a12f31
            }

            .list-table .list-table-data .data.title {
                padding-left: 8px;
                text-align: left
            }

            .list-unit .loading-space {
                margin: 8px;
                height: 20px;
                font-size: 20px;
                text-align: center;
            }

            .lala{
                text-align: center!important; 
            }

            .navbar{
                font-size: 1rem;
                font-family: -apple-system,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif,"Apple Color Emoji","Segoe UI Emoji","Segoe UI Symbol";
                line-height: 1.5rem;
            }

            .progresseps{
                border: 1px solid #000000!important;
            }

            .status-button{
                text-decoration: none!important;
            }
        </style>
        <title><s:property value="%{#session.username}"/>'s Anime List - Wibu Anime List</title>
    </head>
    <body style="background-image: url('images/bg-12.png'); background-repeat: no-repeat; background-size: cover; background-position: center; background-attachment: fixed;"> 
        <jsp:include page='header.jsp'/>
        <div class="header lala col-md-3 font-bold font-weight-bold" style="color:#343a40">
            <div class="header-menu">
                <div class="btn-menu" style="font-size:20px">
                    Viewing <s:property value="%{#session.username}"/> Anime List
                </div>
            </div>
        </div>

        <div id="list-container" class="list-container">
            <div id="status-menu" class="status-menu-container">
                <div class="status-menu">
                    <a id="all_anime" href="viewAnimeList?accountID=<s:property value="%{#session.id}"/>&listStatus=0" class="status-button all_anime">All Anime</a>
                    <a id="watching" href="viewAnimeList?accountID=<s:property value="%{#session.id}"/>&listStatus=1"
                       class="status-button watching ">Currently Watching</a>
                    <a id="completed" href="viewAnimeList?accountID=<s:property value="%{#session.id}"/>&listStatus=2"
                       class="status-button completed ">Completed</a>
                    <a id="onhold" href="viewAnimeList?accountID=<s:property value="%{#session.id}"/>&listStatus=3"
                       class="status-button onhold ">On Hold</a>
                    <a id="dropped" href="viewAnimeList?accountID=<s:property value="%{#session.id}"/>&listStatus=4"
                       class="status-button dropped ">Dropped</a>
                    <a id="plantowatch" href="viewAnimeList?accountID=<s:property value="%{#session.id}"/>&listStatus=5"
                       class="status-button plantowatch ">Plan to Watch</a><div class="search-container">
                    </div>
                </div>
            </div>

            <div class="list-block">

                <div class="list-unit all_anime">
                    <div class="list-status-title" style="background-image: url('images/bg-03.jpg'); background-repeat: no-repeat; background-size: cover; background-position: center; background-attachment: fixed;">
                        <span class="text">ALL ANIME</span>
                    </div>
                    <table class="list-table" style="font-size: 12px;">
                        <thead><tr class="list-table-header">
                                <th class="header-title number">No.</th>
                                <th class="header-title image"><a class="link hover_info">Poster</a></th>
                                <th class="header-title title"><a href="" class="link sort">Anime Title</a></th>
                                <th class="header-title score"><a href="" class="link sort">Type</a></th>
                                <th class="header-title type"><a href="" class="link sort">Progress</a></th>
                                <th class="header-title progress"><a href="" class="link sort">Status</a></th>
                                <th class="header-title edit"><a href="" class="link sort">Edit</a></th>
                            </tr>
                        </thead>
                        <% ArrayList<ListDTO> animeList = (ArrayList<ListDTO>) request.getAttribute("AnimeList");
                            ArrayList<AnimeDTO> animeDetailsList = (ArrayList<AnimeDTO>) request.getAttribute("AnimeDetailsList");
                            int animeIndex = 0;
                            if (!animeDetailsList.isEmpty()) {
                                ArrayList<String> statusList = (ArrayList<String>) request.getAttribute("StatusList");
                                for (AnimeDTO anime : animeDetailsList) {
                                    ListDTO listAnime = animeList.get(animeIndex);
                                    animeIndex++;
                        %> 
                        <tbody class="list-item" >
                        <form action="editAnimeList" method="POST">
                            <tr class="list-table-data">
                                <td class="data number" style="width: 5%;"><%= animeIndex%></td>

                                <td class="data image" style="width: 7%;">
                                    <a href="viewAnime?animeID=<%= anime.getId()%>" class="link sort">
                                        <img src="images/poster/<%= anime.getPoster()%>" class="hover-info image">
                                    </a>
                                </td>

                                <td class="data title clearfix" style="width: 50%;">
                                    <a href="viewAnime?animeID=<%= anime.getId()%>" class="link sort"><%= anime.getName()%></a>
                                </td>

                                <td class="data type" style="width: 8%;">
                                    <%= anime.getType()%>
                                </td>

                                <td class="data progress" style="background-color: transparent!important; width: 9%; text-align: left">
                                    <input type="text" style="font-size: 12px;" name="progressEdit" size="1" class="inputtext form-control-sm progresseps" value="<%= listAnime.getProgress()%>" style="border: 1px solid #ced4da!important;">
                                    / <%= anime.getEpisodes()%><input type="hidden" name="episodesEdit" value="<%= anime.getEpisodes()%>" />
                                    <input type="hidden" name="animeIDEdit" value="<%= anime.getId()%>" />
                                    <input type="hidden" name="accountIDEdit" value="<s:property value="%{#session.id}"/>" />
                                </td>

                                <td class="data status" style="width: 15%;">
                                    <select name="statusEdit"> 
                                        <%                            
                                            int statusIndex = 1;
                                            for (String status : statusList) {
                                                if (status != listAnime.getStatus()) {
                                        %>
                                        <option value="<%= statusIndex %>"> <%= status %> </option> <br />
                                        <% } else {%>
                                        <option value="<%= statusIndex%>" selected="selected"> <%= status %> </option> <br />
                                        <% }
                                                statusIndex++;
                                            } %>
                                    </select>
                                </td>
                                <td style="width: 5%; text-align: center;">
                                    <input type="submit" style="font-size: 12px;" value="Edit" />
                                </td>
                            </tr>
                        </form>
                        </tbody>
                        <% }
                            }%>
                    </table>
                    <% if (animeDetailsList.isEmpty()) {%>
                    <div style="text-align: center!important; font-size: 20px; margin-top: 3%;">There are no animes to display!</div>
                    <% }%>
                </div>
            </div>
        </div>
        <script>
            var status =<s:property value="listStatus" />;
            if (status == 0) {
                $("#all_anime").addClass("on");
                $("#watching").removeClass("on");
                $("#completed").removeClass("on");
                $("#onhold").removeClass("on");
                $("#dropped").removeClass("on");
                $("#plantowatch").removeClass("on");
            } else if (status == 1) {
                $("#all_anime").removeClass("on");
                $("#watching").addClass("on");
                $("#completed").removeClass("on");
                $("#onhold").removeClass("on");
                $("#dropped").removeClass("on");
                $("#plantowatch").removeClass("on");
            } else if (status == 2) {
                $("#all_anime").removeClass("on");
                $("#watching").removeClass("on");
                $("#completed").addClass("on");
                $("#onhold").removeClass("on");
                $("#dropped").removeClass("on");
                $("#plantowatch").removeClass("on");
            } else if (status == 3) {
                $("#all_anime").removeClass("on");
                $("#watching").removeClass("on");
                $("#completed").removeClass("on");
                $("#onhold").addClass("on");
                $("#dropped").removeClass("on");
                $("#plantowatch").removeClass("on");
            } else if (status == 4) {
                $("#all_anime").removeClass("on");
                $("#watching").removeClass("on");
                $("#completed").removeClass("on");
                $("#onhold").removeClass("on");
                $("#dropped").addClass("on");
                $("#plantowatch").removeClass("on");
            } else if (status == 5) {
                $("#all_anime").removeClass("on");
                $("#watching").removeClass("on");
                $("#completed").removeClass("on");
                $("#onhold").removeClass("on");
                $("#dropped").removeClass("on");
                $("#plantowatch").addClass("on");
            }
        </script>
    </body>
</html>
