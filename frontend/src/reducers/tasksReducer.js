import { GET_TASKS, CREATE_OR_UPDATE_TASK } from '../actions/types'

export const tasks = (tasks = [], action) => {
  switch (action.type) {
    case GET_TASKS:
      return action.tasks
    case CREATE_OR_UPDATE_TASK:
      const exist = tasks.some(task => task.id === action.task.id)
      if (exist) {
        return tasks.map(task => {
          if (task.id === action.task.id) {
            task = action.task
          }
          return task
        })
      } else {
        return [...tasks, action.task]
      }
    default:
      return tasks
  }
}