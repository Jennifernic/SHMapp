<%-- 
    Document   : admin_view_Acceptrequest
    Created on : Dec 14, 2022, 7:56:56 PM
    Author     : Riss
--%>

<%@ include  file= 'adminheader.jsp'%>
<%@include file="connections.jsp" %>
<%@include file="tempheader.jsp" %>

<center>
    			<form method="post" >

			<h1>View Accepted Request</h1>
	
			<table align="center"  style="width:500px" class="table">
			<tr>
				<th>Student Name</th>
				<th>Event </th>
				<th>Place</th>
				<th>Status</th>
				
			</tr>
        <%

        rs=st.executeQuery("SELECT *,request.date as rdate FROM `request` INNER JOIN `student` USING(`student_id`)INNER JOIN `event` USING(`event_id`) ");
        while(rs.next()){%>
        <tr>
        <td><%=rs.getString("fname")%> <%=rs.getString("lname")%></td>
        <td><%=rs.getString("event")%></td>
        <td><%=rs.getString("rdate")%></td>
        <td><%=rs.getString("status")%></td>
  

        </tr>
        <% } %>
</table>

</form>

</center>
<%@ include  file= 'temp_footer.jsp'%>
<%@ include  file= 'footer.jsp'%>