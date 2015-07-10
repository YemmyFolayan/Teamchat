package com.pivotal.bot;

import javax.annotation.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Data {

	@Expose
	private String kind;
	@Expose
	private Integer id;
	@Expose
	private String name;
	@Expose
	private String plan;
	@Expose
	private String status;
	@SerializedName("days_left")
	@Expose
	private Integer daysLeft;
	@SerializedName("over_the_limit")
	@Expose
	private Boolean overTheLimit;
	@SerializedName("created_at")
	@Expose
	private String createdAt;
	@SerializedName("updated_at")
	@Expose
	private String updatedAt;

	/**
	 * 
	 * @return The kind
	 */
	public String getKind() {
		return kind;
	}

	/**
	 * 
	 * @param kind
	 *            The kind
	 */
	public void setKind(String kind) {
		this.kind = kind;
	}

	public Data withKind(String kind) {
		this.kind = kind;
		return this;
	}

	/**
	 * 
	 * @return The id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 *            The id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	public Data withId(Integer id) {
		this.id = id;
		return this;
	}

	/**
	 * 
	 * @return The name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 *            The name
	 */
	public void setName(String name) {
		this.name = name;
	}

	public Data() {
		super();
	}

	public Data(String kind, Integer id, String name, String plan,
			String status, Integer daysLeft, Boolean overTheLimit,
			String createdAt, String updatedAt) {
		super();
		this.kind = kind;
		this.id = id;
		this.name = name;
		this.plan = plan;
		this.status = status;
		this.daysLeft = daysLeft;
		this.overTheLimit = overTheLimit;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Data withName(String name) {
		this.name = name;
		return this;
	}

	/**
	 * 
	 * @return The plan
	 */
	public String getPlan() {
		return plan;
	}

	/**
	 * 
	 * @param plan
	 *            The plan
	 */
	public void setPlan(String plan) {
		this.plan = plan;
	}

	public Data withPlan(String plan) {
		this.plan = plan;
		return this;
	}

	/**
	 * 
	 * @return The status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 
	 * @param status
	 *            The status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	public Data withStatus(String status) {
		this.status = status;
		return this;
	}

	/**
	 * 
	 * @return The daysLeft
	 */
	public Integer getDaysLeft() {
		return daysLeft;
	}

	/**
	 * 
	 * @param daysLeft
	 *            The days_left
	 */
	public void setDaysLeft(Integer daysLeft) {
		this.daysLeft = daysLeft;
	}

	public Data withDaysLeft(Integer daysLeft) {
		this.daysLeft = daysLeft;
		return this;
	}

	/**
	 * 
	 * @return The overTheLimit
	 */
	public Boolean getOverTheLimit() {
		return overTheLimit;
	}

	/**
	 * 
	 * @param overTheLimit
	 *            The over_the_limit
	 */
	public void setOverTheLimit(Boolean overTheLimit) {
		this.overTheLimit = overTheLimit;
	}

	public Data withOverTheLimit(Boolean overTheLimit) {
		this.overTheLimit = overTheLimit;
		return this;
	}

	/**
	 * 
	 * @return The createdAt
	 */
	public String getCreatedAt() {
		return createdAt;
	}

	/**
	 * 
	 * @param createdAt
	 *            The created_at
	 */
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public Data withCreatedAt(String createdAt) {
		this.createdAt = createdAt;
		return this;
	}

	/**
	 * 
	 * @return The updatedAt
	 */
	public String getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * 
	 * @param updatedAt
	 *            The updated_at
	 */
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Data withUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

}