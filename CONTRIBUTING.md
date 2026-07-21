# Contributing Guide

## GitHub Setup

1. Create the GitHub repository under your account and name it `MediTrack`.
2. Add the other two teammates as collaborators from the repository settings.
3. Ask each teammate to clone the repo with their own GitHub account.
4. Protect `main` so nobody pushes directly without review.
5. Merge work only through pull requests.

## Workflow for the 3 teammates

- Create one GitHub repository for the team.
- Give each teammate write access to the repo.
- Work in separate branches for each feature or module.
- Open pull requests instead of pushing directly to `main`.
- Keep commits small and focused.

## Suggested branch ownership

- Teammate 1: setup, docs, base entities, enums, constants
- Teammate 2: services, validation, exceptions, CRUD logic
- Teammate 3: file I/O, tests, concurrency, patterns, optional AI feature

## Current working baseline

- `Main` already runs a console menu.
- `Doctor`, `Patient`, `Appointment`, `Bill`, and `BillSummary` are in place.
- `DoctorService`, `PatientService`, and `AppointmentService` already handle basic CRUD.
- `TestRunner` can be expanded with more manual checks.

## Before pushing

- Pull latest changes from `main`.
- Run and verify the project locally.
- Use clear commit messages.
- Avoid editing the same file at the same time unless the team agrees.
