import { combineReducers } from 'redux'
import { ui } from './uiReducer'
import { tasks } from './tasksReducer'

export default combineReducers({
  ui,
  tasks
})