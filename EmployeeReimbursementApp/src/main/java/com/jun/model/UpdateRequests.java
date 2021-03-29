package com.jun.model;

public class UpdateRequests {
	private int rId; 
	private boolean approved;
	
	public UpdateRequests() {
		super();
	}
	
	public UpdateRequests(int rId, boolean approved) {
		super();
		this.rId = rId;
		this.approved = approved;
	}

	public int getrId() {
		return rId;
	}

	public void setrId(int rId) {
		this.rId = rId;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (approved ? 1231 : 1237);
		result = prime * result + rId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UpdateRequests other = (UpdateRequests) obj;
		if (approved != other.approved)
			return false;
		if (rId != other.rId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UpdateRequests [rId=" + rId + ", approved=" + approved + "]";
	}
	
	
}
