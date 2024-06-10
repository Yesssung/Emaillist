package himedia.dao;

import java.util.Date;

// Table emaillist의 레코드를 저장하기 위한 DTO
// 1. 기본생성자를 가지고 있다
// 2. 비즈니스 로직을 가지지 않은 순수 데이터 객체
// 3. 필드, getters/setters
// 4. toString, equals 등 객체 관련 method를 Overide
public class EmailVo {
	
	// 필드
	private Long no;
	private String lastName;
	private String firstName;
	private String email;
	private Date createdAt;
	
	// 기본 생성자
	public EmailVo() {
		
	}

	// 전체 필드 생성자
	public EmailVo(Long no, String lastName, String firstName, String email, Date createdAt) {
		super();
		this.no = no;
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.createdAt = createdAt;
	}
	
	// no(PK) -> sequence, createdAt -> default
	public EmailVo(String lastName, String firstName, String email) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
	}
	
	// getters&setters
	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	// toString
	@Override
	public String toString() {
		return "EmailVo [no=" + no + ", lastName=" + lastName + ", firstName=" + firstName + ", email=" + email
				+ ", createdAt=" + createdAt + "]";
	}
	
	


}
