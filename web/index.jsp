<%--
  Created by IntelliJ IDEA.
  User: ratha
  Date: 7/3/17
  Time: 8:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Reminder Service</title>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script>
        var ctxPath = "<%=request.getContextPath() %>";
        $(document).ready(function() {
            $("#addReminder").click(function () {
                $.ajax({
                    url: ctxPath + "/reminder-service/v1/reminders",
                    type: "POST",
                    data: JSON.stringify({name:$("#txtName1").val(), description:$("#txtDesc1").val(), status: $("#txtStatus1").val(), dueDate: $("#txtDueDate1").val()}),
                    contentType: "application/json",
                    cache: false,
                    dataType: "json",
                    success: function (data, status) {
                        $('#div1').html(JSON.stringify(data));
                    },
                    error: function(data, status){
                        $('#div1').html(JSON.stringify(data.responseJSON));
                    }
                });
            });
            $("#updateReminder").click(function () {
                $.ajax({
                    url: ctxPath + "/reminder-service/v1/reminders/" + $("#txtId2").val(),
                    type: "PUT",
                    data: JSON.stringify({name:$("#txtName2").val(), description:$("#txtDesc2").val(), status: $("#txtStatus2").val(), dueDate: $("#txtDueDate2").val()}),
                    contentType: "application/json",
                    cache: false,
                    dataType: "json",
                    success: function (data, status) {
                        $('#div2').html(JSON.stringify(data));
                    },
                    error: function(data, status){
                        $('#div2').html(JSON.stringify(data.responseJSON));
                    }
                });
            });

            $("#getAReminder").click(function () {
                $.ajax({
                    url: ctxPath + "/reminder-service/v1/reminders/" + $("#txtId3").val(),
                    type: "GET",
                    data: {},
                    cache: false,
                    dataType: "json",
                    success: function (data, status) {
                        $('#div3').html(JSON.stringify(data));
                    },
                    error: function(data, status){
                        $('#div3').html(JSON.stringify(data.responseJSON));
                    }
                });
            });

            $("#getReminders").click(function () {
                $.ajax({
                    url: ctxPath + "/reminder-service/v1/reminders",
                    type: "GET",
                    data: {status: $("#txtStatus4").val(), dueDate: $("#txtDueDate4").val()},
                    cache: false,
                    dataType: "json",
                    success: function (data, status) {
                        $('#div4').html(JSON.stringify(data));
                    },
                    error: function(data, status){
                        $('#div4').html(JSON.stringify(data.responseJSON));
                    }
                });
            });

            $("#deleteReminder").click(function () {
                $.ajax({
                    url: ctxPath + "/reminder-service/v1/reminders/" + $("#txtId5").val(),
                    type: "DELETE",
                    data: {},
                    cache: false,
                    dataType: "json",
                    success: function (data, status) {
                        $('#div5').html("Content Deleted!");
                    },
                    error: function(data, status){
                        $('#div5').html(JSON.stringify(data.responseJSON));
                    }
                });
            });
        });
    </script>
</head>

<body id = "body">
<h1>Reminder Service</h1>
<ul>
    <li><button id="addReminder"><b>Add Reminder</b></button> Name:<input type = text id="txtName1" value ="Meeting"/> Desc:<input type = text id="txtDesc1" value ="Meeting with Manager"/> DueDate:<input type = text id="txtDueDate1" value ="123456789"/> Status:<input type = text id="txtStatus1" value ="NOT_DONE"/><br/><br/><div id="div1"></div> </li>
    <hr/><br/>
    <li><button id="updateReminder"><b>Update Reminder</b></button> Id:<input type = text id="txtId2" value ="1"/><br/> Name:<input type = text id="txtName2" value ="Meeting"/> Desc:<input type = text id="txtDesc2" value ="Meeting with Manager"/> DueDate:<input type = text id="txtDueDate2" value ="123456789"/> Status:<input type = text id="txtStatus2" value ="NOT_DONE"/><br/><br/><div id="div2"></div> </li>
    <hr/><br/>
    <li><button id="getAReminder"><b>Get Reminder ById </b></button> Id:<input type = text id="txtId3" value ="1"/> <br/> <br/> <div id="div3"></div> </li>
    <hr/><br/>
    <li><button id="getReminders"><b>Get Reminder ByStatus and DueDate </b></button><br/>  DueDate:<input type = text id="txtDueDate4" value ="123456789"/> Status:<input type = text id="txtStatus4" value ="DONE"/> <br/> <br/> <div id="div4"></div> </li>
    <hr/><br/>
    <li><button id="deleteReminder"><b>Delete Reminder</b></button> Id:<input type = text id="txtId5" value ="1"/><br/> <br/><div id="div5"></div> </li>

</ul>
</body>

</html>