package com.rab3tech.dao.entity;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="customer_profile_pic")
public class CustomerProfilePicEntity {

	private int ppid;
	private byte[] photo;
	
	private Login login;
	private Timestamp doe;
	private Timestamp dom;
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="ulogin", nullable=false)
	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getPpid() {
		return ppid;
	}

	public void setPpid(int ppid) {
		this.ppid = ppid;
	}

	@Column(columnDefinition="longblob")
	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public Timestamp getDoe() {
		return doe;
	}

	public void setDoe(Timestamp doe) {
		this.doe = doe;
	}

	public Timestamp getDom() {
		return dom;
	}

	public void setDom(Timestamp dom) {
		this.dom = dom;
	}
	
	
}
