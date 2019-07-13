package com.maxmall.common.security;

import com.google.common.collect.Sets;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;
import java.util.Collection;
import java.util.Set;


/**
 * The class Security utils.
 *
 * @author maxmall.net@gmail.com
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SecurityUtils {

	/**
	 * Gets current login name.
	 *
	 * @return the current login name
	 */
	public static String getCurrentLoginName() {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		}
		if (principal instanceof Principal) {
			return ((Principal) principal).getName();
		}
		return String.valueOf(principal);
	}

	/**
	 * Gets current login name.
	 *
	 * @return the current login name
	 */
	public static UserDetails getCurrentUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			return ((UserDetails) principal);
		}
		return null;
	}

	public static Set<String> getCurrentAuthorityPermission() {
		Set<String> path = Sets.newHashSet();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for (final GrantedAuthority authority : authorities) {
			String permission = authority.getAuthority();
			if (StringUtils.isNotEmpty(permission)) {
				path.add(permission);
			}
		}
		return path;
	}
}
