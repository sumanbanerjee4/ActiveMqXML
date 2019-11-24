package activeMq.Repo;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;


@Component
@Entity
public class QModel {
	@Id
	private String profileId;
	
	@Column
	private String qName;
	
	public QModel() {}
	
	public QModel(String profileId, String qName) {
		super();
		this.profileId = profileId;
		this.qName = qName;
	}

	public String getProfileId() {
		return profileId;
	}

	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}

	public String getqName() {
		return qName;
	}

	public void setqName(String qName) {
		this.qName = qName;
	}


	
}
