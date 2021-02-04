package internet.software.architectures.team31.isapharmacy.domain.patient;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ComplaintReply {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	//TODO: Uncomment once SystemAdmin has been added
	/*@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private SystemAdmin admin;
	*/
	@Column(name = "reply_text", nullable = false)
	private String replyText;

	public ComplaintReply() {
		super();
	}
}
