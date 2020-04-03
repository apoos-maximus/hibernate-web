<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

    <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<html>
    <head><center>
        Accounts Page
        </center>
       <style>
           #response {
               font-family: monospace;
               margin: 25px;
               width:300;
               /* word-wrap: break-word; */
               line-height:1.6;
               border-width: 1;
               border-color: black;
           }
           #objDesc {
               font-family: monospace;
               width:400 ;
               height: 80;
               border-width: 1;
               border-color: black;
               line-height: 1.6;
           }
           #create,#update {
               font-family: monospace;
               border-width: 1;
               border-color: black;
           }
           #response {
               float:right;
               /* width: 50%; */
           }
           #outer {
               display: inline-block;
           }
           #controls {
               float: left;
               /* width:50%; */
           }
           
       </style>
    </head>
    <body>
        <br/>
        <div>
            <a href="logout" > Logout</a>
        </div>
        <div id="outer">
            <div id="controls">
                <div id="forms-and-buttons">
                    <div>
                        <a href="/hibernate-web/accountAll">
                            <button>All Accounts</button>
                        </a>
                    </div>
                    <br/>
                    <div>
                        get Account by ID :
                        <form action="accountById" method="GET">
                            <label for="id">Id:</label>
                            <input type="text" id="id" name="id"><br/>
                            <input type="submit" value="Submit">
                        </form>
                    </div>
                    <br/>
    
                    <div id="create">
                        create Account :
                        <form action="account" method="POST">
                            <label for="objDesc">Object Description-[JSON]</label>
                            <input      type="text" id="objDesc" name="objDesc"><br/>
                            <input type="submit" value="Submit">
                        </form>
                    </div>
                    <br/>
                    <div id="update">
                        update Account :
                        <form action="accountEdit" method="POST">
                            <label for="objDesc">Object Description-[JSON]</label>
                            <input type="text" id="objDesc" name="objDesc"><br/>
                            <input type="submit" value="Submit">
                        </form>
                    </div>
                    <br/>
                    <div>
                        Credit :
                        <form action="account/credit" method="POST">
                            <label for="id">Id :</label>
                            <input type="text" id="id" name="id"><br/>
                            <label for="amount">Credit By :</label>
                            <input type="text" id="amount" name="amount"><br/>
                            <input type="submit" value="Submit">
                        </form>
                    </div><br/>
    
                    <div>
                        Debit :
                        <form action="account/debit" method="POST">
                            <label for="id">Id :</label>
                            <input type="text" id="id" name="id"><br/>
                            <label for="amount">Debit By :</label>
                            <input type="text" id="amount" name="amount"><br/>
                            <input type="submit" value="Submit">
                        </form>
                    </div><br/>
                    
                    <div>
                        Delete :
                        <form action="account/delete" method="GET">
                            <label for="id">Id :</label>
                            <input type="text" id="id" name="id"><br/>
                            <input type="submit" value="Submit">
                        </form>
                    </div>
    
                </div>
                <br/>
            </div>
            <div id="response">
                ${resp}
            </div>
        </div>
    </body>
</html>