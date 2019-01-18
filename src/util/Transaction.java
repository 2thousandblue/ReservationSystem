package util;
import java.sql.SQLException;
/**
 * 事务的操作
 * @author Administrator
 *
 */
public interface Transaction {
	void start()  throws SQLException;
	void rollback()  throws SQLException;
	void commit() throws SQLException;
}
