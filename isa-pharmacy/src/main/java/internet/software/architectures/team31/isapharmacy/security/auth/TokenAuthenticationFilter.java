package internet.software.architectures.team31.isapharmacy.security.auth;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import internet.software.architectures.team31.isapharmacy.security.TokenUtils;
import internet.software.architectures.team31.isapharmacy.service.UserDetailsServiceImpl;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

	private TokenUtils tokenUtils;
	
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	public TokenAuthenticationFilter(TokenUtils tokenHelper, UserDetailsServiceImpl userDetailsServiceImpl) {
		this.tokenUtils = tokenHelper;
		this.userDetailsServiceImpl = userDetailsServiceImpl;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String username;
		String authToken = tokenUtils.getToken(request);
		
		if (authToken != null) {
			username = tokenUtils.getUsernameFromToken(authToken);
			
			if (username != null) {
				UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);
				
				if (tokenUtils.validateToken(authToken, userDetails)) {
					TokenBasedAuthentication authentication = new TokenBasedAuthentication(userDetails);
					authentication.setToken(authToken);
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			}
		}
		
		filterChain.doFilter(request, response);
	}

}
