package com.amstech.balloon.decoration.system.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;


/**
 * The persistent class for the email_otp database table.
 * 
 */
@Entity
@Table(name="email_otp")
@NamedQuery(name="EmailOtp.findAll", query="SELECT e FROM EmailOtp e")
public class EmailOtp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String email;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ganrated_at")
	private Date ganratedAt;

	private int otp;

	public EmailOtp() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getGanratedAt() {
		return this.ganratedAt;
	}

	public void setGanratedAt(Date ganratedAt) {
		this.ganratedAt = ganratedAt;
	}

	public int getOtp() {
		return this.otp;
	}

	public void setOtp(int otp) {
		this.otp = otp;
	}

}