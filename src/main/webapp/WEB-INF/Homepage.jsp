<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.net.*"%>
<!Doctype html>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Parkinsans:wght@300..800&family=Roboto+Mono:ital,wght@0,100..700;1,100..700&family=Source+Code+Pro:ital,wght@0,200..900;1,200..900&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Parkinsans:wght@300..800&family=Roboto+Mono:ital,wght@0,100..700;1,100..700&family=Source+Code+Pro:ital,wght@0,200..900;1,200..900&display=swap" rel="stylesheet">
<style>
    #header{
        height: 80px;
        background-color: black;
        color: white;
        padding-left: 50px;
        display: flex;
        align-items: center;
    }
    #header span{
        font-size: 30px;
        font-family: "Parkinsans", serif;
        font-optical-sizing: auto;
        font-style: normal;
    }
    #content{
        display: flex;
        justify-content: space-evenly;
    }
    #download_links{
        margin:10px;
        width:60%;
        height:60vh;
    }
    #management{
        margin:10px;
        height: 60vh;
        width: 37%;
    }
    .management_options{
        margin: 5px; height: 45%;
        display:flex;
        align-items: center;
        justify-content: center;
    }
    input{
        padding: 20px;
        border-radius: 6px;
    }
    .links{
        font-family: "Source Code Pro", serif;
        font-optical-sizing: auto;
        font-style: normal;
        font-size: 25px; margin: 10px;
        border: 2px solid #1e1e25;
        border-radius: 6px;
        width: 25vw; padding: 10px;
        text-align: center;
        box-shadow: 4px 4px #5f5f60;
    }
</style>
<html>
    <div id="header">
        <span>My Computer</span>
    </div>
    <div id="content">
        <div id="download_links" style="">
            <div style="text-align: center; font-size: 35px; font-family: 'Source Code Pro', Serif; margin: 12px; display: flex; justify-content: center;"><u>Shared Files</u></div>
            <form action="/share/delete" method="get" style="display: flex; flex-direction: column; align-items: center;">
                <% Enumeration list=(Enumeration) request.getAttribute("links");
                   if(list!=null){
                    while(list.hasMoreElements()){
                        String file_name= (String)list.nextElement();
                %>
                <span class="links"><input type="checkbox" name="files" value="<%=file_name%>" > <label><%=file_name%></label></span>
                <%      }%><input type="submit" value="DELETE" style="background-color: #fa3131; border: 2px solid #fa3131; color:white; font-size: 25px; margin-top: 10px">
                <%
                    }else{ %><span class="links"><input type="checkbox"> <label>"No Files Are Shared"</label></span>
                <%   }%>

            </form>
        </div>
        <div id="management">
            <div class="management_options">
                <form action="/share/add" method="get" style="display: flex; flex-direction: column; justify-content: center;">
                    <input type="text" name="filepath" id="filepath" placeholder="Enter the Path of the File that you want to share" style="display: inline; margin-right: 10px; margin-top: 10px; width:21vw; font-family: 'Source Code Pro', serif;">
                    <input type="text" name="name" id="name" placeholder="Name of Shared File to be Displayed" style="display: inline; margin-right: 10px; margin-top: 10px; width:15vw; font-family: 'Source Code Pro', serif;">
                    <input type="submit" value="ADD" style="margin-top: 10px; display: inline; width: 80px; background-color: dodgerblue; color: white; border: 2px solid dodgerblue">
                </form>
            </div>
            <div class="management_options">
                <%
                    String localhostaddr=InetAddress.getLocalHost().toString();
                    localhostaddr=localhostaddr.substring(localhostaddr.indexOf("/")+1);
                %>
                <p style="font-family: 'Source Code Pro', Serif;">Download Link: <%=localhostaddr%>/share/download/links <br></p>
            </div>
        </div>
    </div>
</html>