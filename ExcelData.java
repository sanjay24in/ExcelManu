package org.test;


public class ExcelData {

	private String testCaseNumber;
	private String id;
	private String description;
	private String tcDetail;
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ExcelData [" + (testCaseNumber != null ? "testCaseNumber=" + testCaseNumber + ", " : "")
				+ (id != null ? "id=" + id + ", " : "")
				+ (description != null ? "description=" + description + ", " : "")
				+ (tcDetail != null ? "tcDetail=" + tcDetail : "") + "]";
	}
	/**
	 * @return the testCaseNumber
	 */
	public String getTestCaseNumber() {
		return testCaseNumber;
	}
	/**
	 * @param testCaseNumber the testCaseNumber to set
	 */
	public void setTestCaseNumber(String testCaseNumber) {
		this.testCaseNumber = testCaseNumber;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTcDetail() {
		return tcDetail;
	}
	public void setTcDetail(String tcDetail) {
		this.tcDetail = tcDetail;
	}
	
}
