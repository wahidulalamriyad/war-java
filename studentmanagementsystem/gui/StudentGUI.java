// Declares that this class belongs to the "gui" package (folder)
package studentmanagementsystem.gui;

// Import the Student data model
import studentmanagementsystem.entity.Student;

// Import the file I/O helper that reads/writes student records
import studentmanagementsystem.fileio.StudentFileIO;

// Swing imports — javax.swing provides all GUI components (buttons, labels, tables, etc.)
import javax.swing.*;
import javax.swing.table.DefaultTableModel; // Used to manage JTable data programmatically

// AWT imports — java.awt provides layout managers and the Color/Font APIs
import java.awt.*;

// IOException is thrown when file operations fail
import java.io.IOException;

/**
 * StudentGUI - The main application window for the Student Management System.
 *
 * This class extends JFrame (a top-level Swing window) and builds the entire
 * user interface: input fields, a search bar, action buttons, and a data table.
 *
 * Layout overview:
 * NORTH — inputPanel (ID, Name, Age, Course text fields)
 * + searchPanel (keyword search bar)
 * + buttonPanel (Add, Update, Delete, View All, Clear buttons)
 * CENTER — scrollPane containing a JTable that displays student records
 *
 * The GUI communicates with StudentFileIO for all data persistence.
 */
public class StudentGUI extends JFrame {

    // --- Input text fields (one per student attribute) ---
    private JTextField idField; // Field where the user types the student ID
    private JTextField nameField; // Field where the user types the student name
    private JTextField ageField; // Field where the user types the student age
    private JTextField courseField; // Field where the user types the student course
    private JTextField searchField; // Field where the user types a search keyword

    // --- Table components ---
    private JTable table; // The visual table widget displayed in the window
    private DefaultTableModel tableModel; // The data model that backs the JTable

    // =========================================================================
    // CONSTRUCTOR — builds and displays the entire GUI
    // =========================================================================

