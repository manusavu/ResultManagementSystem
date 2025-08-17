1.I have started this project to learn about jdbc and revise the java concepts.
2.During the development of the project i have faced many issues.
->Forward Compatibility issue: That is the version of the drivers need to be updated and should align with jdk.

1. Environment & Configuration Issues

->Problem: Java Version Mismatch:
_ **Symptom:** You received an `UnsupportedClassVersionError` (class file version 68.0 vs 65.0).
_ **Root Cause:** The project was compiled with a newer JDK (Java 24) but was being run with an older JRE (Java 21). \* **Solution/Learning:** You learned that Java is not forward-compatible. The solution was to align the development and runtime environments to the same Java version (Java 21 LTS). This led to learning how to manage multiple JDKs using tools like SDKMAN! and configuring them per-project in an IDE.

->Problem: Missing Database Driver
_ **Symptom:** Your application printed "No Driver found!" which was caused by a `ClassNotFoundException` for `com.mysql.cj.jdbc.Driver`.
_ **Root Cause:** The MySQL Connector/J JAR file was not included in the project's classpath at runtime. \* **Solution/Learning:** You learned the importance of the classpath. The fix was to add the MySQL JDBC driver as a dependency, either by adding the JAR file to the IDE's build path or by using a build tool like Maven/Gradle.

2. Database Design & Data Integrity

->Problem: Foreign Key Constraint Violation on Deletion** \* **Symptom:** You got an `SQLIntegrityConstraintViolationException` stating "Cannot delete or update a parent row." \* **Root Cause:** You tried to delete a record from a parent table (`STUDENTS`) while child records still existed in other tables (`PASSWORDS`, `MARKS`) that referenced it. \* **Solution/Learning:** You learned about **referential integrity** and foreign keys. The solution was to set up **`ON DELETE CASCADE`\*\* on your foreign keys. This taught the database to automatically delete child records when their parent record is deleted, simplifying your Java code.

->Problem: How to Enforce Unique Roll Numbers** \* **Symptom:** You needed to ensure no two students could have the same `ROLLNUMBER`. \* **Root Cause:** The database schema needed a constraint to enforce this business rule. \* **Solution/Learning:\*\* You learned the difference between a `PRIMARY KEY` and a `UNIQUE` constraint. You correctly made `ID` the primary key and added a separate `UNIQUE` constraint to the `ROLLNUMBER` column.

->Problem: How to Structure a Relational Schema** \* **Symptom:** You needed to create multiple related tables (`STUDENTS`, `MARKS`, `PASSWORDS`) from scratch. \* **Root Cause:** Understanding the correct order of creation and the rules for foreign keys. \* **Solution/Learning:\*\* You learned that parent tables must be created before the child tables that reference them. You also learned that a foreign key can reference either a `PRIMARY KEY` or any column with a `UNIQUE` constraint, which clarified your schema design.

3. Application Logic & SQL Queries

->Problem: Efficiently Checking if a Record Exists** \* **Symptom:** Before adding a new student, you needed to check if their `ROLLNUMBER` was already in the database. \* **Root Cause:** Needed an efficient SQL query to perform this check without retrieving unnecessary data. \* **Solution/Learning:\*_ You implemented a Java method that uses the `SELECT COUNT(_)` query. This is the standard, performant way to check for the existence of a record.
