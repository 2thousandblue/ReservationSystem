package util;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.sql.DataSource;
import javax.swing.JOptionPane;
import org.apache.commons.dbcp.BasicDataSourceFactory;
/**
 * jdbc的工具类
 * 定义静态方法
 * 获得数据库连接、执行sql、释放资源
 */
public class JDBCUtil {
	static Properties prop = new Properties();
	static DataSource ds = null;
	static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();
	static{
		try {
			prop.load(new FileInputStream("src/connReservation.properties"));
			ds = BasicDataSourceFactory.createDataSource(prop);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "暂时无法提供服务，请耐心等待","错误提示",JOptionPane.ERROR_MESSAGE);
			//写日志"服务器错误，连接数据库的配置文件路径错误"
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "暂时无法提供服务，请耐心等待","错误提示",JOptionPane.ERROR_MESSAGE);
			//写日志"服务器错误，连接数据库的配置文件参数错误"
		}  catch (Exception e) {
			JOptionPane.showMessageDialog(null, "暂时无法提供服务，请耐心等待","错误提示",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			//写日志
		}
	}
	/**
	 * 获得连接对象
	 * @return
	 * @throws SQLException 
	 */
	static public Connection getConnection() throws SQLException{
		Connection conn = null;
		try {
			conn = threadLocal.get();
			if(conn == null){
				conn = ds.getConnection();
				threadLocal.set(conn);
			}
		} catch (SQLException e) {
			throw e;
		}
		return conn;
	}
	/**
	 * 执行增删改
	 * @param sql  参数化的sql，也可以没有参数
	 * @param objects 要设置的参数的值
	 * @return 执行结果，若操作修改了原有数据则返回大于0的数，否则返回0
	 * @throws SQLException 
	 */
	static public int executeUpdate(String sql,Object...objects ) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			setParm(pstmt, objects);
			result = pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
			throw e;
		} finally{
			close(null, pstmt, null);
		}
		return result;
	}
	static void setParm(PreparedStatement pstmt,Object...objects ) throws SQLException{
		for (int i = 0; i < objects.length; i++) {
			pstmt.setObject(i+1, objects[i]);
		}
	}
	static void close(ResultSet rs,Statement stmt, Connection conn){
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(stmt != null){
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn != null){
			try {
				conn.close();
				threadLocal.remove();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 查询，循环中转为对象的过程使用接口RowMappingObject实现，
	 * 由接口的实现类完成对象的转换工作。
	 * 该查询方法可以脱离具体的查询表也及时回收了系统资源。
	 * @param sql
	 * @param rmo 行映射接口
	 * @param objects
	 * @return
	 * @throws SQLException 
	 */
	public static List<Object> executeQuery(String sql, RowMapperObject rmo,Object...objects) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Object> list = new ArrayList<Object>();
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			setParm(pstmt, objects);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Object o = rmo.rowMapperObject(rs);
				list.add(o);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}finally{
			close(rs,pstmt,null);
		}
		return list;
	}
}