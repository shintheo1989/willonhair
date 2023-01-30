package com.shintheo.willonhair.entity;

import java.io.Serializable;


import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.shintheo.willonhair.base.Status;
import com.shintheo.willonhair.base.WorkStatus;

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
@Table(name = "t_appointment")
public class AppointmentDao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "c_id", updatable = false, nullable = false)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "c_status", nullable = false, length = 20)
	private Status status;
	
	@DateTimeFormat
	@Column(name = "c_booking_start", nullable = false)
	private Timestamp bookingStart;
	
	@DateTimeFormat
	@Column(name = "c_booking_end", nullable = false)
	private Timestamp bookingEnd;
	
	@Column(name = "c_notify_participants", nullable = false, length = 1)
	private int notifyParticipants;
	
	@Column(name = "c_package_id", nullable = true, length = 11)
	private int packageId;
	
	@Column(name = "c_provider_id", nullable = true, length = 11)
	private int providerId;
	
	@Column(name = "c_internal_notes", length = 627)
	private String internalNotes;
	
	@Column(name = "c_google_calendar_event_id")
	private String googleCalendarEventId;
	
	@Column(name = "c_google_meet_url")
	private String googleMeetUrl;
	
	@Column(name = "c_outlook_calendar_event_id")
	private String outlookCalendarEventId;
	
	@Column(name = "c_zoom_meeting", length = 627)
	private String zoomMeeting;
	
	@Column(name = "c_lesson_space", length = 627)
	private String lessonSpace;
		
	@Column(name = "c_work_status")
	private WorkStatus workStatus;
	
	@Column(name = "c_created_at")
	@CreationTimestamp
	private Timestamp createdAt;
	
	@Column(name = "c_updated_at")
	@UpdateTimestamp
	private Timestamp updatedAt;
	
	@Column(name = "c_started_at")
	private Timestamp startedAt;
	
	@Column(name = "c_completed_at")
	private Timestamp compltedAt;
	
	@Column(name = "c_canceled_at")
	private Timestamp canceledAt;
	
	@Column(name = "c_note")
	private String note;
	
	@ManyToOne
	@JoinColumn(name = "c_parent_id", nullable = true)
	@JsonManagedReference
	private AppointmentDao parent;
	
	@ManyToOne
	@JoinColumn(name = "c_location_id")
	@JsonManagedReference
	private LocationDao location;
	
	@ManyToOne
	@JoinColumn(name = "service_id")
	@JsonManagedReference
	private ServiceDao service;
	
	@ManyToOne
	@JoinColumn(name = "client_id", nullable = true)
	@JsonManagedReference
	private UserDao client;
	
	@ManyToOne
	@JoinColumn(name = "employee_id", nullable = true)
	@JsonManagedReference
	private UserDao employee;
	
	@ManyToOne
	@JoinColumn(name = "reservation_transfer_id", nullable = true)
	@JsonManagedReference
	private AppointmentTransferDao reservationTransfer;

	@OneToMany(mappedBy = "appointment", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
	@JsonManagedReference
	private List<CustomerBookingDao> bookinks;
	
	public void init() {
		this.workStatus = WorkStatus.ON_HOLD;
	}
	
	public boolean canUpdate() {
		// TODO()
		return this.workStatus != WorkStatus.PAID;
	}
	
	public boolean canStart() {
		return this.workStatus == WorkStatus.ON_HOLD;
	}
	
	public boolean canComplete() {
		return this.workStatus == WorkStatus.IN_PROGRESS;
	}
	
	public boolean canPay() {
		return this.workStatus == WorkStatus.COMPLETED;
	}
	
	public boolean canCancel() {
		return this.workStatus == WorkStatus.ON_HOLD;
	}
	
	public void cancel() {
		this.workStatus = WorkStatus.CANCELD;
		this.canceledAt = new Timestamp(System.currentTimeMillis());
	}
	
	public void startWork() {
		this.workStatus = WorkStatus.IN_PROGRESS;
		this.startedAt = new Timestamp(System.currentTimeMillis());
	}
	
	public void completeWork() {
		this.workStatus = WorkStatus.COMPLETED;
		this.compltedAt = new Timestamp(System.currentTimeMillis());
	}
	
	public void markAsPaid() {
		this.workStatus = WorkStatus.PAID;
	}
}
