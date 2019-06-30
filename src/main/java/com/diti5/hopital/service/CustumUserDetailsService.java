package com.diti5.hopital.service;



import com.diti5.hopital.dao.*;
import com.diti5.hopital.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service("userService")
//ou @Component
public class CustumUserDetailsService implements UserDetailsService {
	@Autowired
	private UtilisateurDAO userDAO ;
	@Autowired
	private ServiceDOA serviceDOA ;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Utilisateur user = userDAO.findByUsername(username);
		if(user != null)
		{
			 User u = new User(user.getUsername(),user.getPassword(),
					 true,true,true,true,getAuthorities(user.getListeRoles()));
			  
			 return u ;
		}
		
		return null;
	}

	private Collection<? extends GrantedAuthority> getAuthorities(
			Collection<Role> roles) {
		List<GrantedAuthority> authorities
				= new ArrayList<>();
		for (Role role: roles) {
			authorities.add(new SimpleGrantedAuthority(role.getLibelle()));
		}
		return authorities;
	}

}
