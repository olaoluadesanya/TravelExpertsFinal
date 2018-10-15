package model;

public class Clas {
	
	private String classId;
	
	private String className;

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Clas(String classId, String className) {
		super();
		this.classId = classId;
		this.className = className;
	}	
}
