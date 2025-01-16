<%-- 
    Document   : admin_manage_event
    Created on : Dec 14, 2022, 7:56:22 PM
    Author     : Riss
--%>
<%@ include  file= 'adminheader.jsp'%>
<%@include file="connections.jsp" %>
<%@include file="tempheader.jsp" %>
<%


if(request.getParameter("add")!= null)
{
   String a=request.getParameter("event");
   String b=request.getParameter("weekdays");
   String c=request.getParameter("date");

   
   st1.executeUpdate("insert into event values(null,'"+a+"','"+b+"','"+c+"')");
  %>
   <script>
       alert("Added successfully");
       window.location("admin_manage_event.jsp");
//   </script>
<%
}

if(request.getParameter("did")!=null)
{
    String did=request.getParameter("did");
    st.executeUpdate("delete from event where event_id='"+did+"'");
    %>
   <script>
       alert("deleted successfully");
       window.location("admin_manage_event.jsp");
   </script>
<%
}
%>
<%
if(request.getParameter("update")!= null)
{
   String a=request.getParameter("event");
   String b=request.getParameter("weekdays");
   String c=request.getParameter("date");
   String uid=request.getParameter("uid");

   
 st2.executeUpdate("UPDATE event SET event='"+a+"',weekdays='"+b+"',`date`='"+c+"' WHERE event_id='"+uid+"' ");
  
 %>
   <script>
       alert("updated successfully");
       window.location="admin_manage_event.jsp";
   </script>
<%
}
%>

<center>
    <form method="POST"  >

                            <h2 style="color: black">Event</h2>
		<% 
                
        String uid=request.getParameter("uid");
       rs1= st.executeQuery("select * from event where event_id='"+uid+"'");
        if(rs1.next())
        {
                
                
                %>
                        
                        
			

			<table align="center" style="width: 500px">


				<tr>
					<th>Event</th>
					<td><br><input type="text" name="event" class="form-control" required="" value="<%=rs1.getString("event") %>"></td>
				</tr>
				<tr>
					<th>Week Days</th>
					<td><br><input type="text" name="weekdays" class="form-control" required="" value="<%=rs1.getString("weekdays")%>"></td>
				</tr>

				<tr>
					<th>Date </th>
					<td><br>
                                            <input type="date" name="date" class="form-control" required="" value="<%=rs1.getString("date")%>">

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
					<th>Event</th>
					<td><br><input type="text" name="event" class="form-control" required=""></td>
				</tr>
				<tr>
					<th>Week Days</th>
					<td><br><input type="text" name="weekdays" class="form-control" required=""></td>
				</tr>

				<tr>
					<th>Date </th>
					<td><br>
                                            <input type="date" name="date" class="form-control" required="">
                                        </td>
				</tr>

				
				<tr>
                                    <td colspan="2" align="center"><br><input type="submit" name="add" value="Add" class="btn btn-success"></td>
				</tr>

			</table>



			 <% } %>

 
      
      
      
      			 <h1 align="center">View Events</h1>
                        <table align="center" style="width: 500px" >
                             <tr>
                                 <th>Event</th>
                                 <th>Weekdays</th>
                                 <th>Date</th>
                             </tr>
                             
                         
 
                                              <%
        rs=st.executeQuery("SELECT * FROM `event`");  
        while(rs.next()){%>
                <tr>
                     <td><%=rs.getString("event")%></td>
                     <td><%=rs.getString("weekdays")%></td>
                     <td><%=rs.getString("date")%></td>
                     <td><a class="btn btn-success" href="?uid=<%=rs.getString("event_id")%>">update</a></td>
                     <td><a class="btn btn-danger" href="?did=<%=rs.getString("event_id")%>">Delete</a></td>
		</tr>

                     <% } %>

                                  </table>

		
		</form>
		</center>
<%@ include  file= 'temp_footer.jsp'%>
<%@ include  file= 'footer.jsp'%>