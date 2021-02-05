package internet.software.architectures.team31.isapharmacy.domain.patient;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import internet.software.architectures.team31.isapharmacy.domain.users.SystemAdmin;
import internet.software.architectures.team31.isapharmacy.dto.ComplaintReplyCreateDTO;

@Entity
public class ComplaintReply {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private SystemAdmin admin;
	
	@Column(name = "reply_text", nullable = false)
	private String replyText;

	public ComplaintReply() {
		super();
	}
	
	public ComplaintReply(Long id, SystemAdmin admin, String replyText) {
		super();
		this.id = id;
		this.admin = admin;
		this.replyText = replyText;
	}

	public ComplaintReply(ComplaintReplyCreateDTO dto) {
		super();
		this.replyText = dto.getReplyText();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SystemAdmin getAdmin() {
		return admin;
	}

	public void setAdmin(SystemAdmin admin) {
		this.admin = admin;
	}

	public String getReplyText() {
		return replyText;
	}

	public void setReplyText(String replyText) {
		this.replyText = replyText;
	}
}
