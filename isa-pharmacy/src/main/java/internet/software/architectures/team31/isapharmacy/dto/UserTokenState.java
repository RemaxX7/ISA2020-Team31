package internet.software.architectures.team31.isapharmacy.dto;

public class UserTokenState {

	private String accessToken;
    private Long expiresIn;
    private UserDetailsDTO user;

    public UserTokenState() {
        this.accessToken = null;
        this.expiresIn = null;
        this.user = null;
    }

    public UserTokenState(String accessToken, long expiresIn, UserDetailsDTO user) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.user = user;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

	public UserDetailsDTO getUser() {
		return user;
	}

	public void setUser(UserDetailsDTO user) {
		this.user = user;
	}
}
