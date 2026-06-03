// Declares that this class belongs to the "entity" package (folder)
package studentmanagementsystem.entity;

/**
 * Student - Data model representing a single student record.
 *
 * Each student has four pieces of information:
 * id - an 8-digit numeric string that uniquely identifies the student
 * name - the student's full name
 * age - the student's age stored as a String (converted to int when validated)
 * course - the course or program the student is enrolled in
 *
 * This class also provides helper methods to convert a Student to/from
 * the CSV (comma-separated) format used in the data file (students.txt).
 */
public class Student {

    // --- Fields (private so only this class can access them directly) ---

    private String id; // Unique 8-digit student ID, e.g. "20240001"
    private String name; // Full name, e.g. "Alice Smith"
    private String age; // Age as text, e.g. "20"
    private String course; // Enrolled course, e.g. "Computer Science"

    // --- Constructor ---

    /**
     * Creates a new Student with all four required fields.
     *
     * @param id     Unique 8-digit student ID.
     * @param name   Student's full name.
     * @param age    Student's age (numeric string).
     * @param course Course or program name.
     */
    public Student(String id, String name, String age, String course) {
        this.id = id; // Assign the provided id to this object's id field
        this.name = name; // Assign the provided name
        this.age = age; // Assign the provided age
        this.course = course; // Assign the provided course
    }

    // --- Getters (read-only access to private fields) ---

    /**
     * Returns the student's ID.
     *
     * @return 8-digit student ID string.
     */
    public String getId() {
        return id; // Return the id field value
    }

    /**
     * Returns the student's name.
     *
     * @return Full name string.
     */
    public String getName() {
        return name; // Return the name field value
    }

    /**
     * Returns the student's age.
     *
     * @return Age as a string (e.g. "20").
     */
    public String getAge() {
        return age; // Return the age field value
    }

    /**
     * Returns the student's enrolled course.
     *
     * @return Course name string.
     */
    public String getCourse() {
        return course; // Return the course field value
    }

    // --- Setters (allow controlled modification of private fields) ---

    /**
     * Updates the student's ID.
     *
     * @param id New 8-digit ID string.
     */
    public void setId(String id) {
        this.id = id; // Replace the current id with the new one
    }

    /**
     * Updates the student's name.
     *
     * @param name New name string.
     */
    public void setName(String name) {
        this.name = name; // Replace the current name with the new one
    }

    /**
     * Updates the student's age.
     *
     * @param age New age string.
     */
    public void setAge(String age) {
        this.age = age; // Replace the current age with the new one
    }

    /**
     * Updates the student's course.
     *
     * @param course New course name string.
     */
    public void setCourse(String course) {
        this.course = course; // Replace the current course with the new one
    }

    // --- File serialization helpers ---

    /**
     * Converts this Student into a single CSV (comma-separated) line
     * suitable for writing to the students.txt data file.
     *
     * Example output: "20240001,Alice Smith,20,Computer Science"
     *
     * @return A CSV string with id, name, age, and course separated by commas.
     */
    public String toLine() {
        // Concatenate all four fields with commas between them
        return id + "," + name + "," + age + "," + course;
    }

    /**
     * Parses a CSV line read from the data file and creates a Student object.
     *
     * This is a static factory method — it belongs to the class, not an instance,
     * so it can be called without first creating a Student.
     *
     * Example input: "20240001,Alice Smith,20,Computer Science"
     *
     * @param line A CSV string from the data file (may be null or malformed).
     * @return A new Student if the line is valid; null otherwise.
     */
    public static Student fromLine(String line) {
        // Reject null lines (e.g. blank reads at end of file)
        if (line == null)
            return null;

        // Split the line on commas; limit=-1 keeps empty trailing fields
        String[] data = line.split(",", -1);

        // A valid line must have exactly 4 fields: id, name, age, course
        if (data.length != 4)
            return null; // Malformed line — ignore it

        // Build and return a new Student from the parsed fields
        return new Student(data[0], data[1], data[2], data[3]);
    }

    /**
     * Converts this Student into an Object array row for display in a JTable.
     *
     * JTable rows are represented as Object arrays where each element
     * corresponds to a column: [ID, Name, Age, Course].
     *
     * @return Object array with four elements ready for table insertion.
     */
    public Object[] toRow() {
        // Return a new array containing all four fields in column order
        return new Object[] { id, name, age, course };
    }
}
