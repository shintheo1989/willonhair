package com.shintheo.willonhair.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.shintheo.willonhair.base.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "t_categorie")
public class CategoryDao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "c_id", updatable = false, nullable = false)
	private Long id;
	@Enumerated(EnumType.STRING)
	@Column(name = "c_status", nullable = false, length = 20)
	private Status status;
	@Column(name = "c_name", nullable = false)
	private String name;
	@Column(name = "c_position", nullable = false, length = 11)
	private long position;
	@Column(name = "c_translations", nullable = false, length = 767)
	private String translations;

}