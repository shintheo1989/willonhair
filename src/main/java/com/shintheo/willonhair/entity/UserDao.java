package com.shintheo.willonhair.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.shintheo.willonhair.base.Gender;
import com.shintheo.willonhair.base.Status;
import com.shintheo.willonhair.base.Type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "t_user")
public class UserDao implements Serializable, UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "c_id", updatable = false, nullable = false)
	private Long id;
	@Enumerated(EnumType.STRING)
	@Column(name = "c_status", nullable = false, length = 20)
	private Status status;
	@Enumerated(EnumType.STRING)
	@Column(name = "c_type", nullable = false, length = 20)
	private Type type;
	@Column(name = "c_externalId", length = 11)
	private long externalId;
	@Column(name = "c_firstName")
	private String firstName;
	@Column(name = "c_lastName")
	private String lastName;
	@Column(name = "c_email")
	private String email;
	@Column(name = "c_birthday")
	private Date birthday;
	@Column(name = "c_phone", length = 63)
	private String phone;
	@Enumerated(EnumType.STRING)
	@Column(name = "c_gender", nullable = false, length = 20)
	private Gender gender;
	@Column(name = "c_note", length = 767)
	private String note;
	@Column(name = "c_description", length = 767)
	private String description;
	@Column(name = "c_pictureFullPath", length = 767)
	private String pictureFullPath;
	@Column(name = "c_pictureThumbPath", length = 767)
	private String pictureThumbPath;
	@Column(name = "c_password", length = 128)
	private String password;
	@Column(name = "c_usedTokens")
	private String usedTokens;
	@Column(name = "c_zoomUserId")
	private String zoomUserId;
	@Column(name = "c_countryPhoneIso", length = 2)
	private String countryPhoneIso;
	@Column(name = "c_translations")
	private String translations;
	@Column(name = "c_timeZone")
	private String timeZone;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(type.name())); 
	}
	@Override
	public String getUsername() {
		return this.email;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true; // TODO("Do we need to handle expiration of accounts")
	}
	@Override
	public boolean isAccountNonLocked() {
		return List.of(Status.APPROVED, Status.VISIBLE).contains(this.status);
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true; // TODO("Handle expiration of credentials if needed")
	}
	@Override
	public boolean isEnabled() {
		return List.of(Status.APPROVED, Status.VISIBLE).contains(this.status);
	}

}