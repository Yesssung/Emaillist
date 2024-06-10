package himedia.dao;

import java.util.List;

public interface EmaillistDao {
	public List<EmailVo> getList();			// Emaillist table SELECT
	public boolean insert(EmailVo vo);		// Emaillist table INSERT
	public boolean delete(Long no);			// Emaillist table DELETE
}
