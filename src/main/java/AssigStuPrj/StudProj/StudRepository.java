package AssigStuPrj.StudProj;

import java.sql.*;
import java.util.*;
import java.util.Date;
public class StudRepository {
	
	Connection con=null;
	
	public StudRepository()
	{
		String url="jdbc:mysql://localhost:3306/stud_prj";
		String username="root";
		String password="";
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url,username,password);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	public List<Student> getStudents()
	{
		List<Student> students = new ArrayList<>();
		String sql="select * from student";
		try
		{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next())
			{
				Student s = new Student();
				s.setSno(rs.getInt(1));
				s.setName(rs.getString(2));
				s.setDob(rs.getDate(3));
				s.setDoj(rs.getDate(4));
				
				students.add(s);
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return students;
	}
	
	public void create(Student s1)
	{
		String sql = "insert into student values(?,?,?,?)";
		try
		{
			java.util.Date d1 = s1.getDob();
			java.sql.Date d2 = new java.sql.Date(d1.getDate());
			java.util.Date d3 = s1.getDoj();
			java.sql.Date d4 = new java.sql.Date(d3.getDate());
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1,s1.getSno());
			st.setString(2, s1.getName());
			st.setDate(3,d2);
			st.setDate(4,d4);
			st.executeUpdate();
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	public void update(Student s1)
	{
		String sql = "update student set student_name=?,student_dob=?,student_doj=? where student_no=?";
		try
		{
			java.util.Date d1 = s1.getDob();
			java.sql.Date d2 = new java.sql.Date(d1.getDate());
			java.util.Date d3 = s1.getDoj();
			java.sql.Date d4 = new java.sql.Date(d3.getDate());
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, s1.getName());
			st.setDate(2, d2);
			st.setDate(3, d4);
			st.setInt(4,s1.getSno());
			st.executeUpdate();
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	public Student getStudent(int sno) 
	{
		List<Student> students = new ArrayList<>();
		String sql="select * from student";
		try
		{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next())
			{
				Student s = new Student();
				s.setSno(rs.getInt(1));
				s.setName(rs.getString(2));
				s.setDob(rs.getDate(3));
				s.setDoj(rs.getDate(4));
				
				students.add(s);
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		for(Student s : students)
		{
			if(s.getSno()==sno)
				return s;
		}
		
		return null ;
	}

	public void delete(int sno) {
		String sql = "delete from student where student_no=?";
		try
		{
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1,sno);
			st.executeUpdate();
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}

}
