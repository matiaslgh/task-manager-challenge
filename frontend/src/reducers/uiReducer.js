import { SET_CREATE_TASK_DIALOG, SET_SNACKBAR } from '../actions/types'

const initial = {
  isCreateTaskDialogOpen: false,
  snackbar: {
    isOpen: false,
    message: ''
  }
}

export const ui = (state = initial, action) => {
  switch (action.type) {
    case SET_CREATE_TASK_DIALOG:
      return {
        ...state,
        isCreateTaskDialogOpen: action.isCreateTaskDialogOpen
      }
    case SET_SNACKBAR:
      return {
        ...state,
        snackbar: action.snackbar
      }
    default:
      return state
  }
}