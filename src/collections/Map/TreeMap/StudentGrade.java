package collections.Map.TreeMap;

public class StudentGrade implements Comparable<StudentGrade>{
    private final String name;
    private final float averageGrade;

    public StudentGrade(String name, float averageGrade) {
        this.name = name;
        this.averageGrade = averageGrade;
    }

    public String getName() {
        return name;
    }

    public float getAverageGrade() {
        return averageGrade;
    }

    @Override
    public int compareTo(StudentGrade that) {
        if (this.averageGrade < that.averageGrade) return -1;
        if (this.averageGrade > that.averageGrade) return 1;
        return name.compareTo(that.getName());
    }

    @Override
    public String toString() {
        return "Students name: " + name + " averageGrade: " + averageGrade;
    }
}
