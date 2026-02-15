package com.learncode_backend.dto;

import java.math.BigDecimal;

public class AdminDashboardDTO{
	private long totalCourses;
    private long totalUsers;
    private BigDecimal dailyIncome;
    
	public AdminDashboardDTO(long totalCourses, long totalUsers, BigDecimal dailyIncome) {
		super();
		this.totalCourses = totalCourses;
		this.totalUsers = totalUsers;
		this.dailyIncome = dailyIncome;
	}

	public long getTotalCourses() {
		return totalCourses;
	}

	public void setTotalCourses(long totalCourses) {
		this.totalCourses = totalCourses;
	}

	public long getTotalUsers() {
		return totalUsers;
	}

	public void setTotalUsers(long totalUsers) {
		this.totalUsers = totalUsers;
	}

	public BigDecimal getDailyIncome() {
		return dailyIncome;
	}

	public void setDailyIncome(BigDecimal dailyIncome) {
		this.dailyIncome = dailyIncome;
	}
}