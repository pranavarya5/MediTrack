<<<<<<< HEAD
# MediTrack
=======
# MediTrack

MediTrack is the group Java assignment for managing doctors, patients, appointments, and billing.
Name the GitHub repository `MediTrack` when you create the remote.
# MediTrack

MediTrack is the group Java assignment for managing doctors, patients, appointments, and billing.
Name the GitHub repository `MediTrack` when you create the remote.

## Repository Structure

- `docs/` - setup and JVM notes
- `src/main/java/com/airtribe/meditrack/` - Java source code
- `src/main/resources/` - CSV or other runtime files
- `src/main/java/com/airtribe/meditrack/contracts/` - interfaces used by the project

## Run the project

```bash
javac -d out src/main/java/com/airtribe/meditrack/Main.java
java -cp out com.airtribe.meditrack.Main
```

Run the manual test runner:

```bash
java -cp out com.airtribe.meditrack.test.TestRunner
```

You can also start the app with `--runTests` to execute the test runner from `Main`.

## How the team should work

1. One person keeps `main` stable.
2. Everyone else works on a feature branch.
3. Open a pull request back into `main` after review.
4. Pull latest `main` before starting new work.

## Note on interfaces package

The original rubric says `interface`, but Java reserves that keyword, so the working code uses `contracts` for `Searchable` and `Payable`.

## Branching Example

```bash
git checkout -b feature/patient-crud
git add .
git commit -m "Add patient CRUD skeleton"
git push origin feature/patient-crud
```

## Submission

Submit the GitHub repository link in the required email with subject:
`Java Assignment - [Your Name]`
>>>>>>> 1d21805 (Initial MediTrack project setup with core entities, services, and docs)
