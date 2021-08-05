package collections.Map.TreeMap;

public class SubjectGrade {
    private final String subject;
    private final int grade;

    public SubjectGrade(String subject, int grade) {
        this.subject = subject;
        this.grade = grade;
    }

    public String getSubject() {
        return subject;
    }

    public int getGrade() {
        return grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubjectGrade that = (SubjectGrade) o;

        if (grade != that.grade) return false;
        return subject != null ? subject.equals(that.subject) : that.subject == null;
    }

    @Override
    public int hashCode() {
        int result = subject != null ? subject.hashCode() : 0;
        result = 31 * result + grade;
        return result;
    }

    @Override
    public String toString() {
        return "subject: " + subject + " Grade: " + grade;
    }
}
