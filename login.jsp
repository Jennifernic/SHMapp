<%-- 
    Document   : login
    Created on : Aug 22, 2022, 4:28:42 PM
    Author     : Riss
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="publicheader.jsp" %>
<%@include file="connections.jsp" %>

<%
if(request.getParameter("login")!=null)
{
   String u=request.getParameter("u");
   String pwd=request.getParameter("pwd");
   rs=st.executeQuery("select * from login where username='"+u+"' and password='"+pwd+"'");
   if(rs.next())
   {
       String lid=rs.getString("login_id");
       session.setAttribute("lognid",lid);
       String utype=rs.getString("usertype");
        System.out.println(""+utype);

       if(utype.equals("admin"))
       { 
                   System.out.println("oooooo"+utype);
       %>  
       <script>
           alert("login successfully");
           window.location="adminhome.jsp";
       </script>
       <%

       }
 
  
    
}
}

%>
   
 <div id="about" class="about-us section">
    <div class="container">
      <div class="row">
        <div class="col-lg-6">
          <div class="left-image wow fadeInLeft" data-wow-duration="1s" data-wow-delay="0.5s">
            <img src="assets/images/about-left-image.png" alt="">
          </div>
        </div>
        <div class="col-lg-6 align-self-center wow fadeInRight" data-wow-duration="1s" data-wow-delay="0.5s">
        
          <div class="row">
     
   
         
       
<center>
    <h1>LOGIN</h1>
	<form method="post">
	<table class="table" style="width: 500px;color:black">
		<tr>
			<th>username</th>
			<td><input type="text" name="u" class="form-control"></td>
		</tr>
		<tr>
			<th>password</th>
			<td><input type="password" name="pwd" class="form-control"></td>
		</tr>
		<tr>
			<td align="center" colspan="2"><input type="submit" name="login" value="login" class="btn btn-success"></td>
		</tr>
	</table>
	</form>
</center>
  
          </div>
        </div>
      </div>
    </div>
  </div>
              
                
<%@include file="footer.jsp" %>
