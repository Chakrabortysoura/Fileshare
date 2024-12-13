<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Parkinsans:wght@300..800&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Parkinsans:wght@300..800&family=Roboto+Mono:ital,wght@0,100..700;1,100..700&display=swap" rel="stylesheet">
<style>
    #header{
        text-align: center;
        align-content: center;
        font-size: 35px;
        margin-bottom: 30px;
        background-color: #134dcc;
        border-radius: 10px;
        color: white;
        font-family: "Parkinsans", sans-serif;
        font-optical-sizing: auto;
        font-style: normal;
    }
    #contents{
        font-family: "Roboto Mono", monospace;
        font-optical-sizing: auto;
        font-style: normal;
        font-size: 20px;
        padding-top: 10px;
        padding-bottom: 10px;
        display: flex;
        flex-direction:column;
        justify-content: center;
        align-items:center
    }
    .download_links{
        border: 2px solid black;
        padding: 12px 19px;
        margin: 10px;

    }
    .download_button{
        background-color: #7c7c8e;
        padding: 7px;
        border-radius: 4px;
    }
    a {
        text-decoration:none;
        color:inherit;
    }
</style>
<body>
    <div id="header">Download Links</div>
    <div id="contents">
       <% Enumeration list=(Enumeration) request.getAttribute("links");
          if(list!=null){
            while(list.hasMoreElements()){
                String file_name= (String)list.nextElement();
       %>
       <span class="download_links"><%=file_name%> <span class="download_button"><a href="share/download/<%=file_name%>">Download</a></span></span>
       <%   }
          }else{%> <span class="download_links">No Download Links Available </span>
       <%  }%>
    </div>
</body>
</html>