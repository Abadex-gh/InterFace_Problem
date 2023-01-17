package model.entities;

import java.util.Date;

public class Installment {
	
	private Date dueDate;
	private Double amout;
	public Installment(Date dueDate, Double amout) {
		this.dueDate = dueDate;
		this.amout = amout;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public Double getAmout() {
		return amout;
	}
	public void setAmout(Double amout) {
		this.amout = amout;
	}
	
	
}
