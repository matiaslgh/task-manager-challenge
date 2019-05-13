# Task Manager Challenge
Basic Kanban board with web sockets + failure simulation to represent a task manager

## Assignment
Create a task manager with a simple UI.

Features:
- Create tasks
- Visualize tasks
- Tasks can have 4 status:
  - `WAITING_FOR_RUNNING`
  - `RUNNING`
  - `COMPLETED`
  - `FAILED`

When a task is created, its status is `WAITING_FOR_RUNNING`. The tasks with this status can be manually run to change to the status `RUNNING`. If the task finishes correctly, its status will be `COMPLETED`, if there was an error, the status will be `FAILED`.
Only the `COMPLETED` tasks can show their result (If the number is prime or not. Explained below).

All the tasks do the same: They receive a number and return if it is prime, then wait 1 minute. After this minute, it will be `COMPLETED` unless there is an error.

Force an error with probability = 0.2. This simulation is to get some tasks with status `FAILED`

Constraint: Maximum 3 tasks can run simultaneously.

Notes: The system doesn't need persistence.

## Start backend
- ```cd src/backend```
- ```mvn spring-boot:run``` (maven is required)
> Runs on port 8080

## Start frontend
- ```cd src/frontend```
- ```npm install``` (node is required)
- ```npm start```
> Runs on port 3000
