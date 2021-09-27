package com.model;

public class User {
	private int useracno;
	@Override
	public String toString() {
		return "User [useracno=" + useracno + ", useracname=" + useracname + ", useractype=" + useractype
				+ ", useracbalance=" + useracbalance + ", useracpin=" + useracpin + "]";
	}
	public int getUseracno() {
		return useracno;
	}
	public void setUseracno(int useracno) {
		this.useracno = useracno;
	}
	public String getUseracname() {
		return useracname;
	}
	public void setUseracname(String useracname) {
		this.useracname = useracname;
	}
	public String getUseractype() {
		return useractype;
	}
	public void setUseractype(String useractype) {
		this.useractype = useractype;
	}
	public double getUseracbalance() {
		return useracbalance;
	}
	public void setUseracbalance(double useracbalance) {
		this.useracbalance = useracbalance;
	}
	public int getUseracpin() {
		return useracpin;
	}
	public void setUseracpin(int useracpin) {
		this.useracpin = useracpin;
	}
	private String useracname;
	private String useractype;
	private double useracbalance;
	private int useracpin;
}
