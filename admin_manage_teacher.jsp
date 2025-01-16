<%-- 
    Document   : admin_manage_teacher
    Created on : Dec 14, 2022, 7:56:06 PM
    Author     : Riss
--%>


<%@ include  file= 'adminheader.jsp'%>
<%@include file="connections.jsp" %>
<%@include file="tempheader.jsp" %>
<%


if(request.getParameter("add")!= null)
{
   String a=request.getParameter("fname");
   String b=request.getParameter("lname");
   String c=request.getParameter("place");
   String d=request.getParameter("phone");
   String e=request.getParameter("email");
   String f=request.getParameter("qual");
   String g=request.getParameter("des");
   String u=request.getParameter("u");
   String pwd=request.getParameter("pwd");
   st1.executeUpdate("insert into login values(null,'"+u+"','"+pwd+"','teacher')");
   st1.executeUpdate("insert into teacher values(null,(select max(login_id) from login),'"+a+"','"+b+"','"+c+"','"+d+"','"+e+"','"+f+"','"+g+"')");
  %>
   <script>
       alert("Added successfully");
       window.location("admin_manage_teacher.jsp");
//   </script>
<%
}

if(request.getParameter("did")!=null)
{
    String did=request.getParameter("did");
    st.executeUpdate("delete from teacher where login_id='"+did+"'");
    st.executeUpdate("delete from login where login_id='"+did+"'");
    %>
   <script>
       alert("deleted successfully");
       window.location("admin_manage_teacher.jsp");
   </script>
<%
}
%>
<%
if(request.getParameter("update")!= null)
{
   String a=request.getParameter("fname");
   String b=request.getParameter("lname");
   String c=request.getParameter("place");
   String d=request.getParameter("phone");
   String e=request.getParameter("email");
   String f=request.getParameter("qual");
   String g=request.getParameter("des");
   String uid=request.getParameter("uid");

   
 st2.executeUpdate("UPDATE teacher SET fname='"+a+"',lname='"+b+"',`place`='"+c+"',`phone`='"+d+"',`email`='"+e+"',`qualification`='"+f+"',`designation`='"+g+"' WHERE login_id='"+uid+"' ");
  
 %>
   <script>
       alert("updated successfully");
       window.location="admin_manage_teacher.jsp";
   </script>
<%
}
%>

<center>
    <form method="POST"  >

                            <h2 style="color: black">Manage Teacher</h2>
		<% 
                
        String uid=request.getParameter("uid");
       rs1= st.executeQuery("select * from teacher where login_id='"+uid+"'");
        if(rs1.next())
        {
                
                
                %>
                        
                        
			

			<table align="center" style="width: 500px">

<tr>
					<th>First Name</th>
					<td><br><input type="text" name="fname" class="form-control" required="" value="<%=rs1.getString("fname") %>"></td>
				</tr>
				<tr>
					<th>Last Name</th>
					<td><br><input type="text" name="lname" class="form-control" required="" value="<%=rs1.getString("lname") %>"></td>
				</tr>

				<tr>
				<th>Place </th>
                            <td><br><input type="text" name="place" class="form-control" required="" value="<%=rs1.getString("place") %>"></td>
				</tr>
                                <tr>
				<th>Phone </th>
                            <td><br><input type="text" name="phone" class="form-control" required="" value="<%=rs1.getString("phone") %>"></td>
				</tr>
                                <tr>
				<th>Email </th>
                            <td><br><input type="email" name="email" class="form-control" required="" value="<%=rs1.getString("email") %>"></td>
				</tr>


                                <tr>
				<th>Qualification </th>
                            <td><br><input type="text" name="qual" class="form-control" required="" value="<%=rs1.getString("qualification") %>"></td>
				</tr> 
                                <tr>
				<th>Designation </th>
                            <td><br>
                            <input type="text" name="des" class="form-control" required="" value="<%=rs1.getString("designation") %>"></td>
				</tr>
								
				<tr>
					<td colspan="2" align="center"><br><input type="submit" name="update" value="update" class="btn btn-success"></td>
				</tr>

			</table>
			<% 
                        }
                        else
                        { 
                        %>
			 


                        <table align="center" style="width: 500px" >
				<tr>
					<th>First Name</th>
					<td><br><input type="text" name="fname" class="form-control" required=""></td>
				</tr>
				<tr>
					<th>Last Name</th>
					<td><br><input type="text" name="lname" class="form-control" required=""></td>
				</tr>

				<tr>
				<th>Place </th>
                            <td><br><input type="text" name="place" class="form-control" required=""></td>
				</tr>
                                <tr>
				<th>Phone </th>
                            <td><br><input type="text" name="phone" class="form-control" required=""></td>
				</tr>
                                <tr>
				<th>Email </th>
                            <td><br><input type="email" name="email" class="form-control" required=""></td>
				</tr> 
	

                                <tr>
				<th>Qualification </th>
                            <td><br><input type="text" name="qual" class="form-control" required=""></td>
				</tr>  <tr>
				<th>Designation </th>
                            <td><br><input type="text" name="des" class="form-control" required=""></td>
				</tr>
<tr>
			<th>username</th>
			<td><br><input type="text" name="u" class="form-control"></td>
		</tr>
		<tr>
			<th>password</th>
			<td><br><input type="password" name="pwd" class="form-control"></td>
		</tr>
				
				<tr>
                                    <td colspan="2" align="center"><br><input type="submit" name="add" value="Add" class="btn btn-success"></td>
				</tr>

			</table>



			 <% } %>

 
      
                         <br><br><br>
      
      			 <h1 align="center" style="margin-left: -15em">View Teacher</h1>
                         <br>
                         <table align="center" style="width: 500px;margin-left: -19em" class="table" >
                             <tr>
                               <th> Name</th>
				<th>Place</th>
				<th>Phone</th>
				<th>Email</th>
				<th>Qualification</th>
				<th>Designation</th>
                             </tr>
                             
                         
 
                                              <%
        rs=st.executeQuery("SELECT * FROM `teacher`");  
        while(rs.next()){%>
                <tr>
                    <td><%=rs.getString("fname")%> <%=rs.getString("lname")%></td>
        <td><%=rs.getString("place")%></td>
        <td><%=rs.getString("phone")%></td>
        <td><%=rs.getString("email")%></td>
        <td><%=rs.getString("qualification")%></td>
        <td><%=rs.getString("designation")%></td>
                     <td><a class="btn btn-success" href="?uid=<%=rs.getString("login_id")%>">update</a></td>
                     <td><a class="btn btn-danger" href="?did=<%=rs.getString("login_id")%>">Delete</a></td>
		</tr>

                     <% } %>

                                  </table>

		
		</form>
		</center>
<%@ include  file= 'temp_footer.jsp'%>
<%@ include  file= 'footer.jsp'%>
