package activeMq.Repo;


import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class ScanRequestModel {
	@Id
  	private int trnsRef;
	
    @Column
	private String profileId;
	
    @Column
	private Timestamp date;

	public int getTrnsRef() {
		return trnsRef;
	}

	public void setTrnsRef(int trnsRef) {
		this.trnsRef = trnsRef;
	}

	public String getProfileId() {
		return profileId;
	}

	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}

	public Timestamp getTimestamp() {
		return date;
	}

	public void setTimestamp(Timestamp date) {
		this.date = date;
	}

	public ScanRequestModel() {}
	public ScanRequestModel(int trnsRef, String profileId, Timestamp date) {
		
		this.trnsRef = trnsRef;
		this.profileId = profileId;
		this.date = date;
	}
	
	
	
}