    /**
     * Constructs the StudentGUI window, wires up all components and event
     * listeners,
     * ensures the data file exists, and loads all existing records into the table.
     */
    public StudentGUI() {
        // Set the text shown in the window's title bar
        setTitle("Student Management System");

        // Set the initial window size in pixels (width x height)
        setSize(850, 550);

        // Close the application completely when the user clicks the X button
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Use BorderLayout as the root layout with 10-pixel gaps between regions
        setLayout(new BorderLayout(10, 10));

        // -----------------------------------------------------------------
        // INPUT PANEL — four rows of label + text field pairs
        // GridLayout(rows, cols, hgap, vgap) arranges components in a grid
        // -----------------------------------------------------------------
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 8, 8));

        // Add a decorative border with the title "Student Details"
        inputPanel.setBorder(BorderFactory.createTitledBorder("Student Details"));

        // Row 1: ID label and text field
        inputPanel.add(new JLabel("ID (exactly 8 digits):")); // Descriptive label
        idField = new JTextField(); // Empty text box for ID input
        inputPanel.add(idField);

        // Row 2: Name label and text field
        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        // Row 3: Age label and text field
        inputPanel.add(new JLabel("Age:"));
        ageField = new JTextField();
        inputPanel.add(ageField);

        // Row 4: Course label and text field
        inputPanel.add(new JLabel("Course:"));
        courseField = new JTextField();
        inputPanel.add(courseField);

        // -----------------------------------------------------------------
        // SEARCH PANEL — a text field with a Search button to its right
        // -----------------------------------------------------------------
        JPanel searchPanel = new JPanel(new BorderLayout(5, 5));
        searchPanel.setBorder(BorderFactory.createTitledBorder("Search (by ID or Name)"));

        searchField = new JTextField(); // Keyword entry box
        JButton searchBtn = new JButton("Search"); // Triggers the search action

        searchPanel.add(searchField, BorderLayout.CENTER); // Text field fills available space
        searchPanel.add(searchBtn, BorderLayout.EAST); // Button sits at the right edge

        // -----------------------------------------------------------------
        // BUTTON PANEL — five action buttons in a centered horizontal row
        // FlowLayout.CENTER keeps buttons grouped and centered
        // -----------------------------------------------------------------
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton addBtn = new JButton("Add"); // Save a new student record
        JButton updateBtn = new JButton("Update"); // Overwrite an existing student record
        JButton deleteBtn = new JButton("Delete"); // Remove a student record
        JButton viewAllBtn = new JButton("View All"); // Reload and display all records
        JButton clearBtn = new JButton("Clear"); // Reset all input fields

        // Add all buttons to the panel in left-to-right order
        buttonPanel.add(addBtn);
        buttonPanel.add(updateBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(viewAllBtn);
        buttonPanel.add(clearBtn);

        // -----------------------------------------------------------------
        // TOP PANEL — combines input and search panels vertically
        // -----------------------------------------------------------------
        JPanel topPanel = new JPanel(new BorderLayout(5, 5));
        topPanel.add(inputPanel, BorderLayout.CENTER); // Input fields occupy the main area
        topPanel.add(searchPanel, BorderLayout.SOUTH); // Search bar sits below the input fields

        // -----------------------------------------------------------------
        // NORTH PANEL — stacks topPanel (input + search) above the buttons
        // -----------------------------------------------------------------
        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.add(topPanel, BorderLayout.CENTER); // Input and search in the middle
        northPanel.add(buttonPanel, BorderLayout.SOUTH); // Buttons at the bottom of the top section

        // -----------------------------------------------------------------
        // TABLE — displays student records in a scrollable grid
        // -----------------------------------------------------------------
        // Column headers shown at the top of the table
        String[] columns = { "ID", "Name", "Age", "Course" };

        // Create a custom DefaultTableModel that prevents the user from editing cells
        // directly
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            // isCellEditable returns false for all cells → read-only table
            public boolean isCellEditable(int row, int column) {
                return false; // Editing must go through the input fields + Update button
            }
        };

        table = new JTable(tableModel); // Build the visual table backed by tableModel
        table.setRowHeight(22); // Make each row 22 pixels tall for readability

        // Wrap the table in a scroll pane so a scrollbar appears when there are many
        // records
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Student Records")); // Labelled border

        // -----------------------------------------------------------------
        // ASSEMBLE — add the major panels to the JFrame
        // -----------------------------------------------------------------
        add(northPanel, BorderLayout.NORTH); // Input/search/buttons at the top
        add(scrollPane, BorderLayout.CENTER); // Table fills the remaining space

        // -----------------------------------------------------------------
        // EVENT LISTENERS — wire each button/interaction to its handler method
        // -----------------------------------------------------------------

        // "Add" button → call addStudent() when clicked
        addBtn.addActionListener(e -> addStudent());

        // "Update" button → call updateStudent() when clicked
        updateBtn.addActionListener(e -> updateStudent());

        // "Delete" button → call deleteStudent() when clicked
        deleteBtn.addActionListener(e -> deleteStudent());

        // "Search" button → call searchStudent() when clicked
        searchBtn.addActionListener(e -> searchStudent());

        // "View All" button → clear the search box and reload all records
        viewAllBtn.addActionListener(e -> {
            searchField.setText(""); // Clear any active search keyword
            viewAll(); // Reload all student records into the table
        });

        // "Clear" button → reset all text fields and deselect the table row
        clearBtn.addActionListener(e -> clearFields());

        // Table row click → auto-fill the input fields with the selected student's data
        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow(); // -1 if nothing is selected

            if (row >= 0) { // A valid row was selected
                // Populate each text field from the corresponding table column
                idField.setText(String.valueOf(tableModel.getValueAt(row, 0))); // Column 0 = ID
                nameField.setText(String.valueOf(tableModel.getValueAt(row, 1))); // Column 1 = Name
                ageField.setText(String.valueOf(tableModel.getValueAt(row, 2))); // Column 2 = Age
                courseField.setText(String.valueOf(tableModel.getValueAt(row, 3))); // Column 3 = Course
            }
        });

        // -----------------------------------------------------------------
        // STARTUP — ensure the data file exists, then load existing records
        // -----------------------------------------------------------------
        try {
            StudentFileIO.createFileIfNotExists(); // Create students.txt if it's the first run
        } catch (IOException ex) {
            showError("Error creating file: " + ex.getMessage()); // Alert user if creation fails
        }

        viewAll(); // Load all existing student records into the table on launch

        setLocationRelativeTo(null); // Center the window on the screen
        setVisible(true); // Make the window visible to the user
    }

    // =========================================================================
    // VALIDATION METHODS
    // =========================================================================

    /**
     * Validates that the given ID is exactly 8 numeric digits.
     *
     * Rules enforced:
     * - Must not be empty.
     * - Must match the regular expression \d{8} (exactly 8 digit characters).
     *
     * @param id The ID string entered by the user.
     * @return true if valid; false if invalid (also shows an error dialog).
     */
    private boolean isValidId(String id) {
        // Check for empty input first
        if (id.isEmpty()) {
            showError("ID is required!");
            return false;
        }

        // \d{8} means "exactly 8 digit characters (0-9)" — no letters or symbols
        // allowed
        if (!id.matches("\\d{8}")) {
            showError("ID must be exactly 8 digits (numbers only).\n"
                    + "Minimum: 8 digits, Maximum: 8 digits.");
            return false;
        }
        return true; // ID passed all checks
    }

    /**
     * Validates all four input fields before an Add or Update operation.
     *
     * Rules enforced:
     * - Name, age, and course must not be empty.
     * - ID must pass isValidId() (exactly 8 digits).
     * - No field may contain a comma (commas are the CSV delimiter in the data
     * file).
     * - Age must be parseable as an integer.
     *
     * @param id     ID field value.
     * @param name   Name field value.
     * @param age    Age field value.
     * @param course Course field value.
     * @return true if all fields are valid; false if any validation fails.
     */
    private boolean isValidAllFields(String id, String name, String age, String course) {
        // Ensure none of the non-ID fields are blank
        if (name.isEmpty() || age.isEmpty() || course.isEmpty()) {
            showError("All fields are required!");
            return false;
        }

        // Validate the ID using the dedicated ID validation method
        if (!isValidId(id))
            return false;

        // Commas would break the CSV format in the data file — disallow them everywhere
        if (name.contains(",") || age.contains(",") || course.contains(",")) {
            showError("Commas are not allowed in any field!");
            return false;
        }

        // Age must be a valid integer (e.g. "20", not "twenty" or "20.5")
        try {
            Integer.parseInt(age); // Attempt to parse; exception means it's not a number
        } catch (NumberFormatException ex) {
            showError("Age must be a number!");
            return false;
        }
        return true; // All fields passed validation
    }

    // =========================================================================
    // CRUD ACTION METHODS (called by button listeners)
    // =========================================================================

    /**
     * Reads the input fields and adds a new student to the data file.
     *
     * Steps:
     * 1. Trim whitespace from all field values.
     * 2. Validate all fields.
     * 3. Check that the ID is not already in use.
     * 4. Save the new student.
     * 5. Clear the input fields and refresh the table.
     */
    private void addStudent() {
        // Read and trim each input field value (trim removes leading/trailing spaces)
        String id = idField.getText().trim();
        String name = nameField.getText().trim();
        String age = ageField.getText().trim();
        String course = courseField.getText().trim();

        // Stop immediately if any field fails validation
        if (!isValidAllFields(id, name, age, course))
            return;

        // Prevent adding a second student with the same ID
        if (StudentFileIO.idExists(id)) {
            showError("Duplicate ID! A student with ID " + id + " already exists.");
            return;
        }

        try {
            // Create a new Student object and save it to the data file
            StudentFileIO.addStudent(new Student(id, name, age, course));
            showInfo("Student added successfully!"); // Inform the user of success
            clearFields(); // Reset the input form for the next entry
            viewAll(); // Refresh the table to show the newly added record
        } catch (IOException ex) {
            showError("Error: " + ex.getMessage()); // Show any file I/O error to the user
        }
    }

    /**
     * Reads the input fields and updates the matching student record in the data
     * file.
     *
     * Steps:
     * 1. Trim whitespace from all field values.
     * 2. Validate all fields.
     * 3. Attempt the update (returns false if the ID does not exist).
     * 4. Clear the input fields and refresh the table on success.
     */
    private void updateStudent() {
        // Read and trim each input field value
        String id = idField.getText().trim();
        String name = nameField.getText().trim();
        String age = ageField.getText().trim();
        String course = courseField.getText().trim();

        // Stop if any validation fails
        if (!isValidAllFields(id, name, age, course))
            return;

        try {
            // updateStudent returns true if it found and replaced the record
            boolean updated = StudentFileIO.updateStudent(
                    new Student(id, name, age, course));

            if (updated) {
                showInfo("Student updated successfully!"); // Notify the user
                clearFields(); // Reset the form
                viewAll(); // Refresh the table to show the updated data
            } else {
                showError("Student ID not found!"); // No record with that ID exists
            }
        } catch (IOException ex) {
            showError("Error: " + ex.getMessage()); // Show any file I/O error
        }
    }

    /**
     * Reads the ID field and deletes the matching student record after user
     * confirmation.
     *
     * Steps:
     * 1. Trim and validate the ID field.
     * 2. Ask the user to confirm the deletion (prevents accidental deletes).
     * 3. Attempt the delete (returns false if the ID does not exist).
     * 4. Clear the input fields and refresh the table on success.
     */
    private void deleteStudent() {
        String id = idField.getText().trim(); // Only the ID is needed to identify the record

        // Validate the ID before proceeding
        if (!isValidId(id))
            return;

        // Show a Yes/No confirmation dialog — safety net against accidental deletions
        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete student ID: " + id + "?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION);

        // If the user chose anything other than "Yes", abort the delete
        if (confirm != JOptionPane.YES_OPTION)
            return;

        try {
            // deleteStudent returns true if it found and removed the record
            boolean deleted = StudentFileIO.deleteStudent(id);

            if (deleted) {
                showInfo("Student deleted successfully!"); // Notify the user
                clearFields(); // Reset the form
                viewAll(); // Refresh the table (the deleted record is now gone)
            } else {
                showError("Student ID not found!"); // No record with that ID exists
            }
        } catch (IOException ex) {
            showError("Error: " + ex.getMessage()); // Show any file I/O error
        }
    }

    /**
     * Reads the search field keyword and displays only the matching student
     * records.
     *
     * The search is case-insensitive and matches any ID or Name that contains
     * the keyword as a substring (partial match).
     */
    private void searchStudent() {
        String keyword = searchField.getText().trim(); // The keyword entered by the user

        // Require at least something to search for
        if (keyword.isEmpty()) {
            showError("Enter ID or Name to search!");
            return;
        }

        // Retrieve matching rows from the file (2D array, one row per match)
        Object[][] results = StudentFileIO.searchStudents(keyword);

        tableModel.setRowCount(0); // Clear the current table contents before loading results

        // Add each matching student row to the table
        for (int i = 0; i < results.length; i++) {
            tableModel.addRow(results[i]); // Each results[i] is a 4-element Object array
        }

        // Inform the user if no matches were found
        if (results.length == 0)
            showInfo("No matching student found.");
    }

    /**
     * Loads all student records from the data file and displays them in the table.
     *
     * Called at startup and after every Add, Update, or Delete operation to
     * keep the table in sync with the data file.
     */
    private void viewAll() {
        // Retrieve all records as a 2D array from the file
        Object[][] rows = StudentFileIO.getAllStudents();

        tableModel.setRowCount(0); // Clear all existing rows from the table

        // Add each student row to the table model (which automatically updates the
        // JTable)
        for (int i = 0; i < rows.length; i++) {
            // rows[i][0] is the ID — skip rows where it is null (safety guard for empty
            // slots)
            if (rows[i][0] != null)
                tableModel.addRow(rows[i]);
        }
    }

    // =========================================================================
    // HELPER METHODS
    // =========================================================================

    /**
     * Clears all input text fields and removes any row selection in the table.
     *
     * Called after a successful Add/Update/Delete and when the Clear button is
     * clicked.
     */
    private void clearFields() {
        idField.setText(""); // Erase the ID field
        nameField.setText(""); // Erase the Name field
        ageField.setText(""); // Erase the Age field
        courseField.setText(""); // Erase the Course field
        searchField.setText(""); // Erase the Search field
        table.clearSelection(); // Deselect any highlighted row in the table
    }

    /**
     * Displays an informational pop-up dialog with the given message.
     *
     * Used to show success messages (e.g. "Student added successfully!").
     *
     * @param msg The message to show in the dialog body.
     */
    private void showInfo(String msg) {
        // showMessageDialog(parent, message, title, messageType)
        // INFORMATION_MESSAGE shows a blue "i" icon
        JOptionPane.showMessageDialog(this, msg, "Info",
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Displays an error pop-up dialog with the given message.
     *
     * Used to show validation errors and I/O failure messages.
     *
     * @param msg The error message to show in the dialog body.
     */
    private void showError(String msg) {
        // ERROR_MESSAGE shows a red "X" icon so errors are visually distinct from info
        JOptionPane.showMessageDialog(this, msg, "Error",
                JOptionPane.ERROR_MESSAGE);
    }
}
