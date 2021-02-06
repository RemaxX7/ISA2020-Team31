package internet.software.architectures.team31.isapharmacy.dto;

public class ComplaintReplyCreateDTO {

	private Long complaintId;
	private Long adminId;
	private String replyText;
	
	public ComplaintReplyCreateDTO() {
		super();
	}

	public ComplaintReplyCreateDTO(Long complaintId, Long adminId, String replyText) {
		super();
		this.complaintId = complaintId;
		this.adminId = adminId;
		this.replyText = replyText;
	}

	public Long getComplaintId() {
		return complaintId;
	}

	public void setComplaintId(Long complaintId) {
		this.complaintId = complaintId;
	}

	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	public String getReplyText() {
		return replyText;
	}

	public void setReplyText(String replyText) {
		this.replyText = replyText;
	}
}
