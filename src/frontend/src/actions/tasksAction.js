import { GET_TASKS, CREATE_OR_UPDATE_TASK } from './types'
import * as api from '../utils/apiTasks'

export const getTasks = () => dispatch =>
  api.getTasks().then(tasks => dispatch({
      type: GET_TASKS,
      tasks
    })
  )

export const createOrUpdateTask = task => ({
  type: CREATE_OR_UPDATE_TASK,
  task
})