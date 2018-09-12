package unity;

public class Department {
	private Integer id;
	private String name;
	private int emp_count;
	private Project project;

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getEmp_count() {
		return emp_count;
	}

	public void setEmp_count(int emp_count) {
		this.emp_count = emp_count;
	}

}
