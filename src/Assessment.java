import java.util.Date;

public interface Assessment {

	public Date getDate();
	public String getTitle();
	public Term getTerm();
    public String getCourseId();
    public Grade getGrade();

    public int getSkz();
    public AssessmentType assessmentType();
    public String getClassCode();
    public double getEcts();
    public double getSws();
    public CourseType getCourseType();
}
