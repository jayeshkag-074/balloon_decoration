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
	@Column(name="generated_at")
	private Date generatedAt;

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

	public Date getGeneratedAt() {
		return this.generatedAt;
	}

	public void setGeneratedAt(Date generatedAt) {
		this.generatedAt = generatedAt;
	}

	public int getOtp() {
		return this.otp;
	}

	public void setOtp(int otp) {
		this.otp = otp;
	}

}