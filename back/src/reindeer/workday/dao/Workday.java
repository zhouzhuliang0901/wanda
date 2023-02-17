package reindeer.workday.dao;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="WWD_WORKDAY")
public class Workday implements Serializable{

	private static final long serialVersionUID = 6664541303289409732L;

	/**
	 * 工作日主键
	 */
	@Id
	@Column(name ="ST_WORKDAY_ID")
	private String stWorkdayId;
	
	/**
	 * 标志更改的某天
	 */
	@Column(name ="DT_WORKDAY")
	private Timestamp dtWorkday;
	
	/**
	 * 是否是工作日
	 */
	@Column(name ="ST_IS_WORKDAY")
	private String stIsWorkday;
	
	public Workday() {
	} 
	
	public Workday(String stWorkdayId, Timestamp dtWorkday, String stIsWorkday) {
		super();
		this.stWorkdayId = stWorkdayId;
		this.dtWorkday = dtWorkday;
		this.stIsWorkday = stIsWorkday;
	}

	public String getStWorkdayId() {
		return stWorkdayId;
	}

	public void setStWorkdayId(String stWorkdayId) {
		this.stWorkdayId = stWorkdayId;
	}

	public Date getDtWorkday() {
		return dtWorkday;
	}

	public void setDtWorkday(Timestamp dtWorkday) {
		this.dtWorkday = dtWorkday;
	}

	public String getStIsWorkday() {
		return stIsWorkday;
	}

	public void setStIsWorkday(String stIsWorkday) {
		this.stIsWorkday = stIsWorkday;
	}
	
	
}
