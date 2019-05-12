import { SET_CREATE_TASK_DIALOG, SET_SNACKBAR } from './types'

export const openCreateTaskDialog = () => ({
  type: SET_CREATE_TASK_DIALOG,
  isCreateTaskDialogOpen: true
})

export const closeCreateTaskDialog = () => ({
  type: SET_CREATE_TASK_DIALOG,
  isCreateTaskDialogOpen: false
})

export const openSnackbar = message => ({
  type: SET_SNACKBAR,
  snackbar: {
    isOpen: true,
    message
  }
})

export const closeSnackbar = () => ({
  type: SET_SNACKBAR,
  snackbar: {
    isOpen: false,
    message: ''
  }
})